package nourl.mythicmetalsdecorations.blocks.chest;

import net.minecraft.block.enums.ChestType;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.SpriteIdentifier;
import nourl.mythicmetalsdecorations.client.MythicMetalsDecorationsClient;

import java.util.*;
import java.util.function.BiConsumer;

public class ChestTextureLayers {
    public static List<EntityModelLayer> modelList = new ArrayList<>();
    public static Map<String, SpriteIdentifier> chestSpriteMap = new HashMap<>();

    /**
     * For each entry in the list assign it a model
     * The list of models is created in order by single chest, left double chest, right double chest
     * @param consumer A bi-consumer for registering the modellayers is passed here
     */
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

    /**
     * Returns a specific chest sprite from our chest sprite map
     * This is filled during {@link MythicMetalsDecorationsClient#createChestModelsAndSprites()}
     * @param name  The name of the chest, stored in {@link MythicChestBlock} and {@link MythicChestBlockEntity}
     * @param type  The {@link ChestType} of the current chest
     * @return      The sprite in the map
     */
    public static SpriteIdentifier getChestIdentifier(String name, ChestType type) {
        return chestSpriteMap.get(name + type.name());
    }

}
