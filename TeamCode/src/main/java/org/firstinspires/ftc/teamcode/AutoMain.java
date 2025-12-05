package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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

@Disabled
@Autonomous(name = "AutoMain", group = "Auto")
public class AutoMain extends LinearOpMode {
    // Global Variables to store Game Specific Information
    AutoMode autoMode = AutoMode.AUTO_MODE_NOT_SELECTED; // store autonomous mode selected
    private AutoDrive drive;
    private AutoTurn turn;
    private CommonAutoMethods autoMethods;

    // OpMode for autonomous code
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new AutoDrive(this, hardwareMap);
        turn = new AutoTurn(this, hardwareMap);
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

        autoMode = autoMethods.SelectAutoMode();
        // parkLocation = SelectParkLoc();
        delayTimeMilliseconds = autoMethods.SelectDelayTime();


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

    // Possible Autonomous Modes
    public enum AutoMode {
        AUTO_MODE_NOT_SELECTED,
        AUTO_MODE_DEFAULT,
    }
}
