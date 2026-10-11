package tech.ariabaniasadi.lorestanicatdisc;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.phys.AABB;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class CatDanceController {
    private static final Map<UUID, Integer> DANCING = new ConcurrentHashMap<>();

    private CatDanceController() {}

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                AABB area = player.getBoundingBox().inflate(12.0);
                for (Cat cat : player.level().getEntitiesOfClass(Cat.class, area)) {
                    if (!DANCING.containsKey(cat.getUUID())) {
                        cat.teleportTo(player.getX() + 1.0, player.getY() + 1.0, player.getZ());
                        cat.setDeltaMovement(0, 0.08, 0);
                        DANCING.put(cat.getUUID(), 400);
                    }
                }
            }
            DANCING.replaceAll((id, ticks) -> ticks - 1);
            server.getAllLevels().forEach(level -> level.getEntities().getAll().forEach(entity -> {
                if (entity instanceof Cat cat && DANCING.containsKey(cat.getUUID())) {
                    int left = DANCING.get(cat.getUUID());
                    cat.setNoGravity(true);
                    cat.setDeltaMovement(Math.sin((400 - left) * 0.3) * 0.12, 0.03, Math.cos((400 - left) * 0.3) * 0.12);
                    if (left <= 0) {
                        cat.setNoGravity(false);
                        DANCING.remove(cat.getUUID());
                        cat.playSound(LorestaniCatDisc.PHRASE_SOUND, 1.0f, 1.0f);
                    }
                }
            }));
        });
    }
}
