package com.whut.ein3614.animationtest;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private Button btnColorAnim,btnTrans,btnCompAnim,btnCompAnimXml,btnChangeWidth;
    private ValueAnimator colorAnim,transAnim,changeWidthAnim,valueAnimator;
    private AnimatorSet set;
    private static final String TAG = "SecondActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
        btnColorAnim.setOnClickListener(this);
        btnTrans.setOnClickListener(this);
        btnCompAnim.setOnClickListener(this);
        btnCompAnimXml.setOnClickListener(this);
        btnChangeWidth.setOnClickListener(this);
    }
    private void initViews(){
        imageView = findViewById(R.id.imageView);
        btnColorAnim = findViewById(R.id.btn_color_anim);
        btnTrans = findViewById(R.id.btn_translate);
        btnCompAnim = findViewById(R.id.btn_comp_anim);
        btnCompAnimXml = findViewById(R.id.btn_comp_anim_xml);
        btnChangeWidth = findViewById(R.id.btn_change_width);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_color_anim:
                backgroundColorAnim(imageView);
                break;
            case R.id.btn_translate:
                translateAnim(btnTrans);
                break;
            case R.id.btn_comp_anim:
                compoundAnim(btnCompAnim);
                break;
            case R.id.btn_comp_anim_xml:
                compoundAnimByXml(btnCompAnimXml);
                break;
            case R.id.btn_change_width:
//                changeWidthAnim(btnChangeWidth);
                changeWidthAnimByListener(btnChangeWidth,btnChangeWidth.getWidth(),800);
                break;
            default:
                break;
        }
    }
    /**
     * 背景色变换动画
     * */
    private void backgroundColorAnim(Object target){
        colorAnim = ObjectAnimator.ofInt((View)target,"backgroundColor",0xFFFF8080,0xFF8080FF);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        //无限循环
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        //反转变换
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
    }
    /**
     * 向左平移然后归位
     * */
    private void translateAnim(Object target){
        View view = (View) target;
        float curTranslationX = view.getTranslationX();
        transAnim = ObjectAnimator.ofFloat(view,"translationX",curTranslationX,-20,curTranslationX);
        transAnim.setDuration(3000);
        transAnim.start();
    }
    /**
     * 复合动画
     * */
    private void compoundAnim(Object target){
        View view = (View) target;
        set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view,"rotationX",0,360),
                ObjectAnimator.ofFloat(view,"rotationY",0,180),
                ObjectAnimator.ofFloat(view,"rotation",0,-90),
                ObjectAnimator.ofFloat(view,"alpha",1,0.25f,1),
                ObjectAnimator.ofFloat(view,"scaleX",1,0.5f),
                ObjectAnimator.ofFloat(view,"scaleY",1,0.5f)
        );
//        set.play(ObjectAnimator.ofFloat(view,"rotationX",0,360))
//                .with(ObjectAnimator.ofFloat(view,"rotationY",0,180))
//                .with(ObjectAnimator.ofFloat(view,"rotation",0,-90))
//                .before(ObjectAnimator.ofFloat(view,"alpha",1,0.25f,1))
//                .before(ObjectAnimator.ofFloat(view,"scaleX",1,0.5f))
//                .before(ObjectAnimator.ofFloat(view,"scaleY",1,0.5f));
        set.setDuration(5000).start();
    }
    /**
     * 通过xml文件实现相同的复合动画
     * */
    private void compoundAnimByXml(Object target){
        set = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.compound_anim);
        set.setTarget(target);
        set.start();
    }
    /**
     * 变更按钮宽度动画
     * */
    private void changeWidthAnim(Object target){
        ViewWrapper wrapper = new ViewWrapper((View)target);
        changeWidthAnim = ObjectAnimator.ofInt(wrapper,"width",800);
        changeWidthAnim.setDuration(3000);
        changeWidthAnim.start();
    }
    /**
     * 变更按钮宽度动画（通过监听器来实现）
     * */
    private void changeWidthAnimByListener(final View target, final int start, final int end){
        valueAnimator = ValueAnimator.ofInt(1,100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            //新建一个 IntEvaluator 对象，方便待会估值时使用
            private IntEvaluator mEvaluator = new IntEvaluator();
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                //获取当前动画进度 1-100 整型
//                int currentValue = (int) animation.getAnimatedValue();
                //获取当前进度占整个动画过程的比例 0-1 浮点型
                float fraction = animation.getAnimatedFraction();
                //通过整型估值器计算出当前宽度并设给View
                target.getLayoutParams().width = mEvaluator.evaluate(fraction,start,end);
                target.requestLayout();
            }
        });
        valueAnimator.setDuration(3000).start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(colorAnim != null)
            colorAnim.end();
        if(transAnim != null)
            transAnim.end();
        if(set != null)
            set.end();
        if(changeWidthAnim != null)
            changeWidthAnim.end();
        if(valueAnimator != null)
            valueAnimator.end();
    }
    private static class ViewWrapper {
        private View mTarget;
        public ViewWrapper (View target){
            mTarget = target;
        }
        public int getWidth(){
            return mTarget.getLayoutParams().width;
        }
        public void setWidth(int width){
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }
}
