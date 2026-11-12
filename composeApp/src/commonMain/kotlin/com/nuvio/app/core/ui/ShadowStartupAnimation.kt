package com.nuvio.app.core.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import nuvio.composeapp.generated.resources.Res
import nuvio.composeapp.generated.resources.startup_frame_1
import nuvio.composeapp.generated.resources.startup_frame_2
import nuvio.composeapp.generated.resources.startup_frame_3
import nuvio.composeapp.generated.resources.startup_frame_4
import nuvio.composeapp.generated.resources.startup_frame_5
import nuvio.composeapp.generated.resources.startup_frame_6
import nuvio.composeapp.generated.resources.startup_frame_7
import nuvio.composeapp.generated.resources.startup_frame_8
import nuvio.composeapp.generated.resources.startup_frame_9
import org.jetbrains.compose.resources.painterResource

/** Process-wide so a rotation or activity recreation mid-animation doesn't replay it. */
private var hasShownStartupAnimation = false

private const val FrameDurationMillis = 180L
private const val LastFrameHoldMillis = 220L
private const val FadeDurationMillis = 320L

/**
 * Plays the SHADOW eclipse logo once over the app and fades it away, revealing the
 * content underneath. The frames ship as drawables instead of the GIF so the timing
 * and blending are identical on Android and iOS.
 */
@Composable
fun ShadowStartupAnimation(modifier: Modifier = Modifier) {
    if (LocalInspectionMode.current) return

    var visible by remember { mutableStateOf(!hasShownStartupAnimation) }
    if (!visible) return

    val frames = remember {
        listOf(
            Res.drawable.startup_frame_1,
            Res.drawable.startup_frame_2,
            Res.drawable.startup_frame_3,
            Res.drawable.startup_frame_4,
            Res.drawable.startup_frame_5,
            Res.drawable.startup_frame_6,
            Res.drawable.startup_frame_7,
            Res.drawable.startup_frame_8,
            Res.drawable.startup_frame_9,
        )
    }
    var frameIndex by remember { mutableIntStateOf(0) }
    var fading by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (fading) 0f else 1f,
        animationSpec = tween(FadeDurationMillis),
        label = "startupFade",
    )

    LaunchedEffect(frames) {
        hasShownStartupAnimation = true
        frames.forEachIndexed { index, _ ->
            frameIndex = index
            delay(FrameDurationMillis)
        }
        delay(LastFrameHoldMillis)
        fading = true
        delay(FadeDurationMillis)
        visible = false
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .alpha(alpha)
            // The artwork carries its own black field, so the overlay matches it instead of the theme
            .background(Color.Black)
            // Swallow touches so the app underneath can't be operated mid-animation.
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        awaitPointerEvent(PointerEventPass.Initial).changes.forEach { it.consume() }
                    }
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(frames[frameIndex]),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(0.74f)
                .widthIn(max = 300.dp),
        )
    }
}
