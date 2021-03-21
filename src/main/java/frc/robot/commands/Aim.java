// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.ShooterSub;

import static frc.robot.Constants.*;

public class Aim extends CommandBase {
  /** Creates a new Aim. */
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSub m_subsystem;

  private Boolean m_goUP = true;

  public Aim(ShooterSub subsystems, Boolean UP) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_goUP = UP;
    m_subsystem = subsystems;
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = 0.0;
    if(m_goUP){
      speed = k_aimSpeed;
    }
    else{
      speed = -1.0 * k_aimSpeed;      
    }
    m_subsystem.positionMotorOn(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
