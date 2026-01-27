package org.firstinspires.ftc.teamcode;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.Utils_13233.LaunchControls;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MockMotorUtil {
    public DcMotor launcher, launcher2, intake, rightFront, leftFront, leftBack, rightBack, Sorter;
    public Servo Flipper;
    public HardwareMap hardwareMap;
    public IMU imu;
    public Limelight3A limelight;

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

        Sorter = mock(DcMotor.class);
        Flipper = mock(Servo.class);

        VoltSens = mock(VoltageSensor.class);
        imu = mock(IMU.class);

        limelight = mock(Limelight3A.class);


        // Mock the motors
        when(hardwareMap.get(DcMotor.class, "intake")).thenReturn(intake);

        when(hardwareMap.get(DcMotor.class, "leftFront")).thenReturn(leftFront);
        when(hardwareMap.get(DcMotor.class, "rightFront")).thenReturn(rightFront);
        when(hardwareMap.get(DcMotor.class, "leftBack")).thenReturn(leftBack);
        when(hardwareMap.get(DcMotor.class, "rightBack")).thenReturn(rightBack);

        when(hardwareMap.get(DcMotor.class, "Launcher")).thenReturn(launcher);
        when(hardwareMap.get(DcMotor.class, "Launcher2")).thenReturn(launcher2);

        when(hardwareMap.get(DcMotor.class, "sorter")).thenReturn(Sorter);
        when(hardwareMap.get(Servo.class, "flipper")).thenReturn(Flipper);

        when(hardwareMap.get(VoltageSensor.class, "Control Hub")).thenReturn(VoltSens);

        hardwareMap.voltageSensor = voltageSensorMap;
        when(voltageSensorMap.get("Control Hub")).thenReturn(VoltSens);

        // Mock the IMU (gyroscope)
        when(hardwareMap.get(IMU.class, "imu")).thenReturn(imu);
        when(hardwareMap.get(Limelight3A.class, "limelight")).thenReturn(limelight);

        // Set direction of the main drive motors
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);

        // IC2 port 0 on a Core Device Interface Module
        imu = hardwareMap.get(IMU.class, "imu");
    }
}
