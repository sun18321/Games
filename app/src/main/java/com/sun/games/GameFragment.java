package com.sun.games;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sun.bean.BetBean;
import com.sun.bean.BindBean;
import com.sun.bean.CountdownBean;
import com.sun.bean.CreateRoomBean;
import com.sun.bean.DataTypeBean;
import com.sun.bean.EnterRoomBean;
import com.sun.bean.GameBetBean;
import com.sun.bean.GameResultBean;
import com.sun.bean.SendBetBean;
import com.sun.bean.SendBindBean;
import com.sun.bean.SendCreateRoomBean;
import com.sun.bean.SendStartGameBean;
import com.sun.bean.StartGameBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by stephensun on 2017/5/22.
 */
public class GameFragment extends Fragment implements View.OnClickListener {

    private View mView;
    private ImageView mIv_deal;
    private ImageView mIv_trans;
    private Context mContext;
    private int[] mLocation;
    private int[] mLocation2;
    private ImageView mBull_1;
    private ImageView mBull_2;
    private ImageView mBull_3;
    private int mWidth;
    private int mHeight;
    private MainActivity mActivity;
    private RelativeLayout mPlay1;
    private ImageView mIv_player1_poker1;
    private ImageView mIv_player1_poker2;
    private ImageView mIv_player1_poker3;
    private ImageView mIv_player1_poker4;
    private ImageView mIv_player1_poker5;
    private RelativeLayout mPlay2;
    private ImageView mIv_player2_poker1;
    private ImageView mIv_player2_poker2;
    private ImageView mIv_player2_poker3;
    private ImageView mIv_player2_poker4;
    private ImageView mIv_player2_poker5;
    private RelativeLayout mPlay3;
    private ImageView mIv_player3_poker1;
    private ImageView mIv_player3_poker2;
    private ImageView mIv_player3_poker3;
    private ImageView mIv_player3_poker4;
    private ImageView mIv_player3_poker5;

    private int player_1 = 1;
    private int player_2 = 1;
    private int player_3 = 1;

    private ObjectAnimator mTranslationX;
    private TranslateAnimation mAnimation_2;
    private TranslateAnimation mAnimation_3;
    private RelativeLayout mRL_toast;
    private ScaleAnimation mScaleAnimation_appear;
    private ScaleAnimation mScaleAnimation_clear;
    private RelativeLayout mRL_group1;
    private RelativeLayout mRL_group2;
    private RelativeLayout mRL_group3;
    private ScaleAnimation mAnim_open1;
    private ScaleAnimation mAnim_open2;
    private ScaleAnimation mAnim_open3;
    private RelativeLayout mRl_player1_single1;
    private RelativeLayout mRl_player1_single2;
    private RelativeLayout mRl_player1_single3;
    private RelativeLayout mRl_player1_single4;
    private RelativeLayout mRl_player1_single5;
    private RelativeLayout mRl_player2_single1;
    private RelativeLayout mRl_player2_single2;
    private RelativeLayout mRl_player2_single3;
    private RelativeLayout mRl_player2_single4;
    private RelativeLayout mRl_player2_single5;
    private RelativeLayout mRl_player3_single1;
    private RelativeLayout mRl_player3_single2;
    private RelativeLayout mRl_player3_single3;
    private RelativeLayout mRl_player3_single4;
    private RelativeLayout mRl_player3_single5;
    private ImageView mIv_player1_first_number_center;
    private ImageView mIv_player1_first_number;
    private ImageView mIv_player1_first_color;
    private ImageView mIv_player1_second_number_center;
    private ImageView mIv_player1_second_number;
    private ImageView mIv_player1_second_color;
    private ImageView mIv_player1_third_number_center;
    private ImageView mIv_player1_third_number;
    private ImageView mIv_player1_third_color;
    private ImageView mIv_player1_fourth_number_center;
    private ImageView mIv_player1_fourth_number;
    private ImageView mIv_player1_fourth_color;
    private ImageView mIv_player1_fifth_number_center;
    private ImageView mIv_player1_fifth_number;
    private ImageView mIv_player1_fifth_color;
    private ImageView mIv_player2_first_number_center;
    private ImageView mIv_player2_first_number;
    private ImageView mIv_player2_first_color;
    private ImageView mIv_player2_second_number_center;
    private ImageView mIv_player2_second_number;
    private ImageView mIv_player2_second_color;
    private ImageView mIv_player2_third_number_center;
    private ImageView mIv_player2_third_number;
    private ImageView mIv_player2_third_color;
    private ImageView mIv_player2_fourth_number_center;
    private ImageView mIv_player2_fourth_number;
    private ImageView mIv_player2_fourth_color;
    private ImageView mIv_player2_fifth_number_center;
    private ImageView mIv_player2_fifth_number;
    private ImageView mIv_player2_fifth_color;
    private ImageView mIv_player3_first_number_center;
    private ImageView mIv_player3_first_number;
    private ImageView mIv_player3_first_color;
    private ImageView mIv_player3_second_number_center;
    private ImageView mIv_player3_second_number;
    private ImageView mIv_player3_second_color;
    private ImageView mIv_player3_third_number_center;
    private ImageView mIv_player3_third_number;
    private ImageView mIv_player3_third_color;
    private ImageView mIv_player3_fourth_number_center;
    private ImageView mIv_player3_fourth_number;
    private ImageView mIv_player3_fourth_color;
    private ImageView mIv_player3_fifth_number_center;
    private ImageView mIv_player3_fifth_number;
    private ImageView mIv_player3_fifth_color;

    //倒计时
    private RelativeLayout mRl_clock;
    private TextView mTv_clock;

    //下注数量和下注位置
    private int bet_pos = -1;
    private int bet_amount = -1;
    private String[] arr = {"50", "100", "500"};

    //花色
    private final String POKER_SPADE = "poker_spade";
    private final String POKER_HEART = "poker_heart";
    private final String POKER_DIAMOND = "poker_diamond";
    private final String POKER_CLUB = "poker_club";

    //牌型
    private final String POKER_A_BLACK = "img_poker_ace_black";
    private final String POKER_A_RED = "img_poker_ace_red";
    private final String POKER_2_BLACK = "img_poker_2_black";
    private final String POKER_2_RED = "img_poker_2_red";
    private final String POKER_3_BLACK = "img_poker_3_black";
    private final String POKER_3_RED = "img_poker_3_red";
    private final String POKER_4_BLACK = "img_poker_4_black";
    private final String POKER_4_RED = "img_poker_4_red";
    private final String POKER_5_BLACK = "img_poker_5_black";
    private final String POKER_5_RED = "img_poker_5_red";
    private final String POKER_6_BLACK = "img_poker_6_black";
    private final String POKER_6_RED = "img_poker_6_red";
    private final String POKER_7_BLACK = "img_poker_7_black";
    private final String POKER_7_RED = "img_poker_7_red";
    private final String POKER_8_BLACK = "img_poker_8_black";
    private final String POKER_8_RED = "img_poker_8_red";
    private final String POKER_9_BLACK = "img_poker_9_black";
    private final String POKER_9_RED = "img_poker_9_red";
    private final String POKER_10_BLACK = "img_poker_10_black";
    private final String POKER_10_RED = "img_poker_10_red";
    private final String POKER_J_BLACK = "img_poker_jack_black";
    private final String POKER_J_RED = "img_poker_jack_red";
    private final String POKER_Q_BLACK = "img_poker_queen_black";
    private final String POKER_Q_RED = "img_poker_queen_red";
    private final String POKER_K_BLACK = "img_poker_king_black";
    private final String POKER_K_RED = "img_poker_king_red";

