package fr.vahren.view

import fr.vahren.world.GameBlock
import fr.vahren.GameConfig
import fr.vahren.world.Game
import fr.vahren.world.GameBuilder
import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.ComponentDecorations
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.GameComponents
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.uievent.KeyboardEventType
import org.hexworks.zircon.api.uievent.Processed
import org.hexworks.zircon.api.view.base.BaseView

class PlayView(private val tileGrid: TileGrid, private val game: Game = GameBuilder.defaultGame()) : BaseView(tileGrid, ColorThemes.arc()) {

    override fun onDock() {
        val sideBar = Components.panel()
                .withSize(GameConfig.SIDEBAR_WIDTH, GameConfig.WINDOW_HEIGHT)
                .withDecorations(ComponentDecorations.box(BoxType.DOUBLE, "Info"))
        val log = Components.logArea()
                .withSize(GameConfig.WINDOW_WIDTH - GameConfig.SIDEBAR_WIDTH, GameConfig.LOG_AREA_HEIGHT)
                .withAlignmentWithin(screen, ComponentAlignment.BOTTOM_RIGHT)
                .withDecorations(ComponentDecorations.box(BoxType.SINGLE, "Log"))
        val gameComponent = GameComponents.newGameComponentBuilder<Tile, GameBlock>()
                .withGameArea(game.world)
                .withAlignmentWithin(screen, ComponentAlignment.TOP_RIGHT)

        screen.addComponents(sideBar, log, gameComponent)

        // inputs
        tileGrid.processKeyboardEvents(KeyboardEventType.KEY_PRESSED) { event, _ ->
            game.world.update(screen, event, game)
            Processed
        }


    }
}