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

public class ArticlesActivity extends AppCompatActivity {

    private String[][] health_details = {
            {"Old age Diet","","","","More Details..."},
            {"Cardio Health","","","","More Details..."},
            {"Healthy Living Tips","","","","More Details..."},
            {"Mental Health","","","","More Details..."},
            {"Diabetes Diet","","","","More Details..."}
    };
    private int[] images = {
            R.drawable.article1,
            R.drawable.article2,
            R.drawable.article3,
            R.drawable.article4,
            R.drawable.article5
    };
    HashMap<String,String>item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnBack;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);
        btnBack = findViewById(R.id.buttonHABack);
        lst = findViewById(R.id.ListViewHA);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ArticlesActivity.this,HomeActivity.class));
            }
        });
        list = new ArrayList();
        for(int i=0;i<health_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",health_details[i][0]);
            item.put("line2",health_details[i][1]);
            item.put("line3",health_details[i][2]);
            item.put("line4",health_details[i][3]);
            item.put("line5",health_details[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(ArticlesActivity.this, ArticlesDetailsActivity.class);
                it.putExtra("text1",health_details[i][0]);
                it.putExtra("text2",images[i]);
                startActivity(it);
            }
        });
    }
}