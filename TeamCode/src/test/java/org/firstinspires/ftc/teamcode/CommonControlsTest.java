package org.firstinspires.ftc.teamcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommonControlsTest {

    private DcMotor leftFront, rightFront, leftBack, rightBack, intake, Launcher, Launcher2,
        LauncherWheelM;
    private CRServo rampServo1, rampServo2;
    private HardwareMap hardwareMap;
    private CommonControls controls;

    @BeforeEach
    void setUp() {
        // Mock all motors
        leftFront = mock(DcMotor.class);
        rightFront = mock(DcMotor.class);
        leftBack = mock(DcMotor.class);
        rightBack = mock(DcMotor.class);

        intake = mock(DcMotor.class);
        Launcher = mock(DcMotor.class);
        Launcher2 = mock(DcMotor.class);
        LauncherWheelM = mock(DcMotor.class);

        rampServo1 = mock(CRServo.class);
        rampServo2 = mock(CRServo.class);



        // Mock HardwareMap and its nested dcMotor map
        hardwareMap = mock(HardwareMap.class);
        HardwareMap.DeviceMapping<CRServo> crServoMap = mock(HardwareMap.DeviceMapping.class);
        HardwareMap.DeviceMapping<DcMotor> dcMotorMap = mock(HardwareMap.DeviceMapping.class);

        when(hardwareMap.get(DcMotor.class, "intake")).thenReturn(intake);
        when(hardwareMap.get(DcMotor.class, "leftFront")).thenReturn(leftFront);
        when(hardwareMap.get(DcMotor.class, "rightFront")).thenReturn(rightFront);
        when(hardwareMap.get(DcMotor.class, "leftBack")).thenReturn(leftBack);
        when(hardwareMap.get(DcMotor.class, "rightBack")).thenReturn(rightBack);

        when(dcMotorMap.get("leftFront")).thenReturn(leftFront);
        when(dcMotorMap.get("rightFront")).thenReturn(rightFront);
        when(dcMotorMap.get("leftBack")).thenReturn(leftBack);
        when(dcMotorMap.get("rightBack")).thenReturn(rightBack);
        when(dcMotorMap.get("Launcher")).thenReturn(Launcher);
        when(dcMotorMap.get("Launcher2")).thenReturn(Launcher2);
        when(dcMotorMap.get("launcherWheelM")).thenReturn(LauncherWheelM);

        when(crServoMap.get("rampServo1")).thenReturn(rampServo1);
        when(crServoMap.get("rampServo2")).thenReturn(rampServo2);

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


        hardwareMap.dcMotor = dcMotorMap;
        hardwareMap.crservo = crServoMap;
        controls = new CommonControls(hardwareMap);
    }

    @DisplayName("Pushing left stick Y fully should set all motors to 1.0")
    @Test
    void testSetDrivePower_forwardOnly() {
        // Forward input only, no strafe or rotation, full speed
        controls.setDrivePower(1.0f, 0.0f, 0.0f,
            0.0f);

        // Expected power: all motors get 1.0
        verify(leftFront).setPower(1.0);
        verify(rightFront).setPower(1.0);
        verify(leftBack).setPower(1.0);
        verify(rightBack).setPower(1.0);
    }

    @DisplayName("Pushing left stick Y fully should set back right and front left motors to 1.0" +
        "and should set back left and front right motors to -1.0")
    @Test
    void testSetDrivePower_strafeRightOnly(){
        controls.setDrivePower(0.0f, -1.0f, 0.0f,
            0.0f);

        // Expected power: set back right and front left motors to 1.0 and should set back left and
        // front right motors to -1.0"
        // Expected power: all motors get 1.0
        verify(leftFront).setPower(1.0);
        verify(rightFront).setPower(-1.0);
        verify(leftBack).setPower(-1.0);
        verify(rightBack).setPower(1.0);
    }

    @DisplayName("Pushing right stick X fully should set the left motors to 1.0" +
        " and the right motors to -1.0")
    @Test
    void testSetDrivePower_turnRight(){
        controls.setDrivePower(0.0f, 0.0f, -1.0f,
            0.0f);

        // Expected power: left motors get 1.0 and right motors get -1.0
        verify(leftFront).setPower(1.0);
        verify(rightFront).setPower(-1.0);
        verify(leftBack).setPower(1.0);
        verify(rightBack).setPower(-1.0);
    }

    @DisplayName("Intake should spin forward when forward button is pressed")
    @Test
    void testIntakeForward() {
        controls.setIntakeDirection(true, false);
        verify(intake).setPower(1.0);
        verify(rampServo1).setPower(-1.0);
        verify(rampServo2).setPower(1.0);
        verify(LauncherWheelM).setPower(0.5);
    }

    @DisplayName("Intake should spin backward when reverse button is pressed")
    @Test
    void testIntakeReverse() {
        controls.setIntakeDirection(false, true);
        verify(intake).setPower(-1.0);
        verify(rampServo1).setPower(1.0);
        verify(rampServo2).setPower(-1.0);
        verify(LauncherWheelM).setPower(-0.5);
    }

    @DisplayName("Intake should not spin when direction buttons are not depressed")
    @Test
    void testIntakeNeutral() {
        controls.setIntakeDirection(false, false);
        verify(intake).setPower(0.0);
        verify(rampServo1).setPower(-0.0);
        verify(rampServo2).setPower(0.0);
        verify(LauncherWheelM).setPower(0.0);
    }

    @DisplayName("Intake should stop when both direction buttons pressed at the same time")
    @Test
    void testIntakeConflict() {
        controls.setIntakeDirection(true, true);
        verify(intake).setPower(0.0);
        verify(rampServo1).setPower(-0.0);
        verify(rampServo2).setPower(0.0);
        verify(LauncherWheelM).setPower(0.0);
    }

    @DisplayName("Launcher should spin when input given")
    @Test
    void testLaunchPower(){
        controls.setLaunchPower(true);
        verify(Launcher).setPower(-1.0);
        verify(Launcher2).setPower(1.0);
        verify(LauncherWheelM).setPower(1.0);
    }

    @DisplayName("Launcher should stop when no input it given")
    @Test
    void testNoLaunchPower(){
        controls.setLaunchPower(false);
        verify(Launcher).setPower(-0.0);
        verify(Launcher2).setPower(0.0);
        verify(LauncherWheelM).setPower(0.0);
    }
}
