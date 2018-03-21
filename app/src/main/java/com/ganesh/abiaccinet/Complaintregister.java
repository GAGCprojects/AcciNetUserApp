package com.ganesh.abiaccinet;

/**
 * Created by User on 17-03-2018.
 */

public class Complaintregister {
    private String id,Landmark,Description,Risklevel,Nationalhighways,Location,Email;
    public Complaintregister(){}
    public Complaintregister(String id,String Landmark,String Description,String Risklevel,String Nationalhighways,String Location,String Email){
        this.id=id;
        this.Landmark=Landmark;
        this.Description=Description;
        this.Risklevel=Risklevel;
        this.Nationalhighways=Nationalhighways;
        this.Location=Location;
        this.Email=Email;
    }
    public String getId()
    {
        return id;
    }
    public String getLandmark() {
        return Landmark;
    }
    public String getDescription() {
        return Description;
    }
    public String getRisklevel() {
        return Risklevel;
    }

    public String getNationalhighways() {
        return Nationalhighways;
    }

    public String getLocation() {
        return Location;
    }

    public String getEmail() {
        return Email;
    }
}
