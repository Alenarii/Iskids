package com.example.my.ui.add_ras;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
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

import com.example.my.ui.BasaData.DBHelper_Sub;
import com.example.my.R;

import java.util.ArrayList;

public class AddRasFragment extends Fragment implements View.OnClickListener{

    DBHelper_Sub dbHelper;
    EditText etnom;
    String itemSub,  itemDay;
    Integer itemNom;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add_ras, container, false);

        dbHelper = new DBHelper_Sub(getContext());

        ArrayList<String> NamesList = new ArrayList<>(dbHelper.ReadSub(getContext()));
        String[] sm = NamesList.toArray(new String[0]);

        ArrayList<String> NamesListDay = new ArrayList<>(dbHelper.ReadDay(getContext()));
        String[] smday = NamesListDay.toArray(new String[0]);

        Spinner spiday = view.findViewById(R.id.SpiDay);
        etnom = view.findViewById(R.id.etNome);
        Spinner spisub = view.findViewById(R.id.SpiSub);

        Button btn = view.findViewById(R.id.BtnAddRas);
        btn.setOnClickListener(this);

        ArrayAdapter<String> adapterDay = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, smday);
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiday.setAdapter(adapterDay);

        ArrayAdapter<String> adapterSub = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, sm);
        adapterSub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spisub.setAdapter(adapterSub);

        AdapterView.OnItemSelectedListener itemSelectedListenerSub = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemSub = (String)parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };

        AdapterView.OnItemSelectedListener itemSelectedListenerDay = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemDay = (String)parent.getItemAtPosition(position);
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
        String s = etnom.getText().toString();
        boolean isOnlyDigits = true;
        for(int i = 0; i < s.length() && isOnlyDigits; i++) {
            if(!Character.isDigit(s.charAt(i))) {
                isOnlyDigits = false;
            }
        };
        if (isOnlyDigits)
        {
            itemNom =  Integer.parseInt(etnom.getText().toString());
        dbHelper.AddRas(getContext(), itemDay, itemNom, itemSub);
            //etnom.setText('0');
        }
        else Toast.makeText(getContext(), "Введите корректный номер урока", Toast.LENGTH_SHORT).show();
    }

}