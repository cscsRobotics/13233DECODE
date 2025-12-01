package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class AutoTurn {
    private final MotorConstructor motors;
    CommonAutoMethods autoMethods;
    AutoConstants autoConst;
    private final LinearOpMode opMode;

    public AutoTurn(LinearOpMode opMode, HardwareMap hardwareMap) {
        this.motors = new MotorConstructor(hardwareMap);
        this.autoMethods = new CommonAutoMethods(opMode, hardwareMap);
        this.autoConst = new AutoConstants();
        this.opMode = opMode;
    }

    /**
     * Rotate left or right the number of degrees. Does not support turning more than 180 degrees.
     *
     * @param degrees Degrees to turn, + is left - is right
     */
    public void rotate(int degrees, double power) {
        double leftPower, rightPower;

        // restart imu movement tracking.
        resetAngle();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).
        if (degrees < 0) {    // turn right.
            leftPower = power;
            rightPower = -power;
        } else if (degrees > 0) {    // turn left.
            leftPower = -power;
            rightPower = power;
        } else return;

        // set power to rotate.
        autoMethods.setDrivePower(leftPower, rightPower, leftPower, rightPower);

        // turn the motors off.
        autoMethods.setDrivePower(0.0, 0.0, 0.0, 0.0);

        // wait for rotation to stop.
        opMode.sleep(500);

        // reset angle tracking on new heading.
        resetAngle();
    }


    public void resetAngle() {
        motors.imu.resetYaw();
        YawPitchRollAngles lastAngles = motors.imu.getRobotYawPitchRollAngles();

        int globalAngle = 0;
    }
}
