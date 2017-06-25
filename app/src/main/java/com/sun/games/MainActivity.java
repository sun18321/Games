package com.sun.games;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sun.bean.CountdownBean;
import com.sun.bean.CreateRoomBean;
import com.sun.bean.GameResultBean;
import com.sun.bean.SendCreateRoomBean;
import com.sun.bean.StartGameBean;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout mFrameLayout;
    private ImageView mIv_play;
    private GameFragment mGameFragment;
    private boolean gameShow = false;
    private FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
    private ImageView mIv_start;
    private ImageView mIv_close;

    public boolean firstPoker = true;
    private Socket mSocket;
    private int mPermission_internet;
    private int mPermission_write;
    private String mS;

    public String id_host;
    public String id_guest;
    public String id_guest_host;
    public boolean isHost;
    private boolean dataComplete = false;


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

        temporaryInit();

    }

    private void temporaryInit() {
        final EditText edit_host = (EditText) findViewById(R.id.edit_host);
        final EditText edit_guest = (EditText) findViewById(R.id.edit_guest);
        final EditText edit_guest_host = (EditText) findViewById(R.id.edit_guest_host);

        findViewById(R.id.btn_host).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_host = edit_host.getText().toString();
                boolean b = "".equals(id_host);
                System.out.println("是否为空"+b);
                if (b) {
                    Toast.makeText(MainActivity.this, "请完善信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                isHost = true;
                dataComplete = true;
                if (mGameFragment == null) {
                    mGameFragment = new GameFragment();
                }
                mGameFragment.setHost(id_host, isHost);
            }
        });

        findViewById(R.id.edit_guest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_guest = edit_guest.getText().toString();
                id_guest_host = edit_guest_host.getText().toString();

                boolean b = "".equals(id_guest);
                boolean a = "".equals(id_guest_host);
                if (a || b) {
                    Toast.makeText(MainActivity.this, "请完善信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                isHost = false;
                dataComplete = true;
                if (mGameFragment==null) {
                    mGameFragment = new GameFragment();
                }
                mGameFragment.setGuest(id_guest, id_guest_host, isHost);
            }
        });

    }






    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_playgame:
                if (!dataComplete) {
                    Toast.makeText(MainActivity.this, "请完善信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                showFragment();
                break;

            case R.id.game_start:
                if (firstPoker) {
                    mGameFragment.gameReset();
                } else {
                    mGameFragment.sendPoker();
                    firstPoker = !firstPoker;
                }
                mIv_start.setClickable(false);
                mGameFragment.startGame();
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
                SendCreateRoomBean openRoom = new SendCreateRoomBean();
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

    public void showFragment() {
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
            if (isHost) {
                mIv_start.setVisibility(View.VISIBLE);
            }
            mIv_close.setVisibility(View.VISIBLE);
        }
        gameShow = !gameShow;
//                mGameFragment.connectToServer();
    }

    private void connectserver() {


    }

    public void startClick() {
        mIv_start.setClickable(true);
    }

    private void connectServerWithTCPSocket() {
        try {

            System.out.println("开始socket连接了");
            mSocket = new Socket("139.199.184.20", 9501);
            boolean connected = mSocket.isConnected();

            if (mSocket == null) {
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class ReadThread extends Thread {
        @Override
        public void run() {
            InputStream inputStream = null;
            try {
                inputStream = mSocket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("ReadThread获取socket的流");
            while (mSocket != null) {
                try {
                    InputStreamReader isr = new InputStreamReader(inputStream);
                    char[] charsbuffer = new char[1024];
                    int length = -1 ;
                    int total = 0 ;
                    StringBuffer reply = new StringBuffer();
                    boolean countdownEnd = false;
                    boolean createSuccess = false;
                    boolean startSuccess = false;

                    while ((length = isr.read(charsbuffer))!= -1){
                        reply.append(charsbuffer,total,length);
//                        total += length ;
//                        System.out.println(reply.toString());
                        String json = reply.toString();
                        Gson gson = new Gson();
                        if (!createSuccess) {
                            CreateRoomBean createRoomBean = gson.fromJson(json, CreateRoomBean.class);
                            if (createRoomBean.getStatus() == 0) {
                                System.out.println("创建房间成功");
                                createSuccess = true;
                            } else if (createRoomBean.getStatus() == 1) {
                                System.out.println("创建status=1" + createRoomBean.getData().getRoomId());
                            }
                        } else if (!startSuccess) {
                            StartGameBean startGameBean = gson.fromJson(json, StartGameBean.class);
                            if (startGameBean.getStatus() == 0) {
                                System.out.println("开始游戏成功");
                                startSuccess = true;
                            }
                        } else {
                            if (countdownEnd) {
                                GameResultBean gameResultBean = gson.fromJson(json, GameResultBean.class);
                                System.out.println("谁赢了" + gameResultBean.getData().getResult().getWin());
                                List<String> card1 = gameResultBean.getData().getResult().getDetail().get(0).getCards();
                                for (int i = 0; i < card1.size(); i++) {
                                    System.out.println("第一个人的牌" + card1.get(i));
                                }
                                List<String> card2 = gameResultBean.getData().getResult().getDetail().get(1).getCards();
                                for (int i = 0; i < card1.size(); i++) {
                                    System.out.println("第二个人的牌" + card2.get(i));
                                }
                                List<String> card3 = gameResultBean.getData().getResult().getDetail().get(2).getCards();
                                for (int i = 0; i < card1.size(); i++) {
                                    System.out.println("第三个人的牌" + card1.get(i));
                                }
                                countdownEnd = false;
                                startSuccess = false;
                            } else if (!countdownEnd) {
                                CountdownBean countdownBean = gson.fromJson(json, CountdownBean.class);
                                System.out.println("倒计时" + countdownBean.getData().getTimecount());
                                if (countdownBean.getData().getTimecount() == 1) {
                                    countdownEnd = true;
                                }
                            }
                        }
                        reply.delete(0, reply.length());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("err" + e.toString());
                }
            }
        }
    }

}
