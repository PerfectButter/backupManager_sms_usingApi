package com.example.mysms01;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class SMS01Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms01);
		
		// READ SMS
		Uri uri = Uri.parse("content://sms/inbox");
		Cursor c= getContentResolver().query(uri, null, null ,null,null);
		startManagingCursor(c);
		
		
		String[] body = new String[c.getCount()];
		String[] number = new String[c.getCount()];
		                
		if(c.moveToFirst()){
		        for(int i=0;i<c.getCount();i++){
		                body[i]= c.getString(c.getColumnIndexOrThrow("body")).toString();
		                number[i]=c.getString(c.getColumnIndexOrThrow("address")).toString();
		                c.moveToNext();
		        }
		}
		c.close();
		
		Log.v(SMS01Activity.class.getSimpleName(), "body.length=" + body.length);
		Log.v(SMS01Activity.class.getSimpleName(), "number.length=" + number.length);
		
		for(int i=0; i<body.length; i++) {
			Log.v(SMS01Activity.class.getSimpleName(), "[BODY] i="+i+" "+body[i]);
		}
		for(int i=0; i<number.length; i++) {
			Log.v(SMS01Activity.class.getSimpleName(), "[number] i="+i+" "+body[i]);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sms01, menu);
		return true;
	}

}
