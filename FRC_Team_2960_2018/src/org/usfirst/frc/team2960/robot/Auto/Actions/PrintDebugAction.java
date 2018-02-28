package org.usfirst.frc.team2960.robot.Auto.Actions;

/**
 * Prints a message to the console for debug purposes
 * 
 * @see Action
 * @see RunOnceAction
 */
public class PrintDebugAction extends RunOnceAction implements Action {
    String debugMessage;

    public PrintDebugAction(String s) {
        debugMessage = s;
    }

    @Override
    public void runOnce() {
        System.out.println(debugMessage);
    }

}
