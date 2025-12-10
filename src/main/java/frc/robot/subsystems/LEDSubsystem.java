package frc.robot.subsystems;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LEDConstants;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.units.measure.Distance;

public class LEDSubsystem extends SubsystemBase {

    private AddressableLED m_led = new AddressableLED(0);
    private AddressableLEDBuffer ledBuffer;

    public LEDSubsystem() {

        ledBuffer = LEDConstants.m_ledBuffer;
        m_led.setLength(ledBuffer.getLength());

        m_led.setData(ledBuffer);
        m_led.start();

    }

    public AddressableLED getLeds() {
        return m_led;
    }

    public void setLEDPattern(LEDPattern pattern) {

        pattern.applyTo(ledBuffer);
        m_led.setData(ledBuffer);

    }


     
        
    



    
}
