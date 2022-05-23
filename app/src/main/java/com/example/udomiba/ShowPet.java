package com.example.udomiba;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowPet extends AppCompatActivity {
    private ImageView imageView;
    private TextView name;
    private TextView description;
    private TextView birthdate;
    private TextView gender;
    private TextView vaccinated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pet);

        imageView=findViewById(R.id.petimage);
        name=findViewById(R.id.petname_data);
        description=findViewById(R.id.description_data);
        birthdate=findViewById(R.id.birthdate_data);
        gender=findViewById(R.id.gender_data);
        vaccinated=findViewById(R.id.vaccinated_data);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        if(extra!=null){

            name.setText(extra.getString(com.example.udomiba.MainActivity.NAME));
            description.setText(extra.getString(com.example.udomiba.MainActivity.DESCRIPTION));
            imageView.setImageResource(extra.getInt(com.example.udomiba.MainActivity.IMAGE));
            birthdate.setText(extra.getString(com.example.udomiba.MainActivity.BIRTHDATE));
            gender.setText(extra.getString(com.example.udomiba.MainActivity.GENDER));
            vaccinated.setText(extra.getString(com.example.udomiba.MainActivity.VACCINATED));
        }

    }


}