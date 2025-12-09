package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.robotcore.hardware.HardwareMap;


public class SorterControls {
    //TODO: Update values to the real positions of the motor
    public double intakePos1 = 1;
    public double intakePos2 = 2;
    public double intakePos3 = 3;
    
    //TODO: Update values to the real positions of the motor
    public double LaunchPos1 = 1;
    public double LaunchPos2 = 2;
    public double LaunchPos3 = 3;
    private MotorConstructor motors;


    public SorterControls(HardwareMap hardwareMap) {
        this.motors = new MotorConstructor(hardwareMap);
    }

    public enum intakePos {
        pos1,
        pos2,
        pos3
    }

    public enum launchPos {
        po1,
        pos2,
        pos3
    }


}
