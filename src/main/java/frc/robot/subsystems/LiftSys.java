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
    
    public static SparkMax m_leftLiftMtr = new SparkMax(CANDevices.m_leftLiftMtrId, MotorType.kBrushless);
    public static SparkMax m_rightLiftMtr = new SparkMax(CANDevices.m_rightLiftMtrId, MotorType.kBrushless);

    RelativeEncoder m_leftliftEnc = m_leftLiftMtr.getEncoder();
    RelativeEncoder m_rightliftEnc = m_rightLiftMtr.getEncoder();

    double masterPose = m_leftliftEnc.getPosition();
    double slavePose = m_rightliftEnc.getPosition();

    private boolean islvl4Called = false;
    private boolean islvl3Called = false;
    private boolean islvl2Called = false;
    private boolean islvl1Called = false;
    private boolean islvl0Called = false;

    //private final SlewRateLimiter limit;

    public LiftSys() {

    
        SparkMaxConfig rightConfig = new SparkMaxConfig();
        SparkMaxConfig leftConfig = new SparkMaxConfig();

        //limit = new SlewRateLimiter(2);

        m_leftliftEnc.setPosition(0);
        m_rightliftEnc.setPosition(0);

        leftConfig.idleMode(IdleMode.kBrake);
        leftConfig.closedLoop.pid(0,0.00025,0);
        //leftConfig.encoder.inverted(true);

        rightConfig.idleMode(IdleMode.kBrake);
        rightConfig.closedLoop.pid(0,0.00025,0);
        

        m_leftLiftMtr.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_rightLiftMtr.configure(rightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        //m_leftLiftMtr.configureAsync(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        //m_rightLiftMtr.configureAsync(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        

    }

    public boolean islvl4Called() {
        return islvl4Called = true;
    }

    public boolean islvl3Called() {
        return islvl3Called = true;
    }

    public boolean islvl2Called() {
        return islvl2Called = true;
    }

    public boolean islvl1Called() {
        return islvl1Called = true;
    }

    public boolean islvl0Called() {
        return islvl0Called = true;
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
        
    double rightLiftSpeed = 0.3;
    double leftLiftSpeed = -0.3;

    @Override
    public void periodic() {

        double masterPose = m_leftliftEnc.getPosition();

        if (-masterPose > m_rightliftEnc.getPosition()){
            //m_rightLiftMtr.set(limit.calculate(0.325));
            m_rightLiftMtr.set(rightLiftSpeed);
        }
        else if (-masterPose < m_rightliftEnc.getPosition()){
            m_rightLiftMtr.set(-0.1);
        }
        else if (islvl0Called == true && (m_rightliftEnc.getPosition() > 0.3) && islvl1Called) {
            m_rightLiftMtr.set(-0.001);
        }
        
        

        if (DriverStation.isEnabled() == true) {

        //System.out.println("Left lift encoder: " + m_leftliftEnc.getPosition());
        //System.out.println("Right lift encoder: " + m_rightliftEnc.getPosition());
        
        
        if (islvl4Called && m_leftliftEnc.getPosition() < -20) {
            m_leftLiftMtr.set(-0.05);
            m_rightLiftMtr.set(0.05);
            islvl4Called = false;
            islvl0Called = true;
        }
        else if (islvl4Called == true && (m_leftliftEnc.getPosition() > -20)) {
            m_leftLiftMtr.set(leftLiftSpeed);
            islvl4Called = false;
            islvl0Called = true;
            System.out.println("At level 4");
        }
        else if (islvl3Called && m_leftliftEnc.getPosition() < -15) {
            m_leftLiftMtr.set(-0.05);
            m_rightLiftMtr.set(0.05);
            islvl3Called = false;
            islvl0Called = true;
        }
        else if (islvl3Called == true && (m_leftliftEnc.getPosition() > -15)) {
            m_leftLiftMtr.set(leftLiftSpeed);
            islvl3Called = false;
            islvl0Called = true;
            System.out.println("At level 3");
        }
        else if (islvl2Called && m_leftliftEnc.getPosition() < -10) {
            m_leftLiftMtr.set(-0.05);
            m_rightLiftMtr.set(0.05);
            islvl2Called = false;
            islvl0Called = true;
        }
        else if (islvl2Called == true && (m_leftliftEnc.getPosition() > -10)) {
            m_leftLiftMtr.set(leftLiftSpeed);
            System.out.println("At level 2");
            islvl2Called = false;
            islvl0Called = true;
        }
        else if (islvl1Called == true && m_leftliftEnc.getPosition() < -7) {
            m_leftLiftMtr.set(-0.05);
            m_rightLiftMtr.set(0.05);
            islvl1Called = false;
            islvl0Called = true;
        }
        else if (islvl1Called == true && (m_leftliftEnc.getPosition() > -7)) {
            //m_leftLiftMtr.set(limit.calculate(-0.3));
            m_leftLiftMtr.set(leftLiftSpeed);
            System.out.println("At level 1");
            islvl1Called = false;
            islvl0Called = true;
        }
        /*else if (islvl0Called == true && (m_leftliftEnc.getPosition() < -0.3) && islvl1Called) {
            m_leftLiftMtr.set(0.001);
            System.out.println("LOWERING IN PROGRESS");    
        }*/
        else if (islvl0Called == true && (m_leftliftEnc.getPosition() < -0.3)) {
            m_leftLiftMtr.set(0.05);
            m_rightLiftMtr.set(-0.05);
            System.out.println("LOWERING IN PROGRESS");
        }
        /*else if (islvl0Called == true) {
            m_leftLiftMtr.set(0);
            m_rightLiftMtr.set(0);
            System.out.println("At level 0");
        }*/
        else {
            m_leftLiftMtr.set(0);
            m_rightLiftMtr.set(0);
        }
        /*if (islvl1Called == true) {
            if (m_leftliftEnc.getPosition() > m_rightliftEnc.getPosition()){
                m_rightLiftMtr.set(-0.2);
            }
            if (m_leftliftEnc.getPosition() < m_rightliftEnc.getPosition()){
                m_rightLiftMtr.set(0.2);
            }
        } 
        else if (islvl2Called == true) {
            if (m_leftliftEnc.getPosition() > m_rightliftEnc.getPosition()){
                m_rightLiftMtr.set(-0.2);
            }
            else if (m_leftliftEnc.getPosition() < m_rightliftEnc.getPosition()){
                m_rightLiftMtr.set(0.2);
            }
        }
        else if (islvl3Called == true) {
            if (m_leftliftEnc.getPosition() > m_rightliftEnc.getPosition()){
                m_rightLiftMtr.set(-0.2);
            }
            else if (m_leftliftEnc.getPosition() < m_rightliftEnc.getPosition()){
                m_rightLiftMtr.set(0.2);
            }
        } 
        else if (islvl4Called == true) {
            if (m_leftliftEnc.getPosition() > m_rightliftEnc.getPosition()){
                m_rightLiftMtr.set(-0.2);
            }
            else if (m_leftliftEnc.getPosition() < m_rightliftEnc.getPosition()){
                m_rightLiftMtr.set(0.2);
            }
        else {
            islvl0Called = true;
            islvl1Called = false;
            islvl2Called = false;
            islvl3Called = false;
            islvl4Called = false;
        }
        } */
    }
    } 


}
