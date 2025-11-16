package org.firstinspires.ftc.teamcode;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utils_13233.LaunchControls;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class MockMotorUtil {
    public DcMotor launcher, launcher2, intake, rightFront, leftFront, leftBack, rightBack;
    public CRServo rampServo1, rampServo2, rampServo3, rampServo4;
    public HardwareMap hardwareMap;


    public void setUp() {
        MockitoAnnotations.openMocks(this);

        hardwareMap = mock(HardwareMap.class);

        intake = mock(DcMotor.class);

        leftFront = mock(DcMotor.class);
        rightFront = mock(DcMotor.class);
        leftBack = mock(DcMotor.class);
        rightBack = mock(DcMotor.class);

        launcher = mock(DcMotor.class);
        launcher2 = mock(DcMotor.class);

        rampServo1 = mock(CRServo.class);
        rampServo2 = mock(CRServo.class);
        rampServo3 = mock(CRServo.class);
        rampServo4 = mock(CRServo.class);


        when(hardwareMap.get(DcMotor.class, "intake")).thenReturn(intake);

        when(hardwareMap.get(DcMotor.class, "leftFront")).thenReturn(leftFront);
        when(hardwareMap.get(DcMotor.class, "rightFront")).thenReturn(rightFront);
        when(hardwareMap.get(DcMotor.class, "leftBack")).thenReturn(leftBack);
        when(hardwareMap.get(DcMotor.class, "rightBack")).thenReturn(rightBack);

        when(hardwareMap.get(DcMotor.class, "Launcher")).thenReturn(launcher);
        when(hardwareMap.get(DcMotor.class, "Launcher2")).thenReturn(launcher2);

        when(hardwareMap.get(CRServo.class, "rampServo1")).thenReturn(rampServo1);
        when(hardwareMap.get(CRServo.class, "rampServo2")).thenReturn(rampServo2);
        when(hardwareMap.get(CRServo.class, "rampServo3")).thenReturn(rampServo3);
        when(hardwareMap.get(CRServo.class, "rampServo4")).thenReturn(rampServo4);
    }
}
