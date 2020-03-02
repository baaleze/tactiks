package fr.vahren.engine.attribute

import fr.vahren.GameEntity
import fr.vahren.engine.GameContext
import fr.vahren.engine.command.EntityAction
import org.hexworks.amethyst.api.Attribute
import org.hexworks.amethyst.api.entity.EntityType
import kotlin.reflect.KClass

/**
 * Defines which actions are allowed to do on another entity
 */
class EntityActions(private vararg val actions: KClass<out EntityAction<out EntityType, out EntityType>>)
    : Attribute {

    fun createActionsFor(context: GameContext, source: GameEntity<EntityType>, target: GameEntity<EntityType>):
            Iterable<EntityAction<out EntityType, out EntityType>> {
        return actions.map {
            try {
                it.constructors.first().call(context, source, target)
            } catch (e: Exception) {
                throw IllegalArgumentException("Can't create EntityAction. Does it have the proper constructor?")
            }
        }
    }
}