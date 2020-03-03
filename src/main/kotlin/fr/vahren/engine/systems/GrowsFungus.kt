package fr.vahren.engine.systems

import fr.vahren.GameEntity
import fr.vahren.engine.EntityFactory
import fr.vahren.engine.GameContext
import fr.vahren.engine.attribute.FungusSpread
import fr.vahren.engine.position
import fr.vahren.engine.tryToFindAttribute
import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.Size3D

object GrowsFungus : BaseBehavior<GameContext>(FungusSpread::class) {

    override fun update(entity: GameEntity<out EntityType>, context: GameContext): Boolean {
        val world = context.world
        val fungusSpread = entity.tryToFindAttribute(FungusSpread::class)
        val (spreadCount, maxSpread) = fungusSpread
        return if (spreadCount < maxSpread && Math.random() < 0.015) {
            world.findEmptyLocationWithin(
                    offset = entity.position
                            .withRelativeX(-1)
                            .withRelativeY(-1),
                    size = Size3D.create(3, 3, 0)).map { emptyLocation ->
                world.addEntity(EntityFactory.newFungus(fungusSpread), emptyLocation)
                fungusSpread.spreadCount++
            }
            true
        } else false
    }
}
