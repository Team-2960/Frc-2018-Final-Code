package org.usfirst.frc.team2960.robot.Subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2960.Util.Drivers.NavX;
import org.usfirst.frc.team2960.Util.Math.RigidTransform2d;
import org.usfirst.frc.team2960.Util.Math.Rotation2d;
import org.usfirst.frc.team2960.Util.Math.Twist2d;
import org.usfirst.frc.team2960.Util.ReflectingCSVWriter;
import org.usfirst.frc.team2960.Util.control.Lookahead;
import org.usfirst.frc.team2960.Util.control.Path;
import org.usfirst.frc.team2960.Util.control.PathFollower;
import org.usfirst.frc.team2960.robot.Constants;
import org.usfirst.frc.team2960.robot.Kinematics;
import org.usfirst.frc.team2960.robot.RobotState;
import org.usfirst.frc.team2960.robot.loops.Loop;
import org.usfirst.frc.team2960.robot.loops.Looper;


/**
 * Class for controlling the drive motors on our robot
 * @author Malcolm Machesky
 *
 * Includes 6 mRightMasteron SRX's for controlling the drive train through a WCP SS Gearbox
 *
 */
public class Drive extends Subsystem implements SubsystemBase {

    private static Drive m_Instance;

    private boolean mIsOnTarget = false;

    /**
     * The NavX private instance
     */
    private NavX navX;

    // Talons
    private TalonSRX mRightMaster, mRightSlave1, mRightSlave2, mLeftMaster, mLeftSlave1, mLeftSlave2;

    private mDriveState mDriveControlState;

    // Logging
    private final ReflectingCSVWriter<PathFollower.DebugOutput> mCSVWriter;

    // Controllers
    private RobotState mRobotState = RobotState.getInstance();
    private PathFollower mPathFollower;

    private Path mCurrentPath = null;
    private Rotation2d mTargetHeading = new Rotation2d();

    private final Loop mLoop = new Loop() {
        @Override
        public void onStart(double timestamp) {
            synchronized (Drive.this) {
                setSpeed(0,0);
                setNeturalMode(NeutralMode.Coast);
                navX.reset();
            }
        }

        @Override
        public void onLoop(double timestamp) {
            switch (mDriveControlState) {
                case OPEN_LOOP:
                    return;
                case VELOCITY_SETPOINT:
                    return;
                case PATH_FOLLOWING:
                    if (mPathFollower != null) {
                        updatePathFollower(timestamp);
                        mCSVWriter.add(mPathFollower.getDebug());
                    }
                    return;
            }
        }

        @Override
        public void onStop(double timestamp) {
            stop();
            mCSVWriter.flush();
        }
    };

    /**
     * Ultrasonic Sensors, all but front are vex sensors
     */
    private Ultrasonic mUltraRight1, mUltraRight2, mUltraLeft1, mUltraLeft2;
    //private AnalogInput mUltraFront;

    /**
     * The array for the Ultrasonic Sensors
     */
    private Ultrasonic[] mUltrasonics;

    public enum mDriveState {
        OPEN_LOOP, // open loop voltage control
        PATH_FOLLOWING, // used for autonomous driving
        TURN_TO_HEADING, // turn in place
        VELOCITY_SETPOINT
    };

    /**
     * Private constructor for Drive Class
     */
    private Drive() {
        //Talons
        setupTalons();
        //NavX
        navX = new NavX(I2C.Port.kMXP);

        //Ultrasonic setup

        reloadGains();

        mUltraRight1 = new Ultrasonic(Constants.mUltrasonicRight1Out, Constants.mUltrasonicRight1In);
        mUltraRight2 = new Ultrasonic(Constants.mUltrasonicRight2Out, Constants.mUltrasonicRight2In);
        mUltraLeft1 = new Ultrasonic(Constants.mUltrasonicLeft1Out, Constants.mUltrasonicLeft1In);
        mUltraLeft2 = new Ultrasonic(Constants.mUltrasonicLeft2Out, Constants.mUltrasonicLeft2In);
        mUltrasonics = new Ultrasonic[]{mUltraRight1, mUltraRight2, mUltraLeft1, mUltraLeft2};
        mUltraRight1.setAutomaticMode(true);
        //mUltraFront = new AnalogInput(Constants.mUltrasonicFront);

        mCSVWriter = new ReflectingCSVWriter<PathFollower.DebugOutput>("/home/lvuser/PATH-FOLLOWER-LOGS.csv",
                PathFollower.DebugOutput.class);


    }


    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {

    }

