package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.Constants.ButtonPanelConstants;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.drivetrain.AlgeaoutrunCmd;
import frc.robot.commands.drivetrain.AlgeaoutrunbwdCmd;
import frc.robot.commands.drivetrain.ArcadeDriveCmd;
//import frc.robot.commands.drivetrain.ConveyorCmd;
import frc.robot.commands.drivetrain.LintakeinCmd;
import frc.robot.commands.drivetrain.LintakeoutCmd;
import frc.robot.commands.drivetrain.LintakeoutrunCmd;
import frc.robot.commands.drivetrain.LockCmd;
import frc.robot.commands.drivetrain.Lvl0Cmd;
import frc.robot.commands.drivetrain.Lvl1Cmd;
import frc.robot.commands.drivetrain.Lvl2Cmd;
import frc.robot.commands.drivetrain.Lvl3Cmd;
import frc.robot.commands.drivetrain.Lvl4Cmd;
import frc.robot.commands.drivetrain.ReleaseCoralCmd;
import frc.robot.commands.drivetrain.RintakeinCmd;
import frc.robot.commands.drivetrain.RintakeoutCmd;
import frc.robot.commands.drivetrain.RintakeoutrunCmd;
import frc.robot.commands.drivetrain.TurnToHeadingCmd;
import frc.robot.subsystems.ConveyorSys;
import frc.robot.subsystems.EndEffectorSys;
import frc.robot.subsystems.IntakeSys;
import frc.robot.subsystems.LiftSys;
import frc.robot.subsystems.SwerveSys;

public class RobotContainer {
    
    // Initialize subsystems.
    private final SwerveSys swerveSys = new SwerveSys();
    private final LiftSys liftSys = new LiftSys();
    private final EndEffectorSys endEffectorSys = new EndEffectorSys();
    private final ConveyorSys conveyorSys = new ConveyorSys();
    private final IntakeSys intakeSys = new IntakeSys();

    //Initialize joysticks.
    public final static CommandXboxController driverController = new CommandXboxController(ControllerConstants.driverGamepadPort);
    public final static CommandXboxController operatorController = new CommandXboxController(ControllerConstants.operatorGamepadPort);
    public final static Joystick ButtonPanel = new Joystick(ControllerConstants.operatorGamepadPort);
    

    //Name Commands
    private final Lvl0Cmd lvl0Cmd;
    private final Lvl1Cmd lvl1Cmd;
    private final Lvl2Cmd lvl2Cmd;
    private final Lvl3Cmd lvl3Cmd;
    private final Lvl4Cmd lvl4Cmd;
    private final ReleaseCoralCmd releaseCoralCmd;
    //private final ConveyorCmd conveyorCmd;
    private final LintakeoutCmd lintakeoutCmd;
    private final RintakeoutCmd rintakeoutCmd;
    private final RintakeoutrunCmd rintakeoutrunCmd;
    private final LintakeoutrunCmd lintakeoutrunCmd;
    private final AlgeaoutrunCmd algeaoutrunCmd;
    private final AlgeaoutrunbwdCmd algeaoutrunbwdCmd;

    //Initialize auto selector.
    SendableChooser<Command> autoSelector = new SendableChooser<Command>();

    private UsbCamera camera;

