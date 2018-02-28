package org.usfirst.frc.team2960.robot.Subsytems;

import org.usfirst.frc.team2960.robot.loops.Looper;

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

    void registerEnabledLoops(Looper enabledLooper);
}
