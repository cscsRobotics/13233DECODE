package org.firstinspires.ftc.teamcode.Utils_13233;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.limelightvision.limelight3a.*;

@TeleOp(name = "Limelight 3A AprilTag Test", group = "Vision")
public class LimelightAprilTagTest extends LinearOpMode {

    private Limelight3A limelight;

    @Override
    public void runOpMode() throws InterruptedException {

        // ===== INIT LIMELIGHT =====
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        // Pipeline must be an AprilTag pipeline (set in Limelight UI)
        limelight.pipelineSwitch(8);

        limelight.start();

        telemetry.addLine("Limelight initialized");
        telemetry.addLine("Waiting for start...");
        telemetry.update();

        waitForStart();

        // ===== MAIN LOOP =====
        while (opModeIsActive()) {

            LimelightResults results = limelight.getLatestResults();

            if (results != null && results.isValid() && results.targets.size() > 0) {

                for (LimelightTarget target : results.targets) {

                    if (target.getType() == LimelightTargetType.APRILTAG) {

                        telemetry.addLine("=== APRILTAG DETECTED ===");
                        telemetry.addData("Tag ID", target.getFiducialID());
                        telemetry.addData("X Offset", target.getX());
                        telemetry.addData("Y Offset", target.getY());
                        telemetry.addData("Area", target.getArea());
                        telemetry.addData("Skew", target.getSkew());
                        telemetry.addData("Latency (ms)", results.latency);
                        break;
                    }
                }

            } else {
                telemetry.addLine("No AprilTag detected");
            }

            telemetry.update();
        }

        limelight.stop();
    }
}
