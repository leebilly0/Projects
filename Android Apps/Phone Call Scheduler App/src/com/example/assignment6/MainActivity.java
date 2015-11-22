package com.example.assignment6;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * This is the MainActivity. The purpose of this
 * program/app is to automatically make a phone call
 * that was scheduled by the user. It should be noted that
 * if a phone call is scheduled and canceled, the app
 * must be restarted completely for it to work again.
 * The app will only schedule one phone call.
 * @author Billy Lee
 * July 30, 2014
 *
 */
public class MainActivity extends ActionBarActivity implements OnClickListener, 
DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
	
	private EditText phoneEditText;
	private DatePickerDialog dpResult;
	private TimePickerDialog tpResult;
	private Button scheduleButton;
	private Button cancelButton;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private String number;
	private String errorMessage;
	private String cancelMessage;
	private boolean madePhoneCall;
	private boolean phoneCallScheduled = false;
	public static final String PHONE_TIME_LEFT = "PHONE_TIME_LEFT";
	Intent intent;
	public class PhoneCall extends BroadcastReceiver {
		
		public static final String ACTION_RESP = 
				"com.example.assignment6.PHONE_CALL";

		/* (non-Javadoc)
		 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
		 */
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			int timeLeft = bundle.getInt(PHONE_TIME_LEFT);
			
			//if time expires and phonecall has not been made, make phonecall
			if (timeLeft <= 0 && !madePhoneCall)
			{
				madePhoneCall = true;
				Intent intentPhone = new Intent(Intent.ACTION_CALL);
				intentPhone.setData(Uri.parse("tel:" + number));
				startActivity(intentPhone);
				finish();
			}
		}
		
	}
	
	/* (non-Javadoc)
	 * @see android.support.v7.app.ActionBarActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		phoneEditText  = (EditText) findViewById(R.id.edittextphone);
		scheduleButton = (Button) findViewById(R.id.schedulecallbutton);
		cancelButton = (Button) findViewById(R.id.cancelbutton);
		errorMessage = this.getResources().getString(R.string.errorMessage);
		cancelMessage = this.getResources().getString(R.string.cancelMessage);
		scheduleButton.setOnClickListener(this);
		cancelButton.setOnClickListener(this);
		intent = new Intent (this, PhoneObserver.class);
		IntentFilter filter = new IntentFilter(PhoneCall.ACTION_RESP);
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		PhoneCall phonecall = new PhoneCall();
		registerReceiver(phonecall, filter);
		 
	}
	
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override 
	public void onClick(View V){
		 if (V == scheduleButton)
		 {
			 if (!phoneCallScheduled)	//checks if a phone call has already been scheduled
			 {
				 //grabs date and time
				 final Calendar calendar = Calendar.getInstance();
				 year = calendar.get(Calendar.YEAR);
				 month = calendar.get(Calendar.MONTH);
				 day = calendar.get(Calendar.DAY_OF_MONTH);
				 hour = calendar.get(Calendar.HOUR_OF_DAY);
				 minute = calendar.get(Calendar.MINUTE);
				
				 //creates dialods
				 tpResult = new TimePickerDialog(this, this, hour, minute, DateFormat.is24HourFormat(this));
				 dpResult = new DatePickerDialog(this, this, year, month, day);	
			 
				 //show picker dialogs
				 if (!dpResult.isShowing())	
					 dpResult.show();
				 if (!tpResult.isShowing())
					 tpResult.show();
			 
				 number = phoneEditText.getText().toString();	//converts phone number to string
				 phoneCallScheduled = true;
			 }
			 else
				 Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();	//error message if already scheduled
			 
		 }
		 
		 if (V == cancelButton)
		 {
			 madePhoneCall = true;
			 Toast.makeText(this, cancelMessage, Toast.LENGTH_LONG).show();	//cancel message
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
	
	/**
	 * 
	 * After time is set, puts time into intent to send to PhoneObserver
	 * @param TimePicker the view for TimePicker
	 * @param hour chosen by user
	 * @param minute chosen by user
	 * 
	 */
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		this.hour = hourOfDay;
		this.minute = minute;
		intent.putExtra("hour", hour);
		intent.putExtra("minute", minute);
    }

	/**
	 * 
	 * After date is set, puts date into intent to send to PhoneObserver
	 * and starts the intent service
	 * @param DatePicker the view for DatePicker
	 * @param year year chosen by user
	 * @param month month chosen by user
	 * @param day day chosen by user
	 * 
	 */
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		this.year = year;
		this.month = monthOfYear;
		this.day = dayOfMonth;
		intent.putExtra("year", year);
		intent.putExtra("month", month);
		intent.putExtra("day", day);
		madePhoneCall = false;
		startService(intent);
		
	}
}
