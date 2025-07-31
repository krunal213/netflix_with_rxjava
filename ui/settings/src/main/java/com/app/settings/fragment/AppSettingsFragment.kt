package com.app.settings.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.app.settings.R

class AppSettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_fragment_app_settings, rootKey)
    }
}