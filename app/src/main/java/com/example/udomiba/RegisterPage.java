package com.example.udomiba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {

    EditText email, phoneNumber, password, password2;
    public static final String EXTRA_ID="";
    public static final String CHANNEL_ID="";
    //Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + RegisterPage.this.getPackageName() + "/" + R.raw.puppybarking);
    //AudioAttributes attributes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        email=findViewById(R.id.register_email);
        phoneNumber=findViewById(R.id.register_phone);
        password=findViewById(R.id.register_password);
        password2=findViewById(R.id.register_password2);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "šanel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

            //attributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build();
        }
    }

    public void onRegisterButton(View view){
        String newMail = email.getText().toString();
        User user; user=UdomiDatabase.getInstance(this).userDAO().getByEmail(newMail);
        if(user==null){
            User newUser = new User (email.getText().toString(), phoneNumber.getText().toString(), password.getText().toString());
            UdomiDatabase.getInstance(this).userDAO().addUser(newUser);
            int newId = newUser.getUserId();
            Intent intent = new Intent(RegisterPage.this, LoginPage.class);
            intent.putExtra(EXTRA_ID, newId);
            startActivity(intent);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(RegisterPage.this, CHANNEL_ID);
            builder.setContentTitle("Udomi")
                    .setContentText("Registracija uspješna")
                    .setSmallIcon(R.drawable.dog)
                    //.setSound(sound)
            /*.addAction(neki drawable neshto, "šta se radi", intent)*/
            ;
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(RegisterPage.this);
            notificationManagerCompat.notify(1, builder.build());
            Toast.makeText(this, "Registracija uspješna", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Email adresa zauzeta", Toast.LENGTH_SHORT).show();
        }
    }
}
