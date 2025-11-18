package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public abstract class AutoTurn extends LinearOpMode {
    private final MotorConstructor motors;
    CommonAutoMethods autoMethods;

    private AutoTurn(HardwareMap hardwareMap) {
        this.motors = new MotorConstructor(hardwareMap);
        this.autoMethods = new CommonAutoMethods(hardwareMap);
    }

    private void resetAngle() {
        motors.imu.resetYaw();
        YawPitchRollAngles lastAngles = motors.imu.getRobotYawPitchRollAngles();

        int globalAngle = 0;
    }
}
