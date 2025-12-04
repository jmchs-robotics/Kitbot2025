package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AirplaneSubsystem;

public class ShootAirplane extends Command {

    private final AirplaneSubsystem m_shooterSubsystem;

    public ShootAirplane(AirplaneSubsystem shooterSubsystem) {
        
        m_shooterSubsystem = shooterSubsystem;
        addRequirements(m_shooterSubsystem);

    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_shooterSubsystem.setMotor(0.8);
    }

    @Override
    public void end(boolean interrupted) {
        m_shooterSubsystem.stopMotor();
    }
}