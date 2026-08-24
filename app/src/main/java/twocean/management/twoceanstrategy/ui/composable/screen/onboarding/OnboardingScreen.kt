package twocean.management.twoceanstrategy.ui.composable.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoGraph
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import twocean.management.twoceanstrategy.R
import twocean.management.twoceanstrategy.ui.viewmodel.HRPIKOnboardingVM

private data class Page(val title: String, val description: String, val image: Int, val icon: ImageVector)

private val pages = listOf(
    Page("Choose a clear direction", "Explore focused advisory services built around your most important strategic decisions.", R.drawable.service_1, Icons.Outlined.AutoGraph),
    Page("Transform how work gets done", "Align people, processes, and performance measures around outcomes that matter.", R.drawable.service_2, Icons.Outlined.Tune),
    Page("Work with experienced advisors", "Book a practical session and leave with evidence, choices, and a plan of action.", R.drawable.service_3, Icons.Outlined.Groups),
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: HRPIKOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val completed by viewModel.onboardingSetState.collectAsState()
    LaunchedEffect(completed) {
        if (completed) onNavigateToHomeScreen()
    }
    OnboardingScreenContent(modifier, viewModel::setOnboarded)
}

@Composable
private fun OnboardingScreenContent(modifier: Modifier, onOnboardingComplete: () -> Unit) {
    val pager = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(state = pager, modifier = Modifier.weight(1f)) { index ->
            val page = pages[index]
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(page.icon, null, Modifier.size(64.dp), tint = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.height(28.dp))
                Text(page.title, style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(12.dp))
                Text(page.description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.height(28.dp))
                Image(
                    painterResource(page.image),
                    null,
                    Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            pages.indices.forEach { index ->
                Box(
                    Modifier
                        .size(if (pager.currentPage == index) 22.dp else 8.dp, 8.dp)
                        .clip(CircleShape)
                        .background(if (pager.currentPage == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline),
                )
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                if (pager.currentPage == pages.lastIndex) {
                    onOnboardingComplete()
                } else {
                    scope.launch { pager.animateScrollToPage(pager.currentPage + 1) }
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(if (pager.currentPage == pages.lastIndex) "Get Started" else "Next")
        }
    }
}
