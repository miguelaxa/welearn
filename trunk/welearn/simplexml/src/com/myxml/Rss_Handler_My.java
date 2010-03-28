package com.myxml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Rss_Handler_My extends DefaultHandler {

	// ===========================================================
	// Fields
	// ===========================================================

	private boolean in_outertag = false;
	private boolean in_innertag = false;
	private boolean in_mytag = false;
//	private StringBuilder builder;
	// names of the XML tags

	private Rss_My_DataSet myParsedExampleDataSet = new Rss_My_DataSet();

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public Rss_My_DataSet getParsedData() {
		return this.myParsedExampleDataSet;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	@Override
	public void startDocument() throws SAXException {
		this.myParsedExampleDataSet = new Rss_My_DataSet();
		// super.startDocument();
        // messages = new ArrayList<Message>();
		// builder = new StringBuilder();
	}

	@Override
	public void endDocument() throws SAXException {
		// Nothing to do
	}

	/**
	 * Gets be called on opening tags like: <tag> Can provide attribute(s), when
	 * xml was like: <tag attribute="attributeValue">
	 */
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		if (localName.equals("chanel")) {
			this.in_outertag = true;
		} else if (localName.equals("item")) {
			this.in_innertag = true;
		} else if (localName.equals("title")) {
			this.in_mytag = true;
		} else if (localName.equals("link")) {
			this.in_mytag = true;
		} else if (localName.equals("ymaps:Address")) {
			this.in_mytag = true;
		} else if (localName.equals("tagwithnumber")) {
			// Extract an Attribute
			String attrValue = atts.getValue("thenumber");
			int i = Integer.parseInt(attrValue);
			myParsedExampleDataSet.setExtractedInt(i);
		}
	}

	/**
	 * Gets be called on closing tags like: </tag>
	 */
	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		if (localName.equals("chanel")) {
			this.in_outertag = false;
		} else if (localName.equals("item")) {
			this.in_innertag = false;
		} else if (localName.equals("title")) {
			this.in_mytag = false;
		} else if (localName.equals("link")) {
			this.in_innertag = false;
		} else if (localName.equals("ymaps:Address")) {
			this.in_mytag = false;
		} else if (localName.equals("tagwithnumber")) {
			// Nothing to do here
		}
	}

	/**
	 * Gets be called on the following structure: <tag>characters</tag>
	 */
	@Override
	public void characters(char ch[], int start, int length)
			throws SAXException {
		if (this.in_mytag) {
			myParsedExampleDataSet.setExtractedString(new String(ch, start,
					length));
		}
		
		//super.characters(ch, start, length);
		//builder.append(ch, start, length);
	}
}