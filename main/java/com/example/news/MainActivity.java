package com.example.news;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public Context context;
    public FrameLayout frameLayout;
    public FragmentManager fragmentManager;
    public String[] newsTitle;
    public String[] newsDetail;
    public FragmentTransaction fragmentTransaction;
    public TopNewsFragment topFragment;
    public DetailNewsFragment detailNewsFragment;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.purple_700));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar SupportA = getSupportActionBar();
        if(null != SupportA){
            SupportA.hide();
        }
        Resources resourcesGet = getResources();
        newsTitle =resourcesGet.getStringArray(R.array.newsTitle);
        newsDetail =resourcesGet.getStringArray(R.array.newsDetail);
        context = MainActivity.this;
        fragmentManager = getSupportFragmentManager();
        topFragment = new TopNewsFragment(new TopNewsAdapter.OnTopNewsItemClickListener() {
            public void onTopNewsItemClick(View view, int position) {
                Log.e("TAG", "onTopNewsItemClick: "+newsTitle[position]);
                detailNewsFragment = new DetailNewsFragment();
                detailNewsFragment.setnewsDetail(newsDetail[position]);
                detailNewsFragment.setnewsTitle(newsTitle[position]);
                detailNewsFragment.setcurruntPos(position);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fl_content,detailNewsFragment);
                fragmentTransaction.hide(topFragment);
                fragmentTransaction.show(detailNewsFragment);
                fragmentTransaction.commit();
            }
        }, new AllNewsAdapter.OnAllNewsItemClickListener() {
            @Override
            public void onAllNewsItemClick(View view, int position) {
                Log.e("TAG", " onTopNewsItemClick: "+newsDetail[position]);
            }
        });
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_content,topFragment);
        fragmentTransaction.commit();
    }
}

