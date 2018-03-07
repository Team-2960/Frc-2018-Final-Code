package org.usfirst.frc.team2960.robot.Commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2960.robot.Subsytems.Drive;

public class MoveForwardDistance extends CommandGroup{

    Drive drive = Drive.getInstance();
    private boolean isFinish = false;
    private double distance;
    private double speed;

    public MoveForwardDistance(double distance, double speed){
        this.distance = distance;
        this.speed = speed;
    }
    @Override
    protected void initialize() {
        super.initialize();
    }

    /**
     * Starts up the command. Gets the command ready to start. <p> Note that the command will
     * eventually start, however it will not necessarily do so immediately, and may in fact be
     * canceled before initialize is even called. </p>
     *
     *
     */
    @Override
    public synchronized void start() {
        super.start();
    }


    /**
     * Returns true if all the commands in this group have been started and have
     * finished.
     * <p>
     * <p> Teams may override this method, although they should probably reference super.isFinished()
     * if they do. </p>
     *
     * @return whether this {@link CommandGroup} is finished
     */
    @Override
    protected boolean isFinished() {
        if(isFinish)
            return true;
        else
            return false;
    }



    @Override
    protected void execute() {
        isFinish = drive.moveForward(distance, speed);
    }

    @Override
    protected void end() {
        drive.setSpeed(0,0);
    }



    /**
     * Returns whether or not the command is running. This may return true even if the command has
     * just been canceled, as it may not have yet called
     *
     * @return whether or not the command is running
     */
    @Override
    public synchronized boolean isRunning() {
        return super.isRunning();
    }
}
