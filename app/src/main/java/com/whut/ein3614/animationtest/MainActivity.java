package com.whut.ein3614.animationtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private Button btnScale,btnRotate,btnTranslate,btnAlpha,btnTransRotate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        btnScale.setOnClickListener(this);
        btnRotate.setOnClickListener(this);
        btnTranslate.setOnClickListener(this);
        btnAlpha.setOnClickListener(this);
        btnTransRotate.setOnClickListener(this);
    }
    private void initViews(){
        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(getResources().getIdentifier("timg","drawable",getPackageName()));
        btnScale = findViewById(R.id.btn_scale);
        btnRotate = findViewById(R.id.btn_rotate);
        btnTranslate = findViewById(R.id.btn_translate);
        btnAlpha = findViewById(R.id.btn_alpha);
        btnTransRotate = findViewById(R.id.btn_trans_rotate);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_scale:
                bitmapScale();
                break;
            case R.id.btn_rotate:
                bitmapRotate();
                break;
            case R.id.btn_translate:
                bitmapTranslate();
                break;
            case R.id.btn_alpha:
                bitmapAlpha();
                break;
            case R.id.btn_trans_rotate:
                bitmapTranslateAndRotate();
                break;
            default:
                break;
        }
    }
    /**
     * 图片缩放
     * */
    private void bitmapScale(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.scale_anim);
        animation.setDuration(3000);
        imageView.startAnimation(animation);
    }
    /**
     * 图片旋转
     * */
    private void bitmapRotate(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.rotate_anim);
        animation.setDuration(3000);
        imageView.startAnimation(animation);
    }
    /**
     * 图片平移
     * */
    private void bitmapTranslate(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.translate_anim);
        animation.setDuration(3000);
        imageView.startAnimation(animation);
    }
    /**
     * 图片淡出
     * */
    private void bitmapAlpha(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
        alphaAnimation.setDuration(3000);
        imageView.startAnimation(alphaAnimation);
    }
    /**
     * 一边平移一边旋转
     * */
    private void bitmapTranslateAndRotate(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.translate_rotate_anim);
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
        imageView.startAnimation(animation);
    }
}
