package io.github.zwieback.familyfinance.core.preference.custom;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;

import android.util.AttributeSet;


import com.takisoft.preferencex.PreferenceActivityResultListener;
import com.takisoft.preferencex.PreferenceFragmentCompat;

import io.github.zwieback.familyfinance.business.preference.activity.SettingsActivity;
import io.github.zwieback.familyfinance.core.preference.config.BackupPrefs;
import io.github.zwieback.familyfinance.core.preference.config.DatabasePrefs;

public abstract class ActivityResultPreference extends Preference
        implements PreferenceActivityResultListener, OnSuccessPreferenceActivityResultListener,
        Preference.OnPreferenceChangeListener {

    protected SettingsActivity activity;
    protected DatabasePrefs databasePrefs;
    protected BackupPrefs backupPrefs;

    public ActivityResultPreference(Context context, AttributeSet attrs, int defStyleAttr,
                                    int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public ActivityResultPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ActivityResultPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ActivityResultPreference(Context context) {
        super(context);
        init(context);
    }

    protected void init(Context context) {
        activity = determineActivity(context);
        databasePrefs = DatabasePrefs.with(activity);
        backupPrefs = BackupPrefs.with(activity);
        setOnPreferenceChangeListener(this);
    }

    private SettingsActivity determineActivity(Context context) {
        if (context instanceof SettingsActivity) {
            return (SettingsActivity) context;
        }
        if (context instanceof ContextWrapper) {
            if (((ContextWrapper) context).getBaseContext() instanceof SettingsActivity) {
                return (SettingsActivity) ((ContextWrapper) context).getBaseContext();
            }
        }
        throw new ClassCastException(context + " must implement SettingsActivity");
    }

    @Override
    public void onPreferenceClick(@NonNull PreferenceFragmentCompat fragment,
                                  @NonNull Preference preference) {
        fragment.startActivityForResult(getRequestIntent(), getRequestCode());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent resultIntent) {
        if (requestCode == getRequestCode()
                && resultCode == Activity.RESULT_OK
                && resultIntent != null) {
            onSuccessResult(resultIntent);
        }
    }

    protected abstract int getRequestCode();

    protected abstract Intent getRequestIntent();
}
