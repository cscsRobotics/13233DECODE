package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Utils_13233.AutoDrive;
import org.firstinspires.ftc.teamcode.Utils_13233.AutoTurn;
import org.firstinspires.ftc.teamcode.Utils_13233.DriveControls;
import org.firstinspires.ftc.teamcode.Utils_13233.LaunchControls;
import org.firstinspires.ftc.teamcode.Utils_13233.MotorConstructor;
import org.firstinspires.ftc.teamcode.Utils_13233.RampControls;
import org.firstinspires.ftc.teamcode.Utils_13233.SorterControls;

@Autonomous(name = "QuickAutoBlue", group = "Autonomous")
public class QuickAutoBlue extends LinearOpMode {

    private DriveControls drive;
    private LaunchControls launch;
    private SorterControls sorter;
    private MotorConstructor motors;
    private AutoDrive autoDrive;

    private AutoTurn autoTurn;

    private LinearOpMode opMode;

    @Override
    public void runOpMode() {

        drive = new DriveControls(hardwareMap);
//        autoDrive = new AutoDrive(opMode, hardwareMap);
//        autoTurn = new AutoTurn(opMode, hardwareMap);
        launch = new LaunchControls(hardwareMap);
        sorter = new SorterControls(motors);


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {

            //autoDrive.driveForward(10.0, AutoConstants.quarterPower);
            //autoTurn.rotate(90, AutoConstants.quarterPower);


            //Jacob's so useful code

            drive.setDrivePower(-1.0f);
            sleep(1200);
            drive.setDriveMotorZeroPowerBehavior(true);
            drive.setDrivePower(0);//brakes the reverse to ensure no major fouls are incurred
            sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 1);
            //sets position to ensure the ball is correctly lined up
            launch.setLaunchPower(true);

            sleep(2500);//wait state to wait for launcher to spin up
            motors.Flipper.setDirection(Servo.Direction.FORWARD);//runs servo forward, shooting 1st ball
            motors.Flipper.setPosition(0.0);
            sleep(1000);//runs flipper servo into motor
            motors.Flipper.setDirection(Servo.Direction.REVERSE);
            motors.Flipper.setPosition(0.15);
            //2nd ball code
            sleep(750);//waits to spin turntable
            sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 2);
            sleep(1500);//waits while turntable spins
            motors.Flipper.setDirection(Servo.Direction.FORWARD);//runs servo forward, shooting 2nd ball
            motors.Flipper.setPosition(0.0);
            sleep(1000);
            motors.Flipper.setDirection(Servo.Direction.REVERSE);
            motors.Flipper.setPosition(0.15);//brings servo back up to avoid turn table collision
            //3rd ball code
            sleep(1000);
            sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 3);
            sleep(1000);//turns off ramp
            motors.Flipper.setDirection(Servo.Direction.FORWARD);//runs servo forward, shooting 3rd ball
            motors.Flipper.setPosition(0.0);
            sleep(1000);// sets servo back to init pos
            motors.Flipper.setDirection(Servo.Direction.REVERSE);//runs servo forward, shooting 1st ball
            motors.Flipper.setPosition(0.15);
            sleep(1000);
            sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 1);//brings sorter back to init
            launch.setLaunchPower(false);//turns off launcher and ramp
            //strafing section
            drive.setDrivePower(0, 1.0f, 0, 0);
            sleep(1000);
            drive.setDrivePower(1.0f);//strafes over to balls, ensuring ally has space to shoot, and moves off line
            sleep(1000);
            drive.setDrivePower(0.0f);//stops

        }
    }
}
