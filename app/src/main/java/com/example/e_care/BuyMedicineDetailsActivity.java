package com.example.e_care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    TextView tvPackageName,tvTotalCost;
    EditText edDetails;
    Button btnBack,btnAddtoCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);
        tvPackageName = findViewById(R.id.textViewBMCartTitle);
        tvTotalCost = findViewById(R.id.textViewBMCartTotalPrice);
        edDetails = findViewById(R.id.listViewBMCart);
        edDetails.setKeyListener(null);
        btnBack = findViewById(R.id.buttonBMBack);
        btnAddtoCart = findViewById(R.id.buttonBMCartCheckout);

        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("line1"));
        edDetails.setText(intent.getStringExtra("line2"));
        tvTotalCost.setText("Total  Cost : "+intent.getStringExtra("line3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
            }
        });
        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("line3").toString());
                MyDatabase db = new MyDatabase(getApplicationContext(),"ecare",null,1);
                if(db.checkCart(username,product) == 1){
                    Toast.makeText(getApplicationContext(), "Already in Cart", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addCart(username,product,price,"Medicine");
                    Toast.makeText(getApplicationContext(), "Inserted to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
                }
            }
        });
    }
}