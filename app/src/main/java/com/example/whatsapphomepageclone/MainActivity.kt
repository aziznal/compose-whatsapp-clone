package com.example.whatsapphomepageclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.whatsapphomepageclone.ui.theme.WhatsappHomePageCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsappHomePageCloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    HomePage()
                }
            }
        }
    }
}


@Preview
@Composable
fun HomePagePreview() {
    WhatsappHomePageCloneTheme {
        HomePage()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePage() {
    val vm = remember {
        MainActivityViewModel()
    }

    val state = vm.uiState.collectAsState()

    val pagerState = rememberPagerState(pageCount = { TabStatus.entries.size })

    LaunchedEffect(key1 = state.value.selectedTab) {
        pagerState.scrollToPage(state.value.selectedTab.ordinal)
    }

    LaunchedEffect(key1 = pagerState.currentPage) {
        when (pagerState.currentPage) {
            0 -> vm.onTabSelected(TabStatus.CHATS)
            1 -> vm.onTabSelected(TabStatus.UPDATES)
            2 -> vm.onTabSelected(TabStatus.CALLS)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderRow()
        TabsRow(
            selectedTabIndex = state.value.selectedTab.ordinal,
            onTabChanged = { tabIndex ->
                when (tabIndex) {
                    0 -> vm.onTabSelected(TabStatus.CHATS)
                    1 -> vm.onTabSelected(TabStatus.UPDATES)
                    2 -> vm.onTabSelected(TabStatus.CALLS)
                }
            }
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) {
            when (state.value.selectedTab) {
                TabStatus.CHATS -> ChatsTab()
                TabStatus.UPDATES -> UpdatesTab()
                TabStatus.CALLS -> CallsTab()
            }
        }
    }
}

@Composable
fun HeaderRow() {
    Row(
        modifier = Modifier
            .height(72.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(top = 24.dp, bottom = 12.dp, start = 18.dp, end = 18.dp),

        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.app_header_title),
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Icon(
                Icons.Outlined.Share,
                modifier = Modifier.clickable(enabled = true, onClick = {}),
                contentDescription = stringResource(id = R.string.open_camera_icon_description),
                tint = Color.White
            )

            Icon(
                Icons.Outlined.Search,
                modifier = Modifier.clickable(enabled = true, onClick = {}),
                contentDescription = stringResource(id = R.string.open_camera_icon_description),
                tint = Color.White
            )

            Icon(
                Icons.Outlined.MoreVert,
                modifier = Modifier.clickable(enabled = true, onClick = {}),
                contentDescription = stringResource(id = R.string.open_camera_icon_description),
                tint = Color.White
            )
        }
    }
}

@Composable
fun TabsRow(
    onTabChanged: (tabIndex: Int) -> Unit = {},
    selectedTabIndex: Int
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
        indicator = { tabPositions ->
            Box(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(Color.White)
            )
        }
    ) {
        Tab(
            selected = selectedTabIndex == TabStatus.CHATS.ordinal,
            onClick = { onTabChanged(TabStatus.CHATS.ordinal) },
            text = {
                Text(text = "Chats")
            })

        Tab(
            selected = selectedTabIndex == TabStatus.UPDATES.ordinal,
            onClick = { onTabChanged(TabStatus.UPDATES.ordinal) },
            text = {
                Text(text = "Updates")
            })

        Tab(
            selected = selectedTabIndex == TabStatus.CALLS.ordinal,
            onClick = { onTabChanged(TabStatus.CALLS.ordinal) },
            text = {
                Text(text = "Calls")
            })
    }
}

@Composable
fun ChatsTab() {
    Column {
        Text(text = "Chats Tab")
    }
}

@Composable
fun UpdatesTab() {
    Column {
        Text(text = "Updates Tab")
    }
}

@Composable
fun CallsTab() {
    Column {
        Text(text = "Calls Tab")
    }
}
