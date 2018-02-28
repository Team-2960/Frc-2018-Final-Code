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


    //Math
    public static double inchPerRevolution = 6 * Math.PI;
    public static double inchesPerTick = inchPerRevolution/4096;
    public static double meterConversion = 0.0254;


    // Robot Contraints
    public static double kDistanceBetweenWheels = 0.6223;//In Meters
    public static double kMaxVelocityOfTrajectory = .5;//In m/s

    //Talon Constants
    public static int kPIDLoopIDx = 0;
    public static int kTimeoutMs = 10;
    public static int kSlotIdx = 0;

    public static int kCruiseVelocity = 1000; //Sensor UnitPer100ms (Comp) 498 (practice) 1000
    public static int kAcceleration = 500; //Sensor UnitPer100ms

    //PID Constants
    public static double mElevator_kF = 0.2;
    public static double mElevator_kP = 5; // (practice) 5 (Comp) 1 for now
    public static double mElevator_kI = 0;
    public static double mElevator_kD = 0;

    //Elevator Heights
    public static double kElevatorSwitch =  -5097.0;
    public static double kElevatorGround = 0;
    public static double kElevatorScaleUp = -19099.0;
    public static double kElevatorScaleDown = -14550.0;
    public static double kElevatorScaleBalanced = -15877.0;

    //Trajectory Constants
    public static double kLeftTrajectoryP = 1;
    public static double kLeftTrajectoryI = 0;//Not used
    public static double kLeftTrajectoryD = 0;
    public static double kTrajectoryVelocityRatio = 1 / kMaxVelocityOfTrajectory;
    public static double kLeftTrajectoryAccelerationGain = 0;
    public static double kRightTrajectoryP = 1;
    public static double kRightTrajectoryI = 0;//Not used
    public static double kRightTrajectoryD = 0;
    public static double kRightTrajectoryAccelerationGain = 0;

    //AdaptivePurePursuitController Constants

    public static double kLooperDt = 0.005;

    // Wheels
    public static double kDriveWheelDiameterInches = 6;
    public static double kTrackWidthInches = 26.655; //Look into
    public static double kTrackScrubFactor = 0.924;
    public static double kDriveMaxSetpoint = 17.0 * 12.0; // 17 fps Change

    // Path following constants
    public static double kMinLookAhead = 12.0; // inches
    public static double kMinLookAheadSpeed = 9.0; // inches per second
    public static double kMaxLookAhead = 24.0; // inches
    public static double kMaxLookAheadSpeed = 120.0; // inches per second

    public static double kInertiaSteeringGain = 0.0; // angular velocity command is multiplied by this gain *
    // our speed
    // in inches per sec
    public static double kSegmentCompletionTolerance = 0.1; // inches
    public static double kPathFollowingMaxAccel = 120.0; // inches per second^2
    public static double kPathFollowingMaxVel = 120.0; // inches per second
    public static double kPathFollowingProfileKp = 5.00;
    public static double kPathFollowingProfileKi = 0.03;
    public static double kPathFollowingProfileKv = 0.02;
    public static double kPathFollowingProfileKffv = 1.0;
    public static double kPathFollowingProfileKffa = 0.05;
    public static double kPathFollowingGoalPosTolerance = 0.75;
    public static double kPathFollowingGoalVelTolerance = 12.0;
    public static double kPathStopSteeringDistance = 9.0;

    //Talon pidf for velocity control

    public static double kVelocityControlKf = 0.0;
    public static double kVelocityControlKp = 0.0;
    public static double kVelocityControlKi = 0.0;
    public static double kVelocityControlKd = 0.0;



}