package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class SorterControls {
    private final MotorConstructor motors;
    //TODO: Update values to the real positions of the motor
    public int intakePos1 = 263;
    public int intakePos2 = -87;
    public int intakePos3 = 84;
    //TODO: Update values to the real positions of the motor
    public int LaunchPos1 = 0;
    public int LaunchPos2 = 180;
    public int LaunchPos3 = 356;

    public SorterControls(MotorConstructor motors) {
        this.motors = motors;
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
                motors.Sorter.setPower(0.6);
                break;
            case INTAKE_POS_2:
                motors.Sorter.setTargetPosition(intakePos2);
                motors.Sorter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors.Sorter.setPower(0.6);
                break;
            case INTAKE_POS_3:
                motors.Sorter.setTargetPosition(intakePos3);
                motors.Sorter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors.Sorter.setPower(0.6);
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
                motors.Sorter.setPower(0.6);
                break;
            case LAUNCH_POS_2:
                motors.Sorter.setTargetPosition(LaunchPos2);
                motors.Sorter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors.Sorter.setPower(0.6);
                break;
            case LAUNCH_POS_3:
                motors.Sorter.setTargetPosition(LaunchPos3);
                motors.Sorter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors.Sorter.setPower(0.6);
                break;
        }
    }


    /**
     * Moves the sorter to a position defined in mode and pos allows for both intake and launching
     *
     * @param mode Enum to determine weather to launch or intake
     * @param pos  Enum to determine what sorter position to move to can be 1, 2 or 3
     * @throws RuntimeException Throws a runtime error if pos is not 1, 2 or 3
     */
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
                default:
                    // throw error when pos is not 1, 2 or 3
                    throw new RuntimeException("Not a valid position must be 1, 2, or 3, did you" +
                            "say run it?");
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
                default:
                    // throw error when pos is not 1, 2 or 3
                    throw new RuntimeException("Not a valid position must be 1, 2, or 3, did you" +
                            "say run it?");
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
