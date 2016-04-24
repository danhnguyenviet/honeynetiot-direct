package com.example.tinhlethanh.honeynetiotdirect;

/**
 * Created by Tinh LE Thanh on 4/24/2016.
 */
public class History {
    private int id;
    private String history;
    private String datetime;


    public History() {
        super();
    }

    public History(String datetime, String history) {
        this.datetime = datetime;
        this.history = history;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

}
