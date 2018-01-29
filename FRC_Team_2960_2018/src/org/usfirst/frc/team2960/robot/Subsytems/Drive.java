package org.usfirst.frc.team2960.robot.Subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2960.robot.Constants;



/**
 * Class for controlling the drive motors on our robot
 * @author Malcolm Machesky
 *
 * Includes 6 mRightMasteron SRX's for controlling the drive train through a WCP SS Gearbox
 *
 */
public class Drive extends Subsystem implements SubsystemBase {

    private static Drive m_Instance;

    /**
     * The NavX private instance
     */
    private AHRS navX;

    // Talons
    // TODO: 1/17/18 The Talons have to be changed for when we move to the new drivetrain!
    private TalonSRX mRightMaster, mRightSlave, mLeftMaster, mLeftSlave;

    private Ultrasonic mRight1, mRight2, mLeft1, mLeft2, front;

    private Ultrasonic[] mUltrasonics;
    /**
     * Private constructor for Drive Class
     */
    private Drive() {
        //Talons
        setupTalons();
        //NavX
        navX = new AHRS(SPI.Port.kMXP);

        //Ultrasonic setup




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
        /* first choose the sensor */
        mRightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIDx, Constants.kTimeoutMs);
        mRightMaster.setSensorPhase(true);
        mRightMaster.setInverted(false);
        /* Set relevant frame periods to be at least as fast as periodic rate */
        mRightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
        mRightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
        /* set closed loop gains in slot0 - see documentation */
        mRightMaster.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIDx);
        mRightMaster.config_kF(0, 0.2, Constants.kTimeoutMs);
        mRightMaster.config_kP(0, 0.2, Constants.kTimeoutMs);
        mRightMaster.config_kI(0, 0, Constants.kTimeoutMs);
        mRightMaster.config_kD(0, 0, Constants.kTimeoutMs);
        /* set acceleration and cruise velocity */
        mRightMaster.configMotionCruiseVelocity(Constants.kCruiseVelocity, Constants.kTimeoutMs);
        mRightMaster.configMotionAcceleration(Constants.kAcceleration, Constants.kTimeoutMs);

        //Right Slave
        mRightSlave = new TalonSRX(Constants.mRightSlaveId);
        mRightSlave.follow(mRightMaster);

        //Left Master
        mLeftMaster = new TalonSRX(Constants.mLeftMasterId);
        /* first choose the sensor */
        mLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIDx, Constants.kTimeoutMs);
        mLeftMaster.setSensorPhase(true);
        mLeftMaster.setInverted(false);
        /* Set relevant frame periods to be at least as fast as periodic rate */
        mLeftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
        mLeftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
        /* set closed loop gains in slot0 - see documentation */
        mLeftMaster.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIDx);
        mLeftMaster.config_kF(0, 0.2, Constants.kTimeoutMs);
        mLeftMaster.config_kP(0, 0.2, Constants.kTimeoutMs);
        mLeftMaster.config_kI(0, 0, Constants.kTimeoutMs);
        mLeftMaster.config_kD(0, 0, Constants.kTimeoutMs);
        /* set acceleration and cruise velocity */
        mLeftMaster.configMotionCruiseVelocity(Constants.kCruiseVelocity, Constants.kTimeoutMs);
        mLeftMaster.configMotionAcceleration(Constants.kAcceleration, Constants.kTimeoutMs);

        //Left Slave
        mLeftSlave = new TalonSRX(Constants.mLeftSlaveId);
        mLeftSlave.follow(mLeftMaster);
    }

    public void setSpeed(double right, double left){
        mRightMaster.set(ControlMode.PercentOutput, right);
        mLeftMaster.set(ControlMode.PercentOutput, left);
    }

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

    /**
     * A Function to move forward a certain amount of inch's with motion Magic
     * @param inch How many inches you want to move
     */
    public void moveForwardInch(double inch) {
        double pos = inch / Constants.inchPerTick;

        mRightMaster.set(ControlMode.MotionMagic, pos);
        mLeftMaster.set(ControlMode.MotionMagic, pos);
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
        SmartDashboard.putNumber("MotorOutputPercentRight", mRightMaster.getMotorOutputPercent());
        SmartDashboard.putNumber("ClosedLoopErrorRight", mRightMaster.getClosedLoopError(Constants.kPIDLoopIDx));

        SmartDashboard.putNumber("SensorVelLeft", mLeftMaster.getSelectedSensorVelocity(Constants.kPIDLoopIDx));
        SmartDashboard.putNumber("SensorPosLeft",  mLeftMaster.getSelectedSensorPosition(Constants.kPIDLoopIDx));
        SmartDashboard.putNumber("MotorOutputPercentLeft", mLeftMaster.getMotorOutputPercent());
        SmartDashboard.putNumber("ClosedLoopErrorLeft", mLeftMaster.getClosedLoopError(Constants.kPIDLoopIDx));
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
    }
}
