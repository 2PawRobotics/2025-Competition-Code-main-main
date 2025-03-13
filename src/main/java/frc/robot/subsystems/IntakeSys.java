package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.CANDevices;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.PivotConstants;
import frc.robot.Constants.RollerConstants;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

public class IntakeSys extends SubsystemBase {
    public final static CommandXboxController operatorController = new CommandXboxController(ControllerConstants.operatorGamepadPort);

    public static SparkMax m_leftIntakeMtr = new SparkMax(CANDevices.m_rightIntakeMtrId, MotorType.kBrushless);
    //public static SparkMax m_rightIntakeMtr = new SparkMax(CANDevices.m_leftIntakeMtrId, MotorType.kBrushed);
    //public static SparkMax m_bottomRightRollerMtr = new SparkMax(CANDevices.m_bottomRightRollerMtrId, MotorType.kBrushed);
    //public static SparkMax m_topRightRollerMtr = new SparkMax(CANDevices.m_topRightRollerMtrId, MotorType.kBrushed);
    public static SparkMax m_topLeftRollerMtr = new SparkMax(CANDevices.m_topLeftRollerMtrId, MotorType.kBrushed);
    public static SparkMax m_bottomLeftRollerMtr = new SparkMax(CANDevices.m_bottomLeftRollerMtrId, MotorType.kBrushed);

    /*public static RelativeEncoder m_rightIntakeEnc = m_rightIntakeMtr.getEncoder();
    public static RelativeEncoder m_leftIntakeEnc = m_leftIntakeMtr.getEncoder();*/

    RelativeEncoder m_rightIntakeEnc;
    RelativeEncoder m_leftIntakeEnc;

    SparkMaxConfig intakeConfig = new SparkMaxConfig();
    SparkMaxConfig rollerConfig = new SparkMaxConfig();

    private boolean Rintakeout = false;
    private boolean Lintakeout = false;
    private boolean Lintakein = false;
    private boolean Rintakein = false;
    private boolean intakein = false;
    private boolean Lintakeoutrun = false;
    private boolean Rintakeoutrun = false;
    private boolean Rintakeoutrunbwd = false;
    private boolean Lintakeoutrunbwd = false;
    private boolean Algeaoutrun = false;
    private boolean Algeaoutrunbwd = false;

    public boolean Litnakeoutrun() {
        return Lintakeoutrun = true;
    }

    public boolean Litnakeoutrunbwd() {
        return Lintakeoutrunbwd = true;
    }

    public boolean Ritnakeoutrunbwd() {
        return Rintakeoutrunbwd = true;
    }

    public boolean Rintakeoutrun() {
        return Rintakeoutrun = true;
    }

    public boolean Rintakeout() {
        return Rintakeout = true;
    }

    public boolean Lintakeout() {
        return Lintakeout = true;
    }

    public boolean Rintakein() {
        return Rintakein = true;
    }

    public boolean Lintakein() {
        return Lintakein = true;
    }

    public boolean Algeaoutrun() {
        return Algeaoutrun = true;
    }

    public boolean Algeaoutrunbwd() {
        return Algeaoutrunbwd = true;
    }

    public boolean intakein() {
        return intakein = true;
    }

    public IntakeSys() {

        intakeConfig.idleMode(IdleMode.kBrake);

        //m_rightIntakeMtr.configure(intakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_leftIntakeMtr.configure(intakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        /*rollerConfig.idleMode(IdleMode.kCoast).smartCurrentLimit(
        25, 
        50, 
        10);

