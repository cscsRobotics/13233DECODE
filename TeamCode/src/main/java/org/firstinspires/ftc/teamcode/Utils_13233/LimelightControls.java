package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class LimelightControls {
    private final MotorConstructor motors;
    private final int aprilTagPipeline = 0;

    /**
     * Constructor for the LimelightControls class
     *
     * @param hardwareMap Hardware map for the robot
     */
    public LimelightControls(HardwareMap hardwareMap) {
        this.motors = new MotorConstructor(hardwareMap);
    }

    /**
     * Get the ID of the tag that is being seen by the limelight
     *
     * @return Returns the ID of the tag that is being seen by the limelight
     */
    public int getTagID() {
        motors.limelight.pipelineSwitch(aprilTagPipeline);
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
