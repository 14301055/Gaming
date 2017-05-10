package com.image.indicator.entity;

import android.database.Cursor;

import com.image.indicator.activity.MainActivity;

public class ContestDetailData {
	public static String getId(int i,int game) {
		// TODO Auto-generated method stub
		Cursor c = MainActivity.dbp.getById(String.valueOf(i+1),game);
		while(c.moveToNext())
		{
			return c.getString(1);
		}
		return null;
	}
	public static String getDeadline(int i,int game) {
		// TODO Auto-generated method stub
		Cursor c = MainActivity.dbp.getById(String.valueOf(i+1),game);
		while(c.moveToNext())
		{
			return c.getString(2);
		}
		return null;
	}
	public static String getData(int i,int game) {
		// TODO Auto-generated method stub
		Cursor c = MainActivity.dbp.getById(String.valueOf(i+1),game);
		while(c.moveToNext())
		{
			return c.getString(3);
		}
		return null;
	}
	public static String getAddress(int i,int game) {
		// TODO Auto-generated method stub
		Cursor c = MainActivity.dbp.getById(String.valueOf(i+1),game);
		while(c.moveToNext())
		{
			return c.getString(4);
		}
		return null;
	}
	public static String getHost(int i,int game) {
		// TODO Auto-generated method stub
		Cursor c = MainActivity.dbp.getById(String.valueOf(i+1),game);
		while(c.moveToNext())
		{
			return c.getString(5);
		}
		return null;
	}
	public static String getAward(int i,int game) {
		// TODO Auto-generated method stub
		Cursor c = MainActivity.dbp.getById(String.valueOf(i+1),game);
		while(c.moveToNext())
		{
			return c.getString(6);
		}
		return null;
	}
	public static String getRequest(int i,int game) {
		// TODO Auto-generated method stub
		Cursor c = MainActivity.dbp.getById(String.valueOf(i+1),game);
		while(c.moveToNext())
		{
			return c.getString(7);
		}
		return null;
	}
	public static String getRule(int i,int game) {
		// TODO Auto-generated method stub
		Cursor c = MainActivity.dbp.getById(String.valueOf(i+1),game);
		while(c.moveToNext())
		{
			return c.getString(8);
		}
		return null;
	}
	public static String getReview1(int i,int game) {
		// TODO Auto-generated method stub
		Cursor c = MainActivity.dbp.getById(String.valueOf(i+1),game);
		while(c.moveToNext())
		{
			return c.getString(9);
		}
		return null;
	}
	public static String getReview2(int i,int game) {
		// TODO Auto-generated method stub
		Cursor c = MainActivity.dbp.getById(String.valueOf(i+1),game);
		while(c.moveToNext())
		{
			return c.getString(10);
		}
		return null;
	}

}
