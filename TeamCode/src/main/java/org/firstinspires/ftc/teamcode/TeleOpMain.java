// Import libraries
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utils_13233.DriveControls;
import org.firstinspires.ftc.teamcode.Utils_13233.MotorConstructor;
import org.firstinspires.ftc.teamcode.Utils_13233.RampControls;
import org.firstinspires.ftc.teamcode.Utils_13233.LaunchControls;


@TeleOp(name = "TeleOpMain", group = "Drive")
public class TeleOpMain extends LinearOpMode {

    // Constructors for the utils classes
    private final DriveControls drive;
    private final LaunchControls launch;
    private final RampControls ramp;

    public TeleOpMain(HardwareMap hardwareMap) {
        this.drive = new DriveControls(hardwareMap);
        this.launch = new LaunchControls(hardwareMap);
        this.ramp = new RampControls(hardwareMap);
    }


    //This function is executed when this Op Mode is selected from the Driver Station
    @Override
    public void runOpMode() {
        // Initialize the CommonControls class

        // The waitForStart() function will wait for the start button to begin
        // DON'T WRITE ANY CODE AFTER THE WAIT FOR START UNTIL THE "while (opModIsActive())"
        // THIS WILL CAUSE PROBLEMS WHEN GOING THROUGH INSPECTION
        waitForStart();
        // Run code while op mode is active
        while (opModeIsActive()) {
            // Add status data to driver hub display
            telemetry.addData("Status", "opModeIsActive");
            telemetry.update();

            // Set the power to the launch motors based while the x button is being pressed
            launch.setLaunchPower(gamepad1.x);

            //Add option to enable brakes when sharbell holds a
            drive.setDriveMotorZeroPowerBehavior(gamepad1.a);
            //Add option to enable brakes when driver 1 holds the "a" button
            drive.setDriveMotorZeroPowerBehavior(gamepad1.a);

            // Set all of the intake motors and servos to go forward when dpad up is pressed
            // and reverse when dpad down is pressed
            ramp.setIntakeDirection(gamepad2.dpad_up, gamepad2.dpad_down);
            // Wait for motors to speed up before changing value
            // While it is bad practice to put a sleep in a loop it is the only way to (that I know)
            // to make the intake not jitter I also


            // Sets the power to the drive motors based on current gamepad inputs
            drive.setDrivePower(gamepad1.left_stick_y, gamepad1.left_stick_x,
                gamepad1.right_stick_x, gamepad1.left_trigger);
            sleep(50);
        }
        // Do not place any drive code here outside of the while loop (will fail inspection)
    }
}
