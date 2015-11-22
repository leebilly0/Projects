package com.example.assignment6;

import java.util.Calendar;

import android.app.IntentService;
import android.content.Intent;

/**
 * This is the PhoneObserver class that is meant
 * to handle the intent being sent by the user.
 * @author Billy
 * July 30, 2014
 */
public class PhoneObserver extends IntentService {
	
	/** Default constructor
	 * 
	 */
	public PhoneObserver()
	{
		super("Observer");
	}
	

	/* (non-Javadoc)
	 * @see android.app.IntentService#onHandleIntent(android.content.Intent)
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		
		//receives date and time from MainActivit
		int year = intent.getIntExtra("year", 1);
		int month = intent.getIntExtra("month", 1);
		int day = intent.getIntExtra("day", 1);
		int hour = intent.getIntExtra("hour", 1);
		int minute = intent.getIntExtra("minute", 1);
		double millisecondConversion;
		Calendar calendar = Calendar.getInstance();
		
		minute = minute + (hour * 60);	//converts the hours to minutes
		millisecondConversion = minute * 60000;	//calculate milliseconds of minutes
		
		try {
			while (true)
			{
				Thread.sleep(30000);	//sleeps for 30 seconds
				
				//if time left is less than 30 seconds and date matches
				if ((millisecondConversion - System.currentTimeMillis() < 30000) && 
						((year == calendar.get(Calendar.YEAR)) && (month == calendar.get(Calendar.MONTH)) 
								&& (month == calendar.get(Calendar.MONTH)) && 
								(day == calendar.get(Calendar.DAY_OF_MONTH))))
				{
					//sets up broadcast to send
					Intent broadcastIntent = new Intent();
					broadcastIntent.setAction(MainActivity.PhoneCall.ACTION_RESP);
					broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
					broadcastIntent.putExtra(MainActivity.PHONE_TIME_LEFT,(int)
							((millisecondConversion - System.currentTimeMillis()) / 60000));
					sendBroadcast(broadcastIntent);
				}
				
			}
			} catch(InterruptedException ie){
				}
		
	}

}
