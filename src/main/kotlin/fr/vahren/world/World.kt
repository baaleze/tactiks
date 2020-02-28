package fr.vahren.world

import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.game.base.BaseGameArea

class World(
        startingBlocks: Map<Position3D, GameBlock>,
        visibleSize: Size3D,
        actualSize: Size3D)
    : BaseGameArea<Tile, GameBlock>(visibleSize, actualSize, startingBlocks)