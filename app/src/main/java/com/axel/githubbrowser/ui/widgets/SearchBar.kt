package com.axel.githubbrowser.ui.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.axel.githubbrowser.ui.styles.MediumHeadline

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
  CenterAlignedTopAppBar(title = {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      MediumHeadline(text = "Axel Springer GB")
      Box(
        modifier = Modifier
          .clip(CircleShape)
          .size(10.dp)
          .background(MaterialTheme.colorScheme.secondary)
      )
    }
  },
    actions = {
    })
}