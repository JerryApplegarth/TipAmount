package com.applecompose.tipamount


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
import androidx.compose.ui.unit.dp
import com.applecompose.tipamount.components.InputField
import com.applecompose.tipamount.components.TopHeader
import com.applecompose.tipamount.components.TopImage
import com.applecompose.tipamount.ui.theme.newBackgroundColor
import com.applecompose.tipamount.utils.calculateTotalPerPerson
import com.applecompose.tipamount.utils.calculateTotalTip
import com.applecompose.tipamount.widgets.RoundIconButton
import org.w3c.dom.Text


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    range: IntRange = 1..30,
    splitByState: MutableState<Int>,
    tipAmountSate: MutableState<Double>,
    totalPerPersonState: MutableState<Double>,
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
    val sliderPositionState = remember {
        mutableStateOf(0f)
    }
    val tipPercentage = (sliderPositionState.value * 30).toInt()

    TopImage()
    TopHeader(totalPerPerson = totalPerPersonState.value)




    Surface(
        modifier = modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(2.dp, color = MaterialTheme.colors.newBackgroundColor)
    ) {
        Column(
            modifier
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
            if (validState) {
                Row(
                    modifier = modifier
                        .padding(3.dp),
                    horizontalArrangement = Arrangement.Start
                )
                {
                    Text(
                        text = "Split",
                        modifier
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier
                        .width(120.dp))
                    //Split row
                    Row(
                        modifier
                            .padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        RoundIconButton(
                            imageVector = Icons.Default.Remove,
                            onClick = {
                                splitByState.value =
                                    if (splitByState.value > 1) splitByState.value - 1
                                    else 1
                                totalPerPersonState.value =
                                    calculateTotalPerPerson(
                                        totalBill = totalBillState.value.toDouble(),
                                        splitBy = splitByState.value,
                                        tipPercentage = tipPercentage
                                    )
                            })
                        Spacer(modifier.width(4.dp))
                        Text(
                            text = splitByState.value.toString(),
                            modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 12.dp, end = 12.dp)
                        )
                        RoundIconButton(
                            imageVector = Icons.Default.Add,
                            onClick = {
                                if (splitByState.value < range.last) {
                                    splitByState.value = splitByState.value + 1
                                } else 1
                                totalPerPersonState.value =
                                    calculateTotalPerPerson(
                                        totalBill = totalBillState.value.toDouble(),
                                        splitBy = splitByState.value,
                                        tipPercentage = tipPercentage
                                    )
                            }
                        )
                    }
                }
                Spacer(modifier.height(8.dp))
                // Tip Row
                Row(
                    modifier
                        .padding(horizontal = 3.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Tip",
                        modifier
                            .align(alignment = Alignment.CenterVertically)
                    )
                    Spacer(modifier.width(180.dp))
                    //val tipAmountSateRounded: Double = String.format("%.2f", tipAmountSate.value).toDouble()
                            Text(
                        text = "$ ${tipAmountSate.value}",
                        modifier
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
                    Spacer(modifier.height(12.dp))
                    Slider(
                        value = sliderPositionState.value,
                        onValueChange = { newVal ->
                            sliderPositionState.value = newVal
                            tipAmountSate.value =
                                calculateTotalTip(
                                    totalBill = totalBillState.value.toDouble(),
                                    tipPercentage = tipPercentage
                                )
                            totalPerPersonState.value =
                                calculateTotalPerPerson(
                                    totalBill = totalBillState.value.toDouble(),
                                    splitBy = splitByState.value,
                                    tipPercentage = tipPercentage
                                )
                        },
                        modifier = modifier
                            .padding(start = 16.dp, end = 16.dp),
                        steps = 0,
                    )
                }
            } else {
                Box() {
                }
            }
        }
    }

}




