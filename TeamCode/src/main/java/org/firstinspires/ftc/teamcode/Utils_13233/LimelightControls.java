package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class LimelightControls {
    private final MotorConstructor motors;

    public LimelightControls(HardwareMap hardwareMap) {
        this.motors = new MotorConstructor(hardwareMap);
    }

    public int getTagID() {
        motors.limelight.pipelineSwitch(0);
        motors.limelight.start();

        LLResult result = motors.limelight.getLatestResult();

        if (result != null && result.isValid()) {
            for (LLResultTypes.FiducialResult tag : result.getFiducialResults()) {
                return (tag.getFiducialId());
            }
        }
        return 0;
    }
}
