package com.example.udomiba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyPet extends AppCompatActivity {
    private ImageView imageView;
    private TextView name;
    private TextView description;
    private TextView birthdate;
    private TextView gender;
    private TextView vaccinated;
    private Button button;
    int id;
    Pet pet;
    TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pet);

        imageView=findViewById(R.id.petimage);
        name=findViewById(R.id.petname_data);
        description=findViewById(R.id.description_data);
        birthdate=findViewById(R.id.birthdate_data);
        gender=findViewById(R.id.gender_data);
        vaccinated=findViewById(R.id.vaccinated_data);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        if(extra!=null){

            id=extra.getInt(MyPetsFragment.EXTRA_ID);
            pet=UdomiDatabase.getInstance(this).petDAO().getById(id);
            name.setText(pet.getName());
            description.setText(pet.getDescription());
            birthdate.setText(pet.getBirthdate());
            gender.setText(pet.getGender());
            vaccinated.setText(pet.getVaccinated());
            imageView.setImageResource(pet.getPhoto());
            location.setText(pet.getCity());
        }

        button = (Button) findViewById(R.id.adopt_pet_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToEditPet(v);
            }
        });
    }
    public void moveToEditPet(View view){
        Intent intent = new Intent(this, EditPet.class);
        startActivity(intent);
    }
}