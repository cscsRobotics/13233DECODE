package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes.FiducialResult;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.util.List;


@Autonomous(name = "AprilTag Auto", group = "Auto")

public class LimelightAuto extends LinearOpMode {

    Limelight3A limelight;

    @Override
    public void runOpMode() {

        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);
        limelight.start();

        waitForStart();

        while (opModeIsActive()) {

            LLResult result = limelight.getLatestResult();

            if (result != null && result.isValid()) {

                for (FiducialResult tag : result.getFiducialResults()) {
                    telemetry.addData("ID", tag.fiducialId);
                    telemetry.addData("X", tag.poseX);
                    telemetry.addData("Y", tag.poseY);
                }
            }

            telemetry.update();
        }
    }
}
