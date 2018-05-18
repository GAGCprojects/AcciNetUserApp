package com.ganesh.abiaccinet;

/**
 * Created by User on 21-03-2018.
 */

public class ComplaintDesc {
    String desc;
    String landmark;
    String location;
    String nationalhighway;

    public ComplaintDesc(String desc, String landmark, String location, String nationalhighway) {
        this.desc = desc;
        this.landmark = landmark;
        this.location = location;
        this.nationalhighway = nationalhighway;
    }

    public String getDesc() {
        return desc;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getLocation() {
        return location;
    }

    public String getNationalhighway() {
        return nationalhighway;
    }
}
