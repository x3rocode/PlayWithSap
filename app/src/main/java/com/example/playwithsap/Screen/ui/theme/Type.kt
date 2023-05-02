package com.example.playwithsap.Screen.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.playwithsap.R

// Set of Material typography styles to start with

val yoonkr = FontFamily(
        Font(R.font.yoon310, FontWeight.Thin, FontStyle.Normal),
        Font(R.font.yoon320, FontWeight.Light, FontStyle.Normal),
        Font(R.font.yoon330, FontWeight.Normal, FontStyle.Normal),
        Font(R.font.yoon340, FontWeight.Medium, FontStyle.Normal),
        Font(R.font.yoon350, FontWeight.Bold, FontStyle.Normal)
)

val Typography = Typography(
        body1 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        ),
        h3 = TextStyle(
                fontFamily = yoonkr,
                fontWeight = FontWeight.Medium,
                fontSize = 30.sp,
                color = Color.White
        ),
        h5 = TextStyle(
                fontFamily = yoonkr,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                color = Color.White
        )
        /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)