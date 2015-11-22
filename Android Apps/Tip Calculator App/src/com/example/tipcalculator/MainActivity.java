package com.example.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends Activity implements OnClickListener,
		OnEditorActionListener {
	private EditText costOfFoodEditText;
	private EditText tipPercent;
	private TextView tipAmountTextView;
	private TextView totalAmountTextView;
	private Button calculateButton;


	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tipAmountTextView = (TextView) findViewById(R.id.TipAmountText);
		totalAmountTextView = (TextView) findViewById(R.id.TotalAmountText);
		costOfFoodEditText = (EditText) findViewById(R.id.costFoodBox);
		costOfFoodEditText.setOnEditorActionListener(this);
		tipPercent = (EditText) findViewById(R.id.editPercent);
		tipPercent.setOnEditorActionListener(this);
		calculateButton = (Button) findViewById(R.id.calculateButton);
		calculateButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) 
	{
		if (v == calculateButton)
		{
			if (costOfFoodEditText.getText().toString().equals(null) || costOfFoodEditText.getText().toString().equals(""))
			{
				tipAmountTextView.setText("0.00");
				totalAmountTextView.setText("0.00");
			}
			else
			{
				if (tipPercent.getText().toString().equals(null) || tipPercent.getText().toString().equals(""))
				{
					tipPercent.setText("0");
				}
				double foodCost = Double.parseDouble(costOfFoodEditText.getText().toString()); 
				double tip = foodCost * (Double.parseDouble(tipPercent.getText().toString())/100);
				double totalAmount = foodCost + tip; 
				tipAmountTextView.setText(String.format("%5.2f", tip)); 
				totalAmountTextView.setText(String.format("%5.2f", totalAmount));
			}
		}

	}

	public boolean onEditorAction(TextView view, int actionId, KeyEvent event) 
	{
		return true;
	}

}