package com.sun.games;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout mFrameLayout;
    private ImageView mIv_play;
    private GameFragment mGameFragment;
    private boolean gameShow = false;
    private FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
    private ImageView mIv_start;
    private ImageView mIv_close;

    private boolean firstPoker = true;
    private Socket mSocket;


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
                    mIv_start.setVisibility(View.INVISIBLE);
                    mIv_close.setVisibility(View.INVISIBLE);
                } else {
                    getSupportFragmentManager().beginTransaction().show(mGameFragment).commit();
                    mIv_start.setVisibility(View.VISIBLE);
                    mIv_close.setVisibility(View.VISIBLE);
                }
                gameShow = !gameShow;
                break;
            case R.id.game_start:
                if (firstPoker) {
                    mGameFragment.gameReset();
                } else {
                    mGameFragment.sendPoker();
                    firstPoker = !firstPoker;
                }
                mIv_start.setClickable(false);
                break;
            case R.id.game_close:

                break;
        }
    }

    public void startClick() {
        mIv_start.setClickable(true);
    }

    private void connectServerWithTCPSocket() {
        try {
            mSocket = new Socket("192.168.1.32", 1989);
            boolean connected = mSocket.isConnected();

            //接受服务器数据
            InputStream inputStream = mSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(isr);
            bufferedReader.readLine();


            //发送到服务器
            OutputStream outputStream = mSocket.getOutputStream();
            // 步骤2：写入需要发送的数据到输出流对象中
            outputStream.write(("Carson_Ho" + "\n").getBytes("utf-8"));
            // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞
            outputStream.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }

//        os.close();
//        // 断开 客户端发送到服务器 的连接，即关闭输出流对象OutputStream
//
//        br.close();
//        // 断开 服务器发送到客户端 的连接，即关闭输入流读取器对象BufferedReader
//
//        socket.close();
//        // 最终关闭整个Socket连接


    }

}
