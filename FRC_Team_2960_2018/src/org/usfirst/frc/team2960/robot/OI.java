package org.usfirst.frc.team2960.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2960.robot.Subsytems.*;

public class OI {

    private Drive drive = Drive.getInstance();
    private Intake intake = Intake.getInstance();
    private Winch winch = Winch.getInstance();
    private Elevator elevator = Elevator.getInstance();
    private boolean intakeOperatorOverride = false;

    public void driveRobot(Joystick joystick) {
        drive.setSpeed(-joystick.getRawAxis(5), joystick.getRawAxis(1));

        if (joystick.getRawButton(1)) {
            drive.zeroSensors();
            elevator.zeroSensors();
        }
        if(!intakeOperatorOverride) {
            if (joystick.getRawButton(6)) {
                intake.setIntakeState(Intake.mIntakeState.forward);
            } else if (joystick.getRawButton(5)) {
                intake.setIntakeState(Intake.mIntakeState.backward);
            } else {
                intake.setIntakeState(Intake.mIntakeState.stop);
            }
            if (joystick.getRawButton(2)) {
                intake.setIntakeState(Intake.mIntakeState.rotate);
            }
        }

    }


    public void operateRobot(Joystick joystick) {
        double range = 0;
        //Elevator
        if(joystick.getRawButton(16)){
            elevator.testElevator(joystick.getRawAxis(1));
        }
        else {
            if(joystick.getRawButton(1)){
                range = (Constants.kElevatorGround - Constants.kElevatorSwitch);
                range = range * (joystick.getRawAxis(1));
                elevator.setState(Elevator.mElevatorState.Ground, range);
            }
            else if(joystick.getRawButton(3)){
                range = (Constants.kElevatorScaleDown - Constants.kElevatorSwitch);
                range = range * (joystick.getRawAxis(1));
                elevator.setState(Elevator.mElevatorState.Switch, range);
            }
            else if(joystick.getRawButton(5)){
                range = (Constants.kElevatorScaleBalanced - Constants.kElevatorScaleDown);
                range = range * (joystick.getRawAxis(1));
                elevator.setState(Elevator.mElevatorState.ScaleDown, range);
            }
            else if(joystick.getRawButton(7)){
                range = (Constants.kElevatorScaleUp - Constants.kElevatorScaleBalanced);
                range = range * (joystick.getRawAxis(1));
                elevator.setState(Elevator.mElevatorState.ScaleBalanced, range);
            }
            else if(joystick.getRawButton(9)){
                range = 0;
                elevator.setState(Elevator.mElevatorState.ScaleUp, range);
            }
        }

        //Intake
        if(joystick.getRawButton(14)){
            intake.setIntakeState(Intake.mIntakeState.forward);
            intakeOperatorOverride = true;
        }
        else if(joystick.getRawButton(13)){
            intake.setIntakeState(Intake.mIntakeState.backward);
            intakeOperatorOverride = false;
        }
        else {
            intake.setIntakeState(Intake.mIntakeState.stop);
            intakeOperatorOverride = true;
        }


        //Winch
        if(joystick.getRawButton(15)){
            winch.setWinchState(Winch.mWinchState.winchUp);
        }
        else
            winch.setWinchState(Winch.mWinchState.winchStop);

        //Hook
        if(joystick.getRawButton(12)){
            winch.setHookState(Winch.mHookState.hookDeployment);
        }
        else if(joystick.getRawButton(11)){
            winch.setHookState(Winch.mHookState.hookDeploymentbackword);
        }
        else
            winch.setHookState(Winch.mHookState.hookDeploymentStop);

        /*
        //Intake
        //if(joystick.getRawButton(1)) {
            // TODO: 2/20/18 Change back for testing

            //elevator.setState(Elevator.mElevatorState.Switch);

        //}
        //if (joystick.getRawButton(2)) {


           // elevator.testElevator(joystick.getRawAxis(1));


        //}
        //else {
            //elevator.testElevator(0);
        //}
        //else {
            //intake.setIntakeState(Intake.mIntakeState.stop);
        //}

        if(joystick.getRawButton(1)) {
            intake.setIntakeState(Intake.mIntakeState.rotate);
        }
        //Winch
        if(joystick.getRawButton(3)) {
            winch.setWinchState(Winch.mWinchState.winchUp);
        }
        //else if (joystick.getRawButton(6) && joystick.getRawButton(7) && joystick.getRawButton(8)) {
            //winch.setWinchState(Winch.mWinchState.winchDown);
        //}
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

        if (joystick.getRawButton(7)) {
            elevator.zeroSensors();
        }

      //Elevator
        if(joystick.getRawButton(1)) {
            elevator.setState(Elevator.mElevatorState.Ground);
        }
        if (joystick.getRawButton(2)) {
            elevator.setState(Elevator.mElevatorState.Switch);
        }
        if (joystick.getRawButton(3)) {
            elevator.setState(Elevator.mElevatorState.ScaleDown);
        }
        if(joystick.getRawButton(4)) {
            elevator.setState(Elevator.mElevatorState.ScaleBalanced);
        }
        if(joystick.getRawButton(5)) {
            elevator.setState(Elevator.mElevatorState.ScaleUp);
        }


        if(joystick.getRawButton(6)) {
            elevator.testElevator(joystick.getRawAxis(1));
        }
        */
    }

}
