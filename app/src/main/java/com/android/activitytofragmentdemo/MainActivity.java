package com.android.activitytofragmentdemo;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private FragmentA fragmentA;
    private FragmentB fragmentB;

    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    pb.setVisibility(View.GONE);
                    transmitData();
                    break;
            }
        }
    };

    private void transmitData() {
        Bundle bundle = new Bundle();
        bundle.putString("data","Activity中请求过来的数据..");
        fragmentA.setArguments(bundle);
        fragmentB.setArguments(bundle);
        fragmentA.gainedData();

    }

    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        fragmentA = new FragmentA();
        fragmentB = new FragmentB();


        RadioGroup rg_register = (RadioGroup) findViewById(R.id.rg_register);
        pb = findViewById(R.id.pb);

        rg_register.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_register_people) {
                    replaceFragment(R.id.ll_register, fragmentA);
                } else if (checkedId == R.id.rb_register_enterprise) {
                    replaceFragment(R.id.ll_register, fragmentB);
                }
            }
        });
        rg_register.check(R.id.rb_register_people);

    }

    private void initData() {
        requestNetworkData();

    }

    private void requestNetworkData() {
        pb.setVisibility(View.VISIBLE);
        handler.sendEmptyMessageDelayed(0,1000);
    }


    public void replaceFragment(int id, Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id, fragment);
        ft.commit();
    }
}
