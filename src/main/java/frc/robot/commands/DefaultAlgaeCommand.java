package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeSubsystem;

public class DefaultAlgaeCommand extends Command{

    private final AlgaeSubsystem m_algaeWheels;

    public DefaultAlgaeCommand(AlgaeSubsystem algaeWheels) {

        m_algaeWheels = algaeWheels;
        addRequirements(m_algaeWheels);

    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
       m_algaeWheels.stopWheelMotors(); 
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_algaeWheels.stopWheelMotors();
    }

}
