package org.firstinspires.ftc.teamcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommonControlsTest {

    private DcMotor leftFront, rightFront, leftBack, rightBack, intake;
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

        // Mock HardwareMap and its nested dcMotor map
        hardwareMap = mock(HardwareMap.class);
        HardwareMap.DeviceMapping<DcMotor> dcMotorMap = mock(HardwareMap.DeviceMapping.class);

        when(hardwareMap.get(DcMotor.class, "intake")).thenReturn(intake);
        when(hardwareMap.get(DcMotor.class, "leftFront")).thenReturn(leftFront);
        when(hardwareMap.get(DcMotor.class, "rightFront")).thenReturn(rightFront);
        when(hardwareMap.get(DcMotor.class, "leftBack")).thenReturn(leftBack);
        when(hardwareMap.get(DcMotor.class, "rightBack")).thenReturn(rightBack);

        controls = new CommonControls(hardwareMap);
    }

    @DisplayName("Pushing left stick Y fully should set all motors to 1.0")
    @Test
    void testSetDrivePower_forwardOnly() {
        // Forward input only, no strafe or rotation, full speed
        controls.setDrivePower(1.0f, 0.0f, 0.0f, 0.0f);

        // Expected power: all motors get 1.0
        verify(leftFront).setPower(1.0);
        verify(rightFront).setPower(1.0);
        verify(leftBack).setPower(1.0);
        verify(rightBack).setPower(1.0);
    }

    @DisplayName("Intake should spin forward when forward button is pressed")
    @Test
    void testIntakeForward() {
        assertEquals(1.0, controls.setIntakePower(true, false));
    }

    @DisplayName("Intake should spin backward when reverse button is pressed")
    @Test
    void testIntakeReverse() {
        assertEquals(-1.0, controls.setIntakePower(false, true));
    }

    @DisplayName("Intake should not spin when direction buttons are not depressed")
    @Test
    void testIntakeNeutral() {
        assertEquals(0.0, controls.setIntakePower(false, false));
    }

    @DisplayName("Intake should stop when both direction buttons pressed at the same time")
    @Test
    void testIntakeConflict() {
        assertEquals(0.0, controls.setIntakePower(true, true));
    }
}
