package fr.vahren.engine

import fr.vahren.engine.attribute.BlockOccupier
import fr.vahren.engine.attribute.EntityActions
import fr.vahren.engine.attribute.EntityPosition
import fr.vahren.engine.attribute.EntityTile
import fr.vahren.engine.command.Dig
import fr.vahren.engine.systems.Diggable
import fr.vahren.engine.systems.MovesCamera
import fr.vahren.engine.systems.InputReceiver
import fr.vahren.engine.systems.Movable
import fr.vahren.engine.type.Player
import fr.vahren.engine.type.Wall
import fr.vahren.factory.GameTileFactory
import org.hexworks.amethyst.api.Entities
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType

fun <T : EntityType> newGameEntityOfType(type: T, init: EntityBuilder<T, GameContext>.() -> Unit) =
        Entities.newEntityOfType(type, init)

object EntityFactory {

    fun newPlayer() = newGameEntityOfType(Player) {
        attributes(EntityPosition(), EntityTile(GameTileFactory.PLAYER), EntityActions(Dig::class))
        behaviors(InputReceiver)
        facets(Movable, MovesCamera)
    }

    fun newWall() = newGameEntityOfType(Wall) {
        attributes(
                EntityPosition(),
                BlockOccupier,
                EntityTile(GameTileFactory.WALL))
        facets(Diggable)
    }

}