    //牛几
    private final String NIU_NO = "text_bull_mn";
    private final String NIU_ONE = "text_bull_n1";
    private final String NIU_TWO = "text_bull_n2";
    private final String NIU_THREE = "text_bull_n3";
    private final String NIU_FOUR = "text_bull_n4";
    private final String NIU_FIVE = "text_bull_n5";
    private final String NIU_SIX = "text_bull_n6";
    private final String NIU_SEVEN = "text_bull_n7";
    private final String NIU_EIGHT = "text_bull_n8";
    private final String NIU_NINE = "text_bull_n9";
    private final String NIU_TEN = "text_bull_nn";
    private ImageView mIv_player1_fight;
    private ImageView mIv_player2_fight;
    private ImageView mIv_player3_fight;
    private int mPlayer1_niu;
    private int mPlayer2_niu;
    private int mPlayer3_niu;
    private Socket mSocket;

    //三玩家牌集合
    private List<Integer> list_player1_number = new ArrayList<>();
    private List<Integer> list_player2_number = new ArrayList<>();
    private List<Integer> list_player3_number = new ArrayList<>();
    private List<Integer> list_player1_color = new ArrayList<>();
    private List<Integer> list_player2_color = new ArrayList<>();
    private List<Integer> list_player3_color = new ArrayList<>();

    //每头牛收到的下注数量集合
    private List<Integer> list_bet = new ArrayList<>();

    private int clock_countdown;

    private boolean firstCrete = true;
    private OutputStream mOutputStream;

    public final int HANDLER_DISPLAY_POKER = 1002;
    private final int HANDLER_TIME_CLOCK = 1001;
    private final int HANDLER_CREATE_ROOM = 1003;
    private final int HANDLER_DISPLAY_BET = 1004;
    private final int HANDLER_GUEST_START = 1005;


    private boolean timeIsVisible = false;
    private boolean firstPoker = true;

