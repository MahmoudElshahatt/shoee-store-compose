package com.shahtott.shoee_store_compose.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.shahtott.shoee_store_compose.ui.common.RefreshableBox
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.homeState

    HomeContent(
        viewModel::onRefreshHome,
        sliders = state.sliders
    )
}

@Composable
fun HomeContent(
    onRefreshHome: () -> Unit,
    sliders: List<Slider>
) {

    RefreshableBox(
        modifier = Modifier.padding(4.dp),
        onRefresh = {
            onRefreshHome()
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
           // Slider(sliders = sliders)

        }
    }
}

//@Composable
//fun Slider(
//    modifier: Modifier = Modifier,
//    sliders: List<Slider>
//) {
//    val pagerState = rememberPagerState {
//        sliders.size
//    }
//
//    val coroutineScope = rememberCoroutineScope()
//
//    // Auto-scroll implementation
//    LaunchedEffect(pagerState) {
//        while (true) {
//            yield()
//            delay(4000L) // Adjust delay for auto-scroll speed
//            coroutineScope.launch {
//                if (pagerState.pageCount == 0) return@launch
//                val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
//                pagerState.animateScrollToPage(nextPage)
//            }
//        }
//    }
//
//    Column(
//        modifier = modifier
//    ) {
//        HorizontalPager(
//            state = pagerState,
//            pageSpacing = 8.dp,
//            pageSize = PageSize.Fill,
//            modifier = Modifier
//                .fillMaxWidth()
//        ) { page ->
//
//            Card(
//                Modifier
//                    .padding(PaddingDimensions.verySmall)
//                    .fillMaxWidth()
//                    .height(ImagesSizes.xAndHalfLarge)
//                    .graphicsLayer {
//                        // Calculate the absolute offset for the current page from the
//                        // scroll position. We use the absolute value which allows us to mirror
//                        // any effects for both directions
//                        val pageOffset = (
//                                (pagerState.currentPage - page) + pagerState
//                                    .currentPageOffsetFraction
//                                ).absoluteValue
//
//
//                        scaleY = lerp(
//                            start = 0.7f,
//                            stop = 1f,
//                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
//                        )
//
//                    }
//            ) {
//                CoilImage(
//                    url = sliders[page].image ?: "",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .clickable {
//                            navController.navigate(
//                                Screens.ImagePreviewScreen(
//                                    imageUrls = sliders.map {
//                                        it.image ?: ""
//                                    },
//                                    openedPosition = page
//                                )
//                            )
//                        }
//                        .fillMaxSize()
//                        .background(MaterialTheme.colorScheme.onBackground)
//                        .fillMaxSize()
//                )
//            }
//
//
//        }
//
//        DotsIndicator(
//            modifier = Modifier
//                .padding(PaddingDimensions.mini)
//                .align(Alignment.CenterHorizontally),
//            totalDots = sliders.size,
//            selectedIndex = pagerState.currentPage
//        )
//
//    }
//
//}

@Preview(showSystemUi = true)
@Composable
fun HomeContentPreview() {
    HomeScreen()
}
