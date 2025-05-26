package uk.co.steffandroid.gravyscanner.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import uk.co.steffandroid.gravyscanner.ui.theme.Purple40
import uk.co.steffandroid.gravyscanner.ui.theme.PurpleGrey40
import uk.co.steffandroid.gravyscanner.ui.theme.Pink40
import uk.co.steffandroid.gravyscanner.ui.theme.Purple80
import uk.co.steffandroid.gravyscanner.ui.theme.PurpleGrey80
import uk.co.steffandroid.gravyscanner.ui.theme.Pink80

private val DarkColorScheme = darkColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

private val LightColorScheme = lightColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80

    /* Other default colors to override if needed
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun GravyScannerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MaterialTheme.typography, // Using MaterialTheme.typography as Typography is not defined
        content = content
    )
}
