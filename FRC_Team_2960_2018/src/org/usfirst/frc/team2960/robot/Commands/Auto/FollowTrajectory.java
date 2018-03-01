package org.usfirst.frc.team2960.robot.Commands.Auto;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.DistanceFollower;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;
import org.usfirst.frc.team2960.robot.Constants;
import org.usfirst.frc.team2960.robot.Subsytems.Drive;

public class FollowTrajectory extends Command{

    private Trajectory trajectory;
    private TankModifier modifier;
    EncoderFollower left;
    EncoderFollower right;
    public Runnable run = new Runnable() {
        @Override
        public void run() {
            System.out.println(drive.getLeftEncoder());


            double leftOutput = left.calculate(-drive.getRightEncoder());
            double rightOutput = right.calculate(drive.getLeftEncoder());

            double gyroHeading = drive.getHeading();
            double desiredHeading = Pathfinder.r2d(left.getHeading());

            double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
            double turn = 0.8 * (-1.0 / 80.0) * angleDifference;

            drive.setSpeed((rightOutput /*- turn*/), -(leftOutput /*+ turn*/));
            SmartDashboard.putNumber("Right Drive", rightOutput);
            SmartDashboard.putNumber("Left Drive", leftOutput);
        }
    };
    public Notifier notifier = new Notifier(run);

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
        left = new EncoderFollower(modifier.getLeftTrajectory());
        right = new EncoderFollower(modifier.getRightTrajectory());
        left.configureEncoder(-drive.getLeftEncoder(), 4096, .1524);
        right.configureEncoder(drive.getRightEncoder(), 4096, .1524);
        left.configurePIDVA(Constants.kLeftTrajectoryP, Constants.kLeftTrajectoryI, Constants.kLeftTrajectoryD,
        Constants.kTrajectoryVelocityRatio, Constants.kLeftTrajectoryAccelerationGain);
        right.configurePIDVA(Constants.kRightTrajectoryP, Constants.kRightTrajectoryI, Constants.kRightTrajectoryD,
        Constants.kTrajectoryVelocityRatio, Constants.kRightTrajectoryAccelerationGain);
        notifier.startPeriodic(.05);
    }

    /**
     * Called when the command ended peacefully. This is where you may want to wrap up loose ends,
     * like shutting off a motor that was being used in the command.
     */
    @Override
    protected void end() {
        drive.setSpeed(0,0);
    }

    /**
     * The execute method is called repeatedly until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {


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
        if (right.isFinished() || left.isFinished()) {
            return true;
        }
        return false;
    }
}
