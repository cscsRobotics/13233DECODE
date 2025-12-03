package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utils_13233.DriveControls;
import org.firstinspires.ftc.teamcode.Utils_13233.LaunchControls;
import org.firstinspires.ftc.teamcode.Utils_13233.MotorConstructor;
import org.firstinspires.ftc.teamcode.Utils_13233.RampControls;

@Autonomous(name = "QuickAuto")
public class QuickAuto extends LinearOpMode {

    private DriveControls drive;
    private LaunchControls launch;
    private RampControls setIntakeDirection;

    @Override
    public void runOpMode() {

        drive = new DriveControls(hardwareMap);
        launch = new LaunchControls(hardwareMap);
        setIntakeDirection = new RampControls(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            drive.setDrivePower(-1.0f);
            sleep(1500);
            drive.setDrivePower(0);

            launch.setLaunchPower(true);

            sleep(2000);
            setIntakeDirection.setIntakeDirection(1);
            sleep(1000);
            setIntakeDirection.setIntakeDirection(0);
            sleep(1000);
            setIntakeDirection.setIntakeDirection(1);
            sleep(3000);
            setIntakeDirection.setIntakeDirection(0);
        }
    }
}
