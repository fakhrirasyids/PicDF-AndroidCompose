package com.fakhrirasyids.picdf.ui.screen.addedit

import android.Manifest
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import com.fakhrirasyids.picdf.BuildConfig
import com.fakhrirasyids.picdf.R
import com.fakhrirasyids.picdf.ui.components.CustomAddImageButton
import com.fakhrirasyids.picdf.ui.components.CustomDialog
import com.fakhrirasyids.picdf.ui.components.ImagePreviewItem
import com.fakhrirasyids.picdf.ui.theme.primaryBlue
import com.fakhrirasyids.picdf.utils.PermissionUtils
import com.fakhrirasyids.picdf.utils.PermissionUtils.createImageFile
import io.github.farhanroy.composeawesomedialog.utils.ComposeAwesomeDialogType
import org.koin.androidx.compose.koinViewModel
import java.util.Objects

@Composable
fun AddEditScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    addEditViewModel: AddEditViewModel = koinViewModel()
) {
    val numberOfItemsByRow = LocalConfiguration.current.screenWidthDp / 200
    val context = LocalContext.current
    val file = context.createImageFile()

    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        BuildConfig.APPLICATION_ID + ".provider", file
    )

    val state = addEditViewModel.state.collectAsState()
    val imageBitmaps = state.value.imageBitmaps

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val storagePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE

    val storagePermissionResult = remember { mutableStateOf(2) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
            storagePermissionResult.value = 1
        }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            val tempUri = listOf(uri)
            addEditViewModel.onImagesSelected(tempUri, context)
        }

    val createDocumentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument(),
        onResult = {
            if (it != null) {
                addEditViewModel.writeToSelectedPath(it, context)
            }
        }
    )

    val selectMultipleImages = rememberLauncherForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) {
        addEditViewModel.onImagesSelected(it, context)
    }

    if (storagePermissionResult.value == 0) {
        CustomDialog(
            type = ComposeAwesomeDialogType.Info,
            title = "Storage Permission",
            desc = "Allow app to access files on your device",
            onDismiss = {},
            onOkClick = { galleryLauncher.launch(storagePermission) },
            onCancelClick = {},
            showCancelButton = false
        )
    }

    if (storagePermissionResult.value == 0) {
        CustomDialog(
            type = ComposeAwesomeDialogType.Info,
            title = "Camera Permission",
            desc = "Allow app to open Camera on your device",
            onDismiss = {},
            onOkClick = { cameraLauncher.launch(uri) },
            onCancelClick = {},
            showCancelButton = false
        )
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Transparent,
                actions = {
                    Button(
                        modifier = modifier
                            .width(screenWidth * 0.75f)
                            .fillMaxHeight()
                            .padding(8.dp),
                        onClick = { createDocumentLauncher.launch("ImgToPdf_${System.currentTimeMillis()}.pdf") }) {
                        Text(text = "Save PDF")
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /* FAB onClick */ },
                        containerColor = primaryBlue,
                        elevation = androidx.compose.material3.FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Default.Build, "")
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                    .background(color = primaryBlue)
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { navigateBack() }
                )

                CustomAddImageButton(
                    painter = painterResource(id = R.drawable.ic_gallery),
                    buttonText = "Select Gallery Image",
                    backgroundColor = Color.White,
                    fontColor = Color.Black,
                    modifier = modifier.padding(
                        top = 12.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    ),
                    onClick = {
                        storagePermissionResult.value =
                            PermissionUtils.checkAndRequestPermission(context, storagePermission)

                        if (storagePermissionResult.value == 1) {
                            selectMultipleImages.launch("image/jpeg")
                        }
                    }
                )

                CustomAddImageButton(
                    painter = painterResource(id = R.drawable.ic_camera),
                    buttonText = "Take Camera Image",
                    backgroundColor = Color.White,
                    fontColor = Color.Black,
                    modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    onClick = {
                        storagePermissionResult.value =
                            PermissionUtils.checkAndRequestPermission(context, storagePermission)

                        if (storagePermissionResult.value == 1) {
                            cameraLauncher.launch(uri)
                        }
                    }
                )
            }

            if (imageBitmaps.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 150.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight * 0.75f)
                        .padding(top = 8.dp, bottom = 8.dp)
                ) {
                    itemsIndexed(imageBitmaps) { index: Int, bitmap: Bitmap ->
                        Card(
                            border = BorderStroke(1.dp, Color.LightGray),
                            modifier = modifier
                                .fillMaxWidth()
                                .height(350.dp)
                                .padding(4.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent,
                            ),
                        ) {
                            Row(
                                modifier = Modifier.background(color = Color.LightGray),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Page ${index + 1} of ${imageBitmaps.size}",
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .weight(1f),
                                    fontSize = 12.sp
                                )
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Back",
                                    tint = Color.Red,
                                    modifier = Modifier
                                        .padding(6.dp)
                                        .clickable {
                                            addEditViewModel.removeImage(index)
                                        }
                                )
                            }
                            Box(
                                modifier = modifier.padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                ImagePreviewItem(
                                    bitmap = bitmap,
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(horizontal = 15.dp),
                                    onRemoveClick = {
                                        addEditViewModel.removeImage(index)
                                    }
                                )
                            }
                        }
                    }
                }
            } else Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight * 0.75f),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Select some images")
            }
        }
    }
}