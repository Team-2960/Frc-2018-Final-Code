package org.usfirst.frc.team2960.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.IllegalUseOfCommandException;
import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team2960.robot.Subsytems.Elevator;

public class ElevatorMove extends Command{

    Elevator elevator = Elevator.getInstance();
    private Elevator.mElevatorState state;

    public ElevatorMove(Elevator.mElevatorState state) {
        this.state = state;
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
        return false;
    }

    /**
     * The initialize method is called the first time this Command is run after being started.
     */
    @Override
    protected void initialize() {

    }

    /**
     * The execute method is called repeatedly until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        elevator.setState(state, 0);
    }

    /**
     * Called when the command ended peacefully. This is where you may want to wrap up loose ends,
     * like shutting off a motor that was being used in the command.
     */
    @Override
    protected void end() {

    }

    /**
     * Called when the command ends because somebody called {@link Command#cancel() cancel()} or
     * another command shared the same requirements as this one, and booted it out.
     * <p>
     * <p>This is where you may want to wrap up loose ends, like shutting off a motor that was being
     * used in the command.
     * <p>
     * <p>Generally, it is useful to simply call the {@link Command#end() end()} method within this
     * method, as done here.
     */
    @Override
    protected void interrupted() {

    }

    /**
     * Starts up the command. Gets the command ready to start. <p> Note that the command will
     * eventually start, however it will not necessarily do so immediately, and may in fact be
     * canceled before initialize is even called. </p>
     *
     * @throws IllegalUseOfCommandException if the command is a part of a CommandGroup
     */
    @Override
    public synchronized void start() {

    }
}
