package frc.robot.Unused;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class Timer_Run_Shooter extends Command {

    private final double bw_speed;
    private final double sw_speed;
    private final Shooter Shooter;
    protected Timer m_timer = new Timer();

    public Timer_Run_Shooter(Shooter shooter, double bw_speed, double sw_speed) {
        this.Shooter = shooter;
        this.bw_speed = bw_speed;
        this.sw_speed = sw_speed;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        m_timer.reset();  // Ensure the timer is reset before starting
        m_timer.start();  // Start the timer
    }

    @Override
    public void execute() {
        Shooter.runShooter(sw_speed, bw_speed);
    }

    @Override
    public void end(boolean interrupted) {
        Shooter.runShooter(0, 0);  // Stop the shooter when the command ends
        m_timer.stop();
    }

    @Override
    public boolean isFinished() {
        return m_timer.hasElapsed(1);  // Command finishes after 1 second
    }
}
