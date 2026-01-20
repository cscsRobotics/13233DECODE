package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes.FiducialResult;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Utils_13233.DriveControls;
import org.firstinspires.ftc.teamcode.Utils_13233.LaunchControls;
import org.firstinspires.ftc.teamcode.Utils_13233.LimelightControls;
import org.firstinspires.ftc.teamcode.Utils_13233.MotorConstructor;
import org.firstinspires.ftc.teamcode.Utils_13233.SorterControls;

import java.util.List;

@Autonomous(name = "AprilTagBlueAuto", group = "Auto")

public class LimeLightAutoBlue extends LinearOpMode {

    // Constructors for the utils classes
    private DriveControls drive;
    private LaunchControls launch;
    private SorterControls sorter;
    private MotorConstructor motors;
    private LimelightControls limelightCont;


    //This function is executed when this Op Mode is selected from the Driver Station
    @Override
    public void runOpMode() {
        // Create the utils classes
        drive = new DriveControls(hardwareMap);
        launch = new LaunchControls(hardwareMap);
        motors = new MotorConstructor(hardwareMap);
        limelightCont = new LimelightControls(hardwareMap);
        sorter = new SorterControls(motors);
        waitForStart();


        while (opModeIsActive()) {
            telemetry.addData("ID", limelightCont.getTagID());
            telemetry.update();
        }
    }
}
