package ir.shervin.messenger

import androidx.compose.ui.Modifier

fun Modifier.applyIf(condition: Boolean, builder: Modifier.() -> Modifier) = if (condition) then(builder(Modifier)) else this
