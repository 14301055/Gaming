package com.image.indicator.activity;

import java.util.ArrayList;

import com.image.indicator.R;
import com.image.indicator.layout.SlideImageLayout;
import com.image.indicator.parser.NewsXmlParser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 头条新闻Activity
 * @Description: 头条新闻Activity

 * @File: TopicNews.java

 * @Package com.image.indicator.activity

 * @Author Hanyonglu

 * @Date 2012-6-18 上午08:24:36

 * @Version V1.0
 */
public class TopicNews extends Activity{
	// 滑动图片的集合
	private ArrayList<View> mImagePageViewList = null;
	private ViewGroup mMainView = null;
	private ViewPager mViewPager = null;
	// 当前ViewPager索引
//	private int pageIndex = 0; 
	
	// 包含圆点图片的View
	private ViewGroup mImageCircleView = null;
	private ImageView[] mImageCircleViews = null; 
	
	// 滑动标题
	private TextView mSlideTitle = null;
	
	// 布局设置类
	private SlideImageLayout mSlideLayout = null;
	// 数据解析类
	private NewsXmlParser mParser = null; 
	
	private ImageView m_pic_main;
	//记录哪一个游戏被点击了第一款是1 先左到右数，再上往下
	public static int GameOnTouch=0;
	public static void setGameOntouch(int i)
	{
		GameOnTouch = i;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Translucent_NoTitleBar);		
		// 初始化
		initeViews();
		//主界面，点游戏图片查看该游戏的所有相关比赛
		
		m_pic_main = (ImageView)findViewById(R.id.imageView_main);
		m_pic_main.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getX() >= 10 && event.getX() <= 140)
					if(event.getY() >= 30 && event.getY() <= 230)
				{
					Toast.makeText(getApplicationContext(),"荣耀范围",Toast.LENGTH_SHORT).show();
					GameOnTouch = 1;
				//	startActivityForResult(new Intent(TopicNews.this, InfoNews.class),  1);
				}
				if(event.getX() >= 190 && event.getX() <= 320)
					if(event.getY() >= 30 && event.getY() <= 230)
				{
					Toast.makeText(getApplicationContext(),"英雄联盟",Toast.LENGTH_SHORT).show();
					GameOnTouch = 2;
				//	startActivityForResult(new Intent(TopicNews.this, InfoNews.class),  1);
				}

				if(event.getX() >= 370 && event.getX() <= 500)
					if(event.getY() >= 30 && event.getY() <= 230)
				{
				//	Toast.makeText(getApplicationContext(),"这个范围是炉石传说",Toast.LENGTH_SHORT).show();
					GameOnTouch = 3;
					startActivityForResult(new Intent(TopicNews.this, InfoNews.class),  1);
				}
				if(event.getX() >= 550 && event.getX() <= 680)
					if(event.getY() >= 30 && event.getY() <= 230)
				{
				//	Toast.makeText(getApplicationContext(),"这个范围是DOTA2",Toast.LENGTH_SHORT).show();
					GameOnTouch = 4;
					startActivityForResult(new Intent(TopicNews.this, InfoNews.class),  1);
				}

				return false;
			}
		});
		/*
		m_pic_main.setOnClickListener(new View.OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				//MotionEvent MotionEvent event;
			//	m_pic_main.onTouchEvent(MotionEvent event);
				if( m_pic_main.getX() >=100)
					startActivityForResult(new Intent(TopicNews.this, InfoNews.class),  1);
				else
					Toast.makeText(getApplicationContext(),"功能尚在制作中",Toast.LENGTH_SHORT).show();
			}
		});
		*/
	}
	
	/**
	 * 初始化
	 */
	private void initeViews(){
		// 滑动图片区域
		mImagePageViewList = new ArrayList<View>();
		LayoutInflater inflater = getLayoutInflater();  
		mMainView = (ViewGroup)inflater.inflate(R.layout.page_topic_news, null);
		mViewPager = (ViewPager) mMainView.findViewById(R.id.image_slide_page);  
		
		// 圆点图片区域
		mParser = new NewsXmlParser();
		int length = mParser.getSlideImages().length;
		mImageCircleViews = new ImageView[length];
		mImageCircleView = (ViewGroup) mMainView.findViewById(R.id.layout_circle_images);
		mSlideLayout = new SlideImageLayout(TopicNews.this);
		mSlideLayout.setCircleImageLayout(length);
		
		for(int i = 0; i < length; i++){
			mImagePageViewList.add(mSlideLayout.getSlideImageLayout(mParser.getSlideImages()[i]));
			mImageCircleViews[i] = mSlideLayout.getCircleImageLayout(i);
			mImageCircleView.addView(mSlideLayout.getLinearLayout(mImageCircleViews[i], 10, 10));
		}
		
		// 设置默认的滑动标题
		mSlideTitle = (TextView) mMainView.findViewById(R.id.tvSlideTitle);
		mSlideTitle.setText(mParser.getSlideTitles()[0]);
		
		setContentView(mMainView);
		
		// 设置ViewPager
        mViewPager.setAdapter(new SlideImageAdapter());  
        mViewPager.setOnPageChangeListener(new ImagePageChangeListener());
	}
	
	// 滑动图片数据适配器
    private class SlideImageAdapter extends PagerAdapter {  
        @Override  
        public int getCount() { 
            return mImagePageViewList.size();  
        }  
  
        @Override  
        public boolean isViewFromObject(View view, Object object) {  
            return view == object;  
        }  
  
        @Override  
        public int getItemPosition(Object object) {  
            return super.getItemPosition(object);  
        }  
  
        @Override  
        public void destroyItem(View view, int arg1, Object arg2) {  
            ((ViewPager) view).removeView(mImagePageViewList.get(arg1));  
        }  
  
        @Override  
        public Object instantiateItem(View view, int position) {  
        	((ViewPager) view).addView(mImagePageViewList.get(position));
            
            return mImagePageViewList.get(position);  
        }  
  
        @Override  
        public void restoreState(Parcelable arg0, ClassLoader arg1) {  
  
        }  
  
        @Override  
        public Parcelable saveState() {  
            return null;  
        }  
  
        @Override  
        public void startUpdate(View arg0) {  
        }  
  
        @Override  
        public void finishUpdate(View arg0) {  
        }  
    }
    
    // 滑动页面更改事件监听器
    private class ImagePageChangeListener implements OnPageChangeListener {
        @Override  
        public void onPageScrollStateChanged(int arg0) {  
        }  
  
        @Override  
        public void onPageScrolled(int arg0, float arg1, int arg2) {  
        }  
  
        @Override  
        public void onPageSelected(int index) {  
//        	pageIndex = index;
        	mSlideLayout.setPageIndex(index);
        	mSlideTitle.setText(mParser.getSlideTitles()[index]);
        	
            for (int i = 0; i < mImageCircleViews.length; i++) {  
            	mImageCircleViews[index].setBackgroundResource(R.drawable.dot_selected);
                
                if (index != i) {  
                	mImageCircleViews[i].setBackgroundResource(R.drawable.dot_none);  
                }  
            }
        }  
    }
    public static int getGameOnTouch()
    {
    	return GameOnTouch;
    }
}
