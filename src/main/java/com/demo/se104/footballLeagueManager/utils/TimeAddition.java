package com.demo.se104.footballLeagueManager.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeAddition {

	// Hàm chuyển đổi chuỗi thời gian thành LocalTime
    public static LocalTime parseTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalTime.parse(time, formatter);
    }

    // Hàm cộng hai khoảng thời gian
    public static String addTimes(String time1, String time2) {
        LocalTime t1 = parseTime(time1);
        LocalTime t2 = parseTime(time2);

        // Cộng thời gian
        LocalTime result = t1.plusHours(t2.getHour())
                             .plusMinutes(t2.getMinute())
                             .plusSeconds(t2.getSecond());

        // Chuyển kết quả thành chuỗi
        return result.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
