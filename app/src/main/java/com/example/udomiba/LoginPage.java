package com.example.udomiba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
    //Button prijaviSe = (Button) findViewById(R.id.prijavi_se_button);
    EditText email, password;
    public static final String EXTRA_ID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        email=findViewById(R.id.login_email);
        password=findViewById(R.id.login_password);
    }

    public void onRegisterClick(View view){
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }

    public void onLoginClick(View view){
        User user = UdomiDatabase.getInstance(this).userDAO().verifyLogin(email.getText().toString(), password.getText().toString());
        if(user==null){
            Toast.makeText(this, "Korisnik ne postoji/netačna lozinka", Toast.LENGTH_SHORT).show();
        } else {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_ID, user.getUserId());
        startActivity(intent)
        ;}
    }

}