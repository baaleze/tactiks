package fr.vahren.engine.type

import org.hexworks.amethyst.api.base.BaseEntityType

object Player : BaseEntityType(
        name = "player"), Combatant

object Wall : BaseEntityType(
        name = "wall")

object Fungus : BaseEntityType(
        name = "fungus"), Combatant