package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.controller.PIDController;
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

import com.revrobotics.REVLibError;

public class LiftSys extends SubsystemBase {
    
    public static SparkMax m_leftLiftMtr = new SparkMax(CANDevices.m_leftLiftMtrId, MotorType.kBrushless);
    public static SparkMax m_rightLiftMtr = new SparkMax(CANDevices.m_rightLiftMtrId, MotorType.kBrushless);

    RelativeEncoder m_leftliftEnc = m_leftLiftMtr.getEncoder();
    RelativeEncoder m_rightliftEnc = m_rightLiftMtr.getEncoder();

    SparkMaxConfig config = new SparkMaxConfig();

    private boolean islvl4Called = false;
    private boolean islvl3Called = false;
    private boolean islvl2Called = false;
    private boolean islvl1Called = false;
    private boolean islvl0Called = false;


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

    @Override
    public void periodic() {

        if (DriverStation.isEnabled() == true) {

        if(
            islvl4Called == true
        ){
            m_leftliftEnc.setPosition(1);
            m_rightliftEnc.setPosition(1);
            islvl4Called = false;
            islvl0Called = true;
            System.out.println("At level 4");
        }
        else if (
            islvl3Called == true
        ){
            m_leftliftEnc.setPosition(0.75);
            m_rightliftEnc.setPosition(0.75);
            islvl3Called = false;
            islvl0Called = true;
            System.out.println("At level 3");
        }
        else if (
            islvl2Called == true
        ) {
            m_leftliftEnc.setPosition(0.50);
            m_rightliftEnc.setPosition(0.50);
            System.out.println("At level 2");
            islvl2Called = false;
            islvl0Called = true;
        }
        else if (
            islvl1Called == true
        ) {
            m_leftliftEnc.setPosition(0.25);
            m_rightliftEnc.setPosition(0.25);
            System.out.println("At level 1");
            islvl1Called = false;
            islvl0Called = true;
        }
        else if (
            islvl0Called == true
        ) {
            m_leftliftEnc.setPosition(0);
            m_rightliftEnc.setPosition(0);
            System.out.println("At level 0");
        }
    }
    } 

    public LiftSys() {

        m_leftliftEnc.setPosition(0);
        m_rightliftEnc.setPosition(0);

        config.idleMode(IdleMode.kCoast).smartCurrentLimit(
        LiftConstants.stallLimitAmps, 
        LiftConstants.freeLimitAmps, 
        LiftConstants.maxRPM);

        m_leftLiftMtr.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_rightLiftMtr.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    }
}
