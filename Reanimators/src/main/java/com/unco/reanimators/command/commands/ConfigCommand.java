package com.unco.reanimators.command.commands;

import com.unco.reanimators.Reanimators;
import com.unco.reanimators.command.Command;
import com.unco.reanimators.command.syntax.ChunkBuilder;
import com.unco.reanimators.command.syntax.parsers.DependantParser;
import com.unco.reanimators.command.syntax.parsers.EnumParser;
import com.unco.reanimators.gui.reanimators.ReanimatorsGUI;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigCommand extends Command {

    public ConfigCommand() {
        super("config", new ChunkBuilder()
                .append("mode", true, new EnumParser(new String[]{"reload", "save", "path"}))
                .append("path", true, new DependantParser(0, new DependantParser.Dependency(new String[][]{{"path", "path"}}, "")))
                .build());
    }

    @Override
    public void call(String[] args) {
        if (args[0] == null) {
            Command.sendChatMessage("Missing argument &bmode&r: Choose from reload, save or path");
            return;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                reload();
                break;
            case "save":
                try {
                    Reanimators.saveConfigurationUnsafe();
                    Command.sendChatMessage("Saved configuration!");
                } catch (IOException e) {
                    e.printStackTrace();
                    Command.sendChatMessage("Failed to save! " + e.getMessage());
                }
                break;
            case "path":
                if (args[1] == null) {
                    Path file = Paths.get(Reanimators.getConfigName());
                    Command.sendChatMessage("Path to configuration: &b" + file.toAbsolutePath().toString());
                } else {
                    String newPath = args[1];
                    if (!Reanimators.isFilenameValid(newPath)) {
                        Command.sendChatMessage("&b" + newPath + "&r is not a valid path");
                        break;
                    }
                    try(BufferedWriter writer = Files.newBufferedWriter(Paths.get("ReanimatorsLastConfig.txt"))) {
                        writer.write(newPath);
                        reload();
                        Command.sendChatMessage("Configuration path set to &b" + newPath + "&r!");
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                        Command.sendChatMessage("Couldn't set path: " + e.getMessage());
                        break;
                    }
                }
                break;
            default:
                Command.sendChatMessage("Incorrect mode, please choose from: reload, save or path");
        }
    }

    private void reload() {
        Reanimators.getInstance().guiManager = new ReanimatorsGUI();
        Reanimators.getInstance().guiManager.initializeGUI();
        Reanimators.loadConfiguration();
        Command.sendChatMessage("Configuration reloaded!");
    }

}
