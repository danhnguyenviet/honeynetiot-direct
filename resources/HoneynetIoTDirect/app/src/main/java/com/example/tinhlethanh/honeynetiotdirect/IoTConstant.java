package com.example.tinhlethanh.honeynetiotdirect;

/**
 * Created by Danh on 4/26/2016.
 */
public class IoTConstant {
    public static String PORT       = "8080";
    public static String IP         = "192.168.55.104";

    public static String TURN_ON_LIGHT_URL      = "http://" + IP + ":" + PORT + "/mobie/api.php?gpio=2&status=on";
    public static String TURN_OFF_LIGHT_URL     = "http://" + IP + ":" + PORT + "/mobie/api.php?gpio=2&status=off";
}
