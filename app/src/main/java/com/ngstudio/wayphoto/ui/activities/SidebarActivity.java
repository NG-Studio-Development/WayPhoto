package com.ngstudio.wayphoto.ui.activities;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.ngstudio.wayphoto.R;

public abstract class SidebarActivity extends BaseActivity implements Animation.AnimationListener {

    private View content;
    private View menu;

    private boolean menuOut;
    private AnimParams animParams = new AnimParams();

    private class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Animation animation;
            int w = content.getMeasuredWidth();
            int h = content.getMeasuredHeight();
            int left = (int) (content.getMeasuredWidth() * 0.8);

            if (!menuOut) {
                animation = new TranslateAnimation(0, left, 0, 0);
                menu.setVisibility(View.VISIBLE);
                animParams.init(left, 0, left + w, h);

            } else {
                animation = new TranslateAnimation(0, -left, 0, 0);
                animParams.init(0, 0, w, h);
            }

            animation.setDuration(500);
            animation.setAnimationListener(SidebarActivity.this);
            animation.setFillAfter(true);
            content.startAnimation(animation);
        }
    }

    @Override
    protected abstract int getFragmentContainerId();

    private void layoutApp() {
        content.layout(animParams.left, animParams.top, animParams.right, animParams.bottom);
        content.clearAnimation();
    }


    @Override
    public void onAnimationEnd(Animation animation) {

        menuOut = !menuOut;
        if (!menuOut) {
            menu.setVisibility(View.INVISIBLE);
        }
        layoutApp();

        if (animationStateListener != null)
            animationStateListener.onAnimationEnd(animation);
    }

    public AnimationStateListener animationStateListener;

    public void setAnimationStateListener(AnimationStateListener animationStateListener) {
        this.animationStateListener = animationStateListener;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        if (animationStateListener != null)
            animationStateListener.onAnimationRepeat(animation);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        if (animationStateListener != null)
            animationStateListener.onAnimationStart(animation);
    }

     protected void setStateAnimation (boolean menuOut) {
        this.menuOut = menuOut;
     }

    protected void initializeMenu(int resource) { this.menu = findViewById(resource); }

    protected void initializeContent(int resource) {
        this.content = findViewById(resource);
        //this.content.findViewById(R.id.buttonOpenMenu).setOnClickListener(new ClickListener());
    }

    public interface AnimationStateListener {
        void onAnimationRepeat(Animation animation);
        void onAnimationStart(Animation animation);
        void onAnimationEnd(Animation animation);
    }

    private static class AnimParams {
        int left, right, top, bottom;

        void init(int left, int top, int right, int bottom) {
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }
    }
}
