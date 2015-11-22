package com.example.assignment7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This program is an Note based App designed for the android system
 * This program is meant to simulate an actual notepad that allows
 * the user to save the notes onto their sd card. Furthermore,
 * the app also loads up all notes saved in the sd card for the
 * user to see and edit if needed.
 * I was able to build everything in one class without much
 * use of classes as well
 * @author Billy
 * August 13, 2014
 */
public class MainActivity extends Activity implements OnClickListener {

	private EditText title;
	private EditText body;
	private Button saveButton;
	private String saveMessage;
	private ListView list;
	private TextView noList;
	private String locationName;
	ArrayList<String> nameList;
	String[] titleNames;
	ArrayAdapter<String> adapter;
	 
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//initializing the variable
		title = (EditText) findViewById(R.id.title);
		body = (EditText) findViewById(R.id.body);
		saveButton = (Button) findViewById(R.id.save);
		saveMessage = this.getResources().getString(R.string.saveMessage);
		list = (ListView) findViewById(R.id.list);
		noList = (TextView) findViewById(R.id.emptyText);
		
		saveButton.setOnClickListener(this);
		File path = getStorageDirectory(this, "notes");
		locationName = path.toString();
		
		//I had to convert it because the arrayadapter didn't accept an arraylist of strings
		nameList = checkForFiles();	//initalizes arraylist 
		checkTitleName();	//initializes string array
		
		
		//adapter stuff for list view
		//had to use the stringarray because arraylist wasn't accepted by this adapter
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, titleNames);
		list.setAdapter(adapter); 

		list.setOnItemClickListener(new OnItemClickListener() {

			//onclick for listview
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// ListView Clicked item index
				int itemPosition     = position;

				// ListView Clicked item value
				String  itemValue    = (String) list.getItemAtPosition(position);
				
				//loads up title
				title.setText(itemValue);
				
				BufferedReader br = null;
				
				//Loads up note written in the file
				try {
					 
					String sCurrentLine;
		 
					br = new BufferedReader(new FileReader(locationName + "/" + itemValue));
		 
					while ((sCurrentLine = br.readLine()) != null) {
						body.setText(sCurrentLine);
					}
		 
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (br != null)br.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}

				// Show Alert 
				Toast.makeText(getApplicationContext(),
						"Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_SHORT)
						.show();
			}
		});

		
	}
	
	//converts from arraylist to string array
	public void checkTitleName()
	{
		titleNames = new String[nameList.size()];
		
		for (int i = 0; i < titleNames.length; i++)
		{
			titleNames[i] = nameList.get(i);
		}
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		//checks for sd card and saves file there
		try {
			if (isExternalStorageWritable()) {
				File path = getStorageDirectory(this, "notes");
				File file = new File(path, title.getText().toString() + ".txt");
				OutputStream fileOutput = new FileOutputStream(file);
				if (file.exists()) {
					fileOutput.write(body.getText().toString().getBytes());
					fileOutput.close();
					Toast.makeText(this, saveMessage, Toast.LENGTH_LONG).show();
				}
			}
		} catch (FileNotFoundException e) {
			Log.d("WRITE", "fnfe");
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("WRITE", "ioe");
			e.printStackTrace();
		}
		
		//updates lists on stringarray and arraylist
		nameList = checkForFiles();
		checkTitleName();
		
		//reinitializes new data to adapter
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, titleNames);
		
		//sets new list
		list.setAdapter(adapter); 
		
	}
	
	//checks file names in directory to make sure 
	//it saved correctly and returns it as an arraylist
	/**
	 * updates ArrayList<String> for file names in folder
	 * @return ArrayList<String>
	 */
	public ArrayList<String> checkForFiles()
	{
		ArrayList<String> fileNames = new ArrayList<String>();
		File folder = new File(locationName);
		File[] listOfFiles = folder.listFiles();
		
		//loop to update names of documents inside directory
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()){
				fileNames.add(listOfFiles[i].getName());
				noList.setText("");
			}
		}
		
		return fileNames;
	}

	/* Checks if external storage is available for read and write */
	/**
	 * checks to see if there is an external storage
	 * @return true if there is an external source to write on
	 * @return false if no external was detected
	 */
	public boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the storage directory for where to store documents
	 * and returns it as a file type
	 * @param context
	 * @param fileName
	 * @return File
	 */
	@SuppressLint("NewApi")
	public File getStorageDirectory(Context context, String fileName) {
		File file = new File(context.getExternalFilesDir(
				Environment.DIRECTORY_DOCUMENTS), fileName);
		if (!file.mkdirs()) {
			Log.e("WRITE", "Directory not created");
		}
		return file;
	}
	
	
}