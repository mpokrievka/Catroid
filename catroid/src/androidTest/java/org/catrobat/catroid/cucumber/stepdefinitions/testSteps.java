/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2018 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catrobat.catroid.cucumber.stepdefinitions;

import android.app.Activity;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.R;
import org.catrobat.catroid.content.BroadcastScript;
import org.catrobat.catroid.content.Project;
import org.catrobat.catroid.content.Script;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.StartScript;
import org.catrobat.catroid.content.bricks.BroadcastBrick;
import org.catrobat.catroid.content.bricks.ChangeSizeByNBrick;
import org.catrobat.catroid.content.bricks.ChangeVariableBrick;
import org.catrobat.catroid.content.bricks.CloneBrick;
import org.catrobat.catroid.content.bricks.SetVariableBrick;
import org.catrobat.catroid.formulaeditor.Formula;
import org.catrobat.catroid.formulaeditor.FormulaElement;
import org.catrobat.catroid.formulaeditor.SensorHandler;
import org.catrobat.catroid.formulaeditor.Sensors;
import org.catrobat.catroid.formulaeditor.UserVariable;
import org.catrobat.catroid.formulaeditor.datacontainer.DataContainer;
import org.catrobat.catroid.stage.StageActivity;
import org.catrobat.catroid.ui.SpriteActivity;
import org.catrobat.catroid.uiespresso.content.brick.utils.BrickTestUtils;
import org.catrobat.catroid.uiespresso.util.rules.BaseActivityInstrumentationRule;
import org.junit.Rule;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;
import static org.catrobat.catroid.uiespresso.formulaeditor.utils.FormulaEditorDataListWrapper.onDataList;
import static org.catrobat.catroid.uiespresso.formulaeditor.utils.FormulaEditorWrapper.onFormulaEditor;

public class testSteps {

    final String varName = "variable";

    /*@Rule
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
    }*/
/*
    @Rule
    public BaseActivityInstrumentationRule<StageActivity> baseActivityTestRule = new
            BaseActivityInstrumentationRule<>(StageActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        createProject();
    } */
/*
    @Given("^I have an activity$")
    public void I_have_an_activity() throws InterruptedException {
        // TimeUnit.SECONDS.sleep(10);
        assertNotNull(activity);
    }
/*
    @When("^I create an variable$")
    public void I_create_a_variable() {
        onDataList()
                .performAdd(varName);

        /*onView(withId(R.id.button_add))
                .perform(click());
        onView(withId(R.id.input_edit_text))
                .perform(typeText(varName), closeSoftKeyboard());

        onView(withId(R.id.global))
                        .check(matches(isChecked()));

        onView(withId(R.id.make_list))
                        .check(matches(isNotChecked()));

        onView(withId(android.R.id.button1))
                .perform(click());

       // Sensors inclinationSensor = Sensors.getSensorByValue("X_INCLINATION");
      //  Object tt = SensorHandler.getSensorValue(inclinationSensor);
       /* pressBack();
        onView(withId(R.id.button_play))
                .perform(click());
    } */
/*
    @Then("^I should see that variable in the variable list$")
    public void I_should_see_that_variable_in_the_variable_list() {

        onDataList().onVariableAtPosition(0)
                .checkHasName(varName);
    }*/


}
