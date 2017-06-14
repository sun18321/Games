package com.sun.games;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by stephensun on 2017/5/22.
 */
public class GameFragment extends Fragment {

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

    private void init() {
        mContext = this.getActivity();
        mIv_deal = (ImageView) mView.findViewById(R.id.poker_deal);
        mIv_trans = (ImageView) mView.findViewById(R.id.poker_translate);
        mBull_1 = (ImageView) mView.findViewById(R.id.bull_1);
        mBull_2 = (ImageView) mView.findViewById(R.id.bull_2);
        mBull_3 = (ImageView) mView.findViewById(R.id.bull_3);
        mRL_toast = (RelativeLayout) mView.findViewById(R.id.game_start_toast);

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

        mActivity = (MainActivity) GameFragment.this.getActivity();

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);

        mWidth = wm.getDefaultDisplay().getWidth();
        mHeight = wm.getDefaultDisplay().getHeight();

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

                mRL_toast.setVisibility(View.VISIBLE);
                mRL_toast.startAnimation(mScaleAnimation_appear);

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
                List<Integer> list_num = getRandomNum();
                List<Integer> list_color = getRandomColor();
                int randomNiu = getRandomNiu();
                int exact_niu = getNiu(randomNiu);
//                System.out.println("牌型集合大小" + list_num.size());
//                System.out.println("花色集合大小" + list_color.size());
                for (int i = 0; i < 5; i++) {
                    int exact_num = whichCard(list_num.get(i), list_color.get(i));
                    int exact_color = whichColor(list_color.get(i));
                    System.out.println("花色" + list_color.get(i));
                    System.out.println("牌型" + list_num.get(i));
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
            System.out.println("牌" + i + "---" + j);
            list.add(j);
        }
        return list;
    }

    private List<Integer> getRandomColor() {
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int j = random.nextInt(4) + 1;
            System.out.println("花" + i + "---" + j);
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
}