    private final String TYPE_CREATE = "create";
    private final String TYPE_GAME_NEW = "game_new";
    private final String TYPE_GAME_START = "game_poll";
    private final String TYPE_GAME_RESULT = "game_result";
    private final String TYPE_GAME_BET = "game_bet"; //用于统计每个牛的下注多少
    private final String TYPE_ENTER = "enter";
    private final String TYPE_BIND = "bind";
    private final String TYPE_LEAVE = "leave";
    private final String TYPE_BET = "bet"; //用于接受服务器返回玩家下注是否成功
    private final String TYPE_BONUS = "game_bonus";
    private final String TYPE_CLOSE = "close";
    private final String TYPE_NEW = "new";//主播开始游戏的反馈(只有主播会收到)


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HANDLER_DISPLAY_POKER:
                    mRL_toast.setVisibility(View.VISIBLE);
                    mRL_toast.startAnimation(mScaleAnimation_appear);
                    mRl_clock.setVisibility(View.INVISIBLE);
                    timeIsVisible = false;
                    break;
                case HANDLER_TIME_CLOCK:
                    if (!timeIsVisible) {
                        mRl_clock.setVisibility(View.VISIBLE);
                        timeIsVisible = true;
                    }
                    mTv_clock.setText("" + clock_countdown);
                    break;
                case HANDLER_CREATE_ROOM:
                    createRoom();
                    break;
                case HANDLER_DISPLAY_BET:
                    for (int i = 0; i < list_bet.size(); i++) {
                        System.out.println("下注集合大小" + list_bet.size());
                        switch (i) {
                            case 0:
                                mTv_bet_0.setText("" + list_bet.get(i));
                                break;
                            case 1:
                                mTv_bet_1.setText("" + list_bet.get(i));
                                break;
                            case 2:
                                mTv_bet_2.setText("" + list_bet.get(i));
                                break;
                        }
                    }
                    break;
                case HANDLER_GUEST_START:
                    if (firstPoker) {
                        gameReset();
                    } else {
                        sendPoker();
                        firstPoker = !firstPoker;
                    }
                    break;
            }
        }
    };
    private LinearLayout mLin_bet;
    private RelativeLayout mRl_bet_0;
    private RelativeLayout mRl_bet_1;
    private RelativeLayout mRl_bet_2;
    private ImageView mImg_pos_0;
    private ImageView mImg_pos_1;
    private ImageView mImg_pos_2;
    private TextView mTv_bet_0;
    private TextView mTv_bet_1;
    private TextView mTv_bet_2;
    private RelativeLayout mRl_chose_0;
    private RelativeLayout mRl_chose_1;
    private RelativeLayout mRl_chose_2;
    private Button mBtn_bet;

    public String id_host;
    public String id_guest;
    public String id_guest_host;
    public boolean isHost;
    private boolean isFirst2Server = true;

    private ImageView mImg_niu_0;
    private ImageView mImg_niu_1;
    private ImageView mImg_niu_2;


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.view_battle, container, false);

        init();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    private void init() {
        mContext = this.getActivity();
        mIv_deal = (ImageView) mView.findViewById(R.id.poker_deal);
        mIv_trans = (ImageView) mView.findViewById(R.id.poker_translate);
        mBull_1 = (ImageView) mView.findViewById(R.id.bull_1);
        mBull_2 = (ImageView) mView.findViewById(R.id.bull_2);
        mBull_3 = (ImageView) mView.findViewById(R.id.bull_3);
        mRL_toast = (RelativeLayout) mView.findViewById(R.id.game_start_toast);

        //倒计时
        mRl_clock = (RelativeLayout) mView.findViewById(R.id.rl_clock);
        mTv_clock = (TextView) mView.findViewById(R.id.tv_countdown);

        //poker 背面
        mPlay1 = (RelativeLayout) mView.findViewById(R.id.player_1);
        mIv_player1_poker1 = (ImageView) mPlay1.findViewById(R.id.group_1);
        mIv_player1_poker2 = (ImageView) mPlay1.findViewById(R.id.group_2);
        mIv_player1_poker3 = (ImageView) mPlay1.findViewById(R.id.group_3);
        mIv_player1_poker4 = (ImageView) mPlay1.findViewById(R.id.group_4);
        mIv_player1_poker5 = (ImageView) mPlay1.findViewById(R.id.group_5);

        mPlay2 = (RelativeLayout) mView.findViewById(R.id.player_2);
        mIv_player2_poker1 = (ImageView) mPlay2.findViewById(R.id.group_1);
        mIv_player2_poker2 = (ImageView) mPlay2.findViewById(R.id.group_2);
        mIv_player2_poker3 = (ImageView) mPlay2.findViewById(R.id.group_3);
        mIv_player2_poker4 = (ImageView) mPlay2.findViewById(R.id.group_4);
        mIv_player2_poker5 = (ImageView) mPlay2.findViewById(R.id.group_5);

        mPlay3 = (RelativeLayout) mView.findViewById(R.id.player_3);
        mIv_player3_poker1 = (ImageView) mPlay3.findViewById(R.id.group_1);
        mIv_player3_poker2 = (ImageView) mPlay3.findViewById(R.id.group_2);
        mIv_player3_poker3 = (ImageView) mPlay3.findViewById(R.id.group_3);
        mIv_player3_poker4 = (ImageView) mPlay3.findViewById(R.id.group_4);
        mIv_player3_poker5 = (ImageView) mPlay3.findViewById(R.id.group_5);


        //poker 正面
        mRL_group1 = (RelativeLayout) mPlay1.findViewById(R.id.poker_group);
        mRL_group2 = (RelativeLayout) mPlay2.findViewById(R.id.poker_group);
        mRL_group3 = (RelativeLayout) mPlay3.findViewById(R.id.poker_group);

        mRl_player1_single1 = (RelativeLayout) mPlay1.findViewById(R.id.group_single_1);
        mRl_player1_single2 = (RelativeLayout) mPlay1.findViewById(R.id.group_single_2);
        mRl_player1_single3 = (RelativeLayout) mPlay1.findViewById(R.id.group_single_3);
        mRl_player1_single4 = (RelativeLayout) mPlay1.findViewById(R.id.group_single_4);
        mRl_player1_single5 = (RelativeLayout) mPlay1.findViewById(R.id.group_single_5);

        mRl_player2_single1 = (RelativeLayout) mPlay2.findViewById(R.id.group_single_1);
        mRl_player2_single2 = (RelativeLayout) mPlay2.findViewById(R.id.group_single_2);
        mRl_player2_single3 = (RelativeLayout) mPlay2.findViewById(R.id.group_single_3);
        mRl_player2_single4 = (RelativeLayout) mPlay2.findViewById(R.id.group_single_4);
        mRl_player2_single5 = (RelativeLayout) mPlay2.findViewById(R.id.group_single_5);

        mRl_player3_single1 = (RelativeLayout) mPlay3.findViewById(R.id.group_single_1);
        mRl_player3_single2 = (RelativeLayout) mPlay3.findViewById(R.id.group_single_2);
        mRl_player3_single3 = (RelativeLayout) mPlay3.findViewById(R.id.group_single_3);
        mRl_player3_single4 = (RelativeLayout) mPlay3.findViewById(R.id.group_single_4);
        mRl_player3_single5 = (RelativeLayout) mPlay3.findViewById(R.id.group_single_5);

        //玩家一
        mIv_player1_first_number_center = (ImageView) mRl_player1_single1.findViewById(R.id.poker_number_center);
        mIv_player1_first_number = (ImageView) mRl_player1_single1.findViewById(R.id.poker_number);
        mIv_player1_first_color = (ImageView) mRl_player1_single1.findViewById(R.id.poker_color);
        mIv_player1_second_number_center = (ImageView) mRl_player1_single2.findViewById(R.id.poker_number_center);
        mIv_player1_second_number = (ImageView) mRl_player1_single2.findViewById(R.id.poker_number);
        mIv_player1_second_color = (ImageView) mRl_player1_single2.findViewById(R.id.poker_color);
        mIv_player1_third_number_center = (ImageView) mRl_player1_single3.findViewById(R.id.poker_number_center);
        mIv_player1_third_number = (ImageView) mRl_player1_single3.findViewById(R.id.poker_number);
        mIv_player1_third_color = (ImageView) mRl_player1_single3.findViewById(R.id.poker_color);
        mIv_player1_fourth_number_center = (ImageView) mRl_player1_single4.findViewById(R.id.poker_number_center);
        mIv_player1_fourth_number = (ImageView) mRl_player1_single4.findViewById(R.id.poker_number);
        mIv_player1_fourth_color = (ImageView) mRl_player1_single4.findViewById(R.id.poker_color);
        mIv_player1_fifth_number_center = (ImageView) mRl_player1_single5.findViewById(R.id.poker_number_center);
        mIv_player1_fifth_number = (ImageView) mRl_player1_single5.findViewById(R.id.poker_number);
        mIv_player1_fifth_color = (ImageView) mRl_player1_single5.findViewById(R.id.poker_color);

        //玩家二
        mIv_player2_first_number_center = (ImageView) mRl_player2_single1.findViewById(R.id.poker_number_center);
        mIv_player2_first_number = (ImageView) mRl_player2_single1.findViewById(R.id.poker_number);
        mIv_player2_first_color = (ImageView) mRl_player2_single1.findViewById(R.id.poker_color);
        mIv_player2_second_number_center = (ImageView) mRl_player2_single2.findViewById(R.id.poker_number_center);
        mIv_player2_second_number = (ImageView) mRl_player2_single2.findViewById(R.id.poker_number);
        mIv_player2_second_color = (ImageView) mRl_player2_single2.findViewById(R.id.poker_color);
        mIv_player2_third_number_center = (ImageView) mRl_player2_single3.findViewById(R.id.poker_number_center);
        mIv_player2_third_number = (ImageView) mRl_player2_single3.findViewById(R.id.poker_number);
        mIv_player2_third_color = (ImageView) mRl_player2_single3.findViewById(R.id.poker_color);
        mIv_player2_fourth_number_center = (ImageView) mRl_player2_single4.findViewById(R.id.poker_number_center);
        mIv_player2_fourth_number = (ImageView) mRl_player2_single4.findViewById(R.id.poker_number);
        mIv_player2_fourth_color = (ImageView) mRl_player2_single4.findViewById(R.id.poker_color);
        mIv_player2_fifth_number_center = (ImageView) mRl_player2_single5.findViewById(R.id.poker_number_center);
        mIv_player2_fifth_number = (ImageView) mRl_player2_single5.findViewById(R.id.poker_number);
        mIv_player2_fifth_color = (ImageView) mRl_player2_single5.findViewById(R.id.poker_color);

        //玩家三
        mIv_player3_first_number_center = (ImageView) mRl_player3_single1.findViewById(R.id.poker_number_center);
        mIv_player3_first_number = (ImageView) mRl_player3_single1.findViewById(R.id.poker_number);
        mIv_player3_first_color = (ImageView) mRl_player3_single1.findViewById(R.id.poker_color);
        mIv_player3_second_number_center = (ImageView) mRl_player3_single2.findViewById(R.id.poker_number_center);
        mIv_player3_second_number = (ImageView) mRl_player3_single2.findViewById(R.id.poker_number);
        mIv_player3_second_color = (ImageView) mRl_player3_single2.findViewById(R.id.poker_color);
        mIv_player3_third_number_center = (ImageView) mRl_player3_single3.findViewById(R.id.poker_number_center);
        mIv_player3_third_number = (ImageView) mRl_player3_single3.findViewById(R.id.poker_number);
        mIv_player3_third_color = (ImageView) mRl_player3_single3.findViewById(R.id.poker_color);
        mIv_player3_fourth_number_center = (ImageView) mRl_player3_single4.findViewById(R.id.poker_number_center);
        mIv_player3_fourth_number = (ImageView) mRl_player3_single4.findViewById(R.id.poker_number);
        mIv_player3_fourth_color = (ImageView) mRl_player3_single4.findViewById(R.id.poker_color);
        mIv_player3_fifth_number_center = (ImageView) mRl_player3_single5.findViewById(R.id.poker_number_center);
        mIv_player3_fifth_number = (ImageView) mRl_player3_single5.findViewById(R.id.poker_number);
        mIv_player3_fifth_color = (ImageView) mRl_player3_single5.findViewById(R.id.poker_color);

        //牛几
        mIv_player1_fight = (ImageView) mPlay1.findViewById(R.id.fight_bull_number);
        mIv_player2_fight = (ImageView) mPlay2.findViewById(R.id.fight_bull_number);
        mIv_player3_fight = (ImageView) mPlay3.findViewById(R.id.fight_bull_number);

        //下注界面
        mLin_bet = (LinearLayout) mView.findViewById(R.id.ll_bet);
        mRl_bet_0 = (RelativeLayout) mView.findViewById(R.id.rl_bet_0);
        mRl_bet_1 = (RelativeLayout) mView.findViewById(R.id.rl_bet_1);
        mRl_bet_2 = (RelativeLayout) mView.findViewById(R.id.rl_bet_2);
        mRl_bet_0.setOnClickListener(this);
        mRl_bet_1.setOnClickListener(this);
        mRl_bet_2.setOnClickListener(this);

        //选择牛
        mRl_chose_0 = (RelativeLayout) mView.findViewById(R.id.bet_niu_0);
        mRl_chose_1 = (RelativeLayout) mView.findViewById(R.id.bet_niu_1);
        mRl_chose_2 = (RelativeLayout) mView.findViewById(R.id.bet_niu_2);
        mRl_chose_0.setOnClickListener(this);
        mRl_chose_1.setOnClickListener(this);
        mRl_chose_2.setOnClickListener(this);

        mImg_pos_0 = (ImageView) mView.findViewById(R.id.img_pos_0);
        mImg_pos_1 = (ImageView) mView.findViewById(R.id.img_pos_1);
        mImg_pos_2 = (ImageView) mView.findViewById(R.id.img_pos_2);

        mImg_niu_0 = (ImageView) mView.findViewById(R.id.img_niu_0);
        mImg_niu_1 = (ImageView) mView.findViewById(R.id.img_niu_1);
        mImg_niu_2 = (ImageView) mView.findViewById(R.id.img_niu_2);

        //已经下注数量
        mTv_bet_0 = (TextView) mView.findViewById(R.id.chip_amount_0);
        mTv_bet_1 = (TextView) mView.findViewById(R.id.chip_amount_1);
        mTv_bet_2 = (TextView) mView.findViewById(R.id.chip_amount_2);

        mActivity = (MainActivity) GameFragment.this.getActivity();

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);

        mWidth = wm.getDefaultDisplay().getWidth();
        mHeight = wm.getDefaultDisplay().getHeight();


        mBtn_bet = (Button) mView.findViewById(R.id.btn_chip);
        mBtn_bet.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bet_niu_0:
                if (bet_pos == 0) {
                    bet_pos = -1;
                } else {
                    bet_pos = 0;
                }
                break;
            case R.id.bet_niu_1:
                if (bet_pos == 1) {
                    bet_pos = -1;
                } else {
                    bet_pos = 1;
                }
                break;
            case R.id.bet_niu_2:
                if (bet_pos == 2) {
                    bet_pos = -1;
                } else {
                    bet_pos = 2;
                }
                break;
            case R.id.rl_bet_0:
                if (bet_amount == 0) {
                    bet_amount = -1;
                } else {
                    bet_amount = 0;
                }
                break;
            case R.id.rl_bet_1:
                if (bet_amount == 1) {
                    bet_amount = -1;
                } else {
                    bet_amount = 1;
                }
                break;
            case R.id.rl_bet_2:
                if (bet_amount == 2) {
                    bet_amount = -1;
                } else {
                    bet_amount = 2;
                }
                break;
            case R.id.btn_chip:
                betToServer();
                break;
        }
        if (bet_pos == 0) {
            mImg_niu_0.setVisibility(View.VISIBLE);
            mImg_niu_1.setVisibility(View.INVISIBLE);
            mImg_niu_2.setVisibility(View.INVISIBLE);
        } else if (bet_pos == 1) {
            mImg_niu_0.setVisibility(View.INVISIBLE);
            mImg_niu_1.setVisibility(View.VISIBLE);
            mImg_niu_2.setVisibility(View.INVISIBLE);
        } else if (bet_pos == 2) {
            mImg_niu_0.setVisibility(View.INVISIBLE);
            mImg_niu_1.setVisibility(View.INVISIBLE);
            mImg_niu_2.setVisibility(View.VISIBLE);
        } else if (bet_pos == -1) {
            mImg_niu_0.setVisibility(View.INVISIBLE);
            mImg_niu_1.setVisibility(View.INVISIBLE);
            mImg_niu_2.setVisibility(View.INVISIBLE);
        }


        if (bet_amount == 0) {
            mImg_pos_0.setVisibility(View.VISIBLE);
            mImg_pos_1.setVisibility(View.INVISIBLE);
            mImg_pos_2.setVisibility(View.INVISIBLE);
        } else if (bet_amount == 1) {
            mImg_pos_0.setVisibility(View.INVISIBLE);
            mImg_pos_1.setVisibility(View.VISIBLE);
            mImg_pos_2.setVisibility(View.INVISIBLE);
        } else if (bet_amount == 2) {
            mImg_pos_0.setVisibility(View.INVISIBLE);
            mImg_pos_1.setVisibility(View.INVISIBLE);
            mImg_pos_2.setVisibility(View.VISIBLE);
        } else if (bet_amount == -1) {
            mImg_pos_0.setVisibility(View.INVISIBLE);
            mImg_pos_1.setVisibility(View.INVISIBLE);
            mImg_pos_2.setVisibility(View.INVISIBLE);
        }

        if (bet_amount != -1 && bet_pos != -1) {
            mBtn_bet.setClickable(true);
        } else {
            mBtn_bet.setClickable(false);
        }
    }

    public void setHost(String host, boolean isHost) {
        id_host = host;
        this.isHost = isHost;
        if (isFirst2Server) {
            connectToServer();
            isFirst2Server = false;
        }
    }

    public void setGuest(String guest, String guest_host, boolean isHost) {
        id_guest = guest;
        id_guest_host = guest_host;
        this.isHost = isHost;

        if (isFirst2Server) {
            connectToServer();
            isFirst2Server = false;
        }
    }

    private void betToServer() {
        SendBetBean sendBetBean = new SendBetBean();
        sendBetBean.cmd = "bet";
        String coin = arr[bet_amount];
        sendBetBean.coin = coin;
        if (isHost) {
            sendBetBean.uid = id_host;
            sendBetBean.roomId = id_host;
        } else {
            sendBetBean.uid = id_guest;
            sendBetBean.roomId = id_guest_host;
        }
        sendBetBean.pos = "" + bet_pos;

        Gson gson = new Gson();
        final String json = gson.toJson(sendBetBean);
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mSocket != null) {
                    try {
                        if (mSocket != null) {
                            mOutputStream = mSocket.getOutputStream();
                        }
                        String s = json + "\\n";
                        System.out.println("json拼接" + s);
                        mOutputStream.write((s).getBytes("utf-8"));
                        // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞
                        mOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    public void sendPoker() {
//        int i = 1;
//        mTranslationX = ObjectAnimator.ofFloat(mIv_trans, "translationX", 0, (float) (-0.3 * mWidth));
//        mTranslationX.setDuration(600);
//        mTranslationX.setRepeatCount(4);
//        mTranslationX.setRepeatMode(ValueAnimator.RESTART);
//        mTranslationX.addListener(this);
//        mTranslationX.start();

        mIv_deal.setVisibility(View.VISIBLE);
        mIv_trans.setVisibility(View.VISIBLE);
        TranslateAnimation animation_1 = new TranslateAnimation(0, (float) (-0.3 * mWidth), 0, (float) (-0.3 * mWidth));
        animation_1.setDuration(200);
        animation_1.setRepeatCount(4);

        animation_1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                mIv_player1_poker5.setVisibility(View.VISIBLE);
                mIv_trans.startAnimation(mAnimation_2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

                switch (player_1) {
                    case 1:
                        mIv_player1_poker1.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        mIv_player1_poker2.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        mIv_player1_poker3.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        mIv_player1_poker4.setVisibility(View.VISIBLE);
                        break;
                }
                player_1++;
            }
        });
        mIv_trans.startAnimation(animation_1);

        mAnimation_2 = new TranslateAnimation(0, 0, 0, (float) (-0.3 * mWidth));
        mAnimation_2.setDuration(200);
        mAnimation_2.setRepeatCount(4);
        mAnimation_2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                mIv_player2_poker5.setVisibility(View.VISIBLE);
                mIv_trans.startAnimation(mAnimation_3);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

                switch (player_2) {
                    case 1:
                        mIv_player2_poker1.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        mIv_player2_poker2.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        mIv_player2_poker3.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        mIv_player2_poker4.setVisibility(View.VISIBLE);
                        break;
                }
                player_2++;

            }
        });

        mAnimation_3 = new TranslateAnimation(0, (float) (0.3 * mWidth), 0, (float) (-0.3 * mWidth));
        mAnimation_3.setDuration(200);
        mAnimation_3.setRepeatCount(4);
        mAnimation_3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                mIv_player3_poker5.setVisibility(View.VISIBLE);
                mIv_trans.setVisibility(View.INVISIBLE);
                mIv_deal.setVisibility(View.INVISIBLE);

                //开始socket请求
