package com.southsai.countertimesimple;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.greenrobot.event.EventBus;

/**
 * Created by thao on 4/9/15.
 */
public class FragmentCounterTime extends Fragment {
    private ToggleButton onoffbutton;
    private TextView counterlable;
    private long timeCounted;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_counter_time, container, false);
        onoffbutton = (ToggleButton) view.findViewById(R.id.onoffbutton);
        counterlable = (TextView) view.findViewById(R.id.counterlable);
        timeCounted = 0;
        counterlable.setText(getStringDatefromlong(timeCounted));
        final Intent intent = new Intent(getActivity(), CounterService.class);
        onoffbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    EventBus.getDefault().postSticky(timeCounted);
                    getActivity().startService(intent);
                } else {
                    getActivity().stopService(intent);
                }
            }
        });
        return view;
    }

    private String getStringDatefromlong(long l) {
        Date date = new Date(l * 1000);//1000 = 1 giay
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss", Locale.getDefault());
        return sdf.format(date);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this);
    }

    public void onEvent(Long l) {
        timeCounted = l;
        counterlable.setText(getStringDatefromlong(timeCounted));
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
