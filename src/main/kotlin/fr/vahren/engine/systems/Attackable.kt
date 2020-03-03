package fr.vahren.engine.systems

import fr.vahren.GameCommand
import fr.vahren.engine.GameContext
import fr.vahren.engine.command.Attack
import fr.vahren.engine.command.Destroy
import fr.vahren.engine.isPlayer
import fr.vahren.engine.type.combatStats
import fr.vahren.engine.whenHasNoHealthLeft
import fr.vahren.logGameEvent
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType

object Attackable : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(Attack::class) { (context, attacker, target) ->
        if (attacker.isPlayer || target.isPlayer) { // 1
            val finalDamage = (0..(attacker.combatStats.attackValue - target.combatStats.defenseValue)).random() + 1

            target.combatStats.hp -= finalDamage
            logGameEvent("The $attacker hits the $target for $finalDamage!", context)

            target.whenHasNoHealthLeft {
                target.executeCommand(Destroy(
                        context = context,
                        source = attacker,
                        target = target,
                        cause = "a blow to the head"))
            }
            Consumed
        } else Pass
    }
}
