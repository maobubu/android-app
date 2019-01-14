package mingrisoft.maobu.maobu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Jiemian extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiemian);
        Button guding = (Button) findViewById(R.id.button);
        Button suoyou = (Button) findViewById(R.id.button2);

        guding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Jiemian.this, MainActivity.class);
                startActivity(intent);
            }
        });
        suoyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent1 = new Intent(Jiemian.this, Suoyou.class);
                startActivity(intent1);

            }
        });
    }
}