package fr.vahren.world

import fr.vahren.factory.GameTilesFactory
import kotlinx.collections.immutable.persistentMapOf
import org.hexworks.zircon.api.data.BlockTileType
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.base.BaseBlock

class GameBlock(private var tile: Tile = GameTilesFactory.FLOOR)
    : BaseBlock<Tile>(GameTilesFactory.EMPTY, persistentMapOf(BlockTileType.TOP to tile)) {

    val isFloor: Boolean
        get() = tile == GameTilesFactory.FLOOR


    val isWall: Boolean
        get() = tile == GameTilesFactory.WALL


}
