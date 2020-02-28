package fr.vahren.world

import fr.vahren.GameEntity
import fr.vahren.engine.GameContext
import fr.vahren.engine.position
import org.hexworks.amethyst.api.Engine
import org.hexworks.amethyst.api.Engines
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.game.base.BaseGameArea

class World(
        startingBlocks: Map<Position3D, GameBlock>,
        visibleSize: Size3D,
        actualSize: Size3D)
    : BaseGameArea<Tile, GameBlock>(visibleSize, actualSize, startingBlocks) {

    private val engine: Engine<GameContext> = Engines.newEngine() // 1

    init {
        startingBlocks.forEach { pos, block ->
            block.entities.forEach { entity ->
                engine.addEntity(entity) // 2
                entity.position = pos // 3
            }
        }
    }

    /**
     * Adds the given [Entity] at the given [Position3D].
     * Has no effect if this world already contains the
     * given [Entity].
     */
    fun addEntity(entity: Entity<EntityType, GameContext>, position: Position3D) { // 4
        entity.position = position
        engine.addEntity(entity)
        fetchBlockAt(position).map {
            it.addEntity(entity)
        }
    }

    fun addAtEmptyPosition(entity: GameEntity<EntityType>, // 5
                           offset: Position3D = Position3D.defaultPosition(),
                           size: Size3D = actualSize): Boolean {
        return findEmptyLocationWithin(offset, size).fold(
                whenEmpty = { false },
                whenPresent = { location ->  // 7
                    addEntity(entity, location)
                    true
                })

    }

    /**
     * Finds an empty location within the given area (offset and size) on this [World].
     */
    fun findEmptyLocationWithin(offset: Position3D, size: Size3D): Maybe<Position3D> { // 8
        var position = Maybe.empty<Position3D>()
        val maxTries = 10
        var currentTry = 0
        while (position.isPresent.not() && currentTry < maxTries) {
            val pos = Position3D.create(
                    x = (Math.random() * size.xLength).toInt() + offset.x,
                    y = (Math.random() * size.yLength).toInt() + offset.y,
                    z = (Math.random() * size.zLength).toInt() + offset.z)
            fetchBlockAt(pos).map {
                if (it.isEmptyFloor) {
                    position = Maybe.of(pos)
                }
            }
            currentTry++
        }
        return position
    }



}
