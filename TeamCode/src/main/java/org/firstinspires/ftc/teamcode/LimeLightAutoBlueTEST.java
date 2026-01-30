package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes.FiducialResult;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.teamcode.Utils_13233.DriveControls;
import org.firstinspires.ftc.teamcode.Utils_13233.LaunchControls;
import org.firstinspires.ftc.teamcode.Utils_13233.LimelightControls;
import org.firstinspires.ftc.teamcode.Utils_13233.MotorConstructor;
import org.firstinspires.ftc.teamcode.Utils_13233.SorterControls;

@Autonomous(name = "CloseBlue", group = "Auto")
public class LimeLightAutoBlueTEST extends LinearOpMode {

    // ================= HARDWARE =================

    private DriveControls drive;
    private LaunchControls launch;
    private SorterControls sorter;
    private MotorConstructor motors;
    private LimelightControls limelightCont;

    @Override
    public void runOpMode() {

        //INIT
        drive = new DriveControls(hardwareMap);
        launch = new LaunchControls(hardwareMap);
        motors = new MotorConstructor(hardwareMap);
        limelightCont = new LimelightControls(hardwareMap);
        sorter = new SorterControls(motors);
        telemetry.addLine("Initialized. Waiting for start...");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) return;

        // - PHASE 1: MOVE TO SEE TAG
//        preMove();
        drive.setDrivePower(0, -1.0f, 0, 0);//strafes over to launch
        sleep(1400);

        int tagID = scanTag();

        drive.setDrivePower(0.0f, 0.0f, -1.0f, 0.0f);
        sleep(400);

        drive.setDrivePower(0.0f);

        // - PHASE 2: DETECT TAG

        telemetry.addData("ID", limelightCont.getTagID());
        telemetry.update();
        sleep(500);

        //  PHASE 3: RUN AUTO
        runAuto(tagID);

        //  END
        requestOpModeStop();
    }

    // ==========================================================
    // ===================== PHASE METHODS ======================
    // ==========================================================

//    private void preMove() {
//    }

    private int scanTag() {
        int tagID = -1;
        long startTime = System.currentTimeMillis();

        while (opModeIsActive()
                && tagID == -1
                && System.currentTimeMillis() - startTime < 2000) {

            tagID = limelightCont.getTagID();

            telemetry.addData("Scanning Tag", tagID);
            telemetry.update();
        }

        return tagID;
    }

    private void runAuto(int tagID) {

        switch (tagID) {

            case 21:
                telemetry.addLine("AUTO: Green Purple Purple");
                telemetry.update();
                auto21();
                break;

            case 22:
                telemetry.addLine("AUTO: Purple Green Purple");
                telemetry.update();
                auto22();
                break;

            case 23:
                telemetry.addLine("AUTO: Purple Purple Green");
                telemetry.update();
                auto23();
                break;

            default:
                telemetry.addLine("AUTO: DEFAULT (NO TAG)");
                telemetry.update();
                auto24();
                break;
        }
    }

    // ==========================================================
    // ===================== AUTO PATHS =========================
    // ==========================================================

    private void auto21() {
        //runs GPP
        sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 1);
        //sets position to ensure the ball is correctly lined up
        launch.setLaunchPower(true, 0.8f);
        while (motors.Sorter.isBusy()) {
            sleep(250);
        }

//        sleep(2500);//wait state to wait for launcher to spin up
        motors.Flipper.setDirection(Servo.Direction.FORWARD);//runs servo forward, shooting 1st ball
        motors.Flipper.setPosition(0.0);
        sleep(1000);//runs flipper servo into motor
        motors.Flipper.setDirection(Servo.Direction.REVERSE);
        motors.Flipper.setPosition(0.15);
        sleep(2000);

        //2nd ball code
        sleep(750);//waits to spin turntable
        sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 2);
        while (motors.Sorter.isBusy()) {
            sleep(250);
        }
//        sleep(1500);//waits while turntable spins
        motors.Flipper.setDirection(Servo.Direction.FORWARD);//runs servo forward, shooting 2nd ball
        motors.Flipper.setPosition(0.0);
        sleep(1000);
        motors.Flipper.setDirection(Servo.Direction.REVERSE);
        motors.Flipper.setPosition(0.15);//brings servo back up to avoid turn table collision
        sleep(2000);

        //3rd ball code
        sleep(1000);
        sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 3);
        while (motors.Sorter.isBusy()) {
            sleep(250);
        }
