package com.example.udomiba;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;

public class AddPet extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    public static final String EXTRA_ID = "";

    EditText name, description;
    RadioGroup gender, vaccinated;
    RadioButton male, female, vacc, nvacc;

    TextView locdata;
    Spinner species;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        Toast.makeText(AddPet.this, "Molimo Vas, prvo dodajte lokaciju", Toast.LENGTH_LONG).show();

        name = findViewById(R.id.add_name);
        description = findViewById(R.id.add_description);
        gender = findViewById(R.id.radioGenderGroup);
        vaccinated = findViewById(R.id.radioGroupVaccinated);
        male = findViewById(R.id.male_radio_button);
        female = findViewById(R.id.female_radio_button);
        vacc = findViewById(R.id.vaccinated_radio_button);
        nvacc = findViewById(R.id.notvaccinated_radio_button);

        species = findViewById(R.id.species_spinner);

        locdata = findViewById(R.id.location_data);


        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.species, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        species.setAdapter(adapter);
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;
                String date = makeDateString(dayOfMonth, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);


    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public void onLocationClick(View view) {
        Intent intent = new Intent(AddPet.this, Map.class);
        startActivity(intent);
    }

    public void onSave(View view) {
        Intent intent = new Intent(this, MainActivity.class);//after svega

        Intent locIntent = new Intent(this, Map.class);
        locIntent = getIntent();
        Bundle b = locIntent.getExtras();
        String city = b.getString(Map.EXTRA_LOCATION);
        Log.i("debug", city);

        String s;
        String x;
        if (male.isSelected()) {
            s = male.getText().toString();
        } else {
            s = female.getText().toString();
        }
        if (vacc.isSelected()) x = vacc.getText().toString();
        else x = nvacc.getText().toString();

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt(MyPetsFragment.EXTRA_ID);

        int photoId;

        String sp = species.getSelectedItem().toString();

        switch (sp) {
            case "Pas":
                photoId = (R.drawable.dog1);
                break;
            case "Mačka":
                photoId = (R.drawable.cat1);
                break;
            case "Zec":
                photoId = (R.drawable.bunny1);
                break;
            case "Ptica":
                photoId = R.drawable.birb;
                break;
            case "Hrčak":
                photoId = R.drawable.hamster;
                break;
            default:
                photoId = (R.drawable.dog);
        }

        Pet pet;
        pet = new Pet(name.getText().toString(), description.getText().toString(), x, s, dateButton.getText().toString(), id, photoId, city);


        UdomiDatabase.getInstance(this).petDAO().addPet(pet);
        intent.putExtra(EXTRA_ID, pet.getPetId());
        startActivity(intent);
    }

}
