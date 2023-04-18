package com.example.my.ui.clear;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.my.R;
import com.example.my.ui.BasaData.DBHelper;

import java.util.ArrayList;

public class ClearFragment extends Fragment implements View.OnClickListener {
    Button bS, bR, bA, bSO, bHO, bH, bRO;
    Dialog dialog;
    Spinner spin, spind, spinr;
    DBHelper dbHelper;
    String itemSub, itemDay, itemRas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clear, container, false);
        bS = view.findViewById(R.id.BtnDelSub);
        bS.setOnClickListener(this);
        bSO = view.findViewById(R.id.BtnDelSubOne);
        bSO.setOnClickListener(this);
        bR = view.findViewById(R.id.BtnDelRas);
        bR.setOnClickListener(this);
        bA = view.findViewById(R.id.BtnDelAll);
        bA.setOnClickListener(this);
        bHO = view.findViewById(R.id.BtnDelHWOne);
        bHO.setOnClickListener(this);
        bH = view.findViewById(R.id.BtnDelHW);
        bH.setOnClickListener(this);
        bRO = view.findViewById(R.id.BtnDelRasOne);
        bRO.setOnClickListener(this);

        spin = view.findViewById(R.id.spinnerSubDel);
        dbHelper = new DBHelper(getContext());
        ArrayList<String> NamesList = new ArrayList<>(dbHelper.ReadSub(getContext()));
        String[] sm = NamesList.toArray(new String[0]);
        ArrayAdapter<String> adapterDay = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, sm);
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapterDay);

        AdapterView.OnItemSelectedListener itemSelectedListenerSub = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemSub = (String)parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };

        spin.setOnItemSelectedListener(itemSelectedListenerSub);

        spind = view.findViewById(R.id.spinnerDayDel);
        ArrayList<String> NamesListDay = new ArrayList<>(dbHelper.ReadDay(getContext()));
        String[] smday = NamesListDay.toArray(new String[0]);
        ArrayAdapter<String> adapterrDay = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, smday);
        adapterrDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spind.setAdapter(adapterrDay);

        AdapterView.OnItemSelectedListener itemSelectedListenerDay = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemDay = (String)parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };

        spind.setOnItemSelectedListener(itemSelectedListenerDay);

        spinr = view.findViewById(R.id.spinnerRasOneDel);
        ArrayList<String> NamesListRas = new ArrayList<>(dbHelper.ReadDay(getContext()));
        String[] smread = NamesListRas.toArray(new String[0]);
        ArrayAdapter<String> adapterRas = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, smread);
        adapterRas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinr.setAdapter(adapterRas);

        AdapterView.OnItemSelectedListener itemSelectedListenerRas = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemRas = (String)parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };

        spinr.setOnItemSelectedListener(itemSelectedListenerRas);

        dialog = new Dialog(getContext());

        return view;
    }

    @Override
    public void onClick(View view) {
        String selectedDel = "";
        switch (view.getId()) {
            case R.id.BtnDelSub: {
                selectedDel = bS.getText().toString();
                break;
            }
            case R.id.BtnDelRas: {
                selectedDel = bR.getText().toString();
                break;
            }
            case R.id.BtnDelAll: {
                selectedDel = bA.getText().toString();
                break;
            }
            case R.id.BtnDelSubOne:{
                selectedDel = bSO.getText().toString() + "//" + itemSub;
                break;
            }
            case R.id.BtnDelHWOne:{
                selectedDel = bHO.getText().toString() + "//" + itemDay;
                break;
            }
            case R.id.BtnDelHW:{
                selectedDel = bH.getText().toString();
                break;
            }
            case R.id.BtnDelRasOne:{
                selectedDel = bRO.getText().toString() + "//" + itemRas;
                break;
            }

        }

        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        CustomDialogDelete dialog = new CustomDialogDelete();
        Bundle args = new Bundle();

        args.putString("del", selectedDel);
        dialog.setArguments(args);

        dialog.show(getParentFragmentManager(), "custom");
    }
}