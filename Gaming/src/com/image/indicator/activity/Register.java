package com.image.indicator.activity;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import com.image.indicator.entity.User;
import com.image.indicator.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;








public class Register extends Activity {
	
	public final static int TELEPHONE = 0;
	public final static int EMAIL = 1;
	public final static int QQ = 2;
	public final static int WECHAT = 3;
	public final static int OTHERS = 4;

	private EditText userName = null;
	private EditText password = null;
	private EditText rePassword = null;
	private RadioGroup sex = null;
	private RadioButton male = null;
	private RadioButton female = null;
	private Spinner communication = null;
	private Button register = null;
	private Button goback = null;
	private User user = null;
	private boolean usernameCursor = true;// 鍒よ鐢ㄦ埛鍚嶈緭鍏ユ鏄け鍘诲厜鏍囪繕鏄幏寰楀厜鏍�
	private boolean repasswordCursor = true;// 鍒よ閲嶅瀵嗙爜杈撳叆妗嗘槸澶卞幓鍏夋爣杩樻槸鑾峰緱鍏夋爣
	private String mySex = null;
	private String myCommunication = null;
	private TextView communication_way_choice = null;
	private EditText communication_content = null;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			if (msg.what == 1) {//绛変簬1鏄鏄庢帴鏀跺埌浜嗘敞鍐屾垚鍔熺殑淇℃伅锛屽綋娉ㄥ唽鎴愬姛鏃讹紝鏈嶅姟鍣ㄤ細杩斿洖1缁欏鎴风
				
					Toast.makeText(Register.this, "娉ㄥ唽鎴愬姛", Toast.LENGTH_SHORT)
					.show();
					Intent register_login = new Intent(Register.this, Login.class);
					startActivity(register_login);
					finish();
				
				
			} else {
				if (userName.getText().toString() == null) {
					Toast.makeText(Register.this, "鐢ㄦ埛鍚嶄笉鑳戒负绌�", Toast.LENGTH_SHORT)
					.show();
					userName.requestFocus();
				} else{
					Toast.makeText(Register.this, "娉ㄥ唽澶辫触", Toast.LENGTH_SHORT)
					.show();
				}			
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		init();
		initListener();
	}

	/**
	 * 椤甸潰鍏冪礌鍒濆鍖�
	 */
	private void init() {

		this.userName = (EditText) this.findViewById(R.id.regi_userName);
		this.password = (EditText) this.findViewById(R.id.regi_password);
		this.rePassword = (EditText) this.findViewById(R.id.regi_repassword);
		this.sex = (RadioGroup) this.findViewById(R.id.regi_sex);
		this.male = (RadioButton) this.findViewById(R.id.regi_male);
		this.female = (RadioButton) this.findViewById(R.id.regi_female);
		this.communication = (Spinner) this
				.findViewById(R.id.regi_communication_way);
		this.register = (Button) this.findViewById(R.id.regi_register);
		this.goback = (Button) this.findViewById(R.id.regi_goback);
		this.communication_way_choice = (TextView)findViewById(R.id.communication_way_choice);
		this.communication_content = (EditText)findViewById(R.id.communication_content);
	}

	/**
	 * 鐩戝惉浜嬩欢鐨勫垵濮嬪寲 鐢ㄦ埛鍚嶈緭鍏ユ鍏夋爣澶卞幓鐩戝惉锛屽瘑鐮侀噸鏂拌緭鍏ユ鐨勫厜鏍囧け鍘荤洃鍚紝鎬у埆澶嶉�夋鐩戝惉锛屾敞鍐屾寜閽偣鍑荤洃鍚紝杩斿洖鎸夐挳鐐瑰嚮鐩戯拷?
	 */
	private void initListener() {
		this.userName.setOnFocusChangeListener(new CheckUsernameListener());
		this.rePassword.setOnFocusChangeListener(new RePasswordListener());
		this.sex.setOnCheckedChangeListener(new RadioGroupSex());
		this.communication.setOnItemSelectedListener(new SpinnerListener());
		this.register.setOnClickListener(new RegisterListener());
		this.goback.setOnClickListener(new ExitListener());
	}

	/**
	 * CheckUsernameListener
	 * @author renzhongfeng 
	 * 褰撹緭鍏ュ畬鐢ㄦ埛鍚嶏紝杈撳叆妗嗗け鍘诲厜鏍囧悗,锟�?锟斤拷璇ョ敤鎴峰悕鍦ㄦ暟鎹簱涓槸鍚﹀瓨锟�?
	 */
	private class CheckUsernameListener implements OnFocusChangeListener {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			String myUsername = userName.getText().toString();
			if (!usernameCursor) {
				if (isUsernameExisted(myUsername)) {
					Toast.makeText(Register.this, "璇ョ敤鎴峰悕宸茬粡瀛樺湪锛岃鏇存敼鐢ㄦ埛鍚�",
							Toast.LENGTH_SHORT).show();
				}
			}
		}

