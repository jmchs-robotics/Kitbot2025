package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCoral extends Command {

    private final ShooterSubsystem m_shooterSubsystem;

    public ShootCoral(ShooterSubsystem shooterSubsystem) {
        
        //define the subsystem and add requirements.

    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        // this is setting motor speeds
    }

    @Override
        
    public void end(boolean interrupted) {
        // this is to stop the motor
    }
}
