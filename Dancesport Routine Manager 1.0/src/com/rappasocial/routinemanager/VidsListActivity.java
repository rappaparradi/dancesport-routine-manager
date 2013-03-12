package com.rappasocial.routinemanager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;


import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.mobeta.android.dslv.DragSortListView;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class VidsListActivity extends FragmentActivity implements
		OnClickListener {

	ArrayList<Video> vids;
	BoxAdapterVideo boxAdapter;
	ExtendedApplication extApp;
	TextView tvCurDanceChar, tvRoutineTitle;
	Button btAddNewRoutune, btRLback;
	DragSortListView lvMain;
	Button btEditRoutine, btYT_id, btShareui;
	ImageButton btDeleteRoutine;
	LinearLayout llRoutinesListActionPanel;
	
    
	
	//action id
    private static final int ID_VLC    = 1;
    private static final int ID_MAIL   = 2;
    private static final int ID_SAFARI = 3;
    
    private static final int ID_EDIT_TITLE    = 1;
    private static final int ID_EDIT_ROUTINE   = 2;

	private DragSortListView.DropListener onDrop = new DragSortListView.DropListener() {
		public void drop(int from, int to) {
			Video item = boxAdapter.getItem(from);

			boxAdapter.remove(item);
			boxAdapter.insert(item, to);
		}
	};

	private DragSortListView.RemoveListener onRemove = new DragSortListView.RemoveListener() {
		public void remove(int which) {
			boxAdapter.remove(boxAdapter.getItem(which));
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vids_list);

		vids = new ArrayList<Video>();   
		extApp = (ExtendedApplication) getApplicationContext();

		fillData();
		boxAdapter = new BoxAdapterVideo(vids, VidsListActivity.this);

		lvMain = (DragSortListView) findViewById(android.R.id.list);

		lvMain.setDropListener(onDrop);
		lvMain.setRemoveListener(onRemove);

		lvMain.setAdapter(boxAdapter);
		
		YouTubePlayerSupportFragment youTubePlayerFragment =
		        (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.youtube_fragment);
		    youTubePlayerFragment.initialize(DeveloperKey.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {
				
				public void onInitializationSuccess(Provider arg0, YouTubePlayer player,
						boolean wasRestored) {
					if (!wasRestored) {
						player.cueVideo("nCgQDjiotG0");
					    }
					
				}
				
				public void onInitializationFailure(Provider arg0,
						YouTubeInitializationResult arg1) {
					// TODO Auto-generated method stub
					
				}
			});
		
	    
 
      
		
		
		
		btAddNewRoutune = (Button) findViewById(R.id.btAddNewRoutune);

		btAddNewRoutune.setOnClickListener(this);
		
		btRLback = (Button) findViewById(R.id.btRLback);

		btRLback.setOnClickListener(this);

		Dance curDance = extApp.getcurrentDance();

		llRoutinesListActionPanel = (LinearLayout) findViewById(R.id.llRoutinesListActionPanel);

		TextView tvCurDanceChar = (TextView) findViewById(R.id.tvCurDanceChar);

		if ((curDance.name).compareToIgnoreCase(DBHelper.Samba) == 0) {

			llRoutinesListActionPanel.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.action_panel_bg_orange));
			btAddNewRoutune.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_orange));
			btRLback.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_orange));
			tvCurDanceChar.setText("S");

		} else if ((curDance.name).compareToIgnoreCase(DBHelper.ChaCha) == 0) {

			llRoutinesListActionPanel.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.action_panel_bg_blue));
			btAddNewRoutune.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_blue));
			btRLback.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_blue));
			tvCurDanceChar.setText("Ch");

		} else if ((curDance.name).compareToIgnoreCase(DBHelper.Rumba) == 0) {

			llRoutinesListActionPanel.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.action_panel_bg_purple));
			btAddNewRoutune.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_purple));
			btRLback.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_purple));
			tvCurDanceChar.setText("R");

		} else if ((curDance.name)
				.compareToIgnoreCase(DBHelper.PasoDoble) == 0) {

			llRoutinesListActionPanel.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.action_panel_bg_red));
			btAddNewRoutune.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_red));
			btRLback.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_red));
			tvCurDanceChar.setText("P");

		} else if ((curDance.name).compareToIgnoreCase(DBHelper.Jive) == 0) {

			llRoutinesListActionPanel.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.action_panel_bg_yellow));
			btAddNewRoutune.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_yellow));
			btRLback.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_yellow));
			tvCurDanceChar.setText("J");

		} else if ((curDance.name).compareToIgnoreCase(DBHelper.Waltz) == 0) {

			llRoutinesListActionPanel.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.action_panel_bg_orange));
			btAddNewRoutune.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_orange));
			btRLback.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_orange));
			tvCurDanceChar.setText("W");

		} else if ((curDance.name).compareToIgnoreCase(DBHelper.Tango) == 0) {

			llRoutinesListActionPanel.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.action_panel_bg_red));
			btAddNewRoutune.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_red));
			btRLback.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_red));
			tvCurDanceChar.setText("T");

		} else if ((curDance.name)
				.compareToIgnoreCase(DBHelper.VienneseWaltz) == 0) {

			llRoutinesListActionPanel.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.action_panel_bg_purple));
			btAddNewRoutune.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_purple));
			btRLback.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_purple));
			tvCurDanceChar.setText("V");

		} else if ((curDance.name).compareToIgnoreCase(DBHelper.Foxtrot) == 0) {

			llRoutinesListActionPanel.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.action_panel_bg_blue));
			btAddNewRoutune.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_blue));
			btRLback.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_blue));
			tvCurDanceChar.setText("F");

		} else if ((curDance.name)
				.compareToIgnoreCase(DBHelper.Quickstep) == 0) {

			llRoutinesListActionPanel.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.action_panel_bg_yellow));
			btAddNewRoutune.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_yellow));
			btRLback.setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.custom_button_yellow));
			tvCurDanceChar.setText("Q");

		}
		
		TextView tvWindowTitle = (TextView) findViewById(R.id.tvWindowTitle);
		tvWindowTitle.setText(this.getString(R.string.win_title_routines));


	        //show on btn1
