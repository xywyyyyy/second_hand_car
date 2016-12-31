package com.ywb.entity;

public class ShoppingCart
{
	private int _id;// 编号
	private int uid;// 用户编号
	private int gid;// 物品编号
	private int gcount;// 物品数量
	private int gsource;// 物品来源
	private int gbuy;// 是否购买

	public ShoppingCart(int uid, int gid, int gcount, int gsource, int gbuy)
	{
		super();
		this.uid = uid;
		this.gid = gid;
		this.gcount = gcount;
		this.gsource = gsource;
		this.gbuy = gbuy;
	}

	public ShoppingCart(int _id, int uid, int gid, int gcount, int gsource,
			int gbuy)
	{
		super();
		this._id = _id;
		this.uid = uid;
		this.gid = gid;
		this.gcount = gcount;
		this.gsource = gsource;
		this.gbuy = gbuy;
	}

	public ShoppingCart()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public int get_id()
	{
		return _id;
	}

	public void set_id(int _id)
	{
		this._id = _id;
	}

	public int getUid()
	{
		return uid;
	}

	public void setUid(int uid)
	{
		this.uid = uid;
	}

	public int getGid()
	{
		return gid;
	}

	public void setGid(int gid)
	{
		this.gid = gid;
	}

	public int getGcount()
	{
		return gcount;
	}

	public void setGcount(int gcount)
	{
		this.gcount = gcount;
	}

	public int getGsource()
	{
		return gsource;
	}

	public void setGsource(int gsource)
	{
		this.gsource = gsource;
	}

	public int getGbuy()
	{
		return gbuy;
	}

	public void setGbuy(int gbuy)
	{
		this.gbuy = gbuy;
	}

}
