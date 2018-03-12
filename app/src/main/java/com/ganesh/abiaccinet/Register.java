package com.ganesh.abiaccinet;

/**
 * Created by User on 12-03-2018.
 */

public class Register {
    private String name,email,phno,aadhar,pass1,pass2,uid;
    public Register(){

    }
    public Register(String name,String aadhar,String email,String phno,String pass1,String pass2,String uid){
        this.name=name;
        this.aadhar=aadhar;
        this.email=email;
        this.phno=phno;
        this.pass1=pass1;
        this.pass2=pass2;
        this.uid=uid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhno() {
        return phno;
    }

    public String getAadhar() {
        return aadhar;
    }

    public String getPass1() {
        return pass1;
    }

    public String getPass2() {
        return pass2;
    }
    public String getUid(){
        return uid;
    }
}