    /**
     * Method to setup settings on the 6 mRightMaster talons
     */
    private void setupTalons() {
        //Right Master
        mRightMaster = new TalonSRX(Constants.mRightMasterId);
        mRightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIDx, Constants.kTimeoutMs);

        //Right Slaves
        mRightSlave1 = new TalonSRX(Constants.mRightSlave1Id);
        mRightSlave1.follow(mRightMaster);

        mRightSlave2 = new TalonSRX(Constants.mRightSlave2Id);
        mRightSlave2.follow(mRightMaster);

        //Left Master
        mLeftMaster = new TalonSRX(Constants.mLeftMasterId);
        mLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIDx, Constants.kTimeoutMs);

        //Left Slave
        mLeftSlave1 = new TalonSRX(Constants.mLeftSlave1Id);
        mLeftSlave1.follow(mLeftMaster);

        mLeftSlave2 = new TalonSRX(Constants.mLeftSlave2Id);
        mLeftSlave2.follow(mLeftMaster);
    }

    public void setSpeed(double right, double left){
        mRightMaster.set(ControlMode.PercentOutput, -right);
        mLeftMaster.set(ControlMode.PercentOutput, -left);
    }

    /**
     * Function to get the array of ultrasonics
     * @return The array of ultrasonics on the Drive train
     */
    //public Ultrasonic[] getUltrasonics() {
        //return mUltrasonics;
    //}

    /**
     * Function to get the private instance of Drive
     * @return Singleton of the drive class
     */
    public static Drive getInstance()  {
        if (m_Instance == null) {
            m_Instance = new Drive();
        }
        return m_Instance;
    }

    public int getRightEncoder() {
        return mRightMaster.getSelectedSensorPosition(Constants.kPIDLoopIDx);
    }

    public int getLeftEncoder() {
        return mLeftMaster.getSelectedSensorPosition(Constants.kPIDLoopIDx);
    }



    public double getRightDistanceInMeters() {
        return getRightEncoder() * Constants.inchesPerTick * Constants.meterConversion;
    }

    public double getLeftDistanceInMeters() {
        return getLeftEncoder() * Constants.inchesPerTick * Constants.meterConversion;
    }

    /**
     * Updates the Subsystem
     */
    @Override
    public void update() {

    }

    /**
     * Startup code for the Subsystem
     */
    @Override
    public void startup() {

    }

    /**
     * Output the Subsystems Values to SmartDashboard
     */
    @Override
    public void toSmartDashboard() {

        SmartDashboard.putNumber("SensorVelRight", mRightMaster.getSelectedSensorVelocity(Constants.kPIDLoopIDx));
        SmartDashboard.putNumber("SensorPosRight",  mRightMaster.getSelectedSensorPosition(Constants.kPIDLoopIDx));
        //SmartDashboard.putNumber("MotorOutputPercentRight", mRightMaster.getMotorOutputPercent());
        //SmartDashboard.putNumber("ClosedLoopErrorRight", mRightMaster.getClosedLoopError(Constants.kPIDLoopIDx));

        SmartDashboard.putNumber("SensorVelLeft", mLeftMaster.getSelectedSensorVelocity(Constants.kPIDLoopIDx));
        SmartDashboard.putNumber("SensorPosLeft",  mLeftMaster.getSelectedSensorPosition(Constants.kPIDLoopIDx));
        //SmartDashboard.putNumber("MotorOutputPercentLeft", mLeftMaster.getMotorOutputPercent());
        //SmartDashboard.putNumber("ClosedLoopErrorLeft", mLeftMaster.getClosedLoopError(Constants.kPIDLoopIDx));
        /*
        SmartDashboard.putNumber("The BAD ULtra", mUltraLeft2.getRangeInches());
        SmartDashboard.putNumber("NAVX ANGLE", navX.getAngle());
        SmartDashboard.putNumber("BARometric Pressure", navX.getBarometricPressure());
        SmartDashboard.putNumber("YAW", navX.getYaw());
        SmartDashboard.putNumber("Navx Rate", navX.getRate());
        SmartDashboard.putNumber("Gyro x", navX.getRawGyroX());
        SmartDashboard.putNumber("Gyro y", navX.getRawGyroY());
        SmartDashboard.putNumber("Gyro z", navX.getRawGyroZ());
        for(Ultrasonic ultra: mUltrasonics){
            SmartDashboard.putNumber("Ultra Value: " + ultra.getName(), ultra.getRangeInches());
        }
        //SmartDashboard.putNumber("Ultra Value: analog ultra", mUltraFront.getValue());
        */
    }

    /**
     * Stops all tasks in the Subsystem
     */
    @Override
    public void stop() {

    }

    /**
     * Zero all Sensors in the Subsystem
     */
    @Override
    public void zeroSensors() {
        mRightMaster.setSelectedSensorPosition(0, Constants.kPIDLoopIDx, Constants.kTimeoutMs);
        mLeftMaster.setSelectedSensorPosition(0, Constants.kPIDLoopIDx, Constants.kTimeoutMs);
        //navX.zeroYaw();
    }

    @Override
    public void registerEnabledLoops(Looper enabledLooper) {
        enabledLooper.register(mLoop);
    }

    public void setNeturalMode(NeutralMode mode) {
        mRightMaster.setNeutralMode(mode);
        mRightSlave1.setNeutralMode(mode);
        mRightSlave2.setNeutralMode(mode);
        mLeftMaster.setNeutralMode(mode);
        mLeftSlave1.setNeutralMode(mode);
        mLeftSlave2.setNeutralMode(mode);
    }

    public synchronized boolean hasPassedMarker(String marker) {
        if (mDriveControlState == mDriveState.PATH_FOLLOWING && mPathFollower != null) {
            return mPathFollower.hasPassedMarker(marker);
        } else {
            System.out.println("Robot is not in path following mode");
            return false;
        }
    }

    /**
     * Called periodically when the robot is in path following mode. Updates the path follower with the robots latest
     * pose, distance driven, and velocity, the updates the wheel velocity setpoints.
     */
    private void updatePathFollower(double timestamp) {
        RigidTransform2d robot_pose = mRobotState.getLatestFieldToVehicle().getValue();
        Twist2d command = mPathFollower.update(timestamp, robot_pose,
                RobotState.getInstance().getDistanceDriven(), RobotState.getInstance().getPredictedVelocity().dx);
        if (!mPathFollower.isFinished()) {
            Kinematics.DriveVelocity setpoint = Kinematics.inverseKinematics(command);
            updateVelocitySetpoint(setpoint.left, setpoint.right);
        } else {
            updateVelocitySetpoint(0, 0);
        }
    }

    /**
     * Adjust Velocity setpoint (if already in velocity mode)
     *
     * @param left_inches_per_sec
     * @param right_inches_per_sec
     */
    private synchronized void updateVelocitySetpoint(double left_inches_per_sec, double right_inches_per_sec) {
        if (usesTalonVelocityControl(mDriveControlState)) {
            final double max_desired = Math.max(Math.abs(left_inches_per_sec), Math.abs(right_inches_per_sec));
            final double scale = max_desired > Constants.kDriveMaxSetpoint
                    ? Constants.kDriveMaxSetpoint / max_desired : 1.0;
            mLeftMaster.set(ControlMode.Velocity, inchesPerSecondToRpm(left_inches_per_sec * scale));
            mRightMaster.set(ControlMode.Velocity, inchesPerSecondToRpm(right_inches_per_sec * scale));
        } else {
            System.out.println("Hit a bad velocity control state");
            mLeftMaster.set(ControlMode.PercentOutput, 0);
            mRightMaster.set(ControlMode.PercentOutput, 0);
        }
    }

    /**
     * Check if the drive talons are configured for velocity control
     */
    protected static boolean usesTalonVelocityControl(mDriveState state) {
        if (state == mDriveState.VELOCITY_SETPOINT || state == mDriveState.PATH_FOLLOWING) {
            return true;
        }
        return false;
    }

    private static double inchesToRotations(double inches) {
        return inches / (Constants.kDriveWheelDiameterInches * Math.PI);
    }

    private static double inchesPerSecondToRpm(double inches_per_second) {
        return inchesToRotations(inches_per_second) * 60;
    }

    private static double rotationsToInches(double rotations) {
        return rotations * (Constants.kDriveWheelDiameterInches * Math.PI);
    }

    private static double rpmToInchesPerSecond(double rpm) {
        return rotationsToInches(rpm) / 60;
    }

    /**
     * Configures the drivebase to drive a path. Used for autonomous driving
     *
     * @see Path
     */
    public synchronized void setWantDrivePath(Path path, boolean reversed) {
        if (mCurrentPath != path || mDriveControlState != mDriveState.PATH_FOLLOWING) {

            RobotState.getInstance().resetDistanceDriven();
            mPathFollower = new PathFollower(path, reversed,
                    new PathFollower.Parameters(
                            new Lookahead(Constants.kMinLookAhead, Constants.kMaxLookAhead,
                                    Constants.kMinLookAheadSpeed, Constants.kMaxLookAheadSpeed),
                            Constants.kInertiaSteeringGain, Constants.kPathFollowingProfileKp,
                            Constants.kPathFollowingProfileKi, Constants.kPathFollowingProfileKv,
                            Constants.kPathFollowingProfileKffv, Constants.kPathFollowingProfileKffa,
                            Constants.kPathFollowingMaxVel, Constants.kPathFollowingMaxAccel,
                            Constants.kPathFollowingGoalPosTolerance, Constants.kPathFollowingGoalVelTolerance,
                            Constants.kPathStopSteeringDistance));
            mDriveControlState = mDriveState.PATH_FOLLOWING;
            mCurrentPath = path;
        } else {
            setVelocitySetpoint(0, 0);
        }
    }

    /**
     * Start up velocity mode. This sets the drive train in high gear as well.
     *
     * @param left_inches_per_sec
     * @param right_inches_per_sec
     */
    public synchronized void setVelocitySetpoint(double left_inches_per_sec, double right_inches_per_sec) {

        mDriveControlState = mDriveState.VELOCITY_SETPOINT;
        updateVelocitySetpoint(left_inches_per_sec, right_inches_per_sec);
    }

    public synchronized Rotation2d getGyroAngle() {
        return navX.getYaw();
    }

    public synchronized void setGyroAngle(Rotation2d angle) {
        navX.reset();
        navX.setAngleAdjustment(angle);
    }

    public double getLeftDistanceInches() {
        return rotationsToInches(mLeftMaster.getSelectedSensorPosition(Constants.kPIDLoopIDx));
    }

    public double getRightDistanceInches() {
        return rotationsToInches(mRightMaster.getSelectedSensorPosition(Constants.kPIDLoopIDx));
    }

    public double getLeftVelocityInchesPerSec() {
        return rpmToInchesPerSecond(mLeftMaster.getSelectedSensorVelocity(Constants.kPIDLoopIDx));
    }

    public double getRightVelocityInchesPerSec() {
        return rpmToInchesPerSecond(mRightMaster.getSelectedSensorVelocity(Constants.kPIDLoopIDx));
    }

    public synchronized boolean isDoneWithPath() {
        if (mDriveControlState == mDriveState.PATH_FOLLOWING && mPathFollower != null) {
            return mPathFollower.isFinished();
        } else {
            System.out.println("Robot is not in path following mode");
            return true;
        }
    }

    public synchronized boolean isDoneWithTurn() {
        if (mDriveControlState == mDriveState.TURN_TO_HEADING) {
            return mIsOnTarget;
        } else {
            System.out.println("Robot is not in turn to heading mode");
            return false;
        }
    }

    public synchronized void forceDoneWithPath() {
        if (mDriveControlState == mDriveState.PATH_FOLLOWING && mPathFollower != null) {
            mPathFollower.forceFinish();
        } else {
            System.out.println("Robot is not in path following mode");
        }
    }

    public synchronized void reloadGains() {


        /* set the peak, nominal outputs */
        mLeftMaster.configNominalOutputForward(0, Constants.kTimeoutMs);
        mLeftMaster.configNominalOutputReverse(0, Constants.kTimeoutMs);
        mLeftMaster.configPeakOutputForward(1, Constants.kTimeoutMs);
        mLeftMaster.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		/* set closed loop gains in slot0 */
        mLeftMaster.config_kF(Constants.kPIDLoopIDx, Constants.kVelocityControlKf, Constants.kTimeoutMs);
        mLeftMaster.config_kP(Constants.kPIDLoopIDx, Constants.kVelocityControlKp, Constants.kTimeoutMs);
        mLeftMaster.config_kI(Constants.kPIDLoopIDx, Constants.kVelocityControlKi, Constants.kTimeoutMs);
        mLeftMaster.config_kD(Constants.kPIDLoopIDx, Constants.kVelocityControlKd, Constants.kTimeoutMs);
    }



}
