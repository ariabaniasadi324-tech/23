package tech.ariabaniasadi.lorestanicatdisc;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public final class LorestaniCatDisc implements ModInitializer {
    public static final String MOD_ID = "lorestani_cat_disc";
    public static final ResourceLocation LORESTANI_ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "lorestani");
    public static final ResourceLocation PHRASE_ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "compro_carros");
    public static final SoundEvent LORESTANI_SOUND = SoundEvent.createVariableRangeEvent(LORESTANI_ID);
    public static final SoundEvent PHRASE_SOUND = SoundEvent.createVariableRangeEvent(PHRASE_ID);
    public static final Item LORESTANI_DISC = new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE));

    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.SOUND_EVENT, LORESTANI_ID, LORESTANI_SOUND);
        Registry.register(BuiltInRegistries.SOUND_EVENT, PHRASE_ID, PHRASE_SOUND);
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, "lorestani_disc"), LORESTANI_DISC);
        CatDanceController.register();
    }
}
