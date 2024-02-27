# 0.6.0

- Fixed a bug where the Stormyx Chain would not animate correctly.
- Chests now extend ChestBlocks instead of AbstractChestBlocks. This should improve compatibility and reduce the amount of crashes.
- Chests screens no longer force scrolling when leaking by an extra row (thanks glisco!).
- Chest recipes now use the `c:wooden_chests` tag for crafting recipes.
- Added a block set for Hydrargym (Storage Block, Anvil, nuggets).
- Add load conditions for nugget-related recipes. This prevents errors in logs if they are disabled.