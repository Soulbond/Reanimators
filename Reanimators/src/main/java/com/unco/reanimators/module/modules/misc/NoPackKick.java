package com.unco.reanimators.module.modules.misc;

import com.unco.reanimators.module.Module;

@Module.Info(name = "NoPackKick", category = Module.Category.MISC)
public class NoPackKick {
	private static NoPackKick INSTANCE;

	public NoPackKick() {
		INSTANCE = this;
	}

	public static boolean isEnabled() {
		return INSTANCE.isEnabled();
	}
}