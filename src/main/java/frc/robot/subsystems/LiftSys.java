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

    RelativeEncoder m_leftliftEnc = m_liftMtr.getEncoder();

    private final PIDController liftController = new PIDController(0.2, 0, 0);

    double masterPose = m_leftliftEnc.getPosition();

    private boolean islvl4Called = false;
    private boolean islvl3Called = false;
    private boolean islvl2Called = false;
    private boolean islvl1Called = false;
    private boolean islvl0Called = false;

    //private final SlewRateLimiter limit;

    public LiftSys() {
        SparkMaxConfig leftConfig = new SparkMaxConfig();

        m_leftliftEnc.setPosition(0);

        leftConfig.idleMode(IdleMode.kBrake);
        
        m_liftMtr.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

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
    private double lvl1Pose = -9;
    private double lvl2Pose = -10;
    private double lvl3Pose = -15;
    private double lvl4Pose = -25;

    @Override
    public void periodic() {
        //System.out.println(m_leftliftEnc.getPosition());
        
            if(islvl4Called == true) {
                m_liftMtr.set(liftController.calculate(m_leftliftEnc.getPosition(), lvl4Pose));
                islvl4Called = false;
            }
            else if(islvl3Called == true) {
                m_liftMtr.set(liftController.calculate(m_leftliftEnc.getPosition(), lvl3Pose));
                islvl3Called = false;
            }
            else if(islvl2Called == true) {
                m_liftMtr.set(liftController.calculate(m_leftliftEnc.getPosition(), lvl2Pose));
                islvl2Called = false;
            }
            else if(islvl1Called == true) {
                m_liftMtr.set(liftController.calculate(m_leftliftEnc.getPosition(), lvl1Pose));
                islvl1Called = false;
            }
            else {
                m_liftMtr.set(liftController.calculate(m_leftliftEnc.getPosition(), lvl0Pose));
            }
    
    } 


}
