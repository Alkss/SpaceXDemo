package com.mindera.rocketscience.featue_demo.presentation.home.links

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mindera.rocketscience.R

@Composable
fun LinksDialog(
    modifier: Modifier = Modifier,
    wikiUrl: String? = null,
    videoUrl: String? = null,
    articleUrl: String? = null,
    onDismiss: () -> Unit,
    onClick: () -> Unit,
) {

    val uriHandler = LocalUriHandler.current

    Dialog(onDismissRequest = { onDismiss.invoke() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                articleUrl?.let {
                    TextButton(onClick = {
                        uriHandler.openUri(it)
                        onClick.invoke()
                    }) {
                        Text(text = stringResource(id = R.string.link_article))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
                videoUrl?.let {
                    TextButton(onClick = {
                        uriHandler.openUri(it)
                        onClick.invoke()
                    }) {
                        Text(text = stringResource(id = R.string.link_video))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
                wikiUrl?.let {
                    TextButton(onClick = {
                        uriHandler.openUri(it)
                        onClick.invoke()
                    }) {
                        Text(text = stringResource(id = R.string.link_wiki))
                    }
                }
            }
        }
    }
}