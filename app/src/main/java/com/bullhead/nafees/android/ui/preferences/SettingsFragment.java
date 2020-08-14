package com.bullhead.nafees.android.ui.preferences;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.bullhead.nafees.android.R;
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;

public class SettingsFragment extends PreferenceFragmentCompat {
    @NonNull
    public static SettingsFragment newInstance() {
        Bundle           args     = new Bundle();
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        Preference preference = findPreference("licences");
        if (preference != null) {
            preference.setOnPreferenceClickListener(p -> {
                if (getContext() != null) {
                    startActivity(new Intent(getContext(), OssLicensesMenuActivity.class));
                }
                return true;
            });
        }
        Preference code = findPreference("code");
        if (code != null) {
            code.setOnPreferenceClickListener(p -> {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://gitlab.com/nafees-app/nafees-android"));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    //ignore
                }
                return true;
            });
        }
    }
}