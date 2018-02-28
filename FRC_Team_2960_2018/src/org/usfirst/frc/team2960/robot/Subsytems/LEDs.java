package org.usfirst.frc.team2960.robot.Subsytems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2960.robot.loops.Looper;

public class LEDs extends Subsystem implements SubsystemBase{

    /**
     * Private instance of LEDs class
     */
    private static LEDs mInstance;

    /**
     * I2C var
     */
    private I2C Wire;

    //Constructor
    private LEDs() {
        Wire = new I2C(I2C.Port.kOnboard, 4);
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
     * Method to get the private instance of the LEDs class
     * @return private instance of the LEDs class
     */
    public static LEDs getInstance() {
        if(mInstance == null) {
            mInstance = new LEDs();
        }
        return mInstance;
    }

    /**
     * Send Data to the Arduino
     * @param data The Data you want to send
     */
    public void sendData(String data) {
        //Splits word into Array
        char[] CharArray = data.toCharArray();
        byte[] WriteData = new byte[CharArray.length];
        for (int i = 0; i < CharArray.length; i++) {
            WriteData[i] = (byte) CharArray[i];
        }
        Wire.writeBulk(WriteData, WriteData.length);
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

    @Override
    public void registerEnabledLoops(Looper enabledLooper) {

    }
}
