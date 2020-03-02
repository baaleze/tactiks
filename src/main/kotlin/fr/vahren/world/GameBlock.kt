package fr.vahren.world

import fr.vahren.GameEntity
import fr.vahren.engine.occupiesBlock
import fr.vahren.engine.tile
import fr.vahren.factory.GameTileFactory
import kotlinx.collections.immutable.persistentMapOf
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.zircon.api.data.BlockTileType
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.base.BaseBlock

class GameBlock(
        private var tile: Tile = GameTileFactory.FLOOR,
        private val currentEntities: MutableList<GameEntity<EntityType>> = mutableListOf())
    : BaseBlock<Tile>(GameTileFactory.EMPTY, persistentMapOf()) {

    val isFloor: Boolean
        get() = tile == GameTileFactory.FLOOR

    val isEmptyFloor: Boolean // 2
        get() = currentEntities.isEmpty() && isFloor

    val occupier: Maybe<GameEntity<EntityType>>
        get() = Maybe.ofNullable(currentEntities.firstOrNull { it.occupiesBlock })  // 1

    val isOccupied: Boolean
        get() = occupier.isPresent

    val entities: Iterable<GameEntity<EntityType>> // 3
        get() = currentEntities.toList()

    override fun getTileByType(blockTileType: BlockTileType): Tile {
        val entityTiles = currentEntities.map { it.tile }
        return when {
            entityTiles.contains(GameTileFactory.PLAYER) -> GameTileFactory.PLAYER
            entityTiles.isNotEmpty() -> entityTiles.first()
            else -> tile
        }
    }

    fun addEntity(entity: GameEntity<EntityType>) { // 8
        currentEntities.add(entity)
    }

    fun removeEntity(entity: GameEntity<EntityType>) { // 9
        currentEntities.remove(entity)
    }

    companion object {
        fun createWith(entity: GameEntity<EntityType>) = GameBlock(
                currentEntities = mutableListOf(entity))
    }

}
