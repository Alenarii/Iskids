package com.example.my.ui.ras;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.my.ui.BasaData.DBHelper;
import com.example.my.R;

import java.util.ArrayList;

public class RasFragment extends ListFragment {
    DBHelper dbHelper;
    String itemDay;
    ArrayAdapter<String> mAdapter;
    Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ras, container, false);

        dbHelper = new DBHelper(getContext());

        ArrayList<String> NamesListDay = new ArrayList<>(dbHelper.ReadDay(getContext()));
        String[] smday = NamesListDay.toArray(new String[0]);

        Spinner spiday = view.findViewById(R.id.spinner);


        ArrayAdapter<String> adapterDay = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, smday);
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiday.setAdapter(adapterDay);



        AdapterView.OnItemSelectedListener itemSelectedListenerDay = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemDay = (String)parent.getItemAtPosition(position);
                ArrayList<String> NamesList = new ArrayList<>(dbHelper.GetByDayOnlySub(getContext(), itemDay));
                ArrayList<String> NamesListYN = new ArrayList<>(dbHelper.GetByDay(getContext(), itemDay));

                mAdapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_list_item_1, NamesList);
                setListAdapter(mAdapter);
                dialog = new Dialog(getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        };
        spiday.setOnItemSelectedListener(itemSelectedListenerDay);
        return view;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        String rrr = itemDay + " " + mAdapter.getItem(position);
        CustomDialogRas dialog = new CustomDialogRas();
        Bundle args = new Bundle();
        args.putString("ras", rrr);
        dialog.setArguments(args);

        dialog.show(getParentFragmentManager(), "custom");
    }
}