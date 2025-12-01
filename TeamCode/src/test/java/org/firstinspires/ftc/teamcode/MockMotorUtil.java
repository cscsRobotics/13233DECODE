package org.firstinspires.ftc.teamcode;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.Utils_13233.LaunchControls;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MockMotorUtil {
    public DcMotor launcher, launcher2, intake, rightFront, leftFront, leftBack, rightBack;
    public CRServo rampServo1, rampServo2, rampServo3, rampServo4;
    public HardwareMap hardwareMap;
    public IMU imu;

    @Mock
    HardwareMap.DeviceMapping<VoltageSensor> voltageSensorMap;

    @Mock
    VoltageSensor VoltSens;


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

        VoltSens = mock(VoltageSensor.class);
        imu = mock(IMU.class);


        // Mock the motors
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
        when(hardwareMap.get(VoltageSensor.class, "Control Hub")).thenReturn(VoltSens);

        hardwareMap.voltageSensor = voltageSensorMap;
        when(voltageSensorMap.get("Control Hub")).thenReturn(VoltSens);

        // Mock the IMU (gyroscope)
        when(hardwareMap.get(IMU.class, "imu")).thenReturn(imu);

        // Set direction of the main drive motors
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);

        // IC2 port 0 on a Core Device Interface Module
        imu = hardwareMap.get(IMU.class, "imu");
    }
}
