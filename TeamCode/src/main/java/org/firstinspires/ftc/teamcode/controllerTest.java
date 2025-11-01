package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;

@TeleOp(name = "controllerTest", group = "Auto")
public class controllerTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        waitForStart();
        while (opModeIsActive()) {
            //Print the value of gamepad1's joysticks
            telemetry.addData("Controller 1 left stick x: ", gamepad1.left_stick_x);
            telemetry.addData("Controller 1 left stick y: ", gamepad1.left_stick_y);
            telemetry.addData("Controller 1 right stick x: ", gamepad1.right_stick_x);
            telemetry.addData("Controller 1 right stick y: ", gamepad1.right_stick_y);

            //Print the value of gamepad2's joysticks
            telemetry.addData("Controller 2 left stick x: ", gamepad2.left_stick_x);
            telemetry.addData("Controller 2 left stick y: ", gamepad2.left_stick_y);
            telemetry.addData("Controller 2 right stick x: ", gamepad2.right_stick_x);
            telemetry.addData("Controller 2 right stick y: ", gamepad2.right_stick_y);

            // Update the values displayed
            telemetry.update();
        }
    }
}
