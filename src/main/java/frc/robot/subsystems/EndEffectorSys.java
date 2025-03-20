package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANDevices;
import com.revrobotics.ColorSensorV3;

public class EndEffectorSys extends SubsystemBase {
    
    public static SparkMax ejectionMtr = new SparkMax(CANDevices.ejectionMtrId, MotorType.kBrushed);
    
    //public static ColorSensorV3 ToFSensor = new ColorSensorV3(I2C.Port.kOnboard);
    private DigitalInput photoSensor = new DigitalInput(3);

    private boolean coralInSystem = false;

    private boolean ejectCoral = false;

    //photoSensor = false;

    public boolean ejectCoral() {
        return ejectCoral = true;
    }

    public EndEffectorSys() {

    }

    @Override
    public void periodic() {
        if(DriverStation.isEnabled() == true){

            if (!photoSensor.get() == true){
                System.out.println("Object Detected");
                coralInSystem = true;
            }
            else {
                coralInSystem = false;
                System.out.println("No Object");
            }


            if (coralInSystem == true) {
                ejectionMtr.set(0.3);
            }
            else {
                ejectionMtr.set(0);
            }
    }
    }
}
