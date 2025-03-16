package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSys;

public class RintakeoutrunCmd extends Command {
        private final IntakeSys intakeSys;

    public RintakeoutrunCmd(IntakeSys intakeSys) {
        this.intakeSys = intakeSys;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
         
    }

    // Called every time the scheduler runs while the command is scheduled.
    /*@Override
    public void execute() {
        intakeSys.Rintakeoutrun();
    }*/

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
