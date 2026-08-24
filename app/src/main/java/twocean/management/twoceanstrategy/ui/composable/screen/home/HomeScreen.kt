package twocean.management.twoceanstrategy.ui.composable.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoGraph
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.SettingsSuggest
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import twocean.management.twoceanstrategy.data.model.ServiceModel
import twocean.management.twoceanstrategy.ui.composable.shared.HRPIKContentWrapper
import twocean.management.twoceanstrategy.ui.state.DataUiState
import twocean.management.twoceanstrategy.ui.theme.OceanSlate
import twocean.management.twoceanstrategy.ui.viewmodel.ServiceViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ServiceViewModel = koinViewModel(),
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    val state by viewModel.servicesState.collectAsState()
    HRPIKContentWrapper(
        dataState = state,
        dataPopulated = {
            ServicesPopulated((state as DataUiState.Populated).data, modifier, onNavigateToServiceDetails)
        },
        dataEmpty = {
            Text("No advisory services are available.", modifier = Modifier.padding(24.dp))
        },
    )
}

@Composable
private fun ServicesPopulated(
    services: List<ServiceModel>,
    modifier: Modifier = Modifier,
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    val categories = listOf(
        "Strategy" to Icons.Outlined.AutoGraph,
        "People" to Icons.Outlined.Groups,
        "Operations" to Icons.Outlined.SettingsSuggest,
        "Insights" to Icons.Outlined.Lightbulb,
    )
    LazyColumn(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item {
            Column(Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
                Text("TWOcean Strategy", style = MaterialTheme.typography.headlineMedium)
                Text("Clarity for the decisions ahead", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        item {
            Surface(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = OceanSlate,
            ) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("NEXT AVAILABLE", color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.labelLarge)
                    Text("Strategy session · Tomorrow, 09:30", color = MaterialTheme.colorScheme.onPrimary, style = MaterialTheme.typography.titleMedium)
                    Text("Start with a focused discovery call.", color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f))
                }
            }
        }
        item {
            Text("Explore by focus", Modifier.padding(horizontal = 20.dp), style = MaterialTheme.typography.titleLarge)
            LazyRow(contentPadding = PaddingValues(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(categories) { item ->
                    CategoryCard(item.first, item.second)
                }
            }
        }
        item {
            Text("Advisory services", Modifier.padding(horizontal = 20.dp), style = MaterialTheme.typography.titleLarge)
        }
        items(services, key = { it.id }) { service ->
            ServiceCard(service, onNavigateToServiceDetails)
        }
        item {
            Column(
                Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text("Transformation portfolio", style = MaterialTheme.typography.titleLarge)
                InsightCard("Regional services group", "+18% operating margin", "Redesigned the operating model and clarified accountability across six business units.")
                InsightCard("Digital scale-up", "2.4× faster decisions", "Introduced decision rights, leadership rhythms, and a portfolio governance model.")
                InsightCard("Industrial supplier", "£3.1m annual savings", "Simplified core processes and built a measurable continuous-improvement pipeline.")
                Text("Knowledge base", style = MaterialTheme.typography.titleLarge)
                InsightCard("Leading through uncertainty", "7 min read", "A practical framework for maintaining direction while assumptions keep changing.")
                InsightCard("Why transformations stall", "6 min read", "Five adoption risks leaders should address before launching a change programme.")
                InsightCard("Reading market signals", "8 min read", "Separate durable shifts from short-term noise with a structured evidence review.")
            }
        }
    }
}

@Composable
private fun InsightCard(title: String, result: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(result, color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.labelLarge)
            Text(description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun CategoryCard(name: String, icon: ImageVector) {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
        Column(
            Modifier
                .size(112.dp, 96.dp)
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(icon, null, tint = MaterialTheme.colorScheme.primary)
            Text(name, style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Composable
private fun ServiceCard(service: ServiceModel, onNavigate: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .clickable { onNavigate(service.id) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = service.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(116.dp),
                contentScale = ContentScale.Crop,
            )
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(service.category.uppercase(), color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.labelLarge)
                Text(service.name, style = MaterialTheme.typography.titleMedium)
                Text(service.description, maxLines = 2, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("From £${service.price.toInt()}  ·  Book now", color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}
