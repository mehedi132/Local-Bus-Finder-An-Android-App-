package com.example.localbusfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class search extends AppCompatActivity {
private AutoCompleteTextView from,to;
private String[] routename;

    public String a;
//private Button findbus;
    private Button findbus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        from=findViewById(R.id.From);
        to=findViewById(R.id.To);
        routename=getResources().getStringArray(R.array.Route);
        ArrayAdapter<String> adapter= new  ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,routename);
        from.setThreshold(1);
        from.setAdapter(adapter);
        to.setThreshold(1);
        to.setAdapter(adapter);

        Intent intent = getIntent();
        a= intent.getStringExtra("b1");





        // innitialization
       findbus=(Button) findViewById(R.id.button_search);



    }

    public void map(View v)
    {
        Intent mapIntent= new Intent(getApplicationContext(), allBus_location.class);
        mapIntent.putExtra("b1", a);


        startActivity(mapIntent);
    }

    public void loginUser(View view) {
            isUser();


        }

        private void isUser() {

            final String start= from.getText().toString();
            final String des =to.getText().toString();
            final String com= start.concat(des);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("route");
            Query checkUser = reference.orderByChild("start").equalTo(com);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {

                        String bus10 =dataSnapshot.child(com).child("bus1").getValue(String.class);
                        String  bus20=dataSnapshot.child(com).child("bus2").getValue(String.class);
                        String  bus30=dataSnapshot.child(com).child("bus3").getValue(String.class);
                        String var =dataSnapshot.child(com).child("vara").getValue(String.class);
                        String r1 =dataSnapshot.child(com).child("d1").getValue(String.class);
                        String r2 =dataSnapshot.child(com).child("d2").getValue(String.class);

                                Toast.makeText(search.this, bus10, Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(getApplicationContext(), showbus.class);
                        intent.putExtra("b1", bus10 );
                        intent.putExtra("b2", bus20 );
                        intent.putExtra("b3", bus30 );
                        intent.putExtra("va", var );
                        intent.putExtra("so", start );
                        intent.putExtra("dis", des );
                        intent.putExtra("r1", r1 );
                        intent.putExtra("r2",r2 );


                        startActivity(intent);


                    }
                    else
                    {
                        Toast.makeText(search.this, "Noroute", Toast.LENGTH_SHORT).show();
                    }


                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {


                }
            });

    }


}