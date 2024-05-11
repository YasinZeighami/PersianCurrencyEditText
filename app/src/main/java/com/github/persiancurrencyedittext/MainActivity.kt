package com.github.persiancurrencyedittext

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.currencyedittext.CurrencyEditText
import com.github.currencyedittext.CurrencyType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val persianEdt = findViewById<CurrencyEditText>(R.id.persian_edt)
        val txt = findViewById<TextView>(R.id.persian_words)

        persianEdt.persianWordsTextView(txt)
        persianEdt.setCurrencyType(CurrencyType.TOMAN)
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