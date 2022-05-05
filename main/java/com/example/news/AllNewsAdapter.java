package com.example.news;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AllNewsAdapter extends RecyclerView.Adapter<AllNewsAdapter.MyViewHolder>{

    
    private OnAllNewsItemClickListener onAllNewsItemClickListener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_allnews,parent,false);
        return new AllNewsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int pos = position;
        holder.text_newsTitle.setText(titles.get(pos));
        holder.text_newsDetail.setText(details.get(pos));
        switch (pos) {
            case 0:
                holder.imageView.setBackgroundResource(R.drawable.pic1);
                break;
            case 1:
                holder.imageView.setBackgroundResource(R.drawable.pic2);
                break;
            case 2:
                holder.imageView.setBackgroundResource(R.drawable.pic3);
                break;
            case 3:
                holder.imageView.setBackgroundResource(R.drawable.pic4);
                break;
            case 4:
                holder.imageView.setBackgroundResource(R.drawable.pic5);
                break;
            case 5:
                holder.imageView.setBackgroundResource(R.drawable.pic6);
                break;
            case 6:
                holder.imageView.setBackgroundResource(R.drawable.pic7);
                break;
            default:
                break;
        }
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAllNewsItemClickListener.onAllNewsItemClick(view,pos);
            }
        });
    }
    public List<String> details;
    public List<String> titles;

    public interface OnAllNewsItemClickListener {
        void onAllNewsItemClick(View view,int pos);
    }

    public void addOnAllNewsItemClickListener(OnAllNewsItemClickListener listener){
        onAllNewsItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView text_newsDetail;
        public ImageView imageView;
        public ConstraintLayout constraintLayout;
        public TextView text_newsTitle;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView1);
            text_newsTitle = itemView.findViewById(R.id.text_newsTitle);
            text_newsDetail =  itemView.findViewById(R.id.text_newsDetail);
            constraintLayout = itemView.findViewById(R.id.layout_cl);
        }
    }

    public AllNewsAdapter(List<String> titles,List<String> details){
        this.titles = titles;
        this.details = details;
    }

}
