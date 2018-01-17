package org.usfirst.frc.team2960.robot;

import org.usfirst.team2960.Util.ConstantsBase;

public class Constants extends ConstantsBase {

    //Talon Ids
    public static int mRightMasterId = 0;
    public static int mLeftMasterId = 1;
    public static int mRightSlaveId = 2;
    public static int mLeftSlaveId = 3;

    public static double inchPerRotation = 6 * Math.PI;
    public static double inchPerTick = inchPerRotation / 4096;

    //Talon Constants
    public static int kPIDLoopIDx = 0;
    public static int kTimeoutMs = 10;
    public static int kSlotIdx = 0;

    public static int kCruiseVelocity; //Sensor UnitPer100ms
    public static int kAcceleration; //Sensor UnitPer100ms
}
