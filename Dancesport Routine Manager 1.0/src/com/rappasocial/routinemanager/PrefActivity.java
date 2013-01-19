package com.rappasocial.routinemanager;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PrefActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceManager().setSharedPreferencesName(
                SharedPrefs.PREFS_NAME);
        addPreferencesFromResource(R.xml.prefs);
    }
}
