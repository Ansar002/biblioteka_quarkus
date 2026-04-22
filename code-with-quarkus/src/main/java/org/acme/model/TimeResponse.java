package org.acme.model;

public class TimeResponse {

    public String ip;
    public String timeZone;
    public String dateTime;
    public String date;
    public String time;
    public String dayOfWeek;   // ✅ camelCase, String
    public boolean dstActive;

    public String getIp() {
        return ip;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDayOfWeek() {   // ✅ vraća String
        return dayOfWeek;
    }

    public boolean isDstActive() {
        return dstActive;
    }
    public void setDstActive(boolean dstActive) {
        this.dstActive = dstActive;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDayOfWeek(String dayOfWeek) {   // ✅ prima String
        this.dayOfWeek = dayOfWeek;
    }


}