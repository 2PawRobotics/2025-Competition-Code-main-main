package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.Constants.ButtonPanelConstants;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.drivetrain.ArcadeDriveCmd;
import frc.robot.commands.drivetrain.ConveyorCmd;
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
import frc.robot.commands.drivetrain.AimToSpeakerCmd;
import frc.robot.commands.lights.LightsDefaultCmd;
import frc.robot.commands.lights.PartyModeCmd;
import frc.robot.subsystems.ConveyorSys;
import frc.robot.subsystems.EndEffectorSys;
import frc.robot.subsystems.IntakeSys;
import frc.robot.subsystems.LiftSys;
import frc.robot.subsystems.LightsSys;
import frc.robot.subsystems.SwerveSys;
import frc.robot.util.limelight.LimelightPoseEstimator;

public class RobotContainer {
    
    // Initialize subsystems.
    private final SwerveSys swerveSys = new SwerveSys();
    private final LiftSys liftSys = new LiftSys();
    private final LightsSys lightsSys = new LightsSys();
    private final EndEffectorSys endEffectorSys = new EndEffectorSys();
    private final ConveyorSys conveyorSys = new ConveyorSys();
    private final IntakeSys intakeSys = new IntakeSys();

    //Initialize joysticks.
    public final static CommandXboxController driverController = new CommandXboxController(ControllerConstants.driverGamepadPort);
    public final static CommandXboxController operatorController = new CommandXboxController(3);
    public final static Joystick ButtonPanel = new Joystick(3);
    

    //Name Commands
    private final Lvl0Cmd lvl0Cmd;
    private final Lvl1Cmd lvl1Cmd;
    private final Lvl2Cmd lvl2Cmd;
    private final Lvl3Cmd lvl3Cmd;
    private final Lvl4Cmd lvl4Cmd;
    private final ReleaseCoralCmd releaseCoralCmd;
    private final ConveyorCmd conveyorCmd;
    private final LintakeoutCmd lintakeoutCmd;
    private final RintakeoutCmd rintakeoutCmd;
    private final RintakeoutrunCmd rintakeoutrunCmd;
    private final LintakeoutrunCmd lintakeoutrunCmd;

    //Initialize auto selector.
    SendableChooser<Command> autoSelector = new SendableChooser<Command>();

    public RobotContainer() {
        RobotController.setBrownoutVoltage(DriveConstants.brownoutVoltage);

        SmartDashboard.putData("auto selector", autoSelector);

        //Initalize Commands
        lvl0Cmd = new Lvl0Cmd(liftSys);
        lvl1Cmd = new Lvl1Cmd(liftSys);
        lvl2Cmd = new Lvl2Cmd(liftSys);
        lvl3Cmd = new Lvl3Cmd(liftSys);
        lvl4Cmd = new Lvl4Cmd(liftSys);
        releaseCoralCmd = new ReleaseCoralCmd(endEffectorSys);
        conveyorCmd = new ConveyorCmd(conveyorSys);
        lintakeoutCmd = new LintakeoutCmd(intakeSys);
        rintakeoutCmd = new RintakeoutCmd(intakeSys);
        rintakeoutrunCmd = new RintakeoutrunCmd(intakeSys);
        lintakeoutrunCmd = new LintakeoutrunCmd(intakeSys);

        //Add Requirements
        lvl0Cmd.addRequirements(liftSys);
        lvl1Cmd.addRequirements(liftSys);
        lvl2Cmd.addRequirements(liftSys);
        lvl3Cmd.addRequirements(liftSys);
        lvl4Cmd.addRequirements(liftSys);
        releaseCoralCmd.addRequirements(endEffectorSys);
        conveyorCmd.addRequirements(conveyorSys);
        lintakeoutCmd.addRequirements(intakeSys);
        lintakeoutrunCmd.addRequirements(intakeSys);
        rintakeoutCmd.addRequirements(intakeSys);
        rintakeoutrunCmd.addRequirements(intakeSys);
            
        //Register Commands to PathPlanner
        NamedCommands.registerCommand("lvl4", new Lvl4Cmd(liftSys));
        NamedCommands.registerCommand("lvl3", new Lvl3Cmd(liftSys));
        NamedCommands.registerCommand("lvl2", new Lvl2Cmd(liftSys));
        NamedCommands.registerCommand("lvl1", new Lvl1Cmd(liftSys));
        NamedCommands.registerCommand("lvl0", new Lvl0Cmd(liftSys));
        NamedCommands.registerCommand("releaseCoral", new ReleaseCoralCmd(endEffectorSys));
        NamedCommands.registerCommand("conveyor", new ConveyorCmd(conveyorSys));
        NamedCommands.registerCommand("Lintakeout", new LintakeoutCmd(intakeSys));
        NamedCommands.registerCommand("Lintakeoutrun", new LintakeoutrunCmd(intakeSys));
        NamedCommands.registerCommand("Rintakeout", new RintakeoutCmd(intakeSys));
        NamedCommands.registerCommand("Rintakeoutrun", new RintakeoutrunCmd(intakeSys));
        NamedCommands.registerCommand("Rintakein", new RintakeinCmd(intakeSys));
        NamedCommands.registerCommand("Lintakein", new LintakeinCmd(intakeSys));

        configDriverBindings();
        configOperatorBindings();
        configButtonPanel();

        //lightsSys.setDefaultCommand(new LightsDefaultCmd(lightsSys, rollerSys::hasNote));
    }

