package com.judacribz.amazonbooks;

import android.content.ComponentName;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.judacribz.amazonbooks.view.activities.DetailActivity;
import com.judacribz.amazonbooks.view.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule =
            new ActivityTestRule<>(MainActivity.class);

//    @Rule
//    public IntentsTestRule<DetailActivity> detailActivityRule =
//            new IntentsTestRule<>(DetailActivity.class);

    @Test
    public void scrollBookListAndClick() {
        RecyclerView recycler = mainActivityRule.getActivity().findViewById(R.id.rvBookList);
        while (recycler.hasPendingAdapterUpdates()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Intents.init();
        onView(withId(R.id.rvBookList))
                .perform(RecyclerViewActions.actionOnItemAtPosition(20, click()));
        intended(hasComponent(new ComponentName(getApplicationContext(), DetailActivity.class)));
        Intents.release();
    }
}
