package fr.vahren.engine.systems

import fr.vahren.GameCommand
import fr.vahren.engine.GameContext
import fr.vahren.engine.command.Destroy
import fr.vahren.logGameEvent
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType

object Destroyable : BaseFacet<GameContext>() {

    override suspend fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(Destroy::class) { (context, attacker, target, cause) ->
        context.world.removeEntity(target)
        logGameEvent("$target dies from $cause.", context)
        Consumed
    }
}