    private void configButtonPanel() {
        JoystickButton lvl4ReefRight = new JoystickButton(ButtonPanel, ButtonPanelConstants.lvl4ReefRightPort);
        JoystickButton lvl4ReeLeft = new JoystickButton(ButtonPanel,  ButtonPanelConstants.lvl4ReefLeftPort);
        JoystickButton lvl3ReefRight = new JoystickButton(ButtonPanel,  ButtonPanelConstants.lvl3ReefRightPort);
        JoystickButton lvl3ReefLeft = new JoystickButton(ButtonPanel,  ButtonPanelConstants.lvl3ReefLeftPort);
        JoystickButton lvl2ReefRight = new JoystickButton(ButtonPanel,  ButtonPanelConstants.lvl2ReefRightPort);
        JoystickButton lvl2ReefLeft = new JoystickButton(ButtonPanel,  ButtonPanelConstants.lvl2ReefLeftPort);
        JoystickButton lvl1ReefRight = new JoystickButton(ButtonPanel,  ButtonPanelConstants.lvl1ReefRightPort);
        JoystickButton lvl1ReefLeft = new JoystickButton(ButtonPanel, ButtonPanelConstants.lvl1ReefLeftPort);
        JoystickButton releaseCoral = new JoystickButton(ButtonPanel,  ButtonPanelConstants.releaseCoralPort);
        JoystickButton conveyorControl = new JoystickButton(ButtonPanel,  ButtonPanelConstants.conveyorControlPort);
        JoystickButton conveyorRun = new JoystickButton(ButtonPanel, ButtonPanelConstants.conveyorRunPort);
        Joystick joystick = new Joystick(ButtonPanelConstants.joystickPort);


        lvl4ReefRight.whileTrue(new Lvl4Cmd(liftSys));
        lvl3ReefRight.whileTrue(new Lvl3Cmd(liftSys));
        lvl2ReefRight.whileTrue(new Lvl2Cmd(liftSys));
        lvl1ReefRight.whileTrue(new Lvl1Cmd(liftSys));
        releaseCoral.toggleOnTrue(new ReleaseCoralCmd(endEffectorSys));
        conveyorRun.whileTrue(new ConveyorCmd(conveyorSys));
        
        
    }



