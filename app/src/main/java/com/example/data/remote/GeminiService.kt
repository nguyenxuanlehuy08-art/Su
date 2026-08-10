package com.example.data.remote

import com.example.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

data class GeminiPart(val text: String? = null)

data class GeminiContent(val parts: List<GeminiPart>)

data class GeminiRequest(
    val contents: List<GeminiContent>,
    val systemInstruction: GeminiContent? = null
)

data class GeminiCandidate(val content: GeminiContent? = null)

data class GeminiResponse(val candidates: List<GeminiCandidate>? = null)

interface GeminiApiService {
    @POST("v1beta/models/gemini-3.5-flash:generateContent")
    suspend fun askGemini(
        @Query("key") apiKey: String,
        @Body request: GeminiRequest
    ): GeminiResponse
}

object GeminiClient {
    private const val BASE_URL = "https://generativelanguage.googleapis.com/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    val service: GeminiApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(GeminiApiService::class.java)
    }

    suspend fun answerHistoryQuestion(userPrompt: String): String = withContext(Dispatchers.IO) {
        val apiKey = try {
            BuildConfig.GEMINI_API_KEY
        } catch (e: Exception) {
            ""
        }

        if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext getOfflineAiFallback(userPrompt)
        }

        val request = GeminiRequest(
            contents = listOf(
                GeminiContent(parts = listOf(GeminiPart(text = userPrompt)))
            ),
            systemInstruction = GeminiContent(
                parts = listOf(
                    GeminiPart(
                        text = "Bạn là Trợ Lý Lịch Sử AI chuyên nghiệp, am hiểu sâu sắc về Lịch sử Việt Nam và Lịch sử Thế giới. Hãy trả lời câu hỏi lịch sử của người dùng một cách chính xác, truyền cảm, hấp dẫn, có cấu trúc rõ ràng (kèm mốc thời gian, ý nghĩa lịch sử) bằng tiếng Việt."
                    )
                )
            )
        )

        try {
            val response = service.askGemini(apiKey, request)
            val replyText = response.candidates
                ?.firstOrNull()
                ?.content
                ?.parts
                ?.firstOrNull()
                ?.text

            if (!replyText.isNullOrBlank()) {
                replyText
            } else {
                getOfflineAiFallback(userPrompt)
            }
        } catch (e: Exception) {
            getOfflineAiFallback(userPrompt)
        }
    }

    private fun getOfflineAiFallback(prompt: String): String {
        val p = prompt.lowercase()
        return when {
            p.contains("bạch đằng") -> """
                📜 **Chiến thắng Bạch Đằng năm 938**:
                - **Chỉ huy**: Ngô Quyền.
                - **Chiến thuật**: Cắm cọc gỗ đầu bọc sắt dưới lòng sông Bạch Đằng, lừa quân Nam Hán vào trận địa khi thủy triều lên rồi đánh triệt hạ khi thủy triều rút.
                - **Ý nghĩa**: Chấm dứt hơn 1000 năm Bắc thuộc, mở ra kỷ nguyên độc lập tự chủ lâu dài cho dân tộc Việt Nam.
            """.trimIndent()
            p.contains("trần") || p.contains("nguyên mông") -> """
                📜 **Nhà Trần 3 lần đại thắng quân Nguyên Mông (1258, 1285, 1288)**:
                - **Lãnh đạo**: Hưng Đạo Đại Vương Trần Quốc Tuấn và các Vua Trần.
                - **Chiến lược**: 'Vườn không nhà trống', khơi dậy hào khí Đông A với khẩu hiệu 'Sát Thát'.
                - **Ý nghĩa**: Đập tan tham vọng của đế chế Mông Cổ hùng mạnh nhất thế giới thời bấy giờ, bảo vệ nền độc lập Đại Việt.
            """.trimIndent()
            p.contains("quang trung") || p.contains("nguyễn huệ") -> """
                📜 **Hoàng đế Quang Trung (Nguyễn Huệ) đại phá quân Thanh (1789)**:
                - **Chiến công tiêu biểu**: Trận Ngọc Hồi - Đống Đa Tết Kỷ Dậu 1789.
                - **Chiến thuật**: Tấn công thần tốc, táo bạo, bất ngờ đánh tan 29 vạn quân Thanh do Tôn Sĩ Nghị chỉ huy chỉ trong 5 ngày Tết.
            """.trimIndent()
            p.contains("điện biên phủ") -> """
                📜 **Chiến dịch Điện Biên Phủ 1954**:
                - **Tổng chỉ huy**: Đại tướng Võ Nguyên Giáp.
                - **Phương châm**: Chuyển từ 'Đánh nhanh giải quyết nhanh' sang 'Đánh chắc tiến chắc'.
                - **Ý nghĩa**: 'Lừng lẫy năm châu, chấn động địa cầu', kết thúc chiến tranh xâm lược của thực dân Pháp tại Đông Dương.
            """.trimIndent()
            else -> """
                🏛️ **Hệ thống Tra Cứu Lịch Sử AI**:
                Cảm ơn bạn đã hỏi về chủ đề lịch sử!
                
                Lịch sử Việt Nam trải qua hơn 4.000 năm văn hiến từ thời các Vua Hùng dựng nước Văn Lang, qua các triều đại phong kiến oanh liệt (Ngô, Đinh, Tiền Lê, Lý, Trần, Lê, Nguyễn) cho đến cuộc đấu tranh giải phóng dân tộc hiện đại.
                
                *Mẹo: Bạn có thể tra cứu chi tiết từng sự kiện, dòng thời gian và làm bài trắc nghiệm ngay tại các mục trên ứng dụng!*
            """.trimIndent()
        }
    }
}
