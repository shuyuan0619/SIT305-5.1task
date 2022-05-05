package com.example.news;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class DetailNewsFragment extends Fragment implements RelatedNewsAdapter.OnRelatedNewsItemClickListener{

    public ImageView pictureTemp;
    public TextView text_title;
    public TextView text_detail;
    public RecyclerView newsId;
    public String newsTitle;
    public String newsDetail;
    public int curruntPos = -2;
    public List<Integer> restNews;
    
    public RelatedNewsAdapter rna;

    public void setcurruntPos(int curruntPos) {
        this.curruntPos = curruntPos;
    }
    public void setnewsDetail(String newsDetail) {
        this.newsDetail = newsDetail;
    }
    public void setnewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String[] newsTitles;
    public String[] newsDetails;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_news, container, false);
        pictureTemp = view.findViewById(R.id.pictureTemp);
        text_title = view.findViewById(R.id.text_title);
        text_detail = view.findViewById(R.id.text_detail);
        newsId = view.findViewById(R.id.newsId);
        text_title.setText(newsTitle);
        text_detail.setText(newsDetail);
        doPicturePut();
        Resources res =getResources();
        newsTitles =res.getStringArray(R.array.newsTitle);
        newsDetails =res.getStringArray(R.array.newsDetail);
        restNews = new ArrayList<>();
        for(int i=0;i<7;i++){
            if(i!=curruntPos){
                restNews.add(i);
            }
        }
        rna = new RelatedNewsAdapter(curruntPos,getContext(),restNews);
        LinearLayoutManager l = new LinearLayoutManager(getContext());
        newsId.setLayoutManager(l);
        rna.addOnRelatedNewsItemClickListener(this);
        newsId.setAdapter(rna);
        return view;
    }

    public void doPicturePut(){
        if(curruntPos == 0){
            pictureTemp.setBackgroundResource(R.drawable.pic1);
        }else if(curruntPos == 1){
            pictureTemp.setBackgroundResource(R.drawable.pic2);
        }else if(curruntPos == 2){
            pictureTemp.setBackgroundResource(R.drawable.pic3);
        }else if(curruntPos == 3){
            pictureTemp.setBackgroundResource(R.drawable.pic4);
        }else if(curruntPos == 4){
            pictureTemp.setBackgroundResource(R.drawable.pic5);
        }else if(curruntPos == 5){
            pictureTemp.setBackgroundResource(R.drawable.pic6);
        }else if(curruntPos == 6) {
            pictureTemp.setBackgroundResource(R.drawable.pic7);
        }
    }

    public void onRelatedNewsItemClick(View view, int position){
        curruntPos = restNews.get(position);
        text_title.setText(newsTitles[curruntPos]);
        text_detail.setText(newsDetails[curruntPos]);
        doPicturePut();
        restNews.clear();
        for(int i=1; i<=7 ;++i){
            if(i == curruntPos){
                continue;
            }else restNews.add(i);
        }
        rna.setCurrentIndex(curruntPos);
        rna.notifyDataSetChanged();


    }
}