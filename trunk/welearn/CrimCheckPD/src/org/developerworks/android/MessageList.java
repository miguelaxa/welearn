package org.developerworks.android;

import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
//import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MessageList extends ListActivity {
	
	private List<Message> messages;
	
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.list_main);
        loadFeed(ParserType.ANDROID_SAX);
    }
    
  public enum ParserType{
	SAX, ANDROID_SAX;
}
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(Menu.NONE, ParserType.ANDROID_SAX.ordinal(), 
				ParserType.ANDROID_SAX.ordinal(), R.string.android_sax);
		menu.add(Menu.NONE, ParserType.SAX.ordinal(), ParserType.SAX.ordinal(),
				R.string.sax);
	
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		super.onMenuItemSelected(featureId, item);
		ParserType type = ParserType.values()[item.getItemId()];
		ArrayAdapter<String> adapter =
			(ArrayAdapter<String>) this.getListAdapter();
		if (adapter.getCount() > 0){
			adapter.clear();
		}
		this.loadFeed(type);
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent viewMessage = new Intent(Intent.ACTION_VIEW, 
				Uri.parse(messages.get(position).getLink().toExternalForm()));
		//Intent viewMessage = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString)
		this.startActivity(viewMessage);
	}
	
	public void loadFeed(ParserType type){
		Bundle bndl = this.getIntent().getExtras();
		String q_zip = bndl.getString("post_q_zip");
		
	try{
		
			
		//String feedUrl = "http://www.androidster.com/android_news.rss";
	    		
		String feedUrl = "http://search.criminalcheck.com/PDsearch.php?p1="+q_zip+"&type=zip&input=grp_sxo_zip&dlnumber=cc&dlstate=cc&id=cc&disp=yahoogeorss";
    	
	    	//FeedParser parser = FeedParserFactory.getParser(type);
	    	FeedParser parser = new AndroidSaxFeedParser(feedUrl);
	    	//long start = System.currentTimeMillis();
	    	messages = parser.parse();
	    	//long duration = System.currentTimeMillis() - start;
	    	//Log.i("AndroidNews", "Parser duration=" + duration);
	    	
	    	List<String> titles = new ArrayList<String>(messages.size());
	    	for (Message msg : messages){
	    		titles.add(msg.getTitle());
	    	}
	    	
	    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row,titles);
	    	this.setListAdapter(adapter);
    	} catch (Throwable t){
    		Log.e("AndroidNews",t.getMessage(),t);
    	}
    }
    
}