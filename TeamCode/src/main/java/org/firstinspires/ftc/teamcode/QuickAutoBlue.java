package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Utils_13233.AutoDrive;
import org.firstinspires.ftc.teamcode.Utils_13233.AutoTurn;
import org.firstinspires.ftc.teamcode.Utils_13233.DriveControls;
import org.firstinspires.ftc.teamcode.Utils_13233.LaunchControls;
import org.firstinspires.ftc.teamcode.Utils_13233.RampControls;

@Autonomous(name = "QuickAutoBlue", group = "Autonomous")
public class QuickAutoBlue extends LinearOpMode {

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
            sleep(1200);
            drive.setDriveMotorZeroPowerBehavior(true);
            drive.setDrivePower(0);//brakes the reverse to ensure no major fouls are incurred

            launch.setLaunchPower(true);

            sleep(2500);//wait state to wait for launcher to spin up
            setIntakeDirection.setIntakeDirection(true);
            sleep(750);//runs ramp servos up, shooting 1 ball
            setIntakeDirection.setIntakeDirection(false);
            sleep(500);//turns off ramp
            setIntakeDirection.setIntakeDirection(true);
            sleep(750);//runs ramp,shooting 1 ball
            setIntakeDirection.setIntakeDirection(false);
            sleep(500);//turns off ramp
            setIntakeDirection.setIntakeDirection(true);
            sleep(5000);//runs ramp for 5 seconds, ensuring all balls are removed
            setIntakeDirection.setIntakeDirection(false);
            launch.setLaunchPower(false);//turns off launcher and ramp

            drive.setDrivePower(0, 1.0f, 0, 0);
            sleep(1000);
            drive.setDrivePower(1.0f);//strafes over to balls, ensuring ally has space to shoot, and moves off line
            sleep(1000);

        }
    }
}
