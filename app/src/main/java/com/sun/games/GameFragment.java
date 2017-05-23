package com.sun.games;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by stephensun on 2017/5/22.
 */
public class GameFragment extends Fragment implements Animator.AnimatorListener {

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
    private RelativeLayout mPlay1;
    private ImageView mIv_player1_poker1;
    private ImageView mIv_player1_poker2;
    private ImageView mIv_player1_poker3;
    private ImageView mIv_player1_poker4;
    private ImageView mIv_player1_poker5;
    private int i = 1;
    private ObjectAnimator mTranslationX;


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

        mPlay1 = (RelativeLayout) mView.findViewById(R.id.player_1);
        mIv_player1_poker1 = (ImageView) mPlay1.findViewById(R.id.group_1);
        mIv_player1_poker2 = (ImageView) mPlay1.findViewById(R.id.group_2);
        mIv_player1_poker3 = (ImageView) mPlay1.findViewById(R.id.group_3);
        mIv_player1_poker4 = (ImageView) mPlay1.findViewById(R.id.group_4);
        mIv_player1_poker5 = (ImageView) mPlay1.findViewById(R.id.group_5);

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);

        mWidth = wm.getDefaultDisplay().getWidth();
        mHeight = wm.getDefaultDisplay().getHeight();

        System.out.println("宽" + mWidth);
        System.out.println("高" + mHeight);


    }

    public void sendPoker() {
        int i = 1;
        mTranslationX = ObjectAnimator.ofFloat(mIv_trans, "translationX", 0, (float) (-0.3 * mWidth));
        mTranslationX.setDuration(600);
        mTranslationX.setRepeatCount(4);
        mTranslationX.setRepeatMode(ValueAnimator.RESTART);
        mTranslationX.addListener(this);
        mTranslationX.start();

    }


    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        mIv_player1_poker5.setVisibility(View.VISIBLE);

    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

        switch (i) {
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
        i++;

    }
}
