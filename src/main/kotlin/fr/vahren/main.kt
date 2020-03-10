package fr.vahren

import fr.vahren.view.PlayView
import org.hexworks.zircon.api.SwingApplications

fun main() {
    val tileGrid = SwingApplications.startTileGrid(GameConfig.buildAppConfig())

    tileGrid.dock(PlayView(tileGrid))
}
