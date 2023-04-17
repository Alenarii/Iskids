package com.example.my.ui;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.my.ui.BasaData.DBHelper_Sub;
import com.example.my.R;

public class EmptyFragment extends Fragment implements View.OnClickListener {
    DBHelper_Sub dbHelper_sub;

    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty, container, false);
        textView = view.findViewById(R.id.textView6);
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(this);
        dbHelper_sub = new DBHelper_Sub(getContext());

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}