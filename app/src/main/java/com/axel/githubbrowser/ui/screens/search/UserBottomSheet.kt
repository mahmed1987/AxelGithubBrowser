package com.axel.githubbrowser.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.axel.githubbrowser.models.view.ViewUser
import com.axel.githubbrowser.ui.styles.LargeSpacer
import com.axel.githubbrowser.ui.styles.LargeTitle
import com.axel.githubbrowser.ui.styles.MediumHeadline
import com.axel.githubbrowser.ui.styles.MediumSpacer
import com.axel.githubbrowser.ui.styles.SecondaryCard
import com.axel.githubbrowser.ui.styles.SmallSpacer
import com.axel.githubbrowser.ui.styles.SmallTitle
import com.axel.githubbrowser.ui.styles.mediumUnit
import com.axel.githubbrowser.ui.theme.AxelGithubBrowserTheme
import com.axel.githubbrowser.ui.widgets.ImageSize
import com.axel.githubbrowser.ui.widgets.RemoteImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserBottomSheet(user: ViewUser, onDismiss: () -> Unit) {
  val scope = rememberCoroutineScope()
  val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = bottomSheetState,
  ) {
    Column(modifier = Modifier.padding(mediumUnit)) {
      RemoteImage(selectedImage = user.avatarUrl, imageSize = ImageSize.Large)
      MediumHeadline(text = user.login)
      LargeSpacer()
      SecondaryCard() {
        Row(verticalAlignment = Alignment.CenterVertically) {
          SmallTitle(text = "Followers")
          MediumSpacer()
          LargeTitle(text = user.followers.toString())
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
          SmallTitle(text = "Public Repo")
          MediumSpacer()
          LargeTitle(text = user.publicRepos.toString())
        }

      }
    }
  }
}

@Preview
@Composable
fun PreviewUserBottomSheet() {
  AxelGithubBrowserTheme() {
    UserBottomSheet(user = ViewUser(1, "Muhammad Ahmed", "3", 33, 33)) {

    }
  }
}