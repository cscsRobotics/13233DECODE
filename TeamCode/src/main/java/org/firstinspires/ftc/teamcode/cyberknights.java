package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class cyberknights {


    public DcMotor leftFront;
    public DcMotor rightFront;
    public DcMotor leftBack;
    public DcMotor rightBack;

    //Intake motor
    public DcMotor intake;

    public cyberknights(HardwareMap hardwareMap) {
        // Main Drive Motors
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightBack = hardwareMap.dcMotor.get("rightBack");

        // Intake Motor
        intake = hardwareMap.dcMotor.get("intake");

        // Set direction of the main drive motors
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set the direction of the intake motor
        intake.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set the zero power behavior
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }


    public void allDrive(float controlLeftStickY, float controlLeftStickX, float controlRightStick){
        rightFront.setPower(controlLeftStickY + controlLeftStickX + controlRightStick);
        leftFront.setPower(controlLeftStickY - controlLeftStickX - controlRightStick);
        rightBack.setPower(controlLeftStickY - controlLeftStickX + controlRightStick);
        leftBack.setPower(controlLeftStickY + controlLeftStickX - controlRightStick);
    }

    public void intake(boolean intakeForwardInput, boolean intakeReverseInput){
        if(intakeForwardInput && intakeReverseInput){
            intake.setPower(0);
        }
        //if(gamepad2.left_bumper)
        else if(intakeForwardInput){
            intake.setPower(1);
        }
        else if(intakeReverseInput){
            intake.setPower(-1);
        }
        else{
            intake.setPower(0);
        }
    }
}
