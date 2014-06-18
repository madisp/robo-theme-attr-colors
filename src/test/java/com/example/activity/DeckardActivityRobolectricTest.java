package com.example.activity;

import android.app.Activity;
import android.content.res.TypedArray;
import android.util.TypedValue;
import com.example.R;
import com.example.robolectric.DeckardActivity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class DeckardActivityRobolectricTest {

    @Test
    public void testSomething() throws Exception {
        Activity activity = Robolectric.buildActivity(DeckardActivity.class).create().get();
        assertTrue(activity != null);
    }

    @Test
    public void testColorBg() throws Exception {
        Activity activity = Robolectric.buildActivity(DeckardActivity.class).create().get();
        activity.setTheme(R.style.MyBlackTheme);
        TypedArray arr = activity.obtainStyledAttributes(new int[] { android.R.attr.windowBackground });
        TypedValue value = new TypedValue();
        arr.getValue(0, value);
        arr.recycle();

        assertThat(value.type).isGreaterThanOrEqualTo(TypedValue.TYPE_FIRST_COLOR_INT)
            .isLessThanOrEqualTo(TypedValue.TYPE_LAST_COLOR_INT);
        assertThat(value.data).isEqualTo(activity.getResources().getColor(android.R.color.black));
    }
}
