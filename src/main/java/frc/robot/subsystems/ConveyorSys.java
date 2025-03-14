package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ButtonPanelConstants;
import frc.robot.Constants.CANDevices;
import frc.robot.RobotContainer;

public class ConveyorSys extends SubsystemBase {
    
    public static SparkMax m_conveyorMtr = new SparkMax(CANDevices.m_conveyorMtrId, MotorType.kBrushed);

    private boolean ConveyorFwd = false;
    private boolean ConveyorBwd = false;
    private boolean ConveyorStp = false;

    public boolean ConveyorFwd() {
        return ConveyorFwd = true;
    }

    @Override
    public void periodic() {

        if(RobotContainer.ButtonPanel.getRawButton(ButtonPanelConstants.conveyorControlPort) == false){
            ConveyorBwd = true;
        }

        if (ConveyorFwd == true && ConveyorBwd == false
        ) {
            m_conveyorMtr.set(0.4);
            ConveyorFwd = false;
            ConveyorStp = true;
            System.out.println("Conveyor Fwd");
        }
        else if (ConveyorBwd == true && ConveyorFwd == true
        ) {
            m_conveyorMtr.set(-0.4);
            ConveyorBwd = false;
            ConveyorFwd = false;
            ConveyorStp = true;
            System.out.println("Conveyor Bwd");
        }
        else if (ConveyorStp == true
        ) {
            m_conveyorMtr.set(0);
        }
    }

    public ConveyorSys() {

    }
}
