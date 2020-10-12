package kr.huijoo.mp_hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    static final int REQ = 2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent11 = getIntent();
        String str_id = intent11.getStringExtra("title");
        Toast.makeText(getApplicationContext(),str_id,Toast.LENGTH_LONG).show();


        ImageButton btn_home = (ImageButton)findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent21 = new Intent(MainActivity2.this, MainActivity.class);
                startActivityForResult(intent21,REQ);
                finish();
            }
        });
        Button button = (Button)findViewById(R.id.btn_buynow2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent22 = new Intent(MainActivity2.this,MainActivity3.class);
                startActivityForResult(intent22,REQ);
                finish();
            }
        });
    }
}