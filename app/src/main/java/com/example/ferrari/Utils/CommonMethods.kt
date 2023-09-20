package com.example.ferrari.Utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService

class CommonMethods {
    companion object{
        fun customColorToHex(customColor: String): String {
            // Remove any unwanted characters from the input string
            val cleanedColor = customColor.replace("[^a-fA-F0-9]".toRegex(), "")

            // Ensure that the cleanedColor has exactly 6 characters (RGB format)
            val hexColor = if (cleanedColor.length == 6) cleanedColor else "000000"

            // Prepend a '#' to the hexadecimal color code
            return "#$hexColor"
        }
    }
     /*fun hideKeyboard(view: View,context: Context) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }*/

}