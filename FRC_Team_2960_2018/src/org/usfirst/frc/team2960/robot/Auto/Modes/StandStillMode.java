package org.usfirst.frc.team2960.robot.Auto.Modes;


import org.usfirst.frc.team2960.robot.Auto.AutoModeBase;
import org.usfirst.frc.team2960.robot.Auto.AutoModeEndedException;

/**
 * Fallback for when all autonomous modes do not work, resulting in a robot standstill
 */
public class StandStillMode extends AutoModeBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        System.out.println("Starting Stand Still Mode... Done!");
    }
}