//                startGame();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

                switch (player_3) {
                    case 1:
                        mIv_player3_poker1.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        mIv_player3_poker2.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        mIv_player3_poker3.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        mIv_player3_poker4.setVisibility(View.VISIBLE);
                        break;
                }
                player_3++;
            }
        });


        mScaleAnimation_appear = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mScaleAnimation_appear.setDuration(400);
        mScaleAnimation_appear.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //配置玩家一的牌
                int exact_niu = getNiu(mPlayer1_niu);
                for (int i = 0; i < 5; i++) {
                    int exact_num = whichCard(list_player1_number.get(i), list_player1_color.get(i));
                    int exact_color = whichColor(list_player1_color.get(i));
                    switch (i) {
                        case 0:
                            mIv_player1_first_number.setImageResource(exact_num);
                            mIv_player1_first_color.setImageResource(exact_color);
                            break;
                        case 1:
                            mIv_player1_second_number.setImageResource(exact_num);
                            mIv_player1_second_color.setImageResource(exact_color);
                            break;
                        case 2:
                            mIv_player1_third_number.setImageResource(exact_num);
                            mIv_player1_third_color.setImageResource(exact_color);
                            break;
                        case 3:
                            mIv_player1_fourth_number.setImageResource(exact_num);
                            mIv_player1_fourth_color.setImageResource(exact_color);
                            break;
                        case 4:
                            mIv_player1_fifth_number.setImageResource(exact_num);
                            mIv_player1_fifth_color.setImageResource(exact_color);
                            break;
                    }
                }
                mIv_player1_fight.setImageResource(exact_niu);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                mRL_group1.startAnimation(mAnim_open1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        mScaleAnimation_clear = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mScaleAnimation_clear.setDuration(400);
        mScaleAnimation_clear.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                mRL_group2.startAnimation(mAnim_open2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {


            }
        });

        mAnim_open1 = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnim_open1.setDuration(300);
        mAnim_open1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                int exact_niu = getNiu(mPlayer2_niu);
                for (int i = 0; i < 5; i++) {
                    int exact_num = whichCard(list_player2_number.get(i), list_player2_color.get(i));
                    int exact_color = whichColor(list_player2_color.get(i));
                    switch (i) {
                        case 0:
                            mIv_player2_first_number.setImageResource(exact_num);
                            mIv_player2_first_color.setImageResource(exact_color);
                            break;
                        case 1:
                            mIv_player2_second_number.setImageResource(exact_num);
                            mIv_player2_second_color.setImageResource(exact_color);
                            break;
                        case 2:
                            mIv_player2_third_number.setImageResource(exact_num);
                            mIv_player2_third_color.setImageResource(exact_color);
                            break;
                        case 3:
                            mIv_player2_fourth_number.setImageResource(exact_num);
                            mIv_player2_fourth_color.setImageResource(exact_color);
                            break;
                        case 4:
                            mIv_player2_fifth_number.setImageResource(exact_num);
                            mIv_player2_fifth_color.setImageResource(exact_color);
                            break;
                    }
                }
                mIv_player2_fight.setImageResource(exact_niu);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                mIv_player1_poker1.setVisibility(View.INVISIBLE);
                mIv_player1_poker2.setVisibility(View.INVISIBLE);
                mIv_player1_poker3.setVisibility(View.INVISIBLE);
                mIv_player1_poker4.setVisibility(View.INVISIBLE);
                mIv_player1_poker5.setVisibility(View.INVISIBLE);

                mRL_toast.startAnimation(mScaleAnimation_clear);
                mRL_toast.setVisibility(View.INVISIBLE);
                mRL_group1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mAnim_open2 = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnim_open2.setDuration(300);
        mAnim_open2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                int exact_niu = getNiu(mPlayer3_niu);
                for (int i = 0; i < 5; i++) {
                    int exact_num = whichCard(list_player3_number.get(i), list_player3_color.get(i));
                    int exact_color = whichColor(list_player3_color.get(i));
                    switch (i) {
                        case 0:
                            mIv_player3_first_number.setImageResource(exact_num);
                            mIv_player3_first_color.setImageResource(exact_color);
                            break;
                        case 1:
                            mIv_player3_second_number.setImageResource(exact_num);
                            mIv_player3_second_color.setImageResource(exact_color);
                            break;
                        case 2:
                            mIv_player3_third_number.setImageResource(exact_num);
                            mIv_player3_third_color.setImageResource(exact_color);
                            break;
                        case 3:
                            mIv_player3_fourth_number.setImageResource(exact_num);
                            mIv_player3_fourth_color.setImageResource(exact_color);
                            break;
                        case 4:
                            mIv_player3_fifth_number.setImageResource(exact_num);
                            mIv_player3_fifth_color.setImageResource(exact_color);
                            break;
                    }
                }
                mIv_player3_fight.setImageResource(exact_niu);

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                mIv_player2_poker1.setVisibility(View.INVISIBLE);
                mIv_player2_poker2.setVisibility(View.INVISIBLE);
                mIv_player2_poker3.setVisibility(View.INVISIBLE);
                mIv_player2_poker4.setVisibility(View.INVISIBLE);
                mIv_player2_poker5.setVisibility(View.INVISIBLE);

                mRL_group2.setVisibility(View.VISIBLE);
                mRL_group3.startAnimation(mAnim_open3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mAnim_open3 = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnim_open3.setDuration(300);
        mAnim_open3.setAnimationListener(new Animation.AnimationListener() {


            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIv_player3_poker1.setVisibility(View.INVISIBLE);
                mIv_player3_poker2.setVisibility(View.INVISIBLE);
                mIv_player3_poker3.setVisibility(View.INVISIBLE);
                mIv_player3_poker4.setVisibility(View.INVISIBLE);
                mIv_player3_poker5.setVisibility(View.INVISIBLE);

                mRL_group3.setVisibility(View.VISIBLE);

                mActivity.startClick();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void gameReset() {
        mRL_group1.setVisibility(View.INVISIBLE);
        mRL_group2.setVisibility(View.INVISIBLE);
        mRL_group3.setVisibility(View.INVISIBLE);

        player_1 = 1;
        player_2 = 1;
        player_3 = 1;

        mTv_bet_0.setText("0");
        mTv_bet_1.setText("0");
        mTv_bet_2.setText("0");

        sendPoker();
    }

    private static Bitmap readBitmap(Context context, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;

        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, options);
    }

    //
    private List<Integer> getRandomNum() {
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int j = random.nextInt(13) + 1;
            list.add(j);
        }
        return list;
    }

    private List<Integer> getRandomColor() {
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int j = random.nextInt(4) + 1;
            list.add(j);
        }
        return list;
    }

    private int getRandomNiu() {
        Random random = new Random();
        return random.nextInt(11);
    }


    private int whichColor(int color) {
        //黑红方草
        switch (color) {
            case 1:
                return getResources().getIdentifier(POKER_SPADE, "mipmap", mContext.getPackageName());
            case 2:
                return getResources().getIdentifier(POKER_HEART, "mipmap", mContext.getPackageName());
            case 3:
                return getResources().getIdentifier(POKER_DIAMOND, "mipmap", mContext.getPackageName());
            case 4:
                return getResources().getIdentifier(POKER_CLUB, "mipmap", mContext.getPackageName());
            default:
                return -1;
        }
    }

    //黑 true  红false
    private int whichCard(int num, int color) {
        switch (num) {
            case 1:
                if (blackOrRed(color)) {
                    return getResources().getIdentifier(POKER_A_BLACK, "mipmap", mContext.getPackageName());
                } else {
                    return getResources().getIdentifier(POKER_A_RED, "mipmap", mContext.getPackageName());
                }
            case 2:
                if (blackOrRed(color)) {
                    return getResources().getIdentifier(POKER_2_BLACK, "mipmap", mContext.getPackageName());
                } else {
                    return getResources().getIdentifier(POKER_2_RED, "mipmap", mContext.getPackageName());
                }
            case 3:
                if (blackOrRed(color)) {
                    return getResources().getIdentifier(POKER_3_BLACK, "mipmap", mContext.getPackageName());
                } else {
                    return getResources().getIdentifier(POKER_3_RED, "mipmap", mContext.getPackageName());
                }
            case 4:
                if (blackOrRed(color)) {
                    return getResources().getIdentifier(POKER_4_BLACK, "mipmap", mContext.getPackageName());
                } else {
                    return getResources().getIdentifier(POKER_4_RED, "mipmap", mContext.getPackageName());
                }
            case 5:
                if (blackOrRed(color)) {
                    return getResources().getIdentifier(POKER_5_BLACK, "mipmap", mContext.getPackageName());
                } else {
                    return getResources().getIdentifier(POKER_5_RED, "mipmap", mContext.getPackageName());
                }
            case 6:
                if (blackOrRed(color)) {
                    return getResources().getIdentifier(POKER_6_BLACK, "mipmap", mContext.getPackageName());
                } else {
                    return getResources().getIdentifier(POKER_6_RED, "mipmap", mContext.getPackageName());
                }
            case 7:
                if (blackOrRed(color)) {
                    return getResources().getIdentifier(POKER_7_BLACK, "mipmap", mContext.getPackageName());
                } else {
                    return getResources().getIdentifier(POKER_7_RED, "mipmap", mContext.getPackageName());
                }
            case 8:
                if (blackOrRed(color)) {
                    return getResources().getIdentifier(POKER_8_BLACK, "mipmap", mContext.getPackageName());
                } else {
                    return getResources().getIdentifier(POKER_8_RED, "mipmap", mContext.getPackageName());
                }
            case 9:
                if (blackOrRed(color)) {
                    return getResources().getIdentifier(POKER_9_BLACK, "mipmap", mContext.getPackageName());
                } else {
                    return getResources().getIdentifier(POKER_9_RED, "mipmap", mContext.getPackageName());
                }
            case 10:
                if (blackOrRed(color)) {
                    return getResources().getIdentifier(POKER_10_BLACK, "mipmap", mContext.getPackageName());
                } else {
                    return getResources().getIdentifier(POKER_10_RED, "mipmap", mContext.getPackageName());
                }
            case 11:
                if (blackOrRed(color)) {
                    return getResources().getIdentifier(POKER_J_BLACK, "mipmap", mContext.getPackageName());
                } else {
                    return getResources().getIdentifier(POKER_J_RED, "mipmap", mContext.getPackageName());
                }
            case 12:
                if (blackOrRed(color)) {
                    return getResources().getIdentifier(POKER_Q_BLACK, "mipmap", mContext.getPackageName());
                } else {
                    return getResources().getIdentifier(POKER_Q_RED, "mipmap", mContext.getPackageName());
                }
            case 13:
                if (blackOrRed(color)) {
                    return getResources().getIdentifier(POKER_K_BLACK, "mipmap", mContext.getPackageName());
                } else {
                    return getResources().getIdentifier(POKER_K_RED, "mipmap", mContext.getPackageName());
                }
            default:
                return -1;
        }
    }

    private boolean blackOrRed(int color) {
        //true 黑  false 红
        if (color == 1 || color == 4) {
            return true;
        } else {
            return false;
        }
    }

    private int getNiu(int niu) {
        switch (niu) {
            case 0:
                return getResources().getIdentifier(NIU_NO, "mipmap", mContext.getPackageName());
            case 1:
                return getResources().getIdentifier(NIU_ONE, "mipmap", mContext.getPackageName());
            case 2:
                return getResources().getIdentifier(NIU_TWO, "mipmap", mContext.getPackageName());
            case 3:
                return getResources().getIdentifier(NIU_THREE, "mipmap", mContext.getPackageName());
            case 4:
                return getResources().getIdentifier(NIU_FOUR, "mipmap", mContext.getPackageName());
            case 5:
                return getResources().getIdentifier(NIU_FIVE, "mipmap", mContext.getPackageName());
            case 6:
                return getResources().getIdentifier(NIU_SIX, "mipmap", mContext.getPackageName());
            case 7:
                return getResources().getIdentifier(NIU_SEVEN, "mipmap", mContext.getPackageName());
            case 8:
                return getResources().getIdentifier(NIU_EIGHT, "mipmap", mContext.getPackageName());
            case 9:
                return getResources().getIdentifier(NIU_NINE, "mipmap", mContext.getPackageName());
            case 10:
                return getResources().getIdentifier(NIU_TEN, "mipmap", mContext.getPackageName());
            default:
                return -1;
        }
    }

    private int getColor(String color) {
        int realColor;
        if ("A".equals(color)) {
            realColor = 1;
        } else if ("B".equals(color)) {
            realColor = 2;
        } else if ("C".equals(color)) {
            realColor = 3;
        } else {
            realColor = 4;
        }
        return realColor;
    }

    public void connectToServer() {
        if (mSocket != null) {
            System.out.println("socket不是空");
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("socket是空的");
                    try {
                        if (mSocket == null) {
                            mSocket = new Socket("139.199.184.20", 9501);
                            System.out.println("创建socket连接");
                            ReadThread readThread = new ReadThread();
                            readThread.start();
                            bindServer();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void createRoom() {
        SendCreateRoomBean createRoomBean = new SendCreateRoomBean();
        createRoomBean.cmd = "create";
        createRoomBean.roomId = id_host;
        createRoomBean.uid = id_host;
        Gson gson = new Gson();
        final String json = gson.toJson(createRoomBean);

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mSocket != null) {
                    try {
                        if (mSocket != null) {
                            mOutputStream = mSocket.getOutputStream();
                        }
                        String s = json + "\\n";
                        System.out.println("json拼接" + s);
                        mOutputStream.write((s).getBytes("utf-8"));
                        // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞
                        mOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        firstCrete = false;
    }

    public void startGame() {
        SendStartGameBean startGameBean = new SendStartGameBean();
        startGameBean.cmd = "new";
        startGameBean.roomId = id_host;
        startGameBean.game = "niu";
        startGameBean.uid = id_host;
        Gson gson = new Gson();
        final String json = gson.toJson(startGameBean);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mSocket != null) {
                        mOutputStream = mSocket.getOutputStream();
                    }
                    String s = json + "\\n";
                    System.out.println("json拼接" + s);
                    mOutputStream.write((s).getBytes("utf-8"));
                    // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞
                    mOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void bindServer() {
        SendBindBean sendBindBean = new SendBindBean();
        if (isHost) {
            sendBindBean.uid = id_host;
        } else {
            sendBindBean.uid = id_guest;
        }
        sendBindBean.cmd = "bind";
        Gson gson = new Gson();
        final String json = gson.toJson(sendBindBean);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mSocket != null) {
                        mOutputStream = mSocket.getOutputStream();
                    }
                    String s = json + "\\n";
                    System.out.println("json拼接" + s);
                    mOutputStream.write((s).getBytes("utf-8"));
                    // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞
                    mOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
                    int length = -1;
                    int total = 0;
                    StringBuffer reply = new StringBuffer();
                    boolean countdownEnd = false;
                    boolean createSuccess = false;
                    boolean startSuccess = false;

                    Gson gson = null;

                    while ((length = isr.read(charsbuffer)) != -1) {

                        reply.append(charsbuffer, total, length);
//                        total += length ;
//                        System.out.println(reply.toString());
                        String json = reply.toString();
                        if (gson == null) {
                            gson = new Gson();
                        }
                        System.out.println("json数据" + json);
                        try {
                            DataTypeBean typeBean = gson.fromJson(json, DataTypeBean.class);
                            String type = typeBean.getType();
                            System.out.println("返回类型---" + type);
                            String body = typeBean.getBody();
                            System.out.println("返回内容" + body);
                            switch (type) {
                                case TYPE_CREATE:
                                    CreateRoomBean createRoomBean = gson.fromJson(body, CreateRoomBean.class);
                                    if (createRoomBean.getStatus() == 0) {
                                        System.out.println("创建房间成功");
                                    } else {
                                    }
                                    break;
                                case TYPE_BIND:
                                    BindBean bindBean = gson.fromJson(body, BindBean.class);
                                    if (bindBean.getStatus() == 0) {
                                        System.out.println("绑定成功");
                                        mHandler.sendEmptyMessage(HANDLER_CREATE_ROOM);
                                    } else {
                                        System.out.println("绑定失败");
                                    }
                                    break;
                                case TYPE_GAME_NEW:
                                    StartGameBean startGameBean = gson.fromJson(body, StartGameBean.class);
                                    if (startGameBean.getStatus() == 0) {
                                        System.out.println("主播开始游戏了");
                                        if (!isHost) {
                                            mActivity.showFragment();
                                            mHandler.sendEmptyMessage(HANDLER_GUEST_START);
                                        }
                                    }
                                    break;
                                case TYPE_NEW:
                                    System.out.println("开始游戏成功");
                                    break;
                                case TYPE_ENTER:
                                    EnterRoomBean enterRoomBean = gson.fromJson(body, EnterRoomBean.class);
                                    if (enterRoomBean.getStatus() == 0) {
                                        System.out.println("加入房间成功");
                                    }
                                    break;
                                case TYPE_LEAVE:


                                    break;
                                case TYPE_BET:
                                    BetBean betBean = gson.fromJson(body, BetBean.class);
                                    if (betBean.getStatus() == 0) {
                                        Toast.makeText(mContext, "下注成功", Toast.LENGTH_SHORT).show();
                                        System.out.println("下注成功");
                                    } else {
                                        Toast.makeText(mContext, "下注失败", Toast.LENGTH_SHORT).show();
                                        System.out.println("下注失败");
                                    }
                                    break;
                                case TYPE_CLOSE:

                                    break;
                                case TYPE_BONUS:
                                    System.out.println("bonus的样子" + body);
                                    break;
                                case TYPE_GAME_START:
                                    CountdownBean countdownBean = gson.fromJson(body, CountdownBean.class);
                                    System.out.println("倒计时" + countdownBean.getData().getTimecount());
                                    clock_countdown = countdownBean.getData().getTimecount();
                                    mHandler.sendEmptyMessage(HANDLER_TIME_CLOCK);
                                    if (countdownBean.getData().getTimecount() == 1) {
                                        countdownEnd = true;
                                    }
                                    break;
                                case TYPE_GAME_BET:
                                    GameBetBean gameBetBean = gson.fromJson(body, GameBetBean.class);
                                    list_bet = gameBetBean.getData().getBet();
                                    mHandler.sendEmptyMessage(HANDLER_DISPLAY_BET);
                                    break;
                                case TYPE_GAME_RESULT:
                                    GameResultBean gameResultBean = gson.fromJson(body, GameResultBean.class);
                                    System.out.println("谁赢了" + gameResultBean.getData().getResult().getWin());

                                    list_player1_color.clear();
                                    list_player1_number.clear();
                                    list_player2_color.clear();
                                    list_player2_number.clear();
                                    list_player3_number.clear();
                                    list_player3_color.clear();

                                    List<String> card1 = gameResultBean.getData().getResult().getDetail().get(0).getCards();
                                    mPlayer1_niu = gameResultBean.getData().getResult().getDetail().get(0).getResult();

                                    for (int i = 0; i < card1.size(); i++) {
                                        String card1_color = card1.get(i).substring(0, 1);
                                        int color = getColor(card1_color);
                                        list_player1_color.add(color);
                                        int number = Integer.parseInt(card1.get(i).substring(1, card1.get(i).length()));
                                        list_player1_number.add(number);
                                        System.out.println("第一个人" + "牌" + number + "花色" + color);
                                    }
                                    List<String> card2 = gameResultBean.getData().getResult().getDetail().get(1).getCards();
                                    mPlayer2_niu = gameResultBean.getData().getResult().getDetail().get(1).getResult();
                                    for (int i = 0; i < card2.size(); i++) {
                                        String card2_color = card2.get(i).substring(0, 1);
                                        int color = getColor(card2_color);
                                        list_player2_color.add(color);
                                        int number = Integer.parseInt(card2.get(i).substring(1, card2.get(i).length()));
                                        list_player2_number.add(number);
                                        System.out.println("第二个人" + "牌" + number + "花色" + color);
                                    }
                                    List<String> card3 = gameResultBean.getData().getResult().getDetail().get(2).getCards();
                                    mPlayer3_niu = gameResultBean.getData().getResult().getDetail().get(2).getResult();
                                    for (int i = 0; i < card1.size(); i++) {
                                        String card3_color = card3.get(i).substring(0, 1);
                                        int color = getColor(card3_color);
                                        list_player3_color.add(color);
                                        int number = Integer.parseInt(card3.get(i).substring(1, card3.get(i).length()));
                                        list_player3_number.add(number);
                                        System.out.println("第三个人" + "牌" + number + "花色" + color);
                                    }
                                    countdownEnd = false;
                                    startSuccess = false;

                                    mHandler.sendEmptyMessage(HANDLER_DISPLAY_POKER);

                                    break;
                            }
                        } catch (Exception e) {
                            System.out.println("gson崩了" + e.getMessage());
                        }
                        reply.delete(0, reply.length());


//                        if (!createSuccess) {
//                            CreateRoomBean createRoomBean = gson.fromJson(json, CreateRoomBean.class);
//                            if (createRoomBean.getStatus() == 0) {
//                                System.out.println("创建房间成功");
////                                Toast.makeText(mContext, "创建房间成功", Toast.LENGTH_SHORT).show();
//                                createSuccess = true;
//                            } else if (createRoomBean.getStatus() == 1) {
//                                System.out.println("创建status=1" + createRoomBean.getData().getRoomId());
//                            }
//                        } else if (!startSuccess) {
//                            StartGameBean startGameBean = gson.fromJson(json, StartGameBean.class);
//                            if (startGameBean.getStatus() == 0) {
//                                System.out.println("开始游戏成功");
////                                Toast.makeText(mContext, "开始游戏", Toast.LENGTH_SHORT).show();
//                                startSuccess = true;
//                            }
//                        } else {
//                            if (countdownEnd) {
//                                GameResultBean gameResultBean = gson.fromJson(json, GameResultBean.class);
//                                System.out.println("谁赢了" + gameResultBean.getData().getResult().getWin());
//
//                                list_player1_color.clear();
//                                list_player1_number.clear();
//                                list_player2_color.clear();
//                                list_player2_number.clear();
//                                list_player3_number.clear();
//                                list_player3_color.clear();
//
//                                List<String> card1 = gameResultBean.getData().getResult().getDetail().get(0).getCards();
//                                mPlayer1_niu = gameResultBean.getData().getResult().getDetail().get(0).getResult();
//
//                                for (int i = 0; i < card1.size(); i++) {
//                                    String card1_color = card1.get(i).substring(0, 1);
//                                    int color = getColor(card1_color);
//                                    list_player1_color.add(color);
//                                    int number= Integer.parseInt(card1.get(i).substring(1, card1.get(i).length()));
//                                    list_player1_number.add(number);
//                                    System.out.println("第一个人" + "牌" + number + "花色" + color);
//                                }
//                                List<String> card2 = gameResultBean.getData().getResult().getDetail().get(1).getCards();
//                                mPlayer2_niu = gameResultBean.getData().getResult().getDetail().get(1).getResult();
//                                for (int i = 0; i < card2.size(); i++) {
//                                    String card2_color = card2.get(i).substring(0, 1);
//                                    int color = getColor(card2_color);
//                                    list_player2_color.add(color);
//                                    int number= Integer.parseInt(card2.get(i).substring(1, card2.get(i).length()));
//                                    list_player2_number.add(number);
//                                    System.out.println("第二个人" + "牌" + number + "花色" + color);
//                                }
//                                List<String> card3 = gameResultBean.getData().getResult().getDetail().get(2).getCards();
//                                mPlayer3_niu = gameResultBean.getData().getResult().getDetail().get(2).getResult();
//                                for (int i = 0; i < card1.size(); i++) {
//                                    String card3_color = card3.get(i).substring(0, 1);
//                                    int color = getColor(card3_color);
//                                    list_player3_color.add(color);
//                                    int number= Integer.parseInt(card3.get(i).substring(1, card3.get(i).length()));
//                                    list_player3_number.add(number);
//                                    System.out.println("第三个人" + "牌" + number + "花色" + color);
//                                }
//                                countdownEnd = false;
//                                startSuccess = false;
//
//                                mHandler.sendEmptyMessage(HANDLER_DISPLAY_POKER);
//
//                            } else if (!countdownEnd) {
//                                CountdownBean countdownBean = gson.fromJson(json, CountdownBean.class);
//                                System.out.println("倒计时" + countdownBean.getData().getTimecount());
//                                clock_countdown = countdownBean.getData().getTimecount();
//                                mHandler.sendEmptyMessage(HANDLER_TIME_CLOCK);
//                                if (countdownBean.getData().getTimecount() == 1) {
//                                    countdownEnd = true;
//                                }
//                            }
//                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("err" + e.toString());
                }
            }
        }
    }
}
