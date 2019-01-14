package mingrisoft.maobu.maobu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;




public class Zhongduan10 extends AppCompatActivity {
    private static final String[] m={"0分","15分","30分","45分","60分"};
    private static final String[] n={"30秒","1分","2分","3分","4分"};

    private Button btnSend;
    private Button btnRecieve;
    private  TextView wendu1;
    private  TextView shijian1;
    private TextView shijian2;
    private TextView shijian3;
    private TextView shijian4;
    private TextView shijian5;
    private int a;
    private int b;
    private TextView riqi1;
    private TextView riqi2;
    private TextView riqi3;
    private TextView riqi4;
    private TextView riqi5;
    private TextView wendu2;
    private TextView wendu3;
    private TextView wendu4;
    private TextView wendu5;
    private TextView result;
    private TextView Ashijian;
    private TextView Ariqi;
    private String yuming;
    private String strid;
    private String strwd1;
    private String strwd2;
    private String strwd3;
    private String strwd4;
    private String strwd5;
    private String strrq;
    private String strsj;
    private String APPriqi;
    private String APPshijian;
    public static String xuanzhi;
    public static int shuaxin;
    public static String id;
    private static String cunchu;
    private TextView view ;
    private TextView view1;
    private Spinner spinner;
    private Spinner spinner2;
    private String input;
    private IntentFilter intentFilter;
    private MyBroadcastReceiver shujuReceiver;

    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter1;





    private Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case SendDateToServer.GETVALUE:
                    cunchu=SendDateToServer.str1;
                    strid=SendDateToServer.str1.substring(0,14);
                    strwd1=SendDateToServer.str1.substring(16,22);
                    strwd2=SendDateToServer.str1.substring(23,29);
                    strwd3=SendDateToServer.str1.substring(30,36);
                    strwd4=SendDateToServer.str1.substring(37,43);
                    strwd5=SendDateToServer.str1.substring(44,50);
                    strrq=SendDateToServer.str1.substring(51,61);
                    strsj=SendDateToServer.str1.substring(62,70);
                    APPriqi=SendDateToServer.str1.substring(71,80);
                    APPshijian=SendDateToServer.str1.substring(81,89);

                    if(SendDateToServer.str1.indexOf("*")==-1){
                        result.setText("终端状态：正常");
                    }else{
                        result.setText("终端状态：异常");
                    }
                    wendu1.setText(getString(R.string.wenduzhi)+strwd1);
                    wendu2.setText(getString(R.string.wenduzhi)+strwd2);
                    wendu3.setText(getString(R.string.wenduzhi)+strwd3);
                    wendu4.setText(getString(R.string.wenduzhi)+strwd4);
                    wendu5.setText(getString(R.string.wenduzhi)+strwd5);
                    riqi1.setText(getString(R.string.riqi)+strrq);
                    riqi2.setText(getString(R.string.riqi)+strrq);
                    riqi3.setText(getString(R.string.riqi)+strrq);
                    riqi4.setText(getString(R.string.riqi)+strrq);
                    riqi5.setText(getString(R.string.riqi)+strrq);
                    shijian1.setText(getString(R.string.shijian)+strsj);
                    shijian2.setText(getString(R.string.shijian)+strsj);
                    shijian3.setText(getString(R.string.shijian)+strsj);
                    shijian4.setText(getString(R.string.shijian)+strsj);
                    shijian5.setText(getString(R.string.shijian)+strsj);
                    Ariqi.setText("App接收日期"+APPriqi);
                    Ashijian.setText("App接收时间"+APPshijian);
                    break;




                case SendDateToServer.SEND_SUCCESS:
                    Toast.makeText(Zhongduan10.this, "发送成功", Toast.LENGTH_SHORT).show();
                    Log.d("发送成功","发送成功");
                    break;

