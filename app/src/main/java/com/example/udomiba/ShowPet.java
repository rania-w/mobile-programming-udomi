package com.example.udomiba;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
    int id;
    Pet pet;
    TextView location;
    String locationText;

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
        location=findViewById(R.id.location_data);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        if(extra!=null){
            id=extra.getInt(HomepageFragment.EXTRA_ID);
            pet=UdomiDatabase.getInstance(this).petDAO().getById(id);
            name.setText(pet.getName());
            description.setText(pet.getDescription());
            birthdate.setText(pet.getBirthdate());
            gender.setText(pet.getGender());
            vaccinated.setText(pet.getVaccinated());
            imageView.setImageResource(pet.getPhoto());
            location.setText(pet.getCity());

        }

    }

    public void dialPhoneNumber(String phoneNumber) {
        phoneNumber = UdomiDatabase.getInstance(this).petDAO().getPhoneByPetId(id);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void onAdopt(View view){
        String num = UdomiDatabase.getInstance(this).petDAO().getPhoneByPetId(id);
        dialPhoneNumber(num);
    }
}