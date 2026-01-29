package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.robotcore.hardware.DcMotor;


public class SorterControls {
    private final MotorConstructor motors;

    // Sorter intake positions
    public int intakePos1 = 263;
    public int intakePos2 = -87;
    public int intakePos3 = 84;

    // Sorter launch positions
    public int LaunchPos1 = 0;
    public int LaunchPos2 = 180;
    public int LaunchPos3 = 356;

    public int greenBallColor[] = {0, 200, 0};
    public int purpleBallColor[] = {150, 0, 150};


    public sorterPositions currentSorterPosition;


    public ballColors[] currentSorterStates = {ballColors.NULL, ballColors.NULL, ballColors.NULL};

    // Motor Speed
    float motorSpeed = 1.0f;

    public SorterControls(MotorConstructor motors) {
        this.motors = motors;
    }

    /**
     * Move the sorter to a position to intake from
     *
     * @param pos Enum to determine what positon to move to
     */
    private void moveToIntakePos(intakePos pos) {
        switch (pos) {
            case INTAKE_POS_1:
                motors.Sorter.setTargetPosition(intakePos1);
                motors.Sorter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors.Sorter.setPower(motorSpeed);
                break;
            case INTAKE_POS_2:
                motors.Sorter.setTargetPosition(intakePos2);
                motors.Sorter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors.Sorter.setPower(motorSpeed);
                break;
            case INTAKE_POS_3:
                motors.Sorter.setTargetPosition(intakePos3);
                motors.Sorter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors.Sorter.setPower(motorSpeed);
                break;
        }
    }


