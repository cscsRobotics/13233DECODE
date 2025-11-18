package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class CommonAutoMethods {
    private final MotorConstructor motors;

    public CommonAutoMethods(HardwareMap hardwareMap) {
        this.motors = new MotorConstructor(hardwareMap);
    }

    protected void setDrivePower(double leftFrontPower, double rightFrontPower,
                                 double leftBackPower, double rightBackPower) {
        motors.rightFront.setPower(rightFrontPower);
        motors.rightBack.setPower(rightBackPower);
        motors.leftFront.setPower(leftFrontPower);
        motors.leftBack.setPower(leftBackPower);
    }

    protected void resetEncoders() {
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
}
