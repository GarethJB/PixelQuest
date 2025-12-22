package com.jb.pixelquest.shared.domain.model.common

/**
 * 색상을 나타내는 도메인 모델
 * UI 의존성 없이 순수 Kotlin으로 정의
 * ARGB 형식으로 저장 (Alpha, Red, Green, Blue)
 */
data class Color(
    val argb: Long
) {
    val alpha: Int get() = ((argb shr 24) and 0xFF).toInt()
    val red: Int get() = ((argb shr 16) and 0xFF).toInt()
    val green: Int get() = ((argb shr 8) and 0xFF).toInt()
    val blue: Int get() = (argb and 0xFF).toInt()

    constructor(alpha: Int, red: Int, green: Int, blue: Int) : this(
        ((alpha and 0xFF).toLong() shl 24) or
        ((red and 0xFF).toLong() shl 16) or
        ((green and 0xFF).toLong() shl 8) or
        ((blue and 0xFF).toLong())
    )

    constructor(red: Int, green: Int, blue: Int) : this(255, red, green, blue)

    /**
     * Hex 문자열로 변환 (예: #FF0000)
     */
    fun toHexString(): String {
        return "#%02X%02X%02X%02X".format(alpha, red, green, blue)
    }

    companion object {
        /**
         * Hex 문자열에서 Color 생성 (예: #FF0000 또는 #FF000000)
         */
        fun fromHex(hex: String): Color {
            val cleanHex = hex.removePrefix("#")
            return when (cleanHex.length) {
                6 -> {
                    val r = cleanHex.substring(0, 2).toInt(16)
                    val g = cleanHex.substring(2, 4).toInt(16)
                    val b = cleanHex.substring(4, 6).toInt(16)
                    Color(r, g, b)
                }
                8 -> {
                    val a = cleanHex.substring(0, 2).toInt(16)
                    val r = cleanHex.substring(2, 4).toInt(16)
                    val g = cleanHex.substring(4, 6).toInt(16)
                    val b = cleanHex.substring(6, 8).toInt(16)
                    Color(a, r, g, b)
                }
                else -> throw IllegalArgumentException("Invalid hex color: $hex")
            }
        }
    }
}

