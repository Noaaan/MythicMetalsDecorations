package nourl.mythicmetalsdecorations.blocks.chest;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import nourl.mythicmetalsdecorations.blocks.MythicDecorationSet;
import nourl.mythicmetalsdecorations.utils.RegHelper;

public class MythicChests {
    public static final MythicChestBlock[] BLOCKS = MythicDecorationSet.CHEST_MAP.values().toArray(new MythicChestBlock[0]);
    public static final BlockEntityType<MythicChestBlockEntity> MYTHIC_CHEST_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder.create(MythicChestBlockEntity::new, BLOCKS).build();

    public static void init() {
        RegHelper.blockEntityType("mythic_chest", MYTHIC_CHEST_BLOCK_ENTITY_TYPE);
    }

}
