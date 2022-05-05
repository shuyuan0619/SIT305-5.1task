package com.example.news;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class TopNewsAdapter extends RecyclerView.Adapter<TopNewsAdapter.MyViewHolder> {


    private OnTopNewsItemClickListener mOnTopNewsItemClickListener;
    private List<Boolean> ifClick;
    private List<Integer> mData;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topnews,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int pos = position;
        if(pos == 0){
            holder.imageView1.setBackgroundResource(R.drawable.pic1);
        }else if(pos == 1){
            holder.imageView1.setBackgroundResource(R.drawable.pic2);
        }else if(pos == 2){
            holder.imageView1.setBackgroundResource(R.drawable.pic3);
        }else if(pos == 3){
            holder.imageView1.setBackgroundResource(R.drawable.pic4);
        }else if(pos == 4){
            holder.imageView1.setBackgroundResource(R.drawable.pic5);
        }else if(pos == 5){
            holder.imageView1.setBackgroundResource(R.drawable.pic6);
        }else if(pos == 6){
            holder.imageView1.setBackgroundResource(R.drawable.pic7);
        }
        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnTopNewsItemClickListener.onTopNewsItemClick(view,pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnTopNewsItemClickListener {
        void onTopNewsItemClick(View view,int position);
    }

    public void addOnTopNewsItemClickListener(OnTopNewsItemClickListener listener){
        mOnTopNewsItemClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView1;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.imageView1);
        }
    }

    public TopNewsAdapter(List<Integer> dataList){
        this.mData = dataList;
        ifClick = new LinkedList<Boolean>();
        for(int i=0;i<mData.size();i++){
            ifClick.add(false);
        }
    }

}
