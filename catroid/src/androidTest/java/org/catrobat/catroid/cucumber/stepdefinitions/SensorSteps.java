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

import android.support.test.InstrumentationRegistry;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.content.Project;
import org.catrobat.catroid.content.Script;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.StartScript;
import org.catrobat.catroid.content.bricks.SetVariableBrick;
import org.catrobat.catroid.formulaeditor.Formula;
import org.catrobat.catroid.formulaeditor.FormulaElement;
import org.catrobat.catroid.formulaeditor.UserVariable;
import org.catrobat.catroid.formulaeditor.datacontainer.DataContainer;
import org.catrobat.catroid.stage.StageActivity;
import org.catrobat.catroid.uiespresso.util.rules.BaseActivityInstrumentationRule;
import org.junit.Rule;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static junit.framework.TestCase.assertTrue;

public class SensorSteps {

    private static Socket s;
    private static BufferedReader input;
    private static DataOutputStream output;
    private static String armIp = "169.254.172.210";
 //   private static String armIp = "10.0.2.2";
    private static int port = 12000;
    private UserVariable xInclinationVar;
    private UserVariable yInclinationVar;
    private String xInclinationVarName = "xInclinationVar";
    private String yInclinationVarName = "yInclinationVar";

    @Rule
    public BaseActivityInstrumentationRule<StageActivity> baseActivityTestRule = new
            BaseActivityInstrumentationRule<>(StageActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        createProject();
    }

    @Given("^I have an activity with variables holding the values of inclination sensors$")
    public void I_have_an_activity() throws InterruptedException {
        baseActivityTestRule.launchActivity(null);
}

    @When("^I change the inclination of the phone$")
    public void I_create_a_variable() throws IOException, InterruptedException {
       /* if (!initRobotArm()) {
            fail();
        }
        if(!sendRobotArmCommand(90, "tilt")) {
            fail();
        }
        if(!sendRobotArmCommand(-90, "tilt")) {
            fail();
        }
        if(!sendRobotArmCommand(-45, "rotate_base")) {
            fail();
        }
        if(!sendRobotArmCommand(45, "rotate_base")) {
            fail();
        }
        if(!sendRobotArmCommand(-45, "rotate_head")) {
            fail();
        }
        if(!sendRobotArmCommand(45, "rotate_head")) {
            fail();
        }*/
    }

    @Then("^the test variable value should change accordingly$")
    public void the_test_variable_value_should_change () throws InterruptedException {
       TimeUnit.SECONDS.sleep(1);
       double value = (double) xInclinationVar.getValue();
       assertTrue(value < -30 && value > -90);
    }

    public boolean initRobotArm () throws IOException, InterruptedException {
        if(!connectionSetup()) {
            return false;
        }
        output.write("init".getBytes());

        String reply = "";
        int dataBuffer;

        while ((dataBuffer = s.getInputStream().read()) != -1) {
            reply = reply + (char) dataBuffer;
        }

        if (reply.equals("error")) {
            return false;
        }
        TimeUnit.SECONDS.sleep(5);

        closeConnection();
        return true;
    }

    public boolean sendRobotArmCommand (int param, String cmd) throws IOException, InterruptedException {
        if(!connectionSetup()) {
            return false;
        }
        String command = cmd + " " + param;
        output.write(command.getBytes());

        String reply = "";
        int dataBuffer;

        while ((dataBuffer = s.getInputStream().read()) != -1) {
            reply = reply + (char) dataBuffer;
        }

        if (reply.equals("error")) {
            return false;
        }
        TimeUnit.SECONDS.sleep(5);

        closeConnection();
        return true;
    }

    public static boolean connectionSetup() throws IOException {
        s = new Socket(armIp, port);
        if(s.isConnected()) {
            output = new DataOutputStream(s.getOutputStream());
            input = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
            return true;
        }
        else {
            System.out.println("Failed");
            return false;
        }
    }

    public static void closeConnection() {
        try {
            output.close();
            input.close();
            s.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createProject() {
        Project project = new Project(InstrumentationRegistry.getTargetContext(), "BroadcastForClonesRegressionTest");
        ProjectManager.getInstance().setProject(project);
        DataContainer dataContainer = project.getDefaultScene().getDataContainer();
        xInclinationVar = new UserVariable(xInclinationVarName);
        yInclinationVar = new UserVariable(yInclinationVarName);
        dataContainer.addUserVariable(xInclinationVar);
        dataContainer.addUserVariable(yInclinationVar);

        Sprite sprite = new Sprite("testSprite");

        Script startScript = new StartScript();
        Formula variableFormulaX = new Formula(new FormulaElement(FormulaElement.ElementType.SENSOR, "X_INCLINATION", null));
        Formula variableFormulaY = new Formula(new FormulaElement(FormulaElement.ElementType.SENSOR, "Y_INCLINATION", null));
        startScript.addBrick(new SetVariableBrick(variableFormulaX, xInclinationVar));
        startScript.addBrick(new SetVariableBrick(variableFormulaY, yInclinationVar));
        sprite.addScript(startScript);

        ProjectManager.getInstance().getCurrentlyEditedScene().addSprite(sprite);
        ProjectManager.getInstance().setCurrentSprite(sprite);
    }
}
