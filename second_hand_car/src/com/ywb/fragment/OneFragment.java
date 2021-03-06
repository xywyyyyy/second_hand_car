package com.ywb.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.ywb.adapter.BrandAdapter;
import com.ywb.adapter.GuidePageAdapter;
import com.ywb.adapter.PartsAdapter;
import com.ywb.dao.BrandDAO;
import com.ywb.dao.PartsDAO;
import com.ywb.entity.Parts;
import com.ywb.ui.R;
import com.ywb.ui.ShowParts;
import com.ywb.ui.ShowSerial;
import com.ywb.ui.Main.MyCommunication;
import com.ywb.ui.Search;

public class OneFragment extends Fragment {
	private View view;
	// 广告
	private ViewPager viewPager;

	// More
	private ImageView mIvBrandMore;
	private ImageView mIvPartsMore;

	// GridView
	private GridView mGvbrand;
	private GridView mGvParts;

	// 搜索
	private ImageView mImgSearch;

	// 广告数组
	private List<View> ar;
	private GuidePageAdapter adapter;

	private AtomicInteger atomicInteger = new AtomicInteger();

	// ImageView
	private ImageView mImages[];
	private ImageView mImage;

	private BrandDAO brandDAO;
	private List brandList;
	private BrandAdapter brandAdapter;

	private PartsDAO partsDAO;
	private Parts parts;
	private List partsList;
	private PartsAdapter partsAdapter;

	// 父容器定义的实现fragment之间通信的接口
	private MyCommunication myCommunication;

	// 跳转到另一个Fragment
	public void jumpFragment(MyCommunication myCommunication) {
		this.myCommunication = myCommunication;
	}

