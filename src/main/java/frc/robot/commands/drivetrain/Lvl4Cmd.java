package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LiftSys;

public class Lvl4Cmd extends Command {

    private final LiftSys liftSys;

        public Lvl4Cmd(LiftSys liftSys) {
        this.liftSys = liftSys;
        }

       // Called when the command is initially scheduled.
       @Override
       public void initialize() {
            
       }
   
       // Called every time the scheduler runs while the command is scheduled.
       @Override
       public void execute() {
           liftSys.lvl4();
           //System.out.println("At Level 4");
       }
   
       // Called once the command ends or is interrupted.
       @Override
       public void end(boolean interrupted) {
           
       }
   
       // Returns true when the command should end.
       @Override
       public boolean isFinished() {
           return false;
       }
}