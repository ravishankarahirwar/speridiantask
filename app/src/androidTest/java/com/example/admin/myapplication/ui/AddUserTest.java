package com.example.admin.myapplication.ui;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.admin.myapplication.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddUserTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addUserTest() {
        ViewInteraction actionMenuAdd = onView(
                allOf(withId(R.id.action_add_user), withContentDescription("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuAdd.perform(click());

        ViewInteraction userFirstName = onView(
                allOf(withId(R.id.user_fname),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        userFirstName.perform(click());

        ViewInteraction userAddFirstName = onView(
                allOf(withId(R.id.user_fname),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        userAddFirstName.perform(replaceText("NewTe"), closeSoftKeyboard());

        ViewInteraction userAddLastName = onView(
                allOf(withId(R.id.user_lname),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                2),
                        isDisplayed()));
        userAddLastName.perform(replaceText("mylastname"), closeSoftKeyboard());

        ViewInteraction addUserButton = onView(
                allOf(withId(android.R.id.button1), withText("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        addUserButton.perform(scrollTo(), click());
    }

    @Test
    public void editUserTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.user_list),
                        childAtPosition(
                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(2, click()));

//        ViewInteraction appCompatTextView = onView(
//                allOf(withId(R.id.user_id), isDisplayed()));
//        appCompatTextView.perform(replaceText("3"), closeSoftKeyboard());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.user_fname), withText("EmmaEdit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.user_fname), withText("EmmaEdit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("EmmaEdit"));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.user_fname), withText("EmmaEdit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.user_lname), withText("WongEdit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("WongEdit"));

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.user_lname), withText("WongEdit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText5.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton.perform(scrollTo(), click());

//        pressBack();

//        ViewInteraction textView = onView(
//                allOf(withId(R.id.user_fname), withText("EmmaEdit"),
//                        childAtPosition(
//                                allOf(withId(R.id.item_layout),
//                                        childAtPosition(
//                                                withId(R.id.user_list),
//                                                2)),
//                                1),
//                        isDisplayed()));
//        textView.check(matches(withText("EmmaEdit")));
//
//        ViewInteraction textView2 = onView(
//                allOf(withId(R.id.user_lname), withText("WongEdit"),
//                        childAtPosition(
//                                allOf(withId(R.id.item_layout),
//                                        childAtPosition(
//                                                withId(R.id.user_list),
//                                                2)),
//                                2),
//                        isDisplayed()));
//        textView2.check(matches(withText("WongEdit")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
