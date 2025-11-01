package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 * Class the holds common functions and variable for the 13233 DECODE robot
 */
public class CommonControls {
    /**
     * Front left drive motor
     */
    public DcMotor leftFront;
    /**
     * Front right drive motor
     */
    public DcMotor rightFront;
    /**
     * Back left drive motor
     */
    public DcMotor leftBack;
    /**
     * Right back drive motor
     */
    public DcMotor rightBack;


    /**
     * The motor for that controls the ball intake
     */
    private DcMotor intake;

    /**
     * One of the motors used to shoot the balls
     */
    private DcMotor Launcher;
    /**
     * One of the motors used to shoot the balls
     */
    private DcMotor Launcher2;


    /**
     * One of the servos used to carry the balls up the ramp
     */
    private CRServo rampServo1;
    /**
     * One of the servos used to carry the balls up the ramp
     */
    private CRServo rampServo2;

    /**
     * One of the servos used to carry the balls up the ramp
     */
    private CRServo rampServo3;


    /**
     * The big flexible wheel used to assist the ramp servos in carrying the ball up the ramp
     */
    public DcMotor LauncherWheelM;

    /**
     * Constructor for the CommonControls class
     *
     * @param hardwareMap The hardware map for the robot
     */
    public CommonControls(HardwareMap hardwareMap) {
        // Map main Drive Motors
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        // Map Intake Motor
        intake = hardwareMap.get(DcMotor.class, "intake");

        // Map the launcher Prototype
        Launcher = hardwareMap.dcMotor.get("Launcher");
        Launcher2 = hardwareMap.dcMotor.get("Launcher2");
        rampServo1 = hardwareMap.crservo.get("rampServo1");
        rampServo2 = hardwareMap.crservo.get("rampServo2");
        LauncherWheelM = hardwareMap.dcMotor.get("launcherWheelM");
        rampServo3 = hardwareMap.crservo.get("rampServo3");


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
        rightFront.setPower(frontRightPower);
        leftFront.setPower(frontLeftPower);
        rightBack.setPower(backRightPower);
        leftBack.setPower(backLeftPower);
    }

    /**
     * Updates just the forward and back movement based on controller stick position
     *
     * @param controlLeftStickY Left stick Y setting, up/down
     */
    public void setDrivePower(float controlLeftStickY) {
        setDrivePower(controlLeftStickY, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Sets the power of all of the launch motors depending on the value of launchInput
     *
     * @param launchInput Button mapped to launch input
     */
    void setLaunchPower(boolean launchInput) {
        double power = launchInput ? 1.0 : 0.0;

        // Set the the power value to the motors
        Launcher.setPower(-power);
        Launcher2.setPower(power);
        LauncherWheelM.setPower(power);
    }

    /**
     * Controls the spin direction of the intake wheel based on controller buttons
     *
     * @param intakeForwardInput Button mapped to forward input
     * @param intakeReverseInput Button mapped to reverse input
     */
    void setIntakeDirection(boolean intakeForwardInput, boolean intakeReverseInput) {
        double intakePower = 0;
        // ^ is the XOR operator, will return true if only one variable is true
        // If intakeForwardInput OR intakeReverseInput is true then this is true, but not
        // if both are true, which is why this differs from the || logical or operator
        if (intakeForwardInput ^ intakeReverseInput) {
            // This uses an inline-if statement which is useful when assigning values to variables
            // This says set power = 1 if intakeForwardInput is true, else set it to -1
            intakePower = intakeForwardInput ? 1 : -1;
        }
        rampServo1.setPower(-intakePower);
        rampServo2.setPower(intakePower);
        rampServo3.setPower(intakePower);
        LauncherWheelM.setPower(intakePower * 0.5);
        intake.setPower(intakePower);
    }

    void setDriveBrake() {
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    void setDriveFloat() {
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }
}
