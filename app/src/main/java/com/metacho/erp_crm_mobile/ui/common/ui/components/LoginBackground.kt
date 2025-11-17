package com.metacho.erp_crm_mobile.ui.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.metacho.erp_crm_mobile.ui.theme.BlueBgDark
import com.metacho.erp_crm_mobile.ui.theme.BlueBgMid
import com.metacho.erp_crm_mobile.ui.theme.PurpleEnd
import com.metacho.erp_crm_mobile.ui.theme.PurpleStart

@Composable
fun LoginBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        BlueBgMid,
                        BlueBgDark
                    ),
                    radius = 900f,
                    center = Offset.Zero
                )
            )
    ) {
        Box(
            modifier = Modifier
                .size(220.dp)
                .offset(x = (-90).dp, y = (-60).dp)
                .graphicsLayer {
                    shadowElevation = 20f
                    shape = CircleShape
                    clip = true
                }
                .background(
                    Brush.radialGradient(
                        colors = listOf(PurpleStart, PurpleEnd)
                    )
                )
        )

        Box(
            modifier = Modifier
                .size(300.dp)
                .offset(x = 160.dp, y = 500.dp)
                .graphicsLayer {
                    shadowElevation = 25f
                    shape = RoundedCornerShape(
                        topStartPercent = 38,
                        topEndPercent = 62,
                        bottomEndPercent = 37,
                        bottomStartPercent = 63
                    )
                    clip = true
                }
                .background(
                    Brush.radialGradient(
                        colors = listOf(PurpleStart, PurpleEnd)
                    )
                )
        )

        content()
    }
}