    /**
     * Move the sorter to a position to launch from
     *
     * @param pos Enum to determine what positon to move to
     */
    private void moveToLaunchPos(launchPos pos) {
        switch (pos) {
            case LAUNCH_POS_1:
                motors.Sorter.setTargetPosition(LaunchPos1);
                motors.Sorter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors.Sorter.setPower(motorSpeed);
                break;
            case LAUNCH_POS_2:
                motors.Sorter.setTargetPosition(LaunchPos2);
                motors.Sorter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors.Sorter.setPower(motorSpeed);
                break;
            case LAUNCH_POS_3:
                motors.Sorter.setTargetPosition(LaunchPos3);
                motors.Sorter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motors.Sorter.setPower(motorSpeed);
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
                    currentSorterPosition = sorterPositions.INTAKE_POS_1;
                    break;
                case 2:
                    moveToIntakePos(intakePos.INTAKE_POS_2);
                    currentSorterPosition = sorterPositions.INTAKE_POS_2;
                    break;
                case 3:
                    moveToIntakePos(intakePos.INTAKE_POS_3);
                    currentSorterPosition = sorterPositions.INTAKE_POS_3;
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
                    currentSorterPosition = sorterPositions.LAUNCH_POS_1;
                    break;
                case 2:
                    moveToLaunchPos(launchPos.LAUNCH_POS_2);
                    currentSorterPosition = sorterPositions.LAUNCH_POS_2;
                    break;
                case 3:
                    moveToLaunchPos(launchPos.LAUNCH_POS_3);
                    currentSorterPosition = sorterPositions.LAUNCH_POS_3;
                    break;
                default:
                    // throw error when pos is not 1, 2 or 3
                    throw new RuntimeException("Not a valid position must be 1, 2, or 3, did you" +
                        "say run it?");
            }
        }
    }

    public void simpleSorterPosition(boolean position1Button, boolean position2Button,
                                     boolean position3Button, sorterModes mode) {
        if (position1Button) {
            moveSorterToPos(mode, 1);
        } else if (position2Button) {
            moveSorterToPos(mode, 2);
        } else if (position3Button) {
            moveSorterToPos(mode, 3);
        }
    }

    /**
     * Sorts through the balls in the sorter and moves a green ball to the launch position
     * automatically
     */
    public void moveGreenToLaunchPos() {
        for (int i = 0; i < currentSorterStates.length; i++) {
            if (currentSorterStates[i] == ballColors.GREEN) {
                moveSorterToPos(sorterModes.LAUNCH, i + 1);
                break;
            }
        }
    }

    /**
     * Sorts through the balls in the sorter and moves a purple ball to the launch position
     * automatically
     */
    public void moveToPurpleLaunchPos() {
        for (int i = 0; i < currentSorterStates.length; i++) {
            if (currentSorterStates[i] == ballColors.PURPLE) {
                moveSorterToPos(sorterModes.LAUNCH, i + 1);
                break;
            }
        }
    }


    /**
     * Gets the colors from the color sensor
     *
     * @return Returns an array of colors from the color sensor in the order red, green, blue
     * so index 0 is red, index 1 is green and index 2 is blue
     */
    public int[] getColors() {
        return new int[]{motors.colorSens.red(), motors.colorSens.green(), motors.colorSens.blue()};
    }

    /**
     * Scans the color of the current ball in order to allow for tracking of the location of those
     * balls in the sorter, will do nothing if the manual override has been activated
     *
     * @throws RuntimeException Throws a runtime error if something goes wrong I don't even know how
     *                          you would get to the error its here as a safety net if you get it
     *                          look to the code in depth and consider your life choices
     */
    public void scanCurrentBall() {
        int[] colors = getColors();
        if (colors[1] > greenBallColor[1]) {
            switch (currentSorterPosition) {
                case INTAKE_POS_1:
                    currentSorterStates[0] = ballColors.GREEN;
                    break;
                case INTAKE_POS_2:
                    currentSorterStates[1] = ballColors.GREEN;
                    break;
                case INTAKE_POS_3:
                    currentSorterStates[2] = ballColors.GREEN;
                    break;
                case MANUAL_OVERRIDE:
                    break;
                default:
                    throw new RuntimeException("If you get here something has gone terribly wrong");
            }
        } else if (colors[0] > purpleBallColor[0] && colors[2] < purpleBallColor[2]) {
            switch (currentSorterPosition) {
                case INTAKE_POS_1:
                    currentSorterStates[0] = ballColors.PURPLE;
                    break;
                case INTAKE_POS_2:
                    currentSorterStates[1] = ballColors.PURPLE;
                    break;
                case INTAKE_POS_3:
                    currentSorterStates[2] = ballColors.PURPLE;
                    break;
                case MANUAL_OVERRIDE:
                    break;
                default:
                    throw new RuntimeException("If you get here something has gone terribly wrong");
            }
        } else {
            switch (currentSorterPosition) {
                case INTAKE_POS_1:
                    currentSorterStates[0] = ballColors.NULL;
                    break;
                case INTAKE_POS_2:
                    currentSorterStates[1] = ballColors.NULL;
                    break;
                case INTAKE_POS_3:
                    currentSorterStates[2] = ballColors.NULL;
                    break;
                case MANUAL_OVERRIDE:
                    break;
                default:
                    throw new RuntimeException("If you get here something has gone terribly wrong");
            }
        }
    }

    public boolean isCurrentSorterIntake() {
        if (currentSorterPosition == sorterPositions.INTAKE_POS_1 ||
            currentSorterPosition == sorterPositions.INTAKE_POS_2 ||
            currentSorterPosition == sorterPositions.INTAKE_POS_3) {
            return true;
        } else {
            return false;
        }

    }


    /**
     * Possible intake positions
     */
    public enum intakePos {
        INTAKE_POS_1,
        INTAKE_POS_2,
        INTAKE_POS_3
    }

    /**
     * Possible launch positions
     */
    public enum launchPos {
        LAUNCH_POS_1,
        LAUNCH_POS_2,
        LAUNCH_POS_3
    }


    /**
     * Different modes that the sorter can be moved to
     */
    public enum sorterModes {
        INTAKE,
        LAUNCH
    }

    /**
     * Possible colors that the balls can be
     */
    public enum ballColors {
        PURPLE,
        GREEN,
        NULL
    }

    public enum sorterPositions {
        INTAKE_POS_1,
        INTAKE_POS_2,
        INTAKE_POS_3,
        LAUNCH_POS_1,
        LAUNCH_POS_2,
        LAUNCH_POS_3,
        MANUAL_OVERRIDE
    }


}
