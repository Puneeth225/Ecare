package com.example.e_care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages = {
            {"Paracetamol SuperX","","","","120"},
            {"Dolo 650","","","","95"},
            {"Vitamin Booster D3","","","","180"},
            {"Eat and Die T250","","","","410"},
            {"Metaformin 500mg","","","","315"},
            {"Crocin newMeta-300","","","","150"},
            {"Glucovita Tablets 200mg","","","","450"},
            {"SuperDeluxe-350","","","","195"},
            {"Betadene 100mg","","","","100"},
    };
    private String[] package_details = {
            "Keeps you cure and always fit\n"+
                    "An analgesic and antipyretic drug that is used to temporarily relieve mild-to-moderate pain and fever.  \n"+
                    "Paracetamol inhibits the production of prostaglandins, which are made by the body to deal with illness and injury",
            "Dolo 650 Tablet helps relieve pain and fever by blocking the release of certain chemical messengers responsible for fever and pain.",
            "Aquasol A Capsule is a medicine used in the treatment of vitamin A deficiency\n"+
                    "It also helps the heart, lungs, kidneys, and other organs function properly.",
            "Vitamin A is an essential nutrient that helps the body to perform various functions. \n"+
                    "It acts directly on the biosynthesis of various proteins, including those involved in regulation of various cell functions ",
            "Metformin is an FDA-approved antidiabetic agent that manages high blood sugar levels.",
            "Crocin Advance 500mg Tablet helps relieve pain and fever by blocking the release of certain chemical messengers responsible for fever and pain. ",
            "Glucon-D is India’s No. 1 Glucose powder brand. Launched in 1933, it boasts of a rich heritage of over 85 years.\n"+
                    "Presenting Glucon-D ImmunoVolt - specially made to build kid’s immunity in a tasty way",
            "Thricof Syrup is a medicine used to treat dry cough. \n"+
                    "pThe most common side effects are nausea, stomach pain, diarrhea, upset stomach, vomiting, loss of appetite, headache, insomnia, rash, and itching",
            "Betadine 10% Solution is an antiseptic and disinfectant agent.",
    };

    HashMap<String,String>item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,btnGotoCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        lst = findViewById(R.id.ListViewHA);
        btnBack = findViewById(R.id.buttonBMback);
        btnGotoCart = findViewById(R.id.buttonBMGoToCart);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });
        btnGotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });
        list = new ArrayList();
        for(int i=0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost: "+packages[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                it.putExtra("line1",packages[i][0]);
                it.putExtra("line2",package_details[i]);
                it.putExtra("line3",packages[i][4]);
                startActivity(it);
            }
        });
    }
}