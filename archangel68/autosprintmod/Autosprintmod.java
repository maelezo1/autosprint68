package archangel68.autosprintmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_2561;

public class Autosprintmod implements ClientModInitializer {
   private static final class_310 client = class_310.method_1551();
   private static boolean enabled = true;

   private void forceSprint() {
      if (enabled && client.field_1690 != null) {
         class_304 sprintKey = client.field_1690.field_1867;
         class_304.method_1416(sprintKey.method_1429(), false);
         class_304.method_1416(sprintKey.method_1429(), true);
         sprintKey.method_23481(true);
      }
   }

   public void onInitializeClient() {
      ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
         dispatcher.register(ClientCommandManager.literal("autosprint68").executes(context -> {
            enabled = !enabled;
            context.getSource().sendFeedback(
               class_2561.method_43470(enabled ? "AutoSprint - On" : "AutoSprint - Off")
            );
            return 1;
         }));
      });

      ClientTickEvents.START_CLIENT_TICK.register((ClientTickEvents.StartTick)(mc) -> {
         forceSprint();
      });

      ClientTickEvents.END_CLIENT_TICK.register((ClientTickEvents.EndTick)(mc) -> {
         forceSprint();
      });
   }
}
