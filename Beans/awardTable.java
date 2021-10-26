package com.springapp.mvc.beans;

/**
 * Created by Jui on 24/4/2017.
 */
public class awardTable {
    private String name;
    private String dob;
    private String a_name;
    private String year;
    private String gender;
    private String institution;
    private  String thesis;
    private String Nationality;
    private byte[] picture;

    public void setDob(String dob) {
        this.dob = dob;
    }

   /* public awardTable() {
        this.name = null;
        this.dob = null;
        this.a_name = null;
        this.year = null;

    }*/

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getThesis() {
        return thesis;
    }

    public void setThesis(String thesis) {
        this.thesis = thesis;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getDob() {
        return dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
