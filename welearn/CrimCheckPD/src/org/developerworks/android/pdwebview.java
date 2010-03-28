package org.developerworks.android;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

//import com.pd.pdcc.R;

public class pdwebview extends Activity {

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.pdwebview);

		final String mimeType = "text/html";
		final String encoding = "utf-8";

		Bundle bndl = this.getIntent().getExtras();
		String q_zip = bndl.getString("post_q_zip");
		String q_type = bndl.getString("post_q_type");
		String q_lname = bndl.getString("post_q_lname");
		//String q_fname = bndl.getString("post_q_fname");

		WebView wv;

		wv = (WebView) findViewById(R.id.wv2);
		if (q_type == "zip")

		{
			wv.loadUrl("http://search.criminalcheck.com/PDsearch.php?p1="
							+ q_zip
							+ "&type=zip&input=grp_sxo_zip&dlnumber=cc&dlstate=cc&id=cc&disp=yahoogeorss");

		} else {
			wv.loadUrl("http://search.criminalcheck.com/PDsearch.php?p1="
							+ q_lname
							+ "&type=name&input=grp_sxo_name&dlnumber=cc&dlstate=cc&id=cc&disp=cchtml");

		}

		// wv.loadUrl("http://search.criminalcheck.com/PDsearch.php?p1=" + q_zip
		// +
		// "&type=zip&input=grp_sxo_zip&dlnumber=cc&dlstate=cc&id=cc&disp=yahoogeorss");
		// wv.loadUrl("http://search.criminalcheck.com/PDsearch.php?p1=" + q_zip
		// +
		// "&type=zip&input=grp_sxo_zip&dlnumber=cc&dlstate=cc&id=cc&disp=yahoogeorss");

		wv = (WebView) findViewById(R.id.wv1);
		wv.loadData("results for " + q_type + ":", mimeType, encoding);

	}
}
