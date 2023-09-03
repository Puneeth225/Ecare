package com.example.e_care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailActivity extends AppCompatActivity {
    private String[][] doctor_list1 = {
            {"Satpal Singh", "New Delhi","Exp : 2yrs","9885145269","300"},
            {"Ajay Verma", "Old Delhi","Exp : 4yrs","9022698569","3000"},
            {"Mahadev Sharma", "Jammu","Exp : 9yrs","9885693256","700"},
            {"Lakhbinder Kaur", "Agra Cantt","Exp : 21yrs","9801118569","100"},
            {"Munish Dev", "Jodhpur","Exp : 14yrs","7685698569","500"},
    };
    private String[][] doctor_list2 = {
            {"Deo Arora", "Mathura","Exp : 2yrs","9885145269","300"},
            {"Sukhdev Masatani", "Delhi","Exp : 4yrs","9022698569","3000"},
            {"Raju Chand", "Bangalore","Exp : 9yrs","9885693256","700"},
            {"Baljeet Singh", "Jalandhar Cantt","Exp : 21yrs","9801118569","100"},
            {"Ramu Jio", "Shimla","Exp : 14yrs","7685698569","500"},
    };
    private String[][] doctor_list3 = {
            {"Savdhaan Sharma", "New Delhi","Exp : 2yrs","9885145269","300"},
            {"Yuvraj Gogoy", "Okla","Exp : 4yrs","9022698569","3000"},
            {"Gopal Dutta", "Delhi DEE","Exp : 9yrs","9885693256","700"},
            {"Manhus Tung", "Mumbai","Exp : 21yrs","9801118569","100"},
            {"Akram Sahay", "Kolkata","Exp : 14yrs","7685698569","500"},
    };
    private String[][] doctor_list4 = {
            {"Roja Mor", "Nutanix","Exp : 2yrs","9885145269","300"},
            {"Siddhu Jat", "Faridabad","Exp : 4yrs","9022698569","3000"},
            {"Dundold jay", "Delhi","Exp : 9yrs","9885693256","700"},
            {"Lammy Roy", "Jaipur","Exp : 21yrs","9801118569","100"},
            {"Punder singh", "Solapur","Exp : 14yrs","7685698569","500"},
    };
    private String[][] doctor_list5 = {
            {"Abhinay Faluda", "Karachi","Exp : 2yrs","9885145269","300"},
            {"Govind Maurya", "Old Delhi","Exp : 4yrs","9022698569","3000"},
            {"Manish Watty", "Goa","Exp : 9yrs","9885693256","700"},
            {"Philip Schulz", "Punjab","Exp : 21yrs","9801118569","100"},
            {"Jack Tommy", "Kurukshetra","Exp : 14yrs","7685698569","500"},
    };
    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonBMGoToCart);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("General Physician") == 0){
            doctor_details = doctor_list1;
        }
        else
        if(title.compareTo("Diabetician") == 0){
            doctor_details = doctor_list2;
        }
        else
        if(title.compareTo("Dentist") == 0){
            doctor_details = doctor_list3;
        }
        else
        if(title.compareTo("Surgeon") == 0){
            doctor_details = doctor_list4;
        }
        else
            doctor_details = doctor_list5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailActivity.this,FindDoctorActivity.class));
            }
        });
        list = new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1","Doctor Name: "+doctor_details[i][0]);
            item.put("line2","Address: "+doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4","Mobile: "+doctor_details[i][3]);
            item.put("line5","Fees: "+doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst = findViewById(R.id.ListViewHA);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}