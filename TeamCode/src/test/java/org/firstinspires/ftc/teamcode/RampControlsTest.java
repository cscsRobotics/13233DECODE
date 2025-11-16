package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.Utils_13233.RampControls;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class RampControlsTest {
    public RampControls rampControls;

    MockMotorUtil mockMotor = new MockMotorUtil();

    @BeforeEach
    void motorSetup(){
        mockMotor.setUp();
        rampControls = new RampControls(mockMotor.hardwareMap);
    }

    @DisplayName("Intake should spin forward when forward button is pressed")
    @Test
    void testIntakeForward() {
        rampControls.setIntakeDirection(true, false);
        verify(mockMotor.intake).setPower(1.0);
        verify(mockMotor.rampServo1).setPower(-1.0);
        verify(mockMotor.rampServo2).setPower(1.0);
    }

    @DisplayName("Intake should spin backward when reverse button is pressed")
    @Test
    void testIntakeReverse() {
        rampControls.setIntakeDirection(false, true);
        verify(mockMotor.intake).setPower(-1.0);
        verify(mockMotor.rampServo1).setPower(1.0);
        verify(mockMotor.rampServo2).setPower(-1.0);
    }

    @DisplayName("Intake should not spin when direction buttons are not depressed")
    @Test
    void testIntakeNeutral() {
        rampControls.setIntakeDirection(false, false);
        verify(mockMotor.intake).setPower(0.0);
        verify(mockMotor.rampServo1).setPower(-0.0);
        verify(mockMotor.rampServo2).setPower(0.0);
    }

    @DisplayName("Intake should stop when both direction buttons pressed at the same time")
    @Test
    void testIntakeConflict() {
        rampControls.setIntakeDirection(true, true);
        verify(mockMotor.intake).setPower(0.0);
        verify(mockMotor.rampServo1).setPower(-0.0);
        verify(mockMotor.rampServo2).setPower(0.0);
    }
}
