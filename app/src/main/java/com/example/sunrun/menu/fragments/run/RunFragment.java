package com.example.sunrun.menu.fragments.run;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sunrun.R;

public class RunFragment extends Fragment {

    private Button startButton;
    private TimerManager timerManager;

    public RunFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_run_layout, container, false);

        TextView textViewTime = view.findViewById(R.id.textViewTime);
        startButton = view.findViewById(R.id.buttonStart);
        Button finishButton = view.findViewById(R.id.buttonFinish);

        timerManager = new TimerManager(textViewTime);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerManager.startOrResumeTimer();
                updateStartButtonText();
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerManager.stopAndResetTimer();
                updateStartButtonText();
            }
        });

        return view;
    }

    private void updateStartButtonText() {
        if (timerManager.isTimerRunning()) {
            startButton.setText(R.string.pause);
        } else {
            startButton.setText(R.string.resume);
        }
    }
}
