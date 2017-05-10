package com.image.indicator.activity;

import com.image.indicator.R;
import com.image.indicator.entity.ContestDetailData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailContest extends Activity {
	private String result;
	private TextView text_id;
	private TextView text_deadline;
	private TextView text_data;
	private TextView text_address;
	private TextView text_host;
	private TextView text_request;
	private TextView text_rule;
	private TextView text_award;
	private TextView text_review1;
	private TextView text_review2;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.page_detail_contest);
		//text_id = new TextView(this);
		text_id = (TextView) findViewById(R.id.textView_detail_id);
		text_deadline = (TextView) findViewById(R.id.textView_detail_deadline);
		text_data = (TextView) findViewById(R.id.textView_detail_data);
		text_address = (TextView) findViewById(R.id.textView_detail_address);
		text_host = (TextView) findViewById(R.id.textView_detail_host);
		text_request = (TextView) findViewById(R.id.textView_detail_request);
		text_rule = (TextView) findViewById(R.id.textView_detail_rule);
		text_award = (TextView) findViewById(R.id.textView_detail_award);
		text_review1 = (TextView) findViewById(R.id.textView_detail_review_1);
		text_review2 = (TextView) findViewById(R.id.textView_detail_review_2);
		intialContestDetaill(InfoNews.listView_position); //加载比赛详情
		
		final Button button_detail_1 = (Button) findViewById(R.id.button_detail_1);
		button_detail_1.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Toast.makeText(getApplicationContext(),"报名成功",Toast.LENGTH_SHORT).show();
			}
		});
		final Button button_detail_2 = (Button) findViewById(R.id.button_detail_2);
		button_detail_2.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Toast.makeText(getApplicationContext(),"功能尚在制作中",Toast.LENGTH_SHORT).show();
			}
		});
		
	}
	public void intialContestDetaill(int i)
	{
		text_id.setText(ContestDetailData.getId(i,TopicNews.GameOnTouch));
		text_data.setText(ContestDetailData.getData(i,TopicNews.GameOnTouch));
		text_deadline.setText(ContestDetailData.getDeadline(i,TopicNews.GameOnTouch));
		text_address.setText(ContestDetailData.getAddress(i,TopicNews.GameOnTouch));
		text_host.setText(ContestDetailData.getHost(i,TopicNews.GameOnTouch));
		text_request.setText(ContestDetailData.getRequest(i,TopicNews.GameOnTouch));
		text_rule.setText(ContestDetailData.getRule(i,TopicNews.GameOnTouch));
		text_award.setText(ContestDetailData.getAward(i,TopicNews.GameOnTouch));
		text_review1.setText(ContestDetailData.getReview1(i,TopicNews.GameOnTouch));
		text_review2.setText(ContestDetailData.getReview2(i,TopicNews.GameOnTouch));
	}
}
