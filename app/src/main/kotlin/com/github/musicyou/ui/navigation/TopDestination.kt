package com.github.musicyou.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class TopDestination<T : Any>(
    val route: T,
    @StringRes val resourceId: Int,
    val unselectedIcon: ImageVector? = null,
    val selectedIcon: ImageVector? = null,
    @DrawableRes val unselectedDrawable: Int? = null,
    @DrawableRes val selectedDrawable: Int? = null
)