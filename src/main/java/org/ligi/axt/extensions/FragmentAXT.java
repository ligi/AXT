package org.ligi.axt.extensions;

import android.app.Fragment;

public class FragmentAXT {

    private final Fragment mFragment;

    public FragmentAXT(Fragment fragment) {
        mFragment = fragment;
    }

    public <T> T findById(int id) {
        return (T) mFragment.getView().findViewById(id);
    }
}
