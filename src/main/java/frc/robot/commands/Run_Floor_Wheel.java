package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Floor_Intake;

public class Run_Floor_Wheel extends Command {

    private final double speed;
    private final Floor_Intake Floor_Intake;

    public Run_Floor_Wheel(Floor_Intake Floor_Intake, double speed) {
        this.Floor_Intake = Floor_Intake;
        this.speed = speed; 
        addRequirements(Floor_Intake);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        Floor_Intake.run_Floor_Wheels(speed);
    }

    @Override
    public void end(boolean interrupted) {
        Floor_Intake.run_Floor_Wheels(0);
    }

    @Override
    public boolean isFinished() {
        return false;  // Command finishes after 1 second
    }
}
