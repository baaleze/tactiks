package fr.vahren.engine.type

import fr.vahren.GameEntity
import fr.vahren.engine.attribute.CombatStats
import org.hexworks.amethyst.api.entity.EntityType

interface Combatant : EntityType

// only entities with types that implements Combatant can use this
val GameEntity<Combatant>.combatStats: CombatStats
    get() = findAttribute(CombatStats::class).get()