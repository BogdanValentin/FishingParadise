# Changelog

## 1.2 (2026-09-15)

Supports Minecraft 1.20.4, 1.21.1, 1.21.11 and 26.2. Each version has its own
jar and its own branch; they are not interchangeable.

### Fixed

- **Crafting fish fillets no longer destroys your sword.** The recipe always
  took a sword as an ingredient, and Minecraft consumes every ingredient unless
  the item says otherwise, so it was eaten on every craft. The sword now comes
  back one point of durability worse for wear, keeping its enchantments and
  name. If that last point would break it, it breaks.
- The mod advertised itself as version `1.1` regardless of the actual version,
  because of a typo in the version field.

### Changed

- The four rods now share one bobber and one item class instead of four
  near-identical copies, and what each rod catches lives in a loot table rather
  than in code. Catch rates are unchanged: 25% junk for the bamboo and wooden
  rods, 5% for metal, and a 1 in 3 chance of catching anything at all with
  netherite.
- The fishing line now renders for modded rods via an item tag rather than a
  hardcoded list, so the mixins no longer need editing to add a rod.

### Notes for 1.21.1 and later

- Fish and fillets are in the `minecraft:wolf_food` tag, so wolves still eat
  them. Minecraft removed the "meat" food flag these items previously used.

## 1.1 (2024-03-23)

- Fixed the first advancement not triggering.
- Ported to 1.20, 1.20.1, 1.20.2, 1.20.3 and 1.20.4.

## 1.0 (2024-03-16)

First release, for Minecraft 1.20.4.

- Eight new catchable fish, each with a cooked variant: anchoveta, carp,
  herring, shrimp, tilapia, tuna, crab and starfish.
- Three legendary catches with effects when eaten: anglerfish (night vision),
  octopus (strength) and serpent (speed).
- Seven dishes: fish fillets, cooked fish fillets, fish and chips, fish pie,
  fish stew, seafood and sushi.
- Four fishing rods with their own loot tables and cast textures: bamboo,
  wooden, metal and netherite.
- Advancements, recipes and a creative menu tab.
