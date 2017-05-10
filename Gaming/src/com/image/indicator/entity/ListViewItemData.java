package com.image.indicator.entity;

import com.image.indicator.activity.MainActivity;
import com.image.indicator.activity.TopicNews;

import android.R.string;
import android.content.Context;
import android.database.Cursor;
public class ListViewItemData {

	
	
	private static Context mc;
	public static String getTitle(int i,int game) {
		// TODO Auto-generated method stub
		Cursor c = MainActivity.dbp.getById(String.valueOf(i+1),game);
		while(c.moveToNext())
		{
			return c.getString(1);
		}
		return null;
	}

	public static int getPhotoResId(int i,int game) {
		// TODO Auto-generated method stub
//		if (i == 0)
//			return 0x7f020022;
//		if (i == 1)
//			return 0x7f020023;
//		if (i == 2)
		if(game == 0)
			return 0x7f020029;
			return 0x7f020026;
			
//		return 0;
	}

	public static String getSummary(int i,int game) {
		// TODO Auto-generated method stub
		Cursor c = MainActivity.dbp.getById(String.valueOf(i+1),game);
		while(c.moveToNext())
		{
			return c.getString(7)+"\r\n"+c.getString(8);
		}
		return null;
	}

	public static String getAuthor(int i,int game) {
		// TODO Auto-generated method stub
		Cursor c = MainActivity.dbp.getById(String.valueOf(i+1),game);
		while(c.moveToNext())
		{
			return c.getString(5);
		}
		return null;
	}

	public static String getPublishtime(int i,int game) {
		// TODO Auto-generated method stub
		Cursor c = MainActivity.dbp.getById(String.valueOf(i+1),game);
		while(c.moveToNext())
		{
			return c.getString(2);
		}
		return null;
	}

	public static int getItemNum() {
		// TODO Auto-generated method stub
		//获得行数
		return MainActivity.dbp.getAll(null, null, TopicNews.GameOnTouch).getCount();
	}

}