//        sleep(1000);//turns off ramp
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
        //id 21
    }

    private void auto22() {
        //runs PGP
        sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 2);
        //sets position to ensure the ball is correctly lined up
        launch.setLaunchPower(true, 0.8f);
        while (motors.Sorter.isBusy()) {
            sleep(250);
        }

//        sleep(2500);//wait state to wait for launcher to spin up
        motors.Flipper.setDirection(Servo.Direction.FORWARD);//runs servo forward, shooting 1st ball
        motors.Flipper.setPosition(0.0);
        sleep(1000);//runs flipper servo into motor
        motors.Flipper.setDirection(Servo.Direction.REVERSE);
        motors.Flipper.setPosition(0.15);
        sleep(2000);

        //2nd ball code
        sleep(750);//waits to spin turntable
        sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 1);
        while (motors.Sorter.isBusy()) {
            sleep(250);
        }
//        sleep(1500);//waits while turntable spins
        motors.Flipper.setDirection(Servo.Direction.FORWARD);//runs servo forward, shooting 2nd ball
        motors.Flipper.setPosition(0.0);
        sleep(1000);
        motors.Flipper.setDirection(Servo.Direction.REVERSE);
        motors.Flipper.setPosition(0.15);//brings servo back up to avoid turn table collision
        sleep(2000);

        //3rd ball code
        sleep(1000);
        sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 3);
        while (motors.Sorter.isBusy()) {
            sleep(250);
        }
//        sleep(1000);//turns off ramp
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
    }

    private void auto23() {
        //runs PPG

        sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 2);
        //sets position to ensure the ball is correctly lined up
        launch.setLaunchPower(true, 0.8f);
        while (motors.Sorter.isBusy()) {
            sleep(250);
        }

        sleep(2500);//wait state to wait for launcher to spin up
        motors.Flipper.setDirection(Servo.Direction.FORWARD);//runs servo forward, shooting 1st ball
        motors.Flipper.setPosition(0.0);
        sleep(1000);//runs flipper servo into motor
        motors.Flipper.setDirection(Servo.Direction.REVERSE);
        motors.Flipper.setPosition(0.15);
        sleep(2000);

        //2nd ball code
        sleep(750);//waits to spin turntable
        sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 3);
        while (motors.Sorter.isBusy()) {
            sleep(250);
        }
//        sleep(1500);//waits while turntable spins
        motors.Flipper.setDirection(Servo.Direction.FORWARD);//runs servo forward, shooting 2nd ball
        motors.Flipper.setPosition(0.0);
        sleep(1000);
        motors.Flipper.setDirection(Servo.Direction.REVERSE);
        motors.Flipper.setPosition(0.15);//brings servo back up to avoid turn table collision
        sleep(2000);

        //3rd ball code
        sleep(1000);
        sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 1);
        while (motors.Sorter.isBusy()) {
            sleep(250);
        }
//        sleep(1000);//turns off ramp
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

    }


    private void auto24() {
        //runs default (PPG)
        sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 2);
        //sets position to ensure the ball is correctly lined up
        launch.setLaunchPower(true, 0.8f);
        while (motors.Sorter.isBusy()) {
            sleep(250);
        }

//        sleep(2500);//wait state to wait for launcher to spin up
        motors.Flipper.setDirection(Servo.Direction.FORWARD);//runs servo forward, shooting 1st ball
        motors.Flipper.setPosition(0.0);
        sleep(1000);//runs flipper servo into motor
        motors.Flipper.setDirection(Servo.Direction.REVERSE);
        motors.Flipper.setPosition(0.15);
        sleep(2000);

        //2nd ball code
        sleep(750);//waits to spin turntable
        sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 3);
        while (motors.Sorter.isBusy()) {
            sleep(250);
        }
//        sleep(1500);//waits while turntable spins
        motors.Flipper.setDirection(Servo.Direction.FORWARD);//runs servo forward, shooting 2nd ball
        motors.Flipper.setPosition(0.0);
        sleep(1000);
        motors.Flipper.setDirection(Servo.Direction.REVERSE);
        motors.Flipper.setPosition(0.15);//brings servo back up to avoid turn table collision
        sleep(2000);

        //3rd ball code
        sleep(1000);
        sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 1);
        while (motors.Sorter.isBusy()) {
            sleep(250);
        }
//        sleep(1000);//turns off ramp
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
    }


}


