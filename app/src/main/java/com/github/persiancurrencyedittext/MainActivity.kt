package com.github.persiancurrencyedittext

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.github.currencyedittext.CurrencyEditText
import com.github.currencyedittext.CurrencyType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val persianEdt = findViewById<CurrencyEditText>(R.id.persian_edt)
        val textView = findViewById<TextView>(R.id.persian_words)

        persianEdt.persianWordsTextView(textView)
        persianEdt.setCurrencyType(CurrencyType.RIAL)
        persianEdt.getNumericValue()
        persianEdt.getPersianNumericValue()
        persianEdt.getWords()
        persianEdt.getValue()
        persianEdt.setRialToTomanConversion(false)
        persianEdt.setShowCurrencyInTextView(true)
        persianEdt.setShowCurrencyInEditText(true)
        persianEdt.setMonetaryDivider("،")
    }
}