package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;

/**
 * Class holds motors and servos that are used throughout the code
 */
public class MotorConstructor {

    // Drive Motor
    public DcMotor leftFront;
    public DcMotor rightFront;
    public DcMotor leftBack;
    public DcMotor rightBack;


    // Intake
    public DcMotor intake;

    // Launch motors to shoot the balls out of the robot
    public DcMotor Launcher;
    public DcMotor Launcher2;

    // The motor that controls the sorter position
    public DcMotor Sorter;

    // The servo the pushes the balls into the launcher flywheels
    public Servo Flipper;

    // The battery voltage sensor
    public VoltageSensor VoltSens;

    //Rotation Sensor on the Control hub
    public IMU imu;

    // The limelight 3a used for scanning April Tags
    public Limelight3A limelight;

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

        Sorter = hardwareMap.get(DcMotor.class, "sorter");
        Flipper = hardwareMap.get(Servo.class, "flipper");

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
        Launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Launcher2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Launcher2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Sorter.setDirection(DcMotorSimple.Direction.REVERSE);
        Sorter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Sorter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Sorter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);
        limelight.start();


        //Flipper.setDirection(Servo.Direction.REVERSE);


        // Set the direction of the intake motor
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void leftFront(float v) {
    }
}
