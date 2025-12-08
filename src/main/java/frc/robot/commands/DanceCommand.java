package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DanceCommand extends Command{

    private DriveSubsystem m_driveSubsystem;

    public DanceCommand(DriveSubsystem driveSubsystem) {

        m_driveSubsystem = driveSubsystem;
        addRequirements(m_driveSubsystem);

    }

    @Override
    public void initialize() {}

    @Override 
    public void execute() {
        m_driveSubsystem.arcadeDrive(0, 1);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_driveSubsystem.stopAllMotors();
    }
    
}
