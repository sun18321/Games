package com.sun.games;

import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout mFrameLayout;
    private ImageView mIv_play;
    private FragmentTransaction mFragmentTransaction;
    private GameFragment mGameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFrameLayout = (FrameLayout) findViewById(R.id.fl_games);
        mIv_play = (ImageView) findViewById(R.id.iv_playgame);
        mIv_play.setOnClickListener(this);
        mGameFragment = new GameFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_playgame:
                mFragmentTransaction.add(R.id.fl_games, mGameFragment).commit();
                break;
        }
    }
}
