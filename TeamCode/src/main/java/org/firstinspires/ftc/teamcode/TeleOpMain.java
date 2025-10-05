//Import required library's
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "TeleOpMain", group = "Drive")
public class TeleOpMain extends LinearOpMode {


    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        double wUP = .3;
        double Iin = 1;
        double Fly = 1;

        cyberknights cyberknights = new cyberknights(hardwareMap);


        //****************************************//
        // Map all robot hardware				  //
        //****************************************//





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
            cyberknights.allDrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            //Intake Control
            cyberknights.intake(gamepad2.left_bumper, gamepad2.right_bumper);

        }
        //NO DRIVE CODE OUT SIDE OF THE OPMODEACTIVE LOOP WILL CAUSE PROBLEMS IN INSPECTION
    }

    //Function allDrive()
    //Usage: to provide Movement controls to the driver of the robot


    //Function: intake()
    //Usage: to provide intake controls to the driver of the robot

}