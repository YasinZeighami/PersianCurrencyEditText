# Persian Currency EditText

The PersianCurrencyEditText library offers a versatile solution for handling Persian currency input and display within Android applications. Designed to enhance user experience, it simplifies the management of numeric input, formatting, and conversion to Persian numerals, while seamlessly integrating with EditText and TextView components.
<br/>
<br/>
[![platform](https://img.shields.io/badge/platform-Android-yellow.svg)](https://www.android.com)
[![](https://jitpack.io/v/YasinZeighami/PersianCurrencyEditText.svg)](https://jitpack.io/#YasinZeighami/PersianCurrencyEditText)
<a href="https://www.apache.org/licenses/LICENSE-2.0.html" rel="nofollow"><img src="https://camo.githubusercontent.com/9e32ef8e3ebb18acba75349c8c435b6d9b16f074c0747cbafbb7e91f9b359966/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6c6963656e73652d417061636865253230322d3445423142412e7376673f" alt="License" data-canonical-src="https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?" style="max-width: 100%;"></a>
<br>
<br>
<img src="https://github.com/YasinZeighami/PersianCurrencyEditText/blob/master/persiancurrencyedittext.gif" width="256"/>



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
	        implementation 'com.github.YasinZeighami:PersianCurrencyEditText:1.0.3'
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

// Set currency type to Rial or Toman
persianEdt.setCurrencyType(CurrencyType.RIAL)


val numericValue = persianEdt.getNumericValue() // 2000000

val words = persianEdt.getWords() // دو میلیون ریال

val value = persianEdt.getValue() // ۲,۰۰۰,۰۰۰

persianEdt.setRialToTomanConversion(true) // (۲,۰۰۰,۰۰۰) دویست هزار تومان

// Set whether to show currency in TextView
persianEdt.setShowCurrencyInTextView(true) 

// Set whether to show currency in EditText
persianEdt.setShowCurrencyInEditText(true) 

// Set monetary divider
persianEdt.setMonetaryDivider("،")


```
## License
```License
 Copyright (c) 2024 Yasin Zeighami
   
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
   
```
 
