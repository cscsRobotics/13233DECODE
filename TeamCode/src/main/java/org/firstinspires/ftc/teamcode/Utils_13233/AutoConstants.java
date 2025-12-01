package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class AutoConstants {
    // Global Variables
    public final static double noPower = 0.0;
    public final static double quarterPower = 0.25;
    public final static double oneThirdPower = 0.34;
    public final static double halfPower = 0.5;
    public final static double threeQuartPower = 0.75;
    public final static double fullPower = 1.0;


    // Variables for imu
    protected double globalAngle, correction;
    protected YawPitchRollAngles lastAngles;

    protected double voltage = 0;
    protected static double batteryConst = 13.5;

    // Variables for driving with Encoders
    protected static final double wheelCircumference = 4.0 * Math.PI;
    protected static final double gearRatio = 18.9;                  // Rev HD Hex 20:1
    protected static final double countsPerRotation = 560.0;         // Rev HD Hex 20:1
    protected static final double scaleFactor = 9.0;                 // need to find scale factor!!!
    protected static final double countsPerInch = countsPerRotation / wheelCircumference
        / gearRatio * scaleFactor;

}
