//Import required librarys
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "TeleOpMain", group = "Drive")
public class TeleOpMain extends LinearOpMode {

    double speed = 1.5;
    double stdSpeedMulti = 1.5;
    double slowSpeedMulti = 0.5;

    //Drive Wheels
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

  /*
  Where the motors are plugged into
  Control Hub:
  BRMoto - Motor Port 0
  BLMoto - Motor Port 1
  leftFront - Motor Port 2
  FRMoto - Motor Port 3
*/

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        double wUP = .3;
        double Iin = 1;
        double Fly = 1;

        //****************************************//
        // Map all robot hardware				  //
        //****************************************//

        //Main Drive Motors
        //****************************************//
        //BR means "back right"				   	  //
        //BL means "back left"					  //
        //FR means "front right"				  //
        //FL means "front left"					  //
        //****************************************//
        leftFront = hardwareMap.dcMotor.get("leftFront"); //Front left drive motor
        rightFront = hardwareMap.dcMotor.get("rightFront"); //Front right drive motor
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightBack = hardwareMap.dcMotor.get("rightBack");


        double contPower = 0.0;

        //*****************************************//
        // Put initialization blocks here.			 //
        //*****************************************//

        //***************************************************//
        // Set direction of the main drive motors				   //
        //***************************************************//
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);


        //Set the zero power behavor of the main drive motors to FLOAT
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


        // You can give the sensor a gain value, will be multiplied by the sensor's raw value before the
        // normalized color values are calculated. Color sensors (especially the REV Color Sensor V3)
        // can give very low values (depending on the lighting conditions), which only use a small part
        // of the 0-1 range that is available for the red, green, and blue values. In brighter conditions,
        // you should use a smaller gain than in dark conditions. If your gain is too high, all of the
        // colors will report at or near 1, and you won't be able to determine what color you are
        // actually looking at. For this reason, it's better to err on the side of a lower gain
        // (but always greater than  or equal to 1).
        float gain = 2;

        // If possible, turn the light on in the beginning (it might already be on anyway,
        // we just make sure it is if we can).

        //The waitForStart() function will wait for the start button will begin
        //DONT WRITE ANY CODE AFTER THE WAIT FOR START UNTIL THE "while (opModIsActive())"
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

            //Gamepad1

            //slow mode
            //changes the speed variable to 0.5 as long as the the a button is pressed
            if (gamepad1.right_bumper) {
                speed = slowSpeedMulti; //change to slow mode speed
            } else {
                speed = stdSpeedMulti; //change speed back to normal speed
            }

            //regular drive controls
            //all to be multiplied by the speed modifier
            rightFront.setPower(gamepad1.right_stick_y * 1.5);
            leftFront.setPower(gamepad1.left_stick_y * 1.5);
            rightBack.setPower(gamepad1.right_stick_y * 1.5);
            leftBack.setPower(gamepad1.left_stick_y * 1.5);

            //Strafe Right using gamepad1 right_trigger
            rightFront.setPower(-gamepad1.right_trigger * 1.5);
            leftFront.setPower(-gamepad1.right_trigger * 1.5);
            rightBack.setPower(gamepad1.right_trigger * 1.5);
            leftBack.setPower(gamepad1.right_trigger * 1.5);

            // Strafe Left using gamepad1 left_trigger
            rightFront.setPower(gamepad1.left_trigger * 1.5);
            leftFront.setPower(gamepad1.left_trigger * 1.5);
            rightBack.setPower(-gamepad1.left_trigger * 1.5);
            leftBack.setPower(-gamepad1.left_trigger * 1.5);
        }
        //NO DRIVE CODE OUT SIDE OF THE OPMODEACTIVE LOOP WILL CAUSE PROBLEMS IN INSPECTION

    }
    void allDrive(float controlStick){
        rightFront.setPower(controlStick * 1.5);
        leftFront.setPower(controlStick * 1.5);
        rightBack.setPower(controlStick * 1.5);
        leftBack.setPower(controlStick * 1.5);
    }
    protected enum DisplayKind {
        MANUAL,
        AUTO,
    }
}
