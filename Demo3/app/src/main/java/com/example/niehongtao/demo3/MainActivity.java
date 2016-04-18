package com.example.niehongtao.demo3;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * 进度条跟随滑动功能
 */
public class MainActivity extends Activity implements OnClickListener {
    /**
     * 标签 *
     */
    private Button buttonTab01, buttonTab02, buttonTab03;
    /**
     * 进度条 *
     */
    private ImageView imageView;
    /**
     * 进度条宽度 *
     */
    private Integer imageViewW = 0;
    /**
     * 当官视图宽度 *
     */
    private Integer viewPagerW = 0;
    /**
     * 适配器 *
     */
    private ViewpagerAdapter viewpagerAdapter;
    /**
     * 多页面控件 *
     */
    private ViewPager viewPager;
    /**
     * 布局属性 *
     */
    private RelativeLayout.LayoutParams layoutParams;
    /**
     * 进度条移动值 *
     */
    private Integer moveI;
    private boolean isFirst = true;

    OnPageChangeListener listener = new OnPageChangeListener() { // 设置变化监听

        /**
         * @param position             当前页面位置
         * @param positionOffset       当前页面偏移比例
         * @param positionOffsetPixels 当前页面偏移的像素位置
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            tabMove(position, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
        }

        /**
         * @param scrollState 滑动状态：0-无操作状态，1-滑动状态，2-滑动完成状态。
         */
        @Override
        public void onPageScrollStateChanged(int scrollState) {
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager_show);
        imageView = (ImageView) findViewById(R.id.imageView_tiao);
        buttonTab01 = (Button) findViewById(R.id.button_01);
        buttonTab02 = (Button) findViewById(R.id.button_02);
        buttonTab03 = (Button) findViewById(R.id.button_03);

        // 布局属性：用来设置进度条宽度和实现移动
        layoutParams = (LayoutParams) imageView.getLayoutParams();

        // 按钮点击监听（标签）
        buttonTab01.setOnClickListener(this);
        buttonTab02.setOnClickListener(this);
        buttonTab03.setOnClickListener(this);

        //初始化viewPager控件
        viewpagerAdapter = new ViewpagerAdapter(this);
        viewPager.setAdapter(viewpagerAdapter);// 设置适配器
        viewPager.setOnPageChangeListener(listener);

        // 设置初始化状态
        viewPager.setCurrentItem(0);
    }


    /**
     * 视图加载完成之后执行：值得注意的是PopupWindow每次显示和销毁都会执行一次
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // 只执行一次
        if (isFirst) {
            // 取标签宽度付给进度条，获得单个界面的宽度
            isFirst = false;
            int wh = buttonTab01.getWidth();
            viewPagerW = viewPager.getWidth();
            imageViewW = wh;
            layoutParams.width = wh;
            imageView.setLayoutParams(layoutParams);
        }
    }

    /**
     * 进度条移动 :核心的移动算法
     * @param position             当前页位置
     * @param positionOffsetPixels 移动像素值
     */
    private void tabMove(int position, int positionOffsetPixels) {
        moveI = (int) ((int) (imageViewW * position) + (((double) positionOffsetPixels / viewPagerW) * imageViewW));
        layoutParams.leftMargin = moveI;
        imageView.setLayoutParams(layoutParams);
    }


    /**
     * 点击监听
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_01:
                // 切换页面
                viewPager.setCurrentItem(0);
                break;
            case R.id.button_02:
                // 切换页面
                viewPager.setCurrentItem(1);
                break;
            case R.id.button_03:
                // 切换页面
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
