package org.usfirst.frc.team2960.robot.Subsytems;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.ConstantsJNI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2960.robot.Constants;

public class Elevator extends Subsystem implements SubsystemBase{

    /**
     * Private Instance of the Subsystem
     */
    private static Elevator mInstance;

    //Sensors for Elevator

    private DigitalInput mBottomPhotoeye;
    private DigitalInput mTopPhotoeye;

    //Talons for Elevator
    private TalonSRX mElevatorMaster, mElevatorSlave;

    /**
     * States of the Elevator Subsystem
     */
    public enum mElevatorState {Switch, ScaleDown, ScaleBalanced, ScaleUp, Ground, MovingHeight, stopManual}

    /**
     * Method to get Singleton of the Subsystem
     * @return Elevator Instance
     */

    public static Elevator getInstance() {

        if(mInstance == null) {
            mInstance = new Elevator();
        }
        return mInstance;
    }

    /**
     * Sets the state of the Elevator subsystem to a specific state
     * @param state the desired state of the elevator
     * @see mElevatorState
     */
    public void setState(mElevatorState state, double rangeUp) {
        switch (state) {
            case Ground:
                mElevatorMaster.set(ControlMode.MotionMagic, Constants.kElevatorGround + rangeUp);
                break;
            case Switch:
                mElevatorMaster.set(ControlMode.MotionMagic, Constants.kElevatorSwitch  + rangeUp);
                break;
            case ScaleUp:
                mElevatorMaster.set(ControlMode.MotionMagic, Constants.kElevatorScaleUp  + rangeUp);
                break;
            case ScaleDown:
                mElevatorMaster.set(ControlMode.MotionMagic, Constants.kElevatorScaleDown  + rangeUp);
                break;
            case ScaleBalanced:
                mElevatorMaster.set(ControlMode.MotionMagic, Constants.kElevatorScaleBalanced  + rangeUp);
                break;
            case MovingHeight:
                mElevatorMaster.set(ControlMode.MotionMagic, Constants.kElevatorMovingHeight + rangeUp);
                break;
            case stopManual:
            	mElevatorMaster.set(ControlMode.MotionMagic, mElevatorMaster.getSelectedSensorPosition(Constants.kPIDLoopIDx));
            	break;
            	
        }
    }


    /**
     * Private Constructors for Elevator Class
     */
    private Elevator() {

        mBottomPhotoeye = new DigitalInput(Constants.kBottomPhotoeyeId);
        mTopPhotoeye = new DigitalInput(Constants.kTopPhotoeyeId);
        setupTalons();
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
     * Function to setup Talons
     */
    private void setupTalons() {
        mElevatorMaster = new TalonSRX(Constants.mElevatorMasterId);

        /* first choose the sensor */
        mElevatorMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIDx, Constants.kTimeoutMs);

        mElevatorMaster.setSensorPhase(true);
        mElevatorMaster.setInverted(true);
        /* Set relevant frame periods to be at least as fast as periodic rate */
        mElevatorMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
        mElevatorMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

        /* set the peak and nominal outputs */
        mElevatorMaster.configNominalOutputForward(0, Constants.kTimeoutMs);
        mElevatorMaster.configNominalOutputReverse(0, Constants.kTimeoutMs);
        mElevatorMaster.configPeakOutputForward(1, Constants.kTimeoutMs);
        mElevatorMaster.configPeakOutputReverse(-1, Constants.kTimeoutMs);
        /* set closed loop gains in slot0 - see documentation */
        mElevatorMaster.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIDx);
        mElevatorMaster.config_kF(Constants.kSlotIdx, Constants.mElevator_kF, Constants.kTimeoutMs);
        mElevatorMaster.config_kP(Constants.kSlotIdx, Constants.mElevator_kP, Constants.kTimeoutMs);
        mElevatorMaster.config_kI(Constants.kSlotIdx, Constants.mElevator_kI, Constants.kTimeoutMs);
        mElevatorMaster.config_kD(Constants.kSlotIdx, Constants.mElevator_kD, Constants.kTimeoutMs);
        /* set acceleration and cruise velocity */
        mElevatorMaster.configMotionCruiseVelocity(Constants.kCruiseVelocity, Constants.kTimeoutMs);
        mElevatorMaster.configMotionAcceleration(Constants.kAcceleration, Constants.kTimeoutMs);


        mElevatorSlave = new TalonSRX(Constants.mElevatorSlaveId);
        
        mElevatorSlave.follow(mElevatorMaster);
        mElevatorSlave.setInverted(false);

        setupLimitSwiches();
    }


    public void testElevator(double speed) {
        //if(getBottomPhotoeye() || getTopPhotoeye()){
            //mElevatorMaster.set(ControlMode.Velocity, 0);
        //}
        //else {
            mElevatorMaster.set(ControlMode.PercentOutput, speed);
        //}
    }

    /**
     * Get The State of the Bottom Photoeye
     * @return State of the Bottom Photoeye
     */
    private Boolean getBottomPhotoeye(){
         return mBottomPhotoeye.get();
    }

    /**
     * Get The State of the Top Photoeye
     * @return State of the Top Photoeye
     */
    private Boolean getTopPhotoeye(){
        return mTopPhotoeye.get();
    }


    /**
     * Updates the Subsystem
     */
    @Override
    public void update() {
        mElevatorMaster.configSetParameter(ParamEnum.eClearPositionOnLimitF, 1, 0, 0, Constants.kTimeoutMs);
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
        //SmartDashboard.putBoolean("Bottom Photoeye", getBottomPhotoeye());
        //SmartDashboard.putBoolean("Top Photoeye", getTopPhotoeye());

        SmartDashboard.putNumber("SensorVel", mElevatorMaster.getSelectedSensorVelocity(Constants.kPIDLoopIDx));
        SmartDashboard.putNumber("SensorPos", mElevatorMaster.getSelectedSensorPosition(Constants.kPIDLoopIDx));
        /*
        SmartDashboard.putNumber("MotorOutputPercent", mElevatorMaster.getMotorOutputPercent());
        SmartDashboard.putNumber("Closed Loop error", mElevatorMaster.getClosedLoopError(Constants.kPIDLoopIDx));
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
        mElevatorMaster.setSelectedSensorPosition(0, Constants.kPIDLoopIDx, Constants.kTimeoutMs);

    }

    public void setupLimitSwiches(){
        mElevatorMaster.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
        mElevatorMaster.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
    }
}