                case SendDateToServer.SEND_FAIL:
                    Toast.makeText(Zhongduan10.this, "发送失败", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhongduan10);
        btnSend=(Button)findViewById(R.id.Sendbtn);
        btnRecieve=(Button)findViewById(R.id.button3);
        wendu1=(TextView)findViewById(R.id.textView6);
        wendu2=(TextView)findViewById(R.id.textView11);
        wendu3=(TextView)findViewById(R.id.textView16);
        wendu4=(TextView)findViewById(R.id.textView39);
        wendu5=(TextView)findViewById(R.id.textView42);
        riqi1=(TextView)findViewById(R.id.textView2);
        riqi2=(TextView)findViewById(R.id.textView13);
        riqi3=(TextView)findViewById(R.id.textView17);
        riqi4=(TextView)findViewById(R.id.textView38);
        riqi5=(TextView)findViewById(R.id.textView43);
        shijian1=(TextView)findViewById(R.id.textView8);
        shijian2=(TextView)findViewById(R.id.textView14);
        shijian3=(TextView)findViewById(R.id.textView34);
        shijian4=(TextView)findViewById(R.id.textView40);
        shijian5=(TextView)findViewById(R.id.textView45);
        result=(TextView)findViewById(R.id.textView18);
        Ariqi=(TextView)findViewById(R.id.textView23);
        Ashijian=(TextView)findViewById(R.id.textView28);
        view= (TextView) findViewById(R.id.textView3);
        view1=(TextView) findViewById(R.id.textView22);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2= (Spinner) findViewById(R.id.spinner2);

        final Intent Service=new Intent(this,MyService9.class);//服务
        intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest9.MY_BROADCAST");//广播
        shujuReceiver=new MyBroadcastReceiver();
        registerReceiver(shujuReceiver,intentFilter);
        SharedPreferences share = getSharedPreferences("saveName10", 0);
        int xuanzhiselection= share.getInt("xuanzhi",0);
        int shuaxinselection= share.getInt("shuaxin",0);
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spinner.setAdapter(adapter);
        //添加事件Spinner事件监听
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
        //设置默认值
        spinner.setVisibility(View.VISIBLE);

        adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,n);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter1);
        spinner.setSelection(xuanzhiselection);
        spinner2.setSelection(shuaxinselection);
        spinner2.setOnItemSelectedListener(new SpinnerSelectedL());
        spinner2.setVisibility(View.VISIBLE);

        id=MainActivity.zhongduanid.substring(4,19);
       // Log.d("id",id);
        input=load();
       // Log.d("input",input);//加载
        if(!TextUtils.isEmpty(input)){
            strid=input.substring(0,14);
            strwd1=input.substring(16,22);
            strwd2=input.substring(23,29);
            strwd3=input.substring(30,36);
            strwd4=input.substring(37,43);
            strwd5=input.substring(44,50);
            strrq=input.substring(51,61);
            strsj=input.substring(62,70);
            APPriqi=input.substring(71,80);
            APPshijian=input.substring(81,89);
            if(input.indexOf("*")==-1){
                result.setText("终端状态：正常");
            }else{
                result.setText("终端状态：异常");
            }
            wendu1.setText(getString(R.string.wenduzhi)+strwd1);
            wendu2.setText(getString(R.string.wenduzhi)+strwd2);
            wendu3.setText(getString(R.string.wenduzhi)+strwd3);
            wendu4.setText(getString(R.string.wenduzhi)+strwd4);
            wendu5.setText(getString(R.string.wenduzhi)+strwd5);
            riqi1.setText(getString(R.string.riqi)+strrq);
            riqi2.setText(getString(R.string.riqi)+strrq);
            riqi3.setText(getString(R.string.riqi)+strrq);
            riqi4.setText(getString(R.string.riqi)+strrq);
            riqi5.setText(getString(R.string.riqi)+strrq);
            shijian1.setText(getString(R.string.shijian)+strsj);
            shijian2.setText(getString(R.string.shijian)+strsj);
            shijian3.setText(getString(R.string.shijian)+strsj);
            shijian4.setText(getString(R.string.shijian)+strsj);
            shijian5.setText(getString(R.string.shijian)+strsj);
            Ariqi.setText("App接收日期"+APPriqi);
            Ashijian.setText("App接收时间"+APPshijian);
            Toast.makeText(this,"Restoring succeeded",Toast.LENGTH_SHORT).show();

        }
        btnSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new SendDateToServer(handler).SendDataToServer(id,xuanzhi);


            }
        });
        btnRecieve.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Service.putExtra("xuanzhi",xuanzhi);
                Service.putExtra("shuaxin",shuaxin);
                startService(Service);

            }
        });

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(shujuReceiver);
        if(cunchu==null){
            cunchu=input;

        }
        save(cunchu);//存储
        SharedPreferences sharedPreferences = getSharedPreferences("saveName10", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //保存被选中的位置
        editor.putInt("xuanzhi", a);
        editor.putInt("shuaxin", b);
        editor.commit();

    }
    public void save(String inputtext){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try {
            out = openFileOutput("data10", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));

            writer.write(inputtext);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer!=null){
                    writer.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    public String load(){
        FileInputStream in=null;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        try {
            in = openFileInput("data10");
            reader = new BufferedReader(new InputStreamReader(in));
            String line= " ";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try{
                    reader.close();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();

    }

    //使用数组形式操作
    class SpinnerSelectedListener implements OnItemSelectedListener{

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            view.setText("你选择的时间间隔是："+m[arg2]);
            switch(m[arg2]){
                case "0分":xuanzhi="0";
                    break;
                case "15分":xuanzhi="1";
                    break;
                case "30分":xuanzhi="2";
                    break;
                case "45分":xuanzhi="3";
                    break;
                case "60分":xuanzhi="4";
                    break;
                default:
                    break;
            }

            a=spinner.getSelectedItemPosition();

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    class SpinnerSelectedL implements OnItemSelectedListener{

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            view1.setText("你选择的刷新时间是:"+n[arg2]);
            switch(n[arg2]){
                case "30秒":shuaxin=30;
                    break;
                case "1分":shuaxin=60;
                    break;
                case "2分":shuaxin=120;
                    break;
                case "3分":shuaxin=180;
                    break;
                case "4分":shuaxin=240;
                    break;
                default:
                    break;
            }

            b=spinner2.getSelectedItemPosition();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }


    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("msg");
            Toast.makeText(context, "终端十刷新完成", Toast.LENGTH_SHORT)
                    .show();
            if(msg!=null){
                cunchu=msg;
                strid=msg.substring(0,14);
                strwd1=msg.substring(16,22);
                strwd2=msg.substring(23,29);
                strwd3=msg.substring(30,36);
                strwd4=msg.substring(37,43);
                strwd5=msg.substring(44,50);
                strrq=msg.substring(51,61);
                strsj=msg.substring(62,70);
                APPriqi=msg.substring(71,80);
                APPshijian=msg.substring(81,89);
                if(msg.indexOf("*")==-1){
                    result.setText("终端状态：正常");
                }else{
                    result.setText("终端状态：异常");
                }
                wendu1.setText(getString(R.string.wenduzhi)+strwd1);
                wendu2.setText(getString(R.string.wenduzhi)+strwd2);
                wendu3.setText(getString(R.string.wenduzhi)+strwd3);
                wendu4.setText(getString(R.string.wenduzhi)+strwd4);
                wendu5.setText(getString(R.string.wenduzhi)+strwd5);
                riqi1.setText(getString(R.string.riqi)+strrq);
                riqi2.setText(getString(R.string.riqi)+strrq);
                riqi3.setText(getString(R.string.riqi)+strrq);
                riqi4.setText(getString(R.string.riqi)+strrq);
                riqi5.setText(getString(R.string.riqi)+strrq);
                shijian1.setText(getString(R.string.shijian)+strsj);
                shijian2.setText(getString(R.string.shijian)+strsj);
                shijian3.setText(getString(R.string.shijian)+strsj);
                shijian4.setText(getString(R.string.shijian)+strsj);
                shijian5.setText(getString(R.string.shijian)+strsj);
                Ariqi.setText("App接收日期"+APPriqi);
                Ashijian.setText("App接收时间"+APPshijian);
            }



        }
    }
}