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

public class MyServicesuoyou extends Service {
    public MyServicesuoyou() {

    }

    private String xuanzhi;
    private String xuanzhi1;
    private String id;
    private String id1;
    private int shuaxin;

    private Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case SendDateToServer.GETVALUE:
                    if(xuanzhi==null) {

                    }else
                        xuanzhi1=xuanzhi;
                    if(id==null){

                    }else
                    id1=id;
                    Intent inta = new Intent("com.example.broadcast.MY_BROADCAST");
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
                Log.d("MyService1", "run");
                id=intent.getStringExtra("id");
                xuanzhi=intent.getStringExtra("xuanzhi");
                shuaxin=intent.getIntExtra("shuaxin",shuaxin);//缺省值
                if(xuanzhi==null){
                    //Log.d("if","if");
                    new SendDateToServer(handler).SendDataToServer(id1,xuanzhi1);
                }else
                    new SendDateToServer(handler).SendDataToServer(id,xuanzhi);


            }

        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = shuaxin * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, MyServicesuoyou.class);
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
