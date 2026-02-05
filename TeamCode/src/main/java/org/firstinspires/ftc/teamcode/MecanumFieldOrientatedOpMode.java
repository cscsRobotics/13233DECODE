package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "FieldOrientedTest", group = "Drive")

public class MecanumFieldOrientatedOpMode extends OpMode {
    FieldOrientedDrive drive = new FieldOrientedDrive();
    double forward, strafe, rotate;

    @Override
    public void init() {
        drive.init(hardwareMap);
    }

    @Override
    public void loop() {
        forward = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        drive.driveFieldRelative(forward, strafe, rotate);

        telemetry.addData("forward", -forward);
        telemetry.addLine();
        telemetry.addData("strafe", strafe);
        telemetry.addLine();
        telemetry.addData("rotate", rotate);
        telemetry.update();
    }
}
