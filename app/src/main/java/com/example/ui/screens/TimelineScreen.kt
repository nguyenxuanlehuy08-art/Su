package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Castle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Fort
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.HistoryEra
import com.example.data.model.HistoryEvent
import com.example.data.viewmodel.HistoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimelineScreen(
    viewModel: HistoryViewModel,
    onEventClick: (String) -> Unit
) {
    val events by viewModel.filteredEvents.collectAsState()
    val selectedEra by viewModel.selectedEra.collectAsState()

    val sortedEvents = remember(events) {
        events.sortedBy { it.sortYear }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.History,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(26.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Dòng Thời Gian Lịch Sử",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Era Selection Filter Chips
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    FilterChip(
                        selected = selectedEra == null,
                        onClick = { viewModel.setSelectedEra(null) },
                        label = { Text("Toàn bộ Dòng thời gian") },
                        leadingIcon = if (selectedEra == null) {
                            { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                        } else null,
                        modifier = Modifier.testTag("timeline_era_chip_all")
                    )
                }
                items(HistoryEra.values()) { era ->
                    val isSelected = selectedEra == era.code
                    FilterChip(
                        selected = isSelected,
                        onClick = { viewModel.setSelectedEra(if (isSelected) null else era.code) },
                        label = { Text(era.displayName) },
                        leadingIcon = if (isSelected) {
                            { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                        } else null,
                        modifier = Modifier.testTag("timeline_era_chip_${era.code}")
                    )
                }
            }

            if (sortedEvents.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Không có sự kiện nào trong giai đoạn này",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    items(sortedEvents.size, key = { index -> sortedEvents[index].id }) { index ->
                        val event = sortedEvents[index]
                        val isLast = index == sortedEvents.size - 1

                        TimelineNodeItem(
                            event = event,
                            isLast = isLast,
                            onClick = { onEventClick(event.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TimelineNodeItem(
    event: HistoryEvent,
    isLast: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag("timeline_node_${event.id}"),
        verticalAlignment = Alignment.Top
    ) {
        // Timeline Vertical Axis & Indicator Node
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(48.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
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
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }

            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .height(110.dp)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Content Card
        Card(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Surface(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = event.yearOrPeriod,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

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
