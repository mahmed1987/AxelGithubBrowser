package com.axel.githubbrowser.ui.widgets

import android.text.Layout.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun RemoteImage(selectedImage: Any?, imageSize: ImageSize = ImageSize.Small) {
  val imageLoadingError = remember { mutableStateOf(false) }
  AsyncImage(
    model = selectedImage,
    fallback = ColorPainter(MaterialTheme.colorScheme.primary),
    error = BrushPainter(
      Brush.linearGradient(
        listOf(
          MaterialTheme.colorScheme.primary,
          MaterialTheme.colorScheme.tertiary
        )
      )
    ),
    onError = {
      imageLoadingError.value = true
    },
    contentDescription = "",
    contentScale = ContentScale.FillWidth,            // crop the image if it's not a square
    modifier = Modifier
      .size(imageSize.size)
      .clip(RoundedCornerShape(8.dp))
  )
}

enum class ImageSize(val size: Dp) {
  XXSmall(24.dp),
  XSmall(40.dp),
  Small(70.dp),
  Medium(100.dp),
  Large(120.dp)
}