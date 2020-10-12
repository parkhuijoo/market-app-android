package kr.huijoo.mp_hw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Product> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<Product> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    //view holder를 최초로 만들어낸다.
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        //이거왜 뷰홀더아니고 커스텀 뷰홀더지?
        return holder;
    }

    @Override
    //실제로 아이템을 매칭시켜주는 역할
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        final int pos = position;
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getImage())
                .into(holder.iv);
        holder.tv_title.setText(arrayList.get(position).getTitle());
        holder.tv_price.setText(arrayList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return (arrayList!=null ? arrayList.size() : 0);
    }

    //list_item 과 연동
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv_title;
        TextView tv_price;


         CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv = itemView.findViewById(R.id.iv);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_price = itemView.findViewById(R.id.tv_price);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int pos = getAdapterPosition();
//                    if (pos!=RecyclerView.NO_POSITION){
//                        if(mListener!=null){
//                            mListener.onItemClick(view,pos);
//                        }
//                    }
//                }
//            });

        }
    }

//    private OnItemClickListener mListener = null ;
//
//    public interface OnItemClickListener {
//        void onItemClick(View v, int position) ;
//    }
//
//    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.mListener = listener ;
//    }

//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.mListener = listener ;
//    }

}
