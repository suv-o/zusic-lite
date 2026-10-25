package com.github.musicyou.ui.screens.settings

import android.content.pm.ApplicationInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.musicyou.LocalPlayerPadding
import com.github.musicyou.R

private data class SocialLinkItem(
    val label: String,
    val iconRes: Int,
    val url: String
)

@Composable
fun About() {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    val playerPadding = LocalPlayerPadding.current

    val colors = MaterialTheme.colorScheme
    val isSystemDark = isSystemInDarkTheme()

    val currentVersion = remember {
        context.packageManager
            .getPackageInfo(context.packageName, 0)
            .versionName ?: "0"
    }

    val buildType = remember {
        if (context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0) {
            "debug"
        } else {
            "release"
        }
    }

    val appIconBgColor = if (isSystemDark) Color.White else Color.Black
    val appIconTintColor = if (isSystemDark) Color.Black else Color.White
    val cardBgColor = colors.surfaceVariant.copy(alpha = 0.5f)
    val badgeBgColor = colors.secondaryContainer.copy(alpha = 0.6f)

    val socialLinks = remember {
        listOf(
            SocialLinkItem("GitHub", R.drawable.github, "https://github.com/suv-o"),
            SocialLinkItem("Instagram", R.drawable.instagram, "https://www.instagram.com/suv.jt")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
            .padding(
                top = 16.dp,
                bottom = playerPadding + 24.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 1. App Info Card
        Card(
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = cardBgColor),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // App Icon Surface
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = appIconBgColor,
                    modifier = Modifier
                        .width(60.dp)
                        .height(80.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(R.drawable.app_icon),
                            contentDescription = stringResource(R.string.app_name),
                            modifier = Modifier.size(48.dp),
                            colorFilter = ColorFilter.tint(appIconTintColor)
                        )
                    }
                }

                Spacer(Modifier.width(20.dp))

                // App Details
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Black,
                        color = colors.onSurface
                    )

                    Spacer(Modifier.height(4.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = colors.primary.copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = buildType.lowercase(),
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = colors.primary,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = badgeBgColor
                        ) {
                            Text(
                                text = currentVersion,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = colors.onSecondaryContainer,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = "by suv-o & zoo™",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = colors.onSurfaceVariant
                    )
                }

                // GitHub Quick Action
                Surface(
                    onClick = { uriHandler.openUri("https://github.com/suv-o/zusic-lite") },
                    shape = CircleShape,
                    color = colors.secondaryContainer.copy(alpha = 0.5f),
                    modifier = Modifier.size(48.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            painter = painterResource(R.drawable.github),
                            contentDescription = stringResource(R.string.github),
                            modifier = Modifier.size(24.dp),
                            tint = colors.onSecondaryContainer
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(32.dp))

        // 2. Overlapping Developer Card
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            // Background Card
            Card(
                shape = RoundedCornerShape(32.dp),
                colors = CardDefaults.cardColors(containerColor = cardBgColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 92.dp, bottom = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Subhajit Adhikary",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Black,
                        color = colors.onSurface
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Developer",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.onSurfaceVariant
                    )
                }
            }

            // Avatar Box (160dp with 6dp outer ring)
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .background(color = colors.surfaceVariant, shape = CircleShape)
                    .padding(6.dp)
                    .clip(CircleShape)
                    .background(colors.surface),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Developer",
                    modifier = Modifier.size(72.dp),
                    tint = colors.onSurfaceVariant
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // 3. Social Media Section Header
        Text(
            text = "Social Media",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = colors.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // 4. Social Media Card
        Card(
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = cardBgColor),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                socialLinks.forEach { link ->
                    Surface(
                        onClick = { uriHandler.openUri(link.url) },
                        color = Color.Transparent,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = colors.secondaryContainer.copy(alpha = 0.5f),
                                modifier = Modifier.size(48.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        painter = painterResource(link.iconRes),
                                        contentDescription = link.label,
                                        modifier = Modifier.size(24.dp),
                                        tint = colors.onSecondaryContainer
                                    )
                                }
                            }

                            Spacer(Modifier.width(20.dp))

                            Text(
                                text = link.label,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = colors.onSurface
                            )
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))
    }
}
