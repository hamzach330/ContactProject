package com.example.hamza.contacts;

import android.provider.SyncStateContract;

import java.util.List;

/**
 * Created by hamza on 9/27/2017.
 */

public class Contacts {

    int id;
    String name;
    String number;
    public static List<Contacts> records;

    //Empty Constructor
    public Contacts() {
    }

    // Constructor

    public Contacts(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public Contacts(String name, String number) {
        this.name = name;
        this.number = number;
    }


    //Getter and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
