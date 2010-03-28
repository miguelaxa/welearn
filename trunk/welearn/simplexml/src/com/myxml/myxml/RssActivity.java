package com.myxml.myxml;
import com.myxml.R;
import java.io.IOException;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

//import org.ifies.android.R;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class RssActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rss_list);
		
		setListView((ListView)findViewById(R.id.rss_all_items));
		setParser(new RssParser("http://search.criminalcheck.com/PDsearch.php?p1=33614&type=zip&input=grp_sxo_zip&dlnumber=cc&dlstate=cc&id=cc&disp=yahoogeorss"));
		try {
			getParser().parse();
			setTitle(getParser().getChannel().getTitle());
			setItems(getParser().getChannel().getItems());
			setChannelAdapter(new ChannelAdapter(this, getItems()));
			getListView().setAdapter(getChannelAdapter());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setListView(ListView listView) {
		m_ListView = listView;
	}
	public ListView getListView() {
		return m_ListView;
	}

	public void setParser(RssParser parser) {
		m_Parser = parser;
	}

	public RssParser getParser() {
		return m_Parser;
	}
	
	public void setItems(List<Item> items) {
		m_Items = items;
	}

	public List<Item> getItems() {
		return m_Items;
	}

	public void setChannelAdapter(ChannelAdapter channelAdapter) {
		m_ChannelAdapter = channelAdapter;
	}

	public ChannelAdapter getChannelAdapter() {
		return m_ChannelAdapter;
	}

	private ListView m_ListView;
	private RssParser m_Parser;
	private List<Item> m_Items;
	private ChannelAdapter m_ChannelAdapter;

}
