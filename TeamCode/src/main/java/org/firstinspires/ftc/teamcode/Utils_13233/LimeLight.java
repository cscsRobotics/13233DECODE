package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Limelight AprilTag Test")

public class LimelightAuto extends LinearOpMode {
    Limelight3A limelight;

    @Override
    public void runOpMode() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        limelight.pipelineSwitch(0); // AprilTag pipeline
        limelight.start();

        waitForStart();

        while (opModeIsActive()) {
            var results = limelight.getLatestResults();

            if (results != null && results.isValid()) {
                if (!results.targets.isEmpty()) {
                    int tagID = results.targets.get(0).getFiducialID();
                    telemetry.addData("AprilTag ID", tagID);
                }
            }
            telemetry.update();
        }
    }
}

