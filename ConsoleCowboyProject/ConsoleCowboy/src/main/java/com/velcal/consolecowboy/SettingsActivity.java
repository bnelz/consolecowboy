package com.velcal.consolecowboy;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        PreferenceCategory header = new PreferenceCategory(this);
        header.setTitle(R.string.pref_header);
        addPreferencesFromResource(R.xml.pref_general);
    }

}
