package com.example.espresso.activity;

import android.app.Activity;
import android.content.res.TypedArray;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.TypedValue;
import com.example.R;
import com.example.robolectric.DeckardActivity;
import static org.fest.assertions.api.Assertions.assertThat;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;

@LargeTest
public class DeckardEspressoTest extends ActivityInstrumentationTestCase2<DeckardActivity> {

    @SuppressWarnings("deprecation")
    public DeckardEspressoTest() {
        // This constructor was deprecated - but we want to support lower API levels.
        super("com.example.activity", DeckardActivity.class);
    }
    @Override
    public void setUp() throws Exception {
        super.setUp();
        // Espresso will not launch our activity for us, we must launch it via getActivity().
        getActivity();
    }

    public void testCheckText() {
        onView(withId(R.id.text))
            .check(matches(withText("Hello Espresso!")));
    }

    public void testColorBg() throws Exception {
        Activity activity = getActivity();
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
