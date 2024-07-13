package com.demo.se104.footballLeagueManager.convert;

public class DateTimeConvert {
	
	public static String convertEntityToModel(String inputDateTime) {
        // Kiểm tra nếu chuỗi đầu vào rỗng hoặc null
        if (inputDateTime == null || inputDateTime.isEmpty()) {
            throw new IllegalArgumentException("Input date/time string must not be empty or null");
        }
        
        // Tách ngày và thời gian từ chuỗi đầu vào
        String datePart = inputDateTime.substring(0, 10); // Lấy "2024-04-23"
        String timePart = inputDateTime.substring(11);    // Lấy "15:00:00"
        
        // Tách các thành phần của ngày
        String[] dateParts = datePart.split("-");
        String year = dateParts[0];   // Lấy "2024"
        String month = dateParts[1];  // Lấy "04"
        String day = dateParts[2];    // Lấy "23"
        
        // Ghép lại thành phần ngày thành định dạng mới "dd-MM-yyyy"
        String newDateFormat = day + "-" + month + "-" + year + " " + timePart;
        
        return newDateFormat;
    }

	public static String convertModelToEntity(String inputDateTime) {
        // Kiểm tra nếu chuỗi đầu vào rỗng hoặc null
        if (inputDateTime == null || inputDateTime.isEmpty()) {
            throw new IllegalArgumentException("Input date/time string must not be empty or null");
        }
        
        // Tách ngày và thời gian từ chuỗi đầu vào
        String datePart = inputDateTime.substring(0, 10); // Lấy "23-04-2024"
        String timePart = inputDateTime.substring(11);    // Lấy "15:00:00"
        
        // Tách các thành phần của ngày
        String[] dateParts = datePart.split("-");
        String day = dateParts[0];   // Lấy "23"
        String month = dateParts[1]; // Lấy "04"
        String year = dateParts[2];  // Lấy "2024"
        
        // Ghép lại thành phần ngày thành định dạng mới "yyyy-MM-dd"
        String newDateFormat = year + "-" + month + "-" + day + " " + timePart;
        
        return newDateFormat;
    }

}