//	        Button btn1 = (Button) this.findViewById(R.id.btn1);
//	        btn1.setOnClickListener(new View.OnClickListener() {
//	            public void onClick(View v) {
//	                quickActionPopup1.show(v);
//	            }
//	        });

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		vids.clear();
		fillData();
		boxAdapter.notifyDataSetChanged();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.routineslist_activity, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.btFigures:

			Intent intent = new Intent(VidsListActivity.this,
					CrudFiguresActivity.class);
			startActivity(intent);
			break;

		}
		return super.onOptionsItemSelected(item);

	}

	/** Called when the activity is first created. */

	// РіРµРЅРµСЂРёСЂСѓРµРј РґР°РЅРЅС‹Рµ РґР»СЏ Р°РґР°РїС‚РµСЂР°
	void fillData() {

		// РїРµСЂРµРјРµРЅРЅС‹Рµ РґР»СЏ query
		// String[] columns = null;
		String selection = null;
		// String[] selectionArgs = null;
		// String groupBy = null;
		// String having = null;
		String orderBy = DBHelper.COLUMN_VIDS_TITLE;

		// РєСѓСЂСЃРѕСЂ
		Cursor c = null;
//		Dance curDance = extApp.getcurrentDance();
//		if (curDance != null) {
//
//			selection = extApp.dbHelper.COLUMN_ROUTINES_DANCE_ID + " = "
//					+ curDance.id;
//
//		} else {
//
//			selection = null;
//		}

		c = extApp.db.query(DBHelper.DB_TABLE_VIDS, null, selection,
				null, null, null, orderBy);

		if (c != null) {
			if (c.moveToFirst()) {

				do {

					vids.add(new Video(
							c.getInt(c
									.getColumnIndex(DBHelper.COLUMN_VIDS_ID)),
							c.getString(c
									.getColumnIndex(DBHelper.COLUMN_VIDS_YT_ID)),
							c.getString(c
									.getColumnIndex(DBHelper.COLUMN_VIDS_TITLE)),
							c.getString(c
									.getColumnIndex(DBHelper.COLUMN_VIDS_DESCRIPTION))));

				} while (c.moveToNext());
			}
			c.close();
		}

		// dbHelper.close();

	}

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btAddNewRoutune:

			Intent intent = new Intent(VidsListActivity.this,
					AddRoutineActivity.class);
			intent.putExtra("editmode", false);
			startActivity(intent);
			break;
			
		case R.id.btRLback:

			onBackPressed();
			break;

		}

	}

	private class ViewHolder {
		public YouTubePlayerView youtube_view;
		public TextView tvVidTitle;
		public TextView tvViddescription;
		public int position;
		
		
	}

	public class BoxAdapterVideo extends ArrayAdapter<Video> implements
			OnClickListener {
		Context ctx;
		LayoutInflater lInflater;
		ArrayList<Video> objects;
		ExtendedApplication extApp;
		LinearLayout llRoutineEditBG, llRoutineEditClickAble;
		

		public BoxAdapterVideo(List<Video> objects, Context ctx) {
			super(VidsListActivity.this, R.layout.vids_list_item,
					R.id.tvVidTitle, objects);
			extApp = (ExtendedApplication) ctx.getApplicationContext();
			this.ctx = ctx;
		}

		// BoxAdapterRoutine(Context context, ArrayList<Routine> routines) {
		// ctx = context;
		// objects = routines;
		// lInflater = (LayoutInflater) ctx
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// extApp = (ExtendedApplication)ctx.getApplicationContext();
		// }

		// public int getCount() {
		// return objects.size();
		// }
		//
		//
		// public Routine getItem(int position) {
		// return objects.get(position);
		// }
		//
		//
		// public long getItemId(int position) {
		// return position;
		// }

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			// View view = convertView;
			// if (view == null) {
			// view = lInflater.inflate(R.layout.routine_list_item, parent,
			// false);
			// }
			//
			// Routine p = getRoutine(position);
			//
			//
			// ((TextView)
			// view.findViewById(R.id.tvRoutineName)).setText(p.name);
			// Button btEditRoutine = (Button)
			// view.findViewById(R.id.btEditRoutine);
			// btEditRoutine.setTag(position);
			// btEditRoutine.setOnClickListener(this);
			// // ((Button)
			// view.findViewById(R.id.btEditRoutine)).setOnClickListener(this);
			// // btEditRoutine.setOnClickListener(this);
			//
			// return view;
			
			View v = super.getView(position, convertView, parent);

			if (v != convertView && v != null) {
				ViewHolder holder = new ViewHolder();

				
				TextView tvVidTitle = (TextView) v.findViewById(R.id.tvVidTitle);
				TextView tvViddescription = (TextView) v.findViewById(R.id.tvViddescription);
				
			

				v.setTag(holder);
			}

//			Button btEditRoutine = (Button) v.findViewById(R.id.btEditRoutine);
//			btEditRoutine.setTag(v.getTag());
//			btEditRoutine.setOnClickListener(this);
//
//			// LinearLayout llClickableRoutineItem = (LinearLayout)
//			// v.findViewById(R.id.llClickableRoutineItem);;
//			// llClickableRoutineItem.setTag(position);
//
//			btDeleteRoutine = (ImageButton) v
//					.findViewById(R.id.btDeleteRoutine);
//			btDeleteRoutine.setTag(v.getTag());
//			btDeleteRoutine.setOnClickListener(this);
			
			
//			btShareui = (Button) v
//					.findViewById(R.id.btShareui);
//			btShareui.setTag(v.getTag());
//			btShareui.setOnClickListener(this);
		
			

			
			
			ViewHolder holder = (ViewHolder) v.getTag();
//			

			

			
			
//			btYT_id = (Button) v
//					.findViewById(R.id.btYT_id);
//			btYT_id.setTag(v.getTag());
//			btYT_id.setOnClickListener(this);
//			
//			String albums = getItem(position).name;
//			String tvYT_id = getItem(position).yt_id;
//			long CreatedOn = getItem(position).created_on;
//			long ModifiedOn = getItem(position).modified_on;
//			
//			
//			java.sql.Date dtCreatedOn = new java.sql.Date(CreatedOn);
//			java.sql.Date dtModifiedOn = new java.sql.Date(ModifiedOn);
//			
//			
//			String fdtCreatedOn = (String) android.text.format.DateFormat.format("dd/MM/yyyy kk:mm:ss", dtCreatedOn);
//			String fdtModifiedOn = (String) android.text.format.DateFormat.format("dd/MM/yyyy kk:mm:ss", dtModifiedOn);
			

//			holder.youtube_view.setText(albums);
//			holder.tvVidTitle.setText(fdtCreatedOn);
//			
//			holder.tvViddescription.setText(tvViddescription);

			v.setOnClickListener(this);
			
			llRoutineEditBG = (LinearLayout) v.findViewById(R.id.llRoutineEditBG);
		      llRoutineEditClickAble = (LinearLayout) v.findViewById(R.id.llRoutineEditClickAble);
		      llRoutineEditClickAble.setTag(position);
		      llRoutineEditClickAble.setOnClickListener(this);
		      Dance curDance = extApp.getcurrentDance();
		        
		  
				if ((curDance.name).compareToIgnoreCase(DBHelper.Samba) == 0) {

					llRoutineEditBG.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_orange));
					llRoutineEditClickAble.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_clickable_orange));

					
				}else if ((curDance.name).compareToIgnoreCase(DBHelper.ChaCha) == 0) {
					
					llRoutineEditBG.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_blue));
					llRoutineEditClickAble.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_clickable_blue));
					
				}else if ((curDance.name).compareToIgnoreCase(DBHelper.Rumba) == 0) {
					
					llRoutineEditBG.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_purple));
					llRoutineEditClickAble.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_clickable_purple));
					
					
				}else if ((curDance.name).compareToIgnoreCase(DBHelper.PasoDoble) == 0) {
					
					llRoutineEditBG.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_red));
					llRoutineEditClickAble.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_clickable_red));
					
					
				}else if ((curDance.name).compareToIgnoreCase(DBHelper.Jive) == 0) {
					
					llRoutineEditBG.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_yellow));
					llRoutineEditClickAble.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_clickable_yellow));
					
					
				}else if ((curDance.name).compareToIgnoreCase(DBHelper.Waltz) == 0) {
					
					llRoutineEditBG.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_orange));
					llRoutineEditClickAble.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_clickable_orange));
					
					
				}else if ((curDance.name).compareToIgnoreCase(DBHelper.Tango) == 0) {
					
					llRoutineEditBG.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_red));
					llRoutineEditClickAble.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_clickable_red));
					
					
				}else if ((curDance.name).compareToIgnoreCase(DBHelper.VienneseWaltz) == 0) {
					
					llRoutineEditBG.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_purple));
					llRoutineEditClickAble.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_clickable_purple));
					
					
				}else if ((curDance.name).compareToIgnoreCase(DBHelper.Foxtrot) == 0) {
					
					llRoutineEditBG.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_blue));
					llRoutineEditClickAble.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_clickable_blue));
					
					
				}else if ((curDance.name).compareToIgnoreCase(DBHelper.Quickstep) == 0) {
					
					llRoutineEditBG.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_yellow));
					llRoutineEditClickAble.setBackgroundDrawable(getResources().getDrawable(R.drawable.listrow_bg_clickable_yellow));
					
					
				}
			
			holder.position = position;

			return v;

		}
		
		 
		  public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
