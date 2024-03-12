package com.mindera.rocketscience.featue_demo.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return dateFilter(text)
    }
}

fun dateFilter(text: AnnotatedString): TransformedText {
    val trimmed = if (text.text.length >= 10) text.text.substring(0..9) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i == 3 || i == 5) out += "-"
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 5) return offset + 1
            if (offset <= 10) return offset + 2
            return 12
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 7) return offset - 1
            if (offset <= 12) return offset - 2
            return 10
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}

fun String.toDateFormatt(): String {
    return if (this.length == 8) {
        val year = this.substring(0..3)
        val month = this.substring(4..5)
        val day = this.substring(6..7)
        LocalDate.parse("$year-$month-$day", DateTimeFormatter.ISO_DATE).toString()
    } else {
        ""
    }
}

