package org.usfirst.frc.team2960.robot;


import org.usfirst.frc.team2960.robot.Subsytems.SubsystemBase;
import org.usfirst.frc.team2960.robot.loops.Looper;

import java.util.List;

/**
 * Used to reset, start, stop, and update all subsystems at once
 */
public class SubsystemManager {

    private final List<SubsystemBase> mAllSubsystems;

    public SubsystemManager(List<SubsystemBase> allSubsystems) {
        mAllSubsystems = allSubsystems;
    }

    public void outputToSmartDashboard() {
        mAllSubsystems.forEach((s) -> s.toSmartDashboard());
    }


    public void stop() {
        mAllSubsystems.forEach((s) -> s.stop());
    }

    public void zeroSensors() {
        mAllSubsystems.forEach((s) -> s.zeroSensors());
    }

    public void registerEnabledLoops(Looper enabledLooper) {
        mAllSubsystems.forEach((s) -> s.registerEnabledLoops(enabledLooper));
    }
}
