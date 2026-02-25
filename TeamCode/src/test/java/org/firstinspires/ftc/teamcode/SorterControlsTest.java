package org.firstinspires.ftc.teamcode;

import static org.mockito.Mockito.verify;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Utils_13233.SorterControls;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SorterControlsTest {
    public SorterControls sorter;

    MockMotorUtil mockMotor = new MockMotorUtil();

    @BeforeEach
    void motorSetup() {
        mockMotor.setUp();
        sorter = new SorterControls(mockMotor.hardwareMap);
    }


    // Test Move to intake position
    @DisplayName("should set the sorter to the intake position 1")
    @Test
    void testMoveToIntakePos_pos1() {
        sorter.moveToIntakePos(SorterControls.intakePos.INTAKE_POS_1);

        verify(mockMotor.Sorter).setTargetPosition(sorter.intakePos1);
    }

    @DisplayName("should set the sorter to the intake position 2")
    @Test
    void testMoveToIntakePos_pos2() {
        sorter.moveToIntakePos(SorterControls.intakePos.INTAKE_POS_2);

        verify(mockMotor.Sorter).setTargetPosition(sorter.intakePos2);
    }

    @DisplayName("should set the sorter to the intake position 3")
    @Test
    void testMoveToIntakePos_pos3() {
        sorter.moveToIntakePos(SorterControls.intakePos.INTAKE_POS_3);

        verify(mockMotor.Sorter).setTargetPosition(sorter.intakePos3);
    }


    // Test Move to launch position
    @DisplayName("should set the sorter to the launch position 1")
    @Test
    void testMoveToLaunchPos_pos1() {
        sorter.moveToLaunchPos(SorterControls.launchPos.LAUNCH_POS_1);

        verify(mockMotor.Sorter).setTargetPosition(sorter.LaunchPos1);
    }

    @DisplayName("should set the sorter to the launch position 2")
    @Test
    void testMoveToLaunchPos_pos2() {
        sorter.moveToLaunchPos(SorterControls.launchPos.LAUNCH_POS_2);

        verify(mockMotor.Sorter).setTargetPosition(sorter.LaunchPos2);
    }

    @DisplayName("should set the sorter to the launch position 3")
    @Test
    void testMoveToLaunchPos_pos3() {
        sorter.moveToLaunchPos(SorterControls.launchPos.LAUNCH_POS_3);

        verify(mockMotor.Sorter).setTargetPosition(sorter.LaunchPos3);
    }

    // Test Move to sorter position
    @DisplayName("should set the sorter to the intake position 1")
    @Test
    void testMoveSorterToPos_intake_pos1() {
        sorter.moveSorterToPos(SorterControls.sorterModes.INTAKE, 1);

        verify(mockMotor.Sorter).setTargetPosition(sorter.intakePos1);
    }

    // Test Move to sorter position
    @DisplayName("should set the sorter to the intake position 2")
    @Test
    void testMoveSorterToPos_intake_pos2() {
        sorter.moveSorterToPos(SorterControls.sorterModes.INTAKE, 2);

        verify(mockMotor.Sorter).setTargetPosition(sorter.intakePos2);
    }

    // Test Move to sorter position
    @DisplayName("should set the sorter to the intake position 3")
    @Test
    void testMoveSorterToPos_intake_pos3() {
        sorter.moveSorterToPos(SorterControls.sorterModes.INTAKE, 3);

        verify(mockMotor.Sorter).setTargetPosition(sorter.intakePos3);
    }

    @DisplayName("should set the sorter to the intake position 1")
    @Test
    void testMoveSorterToPos_launch_pos1() {
        sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 1);

        verify(mockMotor.Sorter).setTargetPosition(sorter.LaunchPos1);
    }

    // Test Move to sorter position
    @DisplayName("should set the sorter to the intake position 2")
    @Test
    void testMoveSorterToPos_launch_pos2() {
        sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 2);

        verify(mockMotor.Sorter).setTargetPosition(sorter.LaunchPos2);
    }

    // Test Move to sorter position
    @DisplayName("should set the sorter to the intake position 3")
    @Test
    void testMoveSorterToPos_launch_pos3() {
        sorter.moveSorterToPos(SorterControls.sorterModes.LAUNCH, 3);

        verify(mockMotor.Sorter).setTargetPosition(sorter.LaunchPos3);
    }
}

