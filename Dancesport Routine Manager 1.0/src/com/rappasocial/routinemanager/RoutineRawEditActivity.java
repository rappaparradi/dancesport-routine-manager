package com.rappasocial.routinemanager;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RoutineRawEditActivity extends Activity implements OnTouchListener, OnClickListener,
OnFocusChangeListener {
	
	ExtendedApplication extApp;
	EditText etTiming;
	EditText etComment;
	Button btSaveRoutineRaw;
	TextView tvCurFigureName;
	private Button mBSpace, mBdone, mBack, mBChange, mNum;
	private RelativeLayout mLayout, mKLayout;
	private boolean isEdit = true;

	private int w, mWindowWidth;

	private Button mB[] = new Button[12];
	
	public void onClick(View v) {
		
		
		if (v == mBdone) {

			disableKeyboard();

//		} else if (v != mBdone && v != mBack && v != mBChange && v != mNum) {
//			addText(v);


		} else if (v == mBack) {
			isBack(v);
		} else if (v == btSaveRoutineRaw) {
		     
			ArrayList<RoutineRaw> Localroutine_rawsBufferArray;
			if (extApp.currentGender == 1) {
				Localroutine_rawsBufferArray = this.extApp.mRoutine_rawsBufferArray;
			} else {

				Localroutine_rawsBufferArray = this.extApp.wRoutine_rawsBufferArray;
			}
			RoutineRaw rrBuf = Localroutine_rawsBufferArray.get(extApp.currentRoutineRawId);
			rrBuf.timing = etTiming.getText().toString();
			rrBuf.comment = etComment.getText().toString();
			Localroutine_rawsBufferArray.set(extApp.currentRoutineRawId, rrBuf);
			extApp.isRoutineModified = true;
			finish();
			
		} 
		

		
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
			setContentView(R.layout.edit_routine_raw);
		
			extApp = (ExtendedApplication)getApplicationContext();
		
			etTiming = (EditText) findViewById(R.id.etTiming);
			etComment = (EditText) findViewById(R.id.etComment);
			tvCurFigureName = (TextView) findViewById(R.id.tvCurFigureName);
			btSaveRoutineRaw = (Button) findViewById(R.id.btSaveRoutineRaw);
			btSaveRoutineRaw.setOnClickListener(this);
		
			etTiming.setOnTouchListener(this);
			
			etTiming.setText(extApp.editTimingBuffer);
			etComment.setText(extApp.editCommentBuffer);
//			etTiming.setOnClickListener(this);
//			etTiming.setOnFocusChangeListener(this);
			
			setKeys();
//			setFrow();
//			setSrow();
//			setTrow();
//			setForow();
		
			mLayout = (RelativeLayout) findViewById(R.id.xK1);
			mKLayout = (RelativeLayout) findViewById(R.id.xKeyBoard);
		
	
		
	}
	
	private void setKeys() {
		mWindowWidth = getWindowManager().getDefaultDisplay().getWidth(); // getting
		// window
		// height
		// getting ids from xml files
		mB[0] = (Button) findViewById(R.id.xA);
		mB[1] = (Button) findViewById(R.id.xAnd);
		mB[2] = (Button) findViewById(R.id.xQ);
		mB[3] = (Button) findViewById(R.id.xS);
		mB[4] = (Button) findViewById(R.id.x1);
		mB[5] = (Button) findViewById(R.id.x2);
		mB[6] = (Button) findViewById(R.id.x3);
		mB[7] = (Button) findViewById(R.id.x4);
		mB[8] = (Button) findViewById(R.id.x5);
		mB[9] = (Button) findViewById(R.id.x6);
		mB[10] = (Button) findViewById(R.id.x7);
		mB[11] = (Button) findViewById(R.id.x8);
		mBSpace = (Button) findViewById(R.id.xSpace);
		mBdone = (Button) findViewById(R.id.xDone);
		mBack = (Button) findViewById(R.id.xBack);
		for (int i = 0; i < mB.length; i++)
			mB[i].setOnClickListener(this);
		mBSpace.setOnClickListener(this);
		mBdone.setOnClickListener(this);
		mBack.setOnClickListener(this);


	}
	
	public boolean onTouch(View v, MotionEvent event) {
	
		if (event.getAction() == MotionEvent.ACTION_DOWN && v == etTiming) {
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            extApp.editTimingBuffer = etTiming.getText().toString();
            Intent intent = new Intent(this, TimingInputActivity.class);
            this.startActivity(intent);
            return true;

		}

		return true;
	}
	
	// enabling customized keyboard
		private void enableKeyboard() {

			mLayout.setVisibility(RelativeLayout.VISIBLE);
			mKLayout.setVisibility(RelativeLayout.VISIBLE);

		}

		// Disable customized keyboard
		private void disableKeyboard() {
			mLayout.setVisibility(RelativeLayout.INVISIBLE);
			mKLayout.setVisibility(RelativeLayout.INVISIBLE);

		}

		private void hideDefaultKeyboard() {
			getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		}
		
		private void addText(View v) {
			if (isEdit == true) {
				String b = "";
				b = (String) v.getTag();
				if (b != null) {
					// adding text in Edittext
					etTiming.append(b);

				}
			}


		}
		
		public void onFocusChange(View v, boolean hasFocus) {
			if (v == etTiming && hasFocus == true) {
				isEdit = true;


			} else {
				
				isEdit = false;
				
			}

		}
		
		private void isBack(View v) {
			if (isEdit == true) {
				CharSequence cc = etTiming.getText();
				if (cc != null && cc.length() > 0) {
					{
						etTiming.setText("");
						etTiming.append(cc.subSequence(0, cc.length() - 1));
					}

				}
			}

	
		}
		
		@Override
	 	protected void onResume() {
	 		super.onResume();
	 		etTiming.setText(extApp.editTimingBuffer);
	 		extApp.editTimingBuffer = "";
	 	
	 		
	 	}

}
