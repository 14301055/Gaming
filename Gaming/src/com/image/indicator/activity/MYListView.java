package com.image.indicator.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MYListView extends Activity {
private ListView listView;

public void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	listView = new ListView(this);
	listView.setAdapter(new  ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,getData()));
	
	setContentView(listView);
	
}
private List<String> getData(){
	List<String> data = new ArrayList<String>();
	
	data.add("1");
	data.add("2");
	data.add("3");
	data.add("4");
	
	return data;
}
}
