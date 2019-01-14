package mingrisoft.maobu.maobu;

/**
 * Created by maobu on 2017/4/19.
 */

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.io.*;
import java.lang.String;



/**
 * 通过GET方式向服务器发送数据
 * @author lhc
 * Date:2017.04.17
 */
public class Chaxun {
    //private static String url="http://simon2017api.duapp.com/queryter.php";
    private static String url="http://simon2017api.duapp.com/tem.php";
    //private static String url = "http://10.26.23.65:8080/Fuwuqi/MyServlet
    public static final int SEND_SUCCESS = 0x123;
    public static final int SEND_FAIL = 0x124;
    public static final int UPdate_text = 1;
    public static final int GETVALUE = 0;
    public static String str;
    public static String str1;
    private Handler handler;
    Message mm1 = new Message();

    public Chaxun(Handler handler) {
        // TODO Auto-generated constructor stub
        this.handler = handler;
    }

    /**
     * 通过Get方式向服务器发送数据
     *
     * @param //ter    查询
     */
    public void Chaxun(String name) {
        // TODO Auto-generated method stub
        final Map<String, String> map = new HashMap<String, String>();
        map.put("ter", name);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    if (sendGetRequest(map, url, "utf-8")) {
                        handler.sendEmptyMessage(SEND_SUCCESS);
                        //通知主线程数据发送成功
                    } else {
                        handler.sendEmptyMessage(SEND_FAIL);//将数据发送给服务器失败
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 发送GET请求
     * //@param map 请求参数
     *
     * @param url 请求路径
     * @return
     * @throws Exception
     */

    public boolean sendGetRequest(Map<String, String> param, String url, String encoding) throws Exception {
        // TODO Auto-generated method stub

        StringBuffer sb = new StringBuffer(url);
        StringBuilder jieshou = new StringBuilder();
        if (!url.equals("") & !param.isEmpty()) {
            sb.append("?");
            for (Map.Entry<String, String> entry : param.entrySet()) {
                sb.append(entry.getKey() + "=");
                sb.append(URLEncoder.encode(entry.getValue(), encoding));
                sb.append("&");
            }
            sb.deleteCharAt(sb.length() - 1);//删除字符串最后 一个字符“&”
            Log.d("maobu", "url" + sb);
            //Message mm2 = new Message();
           // mm2.what = UPdate_text;
            //handler.sendMessage(mm2);
        }

        HttpURLConnection conn = (HttpURLConnection) new URL(sb.toString()).openConnection();
        conn.setReadTimeout(5000);
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");//设置请求方式为GET
        conn.setDoInput(true);

        if (conn.getResponseCode() == 200) {
            InputStream in = conn.getInputStream();                               //接收服务器数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                jieshou.append(line);
            }
            reader.close();
            mm1.what = UPdate_text;
            str = jieshou.toString();
            //mm1.obj = jieshou.toString();
            handler.sendMessage(mm1);
            Log.d("str", str);

            return true;

        }

        return false;


    }
}

