package com.image.indicator.activity;

import com.image.indicator.R;
import com.image.indicator.entity.DatabaseHelper;
import com.image.indicator.utility.DimensionUtility;
import com.image.indicator.utility.ImageAnimation;
import android.app.ActivityGroup;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

/**
 * Android实现局部图片滑动指引效果
 * @Description: 实现以下功能：
 * 1、顶部单张图片左右拖拉滑动；
 * 2、带指引；
 * 3、仅滑动顶部单张图片，不滑动页面，下面的图文内容不动； 
 * 4、类似于新闻客户端的功能

 * @File: MainActivity.java

 * @Package com.image.indicator

 * @Author Hanyonglu  
 * @author lilu Modified the code at 2012-11-7

 * @Date 2012-6-17 下午11:28:43

 * @Version V1.0
 */
@SuppressWarnings("deprecation")
public class MainActivity extends ActivityGroup implements OnClickListener, OnCheckedChangeListener{
	// 选中的新闻条目
	private TextView mSelectedItem = null;
	// 头部新闻条目的Layout
	private RelativeLayout mHeader = null;
	// 中间新闻主体的Layout
	private RelativeLayout mNewsMainLayout = null;
	private LayoutParams params = null;
	//顶部提示
	private TextView mNetEaseTop = null;
	// 新闻分类
	private TextView mNewsItem = null;
	private TextView mInfoItem = null;
	private TextView mBlogItem = null;
	private TextView mMagezineItem = null;
	private TextView mDomainItem = null;
	private TextView mMoreItem = null;
	
