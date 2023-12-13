package com.example.sunrun.menu.fragments.progress;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateManager {

    public static String getCurrentDate() {

        Date currentDate = Calendar.getInstance().getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        return dateFormat.format(currentDate);

    }
}
