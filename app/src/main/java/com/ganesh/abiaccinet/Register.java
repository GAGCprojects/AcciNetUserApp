package com.ganesh.abiaccinet;

/**
 * Created by User on 12-03-2018.
 */

public class Register {
    private String name,phno,aadhar,uid;
    public Register(){}
    public Register(String name,String aadhar,String phno){
        this.name=name;
        this.aadhar=aadhar;
        this.phno=phno;
    }
    public String getName() {
        return name;
    }
    public String getPhno() {
        return phno;
    }
    public String getAadhar() {
        return aadhar;
    }
}
