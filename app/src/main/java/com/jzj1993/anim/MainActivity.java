package com.jzj1993.anim;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = new View(this);
        setContentView(v);
        final int w = getWindowManager().getDefaultDisplay().getWidth();
        final int h = getWindowManager().getDefaultDisplay().getHeight();
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnim(MainActivity.this, 0, w, 0, h);
            }
        });
    }

    public static boolean startAnim(Activity activity, int fromX, int toX, int fromY, int toY) {
        try {
            final ImageView img = new ImageView(activity);
            img.setImageResource(R.mipmap.ic_launcher);

            final FrameLayout tempLayout = new FrameLayout(activity);
            final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(fromX, fromY, 0, 0);
            tempLayout.addView(img, lp);

            final ViewGroup container = (ViewGroup) activity.getWindow().getDecorView();
            container.addView(tempLayout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            final Animation anim = new TranslateAnimation(0, toX - fromX, 0, toY - fromY);
            anim.setDuration(500);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    container.removeView(tempLayout);
//                    container.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            container.removeView(tempLayout);
//                        }
//                    });
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            img.startAnimation(anim);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
