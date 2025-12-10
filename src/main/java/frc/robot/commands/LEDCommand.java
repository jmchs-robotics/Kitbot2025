package frc.robot.commands;

import static edu.wpi.first.units.Units.InchesPerSecond;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Seconds;
import static edu.wpi.first.units.Units.Meters;

import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.LEDConstants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LEDSubsystem;

public class LEDCommand extends Command {
    
    private final LEDSubsystem m_led;
    private static final Distance kLedSpacing = Meters.of(1 / 120.0);
    private final LEDPattern m_rainbow = LEDPattern.rainbow(255, 128);
    private final LEDPattern m_scrollingRainbow = m_rainbow.scrollAtAbsoluteSpeed(MetersPerSecond.of(1), kLedSpacing);
    private final AddressableLEDBuffer ledBuffer;
    private int LEDColor;

    public LEDCommand(LEDSubsystem led) {

        m_led = led;
        addRequirements(m_led);
        ledBuffer = LEDConstants.m_ledBuffer;

    }

    @Override
    public void initialize() {}
        
    @Override
    public void execute() {

        m_led.setLEDPattern(m_scrollingRainbow);

        LEDPattern blink = LEDPattern.kOff;
        LEDPattern asymmetric = blink.blink(Seconds.of(2), Seconds.of(.5));
        // LEDPattern sycned = blink.synchronizedBlink(RobotController::getRSLState);
        asymmetric.applyTo(ledBuffer);
        // m_led.setData(ledBuffer);

        for(int i = 0; i < 20; i++) {
            LEDColor = (int) (Math.random() * 7);

            if (LEDColor == 0) {
                blink = LEDPattern.solid(Color.kRed);
            }

            else if (LEDColor == 1) {
                blink = LEDPattern.solid(Color.kOrange);
            }

            else if (LEDColor == 2) {
                blink = LEDPattern.solid(Color.kYellow);
            }

            else if (LEDColor == 3) {
                blink = LEDPattern.solid(Color.kGreen);
            }

            else if (LEDColor == 4) {
                blink = LEDPattern.solid(Color.kAqua);
            }

            else if (LEDColor == 5) {
                blink = LEDPattern.solid(Color.kDarkBlue);
            }

            else if (LEDColor == 6) {
                blink = LEDPattern.solid(Color.kPink);
            }
        }

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {}
    











}
