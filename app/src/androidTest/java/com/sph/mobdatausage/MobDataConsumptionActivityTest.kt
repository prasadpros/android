package com.sph.mobdatausage

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.sph.mobdatausage.features.home.MobDataConsumptionActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class MobDataConsumptionActivityTest {

    @Rule
    @JvmField
    val activityTestRule: ActivityTestRule<MobDataConsumptionActivity> =
            ActivityTestRule(MobDataConsumptionActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.sph.mobdatausage", appContext.packageName)
    }

    @Test
    fun check1() {
        onView(withId(R.id.rvNetworkStatistics)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
    }
}


