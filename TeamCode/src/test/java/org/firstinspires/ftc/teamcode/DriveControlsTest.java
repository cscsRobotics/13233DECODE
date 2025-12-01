package org.firstinspires.ftc.teamcode;

import static org.mockito.Mockito.verify;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Utils_13233.DriveControls;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DriveControlsTest {
    public DriveControls driveControls;

    MockMotorUtil mockMotor = new MockMotorUtil();

    @BeforeEach
    void motorSetup() {
        mockMotor.setUp();
        driveControls = new DriveControls(mockMotor.hardwareMap);
    }


    @DisplayName("Pushing left stick Y fully should set all motors to 1.0")
    @Test
    void testSetDrivePower_forwardOnly() {
        // Forward input only, no strafe or rotation, full speed
        driveControls.setDrivePower(-1.0f, 0.0f, 0.0f,
            0.0f);

        // Expected power: all motors get 1.0
        verify(mockMotor.leftFront).setPower(-1.0);
        verify(mockMotor.rightFront).setPower(-1.0);
        verify(mockMotor.leftBack).setPower(-1.0);
        verify(mockMotor.rightBack).setPower(-1.0);
    }

    @DisplayName("Pushing left stick Y fully should set back right and front left motors to 1.0" +
        "and should set back left and front right motors to -1.0")
    @Test
    void testSetDrivePower_strafeRightOnly() {
        driveControls.setDrivePower(0.0f, -1.0f, 0.0f,
            0.0f);

        // Expected power: set back right and front left motors to 1.0 and should set back left and
        // front right motors to -1.0"
        // Expected power: all motors get 1.0
        verify(mockMotor.leftFront).setPower(1.0);
        verify(mockMotor.rightFront).setPower(-1.0);
        verify(mockMotor.leftBack).setPower(-1.0);
        verify(mockMotor.rightBack).setPower(1.0);
    }

    @DisplayName("Pushing right stick X fully should set the left motors to 1.0" +
        " and the right motors to -1.0")
    @Test
    void testSetDrivePower_turnRight() {
        driveControls.setDrivePower(0.0f, 0.0f, -1.0f,
            0.0f);

        // Expected power: left motors get 1.0 and right motors get -1.0
        verify(mockMotor.leftFront).setPower(1.0);
        verify(mockMotor.rightFront).setPower(-1.0);
        verify(mockMotor.leftBack).setPower(1.0);
        verify(mockMotor.rightBack).setPower(-1.0);
    }

    @DisplayName("Set zero power behavior to brake when true")
    @Test
    void testDriveBrakeActive() {
        driveControls.setDriveMotorZeroPowerBehavior(true);
        verify(mockMotor.leftFront).setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verify(mockMotor.rightFront).setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verify(mockMotor.leftBack).setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verify(mockMotor.rightBack).setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @DisplayName("Set zero power behavior to float when false")
    @Test
    void testDriveBrakeInactive() {
        driveControls.setDriveMotorZeroPowerBehavior(false);
        verify(mockMotor.leftFront).setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        verify(mockMotor.rightFront).setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        verify(mockMotor.leftBack).setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        verify(mockMotor.rightBack).setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    @DisplayName("Go Forward when only left stick is assigned")
    @Test
    void testDriveForwardOnly() {
        driveControls.setDrivePower(-1.0f);

        // Expected power: all motors get 1.0
        verify(mockMotor.leftFront).setPower(-1.0);
        verify(mockMotor.rightFront).setPower(-1.0);
        verify(mockMotor.leftBack).setPower(-1.0);
        verify(mockMotor.rightBack).setPower(-1.0);
    }
}
