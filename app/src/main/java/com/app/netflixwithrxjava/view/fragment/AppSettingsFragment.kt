package com.app.netflixwithrxjava.view.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.app.netflixwithrxjava.R

class AppSettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_fragment_app_settings, rootKey)

    }
}