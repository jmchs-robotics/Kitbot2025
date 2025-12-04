package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AirplaneSubsystem;

public class DefaultAirplaneCommand extends Command {

    private final AirplaneSubsystem m_airplaneSubsystem;

    public DefaultAirplaneCommand(AirplaneSubsystem airplaneSubsystem) {
        
        m_airplaneSubsystem = airplaneSubsystem;
        addRequirements(m_airplaneSubsystem);

    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_airplaneSubsystem.stopMotor();
    }

    @Override
    public void end(boolean interrupted) {
        m_airplaneSubsystem.stopMotor();
    }
}