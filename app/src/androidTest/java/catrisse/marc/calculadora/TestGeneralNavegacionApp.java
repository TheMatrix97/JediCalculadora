package catrisse.marc.calculadora;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestGeneralNavegacionApp {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void testGeneralNavegacionApp() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonRegisterLog), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.editName),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.editName),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.editName),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("marc"), closeSoftKeyboard());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.editNom),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("ma"), closeSoftKeyboard());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.editSurname),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText5.perform(replaceText("catrisse"), closeSoftKeyboard());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.editPass),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText6.perform(replaceText("catrisse"), closeSoftKeyboard());

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.editPass), withText("catrisse"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText7.perform(pressImeActionButton());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.editConfirmPass),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText8.perform(replaceText("catrisse"), closeSoftKeyboard());

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.editConfirmPass), withText("catrisse"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText9.perform(pressImeActionButton());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonRegister), withText("Register"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout3),
                                        childAtPosition(
                                                withId(R.id.parentLayoutRegister),
                                                2)),
                                5),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.snackbar_action), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.Snackbar$SnackbarLayout")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.editTextUser),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText10.perform(click());

        ViewInteraction textInputEditText11 = onView(
                allOf(withId(R.id.editTextUser),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText11.perform(replaceText("marc"), closeSoftKeyboard());

        ViewInteraction textInputEditText12 = onView(
                allOf(withId(R.id.editTextPass),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText12.perform(replaceText("catrisse"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.buttonLogin), withText("Login"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout2),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar_drawer),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.drawer_nav_view),
                                        0)),
                        1),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.button2), withText("8"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linearLayout),
                                        2),
                                1),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.textView2),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(replaceText("8"), closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.button8), withText("*"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linearLayout),
                                        3),
                                3),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.textView2), withText("8"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView2.perform(replaceText("8*"));

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.textView2), withText("8*"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView3.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.button6), withText("5"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linearLayout),
                                        3),
                                1),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.textView2), withText("8*"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView4.perform(replaceText("8*5"));

        ViewInteraction appCompatTextView5 = onView(
                allOf(withId(R.id.textView2), withText("8*5"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView5.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.button15), withText("="),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linearLayout),
                                        5),
                                2),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar_drawer),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction navigationMenuItemView2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.drawer_nav_view),
                                        0)),
                        2),
                        isDisplayed()));
        navigationMenuItemView2.perform(click());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.game_start), withText("Start"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar_drawer),
                                        2),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.imageButton6),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linearLayout5),
                                        1),
                                1),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.imageButton7),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linearLayout5),
                                        1),
                                2),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.game_stop), withText("Reset"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar_drawer),
                                        2),
                                1),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar_drawer),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton5.perform(click());

        ViewInteraction navigationMenuItemView3 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.drawer_nav_view),
                                        0)),
                        3),
                        isDisplayed()));
        navigationMenuItemView3.perform(click());

        ViewInteraction appCompatImageButton6 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar_drawer),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton6.perform(click());

        ViewInteraction navigationMenuItemView4 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.drawer_nav_view),
                                        0)),
                        6),
                        isDisplayed()));
        navigationMenuItemView4.perform(click());

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
