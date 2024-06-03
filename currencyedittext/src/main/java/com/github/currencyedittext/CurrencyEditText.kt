package com.github.currencyedittext

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import java.math.BigInteger
import java.text.DecimalFormat


/**
 * Custom EditText class to handle Persian numeric input and display.
 */
class CurrencyEditText : AppCompatEditText {

    // Formatter to add thousands separator to numbers
    private val numberFormat = DecimalFormat("#,###")
    private var persianWordsTextView: TextView? = null
    private var currencyType: CurrencyType? = CurrencyType.RIAL
    private var monetaryDivider: String? = ","
    private var convertRial: Boolean? = true
    private var showCurrencyInTextView: Boolean? = true
    private var showCurrencyInEditText: Boolean? = false

    constructor(context: Context) : super(context) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initialize()
    }

    /**
     * Initializes the PersianCurrencyEditText by setting its input type and adding a TextWatcher to handle text changes.
     * The input type is set to allow only numeric input.
     * Text changes are monitored to format the entered text as Persian numeric format, display Persian words,
     * and optionally display currency symbols in the EditText.
     */
    private fun initialize() {
        inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED

        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                removeTextChangedListener(this)


                // Remove commas from the raw text
                val rawText = s.toString().replace(",", "")

                // Format the numeric value and replace commas with the specified monetary divider
                val formattedText =
                    numberFormat.format(getNumericValue().toString().toLongOrNull() ?: 0)
                        .replace(",", monetaryDivider ?: ",")

                // Convert the formatted text to Persian numbers
                val faFormattedText = if (formattedText != "0") {
                    convertEnNumberToPersianNumber(formattedText)
                } else {
                    "0"
                }

                // Convert raw text to Persian words
                val persianWords =
                    if (rawText.isNotBlank()) convertToPersianText(getNumericValue().toBigInteger()) else ""

                // Determine whether to show currency in the EditText
                if (showCurrencyInEditText == true) {
                    val currencyAndFormat = " ${currencyType?.symbol}$faFormattedText"

                    // Update EditText with currency and formatted text
                    if (faFormattedText == "0") {
                        setText("")
                        setSelection(0)
                    } else {
                        setText("$faFormattedText ${currencyType?.symbol}")
                        setSelection(currencyAndFormat.length - currencyType?.symbol?.length!!-1)
                    }
                } else {
                    // Update EditText with only the formatted text
                    if (faFormattedText == "0") {
                        setText("")
                        setSelection(0)

                    } else {
                        setText(faFormattedText)
                        setSelection(faFormattedText.length)
                    }
                }

                // Update the Persian words TextView
                persianWordsTextView?.text = persianWords

                addTextChangedListener(this)
                if (text?.isEmpty() == true) persianWordsTextView?.text = ""

            }
        })
    }


    /**
     * Convert English numerals to Persian numerals.
     */
    private fun convertEnNumberToPersianNumber(enNumber: String): String {
        return enNumber.map { enDigitToFa(it) }.joinToString("")
    }

    /**
     * Convert Persian numerals to English numerals.
     */
    private fun convertPersianNumberToEnNumber(faNumber: String): String {
        return faNumber.map { faDigitToEn(it) }.joinToString("")
    }

    /**
     * Convert English digit to Persian digit.
     */
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

    /**
     * Convert Persian digit to English digit.
     */
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

    /**
     * Get the numeric value as a Long.
     */
    /**
     * Retrieves the numeric value from the text of the EditText.
     * This function removes non-numeric characters (except commas) from the text,
     * then converts the text to a Long value.
     * If the conversion fails, it returns 0.
     *
     * @return The numeric value extracted from the text of the EditText.
     */
    fun getNumericValue(): Long {
        // Convert Persian numerals to English numerals
        val convertToEnNumber = convertPersianNumberToEnNumber(text.toString())

        // Remove commas from the converted text
        val textWithoutCommas = convertToEnNumber.replace(Regex(","), "")

        // Remove non-numeric characters from the text
        val numericText = textWithoutCommas.replace(Regex("[^0-9]"), "")

        // Convert the cleaned numeric text to a Long value, or return 0 if conversion fails
        return numericText.toLongOrNull() ?: 0
    }

    /**
     * Get the numeric value as a Persian formatted String.
     */
    /**
     * Retrieves the Persian numeric value from the text of the EditText and converts it to a Persian numeral string.
     * This function converts the numeric value obtained from [getNumericValue] to a Persian numeral string using [convertEnNumberToPersianNumber].
     *
     * @return The Persian numeral string representing the numeric value extracted from the text of the EditText.
     */
    fun getPersianNumericValue(): String {
        // Convert the numeric value to Persian numerals
        val convertToFaNumber = convertEnNumberToPersianNumber(getNumericValue().toString())
        return convertToFaNumber
    }


    /**
     * Retrieves the Persian numeral representation of the text.
     * This function converts the text to Persian numerals using [convertEnNumberToPersianNumber].
     *
     * @return The Persian numeral representation of the text.
     */
    fun getValue(): String {
        val convertToFaNumber = convertEnNumberToPersianNumber(text.toString())
        return convertToFaNumber
    }


    /**
     * Retrieves the Persian words representation of the numeric value extracted from the EditText.
     * This function obtains the numeric value from the EditText using [getNumericValue] and converts it to
     * Persian words using [convertToPersianText].
     *
     * @return The Persian words representation of the numeric value extracted from the EditText.
     */
    fun getWords(): String {
        return convertToPersianText(getNumericValue().toBigInteger())
    }


    /**
     * Sets the TextView used to display Persian letters.
     * This function assigns the provided TextView to the [persianWordsTextView] property.
     *
     * @param textView The TextView to be set as the Persian words display.
     */
    fun persianWordsTextView(textView: TextView) {
        persianWordsTextView = textView
    }


    /**
     * Sets the currency type for the PersianCurrencyEditText.
     *
     * @param currencyType The currency type enum (CurrencyType.RIAL for Iranian Rial, CurrencyType.TOMAN for Iranian Toman).
     */
    fun setCurrencyType(currencyType: CurrencyType) {
        this.currencyType = currencyType
    }


    /**
     * Sets the conversion from Rials to Tomans enabled status.
     * This function toggles the status of the conversion from Rials to Tomans
     * by updating the [convertRial] property accordingly.
     *
     * @param enabled A boolean value indicating whether the conversion from Rials to Tomans is enabled (true) or disabled (false).
     */
    fun setRialToTomanConversion(enabled: Boolean) {
        convertRial = enabled
    }

    fun setShowCurrencyInTextView(enabled: Boolean) {
        showCurrencyInTextView = enabled
    }

    fun setShowCurrencyInEditText(enabled: Boolean) {
        showCurrencyInEditText = enabled
    }


    /**
     * Converts the given numeric value to a Persian text representation.
     *
     * @param number The numeric value to be converted.
     * @return The Persian text representation of the numeric value with the specified currency.
     */
    private fun convertToPersianText(number: BigInteger): String {
        // Adjust the number based on conversion settings
        val adjustedNumber = if (convertRial == true && currencyType == CurrencyType.TOMAN) {
            // Remove the last digit from the number
            number / BigInteger.TEN
        } else {
            number
        }

        // Determine the currency text based on the specified currency
        val currencyText = if (showCurrencyInTextView == true) {
            currencyType?.symbol
        } else {
            ""
        }

        // Return zero if the adjusted number is zero
        if (adjustedNumber == BigInteger.ZERO) return " صفر $currencyText"

        // Build the Persian text representation of the numeric value
        val result = StringBuilder()
        var remainder = adjustedNumber
        var digitGroup = 0
        while (remainder > BigInteger.ZERO) {
            val threeDigitNumber = remainder % BigInteger.valueOf(1000)
            remainder /= BigInteger.valueOf(1000)

            if (threeDigitNumber > BigInteger.ZERO) {
                val threeDigitText =
                    convertThreeDigitsToText(
                        threeDigitNumber,
                        PersianNumerals.units,
                        PersianNumerals.tens,
                        PersianNumerals.hundreds
                    )
                val thousandText = PersianNumerals.thousands[digitGroup]
                if (result.isNotEmpty()) result.insert(0, " و ")
                result.insert(0, "$threeDigitText $thousandText")
            }

            digitGroup++
        }
        Log.i("MyTag", getNumericValue().toInt().toString())

        // Concatenate the Persian text representation with the currency text
        return "$result $currencyText"
    }


    /**
     * Converts a three-digit number to its Persian text representation.
     * This function takes a three-digit number and converts it to its Persian text representation
     * using the provided arrays for units, tens, and hundreds.
     *
     * @param number The three-digit number to be converted.
     * @param units An array containing Persian text representations of units (0-9).
     * @param tens An array containing Persian text representations of tens (0-9, 10-90).
     * @param hundreds An array containing Persian text representations of hundreds (100-900).
     * @return The Persian text representation of the three-digit number.
     */
    private fun convertThreeDigitsToText(
        number: BigInteger,
        units: Array<String>,
        tens: Array<String>,
        hundreds: Array<String>
    ): String {
        val result = StringBuilder()

        val hundredsDigit = number / BigInteger.valueOf(100)
        val tensDigit = (number % BigInteger.valueOf(100)) / BigInteger.TEN
        val unitsDigit = number % BigInteger.TEN

        if (hundredsDigit > BigInteger.ZERO) {
            result.append(hundreds[hundredsDigit.toInt()])
            if (tensDigit > BigInteger.ZERO || unitsDigit > BigInteger.ZERO) {
                result.append(" و ")
            }
        }

        if (tensDigit > BigInteger.ZERO) {
            if (tensDigit == BigInteger.ONE) {
                // Handle numbers between 10 and 19 directly
                result.append(units[number.toInt() % 100])
            } else {
                result.append(tens[tensDigit.toInt()])
                if (unitsDigit > BigInteger.ZERO) {
                    result.append(" و ")
                }
            }
        }

        if (unitsDigit > BigInteger.ZERO && tensDigit != BigInteger.ONE) {
            result.append(units[unitsDigit.toInt()])
        }

        return result.toString()
    }



    /**
     * Sets the divider used for formatting monetary values in the PersianCurrencyEditText.
     * The divider string is used to replace commas in the formatted text.
     *
     * @param divider The string used as the divider in the formatted monetary values.
     */
    fun setMonetaryDivider(divider: String) {
        monetaryDivider = divider
    }


    /**
     * Retrieves the Persian currency symbol based on the specified currency type.
     *
     * @param currencyType The currency type enum (CurrencyType.RIAL for Iranian Rial, CurrencyType.TOMAN for Iranian Toman).
     * @return The Persian currency symbol corresponding to the specified currency type.
     */
    private fun getPersianCurrency(currencyType: CurrencyType): String {
        return currencyType.symbol
    }


}


