package com.example.dell.offline1;

/**
 * Created by DELL on 01-Jun-17.
 */
public class VacantHousesClass {
    public String flatdesc;
    public String addressdesc;
    public String rent;

    public VacantHousesClass(){
        flatdesc="";
        addressdesc="";
        rent="";
    }

    public VacantHousesClass(String flatdesc, String addressdesc, String rent) {
        this.flatdesc = flatdesc;
        this.addressdesc = addressdesc;
        this.rent = rent;
    }

    public String getFlatdesc() {
        return flatdesc;
    }

    public void setFlatdesc(String flatdesc) {
        this.flatdesc = flatdesc;
    }

    public String getAddressdesc() {
        return addressdesc;
    }

    public void setAddressdesc(String addressdesc) {
        this.addressdesc = addressdesc;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }
}
