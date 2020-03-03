package fr.vahren.engine

import fr.vahren.engine.attribute.*
import fr.vahren.engine.command.Attack
import fr.vahren.engine.command.Dig
import fr.vahren.engine.systems.*
import fr.vahren.engine.type.Fungus
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
        attributes(EntityPosition(),
                EntityTile(GameTileFactory.PLAYER),
                EntityActions(Dig::class, Attack::class),
                CombatStats.create(
                        maxHp = 100,
                        attackValue = 10,
                        defenseValue = 5))
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

    fun newFungus(fungusSpread: FungusSpread = FungusSpread()) = newGameEntityOfType(Fungus) {
        attributes(BlockOccupier,
                EntityPosition(),
                EntityTile(GameTileFactory.FUNGUS),
                fungusSpread,
                CombatStats.create(
                        maxHp = 10,
                        attackValue = 0,
                        defenseValue = 0))
        facets(Attackable, Destroyable)
        behaviors()
    }


}
