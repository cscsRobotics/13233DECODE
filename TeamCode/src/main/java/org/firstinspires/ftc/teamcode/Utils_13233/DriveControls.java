package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveControls {
    private final MotorConstructor motors;

    public DriveControls(HardwareMap hardwareMap) {
        this.motors = new MotorConstructor(hardwareMap);
    }

    // TODO:
    //        Implement field oriented drive

    /**
     * Updates all the drive controls based on the current gamepad stick positions
     *
     * @param controlLeftStickY Left stick Y setting, up/down
     * @param controlLeftStickX Left stick X setting, left/right
     * @param controlRightStick Right Stick setting, controls left right turn
     * @param speedLimiter      Trigger setting, controls speed of the robot
     */
    public void setDrivePower(float controlLeftStickY, float controlLeftStickX,
                              float controlRightStick, float speedLimiter) {
        // For readability and flexibility for future control input changes
        //noinspection UnnecessaryLocalVariable
        float forward = controlLeftStickY;
        //noinspection UnnecessaryLocalVariable
        float strafe = controlLeftStickX;
        //noinspection UnnecessaryLocalVariable
        float rotate = controlRightStick;
        float speed = Math.max(1.0f - speedLimiter, 0.5f);


        // Calculate the power for each motor
        float frontRightPower = (forward + strafe + rotate) * (speed);
        float frontLeftPower = (forward - strafe - rotate) * (speed);
        float backRightPower = (forward - strafe + rotate) * (speed);
        float backLeftPower = (forward + strafe - rotate) * (speed);

        // Set the power for each of the motors
        motors.rightFront.setPower(frontRightPower);
        motors.leftFront.setPower(frontLeftPower);
        motors.rightBack.setPower(backRightPower);
        motors.leftBack.setPower(backLeftPower);
    }

    /**
     * Updates just the forward and back movement based on controller stick position
     *
     * @param controlLeftStickY Left stick Y setting, up/down
     */
    public void setDrivePower(float controlLeftStickY) {
        setDrivePower(controlLeftStickY, 0.0f, 0.0f, 0.0f);
    }

    // TODO: Create JavaDoc
    void setDriveBrake() {
        motors.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors.rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors.leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors.rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // TODO: Create JavaDoc
    void setDriveFloat() {
        motors.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motors.rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motors.leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motors.rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    // TODO: Create JavaDoc
    public void setDriveMotorZeroPowerBehavior(boolean input) {
        if (input) {
            setDriveBrake();
        } else {
            setDriveFloat();
        }
    }

}
