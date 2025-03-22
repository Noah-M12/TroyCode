package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgeaIntake;
import frc.robot.subsystems.Pneunamatics;

public class Run_Algea_Wheel extends Command{
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    
    private final AlgeaIntake AlgeaIntake; 
    private final double speed;
    /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Run_Algea_Wheel(AlgeaIntake AlgeaIntake, double speed) {
    this.AlgeaIntake = AlgeaIntake;
    this.speed = speed; 
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(AlgeaIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    AlgeaIntake.runWheel(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    AlgeaIntake.runWheel(0);
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false; 
  }

}