    public RobotContainer() {
        RobotController.setBrownoutVoltage(DriveConstants.brownoutVoltage);
        
        CameraServer.startAutomaticCapture();

        //Initalize Commands
        lvl0Cmd = new Lvl0Cmd(liftSys);
        lvl1Cmd = new Lvl1Cmd(liftSys);
        lvl2Cmd = new Lvl2Cmd(liftSys, endEffectorSys);
        lvl3Cmd = new Lvl3Cmd(liftSys);
        lvl4Cmd = new Lvl4Cmd(liftSys);
        releaseCoralCmd = new ReleaseCoralCmd(endEffectorSys);
        //conveyorCmd = new ConveyorCmd(conveyorSys);
        lintakeoutCmd = new LintakeoutCmd(intakeSys);
        rintakeoutCmd = new RintakeoutCmd(intakeSys);
        rintakeoutrunCmd = new RintakeoutrunCmd(intakeSys);
        lintakeoutrunCmd = new LintakeoutrunCmd(intakeSys);
        algeaoutrunCmd = new AlgeaoutrunCmd(intakeSys);
        algeaoutrunbwdCmd = new AlgeaoutrunbwdCmd(intakeSys);

        //Add Requirements
        lvl0Cmd.addRequirements(liftSys);
        lvl1Cmd.addRequirements(liftSys);
        lvl2Cmd.addRequirements(liftSys);
        lvl3Cmd.addRequirements(liftSys);
        lvl4Cmd.addRequirements(liftSys);
        releaseCoralCmd.addRequirements(endEffectorSys);
        //conveyorCmd.addRequirements(conveyorSys);
        lintakeoutCmd.addRequirements(intakeSys);
        lintakeoutrunCmd.addRequirements(intakeSys);
        rintakeoutCmd.addRequirements(intakeSys);
        rintakeoutrunCmd.addRequirements(intakeSys);
        algeaoutrunCmd.addRequirements(intakeSys);
        algeaoutrunbwdCmd.addRequirements(intakeSys);
            
        //Register Commands to PathPlanner
        NamedCommands.registerCommand("lvl4", new Lvl4Cmd(liftSys));
        NamedCommands.registerCommand("lvl3", new Lvl3Cmd(liftSys));
        NamedCommands.registerCommand("lvl2", new Lvl2Cmd(liftSys, endEffectorSys));
        NamedCommands.registerCommand("lvl1", new Lvl1Cmd(liftSys));
        NamedCommands.registerCommand("lvl0", new Lvl0Cmd(liftSys));
        NamedCommands.registerCommand("releaseCoral", new ReleaseCoralCmd(endEffectorSys));
        //NamedCommands.registerCommand("conveyor", new ConveyorCmd(conveyorSys));
        NamedCommands.registerCommand("Lintakeout", new LintakeoutCmd(intakeSys));
        NamedCommands.registerCommand("Lintakeoutrun", new LintakeoutrunCmd(intakeSys));
        NamedCommands.registerCommand("Rintakeout", new RintakeoutCmd(intakeSys));
        NamedCommands.registerCommand("Rintakeoutrun", new RintakeoutrunCmd(intakeSys));
        NamedCommands.registerCommand("Rintakein", new RintakeinCmd(intakeSys));
        NamedCommands.registerCommand("Lintakein", new LintakeinCmd(intakeSys));

        // Build an auto chooser. This will use Commands.none() as the default option.
        autoSelector = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("auto selector", autoSelector);
        
        configDriverBindings();
        configButtonPanel();

    }

    private void configButtonPanel() {
        JoystickButton lvl4ReefRight = new JoystickButton(ButtonPanel, ButtonPanelConstants.lvl4ReefRightPort);
        JoystickButton lvl3ReefRight = new JoystickButton(ButtonPanel,  ButtonPanelConstants.lvl3ReefRightPort);
        JoystickButton lvl2ReefRight = new JoystickButton(ButtonPanel,  ButtonPanelConstants.lvl2ReefRightPort);
        JoystickButton lvl1ReefRight = new JoystickButton(ButtonPanel,  ButtonPanelConstants.lvl1ReefRightPort);
        JoystickButton algeaIntake = new JoystickButton(ButtonPanel, ButtonPanelConstants.lvl4ReefLeftPort);
        JoystickButton algeaExtake = new JoystickButton(ButtonPanel, ButtonPanelConstants.lvl3ReefLeftPort);
        JoystickButton releaseCoral = new JoystickButton(ButtonPanel,  ButtonPanelConstants.releaseCoralPort);
        JoystickButton conveyorRun = new JoystickButton(ButtonPanel, ButtonPanelConstants.conveyorRunPort);

        lvl4ReefRight.whileTrue(new Lvl4Cmd(liftSys));
        lvl3ReefRight.whileTrue(new Lvl3Cmd(liftSys));
        lvl2ReefRight.whileTrue(new Lvl2Cmd(liftSys, endEffectorSys));
        lvl1ReefRight.whileTrue(new Lvl1Cmd(liftSys));
        releaseCoral.whileTrue(new ReleaseCoralCmd(endEffectorSys));

        //conveyorRun.whileTrue(new ConveyorCmd(conveyorSys));

    }

