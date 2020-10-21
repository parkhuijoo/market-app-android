package kr.huijoo.mp_hw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Product> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private String lastSelected = "";
    private int REQUEST_TEST = 1;
    final ArrayList<String> atCart = new ArrayList<>();

    Button addToCart;
    Button buyNow;
    ConstraintLayout buttonBox;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TEST) {
            if (resultCode == RESULT_OK) {
                final String[] temp = data.getStringArrayExtra("modifiedCart");
                atCart.clear();
                for(int i = 0; i < temp.length; i++) {
                    atCart.add(temp[i]);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_product);

        recyclerView.setHasFixedSize(true);
        buttonBox = findViewById(R.id.buttonBox);
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

        final CustomAdapter customAdapter = new CustomAdapter(arrayList,this);
        customAdapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (lastSelected.equals(arrayList.get(position).getTitle())) {
                    buttonBox.setVisibility(View.GONE);
                    lastSelected = "";
                    return;
                } else {
                    buttonBox.setVisibility(View.VISIBLE);
                }
                lastSelected = arrayList.get(position).getTitle();
            }
        });
        adapter = customAdapter;
        recyclerView.setAdapter(adapter);

        ImageButton btn_cart = (ImageButton)findViewById(R.id.btn_cart);
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("cartItems", atCart.toArray(new String[atCart.size()]));
                startActivityForResult(intent, REQUEST_TEST);
            }
        });
        addToCart = (Button)findViewById(R.id.btn_addtocart);
        buyNow = (Button)findViewById(R.id.btn_buynow1);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atCart.add(lastSelected);
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("cartItems", atCart.toArray(new String[atCart.size()]));
                startActivityForResult(intent, REQUEST_TEST);
            }
        });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                intent.putExtra("buyItems", new String[] {lastSelected});
                startActivity(intent);
            }
        });
    }
}