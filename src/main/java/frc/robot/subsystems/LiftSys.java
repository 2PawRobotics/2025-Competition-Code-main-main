package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.signals.ControlModeValue;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import frc.robot.Constants.CANDevices;
import frc.robot.Constants.LiftConstants;


public class LiftSys extends SubsystemBase {
    
    public static SparkMax m_liftMtr = new SparkMax(CANDevices.m_liftMtrId, MotorType.kBrushless);

    RelativeEncoder m_liftEnc = m_liftMtr.getEncoder();

    private final PIDController liftController = new PIDController(0.0125, 0.04, 0);

    double masterPose = m_liftEnc.getPosition();

    private boolean islvl4Called = false;
    private boolean islvl3Called = false;
    private boolean islvl2Called = false;
    private boolean islvl1Called = false;
    private boolean islvl0Called = false;

    //private final SlewRateLimiter limit;

    public LiftSys() {
        SparkMaxConfig Config = new SparkMaxConfig();

        m_liftEnc.setPosition(0);

        Config.idleMode(IdleMode.kBrake);
        
        m_liftMtr.configure(Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    }

    public void lvl4() {
        islvl4Called = true;
    }

    public void lvl3() {
        islvl3Called = true;
    }

    public void lvl2() {
        islvl2Called = true;
    }

    public void lvl1() {
        islvl1Called = true;
    }

    public void lvl0() {
        islvl0Called = true;
    }
        
    private double lvl0Pose = 0;
    private double lvl1Pose = 45;
    private double lvl2Pose = 50;
    private double lvl3Pose = 75;
    private double lvl4Pose = 125;

    @Override
    public void periodic() {
        System.out.println(m_liftEnc.getPosition());
        
            if(islvl4Called == true) {
                m_liftMtr.set(liftController.calculate(m_liftEnc.getPosition(), lvl4Pose));
                islvl4Called = false;
            }
            else if(islvl3Called == true) {
                m_liftMtr.set(liftController.calculate(m_liftEnc.getPosition(), lvl3Pose));
                islvl3Called = false;
            }
            else if(islvl2Called == true) {
                m_liftMtr.set(liftController.calculate(m_liftEnc.getPosition(), lvl2Pose));
                islvl2Called = false;
            }
            else if(islvl1Called == true) {
                m_liftMtr.set(liftController.calculate(m_liftEnc.getPosition(), lvl1Pose));
                islvl1Called = false;
            }
            else {
                m_liftMtr.set(liftController.calculate(m_liftEnc.getPosition(), lvl0Pose));
            }
    
    } 


}
