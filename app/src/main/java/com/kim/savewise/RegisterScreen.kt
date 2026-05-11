package com.kim.savewise

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kim.savewise.ui.theme.*

@Composable
fun RegisterScreen(
    onBack: () -> Unit, 
    onRegisterSuccess: () -> Unit, 
    onNavigateToLogin: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        // Sahara Gradients
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        0.0f to Color(0xFFF0A878).copy(alpha = 0.15f),
                        1.0f to Color.Transparent,
                        center = Offset(200f, 400f)
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        0.0f to Color(0xFFC2652A).copy(alpha = 0.1f),
                        1.0f to Color.Transparent,
                        center = Offset(800f, 1600f)
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Branding Header
            Column(
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .clickable { onBack() },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "SaveWise",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = Primary,
                        letterSpacing = (-1).sp
                    )
                )
                Text(
                    text = "Elevate your financial future.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontStyle = FontStyle.Italic,
                        color = Secondary,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            // Registration Container
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(8.dp),
                color = Color.White,
                border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.5f)),
                shadowElevation = 1.dp
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    RegistrationField(label = "FULL NAME", placeholder = "Alex Sterling")
                    RegistrationField(label = "EMAIL ADDRESS", placeholder = "alex@future.io")
                    RegistrationField(label = "SECURE PASSWORD", placeholder = "••••••••••••", isPassword = true)

                    Button(
                        onClick = { onRegisterSuccess() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(top = 12.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Primary),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "Create Account",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                Icons.AutoMirrored.Outlined.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    // Divider
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        HorizontalDivider(modifier = Modifier.weight(1f), color = OutlineVariant)
                        Text(
                            "OR CONTINUE WITH",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 2.sp,
                                color = Outline
                            )
                        )
                        HorizontalDivider(modifier = Modifier.weight(1f), color = OutlineVariant)
                    }

                    // Social Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        SocialButton(
                            text = "GOOGLE",
                            modifier = Modifier.weight(1f),
                            icon = { 
                                Text("G", fontWeight = FontWeight.ExtraBold, color = Color.Gray)
                            }
                        )
                        SocialButton(
                            text = "APPLE",
                            modifier = Modifier.weight(1f),
                            icon = { 
                                Text("A", fontWeight = FontWeight.ExtraBold, color = Color.Gray)
                            }
                        )
                    }

                    // Footer link
                    Text(
                        text = buildAnnotatedString {
                            append("Already have an account? ")
                            withStyle(style = SpanStyle(color = Primary, fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline)) {
                                append("Sign In")
                            }
                        },
                        style = MaterialTheme.typography.bodySmall.copy(color = Secondary, textAlign = TextAlign.Center),
                        modifier = Modifier.fillMaxWidth().clickable { onNavigateToLogin() }
                    )
                }
            }

            // Footer Links
            Row(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FooterLink("PRIVACY")
                FooterSpacer()
                FooterLink("TERMS")
                FooterSpacer()
                FooterLink("SECURITY")
            }
        }
    }
}

@Composable
fun RegistrationField(label: String, placeholder: String, isPassword: Boolean = false) {
    var text by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                color = OnSecondaryFixedVariant
            )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { isFocused = it.isFocused }
        ) {
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = OnSurface),
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
                decorationBox = { innerTextField ->
                    if (text.isEmpty()) {
                        Text(
                            placeholder,
                            style = MaterialTheme.typography.bodyLarge.copy(color = Outline.copy(alpha = 0.4f))
                        )
                    }
                    innerTextField()
                }
            )
            // Bottom border
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(OutlineVariant)
            )
            // Animated focus border
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth(if (isFocused) 1f else 0f)
                    .height(1.5.dp)
                    .background(Primary)
            )
        }
    }
}

@Composable
fun SocialButton(text: String, modifier: Modifier = Modifier, icon: @Composable () -> Unit) {
    OutlinedButton(
        onClick = { },
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, OutlineVariant),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = OnSurface),
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            icon()
            Text(
                text,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Composable
fun FooterLink(text: String) {
    Text(
        text,
        style = MaterialTheme.typography.labelSmall.copy(
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            color = Outline
        )
    )
}

@Composable
fun FooterSpacer() {
    Spacer(modifier = Modifier.width(24.dp))
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun RegisterScreenPreview() {
    SaveWiseTheme {
        RegisterScreen(onBack = {}, onRegisterSuccess = {}, onNavigateToLogin = {})
    }
}
