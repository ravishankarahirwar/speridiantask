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
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressBack;
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
public class UserTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * @since  17/05/2018
     * This test is user for adding a user in the data base
     * I am using NewTestUser as a firstname of user in this test and last name as a NewUserLastName
     */
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
        userAddFirstName.perform(replaceText("NewTestUser"), closeSoftKeyboard());

        ViewInteraction userAddLastName = onView(
                allOf(withId(R.id.user_lname),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                2),
                        isDisplayed()));
        userAddLastName.perform(replaceText("NewUserLastName"), closeSoftKeyboard());

        ViewInteraction addUserButton = onView(
                allOf(withId(android.R.id.button1), withText("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        addUserButton.perform(scrollTo(), click());
    }

    /**
     * @since 17/05/2018
     * This test is use for testing edit user functionality
     * Step 1 - Need to click on userliveview first then edit dialog will popup
     * Step 2 - Replacing first name by EmmaEdit and lastname by  WongEdit
     * then click on add/update button
     */
    @Test
    public void editUserTest() {
        ViewInteraction userList = onView(
                allOf(withId(R.id.user_list),
                        childAtPosition(
                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                0)));
        userList.perform(actionOnItemAtPosition(2, click()));

        /**
         * For first time you need to change in this place like
         * EmmaEdit will replace by Emma(The default value)
         */
        ViewInteraction userFirstName = onView(
                allOf(withId(R.id.user_fname), withText("EmmaEdit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        userFirstName.perform(click());

        /**
         * The value you need to change in place of Emma in my case I am replacing by EmmaEdit
         */
        ViewInteraction userFirstNameEdited = onView(
                allOf(withId(R.id.user_fname), withText("EmmaEdit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        userFirstNameEdited.perform(replaceText("EmmaEdit"));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.user_fname), withText("EmmaEdit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(closeSoftKeyboard());

        ViewInteraction userLastNameEdited = onView(
                allOf(withId(R.id.user_lname), withText("WongEdit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                2),
                        isDisplayed()));
        userLastNameEdited.perform(replaceText("WongEdit"));

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.user_lname), withText("WongEdit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText5.perform(closeSoftKeyboard());

        /**
         * Action to perform edit operation in data base
         */
        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton.perform(scrollTo(), click());

        pressBack();

        ViewInteraction textView = onView(
                allOf(withId(R.id.user_fname), withText("EmmaEdit"),
                        childAtPosition(
                                allOf(withId(R.id.item_layout),
                                        childAtPosition(
                                                withId(R.id.user_list),
                                                2)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("EmmaEdit")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.user_lname), withText("WongEdit"),
                        childAtPosition(
                                allOf(withId(R.id.item_layout),
                                        childAtPosition(
                                                withId(R.id.user_list),
                                                2)),
                                2),
                        isDisplayed()));
        textView2.check(matches(withText("WongEdit")));

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
