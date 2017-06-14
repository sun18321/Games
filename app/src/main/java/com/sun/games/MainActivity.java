package com.sun.games;

import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sun.bean.OpenRoom;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

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
    private int mPermission_internet;
    private int mPermission_write;
    private String mS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reqestPermission();

        findViewById(R.id.btn_json).setOnClickListener(this);
        findViewById(R.id.btn_connect).setOnClickListener(this);
        findViewById(R.id.btn_send).setOnClickListener(this);
        findViewById(R.id.btn_receive).setOnClickListener(this);

        init();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Toast.makeText(MainActivity.this, "得到网络权限了", Toast.LENGTH_SHORT).show();
    }

    private void reqestPermission() {
        mPermission_internet = ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        mPermission_write = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (mPermission_internet != PackageManager.PERMISSION_GRANTED || mPermission_write != PackageManager.PERMISSION_GRANTED) {
            String[] permission = {Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permission, 1001);
        }
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
            case R.id.btn_json:
                ArrayList<String> list = new ArrayList<>();
//                list.add("cmd");
//                list.add("create");
//                list.add("roomId");
//                list.add("13423256");
//                Gson gson = new Gson();
//                String s = gson.toJson(list);
//                System.out.println("是json吗:" + s);
                OpenRoom openRoom = new OpenRoom();
                openRoom.roomId = "13423256";
                openRoom.cmd = "create";
                openRoom.uid = "13423256";
                Gson gson = new Gson();
                mS = gson.toJson(openRoom);
                System.out.println("json:" + mS);
                break;
            case R.id.btn_connect:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        connectServerWithTCPSocket();

                        ReadThread readThread = new ReadThread();
                        readThread.start();
                    }
                }).start();

                break;
            case R.id.btn_send:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //发送到服务器
                            System.out.println("json数据" + mS);
                            if (mS == null) {
                                return;
                            }
                            if (mSocket == null) {
                                return;
                            }
                            OutputStream outputStream = mSocket.getOutputStream();
                            // 步骤2：写入需要发送的数据到输出流对象中
                            outputStream.write((mS).getBytes("utf-8"));
                            // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞
                            outputStream.flush();
                            System.out.println("发送成功了");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.btn_receive:
                System.out.println("点击receive");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("receive空前");
                            if (mSocket == null) {
                                return;
                            }
                            System.out.println("btn_开始收数据");
                            InputStream inputStream = mSocket.getInputStream();
                            InputStreamReader isr = new InputStreamReader(inputStream);
                            BufferedReader bufferedReader = new BufferedReader(isr);
                            String reply = null;
                            while ((reply = bufferedReader.readLine()) != null) {
                                System.out.println("btn_得到的数据" + reply);
                            }
//                            String line = bufferedReader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    }
                }).start();
                break;
        }
    }

    public void startClick() {
        mIv_start.setClickable(true);
    }

    private void connectServerWithTCPSocket() {
        try {
//            System.out.println("连接前" + mSocket.isConnected());

            System.out.println("开始socket连接了");
//            mSocket = new Socket("139.199.184.20", 9501);
            mSocket = new Socket("192.168.16.16", 8080);
            boolean connected = mSocket.isConnected();

            //接受服务器数据
            if (mSocket == null) {
                return;
            }

//            InputStream inputStream = mSocket.getInputStream();
//            InputStreamReader isr = new InputStreamReader(inputStream);
//            BufferedReader bufferedReader = new BufferedReader(isr);
//            String reply = null;
//            while ((reply = bufferedReader.readLine()) != null) {
//                System.out.println("得到的数据" + reply);
//            }

//            方法二
            InputStream is = mSocket.getInputStream();
            int len = -1;
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while ((len = is.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            System.out.println("方法二" + new String(byteArrayOutputStream.toByteArray()));


//            InputStream inputStream = mSocket.getInputStream();
//            InputStreamReader isr = new InputStreamReader(inputStream);
//            BufferedReader bufferedReader = new BufferedReader(isr);
//            String line = bufferedReader.readLine();
//            System.out.println("接受的数据" + line);


//            //发送到服务器
//            OutputStream outputStream = mSocket.getOutputStream();
//            // 步骤2：写入需要发送的数据到输出流对象中
//            outputStream.write(("Carson_Ho" + "\n").getBytes("utf-8"));
//            // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞
//            outputStream.flush();


            System.out.println("连接后" + mSocket.isConnected());
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

    class ReadThread extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("thread空前");

            if (mSocket != null) {
                try {
                    System.out.println("开始readThread了");
                    InputStream is = mSocket.getInputStream();
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) != -1) {
                        System.out.println("thread接收数据");

                        System.out.println("thread收到的" + new String(buffer));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
