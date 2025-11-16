package org.firstinspires.ftc.teamcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.MockMotorUtil;
import org.firstinspires.ftc.teamcode.Utils_13233.CommonControls;
import org.firstinspires.ftc.teamcode.Utils_13233.DriveControls;
import org.firstinspires.ftc.teamcode.Utils_13233.LaunchControls;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
public class LaunchControlsTest {
    public LaunchControls launchControls;

    MockMotorUtil mockMotor = new MockMotorUtil();

    @BeforeEach
    void motorSetup(){
        mockMotor.setUp();
        launchControls = new LaunchControls(mockMotor.hardwareMap);
    }

    @DisplayName("Launcher should spin when input given")
    @Test
    void testLaunchPower() {
        launchControls.setLaunchPower(true);
        verify(mockMotor.launcher).setPower(-1.0);
        verify(mockMotor.launcher2).setPower(1.0);
    }

    @DisplayName("Launcher should stop when no input it given")
    @Test
    void testNoLaunchPower() {
        launchControls.setLaunchPower(false);
        verify(mockMotor.launcher).setPower(-0.0);
        verify(mockMotor.launcher2).setPower(0.0);
    }
}
