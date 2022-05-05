package com.example.news;

import android.util.Log;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.content.res.Resources;
import java.util.List;
import android.view.ViewGroup;
import android.widget.ImageView;

public class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsAdapter.MyViewHolder>{
    public List<Integer> newsList;
    public String[] newsTitleList;
    public String[] newsDetailList;
    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
    public int currentIndex;
    public OnRelatedNewsItemClickListener onRelatedNewsItemClickListener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_related_news,parent,false);
        return new RelatedNewsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int pos = position;
        holder.text_newsTitle.setText(newsTitleList[newsList.get(position)]);
        if(newsDetailList[newsList.get(position)].length()>35){
            String str = "";
            str = newsDetailList[newsList.get(position)].substring(0,35);
            str = str + "...";
            holder.text_newsDetail.setText(str);
        }else{
            Log.e("TAG", "onBindViewHolder: 22222" );
            holder.text_newsDetail.setText(newsDetailList[newsList.get(position)]);
        }
        int temp = newsList.get(position);
        if(temp == 0){
            holder.imageView.setBackgroundResource(R.drawable.pic1);
        }else if(temp == 1){
            holder.imageView.setBackgroundResource(R.drawable.pic2);
        }else if(temp == 2){
            holder.imageView.setBackgroundResource(R.drawable.pic3);
        }else if(temp == 3){
            holder.imageView.setBackgroundResource(R.drawable.pic4);
        }else if(temp == 4){
            holder.imageView.setBackgroundResource(R.drawable.pic5);
        }else if(temp == 5){
            holder.imageView.setBackgroundResource(R.drawable.pic6);
        }else if(temp == 6){
            holder.imageView.setBackgroundResource(R.drawable.pic7);
        }
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onRelatedNewsItemClickListener.onRelatedNewsItemClick(view,pos);
            }
        });
    }

    public interface OnRelatedNewsItemClickListener {
        void onRelatedNewsItemClick(View view,int position);
    }
    public void addOnRelatedNewsItemClickListener(OnRelatedNewsItemClickListener onListener){
        Log.e("TAG", "addOnTopNewsItemClickListener: " + " listenner sucess" );
        onRelatedNewsItemClickListener = onListener;
    }
    @Override
    public int getItemCount() {
        int count = newsList.size();
        return count;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView text_newsTitle;
        public TextView text_newsDetail;
        public ConstraintLayout constraintLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pictureTemp);
            text_newsTitle = itemView.findViewById(R.id.text_title);
            text_newsDetail =  itemView.findViewById(R.id.text_detail);
            constraintLayout = itemView.findViewById(R.id.layout_cl);
        }
    }

    public RelatedNewsAdapter(int index, Context context,List<Integer> newsList){
        this.newsList = newsList;
        currentIndex = index;
        Resources res =context.getResources();
        newsTitleList =res.getStringArray(R.array.newsTitle);
        newsDetailList =res.getStringArray(R.array.newsDetail);
    }
}
