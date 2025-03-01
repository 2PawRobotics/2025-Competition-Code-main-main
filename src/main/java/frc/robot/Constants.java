package frc.robot;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.util.Color;

public class Constants {

    public static final class ButtonPanelConstants {
        public static final int lvl4ReefRightPort = 1;
        public static final int lvl4ReefLeftPort = 2;
        public static final int lvl3ReefRightPort = 3;
        public static final int lvl3ReefLeftPort = 4;
        public static final int lvl2ReefRightPort = 5;
        public static final int lvl2ReefLeftPort = 6;
        public static final int lvl1ReefLeftPort = 7;
        public static final int lvl1ReefRightPort = 8;
        public static final int releaseCoralPort = 9;
        public static final int conveyorControlPort = 10;
        public static final int conveyorRunPort = 11;
        public static final int joystickPort = 13;
    }

    public static final class CANDevices {
        // Set these CAN ID values to the those of your robot, or change your CAN ID's to match this convention.

        //Pigeon
        public static final int imuId = 9;

        //FL Mod
        public static final int frontLeftCanCoderId = 11;
        public static final int frontLeftSteerMtrId = 3;
        public static final int frontLeftDriveMtrId = 4;

        //FR Mod
        public static final int frontRightCanCoderId = 10;
        public static final int frontRightSteerMtrId = 5;
        public static final int frontRightDriveMtrId = 6;

        //BL Mod
        public static final int backLeftCanCoderId = 12;
        public static final int backLeftSteerMtrId = 1;
        public static final int backLeftDriveMtrId = 2;

        //BR Mod
        public static final int backRightCanCoderId = 13;
        public static final int backRightSteerMtrId = 7;
        public static final int backRightDriveMtrId = 8;

        //Rollers
        public static final int m_topLeftRollerMtrId = 19;
        public static final int m_bottomLeftRollerMtrId = 18;
        public static final int m_topRightRollerMtrId = 17;
        public static final int m_bottomRightRollerMtrId = 16;
        public static final int m_rightIntakeMtrId = 15;
        public static final int m_leftIntakeMtrId = 14;

        //Lift
        public static final int m_rightLiftMtrId = 22;
        public static final int m_leftLiftMtrId = 21;

        //Conveyor
        public static final int m_conveyorMtrId = 20;

        //EndEffector
        public static final int m_coralReleaseSevCnl = 1;
       
    }

    public static final class ControllerConstants {

        //Controllers
        public static final int driverGamepadPort = 0;
        public static final int operatorGamepadPort = 1;
        
        public static final double joystickDeadband = 0.15;

        public static final double triggerPressedThreshhold = 0.25;

        public static final int driverRightJoystick = 1;

    }
    
    public static final class DriveConstants {
        /**
         * The track width from wheel center to wheel center.
         */
        // Make sure to measure from the center of each wheel
        public static final double trackWidth = Units.inchesToMeters(19.6875);

        /**
         * The track length from wheel center to wheel center.
         */
        // mature sure to measure from the center of each wheel
        public static final double wheelBase = Units.inchesToMeters(19.6875);

        /**
         * The SwerveDriveKinematics used for control and odometry.
         */
        public static final SwerveDriveKinematics kinematics = 
            new SwerveDriveKinematics(
                new Translation2d(trackWidth / 2.0, wheelBase / 2.0),  // front left
                new Translation2d(trackWidth / 2.0, -wheelBase / 2.0), // front right
                new Translation2d(-trackWidth / 2.0, wheelBase / 2.0), // back left
                new Translation2d(-trackWidth / 2.0, -wheelBase / 2.0) // back right
            );

        /**
         * The gear reduction from the drive motor to the wheel.
         * 
         * The drive gear ratios for the different levels can be found from the chart at
         * swervedrivespecialties.com/products/mk41-swerve-module.
         */
        // This is the gear ratio for L3 modules.
        public static final double driveMtrGearReduction = (16.0 / 50.0) * (28.0 / 16.0) * (15.0 / 45.0);

        /**
         * The gear reduction from the steer motor to the wheel.
         */
        public static final double steerMtrGearReduction = (14.0 / 50.0) * (10.0 / 60.0);

        public static final double wheelRadiusMeters = Units.inchesToMeters(2);
        public static final double wheelCircumferenceMeters = 2.0 * wheelRadiusMeters * Math.PI;

        public static final double driveMetersPerEncRev = wheelCircumferenceMeters * driveMtrGearReduction;
        public static final double driveMetersPerSecPerMtrRPM = driveMetersPerEncRev / 60.0;

        public static final double steerRadiansPerEncRev = 2 * Math.PI * DriveConstants.steerMtrGearReduction;

        public static final double freeMetersPerSecond = 6784 * driveMetersPerSecPerMtrRPM;

        /**
         * The maximum possible speed a module can be driven. Used for desaturation.
         */
        public static final double maxModuleSpeedMetersPerSec = 10;

        public static final double maxDriveSpeedMetersPerSec = 10;

        /**
         * The rate the robot will spin with full Rot command.
         */
        public static final double maxTurnSpeedRadPerSec = 3.0 * Math.PI;

        // Set line up the swerve modules and set these values.

