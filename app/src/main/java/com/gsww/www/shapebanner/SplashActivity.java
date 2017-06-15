package com.gsww.www.shapebanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Author   : luweicheng on 2017/6/15 0015 15:38
 * E-mail   ：1769005961@qq.com
 * GitHub   : https://github.com/luweicheng24
 * funcation:
 */

public class SplashActivity extends AppCompatActivity {
   private ImageView img;
    private TextView title;
    private AlphaAnimation anim;
    private ScaleAnimation scaleAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        img = (ImageView) findViewById(R.id.img);
        title = (TextView) findViewById(R.id.tv_title);
       /* Glide.with(this)
                .load(R.drawable.pic24)
                .bitmapTransform(new BlurTransformation(this,5),new CenterCrop(this))
                .into(img);*/
        initAnim();
        img.setAnimation(anim);
        title.setAnimation(scaleAnimation);

    }

    @Override
    protected void onResume() {
        super.onResume();
        title.setVisibility(View.VISIBLE);
        anim.start();
        scaleAnimation.start();

    }

    private void initAnim() {
        anim = new AlphaAnimation(0, 1);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setDuration(6000);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

         scaleAnimation = new ScaleAnimation(0,1,0,1);
         scaleAnimation.setDuration(4000);
         scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
         scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
             @Override
             public void onAnimationStart(Animation animation) {

             }

             @Override
             public void onAnimationEnd(Animation animation) {

             }

             @Override
             public void onAnimationRepeat(Animation animation) {

             }
         });

    }
}
