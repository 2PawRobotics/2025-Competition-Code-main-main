package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.EndEffectorSys;
import frc.robot.subsystems.LiftSys;

public class Lvl2Cmd extends Command {

    private final LiftSys liftSys;
    private final EndEffectorSys endEffectorSys;

        public Lvl2Cmd(LiftSys liftSys, EndEffectorSys endEffectorSys) {
        this.liftSys = liftSys;
        this.endEffectorSys = endEffectorSys;
        }

       // Called when the command is initially scheduled.
       @Override
       public void initialize() {
            
       }
   
       // Called every time the scheduler runs while the command is scheduled.
       @Override
       public void execute() {
           liftSys.lvl2();
           /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        endEffectorSys.ejectCoral();*/
       }
   
       // Called once the command ends or is interrupted.
       @Override
       public void end(boolean interrupted) {
        liftSys.lvl2Finish();
       }
   
       // Returns true when the command should end.
       @Override
       public boolean isFinished() {
            //liftSys.lvl2Finish();
           return false;
       }
}