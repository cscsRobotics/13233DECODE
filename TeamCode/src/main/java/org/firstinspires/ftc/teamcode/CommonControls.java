package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class CommonControls {

    private final DcMotor leftFront;
    private final DcMotor rightFront;
    private final DcMotor leftBack;
    private final DcMotor rightBack;
    //Intake motor
    private final DcMotor intake;

    public CommonControls(HardwareMap hardwareMap) {
        // Main Drive Motors
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        // Intake Motor
        intake = hardwareMap.get(DcMotor.class, "intake");

        // Set direction of the main drive motors
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set the direction of the intake motor
        intake.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set the zero power behavior
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    /**
     * Updates all the drive controls based on the current gamepad stick positions
     *
     * @param controlLeftStickY Left stick Y setting, up/down
     * @param controlLeftStickX Left stick X setting, left/right
     * @param controlRightStick Right Stick setting, controls left right turn
     * @param speedControl      Trigger setting, controls speed of the robot
     */
    public void setDrivePower(float controlLeftStickY, float controlLeftStickX,
                              float controlRightStick, float speedControl) {
        // For readability and flexibility for future control input changes
        //noinspection UnnecessaryLocalVariable
        float forward = controlLeftStickY;
        //noinspection UnnecessaryLocalVariable
        float strafe = controlLeftStickX;
        //noinspection UnnecessaryLocalVariable
        float rotate = controlRightStick;
        float speed = 1 - speedControl;

        float frontRightPower = (forward + strafe + rotate) * (speed);
        float frontLeftPower = (forward - strafe - rotate) * (speed);
        float backRightPower = (forward - strafe + rotate) * (speed);
        float backLeftPower = (forward + strafe - rotate) * (speed);

        rightFront.setPower(frontRightPower);
        leftFront.setPower(frontLeftPower);
        rightBack.setPower(backRightPower);
        leftBack.setPower(backLeftPower);
    }

    /**
     * Controls the spin direction of the intake wheel based on controller buttons
     *
     * @param intakeForwardInput Button mapped to forward input
     * @param intakeReverseInput Button mapped to reverse input
     */
    void setIntakeDirection(boolean intakeForwardInput, boolean intakeReverseInput) {
        double power = 0;

        // ^ is the XOR operator, will return true if only one variable is true
        // If intakeForwardInput OR intakeReverseInput is true then this is true, but not
        // if both are true, which is why this differs from the || logical or operator
        if (intakeForwardInput ^ intakeReverseInput) {
            // This uses an inline-if statement which is useful when assigning values to variables
            // This says set power = 1 if intakeForwardInput is true, else set it to -1
            power = intakeForwardInput ? 1 : -1;
        }

        intake.setPower(power);
    }
}
