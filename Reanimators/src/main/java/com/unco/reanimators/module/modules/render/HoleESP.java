package com.unco.reanimators.module.modules.render;

import com.unco.reanimators.module.Module;
import com.unco.reanimators.module.ModuleManager;
import com.unco.reanimators.event.events.RenderEvent;
import com.unco.reanimators.util.GeometryMasks;
import com.unco.reanimators.util.ReanimatorsTessellator;
import com.unco.reanimators.setting.Setting;
import com.unco.reanimators.setting.Settings;
import com.unco.reanimators.module.modules.combat.TurokCrystal;
import static com.unco.reanimators.module.modules.combat.TurokCrystal.get_player_pos;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

// Rina.
@Module.Info(name = "HoleESP", category = Module.Category.RENDER)
public class HoleESP extends Module {
	private ConcurrentHashMap<BlockPos, Boolean> safe_holes;
	private final BlockPos[] barrier_ = {
		new BlockPos(0, -1, 0),
		new BlockPos(0, 0, -1),
		new BlockPos(1, 0, 0),
		new BlockPos(0, 0, 1),
		new BlockPos(-1, 0, 0)
	};

	private Setting<Double> range = register(Settings.d("Range", 10.0d));

	@Override
	public void onUpdate() {
		if (safe_holes == null) {
			safe_holes = new ConcurrentHashMap<>();
		} else {
			safe_holes.clear();
		}

		int range_ = (int) Math.ceil(range.getValue());

		TurokCrystal crystal_function = (TurokCrystal) ModuleManager.getModuleByName("TurokCrystal");
		List<BlockPos> block_pos = crystal_function.get_sphere(get_player_pos(), range_, range_, false, true, 0);

		for (BlockPos pos : block_pos) {
			if (!mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR)) continue;
			if (!mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) continue;
			if (!mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) continue;

			boolean safe = true;
			boolean bedrock = true;

			for (BlockPos offset : barrier_) {
				Block block = mc.world.getBlockState(pos.add(offset)).getBlock();
				if (block != Blocks.BEDROCK) bedrock = false;
				if (block != Blocks.BEDROCK && block != Blocks.OBSIDIAN && block != Blocks.ENDER_CHEST && block != Blocks.ANVIL) {
					safe = false;
					break;
				}
			}

			if (safe) safe_holes.put(pos, bedrock);
		}
	}

	@Override
	public void onWorldRender(final RenderEvent event) {
		if (mc.player == null || safe_holes == null) return;
		if (safe_holes.isEmpty()) return;

		ReanimatorsTessellator.prepare(GL11.GL_QUADS);
		safe_holes.forEach((block_pos, bedrock) -> {
			draw(block_pos, 255, 255, 255);
			draw(block_pos, 255, 255, 255);
			draw(block_pos, 255, 255, 255);
		});
		ReanimatorsTessellator.release();
	}

	private void draw(BlockPos block_pos, int r, int g, int b) {
		Color color = new Color(r, g, b, 75);
		ReanimatorsTessellator.drawBox(block_pos, color.getRGB(), GeometryMasks.Quad.ALL);
	}
}