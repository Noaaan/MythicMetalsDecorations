package nourl.mythicmetalsdecorations.blocks.chest;

import net.minecraft.block.enums.ChestType;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.SpriteIdentifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class ChestTextureLayers {
    public static List<EntityModelLayer> modelList = new ArrayList<>();
    public static Map<String, SpriteIdentifier> chestSpriteList = new HashMap<>();

    public static void init(BiConsumer<EntityModelLayer, TexturedModelData> consumer) {
        for (int i = 0; i < modelList.size(); i++) {
            if (i % 3 == 0) {
                consumer.accept(modelList.get(i), MythicChestBlockEntityRenderer.getSingleTexturedModelData());
            }
            if (i % 3 == 1) {
                consumer.accept(modelList.get(i), MythicChestBlockEntityRenderer.getLeftDoubleTexturedModelData());
            }
            if (i % 3 == 2) {
                consumer.accept(modelList.get(i), MythicChestBlockEntityRenderer.getRightDoubleTexturedModelData());
            }

        }
    }

    public static SpriteIdentifier getChestIdentifier(String name, ChestType type) {
        return chestSpriteList.get(name + type.name());
    }

}
