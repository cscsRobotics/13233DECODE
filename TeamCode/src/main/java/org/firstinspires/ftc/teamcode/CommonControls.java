package org.firstinspires.ftc.teamcode;

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
    public float launchPower = 1.0f;
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
    private CRServo rampServo3;
    private CRServo rampServo4;

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
        rampServo3 = hardwareMap.crservo.get("rampServo3");
        rampServo4 = hardwareMap.crservo.get("rampServo4");
        // Set direction of the main drive motors
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set the direction of the intake motor
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
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
        // Remember the direction of the stick is the opposite of what you would expect
        // up is in the negatives and down is in the positives
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

    void setDriveMotorZeroPowerBehavior(boolean input) {
        if (input) {
            setDriveBrake();
        } else {
            setDriveFloat();
        }
    }
}
