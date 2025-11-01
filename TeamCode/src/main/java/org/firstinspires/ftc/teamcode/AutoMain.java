package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


// Telemetry - print on driver hub
//


// Robot Hardware Classes
//
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.VoltageSensor;


// import for IMU (gyroscope)
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

import kotlin.contracts.Returns;


@Autonomous(name = "AutoMain", group = "Auto")

public class AutoMain extends LinearOpMode {
    CommonControls CommonControls = new CommonControls(hardwareMap);
    private IMU imu;

    private VoltageSensor VoltSens;


    // Variables for imu
    double globalAngle, correction;
    YawPitchRollAngles lastAngles;

    double voltage = 0;
    double batteryConst = 13.5;


    // Possible Autonomous Modes
    public enum AutoMode {
        AUTO_MODE_NOT_SELECTED,
        AUTO_MODE_DEFAULT,
    }


    // Variables for driving with Encoders
    private static final double wheelCircumference = 4.0 * Math.PI;
    private static final double gearRatio = 18.9;                  // Rev HD Hex 20:1
    private static final double countsPerRotation = 560.0;         // Rev HD Hex 20:1
    private static final double scaleFactor = 9.0;                 // need to find scale factor!!!
    private static final double countsPerInch = countsPerRotation / wheelCircumference
        / gearRatio * scaleFactor;


    // Global Variables
    private final static double noPower = 0.0;
    private final static double quarterPower = 0.25;
    private final static double oneThirdPower = 0.34;
    private final static double halfPower = 0.5;
    private final static double threeQuartPower = 0.75;
    private final static double fullPower = 1.0;


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

        double tgtPower = 0;

        VoltSens = hardwareMap.voltageSensor.get("Control Hub");



        /* Setup IMU parameters */


        // Retrieve and initialize the IMU. The IMU should be attached to
        // IC2 port 0 on a Core Device Interface Module
        imu = hardwareMap.get(IMU.class, "imu");

        /* The next two lines define Hub orientation.
         * The Default Orientation (shown) is when a hub is mounted horizontally with the printed
         *  logo pointing UP and the USB port pointing FORWARD.
         *
         * To Do:  EDIT these two lines to match YOUR mounting configuration.
         */
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection
            = RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection
            = RevHubOrientationOnRobot.UsbFacingDirection.DOWN;

        telemetry.addData("Mode", "calibrating imu....");
        telemetry.update();

        try {
            RevHubOrientationOnRobot orientationOnRobot =
                new RevHubOrientationOnRobot(logoDirection, usbDirection);
            imu.initialize(new IMU.Parameters(orientationOnRobot));

            telemetry.addData("imu calib status", "calibrated");
            telemetry.update();

            // initialize imu global variables after calibrating imu
            resetAngle();

        } catch (IllegalArgumentException e) {
            telemetry.addData("imu calib status", "failed - try again");
            telemetry.update();
        }


        // set direction of motors
        CommonControls.leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        CommonControls.rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        CommonControls.leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        CommonControls.rightBack.setDirection(DcMotorSimple.Direction.FORWARD);


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

