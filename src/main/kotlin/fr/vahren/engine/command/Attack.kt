package fr.vahren.engine.command

import fr.vahren.GameEntity
import fr.vahren.engine.GameContext
import fr.vahren.engine.type.Combatant
import org.hexworks.amethyst.api.entity.EntityType

data class Attack(override val context: GameContext,
                  override val source: GameEntity<Combatant>,
                  override val target: GameEntity<Combatant>) : EntityAction<Combatant, Combatant>
