package com.ywb.ui;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.ywb.adapter.MyIndentAdapter;
import com.ywb.fragment.CompleteIndent;
import com.ywb.fragment.NoCompleteIndent;

/**
 * 我的订单
 * @author Administrator
 *
 */
public class MyIndent extends FragmentActivity {
	private CompleteIndent completeIndent;// 已完成订单
	private NoCompleteIndent noCompleteIndent;// 未完成订单

	private ViewPager mVpMyIndent;
	private Button mBtnCompleteYes;
	private Button mBtnCompleteNo;

	private FragmentManager fragmentManager;
	private MyIndentAdapter myIndentAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my_indent);
		fragmentManager = getSupportFragmentManager();
		// 初始化控件
		initView();
		// 加事件
		initEvent();
	}

	/**
	 * 加事件
	 */
	private void initEvent() {
		// 按钮点击事件
		mBtnCompleteYes.setOnClickListener(listener);
		mBtnCompleteNo.setOnClickListener(listener);

		// ViewPager滑动事件
		mVpMyIndent.setOnPageChangeListener(vp_listener);
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		mVpMyIndent = (ViewPager) this.findViewById(R.id.vp_myindent);
		mBtnCompleteYes = (Button) this.findViewById(R.id.btn_complete_yes);
		mBtnCompleteNo = (Button) this.findViewById(R.id.btn_complete_no);

		// 实例化FragMent
		completeIndent = new CompleteIndent();
		noCompleteIndent = new NoCompleteIndent();

		// 将FragMent添加到数组中
		List ar = new ArrayList();
		ar.add(completeIndent);
		ar.add(noCompleteIndent);

		myIndentAdapter = new MyIndentAdapter(fragmentManager, ar);
		mVpMyIndent.setAdapter(myIndentAdapter);

		// 默认选择第一个
		mVpMyIndent.setCurrentItem(0);

	}

	/**
	 * 按钮点击事件
	 */
	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.btn_complete_yes) {
				mVpMyIndent.setCurrentItem(0);
			}
			if (v.getId() == R.id.btn_complete_no) {
				mVpMyIndent.setCurrentItem(1);
			}
		}
	};

	/**
	 * ViewPager滑动事件
	 */
	OnPageChangeListener vp_listener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			// 清除样式
			clearStyle();
			switch (arg0) {
			case 0:
				mBtnCompleteYes.setBackgroundColor(Color.parseColor("#e0620d"));

				break;
			case 1:
				mBtnCompleteNo.setBackgroundColor(Color.parseColor("#e0620d"));

				break;
			default:
				break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}
	};

	/**
	 * 清除样式
	 */
	private void clearStyle() {
		mBtnCompleteYes.setBackgroundColor(Color.parseColor("#FF5809"));
		mBtnCompleteNo.setBackgroundColor(Color.parseColor("#FF5809"));
	}
}
