# Fishing Paradise

Fabric mod. One branch per Minecraft version, one tag per release.

| branch | tag | minecraft | java | gradle JVM |
|---|---|---|---|---|
| `1.20.4`  | `1.2-1.20.4`  | 1.20.4  | 17 | `ms-17` |
| `1.21.1`  | `1.2-1.21.1`  | 1.21.1  | 21 | `ms-21` |
| `1.21.11` | `1.2-1.21.11` | 1.21.11 | 21 | `ms-21` |
| `26.2`    | `1.2-26.2`    | 26.2    | 25 | `ms-25` |

`1.20.4` uses Yarn mappings and Gradle 8.6. Every later branch uses Mojang
mappings. `26.2` is unobfuscated, so it uses the `fabric-loom` plugin with no
remapping, while the others use `fabric-loom-remap`.

The Gradle JVM is a project setting, not a branch setting, so it has to be
changed in IntelliJ when switching between branches.

## Conventions

- **Do not write comments in code.** No explanatory comments, no javadoc, no
  inline notes. The code stands on its own. Put the explanation in the commit
  message instead, where it belongs. Comments already written by the repo owner
  stay as they are.
- Never commit or push unless asked.
- Tags are `<mod version>-<minecraft version>`, no `v` prefix.
- Built jars go in `dist/`, which is gitignored. Any jar is rebuildable from its
  tag.
