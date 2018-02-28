package org.usfirst.frc.team2960.robot.Auto.Actions;


import org.usfirst.frc.team2960.robot.Subsytems.Drive;

/**
 * Forces the current path the robot is driving on to end early
 * 
 * @see DrivePathAction
 * @see Action
 * @see RunOnceAction
 */
public class ForceEndPathAction extends RunOnceAction {

    @Override
    public synchronized void runOnce() {
        Drive.getInstance().forceDoneWithPath();
    }
}
