package com.alxd.petslist.ui.detailPuppy

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alxd.petslist.data.Puppy

@Composable
fun DetailContent(puppy : Puppy, onNavIconPressed : () -> Unit = {}){

    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                ) {
                    DetailHeader(scrollState, puppy, this@BoxWithConstraints.maxHeight)
                    DetailBody(puppy, this@BoxWithConstraints.maxHeight)
                }
            }
        }
    }

}

@Composable
private fun DetailHeader(scrollState: ScrollState,
                         puppy: Puppy,
                         containerHeight: Dp
){

    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    Image(modifier = Modifier
        .heightIn(max = containerHeight / 2)
        .fillMaxWidth()
        .padding(top = offsetDp),
        painter = painterResource(id = puppy.puppyImageId),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
private fun DetailBody(puppy: Puppy, containerHeight: Dp){
    Column {
        Spacer(modifier = Modifier.heightIn(8.dp))

        NameSection(puppy = puppy)

        DetailProperty(label = "Edad", value = puppy.age.toString())
        DetailProperty(label = "Descripcion", value = puppy.description)
    }
}

@Composable
private fun NameSection(
    puppy: Puppy
) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Name(
            puppy = puppy,
            modifier = Modifier.paddingFromBaseline(32.dp)
        )
    }
}

@Composable
private fun Name(puppy: Puppy, modifier: Modifier = Modifier) {
    Text(
        text = puppy.title,
        modifier = modifier,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun DetailProperty(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = label,
                modifier = Modifier.paddingFromBaseline(24.dp),
                style = MaterialTheme.typography.caption,
            )
        }
        val style = if (isLink) {
            MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary)
        } else {
            MaterialTheme.typography.body1
        }
        Text(
            text = value,
            modifier = Modifier.paddingFromBaseline(24.dp),
            style = style
        )
    }
}