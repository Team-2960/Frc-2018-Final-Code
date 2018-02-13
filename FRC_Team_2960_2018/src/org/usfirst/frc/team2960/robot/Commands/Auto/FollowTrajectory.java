package org.usfirst.frc.team2960.robot.Commands.Auto;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.DistanceFollower;
import jaci.pathfinder.modifiers.TankModifier;
import org.usfirst.frc.team2960.robot.Constants;
import org.usfirst.frc.team2960.robot.Subsytems.Drive;

public class FollowTrajectory extends Command{

    private Trajectory trajectory;
    private TankModifier modifier;
    DistanceFollower left;
    DistanceFollower right;
    private Drive drive = Drive.getInstance();
    public FollowTrajectory(Trajectory trajectory) {
        this.trajectory = trajectory;
        modifier = new TankModifier(trajectory).modify(Constants.kDistanceBetweenWheels);
    }

    /**
     * The initialize method is called the first time this Command is run after being started.
     */
    @Override
    protected void initialize() {
        left = new DistanceFollower();
        right = new DistanceFollower();
        left.configurePIDVA(Constants.kLeftTrajectoryP, Constants.kLeftTrajectoryI, Constants.kLeftTrajectoryD,
                Constants.kTrajectoryVelocityRatio, Constants.kLeftTrajectoryAccelerationGain);
        right.configurePIDVA(Constants.kRightTrajectoryP, Constants.kRightTrajectoryI, Constants.kRightTrajectoryD,
                Constants.kTrajectoryVelocityRatio, Constants.kRightTrajectoryAccelerationGain);
    }

    /**
     * The execute method is called repeatedly until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        double leftOutput = left.calculate(drive.getLeftEncoder());
        double rightOutput = right.calculate(drive.getRightEncoder());

        double gyroHeading = drive.getHeading();
        double desiredHeading = Pathfinder.r2d(left.getHeading());

        double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
        double turn = 0.8 * (-1.0 / 80.0) * angleDifference;

        drive.setSpeed(rightOutput - turn, leftOutput + turn);
    }

    /**
     * Returns whether this command is finished. If it is, then the command will be removed and {@link
     * Command#end() end()} will be called.
     * <p>
     * <p>It may be useful for a team to reference the {@link Command#isTimedOut() isTimedOut()}
     * method for time-sensitive commands.
     * <p>
     * <p>Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Returning true will result in the
     * command executing once and finishing immediately. We recommend using {@link InstantCommand}
     * for this.
     *
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    @Override
    protected boolean isFinished() {
        if (right.isFinished() && left.isFinished()) {
            return true;
        }
        return false;
    }
}
