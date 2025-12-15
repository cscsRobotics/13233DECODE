package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LaunchControls {
    private final MotorConstructor motors;

    public LaunchControls(HardwareMap hardwareMap) {
        this.motors = new MotorConstructor(hardwareMap);
    }

    /**
     * Sets the power of all of the launch motors to power 1.0 if launchInput is true
     *
     * @param launchInput Button mapped to launch input
     */
    public void setLaunchPower(boolean launchInput) {
        double power = launchInput ? 1.0 : 0.0;

        // Set the the power value to the motors
        motors.Launcher.setPower(-power);
        motors.Launcher2.setPower(power);
    }

    /**
     * Sets the power of all of the launch motors to launchPower depending on if launchInput is true
     *
     * @param launchInput Button mapped to launch input
     * @param launchPower Power to set the motors to
     */
    public void setLaunchPower(boolean launchInput, double launchPower) {
        // set launch equal to launchPower if launchInput is true
        double power = launchInput ? launchPower : 0.0;

        // Set the the power value to the motors
        motors.Launcher.setPower(-power);
        motors.Launcher2.setPower(power);
    }

    /**
     * Sets the power of all of the launch motors to power 1.0 if launchInput is true and allows
     * for the controllers to rumble
     *
     * @param launchInput   Button mapped to launch input
     * @param rumble        is rumble active
     * @param rumbleGamepad what gamepad to rumble
     * @throws RuntimeException throws when no gamepad is specified
     */
    public void setLaunchPower(boolean launchInput, boolean rumble, Gamepad rumbleGamepad) {
        // set the launch power to 1.0 if launchInput is true
        double power = launchInput ? 1.0 : 0.0;

        // Set the rumble power of the motors
        if (rumble) {
            rumbleGamepad.rumble(1.0, 1.0, Gamepad.RUMBLE_DURATION_CONTINUOUS);
        } else if (rumbleGamepad == null) {
            throw new RuntimeException("No controller specified for rumble"); // handle errors
        } else {
            rumbleGamepad.rumble(0.0, 0.0, Gamepad.RUMBLE_DURATION_CONTINUOUS);
        }

        // Set the the power value to the motors
        motors.Launcher.setPower(-power);
        motors.Launcher2.setPower(power);
    }


    /**
     * Sets the power of all of the launch motors to power launchPower if launchInput is true and allows
     * for the controllers to rumble
     *
     * @param launchInput   Button mapped to launch input
     * @param launchPower   Power to set the motors to
     * @param rumble        is rumble active
     * @param rumbleGamepad what gamepad to rumble
     * @throws RuntimeException throws when no gamepad is specified
     */
    public void setLaunchPower(boolean launchInput, double launchPower, boolean rumble,
                               Gamepad rumbleGamepad) {
        // set the launch power to launchPower if launchInput is true
        double power = launchInput ? launchPower : 0.0;

        // Set the rumble power of the motors
        if (rumble) {
            rumbleGamepad.rumble(1.0, 1.0, Gamepad.RUMBLE_DURATION_CONTINUOUS);
        } else if (rumbleGamepad == null) {
            throw new RuntimeException("No controller specified for rumble"); // handle errors
        } else {
            rumbleGamepad.rumble(0.0, 0.0, Gamepad.RUMBLE_DURATION_CONTINUOUS);
        }

        // Set the the power value to the motors
        motors.Launcher.setPower(-power);
        motors.Launcher2.setPower(power);
    }

}
