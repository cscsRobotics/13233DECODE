// Import libraries
package org.firstinspires.ftc.teamcode;

import static com.qualcomm.robotcore.hardware.Gamepad.RUMBLE_DURATION_CONTINUOUS;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Utils_13233.DriveControls;
import org.firstinspires.ftc.teamcode.Utils_13233.MotorConstructor;
import org.firstinspires.ftc.teamcode.Utils_13233.LaunchControls;
import org.firstinspires.ftc.teamcode.Utils_13233.SorterControls;


@TeleOp(name = "TeleOpMain", group = "Drive")
public class TeleOpMain extends LinearOpMode {

    // Constructors for the utils classes
    private DriveControls drive;
    private LaunchControls launch;
    private SorterControls sorter;
    private MotorConstructor motors;


    //This function is executed when this Op Mode is selected from the Driver Station
    @Override
    public void runOpMode() {
        // Create the utils classes
        drive = new DriveControls(hardwareMap);
        launch = new LaunchControls(hardwareMap);
        motors = new MotorConstructor(hardwareMap);
        sorter = new SorterControls(motors);

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
            telemetry.addData("Sorter ", "Stats");
            telemetry.addData("Target", motors.Sorter.getTargetPosition());
            telemetry.addData("Current", motors.Sorter.getCurrentPosition());
            telemetry.addData("Busy", motors.Sorter.isBusy());
            telemetry.addData("Current Position", sorter.currentSorterPosition.toString());
            telemetry.addData("", "");
            telemetry.addData("Current Sorter Positions", ":");
            telemetry.addData("Sorter Slot 1", sorter.currentSorterStates[0].toString());
            telemetry.addData("Sorter Slot 2", sorter.currentSorterStates[1].toString());
            telemetry.addData("Sorter Slot 3", sorter.currentSorterStates[2].toString());
            telemetry.update();

            // Set the power to the launch motors based while the x button is being pressed
            // and rumble to let the driver know that the launch motors are being controlled
            // launch.setLaunchPower(gamepad1.x, true, gamepad1);
            if (gamepad1.x) {
                launch.setLaunchPower(gamepad1.x, 0.7f);
            } else if (gamepad1.left_bumper) {
                launch.setLaunchPower(gamepad1.left_bumper, 1.0f);
            } else {
                launch.setLaunchPower(false);
            }

//            sleep(100);

            // Sorter Controls
            // Intake positions
            if (gamepad2.x) {
                sorter.moveSorterToPos(SorterControls.sorterModes.INTAKE, 1);
            } else if (gamepad2.y) {
                sorter.moveSorterToPos(SorterControls.sorterModes.INTAKE, 2);
            } else if (gamepad2.b) {
                sorter.moveSorterToPos(SorterControls.sorterModes.INTAKE, 3);
            }

            // Launch positons
            if (gamepad2.dpad_left) {
                sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 1);
            } else if (gamepad2.dpad_up) {
                sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 2);
            } else if (gamepad2.dpad_right) {
                sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 3);
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

            if (gamepad1.left_bumper) {
                sorter.moveGreenToLaunchPos();
            }

            //Add option to enable brakes when sharbell holds a
            drive.setDriveMotorZeroPowerBehavior(gamepad1.a);          //Add option to enable brakes when driver 1 holds the "a" button
            drive.setDriveMotorZeroPowerBehavior(gamepad1.a);

            // Wait for motors to speed up before changing value
            // While it is bad practice to put a sleep in a loop it is the only way to (that I know)
            // to make the intake not jitter I also


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
