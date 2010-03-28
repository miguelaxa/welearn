package com.myxml;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Rss_My_DataSet {

	private String extractedString = null;
	private int extractedInt = 0;
	
	static final String CHANNEL = "channel";
	static final  String LINK = "link";
	static final  String ADDR = "ymaps:Address";
	static final  String TITLE = "title";
	static final  String ITEM = "item";
	
	static SimpleDateFormat FORMATTER = new SimpleDateFormat(
			"EEE, dd MMM yyyy HH:mm:ss Z");
	private String title;
	private URL link;
	private String addr;
	private Date date;

	public String getExtractedString() {
		return extractedString;
	}

	public void setExtractedString(String extractedString) {
		this.extractedString = extractedString;
	}

	public int getExtractedInt() {
		return extractedInt;
	}

	public void setExtractedInt(int extractedInt) {
		this.extractedInt = extractedInt;
	}

	// /mig
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title.trim();
	}

	// getters and setters omitted for brevity
	public URL getLink() {
		return link;
	}

	public void setLink(String link) {
		try {
			this.link = new URL(link);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public String getaddr() {
		return addr;
	}

	public void setaddr(String addr) {
		this.addr = addr.trim();
	}

	public String getDate() {
		return FORMATTER.format(this.date);
	}

	public void setDate(String date) {
		// pad the date if necessary
		while (!date.endsWith("00")) {
			date += "0";
		}
		try {
			this.date = FORMATTER.parse(date.trim());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

	}

	 public String toString() {
	 return "ExtractedString = "
	 + this.extractedString
	 + "\nExtractedInt = "
	 + this.extractedInt;
	 }

	/*public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Title: ");
		sb.append(title);
		sb.append('\n');
		sb.append("Date: ");
		sb.append(this.getDate());
		sb.append('\n');
		sb.append("Link: ");
		sb.append(link);
		sb.append('\n');
		sb.append("Addr: ");
		sb.append(addr);
		return sb.toString();

	}*/
}