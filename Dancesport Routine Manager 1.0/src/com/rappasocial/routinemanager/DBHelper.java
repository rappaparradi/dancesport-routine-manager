package com.rappasocial.routinemanager;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class DBHelper extends SQLiteOpenHelper {

	private Context context;
	private static final String DB_NAME = "routineManagerDB";
	private static final int DB_VERSION = 1;
	public static final String DB_TABLE_DANCES = "dances";
	public static final String DB_TABLE_FIGURES = "figures";
	public static final String DB_TABLE_ROUTINES = "routines";
	public static final String DB_TABLE_ROUTINE_RAWS = "routine_raws";

	public static final String COLUMN_DANCES_ID = "_id";
	public static final String COLUMN_DANCES_NAME = "name";
	public static final String COLUMN_DANCES_LAT_or_ST = "lat_or_st";

	public static final String COLUMN_FIGURES_ID = "_id";
	public static final String COLUMN_FIGURES_ID_GLOBAL = "_id_global";
	public static final String COLUMN_FIGURES_NAME = "name";
	public static final String COLUMN_FIGURES_DESCRIPTION = "description";
	public static final String COLUMN_FIGURES_TIMING = "timing";
	public static final String COLUMN_FIGURES_YT_ID = "yt_id";
	public static final String COLUMN_FIGURES_DANCE_ID = "dance_id";
	public static final String COLUMN_FIGURES_CREATED_ON = "created_on";
	public static final String COLUMN_FIGURES_MODIFIED_ON = "modified_on";
	public static final String COLUMN_FIGURES_DEL_MARK = "del_mark";

	public static final String COLUMN_ROUTINES_ID = "_id";
	public static final String COLUMN_ROUTINES_ID_GLOBAL = "_id_global";
	public static final String COLUMN_ROUTINES_NAME = "name";
	public static final String COLUMN_ROUTINES_DANCE_ID = "dance_id";
	public static final String COLUMN_ROUTINES_YT_ID = "yt_id";
	public static final String COLUMN_ROUTINES_CREATED_ON = "created_on";
	public static final String COLUMN_ROUTINES_MODIFIED_ON = "modified_on";
	public static final String COLUMN_ROUTINES_DEL_MARK = "del_mark";

	public static final String COLUMN_ROUTINE_RAWS_ID = "_id";
	public static final String COLUMN_ROUTINE_RAWS_ROUTINE_ID = "routine_id";
	public static final String COLUMN_ROUTINE_RAWS_FIGURE_ID = "figure_id";
	public static final String COLUMN_ROUTINE_RAWS_TIMING = "timing";
	public static final String COLUMN_ROUTINE_RAWS_COMMENT = "comment";
	public static final String COLUMN_ROUTINE_RAWS_WEIGHT = "weihgt";
	public static final String COLUMN_ROUTINE_RAWS_GENDER = "gender";

	// Dances names
	public static final String Samba = "Samba";
	public static final String ChaCha = "Cha Cha Cha";
	public static final String Rumba = "Rumba";
	public static final String PasoDoble = "Paso Doble";
	public static final String Jive = "Jive";

	public static final String Waltz = "Waltz";
	public static final String Tango = "Tango";
	public static final String VienneseWaltz = "Viennese Waltz";
	public static final String Foxtrot = "Foxtrot";
	public static final String Quickstep = "Quickstep";

	public DBHelper(Context context) {

		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("create table " + DB_TABLE_DANCES + " (" + COLUMN_DANCES_ID
				+ " integer primary key autoincrement," + COLUMN_DANCES_NAME
				+ " text," + COLUMN_DANCES_LAT_or_ST + " boolean" + ");");

		db.execSQL("create table " + DB_TABLE_FIGURES + " ("
				+ COLUMN_FIGURES_ID + " integer primary key autoincrement,"
				+ COLUMN_FIGURES_ID_GLOBAL + " integer," + COLUMN_FIGURES_NAME
				+ " text," + COLUMN_FIGURES_DESCRIPTION + " text,"
				+ COLUMN_FIGURES_TIMING + " text," + COLUMN_FIGURES_YT_ID
				+ " text," + COLUMN_FIGURES_DEL_MARK + " boolean,"
				+ COLUMN_FIGURES_CREATED_ON + " long, "
				+ COLUMN_FIGURES_MODIFIED_ON + " long, "
				+ COLUMN_FIGURES_DANCE_ID + " integer, " + " FOREIGN KEY("
				+ COLUMN_FIGURES_DANCE_ID + ") REFERENCES dances("
				+ COLUMN_DANCES_ID + ")" + ");");

		db.execSQL("create table " + DB_TABLE_ROUTINES + " ("
				+ COLUMN_ROUTINES_ID + " integer primary key autoincrement,"
				+ COLUMN_ROUTINES_NAME + " text," + COLUMN_ROUTINES_DANCE_ID
				+ " integer, " + COLUMN_ROUTINES_YT_ID + " text,"
				+ COLUMN_ROUTINES_DEL_MARK + " boolean,"
				+ COLUMN_ROUTINES_CREATED_ON + " long, "
				+ COLUMN_ROUTINES_MODIFIED_ON + " long, " + "FOREIGN KEY("
				+ COLUMN_ROUTINES_DANCE_ID + ") REFERENCES dances("
				+ COLUMN_DANCES_ID + ")" + ");");

		db.execSQL("create table " + DB_TABLE_ROUTINE_RAWS + " ("
				+ COLUMN_ROUTINE_RAWS_ID
				+ "  integer primary key autoincrement,"
				+ COLUMN_ROUTINE_RAWS_ROUTINE_ID + " integer, "
				+ COLUMN_ROUTINE_RAWS_FIGURE_ID + " integer, "
				+ COLUMN_ROUTINE_RAWS_TIMING + " text,"
				+ COLUMN_ROUTINE_RAWS_COMMENT + "  text,"
				+ COLUMN_ROUTINE_RAWS_WEIGHT + "  integer, "
				+ COLUMN_ROUTINE_RAWS_GENDER + "  boolean, " + "FOREIGN KEY("
				+ COLUMN_ROUTINE_RAWS_ROUTINE_ID + ") REFERENCES routines("
				+ COLUMN_ROUTINES_ID + "), " + "FOREIGN KEY("
				+ COLUMN_ROUTINE_RAWS_FIGURE_ID + ") REFERENCES figures("
				+ COLUMN_FIGURES_ID + "));");

		InitializeDances(db);
		InitializeDefaultFigures(db);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	private void InitializeDances(SQLiteDatabase db) {

		ContentValues cv = new ContentValues();

		cv.put(this.COLUMN_DANCES_NAME, "Samba");
		cv.put(this.COLUMN_DANCES_LAT_or_ST, true);
		db.insert(this.DB_TABLE_DANCES, null, cv);

		cv.put(this.COLUMN_DANCES_NAME, "Cha Cha Cha");
		cv.put(this.COLUMN_DANCES_LAT_or_ST, true);
		db.insert(this.DB_TABLE_DANCES, null, cv);

		cv.put(this.COLUMN_DANCES_NAME, "Rumba");
		cv.put(this.COLUMN_DANCES_LAT_or_ST, true);
		db.insert(this.DB_TABLE_DANCES, null, cv);

		cv.put(this.COLUMN_DANCES_NAME, "Paso Doble");
		cv.put(this.COLUMN_DANCES_LAT_or_ST, true);
		db.insert(this.DB_TABLE_DANCES, null, cv);

		cv.put(this.COLUMN_DANCES_NAME, "Jive");
		cv.put(this.COLUMN_DANCES_LAT_or_ST, true);
		db.insert(this.DB_TABLE_DANCES, null, cv);

		cv.put(this.COLUMN_DANCES_NAME, "Waltz");
		cv.put(this.COLUMN_DANCES_LAT_or_ST, false);
		db.insert(this.DB_TABLE_DANCES, null, cv);

		cv.put(this.COLUMN_DANCES_NAME, "Tango");
		cv.put(this.COLUMN_DANCES_LAT_or_ST, false);
		db.insert(this.DB_TABLE_DANCES, null, cv);

		cv.put(this.COLUMN_DANCES_NAME, "Viennese Waltz");
		cv.put(this.COLUMN_DANCES_LAT_or_ST, false);
		db.insert(this.DB_TABLE_DANCES, null, cv);

		cv.put(this.COLUMN_DANCES_NAME, "Foxtrot");
		cv.put(this.COLUMN_DANCES_LAT_or_ST, false);
		db.insert(this.DB_TABLE_DANCES, null, cv);

		cv.put(this.COLUMN_DANCES_NAME, "Quickstep");
		cv.put(this.COLUMN_DANCES_LAT_or_ST, false);
		db.insert(this.DB_TABLE_DANCES, null, cv);

		cv.clear();

		// cv.put(this.COLUMN_FIGURES_NAME, "Виск вправо");
		// cv.put(this.COLUMN_FIGURES_DANCE_ID, 1);
		// db.insert(this.DB_TABLE_FIGURES, null, cv);
		//
		// cv.put(this.COLUMN_FIGURES_NAME, "Виск влево");
		// cv.put(this.COLUMN_FIGURES_DANCE_ID, 1);
		// db.insert(this.DB_TABLE_FIGURES, null, cv);
		//
		// cv.put(this.COLUMN_FIGURES_NAME, "Батукада");
		// cv.put(this.COLUMN_FIGURES_DANCE_ID, 1);
		// db.insert(this.DB_TABLE_FIGURES, null, cv);
		//
		// cv.put(this.COLUMN_FIGURES_NAME, "Наткуральный поворот");
		// cv.put(this.COLUMN_FIGURES_DANCE_ID, 1);
		// db.insert(this.DB_TABLE_FIGURES, null, cv);
		//
		// cv.put(this.COLUMN_FIGURES_NAME, "Стационарный шаг");
		// cv.put(this.COLUMN_FIGURES_DANCE_ID, 1);
		// db.insert(this.DB_TABLE_FIGURES, null, cv);
		//
		// cv.put(this.COLUMN_FIGURES_NAME, "Променадный бег");
		// cv.put(this.COLUMN_FIGURES_DANCE_ID, 1);
		// db.insert(this.DB_TABLE_FIGURES, null, cv);
		//
		// cv.put(this.COLUMN_FIGURES_NAME, "Вольта вправо");
		// cv.put(this.COLUMN_FIGURES_DANCE_ID, 1);
		// db.insert(this.DB_TABLE_FIGURES, null, cv);
		//
		// cv.put(this.COLUMN_FIGURES_NAME, "Поворот под рукой");
		// cv.put(this.COLUMN_FIGURES_DANCE_ID, 1);
		// db.insert(this.DB_TABLE_FIGURES, null, cv);

	}

	private void InitializeDefaultFigures(SQLiteDatabase db) {

		ContentValues cv = new ContentValues();
		int samba_id = 0;
		int chachacha_id = 0;
		int rumba_id = 0;
		int pasodoble_id = 0;
		int jive_id = 0;
		String[] columns = null;
		String selection = null;
		String[] selectionArgs = null;

		Cursor c = null;

		selectionArgs = new String[] { this.Samba };

		selection = this.DB_TABLE_DANCES + "." + this.COLUMN_DANCES_NAME
				+ " = ?";

		c = db.query(this.DB_TABLE_DANCES, null, selection, selectionArgs,
				null, null, null);

		if (c != null) {
			if (c.moveToFirst()) {

				do {
					samba_id = c
							.getInt(c.getColumnIndex(this.COLUMN_DANCES_ID));

				} while (c.moveToNext());
			}
			c.close();
		}
		;

		Resources res = context.getResources();
		String[] figures_samba_array = res
				.getStringArray(R.array.figures_samba);
		for (int i = 0; i < figures_samba_array.length; i++) {

			cv.put(this.COLUMN_FIGURES_NAME, figures_samba_array[i]);
			cv.put(this.COLUMN_FIGURES_DANCE_ID, samba_id);
			db.insert(this.DB_TABLE_FIGURES, null, cv);

		}

		selectionArgs = new String[] { this.ChaCha };

		selection = this.DB_TABLE_DANCES + "." + this.COLUMN_DANCES_NAME
				+ " = ?";

		c = db.query(this.DB_TABLE_DANCES, null, selection, selectionArgs,
				null, null, null);

		if (c != null) {
			if (c.moveToFirst()) {

				do {
					chachacha_id = c.getInt(c
							.getColumnIndex(this.COLUMN_DANCES_ID));

				} while (c.moveToNext());
			}
			c.close();
		}
		;

		String[] chachacha_samba_array = res
				.getStringArray(R.array.figures_chachacha);
		for (int i = 0; i < chachacha_samba_array.length; i++) {

			cv.put(this.COLUMN_FIGURES_NAME, chachacha_samba_array[i]);
			cv.put(this.COLUMN_FIGURES_DANCE_ID, chachacha_id);
			db.insert(this.DB_TABLE_FIGURES, null, cv);

		}
		
		selectionArgs = new String[] { this.Rumba };

		selection = this.DB_TABLE_DANCES + "." + this.COLUMN_DANCES_NAME
				+ " = ?";

		c = db.query(this.DB_TABLE_DANCES, null, selection, selectionArgs,
				null, null, null);

		if (c != null) {
			if (c.moveToFirst()) {

				do {
					rumba_id = c.getInt(c
							.getColumnIndex(this.COLUMN_DANCES_ID));

				} while (c.moveToNext());
			}
			c.close();
		}
		;

		String[] rumba_array = res
				.getStringArray(R.array.figures_rumba);
		for (int i = 0; i < rumba_array.length; i++) {

			cv.put(this.COLUMN_FIGURES_NAME, rumba_array[i]);
			cv.put(this.COLUMN_FIGURES_DANCE_ID, rumba_id);
			db.insert(this.DB_TABLE_FIGURES, null, cv);

		}
		
		selectionArgs = new String[] { this.PasoDoble };

		selection = this.DB_TABLE_DANCES + "." + this.COLUMN_DANCES_NAME
				+ " = ?";

		c = db.query(this.DB_TABLE_DANCES, null, selection, selectionArgs,
				null, null, null);

		if (c != null) {
			if (c.moveToFirst()) {

				do {
					pasodoble_id = c.getInt(c
							.getColumnIndex(this.COLUMN_DANCES_ID));

				} while (c.moveToNext());
			}
			c.close();
		}
		;

		String[] pasodoble_array = res
				.getStringArray(R.array.figures_pasodoble);
		for (int i = 0; i < pasodoble_array.length; i++) {

			cv.put(this.COLUMN_FIGURES_NAME, pasodoble_array[i]);
			cv.put(this.COLUMN_FIGURES_DANCE_ID, pasodoble_id);
			db.insert(this.DB_TABLE_FIGURES, null, cv);

		}
		
		selectionArgs = new String[] { this.Jive };

		selection = this.DB_TABLE_DANCES + "." + this.COLUMN_DANCES_NAME
				+ " = ?";

		c = db.query(this.DB_TABLE_DANCES, null, selection, selectionArgs,
				null, null, null);

		if (c != null) {
			if (c.moveToFirst()) {

				do {
					jive_id = c.getInt(c
							.getColumnIndex(this.COLUMN_DANCES_ID));

				} while (c.moveToNext());
			}
			c.close();
		}
		;

		String[] jive_array = res
				.getStringArray(R.array.figures_Jive);
		for (int i = 0; i < jive_array.length; i++) {

			cv.put(this.COLUMN_FIGURES_NAME, jive_array[i]);
			cv.put(this.COLUMN_FIGURES_DANCE_ID, jive_id);
			db.insert(this.DB_TABLE_FIGURES, null, cv);

		}
		// cv.put(this.COLUMN_FIGURES_NAME, "Виск вправо");
		// cv.put(this.COLUMN_FIGURES_DANCE_ID, 1);
		// db.insert(this.DB_TABLE_FIGURES, null, cv);
		//
		// cv.put(this.COLUMN_FIGURES_NAME, "Виск влево");
		// cv.put(this.COLUMN_FIGURES_DANCE_ID, 1);
		// db.insert(this.DB_TABLE_FIGURES, null, cv);
		//
		// cv.put(this.COLUMN_FIGURES_NAME, "Батукада");
		// cv.put(this.COLUMN_FIGURES_DANCE_ID, 1);
		// db.insert(this.DB_TABLE_FIGURES, null, cv);
		//
		// cv.put(this.COLUMN_FIGURES_NAME, "Наткуральный поворот");
		// cv.put(this.COLUMN_FIGURES_DANCE_ID, 1);
		// db.insert(this.DB_TABLE_FIGURES, null, cv);
		//
		// cv.put(this.COLUMN_FIGURES_NAME, "Стационарный шаг");
		// cv.put(this.COLUMN_FIGURES_DANCE_ID, 1);
		// db.insert(this.DB_TABLE_FIGURES, null, cv);
		//
		// cv.put(this.COLUMN_FIGURES_NAME, "Променадный бег");
		// cv.put(this.COLUMN_FIGURES_DANCE_ID, 1);
		// db.insert(this.DB_TABLE_FIGURES, null, cv);
		//
		// cv.put(this.COLUMN_FIGURES_NAME, "Вольта вправо");
		// cv.put(this.COLUMN_FIGURES_DANCE_ID, 1);
		// db.insert(this.DB_TABLE_FIGURES, null, cv);
		//
		// cv.put(this.COLUMN_FIGURES_NAME, "Поворот под рукой");
		// cv.put(this.COLUMN_FIGURES_DANCE_ID, 1);
		// db.insert(this.DB_TABLE_FIGURES, null, cv);

	}

}
