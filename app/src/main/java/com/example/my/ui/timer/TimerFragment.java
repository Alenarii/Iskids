package com.example.my.ui.timer;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.my.R;

public class TimerFragment extends Fragment implements View.OnClickListener{
    private TextView txt_SecondBreak, txt_SecondWork, texttime, textflag;

    private MediaPlayer mPlayer;
    private Button start;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        txt_SecondWork = (TextView) view.findViewById(R.id.txtSecondWork);
        txt_SecondBreak = (TextView) view.findViewById(R.id.txtSecondBreak);
        texttime = (TextView) view.findViewById(R.id.textTime);
        textflag = (TextView) view.findViewById(R.id.textFlag);

        start = (Button) view.findViewById(R.id.btnStart);
        start.setOnClickListener(this);

        mPlayer = MediaPlayer.create(getContext(), R.raw.timer);

        return view;
    }

    public void onClick(View v) {
        long second = Long.parseLong(txt_SecondWork.getText().toString());
        CountDownTimer myTimer = new CountDownTimer(second*60000, 60) {
            @Override
            public void onTick(long l) {
                texttime.setText(Long.toString((l/1000)/60) + ":" + Long.toString(l/1000%60));
            }

            @Override
            public void onFinish() {
                textflag.setText("Рабочее время закончилось");
                txt_SecondWork.setEnabled(true);
                txt_SecondBreak.setEnabled(true);
                start.setEnabled(true);
                mPlayer.start();
            }
        };
        long secondBreak = Long.parseLong(txt_SecondBreak.getText().toString());
        CountDownTimer myTimerBreak = new CountDownTimer(secondBreak*60000, 60) {
            @Override
            public void onTick(long l) {
                texttime.setText(Long.toString(l/1000/60) + ":" + Long.toString(l/1000%60));
            }

            @Override
            public void onFinish() {
                textflag.setText("Перерыв закончен");
                txt_SecondWork.setEnabled(true);
                txt_SecondBreak.setEnabled(true);
                start.setEnabled(true);
                mPlayer.start();
            }
        };
        txt_SecondWork.setEnabled(false);
        txt_SecondBreak.setEnabled(false);
        start.setEnabled(false);
        Start(myTimer, myTimerBreak);
    }


    public void Start(CountDownTimer timerWork, CountDownTimer timerBreak){
        if (textflag.getText() == "Рабочее время закончилось"){
            textflag.setText("До окончания перерыва осталось:");
            timerBreak.start();}
        else { textflag.setText("До окончания рабочего времени осталось:");
            timerWork.start();}
    }

}