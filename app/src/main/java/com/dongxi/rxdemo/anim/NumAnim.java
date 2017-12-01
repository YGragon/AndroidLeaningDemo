package com.dongxi.rxdemo.anim;

import android.widget.TextView;

import com.dongxi.rxdemo.utils.LogUtil;
import com.dongxi.rxdemo.utils.NumUtil;

import java.util.LinkedList;
import java.util.Random;

/**
 * 滚动的数字动画
 */

public class NumAnim {
    //每秒刷新多少次
    private static final int COUNTPERS = 100;

    public static void startAnim(TextView textV, float num) {
        startAnim(textV, num, 500);
    }

    // 设置滚动时间,小数后边的位数没处理好.....
    public static void startAnim(TextView textV, float num, long time) {
        if (num == 0) {
            textV.setText(NumUtil.NumberFormat(num,2));
            return;
        }
        if (num >= Float.MAX_VALUE){
            LogUtil.e(Float.MAX_VALUE+"");
            textV.setText(NumUtil.NumberFormat(Float.MAX_VALUE,2));
            return;
        }
        float  b   =  (float)(Math.round(num*100))/100;
        Float[] nums = splitnum(b, (int)((time/1000f)*COUNTPERS));

        Counter counter = new Counter(textV, nums, time);

        textV.removeCallbacks(counter);
        textV.post(counter);
    }

    // 数字递增算法
    // 优化：重写splitnum()方法吧，使这个方法产生的float，先产生大数字，慢慢减小，最后产生小数。这样执行起来就和支付宝非常类似了。
    private static Float[] splitnum(float num, int count) {
        Random random = new Random();
//        float numtemp = num;
        float numtemp = NumUtil.NumberFormatFloat(num, 2);
        LogUtil.e("next-numtemp:" + numtemp);
        float sum = 0;
        LinkedList<Float> nums = new LinkedList<Float>();
        nums.add(0f);
        while (true) {
            float nextFloat = NumUtil.NumberFormatFloat((random.nextFloat() * num * 2f)/(float)count, 2);
            LogUtil.e("next-nextFloat:" + nextFloat);
            if (nextFloat == 0.00){
                nextFloat = 0.01f ;
            }
            if (numtemp - nextFloat >= 0) {
                sum = NumUtil.NumberFormatFloat(sum + nextFloat, 2);
                nums.add(sum);
                numtemp -= nextFloat;
            } else {
                nums.add(num);
                return nums.toArray(new Float[0]);
            }
        }
    }

    static class Counter implements Runnable {

        private final TextView view;
        private Float[] nums;
        private long pertime;

        private int i = 0;

        Counter(TextView view,Float[] nums,long time) {
            this.view = view;
            this.nums = nums;
            this.pertime = time/nums.length;
        }

        @Override
        public void run() {
            if (i>nums.length-1) {
                view.removeCallbacks(Counter.this);
                return;
            }
            view.setText(NumUtil.NumberFormat(nums[i++],2));
            view.removeCallbacks(Counter.this);
            view.postDelayed(Counter.this, pertime);
        }
    }
}