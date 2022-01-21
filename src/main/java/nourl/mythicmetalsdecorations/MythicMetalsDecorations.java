package nourl.mythicmetalsdecorations;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChestBase;
import nourl.mythicmetalsdecorations.blocks.chest.MythicChestBlockEntity;
import nourl.mythicmetalsdecorations.blocks.Decorations;
import nourl.mythicmetalsdecorations.utils.RegistryHelper;

public class MythicMetalsDecorations implements ModInitializer {
    private static BlockEntityType<MythicChestBlockEntity> blockEntityType;

    public static BlockEntityType<MythicChestBlockEntity> getBlockEntityType() {
        return blockEntityType;
    }

    public static final String MOD_ID = "mythicmetals_decorations";

    public static final ItemGroup MYTHICMETALS_DECOR = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "decorations")).icon(() -> new ItemStack(Decorations.ADAMANTITE.getChain())).build();

    @Override
    public void onInitialize() {
        Decorations.init();
        blockEntityType = FabricBlockEntityTypeBuilder.create((pos, state) -> new MythicChestBlockEntity(MythicMetalsDecorations.getBlockEntityType(), pos, state)).build();
        RegistryHelper.blockEntityType("chestus_entity", blockEntityType);

        RegistryHelper.block("adamantite_chest", new MythicChestBase(AbstractBlock.Settings.copy(Blocks.CHEST), () -> blockEntityType, 54), MYTHICMETALS_DECOR);

    }
}
