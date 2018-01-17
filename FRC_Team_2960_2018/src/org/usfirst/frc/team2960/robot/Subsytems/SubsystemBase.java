package org.usfirst.frc.team2960.robot.Subsytems;

public interface SubsystemBase {

    /**
     * Updates the Subsystem
     */
    void update();

    /**
     * Startup code for the Subsystem
     */
    void startup();

    /**
     * Output the Subsystems Values to SmartDashboard
     */
    void toSmartDashboard();

    /**
     * Stops all tasks in the Subsystem
     */
    void stop();

    /**
     * Zero all Sensors in the Subsystem
     */
    void zeroSensors();
}
