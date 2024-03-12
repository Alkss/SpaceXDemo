package com.mindera.rocketscience.featue_demo.presentation.home.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mindera.rocketscience.R
import com.mindera.rocketscience.featue_demo.util.DateTransformation
import com.mindera.rocketscience.featue_demo.util.toDateFormatt

@Composable
fun FilterComposable(
    modifier: Modifier = Modifier,
    onDismissRequest: (() -> Unit)? = null,
    onConfirmAction: (startDate: String, endDate: String, isSuccessful: Boolean, isAscending: Boolean) -> Unit
) {
    var startDate by remember {
        mutableStateOf("")
    }
    var endDate by remember {
        mutableStateOf("")
    }
    var isSuccessful by remember {
        mutableStateOf(false)
    }
    var isAscending by remember {
        mutableStateOf(false)
    }

    Dialog(onDismissRequest = { onDismissRequest?.invoke() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Column(
                        Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = startDate,
                            onValueChange = {
                                if (it.length <= 8)
                                    startDate = it
                            },
                            visualTransformation = DateTransformation(),
                            label = { Text(text = "Start Date") }
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Column(
                        Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = endDate,
                            onValueChange = {
                                if (it.length <= 8)
                                    endDate = it
                            },
                            visualTransformation = DateTransformation(),
                            label = { Text(text = "End Date") }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))

                Text(text = "*Date format must be YYYY-MM-DD", fontSize = 10.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(24.dp))

                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = isSuccessful, onCheckedChange = { isSuccessful = it })
                    Text(text = "Only Successful launches")
                }

                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Switch(
                        checked = isAscending,
                        onCheckedChange = { isAscending = it },
                        modifier = Modifier.scale(0.6f)
                    )
                    Text(text = "Filter by Ascending Order")
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    TextButton(
                        onClick = {
                            onConfirmAction(
                                startDate.toDateFormatt(),
                                endDate.toDateFormatt(),
                                isSuccessful,
                                isAscending
                            )
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(stringResource(id = R.string.filter_confirm_button))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun FilterComposablePreview() {
    FilterComposable() { _, _, _, _ ->

    }
}
