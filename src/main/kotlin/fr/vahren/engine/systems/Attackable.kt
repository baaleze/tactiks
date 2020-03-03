package fr.vahren.engine.systems

import fr.vahren.GameCommand
import fr.vahren.engine.GameContext
import fr.vahren.engine.command.Attack
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType

object Attackable : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(Attack::class) { (context, attacker, target) ->
        context.world.removeEntity(target)
        Consumed
    }
}
