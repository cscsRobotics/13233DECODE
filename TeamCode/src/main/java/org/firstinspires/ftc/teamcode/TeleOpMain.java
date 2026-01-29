// Import libraries
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Utils_13233.DriveControls;
import org.firstinspires.ftc.teamcode.Utils_13233.IntakeControls;
import org.firstinspires.ftc.teamcode.Utils_13233.MotorConstructor;
import org.firstinspires.ftc.teamcode.Utils_13233.LaunchControls;
import org.firstinspires.ftc.teamcode.Utils_13233.SorterControls;

/*
    Current Controls:
    Gamepad1:
        Left  Stick Y: Forward backward movement
        Left  Stick X: Left right strafe movement
        Right Stick X: Turn the entier robot left and right

        A:   Move the flipper to push the artifact
        B:   Set the drive motors to brake
        X:   Set launch wheels to 0.7 power
        Left Bumper: Set launch wheels to 1.0 power

    Gamepad2:
        D-Pad:
            Left:  Move sorter to launch position 1
            Up:    Move sorter to launch position 2
            Right: Move sorter to launch position 3

        X: Move sorter to intake position 1
        Y: Move sorter to intake position 2
        B: Move sorter to intake position 3

 */


@TeleOp(name = "TeleOpMain", group = "Drive")
public class TeleOpMain extends LinearOpMode {

    // Constructors for the utils classes
    private DriveControls drive;
    private LaunchControls launch;
    private SorterControls sorter;
    private MotorConstructor motors;
    private IntakeControls intake;


    //This function is executed when this Op Mode is selected from the Driver Station
    @Override
    public void runOpMode() {
        // Create the utils classes
        drive = new DriveControls(hardwareMap);
        launch = new LaunchControls(hardwareMap);
        motors = new MotorConstructor(hardwareMap);
        sorter = new SorterControls(motors);
        intake = new IntakeControls(hardwareMap);


        sorter.currentSorterPosition = SorterControls.sorterPositions.LAUNCH_POS_1;


        // Wait for the game to start (driver presses PLAY)
        // The waitForStart() function will wait for the start button to begin
        // DON'T WRITE ANY CODE AFTER THE WAIT FOR START UNTIL THE "while (opModIsActive())"
        // THIS WILL CAUSE PROBLEMS WHEN GOING THROUGH INSPECTION
        waitForStart();
        // Run code while op mode is active
        while (opModeIsActive()) {
            // Add status data to driver hub display
            telemetry.addData("Status", "opModeIsActive");
            telemetry.addLine();
            telemetry.addData("Sorter ", "Stats");
            telemetry.addData("Target", motors.Sorter.getTargetPosition());
            telemetry.addData("Current", motors.Sorter.getCurrentPosition());
            telemetry.addData("Busy", motors.Sorter.isBusy());
            telemetry.addData("Current Position", sorter.currentSorterPosition.toString());
            telemetry.addLine();
            telemetry.addData("Current Sorter Positions", ":");
            telemetry.addData("Sorter Slot 1", sorter.currentSorterStates[0].toString());
            telemetry.addData("Sorter Slot 2", sorter.currentSorterStates[1].toString());
            telemetry.addData("Sorter Slot 3", sorter.currentSorterStates[2].toString());
            telemetry.update();

            // Set the power to the launch motors based while the x button is being pressed
            // and rumble to let the driver know that the launch motors are being controlled
            if (gamepad1.x) {
                launch.setLaunchPower(true, 0.7f);
            } else if (gamepad1.y) {
                launch.setLaunchPower(true);
            } else {
                launch.setLaunchPower(false);
            }

            sleep(50);

            // Allows for driver Control of the sorter
            // Intake positions for the sorter
            sorter.simpleSorterPosition(gamepad2.x, gamepad2.y, gamepad2.b,
                SorterControls.sorterModes.INTAKE);

            // Launch positons for the sorter
            sorter.simpleSorterPosition(gamepad2.dpad_left, gamepad2.dpad_up, gamepad2.dpad_right,
                SorterControls.sorterModes.LAUNCH);

            // Scans the color of the current ball in the sorter if the sorter is in the intake
            // positon
            if (gamepad2.a && !motors.Sorter.isBusy() && sorter.isCurrentSorterIntake()) {
                sorter.scanCurrentBall();
            }

            // Control the flipper and make sure that when the sorter is moving the flipper is set
            // to the not active positon
            if (gamepad1.a && !motors.Sorter.isBusy()) {
                motors.Flipper.setDirection(Servo.Direction.FORWARD);
                motors.Flipper.setPosition(0.0);
            } else {
                motors.Flipper.setDirection(Servo.Direction.REVERSE);
                motors.Flipper.setPosition(0.15);
            }

            if (gamepad2.back) {
                sorter.moveGreenToLaunchPos();
            }

            if (gamepad2.start) {
                sorter.moveToPurpleLaunchPos();
            }


            intake.setIntakeDirection(gamepad1.left_bumper, gamepad1.right_bumper);
            //Add option to enable brakes when sharbell holds a
            drive.setDriveMotorZeroPowerBehavior(gamepad1.b);

            // Sets the power to the drive motors based on current gamepad inputs
            drive.setDrivePower(gamepad1.left_stick_y, gamepad1.left_stick_x,
                gamepad1.right_stick_x, gamepad1.left_trigger);

            // Slow down the loop because for some reason it goes to fast and causes the motors to
            // not work properly
            sleep(50);
        }
        // Do not place any drive code here outside of the while loop (will fail inspection)
    }
}
