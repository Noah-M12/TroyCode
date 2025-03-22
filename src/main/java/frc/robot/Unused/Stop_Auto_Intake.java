
package frc.robot.Unused;

import com.revrobotics.spark.SparkFlex;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class Stop_Auto_Intake extends Command {

    private final Shooter Shooter;

    public Stop_Auto_Intake(Shooter shooter) {
        this.Shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
  
    }

    @Override
    public void execute() {
        Shooter.runShooter(0, 0);
        
    }

    @Override
    public void end(boolean interrupted) {
        
        }

    @Override
    public boolean isFinished() {
        return false;
    }
}
