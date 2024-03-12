package com.mindera.rocketscience.featue_demo.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mindera.rocketscience.R
import com.mindera.rocketscience.featue_demo.presentation.home.filter.FilterComposable
import com.mindera.rocketscience.featue_demo.presentation.home.links.LinksDialog
import com.mindera.rocketscience.ui.theme.RocketScienceTheme


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state = viewModel.homeState.collectAsState().value

    val isFilterDialogOpen = remember {
        mutableStateOf(false)
    }

    val isLinksDialogOpen = remember {
        mutableStateOf(false)
    }
    val articleUrl = remember {
        mutableStateOf<String?>(null)
    }
    val wikiUrl = remember {
        mutableStateOf<String?>(null)
    }
    val videoUrl = remember {
        mutableStateOf<String?>(null)
    }

    if (isLinksDialogOpen.value) {
        LinksDialog(
            videoUrl = videoUrl.value,
            wikiUrl = wikiUrl.value,
            articleUrl = articleUrl.value,
            onDismiss = { isLinksDialogOpen.value = false },
            onClick = { isLinksDialogOpen.value = false }
        )
    }

    val lazyListState = rememberLazyListState()

    if (isFilterDialogOpen.value) {
        FilterComposable(onDismissRequest = {
            isFilterDialogOpen.value = false
        }) { startDate, endDate, isSuccessful, isAscending ->
            viewModel.applyFilter(startDate, endDate, isSuccessful, isAscending)
            isFilterDialogOpen.value = false
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = R.string.app_header)
                )
                IconButton(modifier = Modifier.align(Alignment.CenterEnd), onClick = {
                    isFilterDialogOpen.value = true
                }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
        ) {
            Text(
                text = stringResource(id = R.string.header_company).uppercase(),
                color = Color.White,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = parseToCompanyDescription(
                    companyName = state.companyInfo.companyName,
                    founderName = state.companyInfo.founderName,
                    year = state.companyInfo.year,
                    employees = state.companyInfo.employees,
                    launchSites = state.companyInfo.launchSites,
                    valuation = state.companyInfo.valuation
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
        ) {
            Text(
                text = stringResource(id = R.string.header_company).uppercase(),
                color = Color.White,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        val offset = remember {
            mutableIntStateOf(13)
        }

        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxWidth(),
        ) {
            itemsIndexed(state.launchList) { _, item ->
                LaunchedEffect(lazyListState) {
                    snapshotFlow { lazyListState.firstVisibleItemIndex }
                        .collect { index ->
                            if (index >= offset.intValue) {
                                viewModel.nextPage()
                                offset.intValue += 20
                            }
                        }
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            articleUrl.value = item.articleUrl
                            videoUrl.value = item.videoUrl
                            wikiUrl.value = item.wikiUrl
                            isLinksDialogOpen.value = true
                        }) {
                    Column(Modifier.weight(1f)) {
                        AsyncImage(
                            modifier = Modifier.size(48.dp),
                            model = item.missionPatch,
                            contentDescription = "Mission Patch",
                        )
                    }
                    Column(Modifier.weight(4f)) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = stringResource(id = R.string.mission_label))
                            Text(text = item.missionName)

                        }
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(text = stringResource(id = R.string.date_time_label))
                            Text(text = parseToDatetime(date = item.date, time = item.time))

                        }

                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(text = stringResource(id = R.string.rocket_label))
                            Text(text = "${item.rocketName}/${item.rocketType}")
                        }

                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (item.isLaunchInThePast) {
                                Text(text = stringResource(id = R.string.days_since_label))
                            } else {
                                Text(text = stringResource(id = R.string.days_from_label))
                            }
                            if (item.isLaunchInThePast) {
                                Text(text = "+ ${item.days}")
                            } else {
                                Text(text = "- ${item.days}")
                            }

                        }

                    }
                    Column(Modifier.weight(1f)) {
                        item.successfulLaunch.let {
                            if (it) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(48.dp)
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = null,
                                    modifier = Modifier.size(48.dp)
                                )
                            }
                        }
                    }
                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
private fun parseToDatetime(
    date: String,
    time: String
): String {
    return stringResource(id = R.string.date_time_value, date, time)
}

@Composable
private fun parseToCompanyDescription(
    companyName: String,
    founderName: String,
    year: String,
    employees: String,
    launchSites: String,
    valuation: String
): String {
    return stringResource(
        id = R.string.header_body,
        companyName,
        founderName,
        year,
        employees,
        launchSites,
        valuation
    )
}

@Preview
@Composable
private fun HomeScreenPreview() {
    RocketScienceTheme {
        Surface {
            HomeScreen()
        }
    }
}