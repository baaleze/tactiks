package fr.vahren

import org.hexworks.zircon.api.CP437TilesetResources
import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.application.AppConfig
import org.hexworks.zircon.api.data.Size
import org.hexworks.zircon.api.data.Size3D

object GameConfig {

    // game
    const val DUNGEON_LEVELS = 2 // 1

    // look & feel
    val TILESET = CP437TilesetResources.rogueYun16x16() // 2
    val THEME = ColorThemes.arc() // 3
    const val SIDEBAR_WIDTH = 18
    const val LOG_AREA_HEIGHT = 8 // 4

    // sizing
    const val WINDOW_WIDTH = 80
    const val WINDOW_HEIGHT = 50

    val WORLD_SIZE = Size3D.create(100, 100, DUNGEON_LEVELS)

    fun buildAppConfig() = AppConfig.newBuilder()
            .enableBetaFeatures()
            .withDefaultTileset(TILESET)
            .withSize(Size.create(WINDOW_WIDTH, WINDOW_HEIGHT))
            .build()

}