        resetEncoders();

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
        driveForward(10.0, quarterPower);
    }


    // Functions needed for driving auto
    private void resetEncoders() {
        CommonControls.rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        CommonControls.rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        CommonControls.leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        CommonControls.leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void runUsingEncoders() {
        CommonControls.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        CommonControls.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        CommonControls.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        CommonControls.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void setDrivePower(double leftFrontPower, double rightFrontPower,
                               double leftBackPower, double rightBackPower) {
        CommonControls.rightFront.setPower(rightFrontPower);
        CommonControls.rightBack.setPower(rightBackPower);
        CommonControls.leftFront.setPower(leftFrontPower);
        CommonControls.leftBack.setPower(leftBackPower);
    }

    /**
     * Function: strafeRight
     * <p>
     * This function is called to have the robot move sideways
     * in a right direction
     */
    private void strafeRight(double inches, double power) {
        driveInches(inches, power, -power, -power, power);
    }

    /**
     * Function: strafeLeft
     * <p>
     * This function is called to have the robot move sideways
     * in a left  direction
     */
    private void strafeLeft(double inches, double power) {
        driveInches(inches, -power, power, power, -power);
    }


    /**
     * Function: driveForward
     * <p>
     * This function is called to have the robot move forward.
     * The robot speed is passed in and that value is used for
     * all wheels.
     */
    private void driveForward(double inches, double power) {
        driveInches(inches, power, power, power, power);
    }


    /**
     * Function: driveBackward
     * <p>
     * This function is called to have the robot move in reverse.
     * The robot speed is passed in and that value is used for
     * all wheels.
     */
    private void driveBackward(double inches, double power) {
        driveInches(inches, -power, -power, -power, -power);
    }


    /**
     * Function: driveInches
     * This function is called to have the robot move straight
     * in a forward or reverse direction.
     * <p>
     * Strafe Forward = negative front wheels, positive back
     * wheels
     */
    private void driveInches(double inches, double leftFrontPower, double rightFrontPower, double leftBackPower, double rightBackPower) {
        double counts = inches * countsPerInch;

        resetEncoders();
        runUsingEncoders();

        voltage = VoltSens.getVoltage();  // read current battery voltage

        double leftFrontPowerCont = ((batteryConst * leftFrontPower) / voltage);
        double rightFrontPowerCont = ((batteryConst * rightFrontPower) / voltage);
        double leftBackPowerCont = ((batteryConst * leftBackPower) / voltage);
        double rightBackPowerCont = ((batteryConst * rightBackPower) / voltage);

        setDrivePower(leftFrontPowerCont, rightFrontPowerCont, leftBackPowerCont, rightBackPowerCont);

        while (opModeIsActive() &&
            (Math.abs(CommonControls.leftFront.getCurrentPosition()) +
                Math.abs(CommonControls.rightFront.getCurrentPosition()) / 2.0) < Math.abs(counts)) {
            // Use gyro to drive in a straight line.
            correction = checkDirection();

            // telemetry.addData("1 imu heading", lastAngles.firstAngle);
            // telemetry.addData("2 global heading", globalAngle);
            // telemetry.addData("3 correction", correction);
            // telemetry.update();

            setDrivePower(leftFrontPower - correction,
                rightFrontPower + correction, leftBackPower - correction,
                rightBackPower + correction);
            idle();
        }

        setDrivePower(noPower, noPower, noPower, noPower);       // Stop all motors
    }

    // Functions for turning and checking robot angle for correction

    /* Resets the cumulative angle tracking to zero.   */

    private void resetAngle() {
        imu.resetYaw();
        lastAngles = imu.getRobotYawPitchRollAngles();

        globalAngle = 0;
    }

    /**
     * Get current cumulative angle rotation from last reset.
     *
     * @return Angle in degrees. + = left, - = right.
     */
    private double getAngle() {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        // Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();

        double deltaAngle = orientation.getYaw(AngleUnit.DEGREES) - lastAngles.getYaw(AngleUnit.DEGREES);

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = orientation;

        return globalAngle;
    }

    /**
     * See if we are moving in a straight line and if not return a power correction value.
     *
     * @return Power adjustment, + is adjust left - is adjust right.
     */
    private double checkDirection() {
        // The gain value determines how sensitive the correction is to direction changes.
        // You will have to experiment with your robot to get small smooth direction changes
        // to stay on a straight line.
        double correction, angle, gain = .05;

        angle = getAngle();

        if (angle == 0)
            correction = 0;             // no adjustment.
        else
            correction = -angle;        // reverse sign of angle for correction.

        correction = correction * gain;

        return correction;
    }

    /**
     * Rotate left or right the number of degrees. Does not support turning more than 180 degrees.
     *
     * @param degrees Degrees to turn, + is left - is right
     */
    private void rotate(int degrees, double power) {
        double leftPower, rightPower;

        // restart imu movement tracking.
        resetAngle();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).
        if (degrees < 0) {    // turn right.
            leftPower = power;
            rightPower = -power;
        } else if (degrees > 0) {    // turn left.
            leftPower = -power;
            rightPower = power;
        } else return;

        // set power to rotate.
        setDrivePower(leftPower, rightPower, leftPower, rightPower);

        // turn the motors off.
        setDrivePower(0.0, 0.0, 0.0, 0.0);

        // wait for rotation to stop.
        sleep(500);

        // reset angle tracking on new heading.
        resetAngle();
    }

}
