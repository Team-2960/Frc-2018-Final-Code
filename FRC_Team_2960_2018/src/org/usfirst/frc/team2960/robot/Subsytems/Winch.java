package org.usfirst.frc.team2960.robot.Subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2960.robot.Constants;

/**
 * Winch Subsystem for the 2018 FRC robot
 *
 * Contains 2 775 pros on talon SRXs to winch up are robot for the end game also includes a snowBlower motor for the hook
 * @author malcolmmachesky
 */
public class Winch extends Subsystem implements SubsystemBase {


    private static Winch mInstance;

    /**
     * States of the winch Subsystem
     */
    public enum mWinchState { winchUp, winchStop, winchDown}
    public enum mHookState {hookDeployment, hookDeploymentStop, hookDeploymentbackword}


    /**
     * Gets the private instance of the winch subsystem
     * @return The private instance of the winch Subsystem
     */
    public static Winch getInstance() {
        if (mInstance == null) {
            mInstance = new Winch();
        }
        return mInstance;
    }

    /**
     * Talons for the winch and hook motors
     */
    private TalonSRX mWinchMaster, mWinchSlave, mHookDeployment;

    /**
     * Private Constructor for Winch Subsystem
     */
    private Winch() {
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


    private void setupTalons() {
        mWinchMaster = new TalonSRX(Constants.mWinchMasterId);

        mWinchSlave = new TalonSRX(Constants.mWinchSlaveId);
        //mWinchSlave.follow(mWinchMaster);
        //mWinchSlave.setInverted(true);

        mHookDeployment = new TalonSRX(Constants.mHookDeploymentId);
    }


    /**
     * Used to set the state of the winch
     * @param state The desired state for the winch
     */
    public void setWinchState(mWinchState state) {
        switch (state) {
            case winchStop:
                mWinchMaster.set(ControlMode.PercentOutput, 0);
                mWinchSlave.set(ControlMode.PercentOutput, 0);
                break;
            case winchUp:
                mWinchMaster.set(ControlMode.PercentOutput, 1);
                mWinchSlave.set(ControlMode.PercentOutput, -1);
                break;
            case winchDown:
                mWinchMaster.set(ControlMode.PercentOutput, -1);
                mWinchSlave.set(ControlMode.PercentOutput, 1);
            default:

                break;

        }
    }

    public void setHookState(mHookState state) {
        switch (state) {
            case hookDeployment:
                mHookDeployment.set(ControlMode.PercentOutput, .5);
                break;
            case hookDeploymentStop:
                mHookDeployment.set(ControlMode.PercentOutput, 0);
                break;
            case hookDeploymentbackword:
                mHookDeployment.set(ControlMode.PercentOutput, -.5);
                break;
            default:

                break;
        }
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
        mWinchMaster.set(ControlMode.PercentOutput, 0);
        mHookDeployment.set(ControlMode.PercentOutput, 0);
    }

    /**
     * Zero all Sensors in the Subsystem
     */
    @Override
    public void zeroSensors() {

    }
}
