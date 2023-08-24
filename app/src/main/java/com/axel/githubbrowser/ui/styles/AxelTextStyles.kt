package com.axel.githubbrowser.ui.styles

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow


/* Public Interface*/
@Composable
fun LargeHeadline(text: String) {
  AxelText(
    modifier = Modifier,
    fontSize = FontSize.Large,
    fontFamily = FontFamily.Headline,
    text = text
  )
}

@Composable
fun MediumHeadline(text: String) {
  AxelText(
    modifier = Modifier,
    fontSize = FontSize.Medium,
    fontFamily = FontFamily.Headline,
    text = text
  )
}

@Composable
fun SmallHeadline(text: String) {
  AxelText(
    modifier = Modifier,
    fontSize = FontSize.Small,
    fontFamily = FontFamily.Headline,
    text = text
  )
}
@Composable
fun LargeTitle(text: String) {
  AxelText(
    modifier = Modifier,
    fontSize = FontSize.Large,
    fontFamily = FontFamily.Title,
    text = text
  )
}

@Composable
fun MediumTitle(text: String) {
  AxelText(
    modifier = Modifier,
    fontSize = FontSize.Medium,
    fontFamily = FontFamily.Title,
    text = text
  )
}

@Composable
fun SmallTitle(text: String) {
  AxelText(
    modifier = Modifier,
    fontSize = FontSize.Small,
    fontFamily = FontFamily.Title,
    text = text
  )
}

@Composable
fun LargeBody(text: String) {
  AxelText(
    modifier = Modifier,
    fontSize = FontSize.Large,
    fontFamily = FontFamily.Body,
    text = text
  )
}

@Composable
fun MediumBody(text: String) {
  AxelText(
    modifier = Modifier,
    fontSize = FontSize.Medium,
    fontFamily = FontFamily.Body,
    text = text
  )
}

@Composable
fun SmallBody(text: String) {
  AxelText(
    modifier = Modifier,
    fontSize = FontSize.Small,
    fontFamily = FontFamily.Body,
    text = text
  )
}

/*Internal*/
@Composable
private fun AxelText(
  modifier: Modifier,
  maxLines: Int = Int.MAX_VALUE,
  fontSize: FontSize = FontSize.Medium,
  fontFamily: FontFamily = FontFamily.Body,
  textAlign: TextAlign? = null,
  fontWeight: FontWeight? = null,
  text: String,
  color: Color = Color.Unspecified
) {
  val baseStyle = getFontStyle(fontSize = fontSize, fontFamily = fontFamily)
  val textStyle = baseStyle.copy(fontWeight = fontWeight).takeIf { fontWeight != null } ?: baseStyle
  Text(
    modifier = modifier,
    style = textStyle,
    text = text,
    color = color,
    maxLines = maxLines,
    overflow = TextOverflow.Ellipsis,
    textAlign = textAlign
  )
}

@Composable
private fun getFontStyle(fontSize: FontSize, fontFamily: FontFamily): TextStyle {
  return when (fontSize) {
    FontSize.Large -> {
      when (fontFamily) {
        FontFamily.Headline -> MaterialTheme.typography.headlineLarge
        FontFamily.Title -> MaterialTheme.typography.titleLarge
        FontFamily.Body -> MaterialTheme.typography.bodyLarge
        FontFamily.Label -> MaterialTheme.typography.labelLarge
      }
    }
    FontSize.Medium -> {
      when (fontFamily) {
        FontFamily.Headline -> MaterialTheme.typography.headlineMedium
        FontFamily.Title -> MaterialTheme.typography.titleMedium
        FontFamily.Body -> MaterialTheme.typography.bodyMedium
        FontFamily.Label -> MaterialTheme.typography.labelMedium
      }
    }

    FontSize.Small -> {
      when (fontFamily) {
        FontFamily.Headline -> MaterialTheme.typography.headlineSmall
        FontFamily.Title -> MaterialTheme.typography.titleSmall
        FontFamily.Body -> MaterialTheme.typography.bodySmall
        FontFamily.Label -> MaterialTheme.typography.labelSmall
      }
    }
  }
}

private enum class FontFamily {
  Headline,
  Title,
  Body,
  Label
}

private enum class FontSize {
  Large,
  Medium,
  Small
}