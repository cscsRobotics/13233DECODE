package org.firstinspires.ftc.teamcode.Utils_13233;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;


public class AutoDrive {
    private final MotorConstructor motors;
    CommonAutoMethods autoMethods;
    private final LinearOpMode opMode;

    public AutoDrive(LinearOpMode opMode, HardwareMap hardwareMap) {
        this.opMode = opMode;
        this.motors = new MotorConstructor(hardwareMap);
        this.autoMethods = new CommonAutoMethods(opMode, hardwareMap);
    }


    /**
     * Function: driveForward
     * <p>
     * This function is called to have the robot move forward.
     * The robot speed is passed in and that value is used for
     * all wheels.
     */
    public void driveForward(double inches, double power) {
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
     * Function: strafeLeft
     * <p>
     * This function is called to have the robot move sideways
     * in a left  direction
     */
    private void strafeLeft(double inches, double power) {
        driveInches(inches, -power, power, power, -power);
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
     * Function: driveInches
     * This function is called to have the robot move straight
     * in a forward or reverse direction.
     * <p>
     * Strafe Forward = negative front wheels, positive back
     * wheels
     */
    private void driveInches(double inches, double leftFrontPower, double rightFrontPower, double leftBackPower, double rightBackPower) {
        double counts = inches * AutoConstants.countsPerInch;

        autoMethods.resetEncoders();
        autoMethods.runUsingEncoders();

        double voltage = motors.VoltSens.getVoltage();  // read current battery voltage

        double leftFrontPowerCont = ((AutoConstants.batteryConst * leftFrontPower) / voltage);
        double rightFrontPowerCont = ((AutoConstants.batteryConst * rightFrontPower) / voltage);
        double leftBackPowerCont = ((AutoConstants.batteryConst * leftBackPower) / voltage);
        double rightBackPowerCont = ((AutoConstants.batteryConst * rightBackPower) / voltage);

        autoMethods.setDrivePower(leftFrontPowerCont, rightFrontPowerCont, leftBackPowerCont, rightBackPowerCont);

        while (opMode.opModeIsActive() &&
            (Math.abs(motors.leftFront.getCurrentPosition()) +
                Math.abs(motors.rightFront.getCurrentPosition()) / 2.0) < Math.abs(counts)) {
            // Use gyro to drive in a straight line.
            double correction = autoMethods.checkDirection();

            // telemetry.addData("1 imu heading", lastAngles.firstAngle);
            // telemetry.addData("2 global heading", globalAngle);
            // telemetry.addData("3 correction", correction);
            // telemetry.update();

            autoMethods.setDrivePower(leftFrontPower - correction,
                rightFrontPower + correction, leftBackPower - correction,
                rightBackPower + correction);
            opMode.idle();
        }

        autoMethods.setDrivePower(AutoConstants.noPower, AutoConstants.noPower, AutoConstants.noPower, AutoConstants.noPower);       // Stop all motors
    }
}
