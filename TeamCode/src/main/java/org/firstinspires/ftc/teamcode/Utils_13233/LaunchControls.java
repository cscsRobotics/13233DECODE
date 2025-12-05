package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class LaunchControls {
    private final MotorConstructor motors;

    public LaunchControls(HardwareMap hardwareMap) {
        this.motors = new MotorConstructor(hardwareMap);
    }

    /**
     * Sets the power of all of the launch motors depending on the value of launchInput
     *
     * @param launchInput Button mapped to launch input
     */
    public void setLaunchPower(boolean launchInput) {
        double power = launchInput ? 0.7f : 0.0f;

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

}
