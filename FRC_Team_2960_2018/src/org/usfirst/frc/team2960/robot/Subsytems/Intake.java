package org.usfirst.frc.team2960.robot.Subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import org.usfirst.frc.team2960.robot.Constants;

/**
 * Class to control the intake on the 2018 robot
 *
 * Includes 2 TalonSRX speed controllers to control 2 bag motors on 70 to 1 Vex versa planetary gearboxes
 */
public class Intake implements SubsystemBase{


    private static Intake mInstance;

    /**
     * Enum for the different states of the intake
     */
    public enum mIntakeState {forward, backward, stop}

    private TalonSRX mIntakeMaster, mIntakeSlave;

    //To create the intake

    /**
     * Gets the private instance of the intake class
     * @return private instance of intake
     */
    public static Intake getInstance() {
        if(mInstance == null)
            mInstance = new Intake();
        return mInstance;
    }
    private Intake() {
        setupTalons();
    }

    /**
     * Setup the Talons
     */
    private void setupTalons() {
        //Master
        mIntakeMaster = new TalonSRX(Constants.mIntakeMasterId);

        //Slave
        mIntakeSlave = new TalonSRX(Constants.mIntakeSlaveId);
        mIntakeSlave.follow(mIntakeMaster);
        mIntakeSlave.setInverted(true);


    }

    /**
     * Sets the intake to a specified state
     * @param state The Desired state of the intake
     */
    public void setIntakeState(mIntakeState state)
    {
        switch (state) {
            case stop:
                setIntakeZero();
                break;
            case forward:
                mIntakeMaster.set(ControlMode.PercentOutput, 1.0);
                break;
            case backward:
                mIntakeMaster.set(ControlMode.PercentOutput, -1.0);
                break;
            default:
                break;
        }
    }

    /**
     * Sets Intake motor to zero
     */
    private void setIntakeZero(){
        mIntakeMaster.set(ControlMode.PercentOutput, 0);
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

    }

    /**
     * Stops all tasks in the Subsystem
     */
    @Override
    public void stop() {
        setIntakeZero();
    }

    /**
     * Zero all Sensors in the Subsystem
     */
    @Override
    public void zeroSensors() {

    }

}