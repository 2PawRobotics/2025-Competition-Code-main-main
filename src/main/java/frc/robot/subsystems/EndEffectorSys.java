package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.ButtonPanelConstants;
import frc.robot.Constants.CANDevices;
import com.revrobotics.ColorSensorV3;

public class EndEffectorSys extends SubsystemBase {

    private final Timer delay = new Timer();
    
    public static SparkMax ejectionMtr = new SparkMax(CANDevices.ejectionMtrId, MotorType.kBrushed);
    
    //public static ColorSensorV3 ToFSensor = new ColorSensorV3(I2C.Port.kOnboard);
    private DigitalInput photoSensor = new DigitalInput(3);
    private DigitalInput photoSensor2 = new DigitalInput(4);

    private boolean coralDetected = false;

    private boolean ejectCoral = false;

    private boolean intakeCoral = false;

    //photoSensor = false;

    public boolean ejectCoral() {
        return ejectCoral = true;
    }

    public boolean intakeCoral() {
        return intakeCoral = true;
    }

    public EndEffectorSys() {

    }

    @Override
    public void periodic() {
        if(DriverStation.isEnabled() == true){

            if (!photoSensor2.get() == true && !photoSensor.get() == true) {

                //intakeCoral = false;
                ejectionMtr.set(0);
                System.out.println("Coral Detected");
                //coralDetected = true;
            }
            else if (!photoSensor.get() == true && !photoSensor2.get() == false) {
                ejectionMtr.set(-0.3);
                System.out.println("running");
            }
            else {
                ejectionMtr.set(0);
                System.out.println("not running");
            }

            if(RobotContainer.ButtonPanel.getRawButton(ButtonPanelConstants.conveyorRunPort) == true){
                ejectionMtr.set(0.3);
                //System.out.println("Intaling Algea");
            }
            else if (RobotContainer.ButtonPanel.getRawButton(ButtonPanelConstants.releaseCoralPort) == true){
                ejectionMtr.set(-0.3);
            }
            else    {
                ejectionMtr.set(0);
                //System.out.println("Stoping Algea");
            }

            /*if (ejectCoral == true) {
                ejectionMtr.set(0.3);
                ejectCoral = false;
            }
            else if (coralDetected == true && ejectCoral == false) {
                ejectionMtr.set(0);
            }
            else {
                ejectionMtr.set(0);
            }*/
        }
    }
}