        m_topLeftRollerMtr.configure(rollerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_topRightRollerMtr.configure(rollerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_bottomLeftRollerMtr.configure(rollerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_bottomRightRollerMtr.configure(rollerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        */
        
        //m_rightIntakeEnc = m_rightIntakeMtr.getEncoder();
        m_leftIntakeEnc = m_leftIntakeMtr.getEncoder();

        m_leftIntakeEnc.setPosition(0);
        
    }

    double topRollerSpeed = -0.5;
    double bottomRollerSpeed = -0.7;
    double topReverseRollerSpeed = 0.5;
    double bottomReverseRollerSpeed = 0.7;

    @Override
    public void periodic() {
        if(DriverStation.isTeleopEnabled() == true){
        if(
            operatorController.getRawAxis(0) < -0.95 && operatorController.getRawAxis(1) < 0.95 && operatorController.getRawAxis(1) > -0.95
        ) {
            Lintakeout();
        }
        else if(
            operatorController.getRawAxis(0) > 0.95 && operatorController.getRawAxis(1) > -0.95 && operatorController.getRawAxis(1) < 0.95
        ) {
            Rintakeout();
        }
        else if(
            operatorController.getRawAxis(1) < -0.95 && operatorController.getRawAxis(0) > 0.95
        ) {
            Rintakeoutrun();
        }

        else if(
            operatorController.getRawAxis(1) > 0.95 && operatorController.getRawAxis(0) < -0.95
        ) {
            Litnakeoutrunbwd();
        }
        else if(operatorController.getRawAxis(1) < -0.95 && operatorController.getRawAxis(0) < -0.95) {
            Litnakeoutrun();
        }
        else if(operatorController.getRawAxis(1) > 0.95 && operatorController.getRawAxis(0) > 0.95){
            Ritnakeoutrunbwd();
        }
        else if(operatorController.getRawAxis(0) > 0.95 && operatorController.getRawAxis(1) < -0.95){
            Algeaoutrun();
        }
        else if(operatorController.getRawAxis(1) > 0.95 && operatorController.getRawAxis(1) > 0.95){
            Algeaoutrunbwd();
        }
        else if(
            operatorController.getRawAxis(1) < 0.95 && operatorController.getRawAxis(1) > -0.95 && operatorController.getRawAxis(0) < 0.95 && operatorController.getRawAxis(0) > -0.95
        ) {
            intakein();
            //System.out.println("Intake in working");
            System.out.println(m_leftIntakeEnc.getPosition());
        }
        else {
            Lintakein();
        }
    }
        /*if (Rintakeout == true) {
            m_rightIntakeMtr.set(0.6);
            Rintakeout = false;
            System.out.println("R Intake Out");
        }
        else if (Rintakein == true) {
            m_rightIntakeMtr.set(0);
            //Rintakein = false;
            System.out.println("R Intake In");
        }*/
        /*if (Lintakein == true && m_leftIntakeEnc.getPosition() < 0) {
            //m_leftIntakeMtr.set(0);
            //Lintakein = false;
            //System.out.println("L Intake In");
            m_leftIntakeMtr.set(0.5);
        }*/
        /*else if (Rintakeoutrun == true){
            m_rightIntakeMtr.set(0.6);
            m_bottomRightRollerMtr.set(rollerSpeed);
            m_topRightRollerMtr.set(rollerSpeed);
            Rintakeoutrun = false;
            System.out.println("Right Intake Out and Running");
        }*/
        /*else if (Rintakeoutrunbwd == true){
            m_rightIntakeMtr.set(0.6);
            m_bottomRightRollerMtr.set(reverseRollerSpeed);
            m_topRightRollerMtr.set(reverseRollerSpeed);
            Rintakeoutrunbwd = false;
            System.out.println("Left Intake Out & Running Back");
        }*/
        if (Lintakeoutrun == true && m_leftIntakeEnc.getPosition() < -27 && intakein == false) {
            m_leftIntakeMtr.set(-0.05);
            m_bottomLeftRollerMtr.set(bottomRollerSpeed);
            m_topLeftRollerMtr.set(topRollerSpeed);
            Lintakeoutrun = false;
        }
        else if (Lintakeoutrun == true && m_leftIntakeEnc.getPosition() > -27) {
            m_leftIntakeMtr.set(-0.25);
            m_bottomLeftRollerMtr.set(bottomRollerSpeed);
            m_topLeftRollerMtr.set(topRollerSpeed);
            Lintakeoutrun = false;
        }
        else if (Lintakeoutrunbwd == true && m_leftIntakeEnc.getPosition() > -27) {
            m_leftIntakeMtr.set(-0.25);
            m_bottomLeftRollerMtr.set(bottomReverseRollerSpeed);
            m_topLeftRollerMtr.set(topReverseRollerSpeed);
            Lintakeoutrunbwd = false;
        }
        else if (Lintakeout == true && m_leftIntakeEnc.getPosition() > -27) {
            m_leftIntakeMtr.set(-0.25);
            m_bottomLeftRollerMtr.set(0);
            m_topLeftRollerMtr.set(0);
            Lintakeout = false;
        }
        else if (Algeaoutrun == true && m_leftIntakeEnc.getPosition() < -15 && intakein == false) {
            m_leftIntakeMtr.set(-0.05);
            m_bottomLeftRollerMtr.set(bottomRollerSpeed);
            m_topLeftRollerMtr.set(topRollerSpeed);
            Algeaoutrun = false;
        }
        else if (Algeaoutrun == true && m_leftIntakeEnc.getPosition() > -15) {
            m_leftIntakeMtr.set(-0.25);
            m_topLeftRollerMtr.set(0.15);
            m_bottomLeftRollerMtr.set(0.15);
            Algeaoutrun = false;
        }
        else if (Algeaoutrunbwd == true && m_leftIntakeEnc.getPosition() < -15 && intakein == false) {
            m_leftIntakeMtr.set(-0.05);
            m_bottomLeftRollerMtr.set(bottomRollerSpeed);
            m_topLeftRollerMtr.set(topRollerSpeed);
            Algeaoutrunbwd = false;
        }
        else if (Algeaoutrunbwd == true && m_leftIntakeEnc.getPosition() > -15) {
            m_leftIntakeMtr.set(-0.25);
            m_bottomLeftRollerMtr.set(-0.15);
            m_topLeftRollerMtr.set(-0.15);
            Algeaoutrunbwd = false;
        }
        //else if (intakein == true){
        /*else if (intakein == true && m_leftIntakeEnc.getDistance() < -32.87)
        {
            System.out.println(m_leftIntakeEnc.getDistance());
            m_leftIntakeMtr.set(0.3);
            Lintakeout = false;
            System.out.println("L Intake Out");

            if (m_leftIntakeEnc.getDistance() < 0) {
                m_leftIntakeMtr.set(0);
                m_bottomLeftRollerMtr.set(0);
                m_topLeftRollerMtr.set(0);
            }

            //m_leftIntakeMtr.set(0);
            m_rightIntakeMtr.set(0);
            //m_bottomLeftRollerMtr.set(0);
            //m_topLeftRollerMtr.set(0);
            m_topRightRollerMtr.set(0);
            m_bottomRightRollerMtr.set(0);
            //intakein = false;
            System.out.println("intakes in");
        }*/
        else if (m_leftIntakeEnc.getPosition() < -27 && m_leftIntakeEnc.getPosition() > -28 && Lintakeout == true){
            m_leftIntakeMtr.set(0.07);
            System.out.println("Reverse Intake");
        }
        else if (m_leftIntakeEnc.getPosition() < -15 && m_leftIntakeEnc.getPosition() > -16 && Algeaoutrun == true){
            m_leftIntakeMtr.set(0.07);
            System.out.println("Reverse Intake");
        }
        else if (m_leftIntakeEnc.getPosition() < -27 && m_leftIntakeEnc.getPosition() > -28 && intakein == true){
            Lintakeout = false;
            m_leftIntakeMtr.set(0.25);
            m_bottomLeftRollerMtr.set(0);
            m_topLeftRollerMtr.set(0);
            System.out.println("Reverse Intake");
        }
        else if (m_leftIntakeEnc.getPosition() > 0){
            m_leftIntakeMtr.set(0);
            m_bottomLeftRollerMtr.set(0);
            m_topLeftRollerMtr.set(0);
        }
    }

}