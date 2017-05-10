package com.image.indicator.activity;


import com.image.indicator.R;
import com.image.indicator.utility.ImageAnimation;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 杂志Activity
 * @Description: 杂志Activity

 * @File: MagazineNews.java

 * @Package com.image.indicator.activity

 * @Author Hanyonglu

 * @Date 2012-6-18 上午08:26:02

 * @Version V1.0
 */
public class MagazineNews extends Activity{
	private TextView mSelectedItem = null;
	private int startX = 0;
	private int mItemWidth = 0;
	private View mNewsMain = null;
	private TextView mNetEaseTop = null;
	private Intent mIntent = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_magazine_news);
		
		final Button button_ceshi1 = (Button) findViewById(R.id.button_ceshi1);
		button_ceshi1.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Toast.makeText(getApplicationContext(),"提示内容11111",Toast.LENGTH_SHORT).show();
			//	Intent m_intent = new Intent(MagazineNews.this,DetailContest.class);
			//	m_intent.setClass(MagazineNews.this,DetailContest.class);
			//	MagazineNews.this.startActivity(m_intent);
			//	MagazineNews.this.finish();
		//		ImageAnimation.SetImageSlide(mSelectedItem, startX, mItemWidth * 2, 0, 0);
		//		startX = mItemWidth * 2;
		//		mSelectedItem.setText(R.string.title_news_category_blog);
		//		mNetEaseTop.setText(R.string.title_news_category_blog);
				startActivityForResult(new Intent(MagazineNews.this, DetailContest.class),  1); 
				// 显示博客信息
		//		mIntent.setClass(MagazineNews.this, BlogNews.class);
		//		mNewsMain = getLocalActivityManager().startActivity(
		//    			"BlogNews", mIntent).getDecorView();
			}
		});
	}
}
