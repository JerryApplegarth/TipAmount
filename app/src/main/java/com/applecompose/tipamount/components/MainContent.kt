package com.applecompose.tipamount.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.applecompose.tipamount.BillForm


@Preview
@Composable
fun MainContent() {
    BillForm(){billAmt ->
        Log.d("BILL", "MainContent: $billAmt")

    }

}