package com.applecompose.tipamount.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


//Light
val Primary = Color(0xff4db6ac)
val Secondary = Color(0xFf00897b)
val OnSurfaceVariant = Color(0xFF52443c)
val OnSurface = Color(0xFF201a17)
val OnPrimary = Color(0xFFffffff)
val OnSecondary = Color(0xFFffffff)
val PrimaryVariant = Color(0xFF82e9de)
val NewBackground = Color(0xFFB3E9E4)
val CardBackground = Color(0xFF54928C)

//Dark
val DarkPrimary = Color(0xff00867d)
val DarkSecondary = Color(0xFF005b4f)
val DarkOnSurfaceVariant = Color(0xFFd7c2b8)
val DarkOnSurface = Color(0xFFece0db)
val DarkOnPrimary = Color(0xFFece0db)
val DarkOnSecondary = Color(0xFF33655E)
val DarkPrimaryVariant = Color(0xFF4ebaaa)
val DarkNewBackground = Color(0xFFece0db)




val Amber200 = Color(0xFFffe54c)
val Amber500 = Color(0xFFffb300)
val Amber700 = Color(0xFFc68400)
val LightAmber = Color(0xFFffecb3)

val MediumGreen = Color(0xFF3BAE06)



val Tan = Color(0xFFDDBC83)

val Colors.cardBackground: Color
    @Composable
    get() = if (isLight) CardBackground else MediumGreen

val Colors.mediumGreen: Color
    @Composable
    get() = if (isLight) MediumGreen else MediumGreen


val Colors.newBackgroundColor: Color
    @Composable
    get() = if (isLight) NewBackground else DarkNewBackground

val Colors.fontColor: Color
    @Composable
    get() = if (isLight) DarkOnSecondary else DarkOnPrimary
