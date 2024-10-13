package dev.hyunec.squirrelvault.coredomain.model

/**
 * 과금 정보 (도토리)
 *
 * @property type 과금 구분
 * @property subType 과금 세부 구분. NONE 은 해당 사항 없음을 의미
 */
data class Acorn(
    val source: Source,

    val type: Type,
    val subType: SubType,

    val memo: String
) {
    enum class Type {
        MODEL_CALL,
        DOCUMENT,
        NOTIFICATION,
    }

    enum class SubType {
        // MODEL_CALL
        CHAT,
        FUNCTION_CALL,
        TEXT_GENERATION,
        IMAGE_GENERATION,
        TRANSLATION,
        SUMMARIZATION,
        QUESTION_ANSWERING,
        SENTIMENT_ANALYSIS,
        DATA_EXTRACTION,
        CUSTOM_MODEL,

        // DOCUMENT
        SUMMARY,
        PDF_CONVERSION,
        TEXT_EXTRACTION,
        CLASSIFICATION,
        OCR_PROCESSING,

        // NOTIFICATION
        KAKAOTALK,
        EMAIL,
        SLACK,
        SMS,

        // NOT APPLICABLE
        NONE
    }
}
