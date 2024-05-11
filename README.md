# Persian Currency EditText
An Android Library for Handling Persian Currency Input and Conversion

The PersianCurrencyEditText library provides developers with a convenient solution for integrating Persian currency input and conversion functionalities into their Android applications. With this library, developers can easily incorporate EditText fields tailored for Persian currency input, complete with support for displaying numeric values in Persian words and customizable currency units. Additionally, the library offers built-in capabilities for converting currency values between Rials and Tomans, enabling seamless handling of financial data in Persian language contexts.
<br/>
<br/>
[![platform](https://img.shields.io/badge/platform-Android-yellow.svg)](https://www.android.com)
[![](https://jitpack.io/v/YasinZeighami/PersianCurrencyEditText.svg)](https://jitpack.io/#YasinZeighami/PersianCurrencyEditText)

# Install
## Gradle
Step 1.Add it in your root build.gradle at the end of repositories:

	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.YasinZeighami:PersianCurrencyEditText:1.0.0'
	}

 ## How do I use PersianCurrencyEditText?
 ## Step 1
 In your layout XML file, include the CurrencyEditText component where you want to use the Persian Currency EditText:
  ```xml
 <com.github.currencyedittext.CurrencyEditText
            android:gravity="center"
            android:id="@+id/persian_edt"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
```
 ## Step 2
 After including the CurrencyEditText component in your layout, you can interact with it programmatically in your Kotlin/Java code:
```kotlin
 // Find views by their IDs
val persianEdt = findViewById<CurrencyEditText>(R.id.persian_edt)
val textView = findViewById<TextView>(R.id.txt)

// Set TextView for displaying Persian words
persianEdt.persianWordsTextView(textView)

// Set currency type to Rial
persianEdt.setCurrencyType(CurrencyType.RIAL)

// Get numeric value
val numericValue = persianEdt.getNumericValue()

// Get numeric value in Persian format
val persianNumericValue = persianEdt.getPersianNumericValue()

// Get words
val words = persianEdt.getWords()

// Get value
val value = persianEdt.getValue()

// Set conversion of Rial to Toman
persianEdt.setRialToTomanConversion(false)

// Set whether to show currency in TextView
persianEdt.setShowCurrencyInTextView(true)

// Set whether to show currency in EditText
persianEdt.setShowCurrencyInEditText(true)

// Set monetary divider
persianEdt.setMonetaryDivider("،")


```
 
