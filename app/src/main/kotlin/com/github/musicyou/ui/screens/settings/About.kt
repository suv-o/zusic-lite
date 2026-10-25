package com.github.musicyou.ui.screens.settings

import android.content.pm.ApplicationInfo
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.github.musicyou.LocalPlayerPadding
import com.github.musicyou.R

@Composable
fun About() {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    val playerPadding = LocalPlayerPadding.current

    val colors = MaterialTheme.colorScheme

    val currentVersion = remember {
        context.packageManager
            .getPackageInfo(context.packageName, 0)
            .versionName ?: "0"
    }

    val buildType = remember {
        if (
            (context.applicationInfo.flags and
                ApplicationInfo.FLAG_DEBUGGABLE) != 0
        ) {
            "debug"
        } else {
            "release"
        }
    }

    val cardColor = colors.surfaceContainer
    val badgeColor = colors.secondaryContainer

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
            .padding(
                top = 16.dp,
                bottom = playerPadding + 24.dp
            ),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        // App Information Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(48.dp),
            color = cardColor
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Surface(
                    modifier = Modifier.size(88.dp),
                    shape = RoundedCornerShape(28.dp),
                    color = Color.White
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(
                                R.drawable.app_icon
                            ),
                            contentDescription = stringResource(
                                R.string.app_name
                            ),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            tint = Color.Unspecified
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = colors.onSurface
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Dynamic build type
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = badgeColor
                        ) {
                            Text(
                                text = buildType,
                                modifier = Modifier.padding(
                                    horizontal = 12.dp,
                                    vertical = 7.dp
                                ),
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = colors.onSecondaryContainer
                            )
                        }

                        // App version
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = badgeColor
                        ) {
                            Text(
                                text = currentVersion,
                                modifier = Modifier.padding(
                                    horizontal = 12.dp,
                                    vertical = 7.dp
                                ),
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = colors.onSecondaryContainer
                            )
                        }
                    }

                    Text(
                        text = "by suv-o & zoo™",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.onSurfaceVariant
                    )
                }

                // GitHub button
                Surface(
                    modifier = Modifier
                        .size(56.dp)
                        .clickable {
                            uriHandler.openUri(
                                "https://github.com/suv-o/zusic-lite"
                            )
                        },
                    shape = CircleShape,
                    color = badgeColor
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(
                                R.drawable.github
                            ),
                            contentDescription = stringResource(
                                R.string.github
                            ),
                            modifier = Modifier.size(28.dp),
                            tint = colors.onSecondaryContainer
                        )
                    }
                }
            }
        }

        // Developer Card with overlapping GitHub avatar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
        ) {

            // Background card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(48.dp),
                color = cardColor
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = 104.dp,
                            bottom = 28.dp,
                            start = 16.dp,
                            end = 16.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Subhajit Adhikary",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = colors.onSurface,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Developer",
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.titleLarge,
                        color = colors.onSurfaceVariant
                    )
                }
            }

            // GitHub profile avatar
            Surface(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .size(144.dp),
                shape = CircleShape,
                color = cardColor
            ) {
                AsyncImage(
                    model = "https://github.com/suv-o.png",
                    contentDescription = "Developer profile picture",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }

        // Social Media Heading
        Text(
            text = "Social Media",
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = colors.primary
        )

        // Social Media Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(48.dp),
            color = cardColor
        ) {
            Column(
                modifier = Modifier.padding(
                    vertical = 16.dp,
                    horizontal = 12.dp
                )
            ) {

                // GitHub
                ListItem(
                    headlineContent = {
                        Text(
                            text = stringResource(R.string.github),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    leadingContent = {
                        Surface(
                            modifier = Modifier.size(64.dp),
                            shape = CircleShape,
                            color = badgeColor
                        ) {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(
                                        R.drawable.github
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp),
                                    tint = colors.onSecondaryContainer
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            uriHandler.openUri(
                                "https://github.com/suv-o/"
                            )
                        },
                    colors = ListItemDefaults.colors(
                        containerColor = Color.Transparent
                    )
                )

                // Instagram
                ListItem(
                    headlineContent = {
                        Text(
                            text = stringResource(R.string.instagram),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    leadingContent = {
                        Surface(
                            modifier = Modifier.size(64.dp),
                            shape = CircleShape,
                            color = badgeColor
                        ) {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(
                                        R.drawable.instagram
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp),
                                    tint = colors.onSecondaryContainer
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            uriHandler.openUri(
                                "https://www.instagram.com/suv.jt/"
                            )
                        },
                    colors = ListItemDefaults.colors(
                        containerColor = Color.Transparent
                    )
                )
            }
        }
    }
}