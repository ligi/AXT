/*
 * with permission taken from here: http://www.ebessette.com/d/TimePickerPreference
 */
package org.ligi.axt.preferences;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TimePicker;

/**
 * A preference type that allows a user to choose a time
 */
public class TimePreference extends DialogPreference implements TimePicker.OnTimeChangedListener {

    /**
     * The validation expression for this preference
     */
    private static final String VALIDATION_EXPRESSION = "[0-2]*[0-9]:[0-5]*[0-9]";

    /**
     * The default value for this preference
     */
    private String defaultValue;

    /**
     * @param context
     * @param attrs
     */
    public TimePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public TimePreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    /**
     * Initialize this preference
     */
    private void initialize() {
        setPersistent(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.preference.DialogPreference#onCreateDialogView()
     */
    @Override
    protected View onCreateDialogView() {

        TimePicker tp = new TimePicker(getContext());
        tp.setIs24HourView(true);
        tp.setOnTimeChangedListener(this);

        int h = getHour();
        int m = getMinute();
        if (h >= 0 && m >= 0) {
            tp.setCurrentHour(h);
            tp.setCurrentMinute(m);
        }

        return tp;
    }

    private int mHour = 0, mMinute = 0;

    /*
     * (non-Javadoc)
     *
     * @see
     * android.widget.TimePicker.OnTimeChangedListener#onTimeChanged(android
     * .widget.TimePicker, int, int)
     */
    public void onTimeChanged(TimePicker view, int hour, int minute) {
        mHour = hour;
        mMinute = minute;
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            if (isPersistent()) {
                String result = mHour + ":" + mMinute;
                persistString(result);
                callChangeListener(result);
            }

        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.preference.Preference#setDefaultValue(java.lang.Object)
     */
    @Override
    public void setDefaultValue(Object defaultValue) {
        // BUG this method is never called if you use the 'android:defaultValue' attribute in your XML preference file, not sure why it isn't

        super.setDefaultValue(defaultValue);

        if (!(defaultValue instanceof String)) {
            return;
        }

        if (!((String) defaultValue).matches(VALIDATION_EXPRESSION)) {
            return;
        }

        this.defaultValue = (String) defaultValue;
    }

    /**
     * Get the hour value (in 24 hour time)
     *
     * @return The hour value, will be 0 to 23 (inclusive)
     */
    private int getHour() {
        String time = getPersistedString(this.defaultValue);
        if (time == null || !time.matches(VALIDATION_EXPRESSION)) {
            return -1;
        }

        return Integer.valueOf(time.split(":")[0]);
    }

    /**
     * Get the minute value
     *
     * @return the minute value, will be 0 to 59 (inclusive)
     */
    private int getMinute() {
        String time = getPersistedString(this.defaultValue);
        if (time == null || !time.matches(VALIDATION_EXPRESSION)) {
            return -1;
        }

        return Integer.valueOf(time.split(":")[1]);
    }
}
