package com.example.udomiba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginPage extends AppCompatActivity {
    //Button prijaviSe = (Button) findViewById(R.id.prijavi_se_button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }

    public void moveToHomepage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void moveToRegisterPage(View view){
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }

}