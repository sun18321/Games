package com.sun.games;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout mFrameLayout;
    private ImageView mIv_play;
    private GameFragment mGameFragment;
    private boolean gameShow = false;
    private FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
    private ImageView mIv_start;
    private ImageView mIv_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mFrameLayout = (FrameLayout) findViewById(R.id.fl_games);
        mIv_play = (ImageView) findViewById(R.id.iv_playgame);
        mIv_play.setOnClickListener(this);
        mIv_start = (ImageView) findViewById(R.id.game_start);
        mIv_close = (ImageView) findViewById(R.id.game_close);
        mIv_start.setOnClickListener(this);
        mIv_close.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_playgame:
                if (mGameFragment == null) {
                    mGameFragment = new GameFragment();
                }
                if (mFragmentTransaction.isEmpty()) {
                    mFragmentTransaction.add(R.id.fl_games, mGameFragment).hide(mGameFragment).commit();
                }

                if (gameShow) {
                    getSupportFragmentManager().beginTransaction().hide(mGameFragment).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().show(mGameFragment).commit();
                }
                gameShow = !gameShow;
                break;
            case R.id.game_start:

                mGameFragment.sendPoker();
                break;
            case R.id.game_close:

                break;
        }
    }

}