    private void configOperatorBindings() {
        // rollerSys.setDefaultCommand(new RollersManualCmd(
        //     () -> (operatorController.getRightTriggerAxis() * RollerConstants.manualFirePower) - 
        //           (operatorController.getLeftTriggerAxis() * RollerConstants.manualIntakePower),
        //     rollerSys));

        /*pivotSys.setDefaultCommand(new PivotManualCmd( 
            () -> MathUtil.applyDeadband((operatorController.getLeftY()), ControllerConstants.joystickDeadband),
            pivotSys));*/

        //operatorController.axisGreaterThan(XboxController.Axis.kLeftTrigger.value, ControllerConstants.triggerPressedThreshhold).onFalse(new RollersStopCmd(rollerSys));

        //operatorController.leftBumper().onTrue(new FeederInCmd(feederSys)).onFalse(new FeederStopCmd(feederSys));

        //operatorController.axisGreaterThan(XboxController.Axis.kRightTrigger.value, ControllerConstants.triggerPressedThreshhold).onFalse(new RollersStopCmd(rollerSys));

        //operatorController.rightBumper().onTrue(new FeederFeedCmd(feederSys)).onFalse(new FeederStopCmd(feederSys));

        //operatorController.a().onTrue(new AutoAmpFireCmd(feederSys, rollerSys, pivotSys, spacebarSys));
        
        /*
        operatorController.b()
            .onTrue(new AutoSpeakerFireCmd(feederSys, rollerSys, pivotSys, swerveSys))
            .onFalse(new RollersStopCmd(rollerSys))
            .onFalse(new PivotHomePresetCmd(pivotSys))
            .onFalse(new FeederStopCmd(feederSys));
        */

        //operatorController.x().onTrue(new PivotHomePresetCmd(pivotSys));

        //operatorController.y().onTrue(new AutoSubwooferFireCmd(feederSys, rollerSys, pivotSys));
      
        //operatorController.povUp().onTrue(new ClimberUpCmd(climberSys)).onFalse(new ClimberStopCmd(climberSys));
        
        //operatorController.povRight().onTrue(new AutoSourceIntakeCmd(pivotSys, feederSys, rollerSys)).onFalse(new AutoAllHomeCmd(pivotSys, feederSys, rollerSys));

        //operatorController.povDown().onTrue(new ClimberDownCmd(climberSys)).onFalse(new ClimberStopCmd(climberSys));
        
        //operatorController.povLeft().onTrue(new AutoAmpHoldUpCmd(pivotSys, spacebarSys)).onFalse(new AutoAmpHoldDownCmd(pivotSys, spacebarSys));
        

        /*operatorController.axisGreaterThan(XboxController.Axis.kRightTrigger.value, ControllerConstants.triggerPressedThreshhold)
            .onTrue(new RollersFireCmd(rollerSys))
            .onFalse(new RollersStopCmd(rollerSys));
        */
        /*operatorController.axisGreaterThan(XboxController.Axis.kLeftTrigger.value, ControllerConstants.triggerPressedThreshhold)
            .onTrue(new RollersIntakeCmd(rollerSys))
            .onFalse(new RollersStopCmd(rollerSys));
        */
        operatorController.start().toggleOnTrue(new PartyModeCmd(lightsSys));

         //Servo spacebar
        // operatorController.povDown().onTrue(new ServoSpacebarHomeCmd(servoSpacebarSys));

        // operatorController.povLeft().onTrue(new ServoSpacebarOutCmd(servoSpacebarSys));


        // For spacebar testin and troubleshootin
        
       //operatorController.povLeft().onTrue(new SpacebarOutCmd(spacebarSys));
       //operatorController.povDown().onTrue(new SpacebarHomeCmd(spacebarSys));

        // For Pivot testin and troubleshootin

        // operatorController.b().onTrue(new PivotPodiumPresetCmd(pivotSys));
        // operatorController.a().onTrue(new PivotGroundPresetCmd(pivotSys));
        // operatorController.y().onTrue(new PivotAmpPresetCmd(pivotSys));
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

        //driverController.axisGreaterThan(XboxController.Axis.kLeftTrigger.value, ControllerConstants.triggerPressedThreshhold)
        //    .whileTrue(new LockCmd(swerveSys));
        
        //driverController.axisGreaterThan(XboxController.Axis.kRightTrigger.value, ControllerConstants.triggerPressedThreshhold)
        //  .onTrue(new AutoGroundIntakeCmd(pivotSys, feederSys, rollerSys, spacebarSys)).onFalse(new AutoAllHomeCmd(pivotSys, feederSys, rollerSys));

        /*driverController.leftBumper().onTrue(new AutoFeedCmd(feederSys, rollerSys, pivotSys));

        driverController.rightBumper().onTrue(new AutoSourceIntakeCmd(pivotSys, feederSys, rollerSys)).onFalse(new AutoAllHomeCmd(pivotSys, feederSys, rollerSys));
        */
        
        driverController.a().whileTrue(new TurnToHeadingCmd(Rotation2d.fromDegrees(170), swerveSys));
        driverController.b().whileTrue(new TurnToHeadingCmd(Rotation2d.fromDegrees(120), swerveSys));
        driverController.x().whileTrue(new TurnToHeadingCmd(Rotation2d.fromDegrees(90), swerveSys));
        driverController.y().whileTrue(new AimToSpeakerCmd(swerveSys));

    }

