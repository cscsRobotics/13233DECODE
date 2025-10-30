//Import required librarys
package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;

@TeleOp(name = "TeleOpTest", group = "Drive")
public class TeleOpTest extends LinearOpMode {

    double voltage = 0;

    //launcer
    private DcMotor Launcher;
    private DcMotor Launcher2;

    private CRServo rampServo1;
    private CRServo rampServo2;
    private CRServo launcherWheel;
    private DcMotor launcherWheelM;


    //battery Constant
    double batteryConst = 13.5;

    long debounceInterval = 100;


  /*
  Where the motors are plugged into
  Control Hub:
  rightBack - Motor Port 0
  leftBack - Motor Port 1
  leftFront - Motor Port 2
  rightFront - Motor Port 3

  Expansion Hub:
  string - Motor Port 1
  arm - Motor Port 2
  wrist - Servo Port 1
  claw - Servo Port 0
  */

    //BlinkinLEDs
    //private RevBlinkinLedDriver blinkinLedDriver;
    //private RevBlinkinLedDriver.BlinkinPattern BasePattern = RevBlinkinLedDriver.BlinkinPattern.BLUE;
    //private RevBlinkinLedDriver.BlinkinPattern StopPattern = RevBlinkinLedDriver.BlinkinPattern.RED;

    //Power Constant
    private static double powerConstant = .51;

    protected enum DisplayKind {
        MANUAL,
        AUTO,
    }

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

        //Launcher Prototype
        Launcher = hardwareMap.dcMotor.get("Launcher");
        Launcher2 = hardwareMap.dcMotor.get("Launcher2");
        rampServo1 = hardwareMap.crservo.get("rampServo1");
        rampServo2 = hardwareMap.crservo.get("rampServo2");
        launcherWheel = hardwareMap.crservo.get("launcherWheel");
        launcherWheelM = hardwareMap.dcMotor.get("launcherWheelM");


        //BlinkinLEDs
        //blinkinLedDriver = hardwareMap.get(RevBlinkinLedDriver.class, "blinkinLed");
        //blinkinLedDriver.setPattern(BasePattern);

        // Once per loop, we will update this hsvValues array. The first element (0) will contain the
        // hue, the second element (1) will contain the saturation, and the third element (2) will
        // contain the value. See http://web.archive.org/web/20190311170843/https://infohost.nmt.edu/tcc/help/pubs/colortheory/web/hsv.html
        // for an explanation of HSV color.

        float hsvValues[] = { 0F, 0F, 0F };

        final float values[] = hsvValues;

        double contPower = 0.0;

        //*****************************************//
        // Put initialization blocks here.			 //
        //*****************************************//

        //***************************************************//
        // Set direction of the main drive motors				   //
        //***************************************************//
        //set Launcher direction
        Launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);

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
        CommonControls CommonControls = new CommonControls(hardwareMap);
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


            //Gamepad2
            if(gamepad1.x){
                Launcher.setPower(-1.0);
                Launcher2.setPower(1.0);
                launcherWheel.setPower(-1);
                launcherWheelM.setPower(1.0);
            } else{
                Launcher.setPower(0.0);
                Launcher2.setPower(0.0);
                launcherWheel.setPower(0);
                launcherWheelM.setPower(0.0);
            }


            rampServo1.setPower(-CommonControls.setIntakePower(gamepad1.dpad_up,
                gamepad1.dpad_down));
            rampServo2.setPower(CommonControls.setIntakePower(gamepad1.dpad_up,
                gamepad1.dpad_down));
            launcherWheelM.setPower(CommonControls.setIntakePower(gamepad1.dpad_up,
                gamepad1.dpad_down) * 0.5);
            CommonControls.intake.setPower(CommonControls.setIntakePower(gamepad1.dpad_up,
                gamepad1.dpad_down));
            //Wait for motors to speed up before changing value
            sleep(50);


            // Sets the power to the drive motors based on current gamepad inputs
            CommonControls.setDrivePower(gamepad1.left_stick_y, gamepad1.left_stick_x,
                gamepad1.right_stick_x, gamepad1.left_trigger);
        }


    }

}
  /*
  void motorToPosition(namespace selectMotor, float setMotorPower, float motorTargetPositon){
	  selectMotor.setTargetPosition(motorTargetPositon);
	  selectMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
	  selectMotor.setPower(setMotorPower);
	}
	*/
//}
