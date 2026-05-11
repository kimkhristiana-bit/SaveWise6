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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kim.savewise.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardsScreen(
    onNavigateToDashboard: () -> Unit = {},
    onNavigateToSavings: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
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
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Primary)
                    }
                },
                actions = {
                    Surface(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(40.dp)
                            .border(1.dp, Primary, CircleShape),
                        shape = CircleShape
                    ) {
                        // Placeholder for avatar
                        Box(Modifier.background(Color.LightGray))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Surface.copy(alpha = 0.9f)
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Surface.copy(alpha = 0.9f),
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToDashboard,
                    icon = { Icon(Icons.Outlined.Dashboard, contentDescription = "Home") },
                    label = { Text("HOME", fontSize = 10.sp, fontWeight = FontWeight.Bold) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToSavings,
                    icon = { Icon(Icons.Outlined.AccountBalanceWallet, contentDescription = "Savings") },
                    label = { Text("SAVINGS", fontSize = 10.sp, fontWeight = FontWeight.Bold) }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Filled.MilitaryTech, contentDescription = "Rewards") },
                    label = { Text("REWARDS", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Primary,
                        selectedTextColor = Primary,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToProfile,
                    icon = { Icon(Icons.Outlined.Person, contentDescription = "Profile") },
                    label = { Text("PROFILE", fontSize = 10.sp, fontWeight = FontWeight.Bold) }
                )
            }
        },
        containerColor = Background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(vertical = 24.dp)
        ) {
            item {
                LoyaltyHeroCard()
            }
            item {
                MilestoneBonusesSection()
            }
            item {
                UnlockedBadgesSection()
            }
            item {
                RewardsShopSection()
            }
        }
    }
}

@Composable
fun LoyaltyHeroCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow),
        border = BorderStroke(1.dp, OutlineVariant),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "LOYALTY STATUS",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Primary,
                            letterSpacing = 2.sp
                        )
                    )
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            "12,450",
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            "PTS",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontFamily = FontFamily.Serif,
                                color = PrimaryContainer,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    Text(
                        "You're in the top 5% of savers this month!",
                        style = MaterialTheme.typography.bodyMedium.copy(color = OnSurfaceVariant)
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                    StatusCircle(icon = Icons.Default.LocalFireDepartment, value = "14", label = "DAY STREAK", color = Primary)
                    StatusCircle(icon = Icons.Default.WorkspacePremium, value = "Gold", label = "TIER LEVEL", color = Tertiary)
                }
            }
        }
    }
}

@Composable
fun StatusCircle(icon: ImageVector, value: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            modifier = Modifier.size(64.dp),
            shape = CircleShape,
            color = SurfaceContainerHighest,
            border = BorderStroke(1.dp, color.copy(alpha = 0.5f))
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(32.dp))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(value, style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold))
        Text(label, style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, letterSpacing = 1.sp, color = OnSurfaceVariant))
    }
}

@Composable
fun MilestoneBonusesSection() {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        Text(
            "Milestone Bonuses",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )
        MilestoneCard(title = "Vault Master", desc = "Save $500 this week", points = "+500 PTS", progress = 0.85f, current = "$425.00", target = "$500.00", color = Primary)
        MilestoneCard(title = "Streak Legend", desc = "20 day log-in streak", points = "+1,200 PTS", progress = 0.7f, current = "14 Days", target = "20 Days", color = Tertiary)
        MilestoneCard(title = "Budget Titan", desc = "Stay under budget 4 weeks", points = "+2,500 PTS", progress = 0.25f, current = "1 Week", target = "4 Weeks", color = PrimaryFixedDim)
    }
}

@Composable
fun MilestoneCard(title: String, desc: String, points: String, progress: Float, current: String, target: String, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainer),
        border = BorderStroke(1.dp, OutlineVariant),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(title, style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold))
                    Text(desc, style = MaterialTheme.typography.bodySmall.copy(color = OnSurfaceVariant))
                }
                Text(points, style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = Primary))
            }
            Spacer(modifier = Modifier.height(16.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(50)),
                color = color,
                trackColor = SurfaceContainerHighest
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(current, style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold, color = OnSurfaceVariant))
                Text(target, style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold, color = OnSurfaceVariant))
            }
        }
    }
}

