package com.applecompose.tipamount.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.applecompose.tipamount.presentation.components.BillForm
import com.applecompose.tipamount.presentation.components.AppLogo

@Composable
fun HomeScreen() {

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

		BillForm(
			splitByState = splitByState,
			range = range,
			tipAmountSate = tipAmountSate,
			totalPerPersonState = totalPerPersonState
		)

		AppLogo()


	}

}
