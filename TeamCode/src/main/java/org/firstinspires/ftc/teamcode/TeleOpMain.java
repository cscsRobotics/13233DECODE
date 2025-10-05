package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "TeleOpMain", group = "Drive")
public class TeleOpMain extends LinearOpMode {
    // Drive Wheels
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    //Intake motor
    private DcMotor intake;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {

        //****************************************//
        // Map all robot hardware				  //
        //****************************************//

        //Main Drive Motors
        leftFront = hardwareMap.dcMotor.get("leftFront"); //Front left drive motor
        rightFront = hardwareMap.dcMotor.get("rightFront"); //Front right drive motor
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightBack = hardwareMap.dcMotor.get("rightBack");

        //Intake Motor
        intake = hardwareMap.dcMotor.get("intake");


        //***************************************************//
        // Set direction of the main drive motors			 //
        //***************************************************//
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        //Set the direction of the intake motor
        intake.setDirection(DcMotorSimple.Direction.REVERSE);


        //Set the zero power behavior of the main drive motors to FLOAT
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //Set the zero power behavior of the intake to FLOAT
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //The waitForStart() function will wait for the start button will begin
        //DON'T WRITE ANY CODE AFTER THE WAIT FOR START UNTIL THE "while (opModIsActive())"
        //THIS WILL CAUSE PROBLEMS WHEN GOING THROUGH INSPECTION
        waitForStart();

        //******************************************//
        // Run code while op mode is active			//
        //******************************************//
        while (opModeIsActive()) {
            //**************************************************************//
            //Telemetry Code the stuff that appears on the right side of the//
            //driver hub													//
            //**************************************************************//
            telemetry.addData("Status", "opModeIsActive");
            telemetry.update();

            //regular drive controls
            allDrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            //Intake Control
            intake(gamepad2.left_bumper, gamepad2.right_bumper);

        }
        //NO DRIVE CODE OUT SIDE OF THE OPMODEACTIVE LOOP WILL CAUSE PROBLEMS IN INSPECTION
    }

    /**
     * Updates all the drive controls based on the current gamepad stick positions
     *
     * @param controlLeftStickY Left stick Y setting, up/down
     * @param controlLeftStickX Left stick X setting, left/right
     * @param controlRightStick Right click setting, controls left right turn
     */
    void allDrive(float controlLeftStickY, float controlLeftStickX, float controlRightStick) {
        rightFront.setPower(controlLeftStickY + controlLeftStickX + controlRightStick);
        leftFront.setPower(controlLeftStickY - controlLeftStickX - controlRightStick);
        rightBack.setPower(controlLeftStickY - controlLeftStickX + controlRightStick);
        leftBack.setPower(controlLeftStickY + controlLeftStickX - controlRightStick);
    }

    /**
     * Controls the spin direction of the intake wheel based on controller buttons
     *
     * @param intakeForwardInput Button mapped to forward input
     * @param intakeReverseInput Button mapped to reverse input
     */
    void intake(boolean intakeForwardInput, boolean intakeReverseInput) {
        double power = 0;

        // ^ is the XOR operator, will return true if only one variable is true
        // If intakeForwardInput OR intakeReverseInput is true then this is true, but not
        // if both are true, which is why this differs from the || logical or operator
        if (intakeForwardInput ^ intakeReverseInput) {
            // This uses an inline-if statement which is useful when assigning values to variables
            // This says set power = 1 if intakeForwardInput is true, else set it to -1
            power = intakeForwardInput ? 1 : -1;
        }

        intake.setPower(power);
    }
}
