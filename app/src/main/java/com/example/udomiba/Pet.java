package com.example.udomiba;

import android.location.Address;
import android.location.Location;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

    public Pet(String name, String description, String vaccinated, String gender, String birthdate, int ownerId, String species, double lat, double lon) {
        this.name = name;
        this.description = description;
        this.vaccinated = vaccinated;
        this.gender = gender;
        this.birthdate = birthdate;
        this.ownerId = ownerId;
        this.species = species;
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
}