		/**
		 * 鐢ㄤ簬妫�娴嬬敤鎴疯緭鍏ョ殑鐢ㄦ埛鍚嶆槸鍚﹀凡缁忔敞鍐�
		 * 灏嗙敤鎴疯緭鍏ョ殑鐢ㄦ埛鍚嶄紶鍔ㄥ埌鏈嶅姟鍣紝鍦ㄧ敤鎴锋暟鎹簱涓煡鎵捐鐢ㄦ埛鍚嶏紝鑻ヨ兘澶熸煡鎵惧埌鍒欒繑鍥瀟rue锛屽惁鍒欒繑鍥瀎alse
		 * @param username
		 *            鐢ㄦ埛杈撳叆鐨勭敤鎴峰悕
		 * @return 鏍囪璇ョ敤鎴峰悕鏄惁宸茬粡瀛樺湪锛屽瓨鍦ㄤ负true锛屼笉瀛樺湪涓篺alse
		 */
		private boolean isUsernameExisted(String username) {
			boolean flag = false;
			return flag;
		}
	}

	/**
	 * RadioGroupSex
	 * @author renzhongfeng 
	 * 鎬у埆澶嶉�夋鐨勭洃鍚被锛屽苟灏嗚幏寰楃殑鎬у埆璧嬬粰鎴愬憳鍙橀噺mySex 2014/07/27
	 */
	private class RadioGroupSex implements RadioGroup.OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if (checkedId == male.getId()) {
				mySex = "鐢�";
			} else {
				mySex = "濂�";
			}
		}
	}

	/**
	 * RePasswordListener
	 * @author renzhongfeng 
	 * 閲嶅杈撳叆瀵嗙爜澶卞幓鍏夋爣鐨勭洃鍚被 2014/07/27
	 */
	private class RePasswordListener implements OnFocusChangeListener {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			if (repasswordCursor=!repasswordCursor) {
				if (!checkPassword(password.getText().toString(), rePassword
						.getText().toString())) {
					rePassword.setText("");
					//rePassword.requestFocus();
					Toast.makeText(Register.this, "涓ゆ瀵嗙爜涓嶄竴鏍凤紝璇烽噸鏂拌緭鍏�",
							Toast.LENGTH_SHORT).show();
				}
			}
		}

		
	}
	
	/**
	 * 姣旇緝涓ゆ杈撳叆鐨勫瘑鐮佹槸鍚︿竴鑷�
	 * rePassword杈撳叆瀹屾垚鍚庯紝鍏夋爣鏀瑰彉鐩戝惉锛屽拰password杩涜姣旇緝锛屽鏋滀笉涓�
	 * 鑷达紝浼氭湁鎻愮ず锛屽苟涓斾袱娆″瘑鐮佸瘑鐮佹竻绌�
	 * 
	 * @param psw1
	 *            瀵嗙爜妗嗕腑杈撳叆鐨勫瘑鐮�
	 * @param psw2
	 *            閲嶅瀵嗙爜妗嗕腑杈撳叆鐨勫瘑鐮�
	 * @return 涓ゆ瀵嗙爜涓�鑷磋繑鍥瀟rue锛屽惁鍒欒繑鍥瀎alse
	 */
	private boolean checkPassword(String psw1, String psw2) {
		if (psw1.equals(psw2))
			return true;
		else
			return false;
	}

	/**
	 * SpinnerListener
	 * @author renzhongfeng 
	 * 鑱旂郴鏂瑰紡鐨剆pinner缁勪欢commnunication鐩戝惉锛岃幏寰桰tem鐨勫唴锟�?
	 */
	private class SpinnerListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			myCommunication = parent.getItemAtPosition(position).toString();
			communication_way_choice.setText(myCommunication);			
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}

	}
	/**
	 * 鎵ц娉ㄥ唽鐨勬柟娉�
	 */
	public void excuteRegister(){
		new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				HttpClient client = new DefaultHttpClient();
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				NameValuePair pair = new BasicNameValuePair("index", "2");
				list.add(pair);
				NameValuePair pair1 = new BasicNameValuePair("username", userName.getText().toString());
				NameValuePair pair2 = new BasicNameValuePair("password", password.getText().toString());
				NameValuePair pair3 = new BasicNameValuePair("sex", mySex);
				NameValuePair pair4 = new BasicNameValuePair("communication_way", myCommunication);
				NameValuePair pair5 = new BasicNameValuePair("communication_num", communication_content.getText().toString());
				list.add(pair1);
				list.add(pair2);
				list.add(pair3);
				list.add(pair4);
				list.add(pair5);
				try {
					HttpEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
					Log.i("register----------->", "HttpPost鍓�");
					HttpPost post = new HttpPost("http://172.21.8.17:8080/server/Servlet");
					Log.i("register----------->", "HttpPost鍚�1");
					post.setEntity(entity);
					HttpResponse response = client.execute(post);
					Log.i("register----------->", "HttpPost鍓�2");
					if (response.getStatusLine().getStatusCode() == 200) {
						InputStream in = response.getEntity().getContent();
						handler.sendEmptyMessage(in.read());//灏嗘湇鍔″櫒绔繑鍥炵粰瀹㈡埛绔殑鏍囪鐩存帴浼犺緭缁檋andler
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
		}.start();
	}
	/**
	 * RegisterListener
	 * @author renzhongfeng 
	 * 鐐瑰嚮娉ㄥ唽鎸夐挳鍚庯紝鍚戞湇鍔″櫒绔彂閫佹敞鍐屼俊鎭紝绛夊埌鏈嶅姟鍣ㄨ繑鍥炵‘璁や俊鎭悗锛屾彁绀烘敞鍐屾垚鍔燂紝骞惰嚜鍔ㄨ繑鍥炵櫥闄嗛〉锟�?
	 * 2014/07/28
	 */
	private class RegisterListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (mySex == null || communication_content.getText().toString() == null) {
				String title = "鎻愮ず锛�";
				String message = "鎮ㄧ殑淇℃伅涓嶅畬鏁达紝濉啓瀹屾暣淇℃伅鏈夊姪浜庢垜浠彁渚涙洿濂界殑鏈嶅姟";
				new AlertDialog.Builder(Register.this).setTitle(title)
						.setMessage(message)
						.setPositiveButton("缁х画娉ㄥ唽", new MakeSureListener())
						.setNegativeButton("杩斿洖淇敼", null).show();
			} else if (checkPassword(password.getText().toString(), rePassword
						.getText().toString())) {			
				excuteRegister();
			}else{
				rePassword.setText("");
				//rePassword.requestFocus();
				Toast.makeText(Register.this, "涓ゆ瀵嗙爜涓嶄竴鏍凤紝璇烽噸鏂拌緭鍏�",
						Toast.LENGTH_SHORT).show();
			}
			
		}
		/**
		 * 鑾峰彇鐢ㄦ埛鐨勬敞鍐屼俊鎭幏鍙栫敤鎴峰湪椤甸潰涓婂～鍐欑殑淇℃伅锛屽苟灏嗚繖浜涗俊鎭皝瑁呭埌User绫讳腑
		 * @return User 鍖呭惈鏈夌敤鎴峰畬鏁存敞鍐屼俊鎭殑User鍖呰锟�?
		 */
		private User getUser() {
			User user = new User();
			user.setPassword(password.getText().toString());
			user.setUsername(userName.getText().toString());
			user.setSex(mySex);
			user.setCommunication(myCommunication);
			user.setCommunication(communication_content.getText().toString());
			return user;
		}
	}

	/**
	 * ExitListener
	 * @author renzhongfeng 
	 * 璁剧疆鈥滆繑鍥炴寜閽殑鐐瑰嚮鐩戝惉锛岀偣鍑诲悗鍥炲埌鐧婚檰鐣岄潰2014/07/27
	 */
	private class ExitListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent register_login = new Intent(Register.this, Login.class);
			startActivity(register_login);
			finish();
		}
	}
	
	/**
	 * MakeSureListener
	 * @author renzhongfeng
	 * 纭畾鎻愮ず妗嗙殑纭畾鎸夐挳鐩戝惉
	 */
	private class MakeSureListener implements
			android.content.DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			if (checkPassword(password.getText().toString(), rePassword
					.getText().toString())) {			
			excuteRegister();
		}else{
			rePassword.setText("");
			//rePassword.requestFocus();
			Toast.makeText(Register.this, "涓ゆ瀵嗙爜涓嶄竴鏍凤紝璇烽噸鏂拌緭鍏�",
					Toast.LENGTH_SHORT).show();
		}
		}
	}
}
