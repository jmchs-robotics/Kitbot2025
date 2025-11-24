package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

public final class Configs {

    public static final class KitbotConfigs {

        public static final TalonFXConfiguration shooterConfig = new TalonFXConfiguration();
        public static final TalonFXConfiguration driveLeftPrimaryConfig = new TalonFXConfiguration();
        public static final TalonFXConfiguration driveRightPrimaryConfig = new TalonFXConfiguration();
        public static final TalonFXConfiguration driveLeftFollowerConfig = new TalonFXConfiguration();
        public static final TalonFXConfiguration driveRightFollowerConfig = new TalonFXConfiguration();
        
        // static {

        //     shooterConfig
        //         .NeutralMode()
        //         .smartCurrentLimit(20)
        //         .inverted(false); 
                    
        //     driveLeftPrimaryConfig
        //         .idleMode(IdleMode.kCoast)
        //         .smartCurrentLimit(50)
        //         .inverted(false);
            
        //     driveRightPrimaryConfig
        //         .idleMode(IdleMode.kCoast)
        //         .smartCurrentLimit(50)
        //         .inverted(true);

        //     driveLeftFollowerConfig
        //         .idleMode(IdleMode.kCoast)
        //         .smartCurrentLimit(50)
        //         .follow(1, false);
            
        //     driveRightFollowerConfig
        //         .idleMode(IdleMode.kCoast)
        //         .smartCurrentLimit(50)
        //         .follow(2, false);  

        // }
    }
}
