package twocean.management.twoceanstrategy.ui.composable.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import twocean.management.twoceanstrategy.R
import twocean.management.twoceanstrategy.ui.theme.DeepOcean
import twocean.management.twoceanstrategy.ui.theme.OceanSlate
import twocean.management.twoceanstrategy.ui.viewmodel.HRPIKSplashVM

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: HRPIKSplashVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
) {
    val onboarded by viewModel.onboardedState.collectAsStateWithLifecycle()
    var visible by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(if (visible) 1f else 0.8f, tween(800), label = "scale")

    LaunchedEffect(Unit) {
        visible = true
        delay(1500)
        if (onboarded) onNavigateToHomeScreen() else onNavigateToOnboarding()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(OceanSlate, DeepOcean))),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Image(
                painter = painterResource(R.drawable.icon),
                contentDescription = null,
                modifier = Modifier
                    .size(132.dp)
                    .scale(progress)
                    .alpha((progress - 0.8f) * 5f),
            )
            Text(
                text = "TWOcean Strategy",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}
