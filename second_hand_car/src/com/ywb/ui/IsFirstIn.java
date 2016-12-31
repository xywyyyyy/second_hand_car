package com.ywb.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Window;

import com.ywb.util.ActivityCollector;

/**
 * 判断用户是否第一次使用
 * 
 * @author Administrator
 * 
 */
public class IsFirstIn extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ActivityCollector.addActivity(this);
		// 判断用户是否第一次使用
		IsFirst();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}
	
	// 判断用户是否第一次使用
	private void IsFirst()
	{
		/**
		 * SharedPreferences: 1、SharedPreferences是Android中最容易理解的数据存储技术
		 * 2、实际上SharedPreferences处理的就是一个key-value（键值对）
		 * 3、SharedPreferences常用来存储一些轻量级的数据
		 */

		// 实例化SharedPreferences对象，参数1指定文件名，参数2指定文件的操作模式
		SharedPreferences preferences = getSharedPreferences("usedcars",
				Context.MODE_PRIVATE);
		// 创建布尔对象first，初始值为true
		boolean first = preferences.getBoolean("first", true);
		// 如果first为true，代表第一次使用
		if (first == true)
		{
			// 创建编辑器Editor
			Editor editor = preferences.edit();
			// 修改first值为false
			editor.putBoolean("first", false);
			// 修改后提交
			editor.commit();
			// 跳转至引导页
			startActivity(new Intent(IsFirstIn.this, GuidePage.class));
			// 关闭该页面
			IsFirstIn.this.finish();
		}
		else
		{
			// 不是第一次使用，跳转至欢迎页
			startActivity(new Intent(IsFirstIn.this, Welcome.class));
			// 关闭该页面
			IsFirstIn.this.finish();
		}

	}

}
