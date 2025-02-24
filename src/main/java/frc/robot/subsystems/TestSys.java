package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SmartMotionConfig;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;

public class TestSys extends SubsystemBase {
    private SparkMax motor;
    private RelativeEncoder encoder;
    private PIDController elevatorController;

    public void testSys() {
        {
            motor = new SparkMax(1, MotorType.kBrushless);
            encoder = motor.getEncoder();
            elevatorController = new PIDController(0.1, 0, 0);

    
            // Set Smart Motion parameters
            int smartMotionSlot = 0;
            SmartMotionConfig smConfig = new SmartMotionConfig();
    }

}
}