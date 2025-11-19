package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Utils_13233.RampControls;
import org.firstinspires.ftc.teamcode.Utils_13233.TeleOpCommonMethods;
import org.junit.jupiter.api.BeforeEach;

public class TeleOpCommonMethodsTest {
    public TeleOpCommonMethods teleOpMethods;

    MockMotorUtil mockMotor = new MockMotorUtil();

    @BeforeEach
    void motorSetup() {
        LinearOpMode opMode = null;
        mockMotor.setUp();
        teleOpMethods = new TeleOpCommonMethods(null);
    }
}
