package com.fakhrirasyids.picdf.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import io.github.farhanroy.composeawesomedialog.ErrorHeader
import io.github.farhanroy.composeawesomedialog.InfoHeader
import io.github.farhanroy.composeawesomedialog.SuccessHeader
import io.github.farhanroy.composeawesomedialog.utils.ComposeAwesomeDialogType

@Composable
fun CustomDialog(
    type: ComposeAwesomeDialogType,
    title: String,
    desc: String,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onCancelClick: () -> Unit,
    showCancelButton: Boolean = false
) {
    MaterialTheme {
        when (type) {
            ComposeAwesomeDialogType.Success -> {
                SuccessDialog(
                    title = title,
                    desc = desc,
                    onDismiss = onDismiss,
                    onOkClick = onOkClick,
                    onCancelClick = onCancelClick
                )
            }

            ComposeAwesomeDialogType.Error -> {
                ErrorDialog(
                    title = title,
                    desc = desc,
                    onDismiss = onDismiss,
                    onOkClick = onOkClick,
                    onCancelClick = onCancelClick
                )
            }

            ComposeAwesomeDialogType.Info -> {
                InfoDialog(
                    title = title,
                    desc = desc,
                    onDismiss = onDismiss,
                    onOkClick = onOkClick,
                    onCancelClick = onCancelClick,
                    showCancelButton = showCancelButton
                )
            }
        }

    }
}

@Composable
fun SuccessDialog(
    title: String = "",
    desc: String = "",
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            Modifier
                .width(300.dp)
                .height(400.dp)
        ) {
            Column(
                Modifier
                    .width(300.dp)
                    .height(300.dp)
            ) {
                Spacer(Modifier.height(36.dp))
                Box(
                    Modifier
                        .width(300.dp)
                        .height(164.dp)
                        .background(color = Color.White, shape = ShapeDefaults.Large)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            title.uppercase(),
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(desc, style = TextStyle(fontSize = 14.sp))
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            CancelButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                onCancelClick = onCancelClick
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            OkButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                onOkClick = onOkClick
                            )
                        }
                    }
                }
            }
            SuccessHeader(
                Modifier
                    .size(72.dp)
                    .align(Alignment.TopCenter)
                    .border(
                        border = BorderStroke(width = 5.dp, color = Color.White),
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun ErrorDialog(
    title: String = "",
    desc: String = "",
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            Modifier
                .width(300.dp)
                .height(300.dp)
        ) {
            Column(
                Modifier
                    .width(300.dp)
                    .height(300.dp)
            ) {
                Spacer(Modifier.height(36.dp))
                Box(
                    Modifier
                        .width(300.dp)
                        .height(164.dp)
                        .background(color = Color.White, shape = ShapeDefaults.Large)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            title.uppercase(),
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(desc, style = TextStyle(fontSize = 14.sp))
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OkButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                onOkClick = onOkClick
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            CancelButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                onCancelClick = onCancelClick
                            )
                        }
                    }
                }
            }
            ErrorHeader(
                Modifier
                    .size(72.dp)
                    .align(Alignment.TopCenter)
                    .border(
                        border = BorderStroke(width = 5.dp, color = Color.White),
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun InfoDialog(
    title: String = "",
    desc: String = "",
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onCancelClick: () -> Unit,
    showCancelButton: Boolean = false
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            Modifier
                .width(300.dp)
                .height(300.dp)
        ) {
            Column(
                Modifier
                    .width(300.dp)
                    .height(300.dp)
            ) {
                Spacer(Modifier.height(36.dp))
                Box(
                    Modifier
                        .width(300.dp)
                        .height(164.dp)
                        .background(color = Color.White, shape = ShapeDefaults.Large)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            title.uppercase(),
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(desc, style = TextStyle(fontSize = 14.sp))
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OkButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                onOkClick = onOkClick
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            if (showCancelButton) {
                                CancelButton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f),
                                    onCancelClick = onCancelClick
                                )
                            }
                        }
                    }
                }
            }
            InfoHeader(
                Modifier
                    .size(72.dp)
                    .align(Alignment.TopCenter)
                    .border(
                        border = BorderStroke(width = 4.dp, color = Color.White),
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun OkButton(modifier: Modifier = Modifier, onOkClick: () -> Unit) {
    Button(
        onClick = { onOkClick() },
        shape = ShapeDefaults.Large,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF02CB6F)),
        modifier = modifier
    ) {
        Text("Ok", style = TextStyle(color = Color.White))
    }
}

@Composable
fun CancelButton(modifier: Modifier = Modifier, onCancelClick: () -> Unit) {
    Button(
        onClick = { onCancelClick() },
        shape = ShapeDefaults.Large,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEE4435)),
        modifier = modifier
    ) {
        Text("Cancel", style = TextStyle(color = Color.White))
    }
}
