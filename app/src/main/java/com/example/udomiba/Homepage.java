package com.example.udomiba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class Homepage extends AppCompatActivity {
    private ListView listView;
/**/
    public static final String IMAGE = "EXTRA_IMAGE";
    public static final String NAME = "EXTRA_NAME";
    public static final String DESCRIPTION = "EXTRA_DESCRIPTION";
    public static final String BIRTHDATE = "EXTRA_BIRTHDATE";
    public static final String VACCINATED = "EXTRA_VACCINATED";
    public static final String GENDER = "EXTRA_GENDER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        listView = (ListView) findViewById(R.id.list_view);

        List<Pet> petList = new ArrayList<>();
        petList.add(new Pet("Candy", "ja sam mawa maca", R.drawable.cat1, "vakcinisana", "ženka", "2020-12-02"));
        petList.add(new Pet("Nugget", "ja sam mawi doggo", R.drawable.dog1, "vakcinisan", "mužjak", "2016-02-03"));
        petList.add(new Pet("Snjeguljica", "nisam ja odavle", R.drawable.bunny1, "nevakcinisana", "ženka", "2019-03-08"));

        CustomListViewAdapter customListViewAdapter = new CustomListViewAdapter(this, petList);
        listView.setAdapter(customListViewAdapter);
        listView.setOnItemClickListener(onItemClickListener);
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Pet pet = (Pet) adapterView.getItemAtPosition(i);

             Intent intent = new Intent(Homepage.this, ShowPet.class);

             intent.putExtra(NAME,pet.getName());
            intent.putExtra(DESCRIPTION,pet.getDescription());
            intent.putExtra(IMAGE,pet.getPhoto());
            intent.putExtra(VACCINATED,pet.getVaccinated());
            intent.putExtra(GENDER,pet.getGender());
            intent.putExtra(BIRTHDATE,pet.getBirthdate());

            startActivity(intent);

        }
    };
}