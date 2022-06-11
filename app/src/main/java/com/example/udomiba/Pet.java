package com.example.udomiba;


import android.location.Address;
import android.location.Geocoder;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Entity(tableName = "pets")
public class Pet {
    @PrimaryKey(autoGenerate = true)
    private int petId;
    @ColumnInfo(name="name")
    String name;
    @ColumnInfo(name="description")
    String description;
    @ColumnInfo(name="photo")
    int photo;
    @ColumnInfo(name="vaccinated")
    String vaccinated;
    @ColumnInfo(name="gender")
    String gender;
    @ColumnInfo(name="birthdate")
    String birthdate;
    @ColumnInfo(name="ownerId")
    int ownerId;
    @ColumnInfo(name="species")
    String species;
    @ColumnInfo(name="lat")
    double lat;
    @ColumnInfo(name="lon")
    double lon;

    @Ignore
    Geocoder geocoder = new Geocoder(MainActivity.context);
    @Ignore
    List<Address> addresses;



    public Pet(String name, String description, String vaccinated, String gender, String birthdate, int ownerId, int photo, double lat, double lon) {
        this.name = name;
        this.description = description;
        this.vaccinated = vaccinated;
        this.gender = gender;
        this.birthdate = birthdate;
        this.ownerId = ownerId;
        this.photo = photo;
        this.lat = lat;
        this.lon = lon;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int id) {
        this.petId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(String vaccinated) {
        this.vaccinated = vaccinated;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }


    public String lonlatToString() throws IOException {
        String city;
        addresses = geocoder.getFromLocation(this.lat, this.lon, 1);
        city=addresses.get(0).getLocality();
        return city;
    }
}
