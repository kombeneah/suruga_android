package com.suruga.tabandroid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.suruga.tabandroid.listview.LazyAdapter;
import com.suruga.tabandroid.listview.XMLParser;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ItemActivity extends Activity {
	// All static variables
	//static final String URL = "http://api.androidhive.info/music/music.xml";
	// XML node keys
	static final String KEY_SONG = "song"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_TITLE = "title";
	static final String KEY_ARTIST = "artist";
	static final String KEY_DURATION = "duration";
	static final String KEY_THUMB_URL = "thumb_url";

	ListView list;
	LazyAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.suruga.tabandroid.R.layout.item_layout);

		ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

		try {
			
			XMLParser parser = new XMLParser();

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder
					.parse(getAssets().open("house.xml"));
			NodeList nl = doc.getElementsByTagName(KEY_SONG);
			
			// looping through all song nodes <song>
			for (int i = 0; i < nl.getLength(); i++) {
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				Element e = (Element) nl.item(i);
				// adding each child node to HashMap key => value
				map.put(KEY_ID, parser.getValue(e, KEY_ID));
				map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
				map.put(KEY_ARTIST, parser.getValue(e, KEY_ARTIST));
				map.put(KEY_DURATION, parser.getValue(e, KEY_DURATION));
				map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));

				// adding HashList to ArrayList
				songsList.add(map);
			}

			list = (ListView) findViewById(com.suruga.tabandroid.R.id.list);

			// Getting adapter by passing xml data ArrayList
			adapter = new LazyAdapter(this, songsList);
			list.setAdapter(adapter);

			// Click event for single list row
			list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

				}
			});
		}

		catch (IOException ex) {
			Log.e("Error", ex.getMessage());
		} catch (Exception ex) {
			Log.e("Error", "Loading exception");
		}

		
		//String xml = parser.getXmlFromUrl(URL); // getting XML from URL
		//Document doc = parser.getDomElement(xml); // getting DOM element
		// DocumentBuilderFactory docBuilderFactory =
		// DocumentBuilderFactory.newInstance();
		// DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		// Document doc = docBuilder.parse
		// (getAssets().open("weatherdata.xml"));
		// InputStream is = res.openRawResource(R.raw.fileName);
		// xr.parse(new InputSource(is));

		
		
	}
}