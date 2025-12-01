package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.VoltageSensor;

/**
 * Class holds motors and servos that are used throughout the code
 */
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

    public VoltageSensor VoltSens;
    public IMU imu;

    public MotorConstructor(HardwareMap hardwareMap) {
        // Map main Drive Motors
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        // Map Intake Motor
        intake = hardwareMap.get(DcMotor.class, "intake");

        // Map the launcher Prototype
        Launcher = hardwareMap.get(DcMotor.class, "Launcher");
        Launcher2 = hardwareMap.get(DcMotor.class, "Launcher2");

        // Map the ramp servos
        rampServo1 = hardwareMap.get(CRServo.class, "rampServo1");
        rampServo2 = hardwareMap.get(CRServo.class, "rampServo2");
        rampServo3 = hardwareMap.get(CRServo.class, "rampServo3");
        rampServo4 = hardwareMap.get(CRServo.class, "rampServo4");

        // Control hub voltage sensor
        // Used to move at a constant speed in auto regardless of battery voltage
        VoltSens = hardwareMap.voltageSensor.get("Control Hub");

        // IC2 port 0 on a Core Device Interface Module
        imu = hardwareMap.get(IMU.class, "imu");

        // Set direction of the main drive motors
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set the direction of the launcher motor
        Launcher.setDirection(DcMotorSimple.Direction.REVERSE);
        Launcher2.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set the launch motors to run using encoders
        Launcher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Launcher2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Set the direction of the intake motor
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
    }

}
