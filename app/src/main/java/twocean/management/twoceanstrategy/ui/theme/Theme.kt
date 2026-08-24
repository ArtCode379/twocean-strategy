package twocean.management.twoceanstrategy.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme = lightColorScheme(
    primary = OceanSlate,
    onPrimary = White,
    secondary = Coral,
    onSecondary = White,
    background = Mist,
    onBackground = Ink,
    surface = White,
    onSurface = Ink,
    surfaceVariant = SeaGlass,
    onSurfaceVariant = Muted,
    outline = Border,
    error = Warning,
)

@Composable
fun ServiceSkeletonTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = AppTypography,
        content = content,
    )
}
