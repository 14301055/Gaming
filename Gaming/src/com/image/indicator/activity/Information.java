package com.image.indicator.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.image.indicator.R;
import com.image.indicator.entity.ListViewItemData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Information extends Activity {
	private ListView mListView;
	private SimpleAdapter simpleAdapter;
	public static int listView_position=0;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_info_news);
		TextView mBlogItem = (TextView) findViewById(R.id.tv_netease_top2);
		mBlogItem.setText("--消息列表");
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
				startActivityForResult(new Intent(Information.this, DetailContest.class),  1); 
			}
		});
	}
    public ArrayList<HashMap<String, Object>> getItem() {
    	TopicNews.setGameOntouch(0);  //标记为消息页
        ArrayList<HashMap<String, Object>> item = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < ListViewItemData.getItemNum(); i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemTitle", ListViewItemData.getTitle(i,0));
            map.put("itemPhoto", ListViewItemData.getPhotoResId(i,0));
            map.put("itemSummary", ListViewItemData.getSummary(i,0));
            map.put("itemAuthor", ListViewItemData.getAuthor(i,0));
            map.put("itemPublishtime", ListViewItemData.getPublishtime(i,0));
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
