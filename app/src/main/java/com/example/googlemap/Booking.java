package com.example.googlemap;

public class Booking {
    public String userid;
    public String pitchid;
    public String ownerid;
    public String book_day;
    public String time_booking;
    public String start_time;
    public String end_time;
    public int verified;
    public String ticket;
    public float price;
    public String key;

    public Booking() {

    }

    public String getUserid() {
        return userid;
    }

    public String getPitchid() {
        return pitchid;
    }

    public String getOwnerid() {
        return ownerid;
    }

    public String getBook_day() {
        return book_day;
    }

    public String getTime_booking() {
        return time_booking;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public int getVerified() {
        return verified;
    }

    public String getTicket() {
        return ticket;
    }

    public float getPrice() {
        return price;
    }

    public String getKey() {
        return key;
    }

    public Booking(String userid, String book_day, String time_booking,
                   String start_time, String end_time, String pitchid, String ownerid, int verified,
                   String ticket, float price, String key
    ) {
        this.userid = userid;
        this.book_day = book_day;
        this.time_booking = time_booking;
        this.start_time = start_time;
        this.end_time = end_time;
        this.pitchid = pitchid;
        this.ownerid = ownerid;
        this.verified = verified;
        this.ticket = ticket;
        this.price = price;
        this.key = key;
    }
}
