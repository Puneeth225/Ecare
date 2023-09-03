package com.example.e_care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewRegistersAvtivity extends AppCompatActivity {

    EditText edUserName,edEmail,edPassword,edConfirmp;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_register_avtivity);

        edUserName = findViewById(R.id.editTextBMBFullName);
        edEmail = findViewById(R.id.editTextBMBAddress);
        edPassword = findViewById(R.id.editTextBMBPincode);
        edConfirmp = findViewById(R.id.editTextBMBContact);
        btn = findViewById(R.id.buttonBMBBook);
        tv = findViewById(R.id.textViewExistingUser);
        //String email = edEmail.getText().toString();

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewRegistersAvtivity.this,LoginActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUserName.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirmPass = edConfirmp.getText().toString();
                MyDatabase db = new MyDatabase(getApplicationContext(),"ecare",null,1);
                if(username.length() == 0 || email.length()==0 || password.length() == 0 || confirmPass.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.compareTo(confirmPass) == 0){
                        if(isValid(password)){
                            if(ValidEmail(email)){
                                db.register(username,email,password);
                                Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(NewRegistersAvtivity.this,LoginActivity.class));
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Password must contain a digit, letter and special symbol with length 8", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Password's didn't match!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean ValidEmail(String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValid(String password){
        int chk1 = 0,chk2 =0,chk3 = 0;
        if(password.length()<8){
            return false;
        }
        else{
            for(int i=0;i<password.length();i++){
                if(Character.isLetter(password.charAt(i))){
                    chk1 = 1;
                }
            }
            for(int i=0;i<password.length();i++){
                if(Character.isDigit(password.charAt(i))){
                    chk2 = 1;
                }
            }
            for(int i=0;i<password.length();i++){
                char c = password.charAt(i);
                if(c>=33 && c<=46 || c==64){
                    chk3 = 1;
                }
            }
            if(chk1==1 && chk2==1 && chk3==1){
                return true;
            }
            return false;
        }
    }
}