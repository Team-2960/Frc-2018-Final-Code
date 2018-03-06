package org.usfirst.frc.team2960.robot.Pid;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import org.usfirst.frc.team2960.robot.Subsytems.Drive;

public class MovePidInput implements PIDSource {

    private Drive drive;
    private PIDSourceType m_PidSource;

    public MovePidInput(Drive drive){
        this.drive = drive;
    }



    /**
     * Set which parameter of the device you are using as a process control variable.
     *
     * @param pidSource An enum to select the parameter.
     */
    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
        m_PidSource = pidSource;
    }

    /**
     * Get which parameter of the device you are using as a process control variable.
     *
     * @return the currently selected PID source parameter
     */
    @Override
    public PIDSourceType getPIDSourceType() {
        return m_PidSource;
    }

    /**
     * Get the result to use in PIDController.
     *
     * @return the result to use in PIDController
     */
    @Override
    public double pidGet() {
        switch (m_PidSource) {
            case kRate:
                return ((drive.ticksPerHundredMillisecondToInchesPerSecond(drive.getRightEncoderVelocity()) + drive.ticksPerHundredMillisecondToInchesPerSecond(drive.getLeftEncoderVelocity()))/2);
            case kDisplacement:
                return 0;
            default:
                return 0;
        }
    }
}
