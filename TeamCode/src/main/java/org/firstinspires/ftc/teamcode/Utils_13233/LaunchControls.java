package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class LaunchControls {
    private static ElapsedTime rpmTimer = new ElapsedTime();
    private final MotorConstructor motors;
    double launch1Rpm;
    double launch2Rpm;
    double avgLaunchRpm;

    public LaunchControls(HardwareMap hardwareMap) {
        this.motors = new MotorConstructor(hardwareMap);
    }

    /**
     * Sets the power of all of the launch motors depending on the value of launchInput
     *
     * @param launchInput Button mapped to launch input
     */
    public void setLaunchPower(boolean launchInput) {
        double power = launchInput ? 0.62f : 0.0f;

        // Set the the power value to the motors
        motors.Launcher.setPower(-power);
        motors.Launcher2.setPower(power);
    }

    public void setLaunchPower(boolean launchInput, float launchPower) {
        double power = launchInput ? launchPower : 0.0f;

        // Set the the power value to the motors
        motors.Launcher.setPower(-power);
        motors.Launcher2.setPower(power);
    }

    public double launch1Speed() {
        if (rpmTimer.milliseconds() >= 50) {
            launch1Rpm = ((double) motors.Launcher.getCurrentPosition() / 28) * 1200;
            motors.Launcher.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rpmTimer.reset();
        }
        return (launch1Rpm);
    }
}
