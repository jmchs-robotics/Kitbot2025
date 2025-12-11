package frc.robot.commands;

import static edu.wpi.first.units.Units.Seconds;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.LEDConstants;
import frc.robot.subsystems.LEDSubsystem;

public class RandomLEDCommand extends Command {
    
    private final LEDSubsystem m_led;
    private final AddressableLEDBuffer ledBuffer;
    private int LEDColor;

    public RandomLEDCommand(LEDSubsystem led) {

        m_led = led;
        addRequirements(m_led);
        ledBuffer = LEDConstants.m_ledBuffer;

    }

    @Override 
    public void initialize() {}

    @Override
    public void execute() {

        LEDPattern blink = LEDPattern.kOff;
        LEDPattern asymmetric = blink.blink(Seconds.of(2), Seconds.of(.5));
        // LEDPattern sycned = blink.synchronizedBlink(RobotController::getRSLState);
        asymmetric.applyTo(ledBuffer);
        // m_led.setData(ledBuffer);

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

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {}
    
}
