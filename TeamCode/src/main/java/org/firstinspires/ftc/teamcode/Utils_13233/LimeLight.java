package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

public abstract class LimeLight extends LinearOpMode {
    private Limelight3A limelight;
    private IMU imu;
    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class,"limelight");
        limelight.pipelineSwitch(8); //remember to change limelight pipeline using browser
        imu = hardwareMap.get(IMU.class,"imu");
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot.LogoFacingDirection(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
            RevHubOrientationOnRobot.UsbFacingDirection.UP);
        imu.initalize(new IMU.Parameters(revHubOrientationOnRobot));
    public void start() {
        limelight.start();
    }
}
