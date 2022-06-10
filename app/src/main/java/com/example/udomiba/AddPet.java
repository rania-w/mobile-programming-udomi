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
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class AddPet extends AppCompatActivity   {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    public static final int CAMERA=9;
    ImageButton imageButton;
    Bitmap bitmap;

    EditText name, description;
    RadioGroup gender, vaccinated;
    RadioButton male, female, vacc, nvacc/*, genderButton, vaccButton*/;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        name=findViewById(R.id.add_name);
        description=findViewById(R.id.add_description);
        gender=findViewById(R.id.radioGroupGender);
        vaccinated=findViewById(R.id.radioGroupVaccinated);
        male=findViewById(R.id.male_radio_button);
        female=findViewById(R.id.female_radio_button);
        vacc=findViewById(R.id.vaccinated_radio_button);
        nvacc=findViewById(R.id.notvaccinated_radio_button);

        imageButton=findViewById(R.id.add_image_button);
        bitmap=null;

        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
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
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    public void onLocationClick(View view){
        Intent intent = new Intent(this, Map.class);
        startActivity(intent);
    }

    /*
    * add image
    * */

    Uri photoUri;
    public void onImageButton(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {

            startActivityForResult(intent, CAMERA);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA && resultCode == RESULT_OK) {
            Bitmap thumbnail = data.getParcelableExtra("data");
            if(thumbnail!=null){
                imageButton.setImageBitmap(thumbnail);
                Uri tempUri = getImageUri(getApplicationContext(), thumbnail);
                File finalFile = new File(getRealPathFromURI(tempUri));
                photoUri = getImageUri(this, thumbnail);
            }
        }

    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        Bitmap OutImage = Bitmap.createScaledBitmap(inImage, 1000, 1000, true);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), OutImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri){
            String path = "";
            if (getContentResolver() != null) {
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cursor.getString(idx);
                    cursor.close();
                }
            }
            return path;
        }


    public void onSave(View view){
        Intent intent = new Intent(this, MainActivity.class);
        /*int selectedIdGender = gender.getCheckedRadioButtonId();
        int selectedIdVacc = vaccinated.getCheckedRadioButtonId();
        genderButton = findViewById(selectedIdGender);
        vaccButton= findViewById(selectedIdVacc);*/


        String s="";
        String x="";

        if(male.isSelected()){
            s = male.getText().toString();
        } else if (female.isSelected()){
            s=female.getText().toString();
        }
        if(vacc.isSelected()) x=vacc.getText().toString();
        else if(nvacc.isSelected()) x=nvacc.getText().toString();

        Bundle bundle = getIntent().getExtras();
        int id=bundle.getInt(MyPetsFragment.EXTRA_ID);
        //can't add images so hardcoded for now
        Pet pet;
        pet = new Pet(name.getText().toString(), description.getText().toString(), getRealPathFromURI(photoUri), x, s, dateButton.getText().toString(), id);
        UdomiDatabase.getInstance(this).petDAO().addPet(pet);
        startActivity(intent);
    }
}
