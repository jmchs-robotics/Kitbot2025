package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MusicSubsystem;

public class MusicCommand extends Command{
    
    private final MusicSubsystem m_musicSubsystem;

    public MusicCommand(MusicSubsystem musicSubsystem) {

        m_musicSubsystem = musicSubsystem;
        addRequirements(m_musicSubsystem);

    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        m_musicSubsystem.startSong();
    }

    @Override
    public void end(boolean interrupted) {
        m_musicSubsystem.pauseSong();
    }

}
