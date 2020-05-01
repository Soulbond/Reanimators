package com.unco.reanimators.command.commands;

import com.unco.reanimators.command.Command;
import com.unco.reanimators.command.syntax.ChunkBuilder;
import com.unco.reanimators.command.syntax.parsers.ModuleParser;
import com.unco.reanimators.module.Module;
import com.unco.reanimators.module.ModuleManager;

public class ToggleCommand extends Command {

    public ToggleCommand() {
        super("t", new ChunkBuilder()
                .append("module", true, new ModuleParser())
                .build());
    }

    @Override
    public void call(String[] args) {
        if (args.length == 0) {
            Command.sendChatMessage("Please specify a module!");
            return;
        }
        Module m = ModuleManager.getModuleByName(args[0]);
        if (m == null) {
            Command.sendChatMessage("Unknown module '" + args[0] + "'");
            return;
        }
        m.toggle();
        Command.sendChatMessage(m.getName() + (m.isEnabled() ? " &aenabled" : " &cdisabled"));
    }
}
