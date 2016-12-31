package com.ywb.adapter;

import java.util.List;
import java.util.Map;

import com.ywb.ui.R;

import android.R.color;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PartsListAdapter extends BaseAdapter
{
	private Context context;
	private List ar;
	private LayoutInflater inflater;

	public PartsListAdapter(Context context, List ar)
	{
		this.context = context;
		this.ar = ar;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return ar.size();
	}

	@Override
	public Object getItem(int arg0)
	{
		// TODO Auto-generated method stub
		return ar.get(arg0);
	}

	@Override
	public long getItemId(int arg0)
	{
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2)
	{
		viewHolder holder;
		if (arg1 == null)
		{
			holder = new viewHolder();
			arg1 = inflater.inflate(R.layout.parts_list_item, null);
			holder.mIvPimage = (ImageView) arg1.findViewById(R.id.iv_pimage);
			holder.mTvPname = (TextView) arg1.findViewById(R.id.tv_pname);
			holder.mTvPprice = (TextView) arg1.findViewById(R.id.tv_pnewprice);
			arg1.setTag(holder);
		}
		else
		{
			holder = (viewHolder) arg1.getTag();
		}
		Map map = (Map) ar.get(arg0);
		holder.mIvPimage.setBackgroundResource(Integer.parseInt(map.get(
				"pimage").toString()));
		holder.mTvPname.setText(map.get("pname").toString());
		holder.mTvPprice.setText(map.get("pnewprice").toString() + "");
		return arg1;
	}

	private class viewHolder
	{
		private ImageView mIvPimage;
		private TextView mTvPname;
		private TextView mTvPprice;
	}

}
