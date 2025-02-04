package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends Command {

    private final ShooterSubsystem m_pewpewSubsystem;

    public ShooterCommand(ShooterSubsystem pewpewSubsystem) {
        
        m_pewpewSubsystem = pewpewSubsystem;
        addRequirements(m_pewpewSubsystem);

    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_pewpewSubsystem.setMotor(0.5);
    }

    @Override
    public void end(boolean interrupted) {
        m_pewpewSubsystem.stopMotor();
    }
}