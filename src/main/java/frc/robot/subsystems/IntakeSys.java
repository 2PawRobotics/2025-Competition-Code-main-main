package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.ButtonPanelConstants;
import frc.robot.Constants.CANDevices;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SmartMotionConfig;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;

public class IntakeSys extends SubsystemBase {
    private SparkMax motor;
    private RelativeEncoder encoder;
    private PIDController intakeController;
    public final static CommandXboxController operatorController = new CommandXboxController(3);

    public static SparkMax m_rightIntakeMtr = new SparkMax(31, MotorType.kBrushed);
    public static SparkMax m_leftIntakeMtr = new SparkMax(30, MotorType.kBrushed);
    public static SparkMax m_bottomRightRollerMtr = new SparkMax(34, MotorType.kBrushed);
    public static SparkMax m_topRightRollerMtr = new SparkMax(35, MotorType.kBrushed);
    public static SparkMax m_topLeftRollerMtr = new SparkMax(36, MotorType.kBrushed);
    public static SparkMax m_bottomLeftRollerMtr = new SparkMax(37, MotorType.kBrushed);

    public static RelativeEncoder m_rightIntakeEnc = m_rightIntakeMtr.getEncoder();
    public static RelativeEncoder m_leftIntakeEnc = m_leftIntakeMtr.getEncoder();

    private boolean Rintakeout = false;
    private boolean Lintakeout = false;
    private boolean Lintakein = false;
    private boolean Rintakein = false;
    private boolean intakein = false;
    private boolean Lintakeoutrun = false;
    private boolean Rintakeoutrun = false;
    private boolean Rintakeoutrunbwd = false;
    private boolean Lintakeoutrunbwd = false;

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

    public boolean intakein() {
        return intakein = true;
    }

    public IntakeSys() {

        m_rightIntakeEnc.setPosition(0);
        m_leftIntakeEnc.setPosition(0);
        
    }

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
            operatorController.getRawAxis(1) < 0.95 && operatorController.getRawAxis(1) > -0.95 && operatorController.getRawAxis(0) < 0.95 && operatorController.getRawAxis(0) > -0.95
        ) {
            intakein = true;
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
    }
        if(
            Rintakeout == true
        ) {
            m_rightIntakeEnc.setPosition(40);
            Rintakeout = false;
            System.out.println("R Intake Out");
        }
        else if(
            Lintakeout == true
        ) {
            m_leftIntakeEnc.setPosition(40);
            Lintakeout = false;
            System.out.println("L Intake Out");
        }
        else if (Rintakein == true) {
            m_rightIntakeEnc.setPosition(0);
            Rintakein = false;
            System.out.println("R Intake In");
        }
        else if (Lintakein == true) {
            m_rightIntakeEnc.setPosition(0);
            Lintakein = false;
            System.out.println("L Intake In");
        }
        else if (Lintakeoutrun == true) {
            m_leftIntakeEnc.setPosition(0);
            m_bottomLeftRollerMtr.set(1);
            m_topLeftRollerMtr.set(-1);
            Lintakeoutrun = false;
            System.out.println("Left Intake Out & Running");
        }
        else if (Rintakeoutrun == true){
            m_rightIntakeEnc.setPosition(0);
            m_bottomRightRollerMtr.set(1);
            m_topRightRollerMtr.set(-1);
            Rintakeoutrun = false;
            System.out.println("Right Intake Out and Running");
        }
        else if (Lintakeoutrunbwd == true){
            m_leftIntakeEnc.setPosition(0);
            m_bottomLeftRollerMtr.set(-1);
            m_topLeftRollerMtr.set(1);
            Lintakeoutrunbwd = false;
            System.out.println("Left Intake Out & Running Back");
        }
        else if (Rintakeoutrunbwd == true){
            m_rightIntakeEnc.setPosition(0);
            m_bottomRightRollerMtr.set(-1);
            m_topRightRollerMtr.set(1);
            Rintakeoutrunbwd = false;
            System.out.println("Left Intake Out & Running Back");
        }
        else if (intakein == true){
            m_leftIntakeEnc.setPosition(0);
            m_rightIntakeEnc.setPosition(0);
            m_bottomLeftRollerMtr.set(0);
            m_topLeftRollerMtr.set(0);
            m_topRightRollerMtr.set(0);
            m_bottomRightRollerMtr.set(0);
            intakein = false;
            System.out.println("intakes in");
        }
    }

}