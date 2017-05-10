package com.image.indicator.activity;


import com.image.indicator.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;





/**
 * Instruction
 * @author renzhongfeng
 * 用户使用说明对应的Activity
 */
public class Instruction extends Activity {
	private Button exit = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instruction);
		exit = (Button)this.findViewById(R.id.instruction_exit);
		exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
