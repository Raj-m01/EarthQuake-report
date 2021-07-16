package com.example.quakereport;

public class info {

    Double mag;
    String location;
    long date;
    String url;

    public  info(Double mag,String location,long date,String url){

        this.mag=mag;
        this.location=location;
        this.date=date;
        this.url=url;

    }

    public Double giveMag(){
        return mag;
    }
    public String giveLocation(){ return location; }
    public long giveDate(){
        return date;
    }
    public String giveUrl(){ return url; }



}
