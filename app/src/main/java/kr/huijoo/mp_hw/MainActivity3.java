package kr.huijoo.mp_hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    static final int REQ = 3;
    EditText editcontact;
    EditText editaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button button = (Button)findViewById(R.id.btn_buynow3);
        editcontact = (EditText)findViewById(R.id.edittext_contact);
        editaddress = (EditText)findViewById(R.id.edittext_address);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contact = editcontact.getText().toString();
                String address = editaddress.getText().toString();
                Intent intent = new Intent(MainActivity3.this,MainActivity.class);
//                Toast.makeText(getApplicationContext(),contact+"hi",Toast.LENGTH_LONG).show();
                if(!contact.isEmpty()&&!address.isEmpty()){
                    Toast.makeText(getApplicationContext(),contact+"님의 상품이 결제완료 되었습니다.\n"+address+"로 배달 예정입니다.",Toast.LENGTH_LONG).show();
                    startActivityForResult(intent,REQ);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"입력해주세요",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}