package com.example.Public;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.example.Entity.Picture;
import com.example.pic.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.util.Log;

public class JsonParse {
	static final String TAG_ID = "id";
	static final String TAG_POOL = "poolId";
	static final String TAG_SOLUTION = "solution";
	static final String TAG_COPYRIGHT = "copyrights";
	Context context;

	public JsonParse(Context context) {
		this.context = context;
	}

	public String getJson() {
		String text = null;
		try {
			InputStream is = context.getResources().openRawResource(
					R.raw.photodata);
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			text = new String(buffer);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return text;
	}

	public ArrayList<Picture> getData(int pool) {
		ArrayList<Picture> listData = new ArrayList<Picture>();
		try {
			JSONArray ja = new JSONArray(this.getJson());
			for (int i = 0; i < ja.length(); i++) {

				JSONObject c = ja.getJSONObject(i);

				int id = c.getInt(TAG_ID);
				int poolId = c.getInt(TAG_POOL);
				String solution = c.getString(TAG_SOLUTION);
				String copyright = c.getString(TAG_COPYRIGHT);

				if (pool == poolId) {
					Picture js = new Picture();
					js.setId(id);
					js.setPoolId(poolId);
					js.setSolution(solution);
					js.setCopyright(copyright);
					
					listData.add(js);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return listData;

	}

}
