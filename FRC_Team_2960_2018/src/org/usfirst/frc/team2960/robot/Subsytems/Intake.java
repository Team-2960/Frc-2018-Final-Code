package org.usfirst.frc.team2960.robot.Subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2960.robot.Constants;

/**
 * Class to control the intake on the 2018 robot
 *
 * Includes 2 TalonSRX speed controllers to control 2 bag motors on 70 to 1 Vex versa planetary gearboxes
 * Also includes the window motor for the adjust of the intake
 */
public class Intake extends Subsystem implements SubsystemBase{


    private static Intake mInstance;

    /**
     * Enum for the different states of the intake
     */
    public enum mIntakeState {forward, backward, stop, rotate}

    public enum mIntakeAdjust {forward, backward, stop}

    private TalonSRX mIntakeMaster, mIntakeSlave, mIntakeAdjust;

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
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {

    }

    /**
     * Setup the Talons
     */
    private void setupTalons() {
        //Master
        mIntakeMaster = new TalonSRX(Constants.mIntakeMasterId);

        //Slave
        mIntakeSlave = new TalonSRX(Constants.mIntakeSlaveId);
        //mIntakeSlave.follow(mIntakeMaster);
        mIntakeSlave.setInverted(true);

        mIntakeAdjust = new TalonSRX(Constants.mintakeAdjustId);


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
                mIntakeSlave.setInverted(true);
                mIntakeMaster.set(ControlMode.PercentOutput, 1.0);
                mIntakeSlave.set(ControlMode.PercentOutput, .5);

                break;
            case backward:
                mIntakeSlave.setInverted(true);
                mIntakeMaster.set(ControlMode.PercentOutput, -1.0);
                mIntakeSlave.set(ControlMode.PercentOutput, -.5);
                break;
            case rotate:
                mIntakeSlave.setInverted(true);
                mIntakeMaster.set(ControlMode.PercentOutput, 1.0);
                mIntakeSlave.set(ControlMode.PercentOutput, 1.0);
                break;
            default:
                break;
        }
    }

    /**
     * Sets the state of the intake Adjust
     * @param state The desired state of the intake adjust
     */
    public void setIntakeAdjustState(mIntakeAdjust state) {
        switch (state) {
            case forward:
                mIntakeAdjust.set(ControlMode.PercentOutput, 1.0);
                break;

            case backward:
                mIntakeAdjust.set(ControlMode.PercentOutput, -1.0);
                break;

            case stop:
                mIntakeAdjust.set(ControlMode.PercentOutput, 0);
                break;
        }
    }

    /**
     * Sets Intake motor to zero
     */
    private void setIntakeZero(){
        mIntakeMaster.set(ControlMode.PercentOutput, 0);
        mIntakeSlave.set(ControlMode.PercentOutput, 0);
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