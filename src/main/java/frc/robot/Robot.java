package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.drivetrain.Lvl1Cmd;

public class Robot extends TimedRobot {
    
    private RobotContainer robotContainer;
    private Command autonomousCommand;

    // private UsbCamera camera;

    @Override
    public void robotInit() {

        robotContainer = new RobotContainer();

        // camera = new UsbCamera("driver camera", 0);
        
        // CameraServer.startAutomaticCapture(camera);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();

        robotContainer.updateInterface();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = robotContainer.getAutonomousCommand();

        if(autonomousCommand != null) autonomousCommand.schedule();
    }

    @Override
    public void teleopInit() {
        if(autonomousCommand != null) autonomousCommand.cancel();
    }

}
