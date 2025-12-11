package frc.robot.commands;

import static edu.wpi.first.units.Units.Seconds;

import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.LEDConstants;
import frc.robot.subsystems.LEDSubsystem;

public class RandomLEDCommand extends Command {

    private final LEDSubsystem m_led;
    private final AddressableLEDBuffer ledBuffer;
    private int LEDColor;
    private Timer timer;
    private LEDPattern blink = LEDPattern.kOff;

    public RandomLEDCommand(LEDSubsystem led) {

        m_led = led;
        addRequirements(m_led);
        ledBuffer = LEDConstants.m_ledBuffer;

    }

    @Override
    public void initialize() {
        timer = new Timer();
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {

        if (timer.get() % 0.5 < 0.05) {
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

        m_led.setLEDPattern(blink);
        // LEDPattern sycned = blink.synchronizedBlink(RobotController::getRSLState);
        // asymmetric.applyTo(ledBuffer);
        // m_led.setLEDBuffer(ledBuffer);

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
    }

}
