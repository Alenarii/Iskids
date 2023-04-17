package com.example.my.ui.add_homework;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my.R;
import com.example.my.ui.BasaData.DBHelper_Sub;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class AddHomeWorkFragment extends Fragment implements View.OnClickListener {
    private EditText etHW;
    private Spinner spiday, spisub;
    private Button add;
    DBHelper_Sub dbHelper;
    String itemSub, itemDay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_home_work, container, false);

        dbHelper = new DBHelper_Sub(getContext());

        ArrayList<String> NamesListDay = new ArrayList<>(dbHelper.ReadDay(getContext()));
        String[] smday = NamesListDay.toArray(new String[0]);

        spiday = view.findViewById(R.id.spinnerDay);
        spisub = view.findViewById(R.id.spinnerSub);
        etHW = view.findViewById(R.id.EtHW);

        add = view.findViewById(R.id.BtnAddHomeWork);
        add.setOnClickListener(this);

        ArrayAdapter<String> adapterDay = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, smday);
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiday.setAdapter(adapterDay);

        AdapterView.OnItemSelectedListener itemSelectedListenerDay = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemDay = (String) parent.getItemAtPosition(position);

                ArrayList<String> NamesList = new ArrayList<>(dbHelper.GetByDayOnlySub(getContext(), itemDay));
                List<String> listWithDuplicates = NamesList;
                Set<String> set = new LinkedHashSet<>(listWithDuplicates);
                List<String> listWithoutDuplicates = new ArrayList<>(set);

                ArrayAdapter<String> adapterSub = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listWithoutDuplicates);
                adapterSub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spisub.setAdapter(adapterSub);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };

        AdapterView.OnItemSelectedListener itemSelectedListenerSub= new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 itemSub = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        spisub.setOnItemSelectedListener(itemSelectedListenerSub);
        spiday.setOnItemSelectedListener(itemSelectedListenerDay);

        return view;
    }

    @Override
    public void onClick(View view) {
        String textHW = String.valueOf(etHW.getText());
        dbHelper.AddHomeWork(getContext(), itemDay, itemSub, textHW);
        Toast.makeText(getContext(), R.string.AddHWToast, Toast.LENGTH_SHORT).show();
        etHW.setText("");
    }
}