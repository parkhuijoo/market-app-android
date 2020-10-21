package kr.huijoo.mp_hw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    EditText editcontact;
    EditText editaddress;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    ImageView buyImage;
    TextView buyTitle;
    TextView buyPrice;
    TextView howMany;
    TextView totalPriceText;
    ArrayList<Integer> prices;
    int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button button = (Button)findViewById(R.id.btn_buynow3);
        editcontact = (EditText)findViewById(R.id.edittext_contact);
        editaddress = (EditText)findViewById(R.id.edittext_address);

        Intent intent = getIntent();
        final String[] buyItems = intent.getStringArrayExtra("buyItems");

        prices = new ArrayList<>();

        buyImage = this.findViewById(R.id.buy_image);
        buyTitle = this.findViewById(R.id.buy_title);
        buyPrice = this.findViewById(R.id.buy_price);
        howMany = this.findViewById(R.id.how_many);
        totalPriceText = this.findViewById(R.id.totalPrice);

        howMany.setText("외 " + (buyItems.length - 1) + "건");

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Product");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i = 0; i < buyItems.length; i++) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Product product = snapshot.getValue(Product.class);
                        if (product.getTitle().equals(buyItems[i])) {
                            if (i == 0) {
                                Glide.with(MainActivity3.this)
                                        .load(product.getImage())
                                        .into(buyImage);
                                buyTitle.setText(product.getTitle());
                                buyPrice.setText(product.getPrice());
                            }
                            totalPrice += Integer.parseInt(product.getPrice().substring(1));
                            totalPriceText.setText(String.valueOf(totalPrice));
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MainActivity3.java", String.valueOf(databaseError.toException()));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contact = editcontact.getText().toString();
                String address = editaddress.getText().toString();
                if(!contact.isEmpty()&&!address.isEmpty()){
                    Toast.makeText(getApplicationContext(),contact+"님의 상품이 결제완료 되었습니다.\n"+address+"로 배송할게요!",Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"모든 칸을 채워주세요!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}