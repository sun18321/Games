package com.sun.games;

import android.animation.ObjectAnimator;
import android.content.Context;
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

        mPlay1 = (RelativeLayout) mView.findViewById(R.id.player_1);
        mIv_player1_poker1 = (ImageView) mPlay1.findViewById(R.id.group_1);
        mIv_player1_poker2 = (ImageView) mPlay1.findViewById(R.id.group_2);
        mIv_player1_poker3 = (ImageView) mPlay1.findViewById(R.id.group_3);
        mIv_player1_poker4 = (ImageView) mPlay1.findViewById(R.id.group_4);
        mIv_player1_poker5 = (ImageView) mPlay1.findViewById(R.id.group_5);
        mRL_group1 = (RelativeLayout) mPlay1.findViewById(R.id.poker_group);


        mPlay2 = (RelativeLayout) mView.findViewById(R.id.player_2);
        mIv_player2_poker1 = (ImageView) mPlay2.findViewById(R.id.group_1);
        mIv_player2_poker2 = (ImageView) mPlay2.findViewById(R.id.group_2);
        mIv_player2_poker3 = (ImageView) mPlay2.findViewById(R.id.group_3);
        mIv_player2_poker4 = (ImageView) mPlay2.findViewById(R.id.group_4);
        mIv_player2_poker5 = (ImageView) mPlay2.findViewById(R.id.group_5);
        mRL_group2 = (RelativeLayout) mPlay2.findViewById(R.id.poker_group);

        mPlay3 = (RelativeLayout) mView.findViewById(R.id.player_3);
        mIv_player3_poker1 = (ImageView) mPlay3.findViewById(R.id.group_1);
        mIv_player3_poker2 = (ImageView) mPlay3.findViewById(R.id.group_2);
        mIv_player3_poker3 = (ImageView) mPlay3.findViewById(R.id.group_3);
        mIv_player3_poker4 = (ImageView) mPlay3.findViewById(R.id.group_4);
        mIv_player3_poker5 = (ImageView) mPlay3.findViewById(R.id.group_5);
        mRL_group3 = (RelativeLayout) mPlay3.findViewById(R.id.poker_group);


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
        TranslateAnimation animation_1 =  new TranslateAnimation(0, (float) (-0.3 * mWidth), 0, (float) (-0.3 * mWidth));
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


        mScaleAnimation_appear = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        mScaleAnimation_appear.setDuration(400);
        mScaleAnimation_appear.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                mRL_group1.startAnimation(mAnim_open1);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        mScaleAnimation_clear = new ScaleAnimation(1.0f, 0.0f, 1.0f,  0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
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

}
