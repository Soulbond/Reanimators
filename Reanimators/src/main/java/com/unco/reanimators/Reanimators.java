package com.unco.reanimators;

import com.google.common.base.Converter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;
import com.unco.reanimators.command.Command;
import com.unco.reanimators.command.CommandManager;
import com.unco.reanimators.event.ForgeEventProcessor;
import com.unco.reanimators.gui.reanimators.ReanimatorsGUI;
import com.unco.reanimators.gui.rgui.component.AlignedComponent;
import com.unco.reanimators.gui.rgui.component.Component;
import com.unco.reanimators.gui.rgui.component.container.Container;
import com.unco.reanimators.gui.rgui.component.container.use.Frame;
import com.unco.reanimators.gui.rgui.util.ContainerHelper;
import com.unco.reanimators.gui.rgui.util.Docking;
import com.unco.reanimators.module.Module;
import com.unco.reanimators.module.ModuleManager;
import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.setting.Settings;
import com.unco.reanimators.setting.SettingsRegister;
import com.unco.reanimators.setting.config.Configuration;
import com.unco.reanimators.util.Friends;
import com.unco.reanimators.util.LagCompensator;
import com.unco.reanimators.util.Wrapper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Mod(modid = Reanimators.MODID, name = Reanimators.MODNAME, version = Reanimators.MODVER)
public class Reanimators {

    public static final String MODID = "reanimators";
    public static final String MODNAME = "Reanimators";
    public static final String MODVER = "b3";

    private static final String REANIMATORS_CONFIG_NAME_DEFAULT = "ReanimatorsBinds.json";

    public static final Logger log = LogManager.getLogger("Reanimators");

    public static final EventBus EVENT_BUS = new EventManager();

    @Mod.Instance
    private static Reanimators INSTANCE;

    public ReanimatorsGUI guiManager;
    public CommandManager commandManager;
    private Setting<JsonObject> guiStateSetting = Settings.custom("gui", new JsonObject(), new Converter<JsonObject, JsonObject>() {
        @Override
        protected JsonObject doForward(JsonObject jsonObject) {
            return jsonObject;
        }

        @Override
        protected JsonObject doBackward(JsonObject jsonObject) {
            return jsonObject;
        }
    }).buildAndRegister("");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {}

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Reanimators.log.info("\n\nInitializing Reanimators " + MODVER);

        ModuleManager.initialize();

        ModuleManager.getModules().stream().filter(module -> module.alwaysListening).forEach(EVENT_BUS::subscribe);
        MinecraftForge.EVENT_BUS.register(new ForgeEventProcessor());
        LagCompensator.INSTANCE = new LagCompensator();

        Wrapper.init();

        guiManager = new ReanimatorsGUI();
        guiManager.initializeGUI();

        commandManager = new CommandManager();

        Friends.initFriends();
        SettingsRegister.register("commandPrefix", Command.commandPrefix);
        loadConfiguration();
        Reanimators.log.info("Settings loaded");

        ModuleManager.updateLookup();
        ModuleManager.getModules().stream().filter(Module::isEnabled).forEach(Module::enable);

        Reanimators.log.info("Reanimators initialized!\n");
    }

    public static String getConfigName() {
        Path config = Paths.get("ReanimatorsCache.txt");
        String reanimatorsConfigName = REANIMATORS_CONFIG_NAME_DEFAULT;
        try(BufferedReader reader = Files.newBufferedReader(config)) {
            reanimatorsConfigName = reader.readLine();
            if (!isFilenameValid(reanimatorsConfigName)) reanimatorsConfigName = REANIMATORS_CONFIG_NAME_DEFAULT;
        } catch (NoSuchFileException e) {
            try(BufferedWriter writer = Files.newBufferedWriter(config)) {
                writer.write(REANIMATORS_CONFIG_NAME_DEFAULT);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reanimatorsConfigName;
    }

    public static void loadConfiguration() {
        try {
            loadConfigurationUnsafe();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadConfigurationUnsafe() throws IOException {
        String reanimatorsConfigName = getConfigName();
        Path reanimatorsConfig = Paths.get(reanimatorsConfigName);
        if (!Files.exists(reanimatorsConfig)) return;
        Configuration.loadConfiguration(reanimatorsConfig);

        JsonObject gui = Reanimators.INSTANCE.guiStateSetting.getValue();
        for (Map.Entry<String, JsonElement> entry : gui.entrySet()) {
            Optional<Component> optional = Reanimators.INSTANCE.guiManager.getChildren().stream().filter(component -> component instanceof Frame).filter(component -> ((Frame) component).getTitle().equals(entry.getKey())).findFirst();
            if (optional.isPresent()) {
                JsonObject object = entry.getValue().getAsJsonObject();
                Frame frame = (Frame) optional.get();
                frame.setX(object.get("x").getAsInt());
                frame.setY(object.get("y").getAsInt());
                Docking docking = Docking.values()[object.get("docking").getAsInt()];
                if (docking.isLeft()) ContainerHelper.setAlignment(frame, AlignedComponent.Alignment.LEFT);
                else if (docking.isRight()) ContainerHelper.setAlignment(frame, AlignedComponent.Alignment.RIGHT);
                else if (docking.isCenterVertical()) ContainerHelper.setAlignment(frame, AlignedComponent.Alignment.CENTER);
                frame.setDocking(docking);
                frame.setMinimized(object.get("minimized").getAsBoolean());
                frame.setPinned(object.get("pinned").getAsBoolean());
            } else {
                System.err.println("Found GUI config entry for " + entry.getKey() + ", but found no frame with that name");
            }
        }
        Reanimators.getInstance().getGuiManager().getChildren().stream().filter(component -> (component instanceof Frame) && (((Frame) component).isPinneable()) && component.isVisible()).forEach(component -> component.setOpacity(0f));
    }

    public static void saveConfiguration() {
        try {
            saveConfigurationUnsafe();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveConfigurationUnsafe() throws IOException {
        JsonObject object = new JsonObject();
        Reanimators.INSTANCE.guiManager.getChildren().stream().filter(component -> component instanceof Frame).map(component -> (Frame) component).forEach(frame -> {
            JsonObject frameObject = new JsonObject();
            frameObject.add("x", new JsonPrimitive(frame.getX()));
            frameObject.add("y", new JsonPrimitive(frame.getY()));
            frameObject.add("docking", new JsonPrimitive(Arrays.asList(Docking.values()).indexOf(frame.getDocking())));
            frameObject.add("minimized", new JsonPrimitive(frame.isMinimized()));
            frameObject.add("pinned", new JsonPrimitive(frame.isPinned()));
            object.add(frame.getTitle(), frameObject);
        });
        Reanimators.INSTANCE.guiStateSetting.setValue(object);

        Path outputFile = Paths.get(getConfigName());
        if (!Files.exists(outputFile))
            Files.createDirectories(Paths.get("ReanimatorsConfig/"));
            Files.createFile(outputFile);
        Configuration.saveConfiguration(outputFile);
        ModuleManager.getModules().forEach(Module::destroy);
    }

    public static boolean isFilenameValid(String file) {
        File f = new File(file);
        try {
            f.getCanonicalPath();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static Reanimators getInstance() {
        return INSTANCE;
    }

    public ReanimatorsGUI getGuiManager() {
        return guiManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
