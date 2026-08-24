package twocean.management.twoceanstrategy.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import twocean.management.twoceanstrategy.data.model.ServiceModel
import java.time.LocalTime

class ServiceRepository {
    private val slots = listOf(LocalTime.of(9, 30), LocalTime.of(12, 0), LocalTime.of(15, 30))

    private val services = listOf(
        service(
            id = 1,
            name = "Growth Strategy Sprint",
            description = "Turn market evidence into a focused three-year growth roadmap with clear priorities.",
            price = 480.0,
            category = "Strategic Planning",
            duration = 120,
            imageUrl = "https://images.unsplash.com/photo-1552664730-d307ca884978?w=1200",
            features = listOf("Market opportunity map", "Strategic choices workshop", "90-day action plan"),
        ),
        service(
            id = 2,
            name = "Business Model Audit",
            description = "Pressure-test revenue streams, customer value, costs, and operating assumptions.",
            price = 360.0,
            category = "Strategic Planning",
            duration = 90,
            imageUrl = "https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?w=1200",
            features = listOf("Business model scorecard", "Risk and gap analysis", "Executive recommendations"),
        ),
        service(
            id = 3,
            name = "Leadership Team Alignment",
            description = "Create shared direction, decision rights, and leadership behaviours for faster execution.",
            price = 420.0,
            category = "People & Leadership",
            duration = 120,
            imageUrl = "https://images.unsplash.com/photo-1521737711867-e3b97375f902?w=1200",
            features = listOf("Leadership interviews", "Alignment session", "Decision charter"),
        ),
        service(
            id = 4,
            name = "Organisation Design Review",
            description = "Design roles, reporting lines, and capabilities around the strategy your business needs.",
            price = 560.0,
            category = "People & Leadership",
            duration = 150,
            imageUrl = "https://images.unsplash.com/photo-1522071820081-009f0129c71c?w=1200",
            features = listOf("Operating model assessment", "Role clarity matrix", "Transition blueprint"),
        ),
        service(
            id = 5,
            name = "Process Efficiency Audit",
            description = "Find bottlenecks and remove wasted effort across your critical business processes.",
            price = 390.0,
            category = "Operational Excellence",
            duration = 120,
            imageUrl = "https://images.unsplash.com/photo-1556761175-b413da4baf72?w=1200",
            features = listOf("Process mapping", "Bottleneck analysis", "Improvement backlog"),
        ),
        service(
            id = 6,
            name = "Performance Management System",
            description = "Connect team objectives, leading indicators, and review rhythms to company goals.",
            price = 440.0,
            category = "Operational Excellence",
            duration = 120,
            imageUrl = "https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=1200",
            features = listOf("KPI architecture", "Dashboard blueprint", "Review cadence"),
        ),
        service(
            id = 7,
            name = "Change Readiness Assessment",
            description = "Measure organisational readiness and build a practical adoption plan for transformation.",
            price = 340.0,
            category = "Change Management",
            duration = 90,
            imageUrl = "https://images.unsplash.com/photo-1517245386807-bb43f82c33c4?w=1200",
            features = listOf("Stakeholder mapping", "Readiness heatmap", "Change action plan"),
        ),
        service(
            id = 8,
            name = "Transformation Office Setup",
            description = "Establish governance, workstreams, and reporting for complex change programmes.",
            price = 620.0,
            category = "Change Management",
            duration = 180,
            imageUrl = "https://images.unsplash.com/photo-1524758631624-e2822e304c36?w=1200",
            features = listOf("Programme governance", "Workstream design", "Benefits tracking"),
        ),
        service(
            id = 9,
            name = "Market Entry Analysis",
            description = "Evaluate a new market using demand, competitor, channel, and commercial evidence.",
            price = 510.0,
            category = "Market Intelligence",
            duration = 150,
            imageUrl = "https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=1200",
            features = listOf("Market sizing", "Competitor landscape", "Entry options"),
        ),
        service(
            id = 10,
            name = "Customer Value Proposition",
            description = "Refine who you serve, the problems you solve, and why customers should choose you.",
            price = 380.0,
            category = "Market Intelligence",
            duration = 120,
            imageUrl = "https://images.unsplash.com/photo-1531482615713-2afd69097998?w=1200",
            features = listOf("Customer insight synthesis", "Value proposition canvas", "Message framework"),
        ),
        service(
            id = 11,
            name = "Board Strategy Advisory",
            description = "Give directors an independent view of strategic risk, choices, and execution progress.",
            price = 680.0,
            category = "Executive Advisory",
            duration = 120,
            imageUrl = "https://images.unsplash.com/photo-1497366811353-6870744d04b2?w=1200",
            features = listOf("Board briefing", "Strategic challenge session", "Written advisory note"),
        ),
        service(
            id = 12,
            name = "Scenario Planning Lab",
            description = "Prepare resilient choices for multiple market futures through facilitated scenario work.",
            price = 590.0,
            category = "Strategic Planning",
            duration = 180,
            imageUrl = "https://images.unsplash.com/photo-1531497865144-0464ef8fb9a9?w=1200",
            features = listOf("Critical uncertainty analysis", "Scenario narratives", "No-regret moves"),
        ),
    )

    fun observeAll(): Flow<List<ServiceModel>> = flowOf(services)

    fun observeById(id: Int): Flow<ServiceModel?> = flowOf(getById(id))

    fun getById(id: Int): ServiceModel? = services.firstOrNull { it.id == id }

    private fun service(
        id: Int,
        name: String,
        description: String,
        price: Double,
        category: String,
        duration: Int,
        imageUrl: String,
        features: List<String>,
    ) = ServiceModel(id, name, description, price, slots, imageUrl, category, duration, features)
}
