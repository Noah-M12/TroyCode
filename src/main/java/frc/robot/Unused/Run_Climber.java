package frc.robot.Unused;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Pneunamatics;

public class Run_Climber extends Command{
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    
    private final Climber Climber; 
    private final double speed;
    /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Run_Climber(Climber Climber, double speed) {
    this.Climber = Climber;
    this.speed = speed; 
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Climber.runClimber(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Climber.runClimber(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false; 
  }

}
