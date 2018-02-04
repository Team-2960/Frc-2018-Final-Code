package org.usfirst.frc.team2960.robot.Subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2960.robot.Constants;



/**
 * Class for controlling the drive motors on our robot
 * @author Malcolm Machesky
 *
 * Includes 6 mRightMasteron SRX's for controlling the drive train through a WCP SS Gearbox
 *
 */
public class Drive extends Subsystem implements SubsystemBase {

    private static Drive m_Instance;

    /**
     * The NavX private instance
     */
    private AHRS navX;

    // Talons
    // TODO: 1/17/18 The Talons have to be changed for when we move to the new drivetrain!
    private TalonSRX mRightMaster, mRightSlave1, mRightSlave2, mLeftMaster, mLeftSlave1, mLeftSlave2;

    /**
     * Ultrasonic Sensors, all but front are vex sensors
     */
    private Ultrasonic mUltraRight1, mUltraRight2, mUltraLeft1, mUltraLeft2;
    private AnalogInput mUltraFront;

    /**
     * The array for the Ultrasonic Sensors
     */
    private Ultrasonic[] mUltrasonics;
    /**
     * Private constructor for Drive Class
     */
    private Drive() {
        //Talons
        setupTalons();
        //NavX
        navX = new AHRS(SPI.Port.kMXP);

        //Ultrasonic setup
        mUltraRight1 = new Ultrasonic(Constants.mUltrasonicRight1Out, Constants.mUltrasonicRight1In);
        mUltraRight1.setAutomaticMode(true);
        mUltraRight2 = new Ultrasonic(Constants.mUltrasonicRight2Out, Constants.mUltrasonicRight2In);
        mUltraRight2.setAutomaticMode(true);
        mUltraLeft1 = new Ultrasonic(Constants.mUltrasonicLeft1Out, Constants.mUltrasonicLeft1In);
        mUltraLeft1.setAutomaticMode(true);
        mUltraLeft2 = new Ultrasonic(Constants.mUltrasonicLeft2Out, Constants.mUltrasonicLeft2In);
        mUltraLeft2.setAutomaticMode(true);
        mUltrasonics = new Ultrasonic[]{mUltraRight1, mUltraRight2, mUltraLeft1, mUltraLeft2};
        mUltraFront = new AnalogInput(Constants.mUltrasonicFront);





    }

    /**
     * Initialize the default command for a subsystem By default subsystems have no default command,
     * but if they do, the default command is set with this method. It is called on all Subsystems by
     * CommandBase in the users program after all the Subsystems are created.
     */
    @Override
    protected void initDefaultCommand() {

    }

    /**
     * Method to setup settings on the 6 mRightMaster talons
     */
    private void setupTalons() {
        //Right Master
        mRightMaster = new TalonSRX(Constants.mRightMasterId);

        //Right Slaves
        mRightSlave1 = new TalonSRX(Constants.mRightSlave1Id);
        mRightSlave1.follow(mRightMaster);

        mRightSlave2 = new TalonSRX(Constants.mRightSlave2Id);
        mRightSlave2.follow(mRightMaster);

        //Left Master
        mLeftMaster = new TalonSRX(Constants.mLeftMasterId);

        //Left Slave
        mLeftSlave1 = new TalonSRX(Constants.mLeftSlave1Id);
        mLeftSlave1.follow(mLeftMaster);

        mLeftSlave2 = new TalonSRX(Constants.mLeftSlave2Id);
        mLeftSlave2.follow(mLeftMaster);
    }

    public void setSpeed(double right, double left){
        mRightMaster.set(ControlMode.PercentOutput, right);
        mLeftMaster.set(ControlMode.PercentOutput, left);
    }

    /**
     * Function to get the private instance of Drive
     * @return Singleton of the drive class
     */
    public static Drive getInstance()  {
        if (m_Instance == null) {
            m_Instance = new Drive();
        }
        return m_Instance;
    }

    /**
     * Updates the Subsystem
     */
    @Override
    public void update() {

    }

    /**
     * Startup code for the Subsystem
     */
    @Override
    public void startup() {

    }

    /**
     * Output the Subsystems Values to SmartDashboard
     */
    @Override
    public void toSmartDashboard() {
        /*
        SmartDashboard.putNumber("SensorVelRight", mRightMaster.getSelectedSensorVelocity(Constants.kPIDLoopIDx));
        SmartDashboard.putNumber("SensorPosRight",  mRightMaster.getSelectedSensorPosition(Constants.kPIDLoopIDx));
        SmartDashboard.putNumber("MotorOutputPercentRight", mRightMaster.getMotorOutputPercent());
        SmartDashboard.putNumber("ClosedLoopErrorRight", mRightMaster.getClosedLoopError(Constants.kPIDLoopIDx));

        SmartDashboard.putNumber("SensorVelLeft", mLeftMaster.getSelectedSensorVelocity(Constants.kPIDLoopIDx));
        SmartDashboard.putNumber("SensorPosLeft",  mLeftMaster.getSelectedSensorPosition(Constants.kPIDLoopIDx));
        SmartDashboard.putNumber("MotorOutputPercentLeft", mLeftMaster.getMotorOutputPercent());
        SmartDashboard.putNumber("ClosedLoopErrorLeft", mLeftMaster.getClosedLoopError(Constants.kPIDLoopIDx));
        */

        //for(Ultrasonic ultra: mUltrasonics){
            //SmartDashboard.putNumber("Ultra Value: ", mUltraRight1.getRangeInches());
        //}
        SmartDashboard.putNumber("Ultra Value: analog ultra", mUltraFront.getValue());
    }

    /**
     * Stops all tasks in the Subsystem
     */
    @Override
    public void stop() {

    }

    /**
     * Zero all Sensors in the Subsystem
     */
    @Override
    public void zeroSensors() {

    }
}
