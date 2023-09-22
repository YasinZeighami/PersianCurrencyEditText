package com.github.yasinzeighami.persiancurrencyedittext

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import java.text.DecimalFormat

class IREditText : AppCompatEditText {
    private val numberFormat = DecimalFormat("#,###")
  //  private val customTypeface: Typeface = Typeface.createFromAsset(context.assets, "font/vazir.ttf")

    constructor(context: Context) : super(context) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
    }

    private fun initialize() {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                removeTextChangedListener(this)

                val rawText = s.toString().replace(",", "")

                val formattedText = numberFormat.format(rawText.toLongOrNull() ?: 0)

                val faFormattedText = convertEnNumberToFaNumber(formattedText)

                setText(faFormattedText)
                setSelection(faFormattedText.length)
                addTextChangedListener(this)
            }
        })


    }

    private fun convertEnNumberToFaNumber(enNumber: String): String {

        return enNumber.map { enDigitToFa(it) }.joinToString("")
    }
    private fun convertFaNumberToEnNumber(faNumber: String): String {

        return faNumber.map { faDigitToEn(it) }.joinToString("")
    }

    private fun enDigitToFa(enDigit: Char): Char {
        return when (enDigit) {
            '0' -> '۰'
            '1' -> '۱'
            '2' -> '۲'
            '3' -> '۳'
            '4' -> '۴'
            '5' -> '۵'
            '6' -> '۶'
            '7' -> '۷'
            '8' -> '۸'
            '9' -> '۹'
            else -> enDigit
        }
    }
    private fun faDigitToEn(faDigit: Char): Char {
        return when (faDigit) {
            '۰' -> '0'
            '۱' -> '1'
            '۲' -> '2'
            '۳' -> '3'
            '۴' -> '4'
            '۵' -> '5'
            '۶' -> '6'
            '۷' -> '7'
            '۸' -> '8'
            '۹' -> '9'
            else -> faDigit
        }
    }



    fun getEnNumericValue(): Long {
        val convertToEnNumber = convertFaNumberToEnNumber(text.toString())
        val textWithoutCommas = convertToEnNumber.replace(",", "")
        return textWithoutCommas.toLongOrNull() ?: 0
    }
    fun getFaNumericValue(): Long {
        val convertToFaNumber = convertEnNumberToFaNumber(text.toString())
        val textWithoutCommas = convertToFaNumber.replace(",", "")
        return textWithoutCommas.toLongOrNull() ?: 0
    }
}