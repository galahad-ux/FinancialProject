package org.isep.financialproject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoreNotifications {
    public static List<String> notifs = new ArrayList<>();
    public static void add(String msg){
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd , HH-mm"));
        notifs.add(0, time + " : " + msg);
    }
}
