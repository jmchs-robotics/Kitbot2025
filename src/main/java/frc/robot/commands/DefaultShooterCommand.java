package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class DefaultShooterCommand extends Command {

    private final ShooterSubsystem m_shooterSubsystem;

    public DefaultShooterCommand(ShooterSubsystem shooterSubsystem) {
        
        // requirements and deinitions

    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        //stop the motor
    }

    @Override
    public void end(boolean interrupted) {
        //stop the motor
    }
}
