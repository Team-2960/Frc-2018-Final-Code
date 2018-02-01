package org.usfirst.frc.team2960.robot.Subsytems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2960.robot.Constants;

public class Elevator implements SubsystemBase{

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
     * Function to setup Talons
     */
    private void setupTalons() {
        mElevatorMaster = new TalonSRX(Constants.mElevatorMasterId);
        mElevatorSlave = new TalonSRX(Constants.mElevatorSlaveId);
        
        mElevatorSlave.follow(mElevatorMaster);
        // TODO: 1/31/2018 Might have to invert slave above  
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
