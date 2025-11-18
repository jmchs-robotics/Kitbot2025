package frc.robot.commands;

import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LEDSubsystem;

public class LEDCommand extends Command {
    
    private final LEDSubsystem m_led;

    public LEDCommand(LEDSubsystem led) {

        m_led = led;
        addRequirements(m_led);
    }

    @Override
    public void initialize() {}
        
    @Override
    public void execute() {

        LEDPattern pattern = LEDPattern.solid(Color.kWhite);
        m_led.setLEDPattern(pattern);

    }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public void end(boolean interrupted) {}
    
}
