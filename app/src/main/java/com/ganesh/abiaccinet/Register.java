package com.ganesh.abiaccinet;

/**
 * Created by User on 12-03-2018.
 */

public class Register {
    public static String name,phno,aadhar,uid,email;
    public Register(){}
    public Register(String name,String aadhar,String phno,String email){
        this.name=name;
        this.aadhar=aadhar;
        this.phno=phno;
        this.email=email;
    }
    public  String getName() {
        return name;
    }
    public String getPhno() {
        return phno;
    }
    public String getAadhar() {
        return aadhar;
    }
    public String getEm(){ return email; }
}
