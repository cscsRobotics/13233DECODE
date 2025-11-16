package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 * Class the holds common functions and variable for the 13233 DECODE robot
 */
public class CommonControls{
    private final MotorConstructor motors;

    public CommonControls(HardwareMap hardwareMap) {
        this.motors = new MotorConstructor(hardwareMap);
    }




}
