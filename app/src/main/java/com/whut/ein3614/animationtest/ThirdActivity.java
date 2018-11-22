package com.whut.ein3614.animationtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {

    private ListView mListView;
    private List<String> data = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mListView = findViewById(R.id.list_view);
        initData();
    }

    /**
     * 初始化listview数据
     */
    private void initData() {
        for (int i = 0; i < 50; i++){
            data.add("item "+i);
        }
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,data);
        mListView.setAdapter(adapter);
    }
}
