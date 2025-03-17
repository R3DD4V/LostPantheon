package pantheon.modid;

import net.fabricmc.api.ClientModInitializer;
import pantheon.modid.blocks.modBlocks;
import pantheon.modid.items.modItems;

import static pantheon.modid.KeyBindings.registerClientTickEvent;

public class LostPantheonClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		modItems.registerModItems();
		KeyBindings.InitializeKeyBindings();
		registerClientTickEvent();
		modBlocks.registerModBlocks();
	}


}