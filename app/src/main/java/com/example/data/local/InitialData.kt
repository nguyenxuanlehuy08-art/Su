package com.example.data.local

import com.example.data.model.HistoryEra
import com.example.data.model.HistoryEvent

object InitialData {
    val sampleEvents = listOf(
        HistoryEvent(
            id = "hung_vuong_van_lang",
            title = "Thời Kỳ Hùng Vương - Nước Văn Lang",
            subtitle = "Khởi đầu văn minh sông Hồng và cội nguồn dân tộc Việt Nam",
            yearOrPeriod = "Khoảng 2879 TCN - 258 TCN",
            sortYear = -2879,
            eraCode = HistoryEra.ANCIENT_VN.code,
            dayOfMonth = 10,
            month = 3, // Giỗ Tổ Hùng Vương 10/3 âm lịch
            summary = "Nước Văn Lang ra đời do Kinh Dương Vương và các Vua Hùng trị vì. Đây là mốc khởi đầu hình thành nhà nước đầu tiên của dân tộc Việt Nam với nền văn hóa Đông Sơn rực rỡ.",
            fullContent = """
                Theo truyền thuyết, Kinh Dương Vương khai sinh ra nước Xích Quỷ, sau đó Lạc Long Quân kết duyên cùng Âu Cơ sinh ra bọc trăm trứng, nở thành 100 người con. 50 người theo mẹ lên núi, 50 người theo cha xuống biển, cùng nhau xây dựng xóm làng.
                
                Người con trưởng lên làm vua, hiệu là Hùng Vương, đặt tên nước là Văn Lang, đóng đô ở Phong Châu (Phú Thọ ngày nay).
                
                Thời kỳ Hùng Vương kéo dài qua 18 đời vua, nổi tiếng với sự phát triển đúc trống đồng Đông Sơn, trồng lúa nước, cày bằng điền, cùng các truyền thuyết kinh điển như Bánh Chưng Bánh Giầy, Sơn Tinh Thủy Tinh, Thánh Gióng.
            """.trimIndent(),
            historicalSignificance = "Hình thành cội nguồn văn hóa dân tộc, tạo nền tảng ý thức đoàn kết 'đồng bào' và lòng yêu nước bảo vệ giang sơn.",
            keyFigures = "Kinh Dương Vương, Lạc Long Quân, Âu Cơ, Các Vua Hùng, Thánh Gióng, Mai An Tiêm",
            location = "Phong Châu (Phú Thọ, Việt Nam)",
            isFeatured = true,
            quizQuestion = "Nước Văn Lang - nhà nước đầu tiên của người Việt - do ai sáng lập và đóng đô ở đâu?",
            quizOptionA = "An Dương Vương đóng đô ở Cổ Loa",
            quizOptionB = "Hùng Vương đóng đô ở Phong Châu (Phú Thọ)",
            quizOptionC = "Lý Nam Đế đóng đô ở Long Biên",
            quizOptionD = "Đinh Bộ Lĩnh đóng đô ở Hoa Lư",
            quizCorrectAnswer = 1,
            quizExplanation = "Nước Văn Lang do các Vua Hùng đứng đầu, đóng đô ở Phong Châu (nay thuộc tỉnh Phú Thọ)."
        ),
        HistoryEvent(
            id = "bach_dang_938",
            title = "Chiến Thắng Bạch Đằng Năm 938",
            subtitle = "Chấm dứt hơn 1000 năm Bắc thuộc, mở ra thời kỳ độc lập tự chủ lâu dài",
            yearOrPeriod = "Năm 938",
            sortYear = 938,
            eraCode = HistoryEra.FEUDAL_VN.code,
            dayOfMonth = 31,
            month = 12,
            summary = "Ngô Quyền dùng chiến thuật cắm cọc gỗ đầu bọc sắt xuống lòng sông Bạch Đằng, đánh tan quân Nam Hán do Lưu Hoằng Tháo chỉ huy.",
            fullContent = """
                Năm 938, vua Nam Hán sai con là Lưu Hoằng Tháo đem thủy quân sang xâm lược nước ta. Nhận định thủy triều sông Bạch Đằng lên xuống rất nhanh, Ngô Quyền đã cho cắm hàng ngàn cọc gỗ vạt nhọn bọc sắt dưới lòng sông.
                
                Khi thủy triều lên, Ngô Quyền cho thuyền nhẹ ra khiêu chiến rồi giả vờ thua rút chạy. Lưu Hoằng Tháo mắc mưu đuổi theo. Khi nước thủy triều rút mạnh, thuyền chiến nặng nề của quân Nam Hán va phải cọc nhọn vỡ nát, lật úp.
                
                Quân ta từ hai bên bờ đánh khép lại, tiêu diệt toàn bộ lực lượng địch. Lưu Hoằng Tháo tử trận tại chỗ.
            """.trimIndent(),
            historicalSignificance = "Trận thắng mộc mạc nhưng vĩ đại này đã chấm dứt hoàn toàn ách đô hộ hơn 10 thế kỷ của các triều đại phong kiến phương Bắc, mở ra kỷ nguyên độc lập lâu dài.",
            keyFigures = "Ngô Quyền, Lưu Hoằng Tháo, Kiều Công Tiễn",
            location = "Sông Bạch Đằng (Quảng Ninh - Hải Phòng)",
            isFeatured = true,
            quizQuestion = "Chiến thuật nổi tiếng nào đã giúp Ngô Quyền đánh tan quân Nam Hán trên sông Bạch Đằng năm 938?",
            quizOptionA = "Dùng hỏa công thiêu rụi hạm đội",
            quizOptionB = "Cắm cọc gỗ đầu bọc sắt dưới lòng sông kết hợp với thủy triều",
            quizOptionC = "Vây thành đào hầm ngầm",
            quizOptionD = "Phục kích trên vùng núi hiểm trở",
            quizCorrectAnswer = 1,
            quizExplanation = "Ngô Quyền đã vận dụng tài tình quy luật thủy triều kết hợp trận địa cọc gỗ bọc sắt chôn sâu dưới lòng sông Bạch Đằng."
        ),
        HistoryEvent(
            id = "ly_thuong_kiet_nam_quoc_son_ha",
            title = "Bài Thơ Nam Quốc Sơn Hà & Phòng Tuyến Sông Như Nguyệt",
            subtitle = "Tuyên ngôn độc lập đầu tiên của dân tộc Việt Nam (1077)",
            yearOrPeriod = "Năm 1077",
            sortYear = 1077,
            eraCode = HistoryEra.FEUDAL_VN.code,
            dayOfMonth = 15,
            month = 2,
            summary = "Lý Thường Kiệt lãnh đạo quân dân nhà Lý đánh tan 30 vạn quân Tống xâm lược tại phòng tuyến sông Như Nguyệt.",
            fullContent = """
                Năm 1075-1077, quân Tống do Quách Quỳ chỉ huy tràn sang xâm lược Đại Việt. Thái úy Lý Thường Kiệt chủ động xây dựng phòng tuyến sông Như Nguyệt (sông Cầu ngày nay).
                
                Trong đêm tối thâm nghiêm tại đền thờ Trương Hống, Trương Hát, bài thơ thần 'Nam Quốc Sơn Hà' đã vang lên đầy khí phách:
                'Nam quốc sơn hà Nam đế cư / Tiệt nhiên định phận tại thiên thư / Như hà nghịch lỗ lai xâm phạm / Nhữ đẳng hành khán thủ bại hư!'
                
                Bài thơ cổ vũ mạnh mẽ tinh thần chiến đấu của quân dân Đại Việt, khiến quân Tống hoảng sợ bấn loạn và nhận thất bại cay đắng.
            """.trimIndent(),
            historicalSignificance = "Nam Quốc Sơn Hà được coi là bản Tuyên ngôn Độc lập đầu tiên của Việt Nam, khẳng định chủ quyền lãnh thổ thiêng liêng.",
            keyFigures = "Lý Thường Kiệt, Quách Quỳ, Vua Lý Nhân Tông",
            location = "Sông Như Nguyệt (Sông Cầu, Bắc Ninh)",
            isFeatured = true,
            quizQuestion = "Bài thơ nào được mệnh danh là Bản Tuyên ngôn Độc lập đầu tiên của nước ta?",
            quizOptionA = "Bình Ngô Đại Cáo",
            quizOptionB = "Nam Quốc Sơn Hà",
            quizOptionC = "Tuyên ngôn Độc lập 1945",
            quizOptionD = "Hịch Tướng Sĩ",
            quizCorrectAnswer = 1,
            quizExplanation = "Bài thơ Nam Quốc Sơn Hà do Lý Thường Kiệt đọc trên phòng tuyến sông Như Nguyệt năm 1077 được coi là bản Tuyên ngôn Độc lập đầu tiên."
        ),
        HistoryEvent(
            id = "nha_tran_ba_lan_thang_nguyen_mong",
            title = "Nhà Trần Ba Lần Đánh Tan Quân Nguyên Mông",
            subtitle = "Trần Hưng Đạo và hào khí Đông A đè bẹp đế chế hùng mạnh nhất thế giới",
            yearOrPeriod = "1258 - 1285 - 1288",
            sortYear = 1288,
            eraCode = HistoryEra.FEUDAL_VN.code,
            dayOfMonth = 9,
            month = 4,
            summary = "Nhà Trần dưới sự lãnh đạo của Vua Trần, Hưng Đạo Vương Trần Quốc Tuấn và tinh thần 'Vạn kiếp công thành' đã 3 lần đánh bại quân Nguyên Mông hung hãn.",
            fullContent = """
                Đế quốc Nguyên Mông lúc bấy giờ bá chủ từ Á sang Âu. Tuy nhiên, khi xâm lược Đại Việt vào các năm 1258, 1285 và 1288, chúng đã nếm trải thất bại ê chề.
                
                Với chiến thuật 'Vườn không nhà trống', Hịch Tướng Sĩ rực lửa và quyết tâm 'Sát Thát' khắc trên cánh tay, quân dân nhà Trần đã đánh bại Thoát Hoan, Ô Mã Nhi.
                
                Trận Bạch Đằng năm 1288 lừng lẫy, Trần Hưng Đạo tái hiện trận địa cọc nhọn, bắt sống tướng Ô Mã Nhi, đập tan hoàn toàn ý đồ xâm lược của đế chế Mông Cổ.
            """.trimIndent(),
            historicalSignificance = "Bảo vệ toàn vẹn độc lập Đại Việt, đập tan huyền thoại 'quân Mông Cổ bách chiến bách thắng', cứu vãn khu vực Đông Nam Á khỏi thảm họa diệt vong.",
            keyFigures = "Trần Hưng Đạo (Trần Quốc Tuấn), Trần Thái Tông, Trần Nhân Tông, Trần Quốc Toản, Thoát Hoan, Ô Mã Nhi",
            location = "Thăng Long, Bạch Đằng, Vạn Kiếp",
            isFeatured = true,
            quizQuestion = "Ai là vị Tiết chế Quốc công vĩ đại chỉ huy quân dân nhà Trần 3 lần đánh tan đế chế Nguyên Mông?",
            quizOptionA = "Trần Quang Khải",
            quizOptionB = "Trần Quốc Toản",
            quizOptionC = "Trần Hưng Đạo (Trần Quốc Tuấn)",
            quizOptionD = "Trần Thủ Độ",
            quizCorrectAnswer = 2,
            quizExplanation = "Hưng Đạo Đại Vương Trần Quốc Tuấn là nhà quân sự thiên tài, tác giả Hịch Tướng Sĩ và chỉ huy tối cao trong cuộc chiến chống Nguyên Mông."
        ),
        HistoryEvent(
            id = "quang_trung_dai_phat_quan_thanh",
            title = "Vua Quang Trung Đại Phá 29 Vạn Quân Thanh",
            subtitle = "Chiến thắng Ngọc Hồi - Đống Đa thần tốc mùa xuân Kỷ Dậu 1789",
            yearOrPeriod = "Đêm Mùng 4 Rạng Mùng 5 Tết Kỷ Dậu 1789",
            sortYear = 1789,
            eraCode = HistoryEra.FEUDAL_VN.code,
            dayOfMonth = 30,
            month = 1, // Mùng 5 Tết
            summary = "Hoàng đế Quang Trung Nguyễn Huệ kéo quân thần tốc ra Bắc, đánh tan 29 vạn quân Thanh xâm lược chỉ trong 5 ngày Tết.",
            fullContent = """
                Tháng 12 năm 1788, Tôn Sĩ Nghị dẫn 29 vạn quân Thanh tràn vào chiếm Thăng Long. Nguyễn Huệ lên ngôi Hoàng đế tại Phú Xuân (Huế), lấy hiệu Quang Trung rồi lập tức tiến quân ra Bắc.
                
                Quang Trung đưa ra lời dụ lịch sử: 'Đánh cho đốm răng / Đánh cho kẻ thù kinh hồn bạt vía'. Tại Tam Điệp, ông cho quân ăn Tết sớm và hứa mùng 7 Tết sẽ vào Thăng Long mở tiệc ăn Tết lại.
                
                Đêm mùng 4 rạng mùng 5 Tết Kỷ Dậu (1789), quân Tây Sơn mở trận tiến công vào đồn Ngọc Hồi - Đống Đa. Quân Thanh hoảng loạn giẫm đạp lên nhau mà chết, Tôn Sĩ Nghị không kịp thắng yên ngựa, tháo chạy về nước.
            """.trimIndent(),
            historicalSignificance = "Một trong những chiến công chống ngoại xâm oanh liệt nhất, thể hiện tài nghệ quân sự thần tốc, táo bạo đỉnh cao của Nguyễn Huệ.",
            keyFigures = "Quang Trung (Nguyễn Huệ), Tôn Sĩ Nghị, Sầm Nghi Đống, Ngô Thì Nhậm",
            location = "Ngọc Hồi, Đống Đa, Thăng Long",
            isFeatured = true,
            quizQuestion = "Hoàng đế Quang Trung đã đánh tan 29 vạn quân Thanh vào dịp nào?",
            quizOptionA = "Mùa hè năm 1788",
            quizOptionB = "Dịp Tết Kỷ Dậu năm 1789",
            quizOptionC = "Mùa thu năm 1792",
            quizOptionD = "Tháng chạp năm 1785",
            quizCorrectAnswer = 1,
            quizExplanation = "Trận đại phá quân Thanh diễn ra thần tốc vào dịp Tết Kỷ Dậu năm 1789, tạo nên chiến thắng Đống Đa lẫy lừng."
        ),
        HistoryEvent(
            id = "dien_bien_phu_1954",
            title = "Chiến dịch Điện Biên Phủ 1954",
            subtitle = "Chiến công lừng lẫy năm châu, chấn động địa cầu",
            yearOrPeriod = "13/03/1954 - 07/05/1954",
            sortYear = 1954,
            eraCode = HistoryEra.MODERN_VN.code,
            dayOfMonth = 7,
            month = 5,
            summary = "Quân đội Nhân dân Việt Nam do Đại tướng Võ Nguyên Giáp chỉ huy đã hoàn toàn tiêu diệt tập đoàn căn cứ điểm Điện Biên Phủ của Pháp.",
            fullContent = """
                Sau 56 ngày đêm 'khoét núi, ngủ hầm, mưa dầm, cơm nắm', quân dân Việt Nam đã đập tan tập đoàn căn cứ điểm bất khả xâm phạm của thực dân Pháp.
                
                Đại tướng Võ Nguyên Giáp đã có quyết định lịch sử: chuyển từ phương châm 'Đánh nhanh giải quyết nhanh' sang 'Đánh chắc tiến chắc'.
                
                Chiều 7/5/1954, lá cờ 'Quyết chiến Quyết thắng' tung bay trên nóc hầm tướng De Castries, báo hiệu kết thúc chiến dịch.
            """.trimIndent(),
            historicalSignificance = "Chấm dứt hoàn toàn ách thực dân Pháp kéo dài gần 100 năm tại Indochina, thúc đẩy phong trào giải phóng dân tộc trên toàn thế giới.",
            keyFigures = "Chủ tịch Hồ Chí Minh, Đại tướng Võ Nguyên Giáp, Tướng De Castries, Cù Chính Lan, Tô Vĩnh Diện, Bế Văn Đàn",
            location = "Điện Biên Phủ, Tỉnh Điện Biên",
            isFeatured = true,
            quizQuestion = "Ai là Tổng chỉ huy Chiến dịch Điện Biên Phủ năm 1954?",
            quizOptionA = "Đại tướng Nguyễn Chí Thanh",
            quizOptionB = "Đại tướng Võ Nguyên Giáp",
            quizOptionC = "Đại tướng Văn Tiến Dũng",
            quizOptionD = "Đại tướng Lê Trọng Tấn",
            quizCorrectAnswer = 1,
            quizExplanation = "Đại tướng Võ Nguyên Giáp là Tổng chỉ huy kiêm Bí thư Đảng ủy chỉ đạo chiến dịch Điện Biên Phủ."
        ),
        HistoryEvent(
            id = "giai_phong_mien_nam_1975",
            title = "Đại Thắng Mùa Xuân 1975 & Giải Phóng Miền Nam",
            subtitle = "Thu giang sơn về một mối, mở ra kỷ nguyên độc lập, thống nhất hoàn toàn",
            yearOrPeriod = "30/04/1975",
            sortYear = 1975,
            eraCode = HistoryEra.MODERN_VN.code,
            dayOfMonth = 30,
            month = 4,
            summary = "Trưa ngày 30/4/1975, xe tăng quân giải phóng húc văng cổng Dinh Độc Lập, kết thúc chiến dịch Hồ Chí Minh lịch sử.",
            fullContent = """
                Chiến dịch Hồ Chí Minh lịch sử bắt đầu từ ngày 26/4/1975. Quân giải phóng tiến vào Sài Gòn từ 5 hướng.
                
                Lúc 10h45 ngày 30/4/1975, xe tăng mang số hiệu 390 và 843 thuộc Lữ đoàn xe tăng 203 húc văng cổng phụ và cổng chính Dinh Độc Lập.
                
                Trung úy Bùi Quang Thận cắm lá cờ Mặt trận Dân tộc Giải phóng Miền Nam Việt Nam trên nóc Dinh Độc Lập lúc 11h30, chấm dứt hoàn toàn cuộc chiến tranh kéo dài 21 năm.
            """.trimIndent(),
            historicalSignificance = "Giải phóng hoàn toàn Miền Nam, thống nhất Tổ quốc, đất nước bước vào thời kỳ phát triển và hội nhập.",
            keyFigures = "Chủ tịch Hồ Chí Minh, Đại tướng Văn Tiến Dũng, Bùi Quang Thận, Vũ Đăng Toàn",
            location = "Sài Gòn (TP. Hồ Chí Minh)",
            isFeatured = true,
            quizQuestion = "Chiến dịch cuối cùng giải phóng Miền Nam thống nhất đất nước mang tên gì?",
            quizOptionA = "Chiến dịch Tây Nguyên",
            quizOptionB = "Chiến dịch Huế - Đà Nẵng",
            quizOptionC = "Chiến dịch Hồ Chí Minh",
            quizOptionD = "Chiến dịch Đường 9 - Nam Lào",
            quizCorrectAnswer = 2,
            quizExplanation = "Bộ Chính trị đã quyết định đổi tên Chiến dịch giải phóng Sài Gòn - Gia Định thành 'Chiến dịch Hồ Chí Minh'."
        ),
        HistoryEvent(
            id = "the_chien_thu_nhat",
            title = "Thế Chiến Thứ Nhất (World War I)",
            subtitle = "Cuộc đại chiến thế giới đầu tiên trong lịch sử nhân loại (1914 - 1918)",
            yearOrPeriod = "1914 - 1918",
            sortYear = 1914,
            eraCode = HistoryEra.WORLD_HISTORY.code,
            dayOfMonth = 28,
            month = 6,
            summary = "Xung đột quy mô lớn giữa hai phe Hiệp ước và Liên minh, bắt nguồn từ vụ ám sát Thái tử Áo-Hưng Franz Ferdinand.",
            fullContent = """
                Ngày 28/6/1914, Thái tử Franz Ferdinand của Áo-Hưng bị ám sát tại Sarajevo. Sự kiện này là mồi lửa châm bùng Thế chiến I.
                
                Cuộc chiến quy tụ hơn 30 quốc gia, nổi bật với chiến tranh hào giao thông, sử dụng vũ khí hóa học, xe tăng và máy bay quân sự lần đầu tiên.
                
                Chiến tranh kết thúc ngày 11/11/1918 khi Đức ký hiệp định đình chiến, dẫn tới sự sụp đổ của 4 đế quốc lớn: Nga, Đức, Áo-Hưng và Ottoman.
            """.trimIndent(),
            historicalSignificance = "Vẽ lại bản đồ địa chính trị châu Âu, châm ngòi cho Cách mạng Tháng Mười Nga và thành lập Hội Quốc Liên.",
            keyFigures = "Franz Ferdinand, Woodrow Wilson, Tsar Nicholas II, Kaiser Wilhelm II",
            location = "Châu Âu, Trung Đông",
            isFeatured = false,
            quizQuestion = "Sự kiện trực tiếp nào đã làm châm ngòi cho Thế chiến thứ nhất bùng nổ năm 1914?",
            quizOptionA = "Đức tấn công Ba Lan",
            quizOptionB = "Thái tử Áo-Hưng Franz Ferdinand bị ám sát tại Sarajevo",
            quizOptionC = "Nhật Bản đánh Trận Trân Châu Cảng",
            quizOptionD = "Cách mạng Tháng Mười Nga thành công",
            quizCorrectAnswer = 1,
            quizExplanation = "Vụ ám sát Thái tử Áo-Hưng Franz Ferdinand tại Sarajevo ngày 28/6/1914 là ngòi nổ châm bùng Thế chiến I."
        ),
        HistoryEvent(
            id = "the_chien_thu_hai",
            title = "Thế Chiến Thứ Hai (World War II)",
            subtitle = "Cuộc chiến tàn khốc nhất lịch sử nhân loại (1939 - 1945)",
            yearOrPeriod = "1939 - 1945",
            sortYear = 1939,
            eraCode = HistoryEra.WORLD_HISTORY.code,
            dayOfMonth = 2,
            month = 9,
            summary = "Chiến tranh giữa phe Đồng Minh và phe Trục Phát xít, liên quan đến hơn 100 triệu binh sĩ trên toàn cầu.",
            fullContent = """
                Thế chiến II bùng nổ ngày 1/9/1939 khi Đức Phát xít xâm lược Ba Lan. Cuộc chiến lan rộng sang Châu Á, Châu Phi và Thái Bình Dương.
                
                Các trận đánh lịch sử bao gồm: Trận Stalingrad, Trận Trân Châu Cảng, Cuộc đổ bộ Normandy (D-Day).
                
                Chiến tranh kết thúc năm 1945 sau khi Phát xít Đức đầu hàng và Mỹ thả 2 quả bom nguyên tử xuống Hiroshima và Nagasaki (Nhật Bản).
            """.trimIndent(),
            historicalSignificance = "Dẫn tới sự ra đời của Liên Hợp Quốc (UN), khởi đầu Trật tự hai cực Yalta và Thời kỳ Chiến tranh Lạnh.",
            keyFigures = "Winston Churchill, Franklin D. Roosevelt, Joseph Stalin, Dwight D. Eisenhower, Adolf Hitler",
            location = "Toàn cầu",
            isFeatured = true,
            quizQuestion = "Tổ chức quốc tế nào được thành lập sau Thế chiến II nhằm giữ gìn hòa bình và an ninh thế giới?",
            quizOptionA = "Hội Quốc Liên",
            quizOptionB = "Liên Hợp Quốc (UN)",
            quizOptionC = "Liên minh Châu Âu (EU)",
            quizOptionD = "Khối ASEAN",
            quizCorrectAnswer = 1,
            quizExplanation = "Liên Hợp Quốc (United Nations) ra đời năm 1945 nhằm ngăn chặn các cuộc xung đột toàn cầu tương tự Thế chiến II."
        ),
        HistoryEvent(
            id = "ho_chi_minh_doc_tuyen_ngon_1945",
            title = "Chủ Tịch Hồ Chí Minh Đọc Tuyên Ngôn Độc Lập 2/9/1945",
            subtitle = "Khai sinh nước Việt Nam Dân chủ Cộng hòa",
            yearOrPeriod = "02/09/1945",
            sortYear = 1945,
            eraCode = HistoryEra.FAMOUS_FIGURES.code,
            dayOfMonth = 2,
            month = 9,
            summary = "Tại Quảng trường Ba Đình lịch sử, Chủ tịch Hồ Chí Minh đã trang trọng đọc bản Tuyên ngôn Độc lập khai sinh nước Việt Nam Dân chủ Cộng hòa.",
            fullContent = """
                Chiều ngày 2/9/1945, tại Quảng trường Ba Đình (Hà Nội), trước hàng chục vạn đồng bào, Chủ tịch Hồ Chí Minh thay mặt Chính phủ lâm thời đọc bản Tuyên ngôn Độc lập.
                
                Bản Tuyên ngôn khẳng định: 'Tất cả mọi người sinh ra đều có quyền bình đẳng. Tạo hóa cho họ những quyền không ai có thể xâm phạm được; trong những quyền ấy, có quyền được sống, quyền tự do và quyền mong cầu hạnh phúc'.
                
                Chủ tịch Hồ Chí Minh dõng đạc tuyên bố: 'Nước Việt Nam có quyền hưởng tự do và độc lập, và sự thật đã thành một nước tự do độc lập. Toàn thể dân tộc Việt Nam quyết đem tất cả tinh thần và lực lượng, tính mạng và của cải để giữ vững quyền tự do, độc lập ấy!'
            """.trimIndent(),
            historicalSignificance = "Chấm dứt chế độ thực dân phong kiến, mở ra kỷ nguyên mới độc lập tự do cho đất nước Việt Nam.",
            keyFigures = "Chủ tịch Hồ Chí Minh, Võ Nguyên Giáp, Phạm Văn Đồng, Trường Chinh",
            location = "Quảng trường Ba Đình, Hà Nội",
            isFeatured = true,
            quizQuestion = "Bản Tuyên ngôn Độc lập khai sinh ra nước Việt Nam Dân chủ Cộng hòa được đọc tại đâu vào ngày 2/9/1945?",
            quizOptionA = "Chợ Bến Thành, Sài Gòn",
            quizOptionB = "Quảng trường Ba Đình, Hà Nội",
            quizOptionC = "Kinh thành Huế",
            quizOptionD = "Chiến khu Việt Bắc",
            quizCorrectAnswer = 1,
            quizExplanation = "Bản Tuyên ngôn Độc lập được Chủ tịch Hồ Chí Minh đọc tại Quảng trường Ba Đình, Hà Nội."
        )
    )
}
