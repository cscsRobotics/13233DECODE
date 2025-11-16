package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utils_13233.DriveControls;
import org.firstinspires.ftc.teamcode.Utils_13233.MotorConstructor;

@Autonomous(name = "QuickAuto")
public class QuickAuto extends LinearOpMode {
    private final DriveControls drive;

    public QuickAuto(HardwareMap hardwareMap) {
        this.drive = new DriveControls(hardwareMap);
    }

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //CommonControls.setDriveBrake();
        waitForStart();
        if (opModeIsActive()) {// OpMode loop
            drive.setDrivePower(-1.0f);
            sleep(500);
            drive.setDrivePower(0);
        }
    }
}
