package com.image.indicator.activity;
import com.image.indicator.entity.ListViewItemData;
import java.util.ArrayList;
import java.util.HashMap;

import com.image.indicator.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 * 资讯Activity
 * @Description: 资讯Activity

 * @File: InfoNews.java

 * @Package com.image.indicator.activity

 * @Author Hanyonglu

 * @Date 2012-6-18 上午08:27:30

 * @Version V1.0
 */
public class InfoNews extends Activity{
	private ListView mListView;
	private SimpleAdapter simpleAdapter;
	public static int listView_position=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_info_news);
		
		mListView = (ListView)this.findViewById(R.id.listview1);
		
		simpleAdapter =new SimpleAdapter(this,this.getItem(),R.layout.page_listview_news,
				new String[] {"itemTitle","itemPhoto", "itemSummary", "itemAuthor", "itemPublishtime"},
				new int[] {R.id.m_title1, R.id.m_imageView, R.id.summary, R.id.m_author, R.id.m_deadLine});
		
		mListView.setAdapter(simpleAdapter);
		
		mListView.setOnItemClickListener(new ListView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?>parent,View view,int position,long id) {
				// TODO Auto-generated method stub
				listView_position = position;
				startActivityForResult(new Intent(InfoNews.this, DetailContest.class),  1); 
			}
		});
	}
    public ArrayList<HashMap<String, Object>> getItem() {
        ArrayList<HashMap<String, Object>> item = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < ListViewItemData.getItemNum(); i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemTitle", ListViewItemData.getTitle(i,TopicNews.GameOnTouch));
            map.put("itemPhoto", ListViewItemData.getPhotoResId(i,TopicNews.GameOnTouch));
            map.put("itemSummary", ListViewItemData.getSummary(i,TopicNews.GameOnTouch));
            map.put("itemAuthor", ListViewItemData.getAuthor(i,TopicNews.GameOnTouch));
            map.put("itemPublishtime", ListViewItemData.getPublishtime(i,TopicNews.GameOnTouch));
            item.add(map);
        }
        return item;
    }
    //记录哪一个list被点击了
    public static int getListViewPostion()
    {
    	return listView_position;
    }
}
