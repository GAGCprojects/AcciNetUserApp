package com.ganesh.abiaccinet;

/**
 * Created by User on 17-03-2018.
 */

public class Complaintregister {
    private String categ,id,Landmark,Description,Risklevel,Nationalhighways,Location,Email,Status,Date,Time,areaT,accTypeT,vehicleT,roadTypeT,constructionT;
    public Complaintregister(){}


    public Complaintregister(String id, String categ, String Landmark, String Description, String Nationalhighways, String Location, String Email, String Status, String Date, String Time, String areaT, String accTypeT, String vehicleT, String roadTypeT, String constructionT){
        this.id=id;
        this.categ=categ;
        this.Landmark=Landmark;
        this.Description=Description;
        this.Nationalhighways=Nationalhighways;
        this.Location=Location;
        this.Email=Email;
        this.Status=Status;
        this.Date =Date;
        this.Time = Time;
        this.areaT = areaT;
        this.accTypeT = accTypeT;
        this.vehicleT = vehicleT;
        this.roadTypeT = roadTypeT;
        this.constructionT = constructionT;


    }

    public String getAreaT() {
        return areaT;
    }

    public String getAccTypeT() {
        return accTypeT;
    }

    public String getVehicleT() {
        return vehicleT;
    }

    public String getRoadTypeT() {
        return roadTypeT;
    }

    public String getConstructionT() {
        return constructionT;
    }

    public String getId()
    {
        return id;
    }
    public String getCateg() { return categ; }
    public String getLandmark() { return Landmark; }
    public String getDescription() {
        return Description;
    }
    public String getNationalhighways() {
        return Nationalhighways;
    }
    public String getLocation() {
        return Location;
    }
    public String getEmail() { return Email; }
    public String getStatus(){ return Status; }
    public String getDate(){
        return Date;
    }
    public String getTime(){
        return Time;
    }

}
