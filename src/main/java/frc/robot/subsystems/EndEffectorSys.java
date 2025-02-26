package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class EndEffectorSys extends SubsystemBase {
    
    public static Servo m_Servo = new Servo(1);

    private boolean releaseCoral = false;

    public boolean releaseCoral() {
        return releaseCoral = true;
    }

    public EndEffectorSys() {

    }

    @Override
    public void periodic() {
        if(
            releaseCoral == true
        ) {
            m_Servo.set(.5);
            releaseCoral = false;
        }
        else if(
            releaseCoral == false
        ) {
            m_Servo.set(0);
        }
    }
}
