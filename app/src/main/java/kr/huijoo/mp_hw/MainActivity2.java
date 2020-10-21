package kr.huijoo.mp_hw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Product> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    ImageView cartImage;
    TextView cartTitle;
    TextView cartPrice;
    CheckBox checkBox;

    @Override
    public void onBackPressed() {
        // Blocked
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        final String[] atCart = intent.getStringArrayExtra("cartItems");
        Arrays.sort(atCart);

        recyclerView = findViewById(R.id.recyclerview_cartProduct);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<Product>();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Product");
        cartImage = findViewById(R.id.iv2);
        cartTitle = findViewById(R.id.tv_title2);
        cartPrice = this.findViewById(R.id.tv_price2);
        checkBox = this.findViewById(R.id.checkbox);
        
        final CustomAdapter2 customAdapter2 = new CustomAdapter2(arrayList,this);
        adapter = customAdapter2;
        recyclerView.setAdapter(adapter);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i = 0; i < atCart.length; i++) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Product product = snapshot.getValue(Product.class);
                        if (product.getTitle().equals(atCart[i])) {
                            arrayList.add(product);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MainActivity2.java", String.valueOf(databaseError.toException()));
            }
        });

        ImageButton btn_home = (ImageButton)findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayList<String> atCart = new ArrayList<>();
                for (int i = 0; i < arrayList.size(); i++) {
                    atCart.add(arrayList.get(i).getTitle());
                }
                Intent intent = new Intent();
                intent.putExtra("modifiedCart", atCart.toArray(new String[atCart.size()]));
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Button buybtn = (Button)findViewById(R.id.btn_buynow2);
        buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayList<String> selected = new ArrayList<>();
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).isSelected()) {
                        selected.add(arrayList.get(i).getTitle());
                    }
                }
                if (selected.size() == 0) return;
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                intent.putExtra("buyItems", selected.toArray(new String[selected.size()]));
                startActivity(intent);
                finish();
            }
        });

        Button delbtn = (Button)findViewById(R.id.btn_del);
        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = arrayList.size() - 1; i >= 0; i--) {
                    if (arrayList.get(i).isSelected()) {
                        arrayList.remove(i);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}