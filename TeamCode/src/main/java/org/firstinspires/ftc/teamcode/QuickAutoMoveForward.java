package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Utils_13233.AutoDrive;
import org.firstinspires.ftc.teamcode.Utils_13233.AutoTurn;
import org.firstinspires.ftc.teamcode.Utils_13233.DriveControls;
import org.firstinspires.ftc.teamcode.Utils_13233.LaunchControls;
import org.firstinspires.ftc.teamcode.Utils_13233.RampControls;

@Autonomous(name = "QuickAutoMoveForward", group = "Autonomous")
public class QuickAutoMoveForward extends LinearOpMode {

    private DriveControls drive;
    private LaunchControls launch;
    private RampControls setIntakeDirection;
    private AutoDrive autoDrive;

    private AutoTurn autoTurn;

    private LinearOpMode opMode;

    @Override
    public void runOpMode() {

        drive = new DriveControls(hardwareMap);
//        autoDrive = new AutoDrive(opMode, hardwareMap);
//        autoTurn = new AutoTurn(opMode, hardwareMap);
        launch = new LaunchControls(hardwareMap);
        setIntakeDirection = new RampControls(hardwareMap);


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {

            //autoDrive.driveForward(10.0, AutoConstants.quarterPower);
            //autoTurn.rotate(90, AutoConstants.quarterPower);


            //Jacob's not so useful code

            drive.setDrivePower(-1.0f);
            sleep(1200);//moves and brakes
            drive.setDriveMotorZeroPowerBehavior(true);
            drive.setDrivePower(0);
        }


    }
}
