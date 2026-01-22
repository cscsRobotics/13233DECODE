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

import java.util.List;

@Autonomous(name = "LimeLightAutoBlue", group = "Auto")

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
            drive.setDrivePower(-1.0f);
            sleep(1600);
            // turns to view april tag tower
            drive.setDrivePower(0.0f, 0.0f, 1.0f,
                0.0f);
            sleep(750);//turns
            drive.setDrivePower(0.0f);

            telemetry.addData("ID", limelightCont.getTagID());
            telemetry.update();

            sleep(1000);
            // turns back viewing goal
            drive.setDrivePower(0.0f, 0.0f, -1.0f,
                0.0f);
            sleep(750);
            drive.setDrivePower(0.0f);

            switch (limelightCont.getTagID()) {
                case 21:
                    telemetry.addLine("AUTO: Green Purple Purple");
                    telemetry.update();
                    //runs GPP
                    sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 1);
                    //sets position to ensure the ball is correctly lined up
                    launch.setLaunchPower(true, 0.9f);

                    sleep(2500);//wait state to wait for launcher to spin up
                    motors.Flipper.setDirection(Servo.Direction.FORWARD);//runs servo forward, shooting 1st ball
                    motors.Flipper.setPosition(0.0);
                    sleep(1000);//runs flipper servo into motor
                    motors.Flipper.setDirection(Servo.Direction.REVERSE);
                    motors.Flipper.setPosition(0.15);
                    sleep(2000);

                    //2nd ball code
                    sleep(750);//waits to spin turntable
                    sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 2);
                    sleep(1500);//waits while turntable spins
                    motors.Flipper.setDirection(Servo.Direction.FORWARD);//runs servo forward, shooting 2nd ball
                    motors.Flipper.setPosition(0.0);
                    sleep(1000);
                    motors.Flipper.setDirection(Servo.Direction.REVERSE);
                    motors.Flipper.setPosition(0.15);//brings servo back up to avoid turn table collision
                    sleep(2000);

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
                    //id 21

                    switch (limelightCont.getTagID()) {
                        case 22:
                            telemetry.addLine("AUTO: Purple Green Purple");
                            telemetry.update();
                            //runs PGP
                            sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 2);
                            //sets position to ensure the ball is correctly lined up
                            launch.setLaunchPower(true, 0.9f);

                            sleep(2500);//wait state to wait for launcher to spin up
                            motors.Flipper.setDirection(Servo.Direction.FORWARD);//runs servo forward, shooting 1st ball
                            motors.Flipper.setPosition(0.0);
                            sleep(1000);//runs flipper servo into motor
                            motors.Flipper.setDirection(Servo.Direction.REVERSE);
                            motors.Flipper.setPosition(0.15);
                            sleep(2000);

                            //2nd ball code
                            sleep(750);//waits to spin turntable
                            sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 1);
                            sleep(1500);//waits while turntable spins
                            motors.Flipper.setDirection(Servo.Direction.FORWARD);//runs servo forward, shooting 2nd ball
                            motors.Flipper.setPosition(0.0);
                            sleep(1000);
                            motors.Flipper.setDirection(Servo.Direction.REVERSE);
                            motors.Flipper.setPosition(0.15);//brings servo back up to avoid turn table collision
                            sleep(2000);

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

                            switch (limelightCont.getTagID()) {
                                case 23:
                                    //runs PPG
                                    telemetry.addLine("AUTO: Purple Purple Green");
                                    telemetry.update();
                                    //runs GPP
                                    sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 2);
                                    //sets position to ensure the ball is correctly lined up
                                    launch.setLaunchPower(true, 0.9f);

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
                                    sleep(1500);//waits while turntable spins
                                    motors.Flipper.setDirection(Servo.Direction.FORWARD);//runs servo forward, shooting 2nd ball
                                    motors.Flipper.setPosition(0.0);
                                    sleep(1000);
                                    motors.Flipper.setDirection(Servo.Direction.REVERSE);
                                    motors.Flipper.setPosition(0.15);//brings servo back up to avoid turn table collision
                                    sleep(2000);

                                    //3rd ball code
                                    sleep(1000);
                                    sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 1);
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


                            }
                    }
            }
        }
    }
}
