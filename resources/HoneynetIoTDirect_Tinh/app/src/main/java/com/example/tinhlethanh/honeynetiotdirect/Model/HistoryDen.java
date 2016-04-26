package com.example.tinhlethanh.honeynetiotdirect.Model;

/**
 * Created by Tinh LE Thanh on 4/26/2016.
 */
public class HistoryDen {
    private int id;
    private String ip;
    private String port;
    private String den;
    private String datettime;

    public HistoryDen() {
        super();
    }

    public HistoryDen(String ip, String port, String den, String datettime) {
        this.ip = ip;
        this.port = port;
        this.den = den;
        this.datettime = datettime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }

    public String getDatettime() {
        return datettime;
    }

    public void setDatettime(String datettime) {
        this.datettime = datettime;
    }
    @Override
    public String toString() {
        return ""+this.datettime+"- IP: "+this.ip+"- PORT: "+this.port+"- ĐÈN: "+this.den+"";
    }
}
