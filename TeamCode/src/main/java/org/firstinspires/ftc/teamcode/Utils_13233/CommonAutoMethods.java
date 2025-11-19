package org.firstinspires.ftc.teamcode.Utils_13233;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class CommonAutoMethods {
    private final MotorConstructor motors;
    AutoConstants autoConst;
    AutoTurn autoTurn;

    public CommonAutoMethods(LinearOpMode opMode, HardwareMap hardwareMap) {
        this.motors = new MotorConstructor(hardwareMap);
        this.autoConst = new AutoConstants();
        this.autoTurn = new AutoTurn(opMode, hardwareMap);
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
}

