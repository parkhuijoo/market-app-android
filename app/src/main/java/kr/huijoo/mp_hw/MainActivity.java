package kr.huijoo.mp_hw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Product> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private boolean mark = false;

    static final int REQ = 1 ;
    ImageView img;
    Button button1; //btn_addtocard
    Button button2; //btn_buynow
    ImageView imagelist[] = new ImageView[3]; //iv 개수 (iv = 카드뷰내의 상품이미)
    boolean flag[] = new boolean[3]; //iv 개수


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_product);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Product");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Product product = snapshot.getValue(Product.class);
                    arrayList.add(product);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MainActivity.java", String.valueOf(databaseError.toException()));
            }
        });

        adapter = new CustomAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);

//        CustomAdapter customAdapter = new CustomAdapter(arrayList,this);
//        customAdapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int pos) {
//                if(pos!=RecyclerView.NO_POSITION){
//                    Toast.makeText(getApplicationContext(),"hi",Toast.LENGTH_LONG).show();
//                    button1.setVisibility(button1.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
//                    button2.setVisibility(button2.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
//                }
//            }
//        });



        ImageButton btn_cart = (ImageButton)findViewById(R.id.btn_cart);
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this,MainActivity2.class);
                startActivityForResult(intent1,REQ);
                finish();
            }
        });
        button1 = (Button)findViewById(R.id.btn_addtocart);
        button2 = (Button)findViewById(R.id.btn_buynow1);
//        button1.setVisibility(View.GONE);
//        button2.setVisibility(View.GONE);



//        recyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                button1.setVisibility(button1.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
//                button2.setVisibility(button2.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
//            }
//        });


//        ImageView imageView = (ImageView)findViewById(R.id.iv);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mark = true;
//                button1.setVisibility(button1.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
//                button2.setVisibility(button2.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
//            }
//        });


//        for (int i = 0; i<3; i++){ //iv 개수
//            final int INDEX;
//            INDEX = i;
//            int k = getResources().getIdentifier("iv", "id", getPackageName());
//            flag[INDEX] = false;
//            imagelist[INDEX] =(ImageView) findViewById(k);
//            imagelist[INDEX].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    flag[INDEX] = true;
//                    button1.setVisibility(button1.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
//                    button2.setVisibility(button2.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
//                }
//            });
//        }

// putextra function


        // itemclick을 기억했다가 flag같은걸 true로 바꿔주고 다른걸 누르면 그거만 true로 해주고 나머지는 다 false로 해야함, 그상황에서 버튼을 누르면 true인 정보만 옮겨준다.
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this,MainActivity2.class);

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this,MainActivity2.class);
                for (int i = 0; i<3; i++) { //iv 개수
                    if(flag[i]){
                        int id = getResources().getIdentifier("tv_title","id",getPackageName());
                        TextView title = (TextView)findViewById(id);
                        String string_id = title.getText().toString();
                        intent1.putExtra("title",string_id);
                        Toast.makeText(getApplicationContext(),"flag"+i,Toast.LENGTH_LONG).show();
                    }
                }
                startActivityForResult(intent1,REQ);
                finish();
            }
        });
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent1 = new Intent(MainActivity.this,MainActivity3.class);
//                startActivityForResult(intent1,REQ);
//                finish();
//            }
//        });
    }
}