	// 新闻分类中每条分类的宽度
	private int mItemWidth = 0;
	// 条目背景移动开始位置
	private int startX = 0;
	private Intent mIntent = null;
	// 设置新闻主题
	private View mNewsMain = null;
	private RadioGroup mRadioGroup;
	//底部选中标志位的ImageView
	private ImageView mImageView;
	//底部Layout
	private RelativeLayout mButtomLayout;
	int startLeft;
	public static DatabaseHelper dbp;
	private  Cursor c1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);      
        // 初始化控件
        
        dbp = new DatabaseHelper(this);
        
        dbp.insert(3,1,"YF咖啡杯 炉石传说比赛", "报名截止：5月17号 12：00","比赛时间：5/18-5/18","地点：北京交通大学YF三楼",
				"举办方：YF咖啡", "总奖金：2000元", "报名要求：排名模式20级以上",
				"比赛规则：征服3，输一场进入败者组，输两场淘汰","热评：炉石传说好好玩", "热评：太棒棒了！");
        dbp.insert(3,2,"烤肉拌饭杯 炉石传说比赛", "报名截止：6月18号 12：00","比赛时间：6/18-6/18","地点：北京交通大学东门，绿色营地网吧",
				"举办方：下沉广场烤肉拌饭", "总奖金：1000元", "报名要求：排名模式5级以上",
				"比赛规则：征服4，瑞士轮，积分前8进入淘汰赛","热评：炉石传说真的好好玩", "热评：等不及啦");
        dbp.insert(3,3,"隔壁老王杯 炉石传说比赛", "报名截止：7月18号 12：00","比赛时间：7/18-7/18","地点：学活小剧场",
				"举办方：隔壁王胖胖", "总奖金：500元", "报名要求：拥有传说卡背",
				"比赛规则：一个卡组，单淘","热评：单淘最刺激", "热评：王胖胖好棒棒");
        
        dbp.insert(4,1,"肯德基全家桶杯 DOTA2比赛", "报名截止：9月17号 12：00","比赛时间：9/18-9/18","地点：线上赛（任何地方）",
				"举办方：肯德基", "总奖金：100000元", "报名要求：5人",
				"比赛规则：CW模式，3轮瑞士轮，前4胜者组，5-8败者组，9-淘汰","热评：拿大鱼小狗就赢了", "热评：充值使你更强大");
        dbp.insert(4,2,"麦当劳杯 DOTA2比赛", "报名截止：10月17号 12：00","比赛时间：10/18-10/18","地点：线上赛（任何地方）",
				"举办方：麦当劳", "总奖金：200000元", "报名要求：5人",
				"比赛规则：RD模式，3轮瑞士轮，前4胜者组，5-8败者组，9-淘汰","热评：拿小鱼小狗就赢了", "热评：充值使你更强大");
        dbp.insert(4,3,"德克士杯 DOTA2比赛", "报名截止：11月17号 12：00","比赛时间：11/18-11/18","地点：线上赛（任何地方）",
				"举办方：德克士", "总奖金：300000元", "报名要求：5人",
				"比赛规则：AP模式，3轮瑞士轮，前4胜者组，5-8败者组，9-淘汰","热评：拿大鱼大狗就赢了", "热评：充值使你更强大");
        dbp.insert(4,4,"塞上客杯 DOTA2比赛", "报名截止：12月17号 12：00","比赛时间：12/18-12/18","地点：线上赛（任何地方）",
				"举办方：塞上客", "总奖金：400000元", "报名要求：5人",
				"比赛规则：SOLO模式，中单SF solo","热评：拿小鱼大狗就赢了", "热评：充值使你更强大");
        
        dbp.insert(0,1,"YF咖啡杯 炉石传说比赛 报名成功！", "状态：等待参赛","比赛时间：5/18-5/18","地点：北京交通大学YF三楼",
				"举办方：YF咖啡", "总奖金：2000元", "提示：你已报名成功，记得按时参赛",
				"比赛规则：征服3，输一场进入败者组，输两场淘汰","热评：炉石传说好好玩", "热评：太棒棒了！");
        /*       c1 = dbp.getById("1");
        int i=0;
        String name=null;
        while(c1.moveToNext())
        {
        	name = c1.getString(1);
        	i++;
        }
        System.out.println(name);
        */
       // System.out.println(c1.getString(0));
      //  System.out.println(dbp.getById("abc").getString(dbp.getById("abc").getColumnIndex("Id")));
        initeViews();
    }
    
    /**
     * 初始化控件
     */
    private void initeViews(){
    	mNewsItem = (TextView) findViewById(R.id.tv_title_news);
    	mInfoItem = (TextView) findViewById(R.id.tv_title_info);
    	mBlogItem = (TextView) findViewById(R.id.tv_title_blog);
    	mMagezineItem = (TextView) findViewById(R.id.tv_title_magazine);
    	mDomainItem = (TextView) findViewById(R.id.tv_title_domain);
    	mMoreItem = (TextView) findViewById(R.id.tv_title_more);
    	
    	mRadioGroup = (RadioGroup)findViewById(R.id.radiogroup);
    	mRadioGroup.setOnCheckedChangeListener(this);
    	mButtomLayout = (RelativeLayout) findViewById(R.id.layout_bottom);
    	
    	mNewsItem.setOnClickListener(this);
    	mInfoItem.setOnClickListener(this);
    	mBlogItem.setOnClickListener(this);
    	mMagezineItem.setOnClickListener(this);
    	mDomainItem.setOnClickListener(this);
    	mMoreItem.setOnClickListener(this);

    	// 设置选中条目属性
    	mSelectedItem = new TextView(this);
    	mSelectedItem.setText(R.string.title_news_category_tops);
    	mSelectedItem.setTextColor(Color.WHITE);
    	mSelectedItem.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
    	mSelectedItem.setGravity(Gravity.CENTER);
    	mSelectedItem.setWidth((getScreenWidth() - DimensionUtility.dip2px(this, 20)) / 6);
    	mSelectedItem.setBackgroundResource(R.drawable.slidebar);
    	RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(
    			LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    	param.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
    	
    	mHeader = (RelativeLayout) findViewById(R.id.layout_title_bar);
    	mNetEaseTop = (TextView) findViewById(R.id.tv_netease_top);
    	
    	mHeader.addView(mSelectedItem, param);
    	
    	// 设置头条新闻主体
    	mIntent = new Intent(MainActivity.this, TopicNews.class);
    	mNewsMain = getLocalActivityManager().startActivity(
    			"TopicNews", mIntent).getDecorView();
    	params = new LayoutParams(
    			LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    	mNewsMainLayout = (RelativeLayout) findViewById(R.id.layout_news_main);
    	mNewsMainLayout.addView(mNewsMain, params);
    	
    	//设置底部选中项
    	
    	mImageView = new ImageView(this);
    	mImageView.setImageResource(R.drawable.tab_front_bg);
    	mButtomLayout.addView(mImageView);
    }
    
    /**
     * 获取屏幕的宽度
     * @return
     */
    private int getScreenWidth(){
    	WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
//		Point point = new Point();
//		display.getSize(point);
//		int screenWidth = point.x; 
		int screenWidth = display.getWidth();
		return screenWidth;
    }
    
    // 新闻分类事件监听
/*	@Override
	public void onClick(View v) {
		mItemWidth = findViewById(R.id.layout).getWidth();
		
		switch (v.getId()) {
		case R.id.tv_title_news:
			//动画滑动
			ImageAnimation.SetImageSlide(mSelectedItem, startX, 0, 0, 0);
			//设置滑动后动画开始位置
			startX = 0;
			//设置选中项显示文字，也就是高亮部分文字
			mSelectedItem.setText(R.string.title_news_category_tops);
			//设置左上角提示文字
		//	mNetEaseTop.setText(R.string.title_news_category_tops);
			
			// 显示头条信息
			mIntent.setClass(MainActivity.this, TopicNews.class);
			mNewsMain = getLocalActivityManager().startActivity(
	    			"TopicNews", mIntent).getDecorView();
			break;
		case R.id.tv_title_info:
			ImageAnimation.SetImageSlide(mSelectedItem, startX, mItemWidth, 0, 0);
			startX = mItemWidth;
			mSelectedItem.setText(R.string.title_news_category_info);
		//	mNetEaseTop.setText(R.string.title_news_category_info);
			
			// 显示资讯信息
			mIntent.setClass(MainActivity.this, InfoNews.class);
			mNewsMain = getLocalActivityManager().startActivity(
	    			"InfoNews", mIntent).getDecorView();
			break;
		case R.id.tv_title_blog:
			ImageAnimation.SetImageSlide(mSelectedItem, startX, mItemWidth * 2, 0, 0);
			startX = mItemWidth * 2;
			mSelectedItem.setText(R.string.title_news_category_blog);
		//	mNetEaseTop.setText(R.string.title_news_category_blog);
			
			// 显示博客信息
			mIntent.setClass(MainActivity.this, BlogNews.class);
			mNewsMain = getLocalActivityManager().startActivity(
	    			"BlogNews", mIntent).getDecorView();
			break;
		case R.id.tv_title_magazine:
			ImageAnimation.SetImageSlide(mSelectedItem, startX, mItemWidth * 3, 0, 0);
			startX = mItemWidth * 3;
			mSelectedItem.setText(R.string.title_news_category_magazine);
		//	mNetEaseTop.setText(R.string.title_news_category_magazine);
			
			// 显示杂志信息
			mIntent.setClass(MainActivity.this, MagazineNews.class);
			mNewsMain = getLocalActivityManager().startActivity(
	    			"MagazineNews", mIntent).getDecorView();
			break;
		case R.id.tv_title_domain:
			ImageAnimation.SetImageSlide(mSelectedItem, startX, mItemWidth * 4, 0, 0);
			startX = mItemWidth * 4;
			mSelectedItem.setText(R.string.title_news_category_domain);
		//	mNetEaseTop.setText(R.string.title_news_category_domain);
			// 显示业界信息
			mIntent.setClass(MainActivity.this, DomainNews.class);
			mNewsMain = getLocalActivityManager().startActivity(
	    			"DomainNews", mIntent).getDecorView();
			break;
		case R.id.tv_title_more:
			ImageAnimation.SetImageSlide(mSelectedItem, startX, mItemWidth * 5, 0, 0);
			startX = mItemWidth * 5;
			mSelectedItem.setText(R.string.title_news_category_more);
		//	mNetEaseTop.setText(R.string.title_news_category_more);
			
			// 显示更多信息
			mIntent.setClass(MainActivity.this, MoreNews.class);
			mNewsMain = getLocalActivityManager().startActivity(
	    			"MoreNews", mIntent).getDecorView();
			break;
		default:
			break;
		}		
		// 更换Layout中的新闻主体
		mNewsMainLayout.removeAllViews();
		mNewsMainLayout.addView(mNewsMain, params);
	}
*/
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		switch (checkedId) {
		case R.id.radio_news:
			ImageAnimation.SetImageSlide(mImageView, startLeft, 0, 0, 0);
			startLeft = 0;
			break;
		case R.id.radio_topic:
			ImageAnimation.SetImageSlide(mImageView, startLeft, mImageView.getWidth(), 0, 0);
			startLeft = mImageView.getWidth();
			startActivityForResult(new Intent(MainActivity.this, Information.class),  1);
			break;
		case R.id.radio_pic:
			ImageAnimation.SetImageSlide(mImageView, startLeft, mImageView.getWidth() * 2, 0, 0);
			startLeft = mImageView.getWidth() * 2;
			break;
		case R.id.radio_follow:
			ImageAnimation.SetImageSlide(mImageView, startLeft, mImageView.getWidth() * 3, 0, 0);
			startLeft = mImageView.getWidth() * 3;
			break;
		case R.id.radio_vote:
			ImageAnimation.SetImageSlide(mImageView, startLeft, mImageView.getWidth() * 4, 0, 0);
			startLeft = mImageView.getWidth() * 4;
			
			startActivityForResult(new Intent(MainActivity.this, UserZoom.class),  1);
			break;

		default:
			break;
		}
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
	}