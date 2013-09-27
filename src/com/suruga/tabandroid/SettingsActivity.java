package com.suruga.tabandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

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
        
        editText1=(EditText) findViewById(R.id.editText1);
        AbsoluteLayout layout = (AbsoluteLayout) findViewById(R.id.layout);
        
        layout.setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideKeyboard(view);
                return false;
            }
        });
        
        


        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
	}
	
	protected void hideKeyboard(View view)
	{
	    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	    in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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