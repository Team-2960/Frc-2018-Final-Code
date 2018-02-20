package org.usfirst.frc.team2960.robot;

public class Constants {

    //Talon Ids
    public static int mRightMasterId = 0;
    public static int mRightSlave1Id = 1;
    public static int mRightSlave2Id = 2;
    public static int mLeftMasterId = 3;
    public static int mLeftSlave1Id = 4;
    public static int mLeftSlave2Id = 5;
    public static int mWinchMasterId = 6;
    public static int mWinchSlaveId = 7;
    public static int mElevatorMasterId = 8;
    public static int mElevatorSlaveId = 9;
    public static int mIntakeMasterId = 10;
    public static int mIntakeSlaveId = 11;
    public static int mHookDeploymentId = 12;

    //DIO (Digital In And Out)
    public static int kBottomPhotoeyeId = 8;
    public static int kTopPhotoeyeId = 9;
    public static int mUltrasonicRight1In = 1;
    public static int mUltrasonicRight1Out = 0;
    public static int mUltrasonicRight2In = 3;
    public static int mUltrasonicRight2Out = 2;
    public static int mUltrasonicLeft1In = 5;
    public static int mUltrasonicLeft1Out = 4;
    public static int mUltrasonicLeft2In = 7;
    public static int mUltrasonicLeft2Out = 6;

    //Analog
    public static int mUltrasonicFront = 0;




    // Robot Contraints
    public static double kDistanceBetweenWheels = 0.6223;//In Meters
    public static double kMaxVelocityOfTrajectory = 5;//In m/s

    //Talon Constants
    public static int kPIDLoopIDx = 0;
    public static int kTimeoutMs = 0;
    public static int kSlotIdx = 0;

    public static int kCruiseVelocity = 10000; //Sensor UnitPer100ms
    public static int kAcceleration = 3000; //Sensor UnitPer100ms

    //PID Constants
    public static double mElevator_kF = 0.2;
    public static double mElevator_kP = 0.2;
    public static double mElevator_kI = 0;
    public static double mElevator_kD = 0;

    //Elevator Heights
    public static double kElevatorLevel1 = 0;
    public static double kElevatorLevel2 = 1;
    public static double kElevator_Level3 = 2;

    //Trajectory Constants
    public static double kLeftTrajectoryP = 1.0;
    public static double kLeftTrajectoryI = 0;//Not used
    public static double kLeftTrajectoryD = 0;
    public static double kTrajectoryVelocityRatio = 1 / kMaxVelocityOfTrajectory;
    public static double kLeftTrajectoryAccelerationGain = 0;
    public static double kRightTrajectoryP = 1.0;
    public static double kRightTrajectoryI = 0;//Not used
    public static double kRightTrajectoryD = 0;
    public static double kRightTrajectoryAccelerationGain = 0;



}