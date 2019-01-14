package mingrisoft.maobu.maobu;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

public class MyService7 extends Service {
    public MyService7() {

    }
    private String xuanzhi;
    private String xuanzhi1;
    private int shuaxin;

    private Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case SendDateToServer.GETVALUE:
                    if(xuanzhi==null) {

                    }else
                        xuanzhi1=xuanzhi;
                    Intent inta = new Intent("com.example.broadcasttest7.MY_BROADCAST");//广播
                    inta.putExtra("msg", SendDateToServer.str1);
                    sendBroadcast(inta);
                    break;

                case SendDateToServer.SEND_SUCCESS:


                    break;

                case SendDateToServer.SEND_FAIL:

                    break;

                default:
                    break;
            }
        }
    };


    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("MyService8", "run");
                xuanzhi=intent.getStringExtra("xuanzhi");
                shuaxin=intent.getIntExtra("shuaxin",shuaxin);//缺省值
                if(xuanzhi==null){
                    new SendDateToServer(handler).SendDataToServer(Zhongduan8.id,xuanzhi1);
                }else
                    new SendDateToServer(handler).SendDataToServer(Zhongduan8.id,xuanzhi);

            }

        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = shuaxin * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, MyService7.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
