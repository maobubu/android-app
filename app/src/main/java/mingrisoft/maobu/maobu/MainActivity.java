package mingrisoft.maobu.maobu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   private List<Wendu> wenduList=new ArrayList<>();
    public static String zhongduanid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWendu();
        WenduAdapter adapter = new WenduAdapter(MainActivity.this, R.layout.wendu_item, wenduList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Wendu wendu = wenduList.get(position);
                Toast.makeText(MainActivity.this, wendu.getName(), Toast.LENGTH_SHORT).show();
                switch(position){
                    case 0: Intent intent=new Intent(MainActivity.this,Zhongduan1.class);
                        zhongduanid=wendu.getName();
                        startActivity(intent);
                        break;
                    case 1: Intent intent1=new Intent(MainActivity.this,Zhongduan2.class);
                        startActivity(intent1);
                        zhongduanid=wendu.getName();
                        break;
                    case 2: Intent intent2=new Intent(MainActivity.this,Zhongduan3.class);
                        startActivity(intent2);
                        zhongduanid=wendu.getName();
                        break;
                    case 3: Intent intent3=new Intent(MainActivity.this,Zhongduan4.class);
                        startActivity(intent3);
                        zhongduanid=wendu.getName();
                        break;
                    case 4: Intent intent4=new Intent(MainActivity.this,Zhongduan5.class);
                        startActivity(intent4);
                        zhongduanid=wendu.getName();
                        break;
                    case 5: Intent intent5=new Intent(MainActivity.this,Zhongduan6.class);
                        startActivity(intent5);
                        zhongduanid=wendu.getName();
                        break;
                    case 6: Intent intent6=new Intent(MainActivity.this,Zhongduan7.class);
                        startActivity(intent6);
                        zhongduanid=wendu.getName();
                        break;
                    case 7: Intent intent7=new Intent(MainActivity.this,Zhongduan8.class);
                        startActivity(intent7);
                        zhongduanid=wendu.getName();
                        break;
                    case 8: Intent intent8=new Intent(MainActivity.this,Zhongduan9.class);
                        startActivity(intent8);
                        zhongduanid=wendu.getName();
                        break;
                    case 9: Intent intent9=new Intent(MainActivity.this,Zhongduan10.class);
                        startActivity(intent9);
                        zhongduanid=wendu.getName();
                        break;
                    default:
                        break;
                }
                Log.d("zhongduanid",zhongduanid);

            }

        });
    }
    private void initWendu(){
        for(int i=0; i<2;i++){
            Wendu zhongduan_1=new Wendu("终端一:100117040110001",R.mipmap.ic_launcher1);
            wenduList.add(zhongduan_1);
            Wendu zhongduan_2=new Wendu("终端二:100117050211012",R.mipmap.ic_launcher1);
            wenduList.add(zhongduan_2);
            Wendu zhongduan_3=new Wendu("终端三:100117050121078",R.mipmap.ic_launcher1);
            wenduList.add(zhongduan_3);
            Wendu zhongduan_4=new Wendu("终端四:100117040315500",R.mipmap.ic_launcher1);
            wenduList.add(zhongduan_4);
            Wendu zhongduan_5=new Wendu("终端五:100117050140026",R.mipmap.ic_launcher1);
            wenduList.add(zhongduan_5);
            Wendu zhongduan_6=new Wendu("终端六:100117040145612",R.mipmap.ic_launcher1);
            wenduList.add(zhongduan_6);
            Wendu zhongduan_7=new Wendu("终端七:100117040100037",R.mipmap.ic_launcher1);
            wenduList.add(zhongduan_7);
            Wendu zhongduan_8=new Wendu("终端八:100117050512044",R.mipmap.ic_launcher1);
            wenduList.add(zhongduan_8);
            Wendu zhongduan_9=new Wendu("终端九:100117050121011",R.mipmap.ic_launcher1);
            wenduList.add(zhongduan_9);
            Wendu zhongduan_10=new Wendu("终端十:100117050212104",R.mipmap.ic_launcher1);
            wenduList.add(zhongduan_10);

        }
    }

    }

