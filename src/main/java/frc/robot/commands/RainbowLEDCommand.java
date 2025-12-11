package frc.robot.commands;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Meters;

import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LEDSubsystem;

public class RainbowLEDCommand extends Command {
    
    private final LEDSubsystem m_led;
    private static final Distance kLedSpacing = Meters.of(1 / 120.0);
    private final LEDPattern m_rainbow = LEDPattern.rainbow(255, 128);
    private final LEDPattern m_scrollingRainbow = m_rainbow.scrollAtAbsoluteSpeed(MetersPerSecond.of(1), kLedSpacing);

    public RainbowLEDCommand(LEDSubsystem led) {

        m_led = led;
        addRequirements(m_led);

    }

    @Override
    public void initialize() {}
        
    @Override
    public void execute() {

        m_led.setLEDPattern(m_scrollingRainbow);

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {}

}