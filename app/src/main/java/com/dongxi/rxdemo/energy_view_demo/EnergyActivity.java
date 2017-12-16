package com.dongxi.rxdemo.energy_view_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dongxi.rxdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnergyActivity extends AppCompatActivity {

    @BindView(R.id.energy_view)
    EnergyView mEnergyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy);
        ButterKnife.bind(this);
        mEnergyView.setProgress(270f);
    }
}