        // The bolt heads should be pointing to the right. These values are subtracted from the CANCoder reading,
        // so they should be the raw CANCoder value when set straight. These values should be between 0 and 360
        // degrees.
        public static final Rotation2d frontLeftModOffset = Rotation2d.fromDegrees(124.09); // 122.43, 318.164, 135.97
        public static final Rotation2d frontRightModOffset = Rotation2d.fromDegrees(117.9); // 184.12, 234.756, 134.5
        public static final Rotation2d backLeftModOffset = Rotation2d.fromDegrees(-150.01); // 62, 252.15804, -164.5
        public static final Rotation2d backRightModOffset = Rotation2d.fromDegrees(10.36); // 82.7, 252.15804, 40.07

        // You may want to change this value.
        public static final int driveCurrentLimitAmps = 70;
        public static final double brownoutVoltage = 6.25;
        
        // These values should be fine, but if the modules start to rattle you may want to play with the steer PID values.
        public static final double drivekP = 0.13;//0.005;
        public static final double drivekD = 0.0;

        public static final double steerkP = 0.37431;
        public static final double steerkD = 0.27186;

        public static final double ksVolts = 0.667;
        public static final double kvVoltSecsPerMeter = 2.44;
        public static final double kaVoltSecsPerMeterSq = 0.0;

        public static final SimpleMotorFeedforward driveFF =
            new SimpleMotorFeedforward(ksVolts, kvVoltSecsPerMeter, kaVoltSecsPerMeterSq);

    }

    public static final class AutoConstants {

        // These drive and rotation PID constants most likely need to be tuned for better accuracy.
        public static final double drivekP = 2.0 * 4.0; // 12.8
        public static final double drivekD = 0.02 * 4.0; //0.0625; // .085

        public static final double rotkP = 5.5; // 1.27
        public static final double rotkD = 0.5; // 0.5

        public static final double maxAccelMetersPerSec = 4.7;
        public static final double maxAccelMetersPerSecSq = 3.7;

        public static final double maxRotAccelMetersPerSecSq = 5.88;

        // Auto aim PID values should ideally be the same as the PathPlanner rotation ones. They are separate for safe measure.

        public static final double autoAimkP = 10.9;
        public static final double autoAimkD = 0.5;

        public static final double autoAimToleranceDeg = 0.2;

        public static final double autoAimTurnSpeedRadPerSec = 2.0 * Math.PI;
        public static final double autoAumTurnAccelRadPerSecSq = 3.0 * Math.PI;

        public static final Pose2d driveToAmpWaypoint = new Pose2d(1.83, 7.81, Rotation2d.fromDegrees(-90.0));
        public static final Pose2d driveToAmpTargetPoint = new Pose2d(1.83, 7.61, Rotation2d.fromDegrees(-90.0));

        public static final double driveToAmpMaxVelMetersPerSec = 4.0;
        public static final double driveToAmpMaxAccelMetersPerSecSq = 3.0;

        public static final double subwooferShotThreshold = 1.8;

        public static final double offsetSubwooferShotThreshold = 1.0;

    }

    public class RollerConstants {
    
        public static final int stallLimitAmps = 25;
        public static final int freeLimitAmps = 50;
        public static final int maxRPM = 2000;

        public static final double gearRatio = 5;

    }

    public class PivotConstants {

        public static final int stallLimitAmps = 10;
        public static final int freeLimitAmps = 25;
        public static final int maxRPM = 1;

    }

    public class LiftConstants {
        
        public static final int stallLimitAmps = 100;
        public static final int freeLimitAmps = 50;
        public static final int maxRPM = 100;

    }

    public class ClimberConstants {
        public static final double gearReduction = 20.0;

        public static final float climberForwardLimit = 147.0f;
        public static final float climberReverseLimit = 0.1f;

        public static final double climberSpeedFactor = 1.0;
    }

    public class SpacebarConstants {
        public static final double gearReduction = 25.0;

        public static final float spacebarForwardLimit = 5.0f;
        public static final float spacebarReverseLimit = 0.01f;

        public static final double kP = 0.015;
        public static final double kD = 0.00025;
        public static final double maxVelDegPerSec = 2000.0;
        public static final double maxAccelDegPerSecSq = 1500.0;

        public static final double spacebarHomeDeg = -2.0;
        public static final double spacebarOutDeg = 200.0;
    }

    public class LightsConstants {
        public static final Color blueAllianceColor = new Color(0, 0, 255);
        public static final Color redAllianceColor = new Color(255, 0, 0);
        public static final Color noAllianceColor = new Color(200, 255, 200);

        public static final Color hasNoteColor = new Color(0, 255, 0);

        public static final double partyModeHueIncrement = 5;
        public static final double partyModeTranslationTimeSec = 0.05;

        public static final double brightnessPercentage = 0.5;
    }

    public class VisionConstants {
        public static final String frontLimelightName = "limelight";
        public static final String backLimelightName = "limelight";

        public static final double targetAreaPercentThreshold = 0.15;
    }

    public class FieldConstants {
        public static final Translation2d blueAllianceSpeakerPose = new Translation2d(0.0, 5.55);
        public static final Translation2d redAllianceSpeakerPose = new Translation2d(16.54, 5.55);

        public static final Translation2d blueReefPose = new Translation2d(4.5, 4);
        public static final Translation2d redReefPose = new Translation2d(13, 4);

        public static final double speakerTargetHeightMeters = 2.03;
    }
}