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
 * Androidʵ�־ֲ�ͼƬ����ָ��Ч��
 * @Description: ʵ�����¹��ܣ�
 * 1����������ͼƬ��������������
 * 2����ָ����
 * 3����������������ͼƬ��������ҳ�棬�����ͼ�����ݲ����� 
 * 4�����������ſͻ��˵Ĺ���

 * @File: MainActivity.java

 * @Package com.image.indicator

 * @Author Hanyonglu  
 * @author lilu Modified the code at 2012-11-7

 * @Date 2012-6-17 ����11:28:43

 * @Version V1.0
 */
@SuppressWarnings("deprecation")
public class MainActivity extends ActivityGroup implements OnClickListener, OnCheckedChangeListener{
	// ѡ�е�������Ŀ
	private TextView mSelectedItem = null;
	// ͷ��������Ŀ��Layout
	private RelativeLayout mHeader = null;
	// �м����������Layout
	private RelativeLayout mNewsMainLayout = null;
	private LayoutParams params = null;
	//������ʾ
	private TextView mNetEaseTop = null;
	// ���ŷ���
	private TextView mNewsItem = null;
	private TextView mInfoItem = null;
	private TextView mBlogItem = null;
	private TextView mMagezineItem = null;
	private TextView mDomainItem = null;
	private TextView mMoreItem = null;
	
