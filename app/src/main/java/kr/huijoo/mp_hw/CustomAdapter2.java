package kr.huijoo.mp_hw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.CustomViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    private OnItemClickListener mListener = null;

    private ArrayList<Product> arrayList;
    private Context context;


    public CustomAdapter2(ArrayList<Product> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        final int pos = position;
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getImage())
                .into(holder.iv2);
        holder.tv_title2.setText(arrayList.get(position).getTitle());
        holder.tv_price2.setText(arrayList.get(position).getPrice());
        holder.checkBox.setChecked(arrayList.get(position).isSelected());
        holder.checkBox.setTag(arrayList.get(position));

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Product product = (Product) cb.getTag();
                product.setSelected(cb.isChecked());
                arrayList.get(position).setSelected(cb.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (arrayList!=null ? arrayList.size() : 0);
    }

    public Product getItem(int i){
        return arrayList.get(i);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv2;
        TextView tv_title2;
        TextView tv_price2;
        CheckBox checkBox;

        CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv2 = itemView.findViewById(R.id.iv2);
            this.tv_title2 = itemView.findViewById(R.id.tv_title2);
            this.tv_price2 = itemView.findViewById(R.id.tv_price2);
            this.checkBox = itemView.findViewById(R.id.checkbox);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(v, pos);
                        }
                    }
                }
            });
        }

        public ArrayList<Product> getarrayList(){
            return arrayList;
        }
    }
}
