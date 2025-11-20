package frc.robot.subsystems;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.units.measure.Distance;

public class LEDSubsystem extends SubsystemBase {

    private AddressableLED m_led = new AddressableLED(9);
    private AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(150);

    public LEDSubsystem() {

        m_led.setLength(m_ledBuffer.getLength());

        m_led.setData(m_ledBuffer);
        m_led.start();

    }

    public AddressableLED getLeds() {
        return m_led;
    }

    public void setLEDPattern(LEDPattern pattern) {

        pattern.applyTo(m_ledBuffer);
        m_led.setData(m_ledBuffer);

    }


    private final LEDPattern m_rainbow = LEDPattern.rainbow(255, 128);
        private static final Distance kLedSpacing = Meters.of(1 / 120.0);

        private final LEDPattern m_scrollingRainbow = m_rainbow.scrollAtAbsoluteSpeed(MetersPerSecond.of(1), kLedSpacing);

    public void robotPeriodic() {

        m_scrollingRainbow.applyTo(m_ledBuffer);
        m_led.setData(m_ledBuffer);

    }




}
