package org.usfirst.frc.team2960.robot.Auto.Actions;


import org.usfirst.frc.team2960.Util.Math.Rotation2d;
import org.usfirst.frc.team2960.robot.Subsytems.Drive;

/**
 * Turns the robot to a specified heading
 * 
 * @see Action
 */
public class TurnToHeadingAction implements Action {

    private Rotation2d mTargetHeading;
    private Drive mDrive = Drive.getInstance();

    public TurnToHeadingAction(Rotation2d heading) {
        mTargetHeading = heading;
    }

    @Override
    public boolean isFinished() {
        return mDrive.isDoneWithTurn();
    }

    @Override
    public void update() {
        // Nothing done here, controller updates in mEnabedLooper in robot
    }

    @Override
    public void done() {
    }

    @Override
    public void start() {
        mDrive.setWantTurnToHeading(mTargetHeading);
    }
}
