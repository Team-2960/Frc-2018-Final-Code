package org.usfirst.frc.team2960.robot;

import org.usfirst.team2960.Util.ConstantsBase;

public class Constants extends ConstantsBase {

    //Talon Ids
    public static int mRightMasterId = 0;
    public static int mLeftMasterId = 1;
    public static int mRightSlaveId = 2;
    public static int mLeftSlaveId = 3;
    public static int mIntakeMasterId = 4;
    public static int mIntakeSlaveId = 5;

    // TODO: 1/17/18 Change wheel diameter to 6 when we get the real robot
    public static double wheelDiameter = 4;
    public static double inchPerRotation = wheelDiameter * Math.PI;
    public static double inchPerTick = inchPerRotation / 4096;

    //Talon Constants
    public static int kPIDLoopIDx = 0;
    public static int kTimeoutMs = 10;
    public static int kSlotIdx = 0;

    public static int kCruiseVelocity = 10000; //Sensor UnitPer100ms
    public static int kAcceleration = 3000; //Sensor UnitPer100ms

    //DIO (Digital In And Out)
    public static int kBottomPhotoEyeId = 1;

}