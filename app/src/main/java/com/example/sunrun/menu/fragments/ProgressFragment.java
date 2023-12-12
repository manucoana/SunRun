package com.example.sunrun.menu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sunrun.R;

import com.example.sunrun.menu.manager.DateManager;
import com.example.sunrun.menu.manager.ProgressManager;

public class ProgressFragment extends Fragment {

    private ProgressManager progressManager;

    public ProgressFragment() {


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_progress_layout, container, false);

        TextView dateTextView = view.findViewById(R.id.date);

        dateTextView.setText(DateManager.getCurrentDate());

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
