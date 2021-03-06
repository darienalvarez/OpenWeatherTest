package com.darien.openweathertest.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "ZIP".
 */
public class Zip {

    private Long id;
    private String zipCode;
    private boolean current;
    private java.util.Date date;

    public Zip() {
    }

    public Zip(Long id) {
        this.id = id;
    }

    public Zip(Long id, String zipCode, boolean current, java.util.Date date) {
        this.id = id;
        this.zipCode = zipCode;
        this.current = current;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public boolean getCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

}