    public void configDriverBindings() {
        swerveSys.setDefaultCommand(new ArcadeDriveCmd(
            () -> MathUtil.applyDeadband(driverController.getLeftY(), ControllerConstants.joystickDeadband),
            () -> MathUtil.applyDeadband(driverController.getLeftX(), ControllerConstants.joystickDeadband),
            () -> MathUtil.applyDeadband(driverController.getRightX(), ControllerConstants.joystickDeadband),
            true,
            true,
            swerveSys));

        driverController.start().onTrue(Commands.runOnce(() -> swerveSys.resetHeading()));

        //Swerve locking system
        driverController.axisGreaterThan(XboxController.Axis.kLeftTrigger.value, ControllerConstants.triggerPressedThreshhold)
           .whileTrue(new LockCmd(swerveSys));
    

        // Add this line to print CANCoder values when pressing back button
        driverController.back().onTrue(Commands.runOnce(() -> swerveSys.printCanCoderAbsolutePositions()));

    }

    public Command getAutonomousCommand() {
        return autoSelector.getSelected();
    } 

    // For uniformity, any information sent to Shuffleboard/SmartDashboard should go here.
    public void updateInterface() {
        
        SmartDashboard.putNumber("heading degrees", swerveSys.getHeading().getDegrees());
        SmartDashboard.putNumber("speed m/s", swerveSys.getAverageDriveVelocityMetersPerSec());

        SmartDashboard.putNumber("pose x meters", swerveSys.getPose().getX());
        SmartDashboard.putNumber("pose y meters", swerveSys.getPose().getY());

        SmartDashboard.putNumber("blue pose x meters", swerveSys.getBlueSidePose().getX());

        SmartDashboard.putNumber("FL MODSTATE angle degrees", swerveSys.getModuleStates()[0].angle.getDegrees());
        SmartDashboard.putNumber("FR MODSTATE angle degrees", swerveSys.getModuleStates()[1].angle.getDegrees());
        SmartDashboard.putNumber("BL MODSTATE angle degrees", swerveSys.getModuleStates()[2].angle.getDegrees());
        SmartDashboard.putNumber("BR MODSTATE angle degrees", swerveSys.getModuleStates()[3].angle.getDegrees());

        SmartDashboard.putNumber("FL RAW CANCoder degrees", swerveSys.getCanCoderAngles()[0].getDegrees());
        SmartDashboard.putNumber("FR RAW CANCoder degrees", swerveSys.getCanCoderAngles()[1].getDegrees());
        SmartDashboard.putNumber("BL RAW CANCoder degrees", swerveSys.getCanCoderAngles()[2].getDegrees());
        SmartDashboard.putNumber("BR RAW CANCoder degrees", swerveSys.getCanCoderAngles()[3].getDegrees());

        SmartDashboard.putNumber("FL OFFSET CANCoder degrees", swerveSys.getCanCoderAngles()[0].getDegrees() - DriveConstants.frontLeftModOffset.getDegrees());
        SmartDashboard.putNumber("FR OFFSET CANCoder degrees", swerveSys.getCanCoderAngles()[1].getDegrees() - DriveConstants.frontRightModOffset.getDegrees());
        SmartDashboard.putNumber("BL OFFSET CANCoder degrees", swerveSys.getCanCoderAngles()[2].getDegrees() - DriveConstants.backLeftModOffset.getDegrees());
        SmartDashboard.putNumber("BR OFFSET CANCoder degrees", swerveSys.getCanCoderAngles()[3].getDegrees() - DriveConstants.backRightModOffset.getDegrees());

        SmartDashboard.putNumber("drive voltage", swerveSys.getAverageDriveVoltage());

    }   
}
