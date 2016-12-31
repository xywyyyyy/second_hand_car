package com.ywb.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ywb.dao.UserInfoDAO;
import com.ywb.entity.UserInfo;
import com.ywb.util.ActivityCollector;

/**
 * �û�ע��3
 * @author Administrator
 *
 */
public class RegLast extends Activity {

	private Button btnlogin;
	private TextView mylasttel;

	private SharedPreferences preferences;

	private String telname;
	private String pwd;

	private UserInfo userInfo;
	private UserInfoDAO userInfoDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_reg_last);
		ActivityCollector.addActivity(this);
		userInfoDAO = new UserInfoDAO(RegLast.this);
		// ��ʼ���ؼ�
		initView();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}

	// ��ʼ���ؼ�
	private void initView() {
		btnlogin = (Button) this.findViewById(R.id.btnlogin);
		mylasttel = (TextView) this.findViewById(R.id.mylasttel);
		// �ӻ�����ȡ���ո�ע��ĵ绰���������
		preferences = getSharedPreferences("Reg", Context.MODE_PRIVATE);
		telname = preferences.getString("telname", "");
		pwd = preferences.getString("pwd", "");
		mylasttel.setText(telname);

		// ��½
		btnlogin.setOnClickListener(listener);

	}

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// userInfo = new UserInfo(telname, pwd, 0);
			userInfo = new UserInfo();
			userInfo.setUname(telname);
			userInfo.setUpwd(pwd);
			userInfo.setUimage(0);

			Toast.makeText(RegLast.this, "�û�����" + telname + "���룺" + pwd, 1)
					.show();

			userInfoDAO.AddUserInfo(userInfo);
			startActivity(new Intent(RegLast.this, Login.class));
			RegLast.this.finish();
		}
	};
}
