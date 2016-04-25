package com.example.tinhlethanh.honeynetiotdirect.Model;

/**
 * Created by Tinh LE Thanh on 4/24/2016.
 */
public class History {
    private int id;
    private String ip;
    private String port;
    private String den;
    private String bom;
    private String datetime;

    public History() {
        super();
    }

    public History(String ip, String port, String den, String bom, String datetime) {
        this.ip = ip;
        this.port = port;
        this.den = den;
        this.bom = bom;
        this.datetime = datetime;
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

    public String getBom() {
        return bom;
    }

    public void setBom(String bom) {
        this.bom = bom;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
