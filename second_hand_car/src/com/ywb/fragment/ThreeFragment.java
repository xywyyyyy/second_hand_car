package com.ywb.fragment;

import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ywb.adapter.PartsListAdapter;
import com.ywb.dao.PartsDAO;
import com.ywb.ui.R;
import com.ywb.ui.ShowParts;

public class ThreeFragment extends Fragment {
	private View view;
	private ListView mLvParts;
	private PartsDAO partsDAO;
	private PartsListAdapter partsListAdapter;
	private List ar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.three_fragment, null);
		// ��ʼ���ؼ�
		initView();
		// ��������
		initData();
		return view;
	}

	// ��������
	private void initData() {
		partsDAO = new PartsDAO(getActivity());
		ar = partsDAO.getAllParts();
		partsListAdapter = new PartsListAdapter(getActivity(), ar);
		mLvParts.setAdapter(partsListAdapter);

	}

	// ��ʼ���ؼ�
	private void initView() {
		mLvParts = (ListView) view.findViewById(R.id.lv_parts);
		mLvParts.setOnItemClickListener(lv_listener);
	}

	OnItemClickListener lv_listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Map map = (Map) ar.get(arg2);

			int pid = Integer.parseInt(map.get("pid").toString());
			String pname = map.get("pname").toString();
			int pimage = Integer.parseInt(map.get("pimage").toString());
			int pinventory = Integer.parseInt(map.get("pinventory").toString());
			float poldprice = Float.parseFloat(map.get("poldprice").toString());
			float pnewprice = Float.parseFloat(map.get("pnewprice").toString());
			String pbrand = map.get("pbrand").toString();
			String preferral = map.get("preferral").toString();

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
