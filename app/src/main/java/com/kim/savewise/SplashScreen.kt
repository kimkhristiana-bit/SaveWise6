package com.kim.savewise

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kim.savewise.ui.theme.*

@Composable
fun SplashScreen(onNavigateToRegister: () -> Unit, onNavigateToLogin: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    0.0f to PrimaryFixed,
                    1.0f to Background,
                    center = Offset(500f, -200f)
                )
            )
    ) {
        // Subtle Background Accents (Blurry Circles)
        Box(
            modifier = Modifier
                .size(250.dp)
                .offset(x = (-50).dp, y = 50.dp)
                .blur(100.dp)
                .background(PrimaryFixedDim.copy(alpha = 0.3f), RoundedCornerShape(999.dp))
        )
        Box(
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 50.dp, y = (-50).dp)
                .blur(120.dp)
                .background(TertiaryFixedDim.copy(alpha = 0.2f), RoundedCornerShape(999.dp))
        )

        // Floating Decorative Card (Subtle/Editorial)
        Box(
            modifier = Modifier
                .padding(32.dp)
                .size(160.dp, 80.dp)
                .offset(x = (-20).dp, y = 180.dp)
                .rotate(-2f)
                .background(Color.White.copy(alpha = 0.8f), RoundedCornerShape(12.dp))
                .border(0.5.dp, OutlineVariant.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = RoundedCornerShape(999.dp),
                    color = PrimaryFixed,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        Icons.Outlined.TrendingUp,
                        contentDescription = null,
                        tint = OnPrimaryContainer,
                        modifier = Modifier.padding(4.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    Modifier
                        .height(6.dp)
                        .width(80.dp)
                        .background(SurfaceVariant, RoundedCornerShape(999.dp))
                )
            }
        }

        // Main Content Container
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo Icon
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .graphicsLayer { rotationZ = 3f }
                    .border(0.5.dp, OutlineVariant.copy(alpha = 0.3f), RoundedCornerShape(24.dp)),
                shape = RoundedCornerShape(24.dp),
                color = Color.White,
                shadowElevation = 4.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Outlined.AccountBalanceWallet,
                        contentDescription = "SaveWise Logo",
                        tint = Primary,
                        modifier = Modifier.size(56.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Brand Name
            Text(
                text = "SaveWise",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-1).sp
                ),
                color = OnSurface
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Description
            Text(
                text = "Refined wealth management for the modern curator. Simple, warm, and secure.",
                style = MaterialTheme.typography.bodyLarge,
                color = OnSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Actions
            Button(
                onClick = { onNavigateToRegister() },
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Get Started", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        Icons.AutoMirrored.Outlined.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = { onNavigateToLogin() },
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Outline)
            ) {
                Text(
                    "Already have an account? Log in",
                    color = OnSurface,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // Editorial Footer Decoration (Line and Labels)
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp)
                .fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color.Transparent, OutlineVariant, Color.Transparent)
                        )
                    )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val footerStyle = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
                Text("CURATED ASSETS", style = footerStyle, color = OnSurfaceVariant.copy(alpha = 0.5f))
                Text("PRIVACY FIRST", style = footerStyle, color = OnSurfaceVariant.copy(alpha = 0.5f))
                Text("GLOBAL ACCESS", style = footerStyle, color = OnSurfaceVariant.copy(alpha = 0.5f))
            }
        }

        // Bottom Security Badge
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp),
            color = SurfaceVariant.copy(alpha = 0.4f),
            shape = RoundedCornerShape(999.dp),
            border = BorderStroke(0.5.dp, OutlineVariant.copy(alpha = 0.2f))
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    Icons.Outlined.VerifiedUser,
                    contentDescription = null,
                    tint = OnSurfaceVariant,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    "ENCRYPTED & PRIVATE",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    ),
                    color = OnSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun SplashScreenPreview() {
    SaveWiseTheme {
        SplashScreen(onNavigateToRegister = {}, onNavigateToLogin = {})
    }
}
