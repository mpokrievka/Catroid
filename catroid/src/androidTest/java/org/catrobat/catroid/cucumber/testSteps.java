package org.catrobat.catroid.cucumber;

import android.app.Activity;

import org.catrobat.catroid.R;
import org.catrobat.catroid.content.Script;
import org.catrobat.catroid.content.bricks.ChangeSizeByNBrick;
import org.catrobat.catroid.ui.SpriteActivity;
import org.catrobat.catroid.uiespresso.content.brick.utils.BrickTestUtils;
import org.catrobat.catroid.uiespresso.util.rules.BaseActivityInstrumentationRule;
import org.junit.Rule;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;
import static org.catrobat.catroid.uiespresso.formulaeditor.utils.FormulaEditorDataListWrapper.onDataList;
import static org.catrobat.catroid.uiespresso.formulaeditor.utils.FormulaEditorWrapper.onFormulaEditor;

public class testSteps {

    final String varName = "variable";

    @Rule
    public BaseActivityInstrumentationRule<SpriteActivity> baseActivityTestRule = new
            BaseActivityInstrumentationRule<>(SpriteActivity.class, SpriteActivity.EXTRA_FRAGMENT_POSITION,
            SpriteActivity.FRAGMENT_SCRIPTS);

    private Activity activity;

    @Before
    public void setup() {

        Script script = BrickTestUtils.createProjectAndGetStartScript("FormulaEditorAddVariableTest");
        script.addBrick(new ChangeSizeByNBrick(0));

        baseActivityTestRule.launchActivity();

        onView(withId(R.id.brick_change_size_by_edit_text))
                .perform(click());
        onFormulaEditor()
                .performOpenDataFragment();
        activity = baseActivityTestRule.getActivity();
    }

    @Given("^I have an activity$")
    public void I_have_an_activity() throws InterruptedException {
        // TimeUnit.SECONDS.sleep(10);
        assertNotNull(activity);
    }

    @When("^I create an variable$")
    public void I_create_a_variable() {

        onView(withId(R.id.button_add))
                .perform(click());
        onView(withId(R.id.input_edit_text))
                .perform(typeText(varName), closeSoftKeyboard());

        onView(withId(R.id.global))
                        .check(matches(isChecked()));

        onView(withId(R.id.make_list))
                        .check(matches(isNotChecked()));

        onView(withId(android.R.id.button1))
                .perform(click());
    }

    @Then("^I should see that variable in the variable list$")
    public void I_should_see_that_variable_in_the_variable_list() {

        onDataList().onVariableAtPosition(0)
                .checkHasName(varName);
    }
}
