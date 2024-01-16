/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entechlib.commands;

import org.entechlib.subsystems.EntechSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class EntechCommand extends Command {
    public EntechCommand() {
    }

    public EntechCommand(EntechSubsystem subsystem) {
        addRequirements(subsystem);
    }

    public EntechCommand(EntechSubsystem subsystem1, EntechSubsystem subsystem2) {
        addRequirements(subsystem1, subsystem2);
    }

    public EntechCommand(EntechSubsystem subsystem1, EntechSubsystem subsystem2, EntechSubsystem subsystem3) {
        addRequirements(subsystem1, subsystem2, subsystem3);
    }
}