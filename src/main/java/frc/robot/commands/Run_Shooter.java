package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class Run_Shooter extends Command {

    private final double bw_speed;
    private final double sw_speed;
    private final Shooter Shooter;

    public Run_Shooter(Shooter shooter, double bw_speed, double sw_speed) {
        this.Shooter = shooter;
        this.bw_speed = bw_speed;
        this.sw_speed = sw_speed;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        Shooter.runShooter(sw_speed, bw_speed);
    }

    @Override
    public void end(boolean interrupted) {
        Shooter.runShooter(0, 0);  // Stop the shooter when the command ends
    }

    @Override
    public boolean isFinished() {
        return false;  // Command finishes after 1 second
    }
}
