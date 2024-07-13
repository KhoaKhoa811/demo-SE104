package com.demo.se104.footballLeagueManager.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.demo.se104.footballLeagueManager.model.Match;

public class NearestFutureMatchUtils {

    // Hàm chuyển đổi chuỗi thời gian thành LocalDateTime
    public static LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.parse(dateTime, formatter);
    }

    // Hàm chọn trận đấu chưa diễn ra gần nhất so với thời điểm hiện tại
    public static Match findNearestFutureMatch(List<Match> matchTimes) {
        if (matchTimes == null || matchTimes.isEmpty()) {
            return new Match();
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime nearestFutureTime = null;
        Match nearestFutureMatch = null;

        for (Match matchTime : matchTimes) {
            LocalDateTime matchDateTime = parseDateTime(matchTime.getDateTime());
            if (matchDateTime.isAfter(currentDateTime)) {
                if (nearestFutureTime == null || matchDateTime.isBefore(nearestFutureTime)) {
                    nearestFutureTime = matchDateTime;
                    nearestFutureMatch = matchTime;
                }
            }
        }

        return nearestFutureMatch;
    }
}
