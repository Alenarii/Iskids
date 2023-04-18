package com.example.my.ui.start;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.my.R;
import com.example.my.ui.BasaData.DBHelper;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class StartFragment extends Fragment {
    Button sho;
    BottomSheetDialog dialog;
    DBHelper dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_start, container, false);
        sho = view.findViewById(R.id.show);

        dialog = new BottomSheetDialog(getContext());
        createDialog();

        sho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        return view;
    }
    String itemDay, itemSub;
    public void createDialog(){
        View view = getLayoutInflater().inflate(R.layout.bottomsheetlayout, null, false);
        Button subl = view.findViewById(R.id.BtnAddHomeWork_2);

        dbHelper = new DBHelper(getContext());

        dbHelper = new DBHelper(getContext());

        ArrayList<String> NamesListDay = new ArrayList<>(dbHelper.ReadDay(getContext()));
        String[] smday = NamesListDay.toArray(new String[0]);


        Spinner spiday = view.findViewById(R.id.spinDay);
        Spinner spisub = view.findViewById(R.id.spinSub);
        EditText etHW = view.findViewById(R.id.EtHW_2);

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

        subl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textHW = String.valueOf(etHW.getText());
                dbHelper.AddHomeWork(getContext(), itemDay, itemSub, textHW);
                Toast.makeText(getContext(), R.string.AddHWToast, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
    }
}