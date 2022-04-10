package com.applecompose.tipamount.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFdf78ef)
val Purple500 = Color(0xFFab47bc)
val Purple700 = Color(0xFF790e8b)
val Teal200 = Color(0xFF03DAC5)

val Amber200 = Color(0xFFffe54c)
val Amber500 = Color(0xFFffb300)
val Amber700 = Color(0xFFc68400)
val LightAmber = Color(0xFFffecb3)

val MediumGreen = Color(0xFF3BAE06)
val LightPurple = Color(0xFFe1bee7)



val Tan = Color(0xFFDDBC83)

val Colors.mediumGreen: Color
    @Composable
    get() = if (isLight) MediumGreen else MediumGreen


val Colors.newBackgroundColor: Color
    @Composable
    get() = if (isLight) LightPurple else Amber500

val Colors.fontColor: Color
    @Composable
    get() = if (isLight) Amber700 else Amber500

val Colors.fontColorDark: Color
    @Composable
    get() = if (isLight) Purple700 else Amber500
