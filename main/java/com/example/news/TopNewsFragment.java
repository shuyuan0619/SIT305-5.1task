package com.example.news;

import android.content.res.Resources;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import java.util.LinkedList;
import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.os.Build;

public class TopNewsFragment extends Fragment implements TopNewsAdapter.OnTopNewsItemClickListener, AllNewsAdapter.OnAllNewsItemClickListener{

    public TopNewsAdapter.OnTopNewsItemClickListener onTopNewsItemClickListener;
    public AllNewsAdapter.OnAllNewsItemClickListener onAllNewsItemClickListener;
    public List<Integer> datas;
    public List<String> Titles;
    public List<String> Details;
    public int nowUsedIdIndex = 0;
    public RecyclerView rvTopNews;
    public RecyclerView rvAllNews;
    public ImageView theFinalNew;
    public ImageView theNextNew;
    public TopNewsAdapter topNewsAdapter;
    public AllNewsAdapter allNewsAdapter;
    public LinearLayoutManager linearLayoutManager;
    public GridLayoutManager gridLayoutManager;

    public TopNewsFragment(TopNewsAdapter.OnTopNewsItemClickListener onTopNewsItemClickListener,AllNewsAdapter.OnAllNewsItemClickListener onAllNewsItemClickListener){
        this.onTopNewsItemClickListener = onTopNewsItemClickListener;
        this.onAllNewsItemClickListener = onAllNewsItemClickListener;
    }

    @Override
    public void onCreate(Bundle savedBundle) {
        super.onCreate(savedBundle);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedBundle) {
        View view = inflater.inflate(R.layout.fragment_top_news, container, false);
        rvTopNews = view.findViewById(R.id.rv_topNews);
        rvAllNews = view.findViewById(R.id.rv_allNews);
        theFinalNew = view.findViewById(R.id.theFinalNews);
        theNextNew = view.findViewById(R.id.theNextNews);

        //start rvTopNews
        datas = new LinkedList<>();
        for(int i=1 ;i<=7;i = i + 1){
            datas.add(i-1);
        }
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTopNews.setLayoutManager(linearLayoutManager);
        topNewsAdapter = new TopNewsAdapter(datas);
        topNewsAdapter.addOnTopNewsItemClickListener(onTopNewsItemClickListener);
        rvTopNews.setAdapter(topNewsAdapter);

        rvTopNews.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                nowUsedIdIndex = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            }
        });
        //start rvAllNews
        Titles = new LinkedList<>();
        Details = new LinkedList<>();
        Resources res =getResources();
        String[] newsTitle =res.getStringArray(R.array.newsTitle);
        String[] newsDetail =res.getStringArray(R.array.newsDetail);
        for(int i=0;i<7;i++){
            String tempStr = new String();
            if(newsTitle[i].length()<= 30)
            {
                Titles.add(newsTitle[i]);
            }
            else
            {
                tempStr = newsTitle[i].substring(0,29);
                tempStr = tempStr + ". .";
                Titles.add(tempStr);
            }
            if(newsDetail[i].length() <= 30)
            {
                Details.add(newsDetail[i]);
            }
            else
            {
                tempStr = newsDetail[i].substring(0,29);
                tempStr = tempStr + ". .";
                Details.add(tempStr);
            }
        }
        //end all news
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        rvAllNews.setLayoutManager(gridLayoutManager);
        allNewsAdapter = new AllNewsAdapter(Titles,Details);
        allNewsAdapter.addOnAllNewsItemClickListener(onAllNewsItemClickListener);
        rvAllNews.setAdapter(allNewsAdapter);
        //start last New
        theFinalNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nowUsedIdIndex>0){
                    ((LinearLayoutManager)rvTopNews.getLayoutManager()).scrollToPositionWithOffset(nowUsedIdIndex-1,0);
                }
            }
        });
        theNextNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nowUsedIdIndex<5){
                    ((LinearLayoutManager)rvTopNews.getLayoutManager()).scrollToPositionWithOffset(nowUsedIdIndex+1,0);
                }
            }
        });
        return view;

    }

    @Override
    public void onAllNewsItemClick(View view, int position) {
    }

    @Override
    public void onTopNewsItemClick(View view, int position) {
    }
}