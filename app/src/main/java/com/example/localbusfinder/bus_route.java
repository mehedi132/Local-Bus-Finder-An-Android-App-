package com.example.localbusfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class bus_route extends AppCompatActivity {
    private TextView t1,t2,d2,d1,d3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_route);
        t1=findViewById(R.id.from);
        t2=findViewById(R.id.to);
        d1=findViewById(R.id.r1);
        d2= findViewById(R.id.r2);
        d3= findViewById(R.id.r3);
        //d1= findViewById(R.id.to);
        showdata();

    }

    private void showdata() {

        Intent intent = getIntent();

        String a= intent.getStringExtra("so");
        String b= intent.getStringExtra("des");
        String c= intent.getStringExtra("d1");
        String d= intent.getStringExtra("d2");
        String e= intent.getStringExtra("d3");

        Toast.makeText(bus_route.this,c+"\n"+d, Toast.LENGTH_SHORT).show();

        // String f= intent.getStringExtra("va");

        t1.setText(a);
        t2.setText(b);
        if(c==null)
        {
            d1.setVisibility(View.GONE);

        }
        else {
            d1.setText(c);
        }

        if(d==null)
        {
            d2.setVisibility(View.GONE);

        }
        else {
            d2.setText(d);
        }

        if(e==null)
        {
            d3.setVisibility(View.GONE);

        }
        else {
            d3.setText(e);
        }





    }

}