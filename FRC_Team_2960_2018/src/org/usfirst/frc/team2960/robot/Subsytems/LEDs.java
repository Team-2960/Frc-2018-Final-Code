package org.usfirst.frc.team2960.robot.Subsytems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LEDs implements SubsystemBase{

    /**
     * Private instance of LEDs class
     */
    private static LEDs mInstance;

    /**
     * I2C var
     */
    private I2C Wire;

    /**
     * String of selected led state
     */
    private String data;

    //Constructor
    private LEDs() {
        Wire = new I2C(I2C.Port.kOnboard, 4);
        data = "No Data";
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
        SmartDashboard.putString("Led Data", data);
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