@Composable
fun UnlockedBadgesSection() {
    Column {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
            Text(
                "Unlocked Badges",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                "VIEW HALL OF FAME",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = Primary, letterSpacing = 2.sp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            BadgeItem(icon = Icons.Default.Savings, label = "Penny Pincher", color = Primary)
            BadgeItem(icon = Icons.Default.Bolt, label = "Instant Saver", color = Tertiary)
            BadgeItem(icon = Icons.Default.Monitoring, label = "Growth Guru", color = Primary)
            BadgeItem(icon = Icons.Default.Group, label = "Community Hero", color = Secondary)
            BadgeItem(icon = Icons.Default.ShieldWithHeart, label = "Safe Guard", color = Primary)
            BadgeItem(icon = Icons.Default.Lock, label = "???", color = Outline, isLocked = true)
        }
    }
}

@Composable
fun BadgeItem(icon: ImageVector, label: String, color: Color, isLocked: Boolean = false) {
    Surface(
        modifier = Modifier.width(100.dp),
        shape = RoundedCornerShape(12.dp),
        color = if (isLocked) SurfaceDim else SurfaceContainerLow,
        border = BorderStroke(1.dp, if (isLocked) OutlineVariant.copy(alpha = 0.3f) else OutlineVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                modifier = Modifier.size(56.dp),
                shape = CircleShape,
                color = if (isLocked) SurfaceContainer else color.copy(alpha = 0.1f),
                border = if (isLocked) null else BorderStroke(1.dp, color.copy(alpha = 0.2f))
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(icon, contentDescription = null, tint = if (isLocked) Outline else color, modifier = Modifier.size(32.dp))
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                label,
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun RewardsShopSection() {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        RewardShopCard(
            type = "Premium Reward",
            title = "Exclusive Metal Card",
            desc = "Unlock the weighted titanium edition with free worldwide concierge services.",
            points = "Redeem 50k PTS",
            imageRes = "https://lh3.googleusercontent.com/aida-public/AB6AXuBaIskRxj1QZEsoYLc5Juus8HiNVs_tv8sigiVfuNNs8A9o8B0eCb0XBs-aXvyfLrTpy6YpKXzEV3vMtTGUgtlhy5HCnK4Pi6oM9rGxtdxbrRqAhl2X_MjgEW9_M9UTMCE_GS9gt9NkyftKr-2ewvnpLm4xLcrEXfNMG3vhjxdxtqCGi6YMLQfaUqtHu2gDHbEDpEa9ybRQomk5FZPhw5vosONx0wZdp6C_KWye_yjJyDY0CkVONFwgyhaoc-N5j_-dBJRXDlHx-lP4"
        )
        RewardShopCard(
            type = "Boost Perk",
            title = "+2% APY Boost",
            desc = "Increase your savings yield by 2% for the next 90 days. limited time offer.",
            points = "Redeem 10k PTS",
            imageRes = "https://lh3.googleusercontent.com/aida-public/AB6AXuDembsTXOEH5WaLBGGXH523jVAddzEbCq4h62XNxMR0wccFqD-_s1BtTqARD2gPGJPO4mcClK9wnWC8NOPOLFqPFx7FXwK0zVREpa-YIvwJBsYWar2OKxMKOdY-04fNhRqYwH5LJvv4rKdZ97itHouRjig6z2HXnUSzGnSyzmISvlGW8MoMbVhDxvLCIMf-DtBuhknryCcvkb7gmm8nfJ0C411ZLnV1gPcXza5FGeDlY7qhZ7MHqo1Kc1fFi_DWSInpwWs5n_uqAJbP"
        )
    }
}

@Composable
fun RewardShopCard(type: String, title: String, desc: String, points: String, imageRes: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainer),
        border = BorderStroke(1.dp, OutlineVariant),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            // Placeholder for image
            Box(
                modifier = Modifier.weight(1f).fillMaxHeight().background(Color.LightGray)
            )
            Column(modifier = Modifier.weight(2f).padding(24.dp), verticalArrangement = Arrangement.Center) {
                Text(type, style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = Primary, letterSpacing = 2.sp))
                Text(title, style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold))
                Spacer(modifier = Modifier.height(8.dp))
                Text(desc, style = MaterialTheme.typography.bodySmall.copy(color = OnSurfaceVariant, lineHeight = 20.sp))
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(50),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
                ) {
                    Text(points, style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = OnPrimary))
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun RewardsScreenPreview() {
    SaveWiseTheme {
        RewardsScreen()
    }
}
