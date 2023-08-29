package com.dali.dali.domain.community.entity;

public enum Time {
    T0("12:00 ~ 01:00"),
    T1("01:00 ~ 02:00"),
    T2("02:00 ~ 03:00"),
    T3("03:00 ~ 04:00"),
    T4("04:00 ~ 05:00"),
    T5("05:00 ~ 06:00"),
    T6("06:00 ~ 07:00"),
    T7("07:00 ~ 08:00"),
    T8("08:00 ~ 09:00"),
    T9("09:00 ~ 10:00"),
    T10("10:00 ~ 11:00"),
    T11("11:00 ~ 12:00");


    private final String value;

    Time(String value) {
        this.value = value;
    }

    public String getTime() {
        return value;
    }
}