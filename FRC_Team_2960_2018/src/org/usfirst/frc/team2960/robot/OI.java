package org.usfirst.frc.team2960.robot;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team2960.robot.Subsytems.Drive;
import org.usfirst.frc.team2960.robot.Subsytems.Elevator;
import org.usfirst.frc.team2960.robot.Subsytems.Intake;
import org.usfirst.frc.team2960.robot.Subsytems.Winch;

public class OI {

    private Drive drive = Drive.getInstance();
    private Intake intake = Intake.getInstance();
    private Winch winch = Winch.getInstance();
    private Elevator elevator = Elevator.getInstance();


    public void driveRobot(Joystick joystick) {
        drive.setSpeed(-joystick.getRawAxis(5), joystick.getRawAxis(1));

        if(joystick.getRawButton(2)) {
            drive.zeroSensors();
        }


    }

    public void operateRobot(Joystick joystick) {
        //Intake
        if(joystick.getRawButton(1)) {
            intake.setIntakeState(Intake.mIntakeState.forward);
        }
        else if (joystick.getRawButton(2)) {
            intake.setIntakeState(Intake.mIntakeState.backward);
        }
        else {
            intake.setIntakeState(Intake.mIntakeState.stop);
        }
        //Winch
        if(joystick.getRawButton(3)) {
            winch.setWinchState(Winch.mWinchState.winchUp);
        }
        else {
            winch.setWinchState(Winch.mWinchState.winchStop);
        }

        if(joystick.getRawButton(4)) {
            winch.setWinchState(Winch.mWinchState.hookDeployment);
        }
        //Elevator
        if(joystick.getRawButton(5)) {
            elevator.testElevator(joystick.getRawAxis(1));
        }

        //else if(joystick.getRawButton(6)){
        //    elevator.testElevator(2.0);
        //}
        //else if(joystick.getRawButton(7)){
        //    elevator.testElevator(0.0);
        //}

    }

}
