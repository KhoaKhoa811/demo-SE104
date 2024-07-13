package com.demo.se104.footballLeagueManager.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.demo.se104.footballLeagueManager.model.Match;

public class NearestPastMatchUtils {

	// Hàm chuyển đổi chuỗi thời gian thành LocalDateTime
    public static LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.parse(dateTime, formatter);
    }

    // Hàm chọn trận đấu đã diễn ra gần nhất so với thời điểm hiện tại
    public static Match findNearestPastMatch(List<Match> matchTimes) {
        if (matchTimes == null || matchTimes.isEmpty()) {
            return null;
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime nearestPastTime = null;
        Match nearestPastMatch = null;

        for (Match matchTime : matchTimes) {
            LocalDateTime matchDateTime = parseDateTime(matchTime.getDateTime());
            if (matchDateTime.isBefore(currentDateTime) || matchDateTime.isEqual(currentDateTime)) {
                if (nearestPastTime == null || matchDateTime.isAfter(nearestPastTime)) {
                    nearestPastTime = matchDateTime;
                    nearestPastMatch = matchTime;
                }
            }
        }

        return nearestPastMatch;
    }
}
