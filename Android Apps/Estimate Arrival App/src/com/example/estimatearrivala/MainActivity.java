package com.example.estimatearrivala;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;


/**
 * @author Billy Lee
 * June 5, 2014
 *
 */
public class MainActivity extends Activity implements OnClickListener, OnEditorActionListener
{
	public EditText totalDistance;
	public EditText distanceTraveled;
	public EditText timeTraveled;
	public TextView remainingDistance;
	public TextView averageSpeed;
	public TextView estimatedArrival;
	public Button calculateButton;
	

	

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		totalDistance = (EditText) findViewById(R.id.totalDistanceInput);
		totalDistance.setOnEditorActionListener(this);
		distanceTraveled = (EditText) findViewById(R.id.distanceTraveledInput);
		distanceTraveled.setOnEditorActionListener(this);
		timeTraveled = (EditText) findViewById(R.id.timeTraveledInput);
		timeTraveled.setOnEditorActionListener(this);
		remainingDistance = (TextView) findViewById(R.id.remainingDistanceOutput);
		averageSpeed = (TextView) findViewById(R.id.averageSpeedOutput);
		estimatedArrival = (TextView) findViewById(R.id.estimatedArrivalTimeOutput);
		calculateButton = (Button) findViewById(R.id.calculateButton);
		calculateButton.setOnClickListener(this);
		//builder = new AlertDialog.Builder(this);
		
	}
	
	/**
	 *  (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v)
	{
		double remainAnswer;
		double avgSpeedAnswer;
		double timeAnswer;
		long timeETA;
		String hours;
		String amPm = "AM";
		
		
		if (v == calculateButton)
		{
			//checks for empty totalDistance
			if (totalDistance.getText().toString().equals("") || totalDistance.getText().toString()
					.equals("0"))
			{
				totalDistance.setText("0");
				distanceTraveled.setText("0");
				timeTraveled.setText("0");
			}
			else
			{
				//checks for other errors that might lead to application crashing
				if (distanceTraveled.getText().toString().equals(""))
					distanceTraveled.setText("0");
				if (timeTraveled.getText().toString().equals(""))
					timeTraveled.setText("0");
			}
		}
		
		//Format for answers
		DecimalFormat df = new DecimalFormat("######.##");
		
		//calculates distance left
		remainAnswer = remainingDistanceCalc(Double.parseDouble(totalDistance.getText().toString()), 
				Double.parseDouble(distanceTraveled.getText().toString()));
		remainingDistance.setText(df.format(remainAnswer) + " Miles");
		
		//calculates average speed
		avgSpeedAnswer = averageSpeedCalc(Double.parseDouble(distanceTraveled.getText().toString()),
				Double.parseDouble(timeTraveled.getText().toString()));
		averageSpeed.setText(df.format(avgSpeedAnswer) + " Miles/Hour");
		
		//Calculates time of travel
		timeAnswer = calculateTime(remainAnswer, avgSpeedAnswer);
		
		//adds current time with time estimated to reach destination
		timeETA = addEstimatedTimeToRealTime(System.currentTimeMillis(), timeAnswer);
	    
		//Initializes a Gregorian calendar
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTimeInMillis(timeETA);	//sets calendar to new calculated time
	    
	    
	    if (calendar.get(Calendar.AM_PM) == 1)
	    	amPm = "PM";	//finds out if time is AM or PM
	    
	    //because the calendar pulls midnight and noon as 
	    if (calendar.get(Calendar.HOUR) == 0)
	    {
	    	hours = "12";	//if hour is midnight or noon, set hour to 12 and print
	    	estimatedArrival.setText(hours + ":" + 
					calendar.get(Calendar.MINUTE) + " " + amPm);
	    }
	    else
	    	estimatedArrival.setText(calendar.get(Calendar.HOUR) + ":" + 
	    			calendar.get(Calendar.MINUTE) + " " + amPm);
			
	}
	
	/**
	 * Adds current time with estimated time it takes
	 * to reach destination and converts to milliseconds 
	 * @param currentMillis_in
	 * @param time_in
	 * @return new calculated time for arrival in milliseconds
	 */
	public long addEstimatedTimeToRealTime(long currentMillis_in, double time_in)
	{
		return (long) (currentMillis_in + (time_in*3600000));
	}
	
	/**
	 * Calculates remaining distance
	 * @param totalDistance_in
	 * @param distanceTraveled_in
	 * @return remaining distance
	 */
	public double remainingDistanceCalc(double totalDistance_in, double distanceTraveled_in)
	{
		return (totalDistance_in - distanceTraveled_in);
	}
	
	/**
	 * Calculates average speed
	 * @param distanceTraveled_in
	 * @param timeTraveled_in
	 * @return average speed
	 */
	public double averageSpeedCalc(double distanceTraveled_in, double timeTraveled_in)
	{
		return (distanceTraveled_in / timeTraveled_in);
	}
	
	/**
	 * Calculates time it takes to reach the remaining distance
	 * @param remainingAnswer_in
	 * @param avgSpeedAnswer_in
	 * @return time left
	 */
	public double calculateTime(double remainingAnswer_in, double avgSpeedAnswer_in)
	{
		return (remainingAnswer_in/avgSpeedAnswer_in);
	}
	

	/* (non-Javadoc)
	 * @see android.widget.TextView.OnEditorActionListener#onEditorAction(android.widget.TextView, int, android.view.KeyEvent)
	 */
	public boolean onEditorAction(TextView view, int actionId, KeyEvent event) 
	{
		return true;
	}
	
	
	/**
	 * On Resume, reset all text back to blank
	 */
	public void onResume()
	{
		super.onResume();
		totalDistance.setText("");
		distanceTraveled.setText("");
		timeTraveled.setText("");
		remainingDistance.setText("");
		averageSpeed.setText("");
		estimatedArrival.setText("");
	}

}
