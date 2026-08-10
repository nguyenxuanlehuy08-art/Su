package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.viewmodel.HistoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    viewModel: HistoryViewModel
) {
    val quizState by viewModel.quizState.collectAsState()
    val quizQuestions by viewModel.quizQuestions.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Psychology,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(26.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Trắc Nghiệm Lịch Sử",
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
                .padding(16.dp)
        ) {
            if (quizQuestions.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (quizState.isCompleted) {
                // Completed State Card
                QuizCompletedCard(
                    score = quizState.score,
                    total = quizQuestions.size,
                    onRestart = { viewModel.restartQuiz() }
                )
            } else {
                val currentQuestionIndex = quizState.currentQuestionIndex
                if (currentQuestionIndex < quizQuestions.size) {
                    val event = quizQuestions[currentQuestionIndex]

                    // Score and Progress Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Câu ${currentQuestionIndex + 1} / ${quizQuestions.size}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Điểm: ${quizState.score}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    LinearProgressIndicator(
                        progress = { (currentQuestionIndex + 1).toFloat() / quizQuestions.size },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(CircleShape),
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Question Card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Text(
                                text = event.quizQuestion ?: "",
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp,
                                lineHeight = 24.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Options List
                    val options = listOfNotNull(
                        event.quizOptionA,
                        event.quizOptionB,
                        event.quizOptionC,
                        event.quizOptionD
                    )

                    options.forEachIndexed { index, optionText ->
                        val isSelected = quizState.selectedOptionIndex == index
                        val isCorrect = index == event.quizCorrectAnswer
                        val isSubmitted = quizState.isAnswerSubmitted

                        val cardBorderColor = when {
                            isSubmitted && isCorrect -> Color(0xFF2E7D32)
                            isSubmitted && isSelected && !isCorrect -> MaterialTheme.colorScheme.error
                            isSelected -> MaterialTheme.colorScheme.primary
                            else -> MaterialTheme.colorScheme.outlineVariant
                        }

                        val cardBgColor = when {
                            isSubmitted && isCorrect -> Color(0xFFE8F5E9)
                            isSubmitted && isSelected && !isCorrect -> MaterialTheme.colorScheme.errorContainer
                            isSelected -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                            else -> MaterialTheme.colorScheme.surface
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .border(1.5.dp, cardBorderColor, RoundedCornerShape(12.dp))
                                .clickable(enabled = !isSubmitted) {
                                    viewModel.selectQuizOption(index)
                                }
                                .testTag("quiz_option_$index"),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = cardBgColor)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${('A' + index)}.",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = optionText,
                                    fontSize = 15.sp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f)
                                )
                                if (isSubmitted) {
                                    if (isCorrect) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = "Đúng",
                                            tint = Color(0xFF2E7D32)
                                        )
                                    } else if (isSelected) {
                                        Icon(
                                            imageVector = Icons.Default.Cancel,
                                            contentDescription = "Sai",
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Explanation Box if submitted
                    AnimatedVisibility(visible = quizState.isAnswerSubmitted) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = "💡 Giải Thích Lịch Sử:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = event.quizExplanation ?: "",
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // Bottom Action Button
                    if (!quizState.isAnswerSubmitted) {
                        Button(
                            onClick = { viewModel.submitQuizAnswer() },
                            enabled = quizState.selectedOptionIndex != null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("submit_quiz_answer_btn"),
                            shape = RoundedCornerShape(24.dp)
                        ) {
                            Text("Xác Nhận Đáp Án", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    } else {
                        Button(
                            onClick = { viewModel.nextQuizQuestion() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("next_quiz_question_btn"),
                            shape = RoundedCornerShape(24.dp)
                        ) {
                            Text(
                                text = if (currentQuestionIndex < quizQuestions.size - 1) "Câu Tiếp Theo ➔" else "Xem Kết Quả 🏆",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun QuizCompletedCard(
    score: Int,
    total: Int,
    onRestart: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.EmojiEvents,
                contentDescription = null,
                tint = Color(0xFFFFD700),
                modifier = Modifier.size(72.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Hoàn Thành Ôn Tập Lịch Sử!",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Bạn đã trả lời đúng $score trên $total câu hỏi",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onRestart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("restart_quiz_btn"),
                shape = RoundedCornerShape(24.dp)
            ) {
                Icon(imageVector = Icons.Default.RestartAlt, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Làm Lại Trắc Nghiệm", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
