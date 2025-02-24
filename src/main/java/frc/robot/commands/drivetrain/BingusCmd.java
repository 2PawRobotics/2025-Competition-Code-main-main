package frc.robot.commands.drivetrain;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.FieldConstants;
import frc.robot.subsystems.SwerveSys;

public class BingusCmd extends Command {

    private final SwerveSys swerveSys;
    private Translation2d targetTranslation;
    private final ProfiledPIDController translationController;
    private final ProfiledPIDController rotationController;
    
    public BingusCmd(SwerveSys swerveSys, Translation2d targetTranslation) {
        this.swerveSys = swerveSys;
        this.targetTranslation = targetTranslation;

        translationController = new ProfiledPIDController(AutoConstants.drivekP, 0, AutoConstants.drivekD,
        new Constraints(AutoConstants.maxAccelMetersPerSec, AutoConstants.maxAccelMetersPerSecSq));
        rotationController = new ProfiledPIDController(AutoConstants.rotkP, 0, AutoConstants.rotkD, 
        new Constraints(AutoConstants.maxRotAccelMetersPerSecSq, AutoConstants.maxRotAccelMetersPerSecSq));
    }

    @Override
    public void initialize() {
        if(DriverStation.getAlliance().isPresent() && DriverStation.getAlliance().get() == Alliance.Red){
            targetTranslation = FieldConstants.redReefPose;
        }
        else{
            targetTranslation = FieldConstants.blueReefPose;
        }
    }
}
