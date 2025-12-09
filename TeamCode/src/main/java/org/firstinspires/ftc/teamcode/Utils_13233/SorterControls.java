package org.firstinspires.ftc.teamcode.Utils_13233;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class SorterControls {
    //TODO: Update values to the real positions of the motor
    public int intakePos1 = 1;
    public int intakePos2 = 2;
    public int intakePos3 = 3;

    //TODO: Update values to the real positions of the motor
    public int LaunchPos1 = 1;
    public int LaunchPos2 = 2;
    public int LaunchPos3 = 3;
    private MotorConstructor motors;


    public SorterControls(HardwareMap hardwareMap) {
        this.motors = new MotorConstructor(hardwareMap);
    }

    /**
     * Move the sorter to a position to intake from
     *
     * @param pos Enum to determine what positon to move to
     */
    void moveToIntakePos(intakePos pos) {
        switch (pos) {
            case INTAKE_POS_1:
                motors.Sorter.setTargetPosition(intakePos1);
                motors.Sorter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors.Sorter.setPower(1);
                break;
            case INTAKE_POS_2:
                motors.Sorter.setTargetPosition(intakePos2);
                motors.Sorter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors.Sorter.setPower(1);
                break;
            case INTAKE_POS_3:
                motors.Sorter.setTargetPosition(intakePos3);
                motors.Sorter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors.Sorter.setPower(1);
                break;
        }
    }


    /**
     * Move the sorter to a position to launch from
     *
     * @param pos Enum to determine what positon to move to
     */
    void moveToLaunchPos(launchPos pos) {
        switch (pos) {
            case LAUNCH_POS_1:
                motors.Sorter.setTargetPosition(LaunchPos1);
                motors.Sorter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors.Sorter.setPower(1);
                break;
            case LAUNCH_POS_2:
                motors.Sorter.setTargetPosition(LaunchPos2);
                motors.Sorter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors.Sorter.setPower(1);
                break;
            case LAUNCH_POS_3:
                motors.Sorter.setTargetPosition(LaunchPos3);
                motors.Sorter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors.Sorter.setPower(1);
                break;
        }
    }

    public void moveSorterToPos(sorterModes mode, int pos) {
        if (mode == sorterModes.INTAKE) {
            switch (pos) {
                case 1:
                    moveToIntakePos(intakePos.INTAKE_POS_1);
                    break;
                case 2:
                    moveToIntakePos(intakePos.INTAKE_POS_2);
                    break;
                case 3:
                    moveToIntakePos(intakePos.INTAKE_POS_3);
                    break;
            }
        } else if (mode == sorterModes.LAUNCH) {
            switch (pos) {
                case 1:
                    moveToLaunchPos(launchPos.LAUNCH_POS_1);
                    break;
                case 2:
                    moveToLaunchPos(launchPos.LAUNCH_POS_2);
                    break;
                case 3:
                    moveToLaunchPos(launchPos.LAUNCH_POS_3);
                    break;
            }
        }
    }

    public enum intakePos {
        INTAKE_POS_1,
        INTAKE_POS_2,
        INTAKE_POS_3
    }

    public enum launchPos {
        LAUNCH_POS_1,
        LAUNCH_POS_2,
        LAUNCH_POS_3
    }

    public enum sorterModes {
        INTAKE,
        LAUNCH
    }


}
