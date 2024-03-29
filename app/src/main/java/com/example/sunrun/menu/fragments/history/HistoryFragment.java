package com.example.sunrun.menu.fragments.history;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sunrun.R;
import com.example.sunrun.database.create.DatabaseRuns;

import com.example.sunrun.database.runs.RunAdapter;
import com.example.sunrun.database.runs.RunEntry;
import com.example.sunrun.menu.fragments.manually.AddManuallyFragment;

import java.util.List;

public class HistoryFragment extends Fragment {

    public HistoryFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fr_history_layout, container, false);
        ListView listView = view.findViewById(R.id.listViewRuns);

        List<RunEntry> runEntries;
        try (DatabaseRuns databaseRuns = new DatabaseRuns(getContext())) {

            runEntries = databaseRuns.getAllRuns();
        }

        RunAdapter runAdapter = new RunAdapter(getContext(), R.layout.single_run_details_layout, runEntries);

        listView.setAdapter(runAdapter);

        runAdapter.notifyDataSetChanged();

        Button buttonAdd = view.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new AddManuallyFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
