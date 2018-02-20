package org.usfirst.frc.team2960.robot;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team2960.robot.Subsytems.*;

public class OI {

    private Drive drive = Drive.getInstance();
    private Intake intake = Intake.getInstance();
    private Winch winch = Winch.getInstance();
    private Elevator elevator = Elevator.getInstance();


    public void driveRobot(Joystick joystick) {
        drive.setSpeed(-joystick.getRawAxis(5), joystick.getRawAxis(1));

        if (joystick.getRawButton(1)) {
            drive.zeroSensors();
            elevator.zeroSensors();
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
        else if (joystick.getRawButton(6) && joystick.getRawButton(7) && joystick.getRawButton(8)) {
            winch.setWinchState(Winch.mWinchState.winchDown);
        }
        else {
            winch.setWinchState(Winch.mWinchState.winchStop);
        }
        //Hook
        if(joystick.getRawButton(4)) {
            winch.setHookState(Winch.mHookState.hookDeployment);
        }else if (joystick.getRawButton(5)){
            winch.setHookState(Winch.mHookState.hookDeploymentbackword);
        } else {
            winch.setHookState(Winch.mHookState.hookDeploymentStop);
        }
        //Elevator
        if (joystick.getRawAxis(1) >= 1) {
            elevator.testElevator(joystick.getRawAxis(1));
        }
        else {
            elevator.testElevator(joystick.getRawAxis(1));
        }

        if (joystick.getRawButton(1)) {
            elevator.zeroSensors();
        }


    }

}
