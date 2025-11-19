package org.firstinspires.ftc.teamcode.Utils_13233;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.AutoMain;

public class CommonAutoMethods {
    private final MotorConstructor motors;
    AutoConstants autoConst;
    AutoTurn autoTurn;
    LinearOpMode opMode;

    public CommonAutoMethods(LinearOpMode opMode, HardwareMap hardwareMap) {
        this.motors = new MotorConstructor(hardwareMap);
        this.autoConst = new AutoConstants();
        this.autoTurn = new AutoTurn(opMode, hardwareMap);
        this.opMode = opMode;
    }

    protected void setDrivePower(double leftFrontPower, double rightFrontPower,
                                 double leftBackPower, double rightBackPower) {
        motors.rightFront.setPower(rightFrontPower);
        motors.rightBack.setPower(rightBackPower);
        motors.leftFront.setPower(leftFrontPower);
        motors.leftBack.setPower(leftBackPower);
    }

    public void resetEncoders() {
        motors.rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motors.rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motors.leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motors.leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    protected void runUsingEncoders() {
        motors.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motors.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motors.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motors.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
        YawPitchRollAngles orientation = motors.imu.getRobotYawPitchRollAngles();

        double deltaAngle = orientation.getYaw(AngleUnit.DEGREES) - autoConst.lastAngles.getYaw(AngleUnit.DEGREES);

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        autoConst.globalAngle += deltaAngle;

        autoConst.lastAngles = orientation;

        return autoConst.globalAngle;
    }

    /**
     * See if we are moving in a straight line and if not return a power correction value.
     *
     * @return Power adjustment, + is adjust left - is adjust right.
     */
    protected double checkDirection() {
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

    public void setUpIMU(RevHubOrientationOnRobot.LogoFacingDirection logoDirection,
                         RevHubOrientationOnRobot.UsbFacingDirection usbDirection) {
        try {
            RevHubOrientationOnRobot orientationOnRobot =
                new RevHubOrientationOnRobot(logoDirection, usbDirection);
            motors.imu.initialize(new IMU.Parameters(orientationOnRobot));

            telemetry.addData("imu calib status", "calibrated");
            telemetry.update();

            // initialize imu global variables after calibrating imu
            autoTurn.resetAngle();

        } catch (
            IllegalArgumentException e) {
            telemetry.addData("imu calib status", "failed - try again");
            telemetry.update();
        }
    }

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
    public Integer SelectDelayTime() {
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

        while (!opMode.isStopRequested() && !opMode.gamepad1.a) {
            if (opMode.gamepad1.left_bumper) {
                delayTimeMilliseconds -= 1000;

                // ensure delay time does not go negative
                if (delayTimeMilliseconds < 0) {
                    delayTimeMilliseconds = 0;
                }

                // Wait for the bumper to be released
                while (opMode.gamepad1.left_bumper) {
                    opMode.idle();
                }

                telemetry.addData("Delay", "%d (decrease)", delayTimeMilliseconds);
                telemetry.update();
            }

            if (opMode.gamepad1.right_bumper) {
                delayTimeMilliseconds += 1000;

                // ensure delay time is not greater than 10 seconds
                if (delayTimeMilliseconds > 10000) {
                    delayTimeMilliseconds = 10000;
                }

                while (opMode.gamepad1.right_bumper) {
                    opMode.idle();
                }
                telemetry.addData("Delay", "%d (increase)", delayTimeMilliseconds);
                telemetry.update();
            }
        }

        // Wait for user to release the a button
        while (!opMode.isStopRequested() && opMode.gamepad1.a) {
            opMode.idle();
        }


        // Display selected delay time
        telemetry.addData("Delay Time SET", delayTimeMilliseconds);
        telemetry.update();
        return delayTimeMilliseconds;       // returns selected delay time

    } // end SelectDelayTime

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
    public AutoMain.AutoMode SelectAutoMode() {
        // Local variable to store selected autonomous mode

        AutoMain.AutoMode autoMode = AutoMain.AutoMode.AUTO_MODE_NOT_SELECTED;
        // Display autonomous mode not selected yet
        telemetry.addData("AutoMode", "Not Selected");
        telemetry.update();


        // Loop until autonomous mode is selected
        while (!opMode.isStopRequested() && autoMode == AutoMain.AutoMode.AUTO_MODE_NOT_SELECTED) {
            autoMode = AutoMain.AutoMode.AUTO_MODE_DEFAULT;
            opMode.idle();
        }

        // Display selected autonomous mode
        telemetry.addData("Autonomous Mode", autoMode.toString());
        telemetry.update();

        // Wait for the user to release the button
        while (!opMode.isStopRequested() && (opMode.gamepad1.a || opMode.gamepad1.b || opMode.gamepad1.x || opMode.gamepad1.y)) {
            opMode.idle();
        }

        return autoMode;

    } // end SelectAutoMode
}