	// 让父容器来管理
	private void startFragment(int position) {
		// 调用父容器接口中定义的方法
		myCommunication.getResultFragment(position);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.one_fragment, null);
		// 初始化控件
		initView();
		// 加载数据
		initData();
		// 加事件
		initEvent();
		return view;
	}

	// 加事件
	private void initEvent() {
		mImgSearch.setOnClickListener(listener);
		mIvBrandMore.setOnClickListener(listener);
		mIvPartsMore.setOnClickListener(listener);
		mGvbrand.setOnItemClickListener(gv_listener);
		mGvParts.setOnItemClickListener(gv_parts_listener);

	}

	// 加载数据
	private void initData() {
		brandDAO = new BrandDAO(getActivity());
		brandList = brandDAO.getEightBrand();
		brandAdapter = new BrandAdapter(getActivity(), brandList);
		mGvbrand.setAdapter(brandAdapter);

		partsDAO = new PartsDAO(getActivity());
		partsList = partsDAO.getFourParts();
		partsAdapter = new PartsAdapter(getActivity(), partsList);
		mGvParts.setAdapter(partsAdapter);

	}

	// 初始化控件
	private void initView() {
		// 第一步：初始化ViewPager
		viewPager = (ViewPager) view.findViewById(R.id.vp_advertise);

		mIvBrandMore = (ImageView) view.findViewById(R.id.iv_brand_more);
		mIvPartsMore = (ImageView) view.findViewById(R.id.iv_prant_more);

		mGvbrand = (GridView) view.findViewById(R.id.gv_brand);
		mGvParts = (GridView) view.findViewById(R.id.gv_parts);
		mImgSearch = (ImageView) view.findViewById(R.id.iv_search);

		// 创建ViewGroup对象，用来存放图片数组
		ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.rounddot);

		// 第二步：创建广告对象
		ar = new ArrayList<View>();
		View v0 = getActivity().getLayoutInflater().inflate(
				R.layout.advertise_item, null);
		LinearLayout l0 = (LinearLayout) v0.findViewById(R.id.advertise_item);
		l0.setBackgroundResource(R.drawable.main_page1);
		ar.add(l0);

		View v1 = getActivity().getLayoutInflater().inflate(
				R.layout.advertise_item, null);
		LinearLayout l1 = (LinearLayout) v1.findViewById(R.id.advertise_item);
		l1.setBackgroundResource(R.drawable.main_page2);
		ar.add(l1);

		View v2 = getActivity().getLayoutInflater().inflate(
				R.layout.advertise_item, null);
		LinearLayout l2 = (LinearLayout) v2.findViewById(R.id.advertise_item);
		l2.setBackgroundResource(R.drawable.main_page3);
		ar.add(l2);

		View v3 = getActivity().getLayoutInflater().inflate(
				R.layout.advertise_item, null);
		LinearLayout l3 = (LinearLayout) v3.findViewById(R.id.advertise_item);
		l3.setBackgroundResource(R.drawable.main_page4);
		ar.add(l3);

		adapter = new GuidePageAdapter(getActivity(), ar);
		viewPager.setAdapter(adapter);

		mImages = new ImageView[ar.size()];
		for (int i = 0; i < ar.size(); i++) {
			mImage = new ImageView(getActivity());
			// 设置图片宽和高
			LayoutParams layoutParams = new LayoutParams(9, 9);
			layoutParams.setMargins(10, 5, 10, 5);
			mImage.setLayoutParams(layoutParams);

			mImages[i] = mImage;

			if (i == 0) {
				mImages[i].setBackgroundResource(R.drawable.small_bg);
			} else {
				mImages[i].setBackgroundResource(R.drawable.small_bg1);
			}
			viewGroup.addView(mImages[i]);
		}

		viewPager.setOnPageChangeListener(vp_listener);

		// 创建定时器
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				handler.sendEmptyMessage(atomicInteger.incrementAndGet() - 1);
			}
		};

		timer.schedule(task, 2000, 2000);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 显示第几项
			viewPager.setCurrentItem(msg.what);

			if (atomicInteger.get() == ar.size()) {
				atomicInteger.set(0);
			}
		};
	};

	OnPageChangeListener vp_listener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			atomicInteger.getAndSet(arg0);
			for (int i = 0; i < mImages.length; i++) {
				mImages[i].setBackgroundResource(R.drawable.small_bg1);
				if (arg0 != i) {
					mImages[i].setBackgroundResource(R.drawable.small_bg);
				}
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

	// 单击事件
	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_search:
				startActivity(new Intent(getActivity(), Search.class));
				break;
			case R.id.iv_brand_more:
				// 跳转到TwoFragment
				startFragment(2);
				break;
			case R.id.iv_prant_more:
				// 跳转到TwoFragment
				startFragment(3);
				break;
			default:
				break;
			}

		}
	};

	/**
	 * 品牌GridView单击事件
	 */
	OnItemClickListener gv_listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Map map = (Map) brandList.get(arg2);
			// 品牌编号
			int bid = Integer.parseInt(map.get("bid").toString());
			// 根据品牌编号查询所有车系
			Intent intent = new Intent(getActivity(), ShowSerial.class);
			intent.putExtra("bid", bid);
			startActivity(intent);

		}
	};

	/**
	 * 饰品GridView单击事件
	 */
	OnItemClickListener gv_parts_listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			Map map = (Map) partsList.get(arg2);
			// 品牌编号
			int pid = Integer.parseInt(map.get("pid").toString());

			// 根据品牌编号查询所有车系

			partsDAO = new PartsDAO(getActivity());
			parts = partsDAO.getAllPartsById(pid);

			// 饰品配饰(编号，名称，图片，库存，原价，现价，品牌，介绍)

			String pname = parts.getPname();
			int pimage = parts.getPimage();
			int pinventory = parts.getPinventory();
			float poldprice = parts.getPoldprice();
			float pnewprice = parts.getPnewprice();
			String pbrand = parts.getPbrand();
			String preferral = parts.getPreferral();

			Intent intent = new Intent(getActivity(), ShowParts.class);

			intent.putExtra("pid", pid);
			intent.putExtra("pname", pname);
			intent.putExtra("pimage", pimage);
			intent.putExtra("pinventory", pinventory);
			intent.putExtra("poldprice", poldprice);
			intent.putExtra("pnewprice", pnewprice);
			intent.putExtra("pbrand", pbrand);
			intent.putExtra("preferral", preferral);
			startActivity(intent);
		}
	};

}
