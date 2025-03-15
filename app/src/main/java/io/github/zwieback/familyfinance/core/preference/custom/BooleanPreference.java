package io.github.zwieback.familyfinance.core.preference.custom;

import android.content.Context;
import androidx.annotation.CallSuper;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;

import android.util.AttributeSet;

public abstract class BooleanPreference
        extends CheckBoxPreference
        implements Preference.OnPreferenceChangeListener {

    @SuppressWarnings("unused")
    public BooleanPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @SuppressWarnings("unused")
    public BooleanPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @SuppressWarnings("unused")
    public BooleanPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @SuppressWarnings("unused")
    public BooleanPreference(Context context) {
        super(context);
        init(context);
    }

    @CallSuper
    protected void init(Context context) {
        setOnPreferenceChangeListener(this);
    }
}