//		    if (errorReason.isUserRecoverableError()) {
//		      errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
//		    } else {
//		      String errorMessage = String.format(getString(R.string.error_player), errorReason.toString());
//		      Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
//		    }
		  }
	

		Video getRoutine(int position) {
			return (getItem(position));
		}

		public void onClick(View v) {

			switch (v.getId()) {

			case R.id.btYT_id:
//				Animation animRotate = AnimationUtils.loadAnimation(ctx,
//						R.anim.anim_scale);
//				v.startAnimation(animRotate);
//				this.extApp.editTimingBuffer = routines.get((Integer) v
//						.getTag()).yt_id;
//				extApp.currentRoutine = getRoutine((Integer) v.getTag()); // /Very
//																			// very
//																			// KRUTO
//				Intent intent = new Intent(RoutinesListActivity.this,
//						YTLinkEditActivity.class);
//				
//				intent.putExtra("cur_routine_id", routines.get((Integer) v.getTag()).id);
//				
//				ctx.startActivity(intent);
//				ViewHolder holder = (ViewHolder) v.getTag();
//				holder.quickActionPopup1.show(v);
				break;
			
//			case R.id.btShareui:
//				try { // catches IOException below
//					
//					String subject = "Dancesport Routine Manager";
//					String body = "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/><html><body><table align=\"center\" border=\"1\" bordercolor=\"#ccc\" cellpadding=\"5\" cellspacing=\"0\" style=\"border-collapse:collapse;margin:10px 0 10px 15px;\">"
//							+ "<thead>"
//							+ "<tr>"
//							+ "<th scope=\"col\">в„–</th>"
//							+ "<th scope=\"col\">Р¤РёРіСѓСЂР°</th>"
//							+ "<th scope=\"col\">РљРѕРјРјРµРЅС‚Р°СЂРёР№</th>"
//							+ "<th scope=\"col\">РЎС‡РµС‚</th>"
//							+ "</tr>"
//							+ "</thead>"
//							+ "<tbody>";
//					for (int i = 0; i < routine_raws.size(); i++) {
//
//						// cv.put(extApp.dbHelper.COLUMN_ROUTINE_RAWS_ROUTINE_ID,
//						// extApp.currentRoutine.id);
//						String htmlfname = extApp.getFiguresNameByID(
//								routine_raws.get(i).figure_id,
//								extApp.getcurrentDance().id);
//						String htmlrawid = "" + (i + 1);
//						String htmltiming = routine_raws.get(i).timing;
//						String htmlcomment = routine_raws.get(i).comment;
//						// cv.put(extApp.dbHelper.COLUMN_ROUTINE_RAWS_GENDER,
//						// routine_raws.get(i).gender);
//						body = body + "<tr>" + "<td>" + htmlrawid + "</td>"
//								+ "<td>" + htmlfname + "</td>" + "<td>"
//								+ htmlcomment + "</td>" + "<td>" + htmltiming
//								+ "</td>" + "</tr>";
//
//					}
//					;
//
//					body = body + "</tbody>" + "</table></body></html>";
//
//		
//					String strFile = Environment.getExternalStorageDirectory().getAbsolutePath();
//					File myFile = new File(strFile + "/dsrm_exported.html");
//		            myFile.createNewFile();
//		            FileOutputStream fOut = new FileOutputStream(myFile);
//		            OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
//		            myOutWriter.append(body);
//		            myOutWriter.close();
//		            fOut.close();
////					FileOutputStream fOut = openFileOutput("dsrm_exported.htm",
//
//
//					final Intent emailIntent = new Intent(
//							android.content.Intent.ACTION_SEND);
//					emailIntent.setType("text/html");
//					emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
//							subject);
//					emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
//							"Р’Р°С€ РґРѕРєСѓРјРµРЅС‚ РІ С„РѕСЂРјР°С‚Рµ html РЅР°С…РѕРґРёС‚СЃСЏ РІРѕ РІР»РѕР¶РµРЅРёРё");
//					emailIntent.putExtra(Intent.EXTRA_STREAM,
//							Uri.parse("file://" + strFile + "/dsrm_exported.html"));
//					startActivity(Intent.createChooser(emailIntent, "Email:"));
//				} catch (IOException ioe) {
//					ioe.printStackTrace();
//				}
//				break;
//			
//			case R.id.btYT_id:
//				 animRotate = AnimationUtils.loadAnimation(ctx,
//						R.anim.anim_scale);
//				v.startAnimation(animRotate);
//				
//				ctx.startActivity(new Intent(Intent.ACTION_VIEW, 
//	                    Uri.parse(routines.get((Integer) v
//	    						.getTag()).yt_id)));
//				break;
//				break;
				
			case R.id.btEditRoutine:
//				Animation animRotate = AnimationUtils.loadAnimation(ctx,
//						R.anim.anim_scale);
//				v.startAnimation(animRotate);
//				holder = (ViewHolder) v.getTag();
//				holder.quickActionPopupEditRoutine.show(v);
////				holder = (ViewHolder) v.getTag();
////				extApp.currentRoutineid = holder.position; // /Very
////																			// very
////																			// KRUTO
////				Intent intent = new Intent(RoutinesListActivity.this,
////						AddRoutineActivity.class);
////				intent.putExtra("editmode", true);
////				
////				intent.putExtra("routines_name_buff", routines.get(holder.position).name);
////				intent.putExtra("cur_routine_id", routines.get(holder.position).id);
////				
////				ctx.startActivity(intent);
				break;

			case R.id.btDeleteRoutine:
//				animRotate = AnimationUtils.loadAnimation(ctx,
//						R.anim.anim_scale);
//				v.startAnimation(animRotate);
//				holder = (ViewHolder) v.getTag();
//				extApp.currentRoutineid = routines.get(holder.position).id; // /Very
//				this.extApp.currentRoutineRawId = holder.position; // very
//				// KRUTO
//				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//						VidsListActivity.this);
//
//				// set title
//				alertDialogBuilder.setTitle(R.string.deleting);
//
//				// set dialog message
//				alertDialogBuilder
//						.setMessage(R.string.deleting_question)
//						.setCancelable(true)
//						.setPositiveButton(R.string.Yes,
//								new DialogInterface.OnClickListener() {
//									public void onClick(DialogInterface dialog,
//											int id) {
//										DeleteCurrentRoutine();
//										Context context = getApplicationContext();
//										CharSequence text = getString(R.string.Deleted);
//										int duration = Toast.LENGTH_SHORT;
//										Toast.makeText(context, text, duration)
//												.show();
//
//									}
//								})
//						.setNegativeButton(R.string.No,
//								new DialogInterface.OnClickListener() {
//									public void onClick(DialogInterface dialog,
//											int id) {
//
//									}
//								});
//
//				// create alert dialog
//				AlertDialog alertDialog = alertDialogBuilder.create();
//
//				// show it
//				alertDialog.show();
				break;

			case R.id.llRoutineEditClickAble:
//				extApp.currentRoutineid = routines.get((Integer) v.getTag()).id;
//				Intent intent = new Intent(ctx, TabHostRoutineEditActivity.class);
//				ctx.startActivity(intent);
				break;

			}

		}

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

		}

	}

	void DeleteCurrentRoutine() {

//		extApp.db.delete(extApp.dbHelper.DB_TABLE_ROUTINE_RAWS,
//				extApp.dbHelper.COLUMN_ROUTINE_RAWS_ROUTINE_ID + "="
//						+ extApp.currentRoutineid, null);
//		extApp.db.delete(extApp.dbHelper.DB_TABLE_ROUTINES,
//				extApp.dbHelper.COLUMN_ROUTINES_ID + "="
//						+ extApp.currentRoutineid, null);
//		routines.remove(extApp.currentRoutineRawId);
//		boxAdapter.notifyDataSetChanged();

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	
	
	 
	  
	
	

}
