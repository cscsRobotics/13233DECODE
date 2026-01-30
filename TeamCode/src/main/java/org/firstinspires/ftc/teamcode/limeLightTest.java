package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes.FiducialResult;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//removed so the DS is cleaner
//@Autonomous(name = "Limelight AprilTag Test")
public class limeLightTest extends LinearOpMode {

    private Limelight3A limelight;


    @Override
    public void runOpMode() {

        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        // Set AprilTag pipeline (must match Limelight web config)
        limelight.pipelineSwitch(0);
        limelight.start();

        telemetry.addLine("Waiting for start...");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            LLResult result = limelight.getLatestResult();

            if (result != null && result.isValid()) {
                for (FiducialResult tag : result.getFiducialResults()) {
                    telemetry.addData("AprilTag ID", tag.getFiducialId());

                }
            } else {
                telemetry.addLine("No tags detected");
            }

            telemetry.update();
        }
    }
}
