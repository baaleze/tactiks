package fr.vahren

import fr.vahren.view.MainMenuView
import fr.vahren.view.PlayView
import org.hexworks.zircon.api.CP437TilesetResources
import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.SwingApplications
import org.hexworks.zircon.api.application.AppConfig
import org.hexworks.zircon.api.extensions.toScreen
import org.hexworks.zircon.api.screen.Screen

fun main(args: Array<String>) {
    val tileGrid = SwingApplications.startTileGrid(GameConfig.buildAppConfig())

    tileGrid.dock(PlayView(tileGrid))
}
