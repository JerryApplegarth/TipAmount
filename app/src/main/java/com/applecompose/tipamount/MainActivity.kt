package com.applecompose.tipamount

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.applecompose.tipamount.components.AppLogo
import com.applecompose.tipamount.components.InputField
import com.applecompose.tipamount.components.TopHeader
import com.applecompose.tipamount.components.TopImage
import com.applecompose.tipamount.ui.theme.TipAmountTheme
import com.applecompose.tipamount.ui.theme.newBackgroundColor
import com.applecompose.tipamount.utils.calculateTotalPerPerson
import com.applecompose.tipamount.utils.calculateTotalTip
import com.applecompose.tipamount.widgets.RoundIconButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipAmountTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {

    val totalPerPersonState = remember {
        mutableStateOf(0.0)
    }
    val range = IntRange(start = 1, endInclusive = 30)
    val tipAmountSate = remember {
        mutableStateOf(0.0)
    }
    val splitByState = remember {
        mutableStateOf(1)
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopImage()
        TopHeader(totalPerPerson = totalPerPersonState.value)
        BillForm(
            splitByState = splitByState,
            range = range,
            tipAmountSate = tipAmountSate,
            totalPerPersonState = totalPerPersonState
        )
        AppLogo()


    }

}


@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    TipAmountTheme {
        MainScreen()

    }
}