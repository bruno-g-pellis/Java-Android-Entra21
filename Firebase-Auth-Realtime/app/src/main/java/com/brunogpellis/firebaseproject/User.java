package com.brunogpellis.firebaseproject;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {

    public  String idUser;
    public String nameUser;
    public String lastNameUser;
    public String emailUser;


    public User(String idUser, String nameUser, String lastNameUser, String emailUser) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.lastNameUser = lastNameUser;
        this.emailUser = emailUser;
    }

    public User() {

    }


    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getLastNameUser() {
        return lastNameUser;
    }

    public void setLastNameUser(String lastNameUser) {
        this.lastNameUser = lastNameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public void saveUser(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("users").child(getIdUser()).setValue(this);
    }
}
