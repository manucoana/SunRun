package com.example.sunrun.database.create;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.sunrun.database.goals.GoalEntry;
import com.example.sunrun.database.runs.RunEntry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRuns extends SQLiteOpenHelper implements IDatabaseRuns {

    private static final String DATABASE_NAME = "SRDB";
    private static final int DATABASE_VERSION = 11;

    private static final String MY_RUNS = "my_run";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "date";

    private static final String COLUMN_DAY = "day";
    private static final String COLUMN_MONTH = "month";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_DISTANCE = "distance";

    private static final String COLUMN_TIME = "time";

    private static final String GOALS_TABLE = "goals";
    private static final String GOAL_COLUMN_ID = "id";
    private static final String GOAL_COLUMN_MONTH_NUMBER = "month_number";
    private static final String GOAL_COLUMN_MONTH_NAME = "month_name";
    private static final String GOAL_COLUMN_GOAL = "goal";


    public DatabaseRuns(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE =
                "CREATE TABLE " + MY_RUNS + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_DATE + " TEXT, " +
                        COLUMN_DISTANCE + " REAL, " +
                        COLUMN_TIME + " REAL);";
        db.execSQL(TABLE_CREATE);

        String INSERT_MODEL_PROBA1 = "INSERT INTO " + MY_RUNS + "("
                + COLUMN_ID + "," + COLUMN_DATE + "," + COLUMN_DISTANCE + "," + COLUMN_TIME + ")"
                + " VALUES(1, '2023-10-12', 12.0, 70)";
        db.execSQL(INSERT_MODEL_PROBA1);

        String INSERT_MODEL_PROBA2 = "INSERT INTO " + MY_RUNS + "("
                + COLUMN_ID + "," + COLUMN_DATE + "," + COLUMN_DISTANCE + "," + COLUMN_TIME + ")"
                + " VALUES(2, '2023-10-10', 15.0, 90)";
        db.execSQL(INSERT_MODEL_PROBA2);

        String INSERT_MODEL_PROBA3 = "INSERT INTO " + MY_RUNS + "("
                + COLUMN_ID + "," + COLUMN_DATE + "," + COLUMN_DISTANCE + "," + COLUMN_TIME + ")"
                + " VALUES(3, '2023-10-09', 7.5, 40)";
        db.execSQL(INSERT_MODEL_PROBA3);

        String CREATE_GOALS_TABLE =
                "CREATE TABLE " + GOALS_TABLE + " (" +
                        GOAL_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        GOAL_COLUMN_MONTH_NUMBER + " INTEGER, " +
                        GOAL_COLUMN_MONTH_NAME + " TEXT, " +
                        GOAL_COLUMN_GOAL + " REAL);";
        db.execSQL(CREATE_GOALS_TABLE);

        for (int i = 1; i <= 12; i++) {
            String monthName = getMonthName(i);
            String INSERT_GOAL = "INSERT INTO " + GOALS_TABLE + "("
                    + GOAL_COLUMN_MONTH_NUMBER + "," + GOAL_COLUMN_MONTH_NAME + "," + GOAL_COLUMN_GOAL + ")"
                    + " VALUES(" + i + ", '" + monthName + "', 0)";
            db.execSQL(INSERT_GOAL);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<RunEntry> getAllRuns() {
        List<RunEntry> runEntries = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                COLUMN_ID,
                COLUMN_DATE,
                COLUMN_DISTANCE,
                COLUMN_TIME
        };

        String sortOrder = COLUMN_ID + " DESC";

        Cursor cursor = db.query(
                MY_RUNS,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        while (cursor.moveToNext()) {
            String id = String.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            String dateString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE));
            LocalDate date = parseDate(dateString);

            String distance = String.valueOf(cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_DISTANCE)));
            String time = String.valueOf(cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_TIME)));

            RunEntry runEntry = new RunEntry(id, date, distance, time);
            runEntries.add(runEntry);
        }

        cursor.close();
        db.close();

        return runEntries;
    }

    @Override
    public List<GoalEntry> getAllGoals() {
        List<GoalEntry> goalEntries = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                GOAL_COLUMN_ID,
                GOAL_COLUMN_MONTH_NUMBER,
                GOAL_COLUMN_MONTH_NAME,
                GOAL_COLUMN_GOAL
        };

        String sortOrder = GOAL_COLUMN_ID + " ASC";

        Cursor cursor = db.query(
                GOALS_TABLE,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(GOAL_COLUMN_ID));
            int monthNumber = cursor.getInt(cursor.getColumnIndexOrThrow(GOAL_COLUMN_MONTH_NUMBER));
            String monthName = cursor.getString(cursor.getColumnIndexOrThrow(GOAL_COLUMN_MONTH_NAME));
            float goal = cursor.getFloat(cursor.getColumnIndexOrThrow(GOAL_COLUMN_GOAL));

            GoalEntry goalEntry = new GoalEntry(id, monthNumber, monthName, goal);
            goalEntries.add(goalEntry);
        }

        cursor.close();
        db.close();

        return goalEntries;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void insertRun(String date, float distance, float time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_DISTANCE, distance);
        values.put(COLUMN_TIME, time);

        LocalDate localDate = parseDate(date);
        values.put(COLUMN_DAY, localDate.getDayOfMonth());
        values.put(COLUMN_MONTH, localDate.getMonthValue());
        values.put(COLUMN_YEAR, localDate.getYear());

        db.insert(MY_RUNS, null, values);
        db.close();
    }

    @Override
    public void insertGoal(int monthNumber, String monthName, float goal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GOAL_COLUMN_MONTH_NUMBER, monthNumber);
        values.put(GOAL_COLUMN_MONTH_NAME, monthName);

        Cursor cursor = db.query(
                GOALS_TABLE,
                new String[]{GOAL_COLUMN_ID},
                GOAL_COLUMN_MONTH_NUMBER + " = ?",
                new String[]{String.valueOf(monthNumber)},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(GOAL_COLUMN_ID));
            values.put(GOAL_COLUMN_GOAL, goal);

            db.update(GOALS_TABLE, values, GOAL_COLUMN_ID + " = ?", new String[]{id});
        } else {
            values.put(GOAL_COLUMN_GOAL, goal);
            db.insert(GOALS_TABLE, null, values);
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();
    }

    private String getMonthName(int monthNumber) {
        String[] monthNames = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        return monthNames[monthNumber - 1];
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }

}