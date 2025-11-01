package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;

@Autonomous(name = "QuickAuto")
public class QuickAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Create a new CommonControls object
        CommonControls CommonControls = new CommonControls(hardwareMap);

        //CommonControls.setDriveBrake();
        waitForStart();
        if (opModeIsActive()) {// OpMode loop
            CommonControls.setDrivePower(-1.0f);
            sleep(500);
            CommonControls.setDrivePower(0);
        }
    }
}
