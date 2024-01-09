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
    private DistanceManager distanceManager;
    private TextView textViewDistance;

    public RunFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_run_layout, container, false);

        TextView textViewTime = view.findViewById(R.id.textViewTime);
        textViewDistance = view.findViewById(R.id.textViewDistance);
        startButton = view.findViewById(R.id.buttonStart);
        Button finishButton = view.findViewById(R.id.buttonFinish);

        timerManager = new TimerManager(textViewTime);
        distanceManager = DistanceManager.getInstance();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerManager.startOrResumeTimer();
                distanceManager.startTrackingDistance();
                updateStartButtonText();
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerManager.stopAndResetTimer();
                distanceManager.stopTrackingDistance();
                updateStartButtonText();

                updateTotalDistanceText();
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

    private void updateTotalDistanceText() {
        textViewDistance.setText(String.format("%.2f km", distanceManager.getTotalDistance()));
    }
}
