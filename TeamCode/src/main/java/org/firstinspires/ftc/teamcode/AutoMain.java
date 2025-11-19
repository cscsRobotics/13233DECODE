package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


// Telemetry - print on driver hub
//


// Robot Hardware Classes
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.VoltageSensor;


// import for IMU (gyroscope)
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.Utils_13233.AutoConstants;
import org.firstinspires.ftc.teamcode.Utils_13233.AutoDrive;
import org.firstinspires.ftc.teamcode.Utils_13233.AutoTurn;
import org.firstinspires.ftc.teamcode.Utils_13233.CommonAutoMethods;
import org.firstinspires.ftc.teamcode.Utils_13233.MotorConstructor;


@Autonomous(name = "AutoMain", group = "Auto")

public class AutoMain extends LinearOpMode{
    private AutoDrive drive;
    private AutoTurn turn;
    private CommonAutoMethods autoMethods;



    // Possible Autonomous Modes
    public enum AutoMode {
        AUTO_MODE_NOT_SELECTED,
        AUTO_MODE_DEFAULT,
    }


    // Global Variables to store Game Specific Information
    AutoMode autoMode = AutoMode.AUTO_MODE_NOT_SELECTED; // store autonomous mode selected


    /**
     * SelectAutoMode
     * This function is use to select the autonomous code to be executed for this match
     * Game pad 1 is used and the following buttons are used for selection:
     * a - Red alliance Sample
     * b - Blue alliance Sample
     * x - Red alliance Specimen
     * y - Blue alliance Specimen
     *
     * @return Selected mode
     */
    private AutoMode SelectAutoMode() {
        // Local variable to store selected autonomous mode

        AutoMode autoMode = AutoMode.AUTO_MODE_NOT_SELECTED;
        // Display autonomous mode not selected yet
        telemetry.addData("AutoMode", "Not Selected");
        telemetry.update();


        // Loop until autonomous mode is selected
        while (!isStopRequested() && autoMode == AutoMode.AUTO_MODE_NOT_SELECTED) {
            autoMode = AutoMode.AUTO_MODE_DEFAULT;
            idle();
        }

        // Display selected autonomous mode
        telemetry.addData("Autonomous Mode", autoMode.toString());
        telemetry.update();

        // Wait for the user to release the button
        while (!isStopRequested() && (gamepad1.a || gamepad1.b || gamepad1.x || gamepad1.y)) {
            idle();
        }

        return autoMode;

    } // end SelectAutoMode

    /**
     * Function: SelectDelayTime
     * <p>
     * This function is use to select how long to delay the start of the autonomous code.
     * Game pad 1 is used and the following controls are used for selection:
     * left bumper - decrease delay time by 1000 milliseconds (1 second)
     * right bumper - increase delay time by 1000 milliseconds (1 second)
     * a button -	set selected time
     * note: if no delay time is needed, just select the a button. The default for the delay time
     * is 0.
     *
     * @return Delay Time in milliseconds
     */
    private Integer SelectDelayTime() {
        int delayTimeMilliseconds = 0;    // Initialize delay to be 0 seconds

        // display delay time not set
        telemetry.addData("Delay", "%d (Not Set)", delayTimeMilliseconds);
        telemetry.addData("Left Bumper -", " decrease delay time by (1 second)");
        telemetry.addData("Right bumper -", " increase delay time by (1 second)");
        telemetry.addData("A", "No delay time is needed");
        telemetry.update();


        /* Select Delay time.
           - Select 'a' button without hitting bumpers if no delay needed
           - Use Left Bumper to decrease delay time
           - Use Right bumper to increase delay time

           Note: After entering delay time, use "a" button to set selected time
        */

        while (!isStopRequested() && !gamepad1.a) {
            if (gamepad1.left_bumper) {
                delayTimeMilliseconds -= 1000;

                // ensure delay time does not go negative
                if (delayTimeMilliseconds < 0) {
                    delayTimeMilliseconds = 0;
                }

                // Wait for the bumper to be released
                while (gamepad1.left_bumper) {
                    idle();
                }

                telemetry.addData("Delay", "%d (decrease)", delayTimeMilliseconds);
                telemetry.update();
            }

            if (gamepad1.right_bumper) {
                delayTimeMilliseconds += 1000;

                // ensure delay time is not greater than 10 seconds
                if (delayTimeMilliseconds > 10000) {
                    delayTimeMilliseconds = 10000;
                }

                while (gamepad1.right_bumper) {
                    idle();
                }
                telemetry.addData("Delay", "%d (increase)", delayTimeMilliseconds);
                telemetry.update();
            }
        }

        // Wait for user to release the a button
        while (!isStopRequested() && gamepad1.a) {
            idle();
        }


        // Display selected delay time
        telemetry.addData("Delay Time SET", delayTimeMilliseconds);
        telemetry.update();
        return delayTimeMilliseconds;       // returns selected delay time

    } // end SelectDelayTime


    // OpMode for autonomous code
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new AutoDrive(this, hardwareMap);
        turn  = new AutoTurn(this, hardwareMap);
        autoMethods = new CommonAutoMethods(this, hardwareMap);

        double tgtPower = 0;


        telemetry.addData("Mode", "calibrating imu....");
        telemetry.update();
        autoMethods.setUpIMU(RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
            RevHubOrientationOnRobot.UsbFacingDirection.DOWN);

        // Create local variable to store selected autonomous mode
        // and amount of delay time
        int delayTimeMilliseconds = 0;

        telemetry.setAutoClear(false);
        telemetry.addData("Status", "Initializing");
        telemetry.update();

        /* Select Autonomous Mode, Parking Location and Delay time	  */

        autoMode = SelectAutoMode();
        // parkLocation = SelectParkLoc();
        delayTimeMilliseconds = SelectDelayTime();


        /* All required data entered.  Autonomous is initialized.	 */

        telemetry.addData("Status", "Initialized");
        telemetry.addData("mode", "waiting for start");
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();

        /* Wait for start of the match	 */
        waitForStart();

        telemetry.clearAll();               // clear display messages

        telemetry.addData("Mode", "running");
        telemetry.update();

        autoMethods.resetEncoders();

        // Delay start if needed
        if (delayTimeMilliseconds > 0) {
            // display delay status
            telemetry.addData("Status", "Delaying");
            telemetry.update();

            // wait selected amount of time
            sleep(delayTimeMilliseconds);

            // display delay over and autonomous code is running
            telemetry.addData("Status", "Running");
            telemetry.update();

        }

        telemetry.clearAll();               // clear display messages

        // Determine which autonomous code to run
        switch (autoMode) {
            case AUTO_MODE_DEFAULT:
                defaultAuto();
                break;
            case AUTO_MODE_NOT_SELECTED:
                // This one should not happen if it does do nothing
                break;
        }
    }

    private void defaultAuto() {
        drive.driveForward(10.0, AutoConstants.quarterPower);
    }
}
