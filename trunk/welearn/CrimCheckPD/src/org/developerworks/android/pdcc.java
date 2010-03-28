package org.developerworks.android;

import org.developerworks.android.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class pdcc extends Activity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.main);

		//Button mapit = (Button) findViewById(R.id.btn_zip);
		Button search_zip = (Button) findViewById(R.id.btn_zip);
		Button search_name = (Button) findViewById(R.id.btn_name);
		EditText ziEdit = (EditText) findViewById(id.txt_zip);
		
		ziEdit.setOnClickListener(new View.OnClickListener() {
			// @Override
			public void onClick(View arg0) {
				EditText zip_input = (EditText) findViewById(R.id.txt_zip);
				zip_input.setText("33614");
			}
		});
			

		search_zip.setOnClickListener(new View.OnClickListener() {
			// @Override
			public void onClick(View arg0) {
				Intent intnt = new Intent(pdcc.this, MessageList.class);
				// 
				EditText zip_input = (EditText) findViewById(R.id.txt_zip);
				String q_message = zip_input.getText().toString();
				//                    
				Bundle bndl = new Bundle();
				bndl.putString("post_q_type", "zip");
				bndl.putString("post_q_zip", q_message.toString());
				intnt.putExtras(bndl);
				startActivity(intnt);
			}
		});

		search_name.setOnClickListener(new View.OnClickListener() {
			// @Override
			public void onClick(View arg0) {
				Intent intnt = new Intent(pdcc.this, MessageList.class);
				// 
				EditText zip_input = (EditText) findViewById(R.id.txt_lname);
				String q_message = zip_input.getText().toString();
				//                    
				Bundle bndl = new Bundle();
				bndl.putString("post_q_type","name");
				bndl.putString("post_q_lname", q_message.toString());
				bndl.putString("post_q_fname", q_message.toString());
				intnt.putExtras(bndl);
				startActivity(intnt);
			}
		});
		
		//mapit.setOnClickListener(new View.OnClickListener() {
			// @Override
		//	public void onClick(View arg0) {
		//		Intent intnt = new Intent(pdcc.this, pdmapview.class);
		//		startActivity(intnt);
		//	}
		//});
	}
}
