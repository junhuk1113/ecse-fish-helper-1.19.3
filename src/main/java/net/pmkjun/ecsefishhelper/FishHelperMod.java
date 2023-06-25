package net.pmkjun.ecsefishhelper;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FishHelperMod implements ModInitializer {
	public static final String MODID = "ecse-fish-helper";

	public static final Logger LOGGER = LogUtils.getLogger();

	public FishHelperClient client;

	@Override
	public void onInitialize() {
		this.client = new FishHelperClient();
		this.client.init();

	}
}