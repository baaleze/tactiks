package fr.vahren.world

import fr.vahren.GameEntity
import fr.vahren.engine.tile
import fr.vahren.factory.GameTileFactory
import kotlinx.collections.immutable.persistentMapOf
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.BlockTileType
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.base.BaseBlock

class GameBlock(
        private var tile: Tile = GameTileFactory.FLOOR,
        private val currentEntities: MutableList<GameEntity<EntityType>> = mutableListOf())
    : BaseBlock<Tile>(GameTileFactory.EMPTY, persistentMapOf(BlockTileType.TOP to tile)) {

    val isFloor: Boolean
        get() = tile == GameTileFactory.FLOOR


    val isWall: Boolean
        get() = tile == GameTileFactory.WALL

    val isEmptyFloor: Boolean // 2
        get() = currentEntities.isEmpty()

    val entities: Iterable<GameEntity<EntityType>> // 3
        get() = currentEntities.toList()

    override var content: Tile = tile
        get() {
            val entityTiles = currentEntities.map { it.tile }
            return when {
                entityTiles.contains(GameTileFactory.PLAYER) -> GameTileFactory.PLAYER // 5
                entityTiles.isNotEmpty() -> entityTiles.first() // 6
                else -> tile // 7
            }
        }


    fun addEntity(entity: GameEntity<EntityType>) { // 8
        currentEntities.add(entity)
    }

    fun removeEntity(entity: GameEntity<EntityType>) { // 9
        currentEntities.remove(entity)
    }
}