	// ���ŷ�����ÿ������Ŀ��
	private int mItemWidth = 0;
	// ��Ŀ�����ƶ���ʼλ��
	private int startX = 0;
	private Intent mIntent = null;
	// ������������
	private View mNewsMain = null;
	private RadioGroup mRadioGroup;
	//�ײ�ѡ�б�־λ��ImageView
	private ImageView mImageView;
	//�ײ�Layout
	private RelativeLayout mButtomLayout;
	int startLeft;
	public static DatabaseHelper dbp;
	private  Cursor c1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);      
        // ��ʼ���ؼ�
        
        dbp = new DatabaseHelper(this);
        
        dbp.insert(3,1,"YF���ȱ� ¯ʯ��˵����", "������ֹ��5��17�� 12��00","����ʱ�䣺5/18-5/18","�ص㣺������ͨ��ѧYF��¥",
				"�ٰ췽��YF����", "�ܽ���2000Ԫ", "����Ҫ������ģʽ20������",
				"������������3����һ����������飬��������̭","������¯ʯ��˵�ú���", "������̫�����ˣ�");
        dbp.insert(3,2,"����跹�� ¯ʯ��˵����", "������ֹ��6��18�� 12��00","����ʱ�䣺6/18-6/18","�ص㣺������ͨ��ѧ���ţ���ɫӪ������",
				"�ٰ췽���³��㳡����跹", "�ܽ���1000Ԫ", "����Ҫ������ģʽ5������",
				"������������4����ʿ�֣�����ǰ8������̭��","������¯ʯ��˵��ĺú���", "�������Ȳ�����");
        dbp.insert(3,3,"���������� ¯ʯ��˵����", "������ֹ��7��18�� 12��00","����ʱ�䣺7/18-7/18","�ص㣺ѧ��С�糡",
				"�ٰ췽������������", "�ܽ���500Ԫ", "����Ҫ��ӵ�д�˵����",
				"��������һ�����飬����","������������̼�", "�����������ֺð���");
        
        dbp.insert(4,1,"�ϵ»�ȫ��Ͱ�� DOTA2����", "������ֹ��9��17�� 12��00","����ʱ�䣺9/18-9/18","�ص㣺���������κεط���",
				"�ٰ췽���ϵ»�", "�ܽ���100000Ԫ", "����Ҫ��5��",
				"��������CWģʽ��3����ʿ�֣�ǰ4ʤ���飬5-8�����飬9-��̭","�������ô���С����Ӯ��", "��������ֵʹ���ǿ��");
        dbp.insert(4,2,"���ͱ� DOTA2����", "������ֹ��10��17�� 12��00","����ʱ�䣺10/18-10/18","�ص㣺���������κεط���",
				"�ٰ췽������", "�ܽ���200000Ԫ", "����Ҫ��5��",
				"��������RDģʽ��3����ʿ�֣�ǰ4ʤ���飬5-8�����飬9-��̭","��������С��С����Ӯ��", "��������ֵʹ���ǿ��");
        dbp.insert(4,3,"�¿�ʿ�� DOTA2����", "������ֹ��11��17�� 12��00","����ʱ�䣺11/18-11/18","�ص㣺���������κεط���",
				"�ٰ췽���¿�ʿ", "�ܽ���300000Ԫ", "����Ҫ��5��",
				"��������APģʽ��3����ʿ�֣�ǰ4ʤ���飬5-8�����飬9-��̭","�������ô���󹷾�Ӯ��", "��������ֵʹ���ǿ��");
        dbp.insert(4,4,"���Ͽͱ� DOTA2����", "������ֹ��12��17�� 12��00","����ʱ�䣺12/18-12/18","�ص㣺���������κεط���",
				"�ٰ췽�����Ͽ�", "�ܽ���400000Ԫ", "����Ҫ��5��",
				"��������SOLOģʽ���е�SF solo","��������С��󹷾�Ӯ��", "��������ֵʹ���ǿ��");
        
        dbp.insert(0,1,"YF���ȱ� ¯ʯ��˵���� �����ɹ���", "״̬���ȴ�����","����ʱ�䣺5/18-5/18","�ص㣺������ͨ��ѧYF��¥",
				"�ٰ췽��YF����", "�ܽ���2000Ԫ", "��ʾ�����ѱ����ɹ����ǵð�ʱ����",
				"������������3����һ����������飬��������̭","������¯ʯ��˵�ú���", "������̫�����ˣ�");
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
     * ��ʼ���ؼ�
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

    	// ����ѡ����Ŀ����
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
    	
    	// ����ͷ����������
    	mIntent = new Intent(MainActivity.this, TopicNews.class);
    	mNewsMain = getLocalActivityManager().startActivity(
    			"TopicNews", mIntent).getDecorView();
    	params = new LayoutParams(
    			LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    	mNewsMainLayout = (RelativeLayout) findViewById(R.id.layout_news_main);
    	mNewsMainLayout.addView(mNewsMain, params);
    	
    	//���õײ�ѡ����
    	
    	mImageView = new ImageView(this);
    	mImageView.setImageResource(R.drawable.tab_front_bg);
    	mButtomLayout.addView(mImageView);
    }
    
    /**
     * ��ȡ��Ļ�Ŀ��
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
    
    // ���ŷ����¼�����
/*	@Override
	public void onClick(View v) {
		mItemWidth = findViewById(R.id.layout).getWidth();
		
		switch (v.getId()) {
		case R.id.tv_title_news:
			//��������
			ImageAnimation.SetImageSlide(mSelectedItem, startX, 0, 0, 0);
			//���û����󶯻���ʼλ��
			startX = 0;
			//����ѡ������ʾ���֣�Ҳ���Ǹ�����������
			mSelectedItem.setText(R.string.title_news_category_tops);
			//�������Ͻ���ʾ����
		//	mNetEaseTop.setText(R.string.title_news_category_tops);
			
			// ��ʾͷ����Ϣ
			mIntent.setClass(MainActivity.this, TopicNews.class);
			mNewsMain = getLocalActivityManager().startActivity(
	    			"TopicNews", mIntent).getDecorView();
			break;
		case R.id.tv_title_info:
			ImageAnimation.SetImageSlide(mSelectedItem, startX, mItemWidth, 0, 0);
			startX = mItemWidth;
			mSelectedItem.setText(R.string.title_news_category_info);
		//	mNetEaseTop.setText(R.string.title_news_category_info);
			
			// ��ʾ��Ѷ��Ϣ
			mIntent.setClass(MainActivity.this, InfoNews.class);
			mNewsMain = getLocalActivityManager().startActivity(
	    			"InfoNews", mIntent).getDecorView();
			break;
		case R.id.tv_title_blog:
			ImageAnimation.SetImageSlide(mSelectedItem, startX, mItemWidth * 2, 0, 0);
			startX = mItemWidth * 2;
			mSelectedItem.setText(R.string.title_news_category_blog);
		//	mNetEaseTop.setText(R.string.title_news_category_blog);
			
			// ��ʾ������Ϣ
			mIntent.setClass(MainActivity.this, BlogNews.class);
			mNewsMain = getLocalActivityManager().startActivity(
	    			"BlogNews", mIntent).getDecorView();
			break;
		case R.id.tv_title_magazine:
			ImageAnimation.SetImageSlide(mSelectedItem, startX, mItemWidth * 3, 0, 0);
			startX = mItemWidth * 3;
			mSelectedItem.setText(R.string.title_news_category_magazine);
		//	mNetEaseTop.setText(R.string.title_news_category_magazine);
			
			// ��ʾ��־��Ϣ
			mIntent.setClass(MainActivity.this, MagazineNews.class);
			mNewsMain = getLocalActivityManager().startActivity(
	    			"MagazineNews", mIntent).getDecorView();
			break;
		case R.id.tv_title_domain:
			ImageAnimation.SetImageSlide(mSelectedItem, startX, mItemWidth * 4, 0, 0);
			startX = mItemWidth * 4;
			mSelectedItem.setText(R.string.title_news_category_domain);
		//	mNetEaseTop.setText(R.string.title_news_category_domain);
			// ��ʾҵ����Ϣ
			mIntent.setClass(MainActivity.this, DomainNews.class);
			mNewsMain = getLocalActivityManager().startActivity(
	    			"DomainNews", mIntent).getDecorView();
			break;
		case R.id.tv_title_more:
			ImageAnimation.SetImageSlide(mSelectedItem, startX, mItemWidth * 5, 0, 0);
			startX = mItemWidth * 5;
			mSelectedItem.setText(R.string.title_news_category_more);
		//	mNetEaseTop.setText(R.string.title_news_category_more);
			
			// ��ʾ������Ϣ
			mIntent.setClass(MainActivity.this, MoreNews.class);
			mNewsMain = getLocalActivityManager().startActivity(
	    			"MoreNews", mIntent).getDecorView();
			break;
		default:
			break;
		}		
		// ����Layout�е���������
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