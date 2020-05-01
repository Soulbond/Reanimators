package com.unco.reanimators.command.commands;

import com.unco.reanimators.command.Command;
import com.unco.reanimators.command.syntax.ChunkBuilder;
import com.unco.reanimators.module.modules.render.Pathfind;
import net.minecraft.pathfinding.PathPoint;

public class PathCommand extends Command {
    public PathCommand() {
        super("path", new ChunkBuilder().append("x").append("y").append("z").build());
    }

    int x = Integer.MIN_VALUE;
    int y = Integer.MIN_VALUE;
    int z = Integer.MIN_VALUE;

    @Override
    public void call(String[] args) {
        if (args[0] != null && args[0].equalsIgnoreCase("retry")) {
            if (x != Integer.MIN_VALUE) {
                PathPoint end = new PathPoint(x, y, z);
                Pathfind.createPath(end);
                if (!Pathfind.points.isEmpty())
                    Command.sendChatMessage("Path created!");
                return;
            }else{
                Command.sendChatMessage("No location to retry pathfinding to.");
                return;
            }
        }
        if (args.length <= 3) {
            Command.sendChatMessage("&cMissing arguments: x, y, z");
            return;
        }
        try {
            x = Integer.parseInt(args[0]);
            y = Integer.parseInt(args[1]);
            z = Integer.parseInt(args[2]);

            PathPoint end = new PathPoint(x, y, z);
            Pathfind.createPath(end);
            if (!Pathfind.points.isEmpty())
                Command.sendChatMessage("Path created!");
        }catch (NumberFormatException e) {
            Command.sendChatMessage("Error: input must be numerical");
            return;
        }
    }
}
