package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.HistoryEra
import com.example.data.model.HistoryEvent
import com.example.data.viewmodel.HistoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HistoryViewModel,
    onEventClick: (String) -> Unit,
    onAskAiClick: () -> Unit
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedEra by viewModel.selectedEra.collectAsState()
    val events by viewModel.filteredEvents.collectAsState()
    val todayEvents by viewModel.todayInHistory.collectAsState()
    val featuredEvents by viewModel.featuredEvents.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = "Logo Tra Cứu Lịch Sử",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Tra Cứu Lịch Sử",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = onAskAiClick,
                        modifier = Modifier.testTag("home_ask_ai_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = "Hỏi AI Lịch Sử",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            // Hero Banner
            item {
                HeroBannerCard(onAskAiClick = onAskAiClick)
            }

            // Search Bar
            item {
                SearchBarSection(
                    searchQuery = searchQuery,
                    onQueryChange = { viewModel.setSearchQuery(it) }
                )
            }

            // Eras Horizontal Filter Chips
            item {
                EraFilterSection(
                    selectedEra = selectedEra,
                    onEraSelected = { viewModel.setSelectedEra(it) }
                )
            }

            // On This Day Section ("Hôm nay trong lịch sử")
            if (todayEvents.isNotEmpty() && searchQuery.isBlank() && selectedEra == null) {
                item {
                    OnThisDaySection(
                        todayEvents = todayEvents,
                        onEventClick = onEventClick
                    )
                }
            }

            // Featured Events Carousel
            if (featuredEvents.isNotEmpty() && searchQuery.isBlank() && selectedEra == null) {
                item {
                    SectionHeader(
                        title = "Sự Kiện Tiêu Biểu",
                        subtitle = "Những mốc son chói lọi trong lịch sử"
                    )
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(featuredEvents, key = { it.id }) { event ->
                            FeaturedEventCard(
                                event = event,
                                onClick = { onEventClick(event.id) }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Main Events List
            item {
                SectionHeader(
                    title = if (selectedEra != null) "Sự Kiện Theo Thời Kỳ" else "Danh Sách Lịch Sử",
                    subtitle = "${events.size} sự kiện tìm thấy"
                )
            }

            if (events.isEmpty()) {
                item {
                    EmptySearchState(query = searchQuery)
                }
            } else {
                items(events, key = { it.id }) { event ->
                    HistoryEventListItem(
                        event = event,
                        onClick = { onEventClick(event.id) },
                        onBookmarkToggle = { viewModel.toggleBookmark(event.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun HeroBannerCard(onAskAiClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_history_banner_1786370466924),
                contentDescription = "Banner Lịch Sử",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.85f))
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "4000 NĂM VĂN HIẾN",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Khám Phá Lịch Sử Việt Nam & Thế Giới",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Tra cứu sự kiện, nhân vật và lắng nghe bài học hào hùng",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun SearchBarSection(
    searchQuery: String,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .testTag("search_history_input"),
        placeholder = { Text("Tìm kiếm Ngô Quyền, Điện Biên Phủ, Hùng Vương...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Tìm kiếm",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = "Xóa")
                }
            }
        },
        shape = RoundedCornerShape(24.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
private fun EraFilterSection(
    selectedEra: String?,
    onEraSelected: (String?) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            FilterChip(
                selected = selectedEra == null,
                onClick = { onEraSelected(null) },
                label = { Text("Tất cả") },
                leadingIcon = if (selectedEra == null) {
                    { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                } else null,
                modifier = Modifier.testTag("era_chip_all")
            )
        }
        items(HistoryEra.values()) { era ->
            val isSelected = selectedEra == era.code
            FilterChip(
                selected = isSelected,
                onClick = { onEraSelected(if (isSelected) null else era.code) },
                label = { Text(era.displayName) },
                leadingIcon = if (isSelected) {
                    { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                } else null,
                modifier = Modifier.testTag("era_chip_${era.code}")
            )
        }
    }
}

@Composable
private fun OnThisDaySection(
    todayEvents: List<HistoryEvent>,
    onEventClick: (String) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        SectionHeader(
            title = "Hôm Nay Trong Lịch Sử 📅",
            subtitle = "Những sự kiện đã diễn ra ngày hôm nay"
        )
        todayEvents.forEach { event ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .clickable { onEventClick(event.id) },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Today,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = event.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "${event.yearOrPeriod} • ${event.subtitle}",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Xem thêm",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
private fun FeaturedEventCard(
    event: HistoryEvent,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(240.dp)
            .height(140.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = event.yearOrPeriod,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
            Text(
                text = event.title,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = event.subtitle,
                fontSize = 11.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun HistoryEventListItem(
    event: HistoryEvent,
    onClick: () -> Unit,
    onBookmarkToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable { onClick() }
            .testTag("event_item_${event.id}"),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when(event.eraCode) {
                        HistoryEra.ANCIENT_VN.code -> Icons.Default.Fort
                        HistoryEra.FEUDAL_VN.code -> Icons.Default.Castle
                        HistoryEra.MODERN_VN.code -> Icons.Default.Flag
                        HistoryEra.WORLD_HISTORY.code -> Icons.Default.Public
                        else -> Icons.Default.Person
                    },
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = event.yearOrPeriod,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    IconButton(
                        onClick = onBookmarkToggle,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = if (event.isBookmarked) Icons.Default.Bookmark else Icons.Outlined.BookmarkBorder,
                            contentDescription = "Lưu bài viết",
                            tint = if (event.isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Text(
                    text = event.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = event.summary,
                    fontSize = 13.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = subtitle,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun EmptySearchState(query: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.SearchOff,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Không tìm thấy sự kiện nào cho '$query'",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