    public Command getAutonomousCommand() {
        return autoSelector.getSelected();
        // return new FollowPathCmd("TEST", swerveSys);
    } 

    // For uniformity, any information sent to Shuffleboard/SmartDashboard should go here.
    public void updateInterface() {
        
        SmartDashboard.putNumber("heading degrees", swerveSys.getHeading().getDegrees());
        SmartDashboard.putNumber("speed m/s", swerveSys.getAverageDriveVelocityMetersPerSec());

        SmartDashboard.putNumber("pose x meters", swerveSys.getPose().getX());
        SmartDashboard.putNumber("pose y meters", swerveSys.getPose().getY());

        SmartDashboard.putNumber("blue pose x meters", swerveSys.getBlueSidePose().getX());

        SmartDashboard.putNumber("FL angle degrees", swerveSys.getModuleStates()[0].angle.getDegrees());
        SmartDashboard.putNumber("FR angle degrees", swerveSys.getModuleStates()[1].angle.getDegrees());
        SmartDashboard.putNumber("BL angle degrees", swerveSys.getModuleStates()[2].angle.getDegrees());
        SmartDashboard.putNumber("BR angle degrees", swerveSys.getModuleStates()[3].angle.getDegrees());

        SmartDashboard.putNumber("FL raw CANCoder degrees", swerveSys.getCanCoderAngles()[0].getDegrees());
        SmartDashboard.putNumber("FR raw CANCoder degrees", swerveSys.getCanCoderAngles()[1].getDegrees());
        SmartDashboard.putNumber("BL raw CANCoder degrees", swerveSys.getCanCoderAngles()[2].getDegrees());
        SmartDashboard.putNumber("BR raw CANCoder degrees", swerveSys.getCanCoderAngles()[3].getDegrees());

        SmartDashboard.putNumber("FL offset CANCoder degrees", swerveSys.getCanCoderAngles()[0].getDegrees() - DriveConstants.frontLeftModOffset.getDegrees());
        SmartDashboard.putNumber("FR offset CANCoder degrees", swerveSys.getCanCoderAngles()[1].getDegrees() - DriveConstants.frontRightModOffset.getDegrees());
        SmartDashboard.putNumber("BL offset CANCoder degrees", swerveSys.getCanCoderAngles()[2].getDegrees() - DriveConstants.backLeftModOffset.getDegrees());
        SmartDashboard.putNumber("BR offset CANCoder degrees", swerveSys.getCanCoderAngles()[3].getDegrees() - DriveConstants.backRightModOffset.getDegrees());

        //SmartDashboard.putNumber("pivot degrees", pivotSys.getCurrentPositionDeg());

        SmartDashboard.putNumber("drive voltage", swerveSys.getAverageDriveVoltage());

        //SmartDashboard.putNumber("roller rpm", rollerSys.getRPM());

        /*SmartDashboard.putNumber("spacebar position degrees", spacebarSys.getCurrentPositionDeg());
        SmartDashboard.putNumber("spacebar position enc counts", spacebarSys.getCurrentPositionEncCounts());
        SmartDashboard.putNumber("spacebar target", spacebarSys.getSpacebarTargetDeg());
        */
    }   
}
