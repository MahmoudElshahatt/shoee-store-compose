package com.shahtott.shoee_store_compose.ui.common

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefreshableBox(
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    var isRefreshing by remember { mutableStateOf(false) }
    val state = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()
    val onRefreshWithDelay: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            delay(500)
            onRefresh()
            isRefreshing = false
        }
    }

    PullToRefreshBox(
        modifier = modifier.padding(4.dp),
        state = state,
        isRefreshing = isRefreshing,
        onRefresh = onRefreshWithDelay,
        content = content
    )
}