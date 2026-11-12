package com.nuvio.app.features.settings

import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

@Composable
internal fun MemberBrandWordmark(
    height: Dp,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    Text(
        text = "SHADOW",
        modifier = modifier.height(height),
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.titleLarge.copy(
            fontSize = (height.value * 0.48f).sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 3.sp,
        ),
    )
}
