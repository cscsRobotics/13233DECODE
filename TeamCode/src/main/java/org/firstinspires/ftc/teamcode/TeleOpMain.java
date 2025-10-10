package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "TeleOpMain", group = "Drive")
public class TeleOpMain extends LinearOpMode {
    /**
     * This function is executed when this Op Mode is selected from the Driver Station
     */
    @Override
    public void runOpMode() {
        CommonControls CommonControls = new CommonControls(hardwareMap);

        // The waitForStart() function will wait for the start button to begin
        // DON'T WRITE ANY CODE AFTER THE WAIT FOR START UNTIL THE "while (opModIsActive())"
        // THIS WILL CAUSE PROBLEMS WHEN GOING THROUGH INSPECTION
        waitForStart();

        // Run code while op mode is active
        while (opModeIsActive()) {
            // Add status data to driver hub display
            telemetry.addData("Status", "opModeIsActive");
            telemetry.update();

            // Sets the power to the drive motors based on current gamepad inputs
            CommonControls.setDrivePower(gamepad1.left_stick_y, gamepad1.left_stick_x,
                gamepad1.right_stick_x, gamepad1.left_trigger);

            // Ball ("artifact"/game element) intake control
            CommonControls.setIntakeDirection(gamepad2.left_bumper, gamepad2.right_bumper);
        }
        // Do not place any drive code here outside of the while loop (will fail inspection)
    }
}
