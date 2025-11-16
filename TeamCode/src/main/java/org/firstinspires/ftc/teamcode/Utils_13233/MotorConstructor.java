package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MotorConstructor {
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
    public DcMotor intake;

    /**
     * One of the motors used to shoot the balls
     */
    public DcMotor Launcher;
    /**
     * One of the motors used to shoot the balls
     */
    public DcMotor Launcher2;


    /**
     * One of the servos used to carry the balls up the ramp
     */
    public CRServo rampServo1;
    /**
     * One of the servos used to carry the balls up the ramp
     */
    public CRServo rampServo2;
    public CRServo rampServo3;
    public CRServo rampServo4;

    public MotorConstructor(HardwareMap hardwareMap){
        // Map main Drive Motors
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        // Map Intake Motor
        intake = hardwareMap.get(DcMotor.class, "intake");

        // Map the launcher Prototype
        Launcher  = hardwareMap.get(DcMotor.class, "Launcher");
        Launcher2 = hardwareMap.get(DcMotor.class, "Launcher2");

        rampServo1 = hardwareMap.get(CRServo.class, "rampServo1");
        rampServo2 = hardwareMap.get(CRServo.class, "rampServo2");
        rampServo3 = hardwareMap.get(CRServo.class, "rampServo3");
        rampServo4 = hardwareMap.get(CRServo.class, "rampServo4");

        // Set direction of the main drive motors
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set the direction of the intake motor
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
    }

}
