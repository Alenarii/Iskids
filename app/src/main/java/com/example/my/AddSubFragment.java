package com.example.my;

import android.os.Bundle;

        import androidx.fragment.app.Fragment;

        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

import com.example.my.ui.BasaData.DBHelper;


public class AddSubFragment extends Fragment implements View.OnClickListener{
    Button btnAdd;
    EditText etSub, etTea, etCab, etDop;
    TextView et;


    DBHelper dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_sub, container, false);
        btnAdd = (Button) view.findViewById(R.id.BtnAddSub);
        btnAdd.setOnClickListener(this);

        etSub = (EditText) view.findViewById(R.id.AddSubName);
        etTea = (EditText)view.findViewById(R.id.AddSubTeacher);
        etCab = (EditText)view.findViewById(R.id.AddSubCab);
        etDop = (EditText)view.findViewById(R.id.AddSubDop);

        dbHelper = new DBHelper(getContext());
        return view;
    }

    @Override
    public void onClick(View view) {
        String name = etSub.getText().toString().substring(0, 1).toUpperCase() + etSub.getText().toString().substring(1).toLowerCase();
        String tea = etTea.getText().toString();
        String cab = etCab.getText().toString();
        String dop = etDop.getText().toString();
        dbHelper.AddSub(getContext(), name, tea, cab, dop);
        etSub.setText("");
        etTea.setText("");
        etCab.setText("");
        etDop.setText("");
    dbHelper.close();
    }
}