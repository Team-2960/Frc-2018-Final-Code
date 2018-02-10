package org.usfirst.frc.team2960.robot.Subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
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
        mElevatorMaster.setInverted(false);
        /* Set relevant frame periods to be at least as fast as periodic rate */
        mElevatorMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
        mElevatorMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
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
        // TODO: 1/31/2018 Might have to invert slave above  
    }
    
    public void goToLevel(double level) {
        // TODO: Make sure that its position(if at 100, you set to 50, then it goes to 50, not 150)
        double targetPosition = 4096 * 10 * level; //4096 ticks/rev, should change depending on where level is
        mElevatorMaster.set(ControlMode.MotionMagic, targetPosition);
        if(getBottomPhotoeye() || getTopPhotoeye()){
            mElevatorMaster.set(ControlMode.Velocity, 0);
        }
        else{
            mElevatorMaster.set(ControlMode.MotionMagic, targetPosition);
        }
    }

    public boolean atLevel(){
        if(mElevatorMaster.getMotorOutputPercent() == 0)
            return true;

        else
            return false;
        
    }



    // TODO: 2/3/18 Delete below function after testing talons
    public void testElevator(double speed) {
        if(getBottomPhotoeye() || getTopPhotoeye()){
            mElevatorMaster.set(ControlMode.Velocity, 0);
        }
        else {
            mElevatorMaster.set(ControlMode.PercentOutput, speed);
        }
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
        SmartDashboard.putBoolean("Bottom Photoeye", getBottomPhotoeye());
        SmartDashboard.putBoolean("Top Photoeye", getTopPhotoeye());
        /*
        SmartDashboard.putNumber("SensorVel", mElevatorMaster.getSelectedSensorVelocity(Constants.kPIDLoopIDx));
        SmartDashboard.putNumber("SensorPos", mElevatorMaster.getSelectedSensorPosition(Constants.kPIDLoopIDx));
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
}
