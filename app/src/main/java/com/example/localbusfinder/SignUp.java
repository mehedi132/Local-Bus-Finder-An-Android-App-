package com.example.localbusfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUp extends AppCompatActivity {
    //Variables
    TextInputLayout regName, regUsername,
            regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        regName = findViewById(R.id.name);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regPhoneNo = findViewById(R.id.phone);
        regPassword = findViewById(R.id.password);
        regBtn = findViewById(R.id.reg);
        regToLoginBtn = findViewById(R.id.reg_login_btn);




        //Register Button method end
    }//onCreate Method End
    public void SignUp(View vo) {
        Toast.makeText(SignUp.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
        reg();
        Intent intent = new Intent(SignUp.this, login.class);
        startActivity(intent);
    }
    private void reg() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
//Get all the values
        String name =regName.getEditText().getText().toString().trim();
        String username = regUsername.getEditText().getText().toString().trim();
        String email = regEmail.getEditText().getText().toString().trim();
        String phoneNo = regPhoneNo.getEditText().getText().toString().trim();
        String password = regPassword.getEditText().getText().toString().trim();

        UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
        reference.child(username).setValue(helperClass);
    }


    public void Signin_return(View vo) {
        Intent intent = new Intent(SignUp.this, login.class);
        startActivity(intent);


    }
}

