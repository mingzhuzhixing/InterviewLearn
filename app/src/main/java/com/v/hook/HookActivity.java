package com.v.hook;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.v.interviewlearn.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);
        Button btnHook=findViewById(R.id.btn_hook);

        btnHook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("click","点击了按钮");
            }
        });

        hookOnClickListener(btnHook);
    }

    /**
     * hook点击事件
     * @param view
     */
    public void hookClick(View view) {

    }


    private void hookOnClickListener(View view){
        try {
            //得到 View 的 ListenerInfo 对象
            Method getListenerInfo=View.class.getDeclaredMethod("getListenerInfo");
            //作用就是让我们在用反射时访问私有变量
            getListenerInfo.setAccessible(true);

            Object listenerInfo = getListenerInfo.invoke(view);

            //得到 原始的 OnClickListener 对象
            Class listenerInfoClz=Class.forName("android.view.View$ListenerInfo");
            Field mOnClickListener=listenerInfoClz.getDeclaredField("mOnClickListener");
            mOnClickListener.setAccessible(true);
            View.OnClickListener orginOnClickListener=(View.OnClickListener) mOnClickListener.get(listenerInfo);


            // 用自定义的 OnClickListener 替换原始的 OnClickListener
            View.OnClickListener hookedOnClickListener=new HookedOnClickListener(orginOnClickListener);
            mOnClickListener.set(listenerInfo,hookedOnClickListener);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    class HookedOnClickListener implements View.OnClickListener{
        private View.OnClickListener orgin;

        HookedOnClickListener(View.OnClickListener orgin){
            this.orgin=orgin;
        }

        @Override
        public void onClick(View v) {
            Log.i("click","Before click");

            if(orgin!=null){
                orgin.onClick(v);
            }

            Log.i("click","After  click");

        }
    }
}
