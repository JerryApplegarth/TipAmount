package com.applecompose.tipamount

import android.content.ContentValues.TAG
import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.applecompose.tipamount.components.InputField
import com.applecompose.tipamount.ui.theme.newBackgroundColor
import com.applecompose.tipamount.widgets.RoundIconButton

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    onValChange: (String) -> Unit = {}
) {

    val totalBillState = remember {
        mutableStateOf("")
    }
    //Looking inside 'totalBillState' to see if something is inside
    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()

    }
    val keyboardController = LocalSoftwareKeyboardController.current



    val splitByState = remember {
        mutableStateOf(1)
    }
    val range = IntRange(start = 1, endInclusive = 100)

    val sliderPositionState = remember {
        mutableStateOf(0f)
    }
    val tipPercentage = (sliderPositionState.value * 30).toInt()

    val tipAmountSate = remember {
        mutableStateOf(0.0)
    }





    Surface(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(2.dp, color = MaterialTheme.colors.newBackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            InputField(
                valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    onValChange(totalBillState.value.trim())
                    keyboardController?.hide()
                }
            )
            Row(
                modifier = Modifier
                    .padding(3.dp),
                horizontalArrangement = Arrangement.Start
            )
            {
                Text(
                    text = "Split",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(120.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 3.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    RoundIconButton(
                        imageVector = Icons.Default.Remove,
                        onClick = {
                            splitByState.value =
                                if (splitByState.value > 1) splitByState.value - 1
                                else 1

                        })
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = splitByState.value.toString(),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 9.dp, end = 9.dp)
                    )

                    RoundIconButton(
                        imageVector = Icons.Default.Add,
                        onClick = {
                            if (splitByState.value < range.last) {
                                splitByState.value = splitByState.value + 1

                            } else 1
                        }
                    )

                }


            }
            Spacer(modifier = Modifier.height(8.dp))
            // Tip Row
            Row(
                modifier = Modifier
                    .padding(horizontal = 3.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Tip",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(180.dp))
                Text(
                    text = "$ ${tipAmountSate.value}",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                )

            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$tipPercentage %"
                )
                Spacer(modifier = Modifier.height(14.dp))
                Slider(
                    value = sliderPositionState.value,
                    onValueChange = { newVal ->
                        sliderPositionState.value = newVal
                        tipAmountSate.value =
                            calculateTotalTip(totalBill = totalBillState.value.toDouble(),
                                tipPercentage = tipPercentage)

                    },
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp),
                    steps = 10,


                    )
            }
        }
    }

}

fun calculateTotalTip(totalBill: Double, tipPercentage: Int): Double {
    return if (totalBill > 1 && totalBill.toString().isNotEmpty())
        (totalBill * tipPercentage) / 100 else 0.0

}
