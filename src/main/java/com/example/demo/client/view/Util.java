package com.example.demo.client.view;



import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    private static final SimpleDateFormat DATE_FORMATTER1;
    private static final SimpleDateFormat DATE_FORMATTER2;

    static{
        DATE_FORMATTER1 = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
        DATE_FORMATTER2 = new SimpleDateFormat("HH:mm:ss");
    }

    public static String FORMAT(Date date){
        return DATE_FORMATTER2.format(date);
    }
}

