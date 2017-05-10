package com.image.indicator.activity;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.image.indicator.R;

/**
 * Login
 * 
 * @author ���ҷ� 
 * �û��ڴ򿪸�Appʱ�����Ľ��棬�����û��ĵ�½������ע�ᣬʹ��˵���Ƚ��� 2014.07.25
 */

public class Login extends Activity {
	private EditText userName = null;
	private EditText password = null;
	private Button register = null;
	private Button instruction = null;
	private Button login = null;
	private TextView login_title = null;
	private TextView forgetPassword = null;
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 1) {
				Intent login_main = new Intent(Login.this, MainActivity.class);
				Log.i("login_main----------------->", "success");
				startActivity(login_main);
				finish();
			}else {
				Toast.makeText(Login.this, "��½ʧ��", Toast.LENGTH_SHORT)
				.show();
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
		initListener();
	}

	
	private void init() {
		userName = (EditText) this.findViewById(R.id.userName);
		password = (EditText) this.findViewById(R.id.password);
		register = (Button) this.findViewById(R.id.register);
		instruction = (Button) this.findViewById(R.id.instruction);
		login = (Button) this.findViewById(R.id.login);
		login_title = (TextView) this.findViewById(R.id.login_title);
		forgetPassword =  (TextView) this.findViewById(R.id.forgetPassword);
	}

	/*
	 * ��login�ϵ�ҳ��Ԫ�����ü���
	 */
	private void initListener() {
		this.login.setOnClickListener(new LoginListener());
		this.register.setOnClickListener(new ButtonRegister());
		this.instruction.setOnClickListener(new ButtonInstruction());
		this.forgetPassword.setOnClickListener(new TextForget());
	}
	/**
	 * 
	 * @author renzhongfeng
	 * �û��ṩ�����ݺ��û����ݿ��е����ݽ���ƥ�䣬������Ե�½��������ʾ��ע��
	 */
	private class TextForget implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			Intent forget_click = new Intent(Login.this, Forget.class);
			Log.i("forget_click----------------->", "success");
			startActivity(forget_click);
			finish();
		}
	}
	 private class LoginListener implements OnClickListener{
		 String myUserName = userName.getText().toString();
			String passwd = password.getText().toString();

			@Override
			public void onClick(View v) {
				/*new Thread (){
					public void run() {
						HttpClient client = new DefaultHttpClient();
						List<NameValuePair> list = new ArrayList<NameValuePair>();
						NameValuePair pair = new BasicNameValuePair("index", "0");
						list.add(pair);
						NameValuePair pair1 = new BasicNameValuePair("username", userName.getText().toString());
						NameValuePair pair2 = new BasicNameValuePair("password",  password.getText().toString());
						
						list.add(pair1);
						list.add(pair2);
						try {
							UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
							HttpPost post = new HttpPost("http://123.206.41.96:8080/GamingPlatform/matchInfo.jsp");
							post.setEntity(entity);
							HttpResponse response = client.execute(post);
							if (response.getStatusLine().getStatusCode() == 200) {
								InputStream in = response.getEntity().getContent();
								handler.sendEmptyMessage(in.read());
								in.close();
							}
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
				}.start();*/
				Intent intent =new Intent(Login.this,MainActivity.class);
				startActivity(intent);
				
			}
			
			
	 }

	/**
	 * ����register����
	 */
	private class ButtonRegister implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			Intent login_agreement = new Intent(Login.this, Agreement.class);
			Log.i("login_agreement----------------->", "success");
			startActivity(login_agreement);
			finish();
		}
	}

	/*
	 * ����instruction�ļ���
	 */
	private class ButtonInstruction implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent login_instruction = new Intent(Login.this, UserZoom.class);
			startActivity(login_instruction);
		}
	}

}
