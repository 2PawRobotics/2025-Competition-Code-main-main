package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.ButtonPanelConstants;
import frc.robot.Constants.CANDevices;
import edu.wpi.first.math.controller.PIDController;
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

    public static RelativeEncoder m_rightIntakeEnc = m_rightIntakeMtr.getEncoder();
    public static RelativeEncoder m_leftIntakeEnc = m_leftIntakeMtr.getEncoder();

    private boolean Rintakeout = false;
    private boolean Lintakeout = false;
    private boolean Lintakein = false;
    private boolean Rintakein = false;
    private boolean intakein = false;

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
            System.out.println("Right Intake out and moving fwd");
        }
        else if(
            operatorController.getRawAxis(1) < 0.95 && operatorController.getRawAxis(1) > -0.95 && operatorController.getRawAxis(0) < 0.95 && operatorController.getRawAxis(0) > -0.95
        ) {
            intakein = true;
        }
        else if(
            operatorController.getRawAxis(1) > 0.95 && operatorController.getRawAxis(0) < -0.95
        ) {
            System.out.println("Left Intake Out and Moving Back");
        }
        else if(operatorController.getRawAxis(1) < -0.95 && operatorController.getRawAxis(0) < -0.95) {
            System.out.println("Left Intake Out and Moving Fwd");
        }
        else if(operatorController.getRawAxis(1) > 0.95 && operatorController.getRawAxis(0) > 0.95){
            System.out.println("Right Intake Out and Moving Bwd");
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
        else if (intakein == true){
            m_leftIntakeEnc.setPosition(0);
            m_rightIntakeEnc.setPosition(0);
            intakein = false;
            System.out.println("intakes in");
        }
    }

}