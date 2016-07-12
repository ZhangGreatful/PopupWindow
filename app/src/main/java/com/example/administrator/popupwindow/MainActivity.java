package com.example.administrator.popupwindow;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button pop = (Button) findViewById(R.id.popButton);
        pop.setOnClickListener(popClick);
    }

    View.OnClickListener popClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getPopupWindow();
            popupWindow.showAtLocation(v, Gravity.VERTICAL_GRAVITY_MASK, 50, 0);
        }
    };

    private void initPopupWindow() {
//        获取自定义布局文件
        final View popupWindow_view = getLayoutInflater().inflate(R.layout.left_pop, null);
//        创建PopupWindow实例,900,LayoutParams.MATCH_PARENT分别是宽和高
        popupWindow = new PopupWindow(popupWindow_view, 900, ViewGroup.LayoutParams.MATCH_PARENT, true);
//        设置动画效果
        popupWindow.setAnimationStyle(R.style.AnimationFade);
//        点击其他地方消失
        popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow_view != null && popupWindow_view.isShown()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        Button button1 = (Button) popupWindow_view.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "当前点击的是我", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /****
     * 获取PopupWindow实例
     ****/
    private void getPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopupWindow();
        }
    }
}
