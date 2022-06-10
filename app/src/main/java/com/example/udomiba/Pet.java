package com.example.udomiba;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pets")
public class Pet {
    @PrimaryKey(autoGenerate = true)
    private int petId;
    String name;
    String description;
    int photo;
    String vaccinated;
    String gender;
    String birthdate;
    int adopted;
    int ownerId;

    public Pet(String name, String description, int photo, String vaccinated, String gender, String birthdate, int ownerId) {
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.vaccinated = vaccinated;
        this.gender = gender;
        this.birthdate = birthdate;
        this.adopted=0;
        this.ownerId = ownerId;
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
}
