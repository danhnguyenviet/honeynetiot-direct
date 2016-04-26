package com.example.tinhlethanh.honeynetiotdirect.Model;

/**
 * Created by Tinh LE Thanh on 4/26/2016.
 */
public class HistoryBom {
    private int id;
    private String ip;
    private String port;
    private String bom;
    private String datetime;

    public HistoryBom() {
        super();
    }

    public HistoryBom(String ip, String port, String bom, String datetime) {
        this.ip = ip;
        this.port = port;
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

    public String getBom() {
        return bom;
    }

    public void setBom(String bom) {
        this.bom = bom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return ""+this.datetime+" - IP: "+this.ip+" - PORT: "+this.port+" - BƠM: "+this.bom+"";
    }
}
