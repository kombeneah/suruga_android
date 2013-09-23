package com.suruga.tabandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SettingsActivity extends Activity implements OnCheckedChangeListener {

	private Spinner spinner1;
	//The segment selection button
	SegmentedRadioGroup segmentText;
	Toast mToast;
	EditText editText1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_layout);
		
		segmentText = (SegmentedRadioGroup) findViewById(R.id.segment_text);
        segmentText.setOnCheckedChangeListener(this);

		addListenerOnSpinnerItemSelection();
		segmentText = (SegmentedRadioGroup) findViewById(R.id.segment_text);
        segmentText.setOnCheckedChangeListener(this);

        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
	}
	
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (group == segmentText) {
			if (checkedId == R.id.button_one) {
//				mToast.setText("One");
//				mToast.show();
			} else if (checkedId == R.id.button_three) {
			}
		} 
	}

	public void addListenerOnSpinnerItemSelection() {

		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		
	}
	
	

}