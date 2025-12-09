package com.shahtott.shoee_store_compose.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.shahtott.shoee_store_compose.ui.common.CoilImage
import com.shahtott.shoee_store_compose.ui.common.DotsIndicator
import com.shahtott.shoee_store_compose.ui.common.RefreshableBox
import com.shahtott.shoee_store_compose.ui.theme.BabyBlue
import com.shahtott.shoee_store_compose.ui.theme.GrayBorder
import com.shahtott.shoee_store_compose.ui.theme.TextGray
import com.shahtott.shoee_store_compose.ui.theme.spacing
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.homeState

    HomeContent(
        viewModel::onRefreshHome,
        sliders = state.sliders,
        query = state.query,
        onQueryChange = viewModel::onQueryChange
    )
}

@Composable
fun HomeContent(
    onRefreshHome: () -> Unit,
    sliders: List<Slider>,
    query: String,
    onQueryChange: (String) -> Unit
) {
    RefreshableBox(
        modifier = Modifier.padding(4.dp),
        onRefresh = {
            onRefreshHome()
        },
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
        ) {

            HomeTopBar(query = query, onQueryChange = onQueryChange)

            Slider(
                modifier = Modifier.fillMaxWidth(),
                sliders = sliders
            )

        }
    }
}

@Composable
fun HomeTopBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    Column {
        Row(
            Modifier.padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBar(
                modifier = Modifier.weight(0.6f),
                query = query,
                onQueryChange = onQueryChange
            ) {}

            Spacer(Modifier.width(12.dp))

            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = TextGray
            )
            Spacer(Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = TextGray
            )
        }

        Spacer(Modifier.height(16.dp))

        HorizontalDivider(
            thickness = 1.5.dp,
            color = GrayBorder
        )
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String = "",
    onQueryChange: (String) -> Unit = {},
    placeholder: String = "Search Product",
    enabled: Boolean = true,
    onSearch: () -> Unit = {}
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = BabyBlue
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                color = TextGray,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        singleLine = true,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearch() }
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun Slider(
    modifier: Modifier = Modifier,
    sliders: List<Slider>
) {
    val pagerState = rememberPagerState {
        sliders.size
    }

    val coroutineScope = rememberCoroutineScope()

    // Auto-scroll implementation
    LaunchedEffect(pagerState) {
        while (true) {
            yield()
            delay(4000L) // Adjust delay for auto-scroll speed
            coroutineScope.launch {
                if (pagerState.pageCount == 0) return@launch
                val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Column(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pagerState,
            pageSpacing = 8.dp,
            pageSize = PageSize.Fill,
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            Card(
                Modifier
                    .padding(MaterialTheme.spacing.extraSmall)
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        scaleY = lerp(
                            start = 0.7f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                CoilImage(
                    url = sliders[page].image,
                    contentScale = ContentScale.Crop,
                    crossfade = true,
                    modifier = Modifier
                        .clickable {}
                        .background(MaterialTheme.colorScheme.onBackground)
                        .fillMaxSize()
                )
            }
        }

        DotsIndicator(
            modifier = Modifier
                .padding(MaterialTheme.spacing.extraSmall)
                .align(Alignment.CenterHorizontally),
            totalDots = sliders.size,
            selectedIndex = pagerState.currentPage
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeContentPreview() {
    HomeContent(
        {},
        listOf(
            Slider("https://picsum.photos/200/300"),
            Slider("https://picsum.photos/id/237/200/300"),
            Slider("https://picsum.photos/seed/picsum/200/300")
        ),
        "",
        {}
    )
}
