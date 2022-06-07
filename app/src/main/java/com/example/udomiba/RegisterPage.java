package com.example.udomiba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {

    EditText email, phoneNumber, password, password2;
    public static final String EXTRA_ID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        email=findViewById(R.id.register_email);
        phoneNumber=findViewById(R.id.register_phone);
        password=findViewById(R.id.register_password);
        password2=findViewById(R.id.register_password2);
    }

    public void onRegisterButton(View view){
        String newMail=email.getText().toString();
        User user;
        user = UdomiDatabase.getInstance(this).userDAO().getByEmail(newMail);
        if(user==null && password.getText().toString().equals(password2.getText().toString())){
            User newUser = new User(email.getText().toString(), phoneNumber.getText().toString(), password.getText().toString());
            UdomiDatabase.getInstance(this).userDAO().addUser(newUser);
            int newId = newUser.getUserId();
            //upon registration the user is brought back to the register page for reasons
            Intent intent = new Intent(this, LoginPage.class);
            intent.putExtra(EXTRA_ID, newId);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Žao nam je, email adresa koju ste unijeli je već uzeta", Toast.LENGTH_SHORT).show();
        }
    }
}
