package fr.vahren.engine.attribute

import org.hexworks.amethyst.api.Attribute
import org.hexworks.zircon.api.data.Tile

data class EntityTile(val tile: Tile = Tile.empty()) : Attribute
