package mingrisoft.maobu.maobu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
public class Huanying extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_huanying);
        final View view=View.inflate(this, R.layout.activity_huanying, null);
        setContentView(view);
        final ImageView imageView=(ImageView)findViewById(R.id.imageView4);
        final Intent it = new Intent(this, Jiemian.class); //转向activity
        //使用AnimationUtils类的静态方法loadAnimation()来加载XML中的动画XML文件
        Animation animation=AnimationUtils.loadAnimation(this, R.anim.ap2);
        view.startAnimation(animation);
        animation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {

            }   //动画开始

            @Override
            public void onAnimationRepeat(Animation arg0) {}  //动画重复

            @Override
            public void onAnimationEnd(Animation arg0) {

            }
        });

    /*    Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(it); //执行
                finish();
                overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果

            }
        };

        timer.schedule(task, 1000 * 3);//3秒后*/
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(it);
                overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果

            }
        });

    }
//再按一次退出程序
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){                           //连击在两秒之内退出
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
