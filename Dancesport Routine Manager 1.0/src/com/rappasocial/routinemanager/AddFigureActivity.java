package com.rappasocial.routinemanager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFigureActivity extends Activity implements OnClickListener {
	
	ExtendedApplication extApp;
	EditText etFifuresName;
	EditText etFifuresDescription;
	Button btAddnewFigure;
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btAddnewFigure:
			ContentValues cv = new ContentValues();
		     
		    cv.put(extApp.dbHelper.COLUMN_FIGURES_NAME, etFifuresName.getText().toString());
		    cv.put(extApp.dbHelper.COLUMN_FIGURES_DESCRIPTION, etFifuresDescription.getText().toString());
		    cv.put(extApp.dbHelper.COLUMN_FIGURES_DANCE_ID, extApp.getcurrentDance().id);
		    extApp.db.insert(extApp.dbHelper.DB_TABLE_FIGURES, null, cv);
		    Context context = getApplicationContext();
		    CharSequence text = "Фигура '" + etFifuresName.getText().toString() + "' добавлена";
		    int duration = Toast.LENGTH_SHORT;
		    Toast.makeText(context, text, duration).show();
		    finish();
			break;

	
		}
		
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_figure);
		
		extApp = (ExtendedApplication)getApplicationContext();
		
		etFifuresName = (EditText) findViewById(R.id.etFifuresName);
		etFifuresDescription = (EditText) findViewById(R.id.etFifuresName);
		btAddnewFigure = (Button) findViewById(R.id.btAddnewFigure);
		btAddnewFigure.setOnClickListener(this);
		
	}

}
