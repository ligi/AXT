package org.ligi.axt.test;

import android.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ligi.axt.listeners.RepeatedOnClicksListener;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import static android.view.View.OnClickListener;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class TheRepeatedOnClicksListener {

    @Mock
    private OnClickListener listener;

    @Mock
    private View view;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_not_fire_when_not_clicked() {
        new RepeatedOnClicksListener(1, listener);

        verify(listener, never()).onClick(any(View.class));
    }

    @Test
    public void should_not_fire_when_not_clicked_enough() {
        final RepeatedOnClicksListener tested = new RepeatedOnClicksListener(3, listener);

        repeatedViewClick(2, tested);

        verify(listener, never()).onClick(any(View.class));
    }

    @Test
    public void should_fire_when_clicked_once_for_two_clicks_at_setting_once() {
        final RepeatedOnClicksListener tested = new RepeatedOnClicksListener(1, listener);

        repeatedViewClick(2, tested);

        verify(listener).onClick(any(View.class));
    }

    @Test
    public void repeating_should_be_allowed_by_default() {
        final RepeatedOnClicksListener tested = new RepeatedOnClicksListener(1, listener);

        repeatedViewClick(4, tested);

        verify(listener, times(2)).onClick(any(View.class));
        assertThat(tested.getCallCount()).isEqualTo(2);
    }


    @Test
    public void should_not_repeat_if_forbidden() {
        final RepeatedOnClicksListener tested = new RepeatedOnClicksListener(1, listener);
        tested.doNotRepeatCalls();

        repeatedViewClick(4, tested);

        verify(listener, times(1)).onClick(any(View.class));
        assertThat(tested.getCallCount()).isEqualTo(1);
    }

    private void repeatedViewClick(int times, OnClickListener listener) {
        for (int i = 0; i < times; i++) {
            listener.onClick(view);
        }
    }
}
