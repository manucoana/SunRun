package com.example.sunrun.menu.fragments.manually;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sunrun.R;
import com.example.sunrun.database.create.DatabaseRuns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddManuallyFragment extends Fragment {

    private EditText editTextDate;
    private EditText editTextDistance;
    private EditText editTextTime;
    private DatabaseRuns databaseRuns;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_manually, container, false);

        editTextDate = view.findViewById(R.id.editTextDate);
        editTextDistance = view.findViewById(R.id.editTextDistance);
        editTextTime = view.findViewById(R.id.editTextTime);

        databaseRuns = new DatabaseRuns(getContext());

        Button buttonAddRun = view.findViewById(R.id.buttonAddRun);
        buttonAddRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRunToDatabase();
            }
        });

        editTextDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                validateDateFormat(editable.toString());
            }
        });

        return view;
    }

    private void addRunToDatabase() {
        String date = editTextDate.getText().toString();
        String distance = editTextDistance.getText().toString();
        String time = editTextTime.getText().toString();

        if (date.isEmpty() || distance.isEmpty() || time.isEmpty()) {
            return;
        }

        if (validateDateFormat(date)) {
            databaseRuns.insertRun(date, Float.parseFloat(distance), Float.parseFloat(time));
            showToast("Run added successfully");
            editTextDate.setText("");
            editTextDistance.setText("");
            editTextTime.setText("");
        } else {
            showToast("Invalid date format. Run not added.");
        }
    }


    private boolean validateDateFormat(String inputDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date date = simpleDateFormat.parse(inputDate);
            return date != null;
        } catch (ParseException e) {
            return false;
        }
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
