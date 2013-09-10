/*package tranduythanh.com;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity {
	Button btnlogin;
	EditText edituser, editpassword;
	CheckBox chksaveaccount;
	// đặt tên cho tập tin lưu trạng thái
	String prefname = "my_data";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnlogin = (Button) findViewById(R.id.btnlogin);
		edituser = (EditText) findViewById(R.id.editUser);
		editpassword = (EditText) findViewById(R.id.editPassword);
		chksaveaccount = (CheckBox) findViewById(R.id.chksaveacount);
		btnlogin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				doLogin();
			}
		});
	}

	*//**
	 * hàm đăng nhập hệ thống
	 *//*
	public void doLogin() {
		finish();// đóng màn hình hiện tại
		Intent i = new Intent(this, DangNhapThanhCongActivity.class);
		// truyền dữ liệu qua màn hình mới
		i.putExtra("user", edituser.getText().toString());
		startActivity(i);// mở màn hình mới
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// gọi hàm lưu trạng thái ở đây
		savingPreferences();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// gọi hàm đọc trạng thái ở đây
		restoringPreferences();
	}

	*//**
	 * hàm lưu trạng thái
	 *//*
	public void savingPreferences() {
		// tạo đối tượng getSharedPreferences
		SharedPreferences pre = getSharedPreferences(prefname, MODE_PRIVATE);
		// tạo đối tượng Editor để lưu thay đổi
		SharedPreferences.Editor editor = pre.edit();
		String user = edituser.getText().toString();
		String pwd = editpassword.getText().toString();
		boolean bchk = chksaveaccount.isChecked();
		if (!bchk) {
			// xóa mọi lưu trữ trước đó
			editor.clear();
		} else {
			// lưu vào editor
			editor.putString("user", user);
			editor.putString("pwd", pwd);
			editor.putBoolean("checked", bchk);
		}
		// chấp nhận lưu xuống file
		editor.commit();
	}

	*//**
	 * hàm đọc trạng thái đã lưu trước đó
	 *//*
	public void restoringPreferences() {
		SharedPreferences pre = getSharedPreferences(prefname, MODE_PRIVATE);
		// lấy giá trị checked ra, nếu không thấy thì giá trị mặc định là false
		boolean bchk = pre.getBoolean("checked", false);
		if (bchk) {
			// lấy user, pwd, nếu không thấy giá trị mặc định là rỗng
			String user = pre.getString("user", "");
			String pwd = pre.getString("pwd", "");
			edituser.setText(user);
			editpassword.setText(pwd);
		}
		chksaveaccount.setChecked(bchk);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}*/