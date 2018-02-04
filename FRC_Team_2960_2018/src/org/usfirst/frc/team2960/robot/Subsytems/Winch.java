package org.usfirst.frc.team2960.robot.Subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import org.usfirst.frc.team2960.robot.Constants;

public class Winch implements SubsystemBase {


    private static Winch mInstance;

    public enum mWinchState {hookDeployment, winchUp, winchStop}

    public static Winch getInstance() {
        if (mInstance == null) {
            mInstance = new Winch();
        }
        return mInstance;
    }

    private TalonSRX mWinchMaster, mWinchSlave, mHookDeployment;


    private Winch() {
        setupTalons();
    }


    private void setupTalons() {
        mWinchMaster = new TalonSRX(Constants.mWinchMasterId);

        mWinchSlave = new TalonSRX(Constants.mWinchSlaveId);
        mWinchSlave.follow(mWinchMaster);

        mHookDeployment = new TalonSRX(Constants.mHookDeploymentId);
    }


    public void setWinchState(mWinchState state) {
        switch (state) {
            case winchStop:
                mWinchMaster.set(ControlMode.PercentOutput, 0);
                break;
            case winchUp:
                mWinchMaster.set(ControlMode.PercentOutput, 1.0);
                break;
            case hookDeployment:
                mHookDeployment.set(ControlMode.PercentOutput, 0);
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

    }

    /**
     * Zero all Sensors in the Subsystem
     */
    @Override
    public void zeroSensors() {

    }
}
