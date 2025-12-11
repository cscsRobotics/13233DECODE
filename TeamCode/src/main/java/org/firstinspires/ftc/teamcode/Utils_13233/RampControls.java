package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class RampControls {
    private final MotorConstructor motors;

    public RampControls(HardwareMap hardwareMap) {
        this.motors = new MotorConstructor(hardwareMap);
    }

    /**
     * Controls the spin direction of the intake wheel based on controller buttons
     *
     * @param intakeForwardInput Button mapped to forward input
     * @param intakeReverseInput Button mapped to reverse input
     */
    public void setIntakeDirection(boolean intakeForwardInput, boolean intakeReverseInput) {
        double intakePower = 0;
        // ^ is the XOR operator, will return true if only one variable is true
        // If intakeForwardInput OR intakeReverseInput is true then this is true, but not
        // if both are true, which is why this differs from the || logical or operator
        if (intakeForwardInput ^ intakeReverseInput) {
            // This uses an inline-if statement which is useful when assigning values to variables
            // This says set power = 1 if intakeForwardInput is true, else set it to -1
            intakePower = intakeForwardInput ? 1 : -1;
        }
        motors.intake.setPower(intakePower);
    }

}
