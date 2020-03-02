package fr.vahren.engine

import fr.vahren.AnyGameEntity
import fr.vahren.engine.attribute.BlockOccupier
import fr.vahren.engine.attribute.EntityActions
import fr.vahren.engine.attribute.EntityPosition
import fr.vahren.engine.attribute.EntityTile
import org.hexworks.amethyst.api.Attribute
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.zircon.api.data.Tile
import kotlin.reflect.KClass

var AnyGameEntity.position // 1
    get() = tryToFindAttribute(EntityPosition::class).position // 2
    set(value) { // 3
        findAttribute(EntityPosition::class).map {
            it.position = value
        }
    }

val AnyGameEntity.tile: Tile
    get() = this.tryToFindAttribute(EntityTile::class).tile

fun <T : Attribute> AnyGameEntity.tryToFindAttribute(klass: KClass<T>): T = findAttribute(klass).orElseThrow {
    NoSuchElementException("Entity '$this' has no property with type '${klass.simpleName}'.")
}

val AnyGameEntity.occupiesBlock: Boolean
    get() = findAttribute(BlockOccupier::class).isPresent

fun AnyGameEntity.tryActionsOn(context: GameContext, target: AnyGameEntity): Response {
    var result: Response = Pass
    findAttribute(EntityActions::class).map {
        it.createActionsFor(context, this, target).forEach { action ->
            if (target.executeCommand(action) is Consumed) {
                result = Consumed
                return@forEach
            }
        }
    }
    return result
}

