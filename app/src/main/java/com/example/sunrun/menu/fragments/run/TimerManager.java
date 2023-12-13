package com.example.sunrun.menu.fragments.run;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.widget.TextView;

public class TimerManager implements ITimerManager {

    private CountDownTimer countDownTimer;
    private long startTimeMillis;
    private final TextView textViewTime;
    private boolean isTimerRunning = false;
    private long remainingTimeMillis;

    public TimerManager(TextView textViewTime) {
        this.textViewTime = textViewTime;
    }

    public boolean isTimerRunning() {
        return isTimerRunning;
    }

    public void startOrResumeTimer() {
        if (isTimerRunning) {
            countDownTimer.cancel();
            isTimerRunning = false;
            remainingTimeMillis = System.currentTimeMillis() - startTimeMillis;
        } else {
            startTimeMillis = System.currentTimeMillis() - remainingTimeMillis;
            startTimer();
            isTimerRunning = true;
        }
    }

    public void stopAndResetTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        isTimerRunning = false;
        remainingTimeMillis = 0;
        updateTimerText(0);
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(Long.MAX_VALUE, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
                updateTimerText(elapsedTimeMillis);
            }

            @Override
            public void onFinish() {
            }
        }.start();
    }

    @SuppressLint("DefaultLocale")
    private void updateTimerText(long elapsedTimeMillis) {
        int seconds = (int) (elapsedTimeMillis / 1000) % 60;
        int minutes = (int) ((elapsedTimeMillis / (1000 * 60)) % 60);
        String time;
        time = String.format("%02d:%02d", minutes, seconds);
        textViewTime.setText(time);
    }
}
