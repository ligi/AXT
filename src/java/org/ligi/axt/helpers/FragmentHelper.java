package org.ligi.axt.helpers;

import android.app.Fragment;

public class FragmentHelper {

    private final Fragment mFragment;

    public FragmentHelper(Fragment fragment) {
        mFragment=fragment;
    }

    public <T> T findById(int id) {
        return (T) mFragment.getView().findViewById(id);
    }
}
