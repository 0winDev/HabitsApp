// kotlin
package com.example.onboarding_presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.core_presentation.HabitButton
import com.example.core_presentation.HabitTittle
import com.example.onboarding_presentation.OnboardingPageInformation
import kotlinx.coroutines.launch

@Composable
fun OnboardingPage(
    pages: List<OnboardingPageInformation>,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val corrutineScope = rememberCoroutineScope()
    Box(modifier = modifier.background(Color.White)) {
        HorizontalPager(state = pagerState) { index ->
            val information = pages[index]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                HabitTittle(information.title)
                Text(text = information.title.uppercase())
                Spacer(modifier = Modifier.height(32.dp))
                Image(
                    painter = painterResource(id = information.image),
                    contentDescription = "Onboarding Image",
                    modifier = Modifier.aspectRatio(1f),
                    contentScale = ContentScale.FillHeight,
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(text = information.subtitle.uppercase(),
                    style=MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 64.dp, start = 16.dp, end = 28.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (pagerState.currentPage == pages.lastIndex) {
                HabitButton(
                    text = "Get started",
                    modifier = Modifier.fillMaxWidth(),
                    isEnabled = true,
                    onClick = { onFinish() }
                )
            } else {
                TextButton(onClick = onFinish) {
                    Text(text = "Skip", color = MaterialTheme.colorScheme.tertiary )
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    val active = MaterialTheme.colorScheme.tertiary
                    val inactive = MaterialTheme.colorScheme.primary

                    repeat(pages.size) { index ->
                        val selected = index == pagerState.currentPage

                        androidx.compose.foundation.layout.Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .height(8.dp)
                                .width(if (selected) 24.dp else 8.dp)
                                .background(
                                    color = if (selected) active else inactive,
                                    shape = androidx.compose.foundation.shape.CircleShape
                                )
                        )
                    }
                }
            }
            TextButton(onClick = {
                corrutineScope.launch {
                    pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                }
            }) {
                Text(text = "Next", color = MaterialTheme.colorScheme.tertiary )
            }
        }
    }
}
