package com.rappasocial.routinemanager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	
	Button btLatin, btStan;
	ExtendedApplication extApp;
	DBHelper dbHelper;
	SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btLatin = (Button) findViewById(R.id.btMenuLatin);
        btLatin.setOnClickListener(this);
        btStan = (Button) findViewById(R.id.btMenuStandard);
        btStan.setOnClickListener(this);
        extApp = (ExtendedApplication)getApplicationContext();
        dbHelper = extApp.dbHelper; 
	    db = extApp.db;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	
		
		switch (item.getItemId()) {
		case R.id.btFigures:
//			Intent intent = new Intent(MainActivity.this,
//					FiguresManagerActivity.class);
//			startActivity(intent);
			break;

		case R.id.btAddRoutine:
			Intent intent = new Intent(
					MainActivity.this,
					com.rappasocial.routinemanager.authenticator.AuthenticatorActivity.class);
			startActivity(intent);
			break;

		case R.id.settings_option_item:
			intent = new Intent(
					MainActivity.this,
					com.rappasocial.routinemanager.PrefActivity.class);
			startActivity(intent);
			break;

		}
		return super.onOptionsItemSelected(item);
		
		
	
	
		
		
	}
  
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btMenuLatin:
			// переменные для query
			    String[] columns = null;
			    String selection = null;
			    String[] selectionArgs = null;
			    //String groupBy = null;
			    //String having = null;
			   // String orderBy = null;
//
//			    // курсор
			    Cursor c = null;
//			  
//			    	
//			    	selection = dbHelper.DB_TABLE_DANCES + "." +dbHelper.COLUMN_DANCES_NAME + " = ?";
//			    	selectionArgs = new String[] { "Samba" };
//			  
//			      
//			      c = db.query(dbHelper.DB_TABLE_DANCES, null, selection, selectionArgs, null, null,
//			          null);
//			      
//			      if (c != null) {
//			          if (c.moveToFirst()) {
//			            
//			            do {
//			            	Dance locCurDance = new Dance(c.getInt(c.getColumnIndex(dbHelper.COLUMN_DANCES_ID)), c.getString(c.getColumnIndex(dbHelper.COLUMN_DANCES_NAME)));
//			            	extApp.setcurrentDance(locCurDance);
//			            	
//			              
//			              
//
//			            } while (c.moveToNext());
//			          }
//			          c.close();
//			        } else dbHelper.close();
			
			 
			Intent intent = new Intent(MainActivity.this, LatinMenuActivity.class);
		    startActivity(intent);
			break;
		case R.id.btMenuStandard:
			// переменные для query
			  //  String[] columns = null;
			  
			  
			    	
//			    	selection = dbHelper.DB_TABLE_DANCES + "." +dbHelper.COLUMN_DANCES_NAME + " = ?";
//			    	selectionArgs = new String[] { "Samba" };
//			  
//			      
//			      c = db.query(dbHelper.DB_TABLE_DANCES, null, selection, selectionArgs, null, null,
//			          null);
//			      
//			      if (c != null) {
//			          if (c.moveToFirst()) {
//			            
//			            do {
//			            	Dance locCurDance = new Dance(c.getInt(c.getColumnIndex(dbHelper.COLUMN_DANCES_ID)), c.getString(c.getColumnIndex(dbHelper.COLUMN_DANCES_NAME)));
//			            	extApp.setcurrentDance(locCurDance);
//			            	
//			              
//			              
//
//			            } while (c.moveToNext());
//			          }
//			          c.close();
//			        } else dbHelper.close();
//			
			 
			 intent = new Intent(MainActivity.this, StandardMenuActivity.class);
		    startActivity(intent);
			break;
		
		}
		
	}
    
   
    
}
