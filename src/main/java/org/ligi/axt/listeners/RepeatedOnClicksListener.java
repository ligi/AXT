package org.ligi.axt.listeners;

import android.view.View;

public class RepeatedOnClicksListener implements View.OnClickListener {

    private final View.OnClickListener listener;
    private final int configuredClicksBeforeFiring;
    private int actClicksBeforeFiring;
    private boolean repeatsAreAllowed = true;
    private int callCount;

    public RepeatedOnClicksListener(int clickCountBeforeFire, View.OnClickListener listener) {
        this.listener = listener;
        this.configuredClicksBeforeFiring = clickCountBeforeFire;
        this.actClicksBeforeFiring = configuredClicksBeforeFiring;
        callCount = 0;
    }

    @Override
    public void onClick(View view) {
        if (callCount == 0 || repeatsAreAllowed) {
            if (actClicksBeforeFiring-- <= 0) {
                listener.onClick(view);
                actClicksBeforeFiring = configuredClicksBeforeFiring;
                callCount++;
            }
        }
    }

    public RepeatedOnClicksListener doNotRepeatCalls() {
        repeatsAreAllowed = false;
        return this;
    }

    public int getCallCount() {
        return callCount;
    }

}
