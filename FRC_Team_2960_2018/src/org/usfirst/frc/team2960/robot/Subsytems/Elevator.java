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

    private Elevator() {

        mBottomPhotoeye = new DigitalInput(Constants.kBottomPhotoeyeId);
        mTopPhotoeye = new DigitalInput(Constants.kTopPhotoeyeId);
        setupTalons();
    }
    private void setupTalons() {

    }
    private void photoeyeSensing() {


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
        SmartDashboard.putBoolean("Bottom Photoeye", mBottomPhotoeye.get());
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
