package com.kim.savewise

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kim.savewise.ui.theme.*
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToSavings: () -> Unit = {},
    onNavigateToRewards: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
    onActivityClick: (String) -> Unit = {},
    onGoalClick: (String) -> Unit = {},
    onBalanceClick: () -> Unit = {},
    onMomentumClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "SaveWise",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            color = Primary
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Primary)
                    }
                },
                actions = {
                    Surface(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(40.dp)
                            .border(1.dp, Primary.copy(alpha = 0.2f), CircleShape)
                            .clickable { onNavigateToProfile() },
                        shape = CircleShape
                    ) {
                        Box(Modifier.background(Color.LightGray))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Surface.copy(alpha = 0.8f)
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Surface.copy(alpha = 0.9f),
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Filled.Dashboard, contentDescription = "Home") },
                    label = { Text("HOME", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Primary,
                        selectedTextColor = Primary,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToSavings,
                    icon = { Icon(Icons.Outlined.AccountBalanceWallet, contentDescription = "Savings") },
                    label = { Text("SAVINGS", fontSize = 10.sp, fontWeight = FontWeight.Bold) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToRewards,
                    icon = { Icon(Icons.Outlined.MilitaryTech, contentDescription = "Rewards") },
                    label = { Text("REWARDS", fontSize = 10.sp, fontWeight = FontWeight.Bold) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToProfile,
                    icon = { Icon(Icons.Outlined.Person, contentDescription = "Profile") },
                    label = { Text("PROFILE", fontSize = 10.sp, fontWeight = FontWeight.Bold) }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = Primary,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        containerColor = Background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                BalanceHero(onClick = onBalanceClick)
            }
            item {
                MomentumAndGoals(
                    onViewAllClick = onNavigateToSavings,
                    onGoalClick = onGoalClick,
                    onMomentumClick = onMomentumClick
                )
            }
            item {
                RecentActivitySection(onActivityClick = onActivityClick)
            }
        }
    }
}

@Composable
fun BalanceHero(onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(Primary, PrimaryContainer)
                )
            )
            .clickable { onClick() }
            .padding(24.dp)
    ) {
        Column {
            Text(
                "TOTAL BALANCE",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            )
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    "$42,680",
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontFamily = FontFamily.Serif,
                        color = Color.White
                    )
                )
                Text(
                    ".54",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FontFamily.Serif,
                        color = Color.White.copy(alpha = 0.7f)
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.TrendingUp,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "+12.4% this month",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}

@Composable
fun MomentumAndGoals(onViewAllClick: () -> Unit, onGoalClick: (String) -> Unit, onMomentumClick: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth().clickable { onMomentumClick() },
            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow),
            border = BorderStroke(0.5.dp, OutlineVariant.copy(alpha = 0.3f)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "MOMENTUM SCORE",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        color = OnSurfaceVariant
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                Box(
                    modifier = Modifier.size(140.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawCircle(
                            color = SurfaceContainerHighest,
                            style = Stroke(width = 10.dp.toPx())
                        )
                        drawArc(
                            color = Primary,
                            startAngle = 180f,
                            sweepAngle = 270f,
                            useCenter = false,
                            style = Stroke(width = 10.dp.toPx())
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "84",
                            style = MaterialTheme.typography.displaySmall.copy(
                                fontFamily = FontFamily.Serif,
                                color = Primary
                            )
                        )
                        Text(
                            "GROWTH",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 2.sp,
                                color = OnSurfaceVariant
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "You're in the top 5% of savers this week.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontStyle = FontStyle.Italic,
                        color = OnSurfaceVariant
                    )
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow),
            border = BorderStroke(0.5.dp, OutlineVariant.copy(alpha = 0.3f)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Active Goals",
                        style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.Serif)
                    )
                    Text(
                        "VIEW ALL",
                        modifier = Modifier.clickable { onViewAllClick() },
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Primary,
                            letterSpacing = 1.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                GoalItem("Tesla Model 3 Fund", 24500f, 45000f, Primary, onGoalClick)
                Spacer(modifier = Modifier.height(24.dp))
                GoalItem("Bali Dream Vacation", 8200f, 10000f, Primary, onGoalClick)
                Spacer(modifier = Modifier.height(24.dp))
                GoalItem("Emergency Buffer", 15000f, 15000f, PrimaryContainer, onGoalClick)
            }
        }
    }
}

@Composable
fun GoalItem(title: String, current: Float, target: Float, color: Color, onClick: (String) -> Unit) {
    Column(modifier = Modifier.clickable { onClick(title) }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                title,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )
            val currentStr = String.format(Locale.getDefault(), "%,.0f", current)
            val targetStr = String.format(Locale.getDefault(), "%,.0f", target)
            Text(
                "$$currentStr / $$targetStr",
                style = MaterialTheme.typography.labelSmall.copy(color = OnSurfaceVariant)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { current / target },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(CircleShape),
            color = color,
            trackColor = SurfaceContainerHighest,
        )
    }
}

@Composable
fun RecentActivitySection(onActivityClick: (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow),
        border = BorderStroke(0.5.dp, OutlineVariant.copy(alpha = 0.3f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Text(
                    "Recent Activity",
                    style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.Serif)
                )
            }
            HorizontalDivider(color = OutlineVariant.copy(alpha = 0.2f))
            ActivityItem(
                icon = Icons.Default.AccountBalance,
                title = "Stock Dividend",
                subtitle = "AAPL • Today, 10:24 AM",
                amount = "+$142.50",
                amountColor = Primary,
                onClick = onActivityClick
            )
            HorizontalDivider(color = OutlineVariant.copy(alpha = 0.2f))
            ActivityItem(
                icon = Icons.Default.ShoppingCart,
                title = "Amazon Purchase",
                subtitle = "Electronics • Yesterday, 8:12 PM",
                amount = "-$89.99",
                amountColor = OnSurface,
                onClick = onActivityClick
            )
            HorizontalDivider(color = OutlineVariant.copy(alpha = 0.2f))
            ActivityItem(
                icon = Icons.Default.Payments,
                title = "Monthly Salary",
                subtitle = "Creative Hub Inc • 2 days ago",
                amount = "+$5,800.00",
                amountColor = Primary,
                onClick = onActivityClick
            )
        }
    }
}

@Composable
fun ActivityItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    amount: String,
    amountColor: Color,
    onClick: (String) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(title) }
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(48.dp),
            shape = CircleShape,
            color = if (amountColor == Primary) PrimaryFixed else SurfaceContainerHighest,
            border = BorderStroke(0.5.dp, Primary.copy(alpha = 0.1f))
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = if (amountColor == Primary) Primary else OnSurfaceVariant,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                title,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                subtitle.uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp,
                    color = OnSurfaceVariant
                )
            )
        }
        Text(
            amount,
            style = MaterialTheme.typography.titleLarge.copy(
                fontFamily = FontFamily.Serif,
                color = amountColor
            )
        )
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun DashboardScreenPreview() {
    SaveWiseTheme {
        DashboardScreen()
    }
}
