package ir.shervin.messenger.pages

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.ClosedCaption
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material.icons.rounded.TagFaces
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import ir.shervin.messenger.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatPage(navController: NavHostController) {
	var message by remember { mutableStateOf("") }
	val messageList = remember {
		mutableStateListOf<Any>(
			"Hello",
			"How are you?"
		)
	}
	val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
		if (it != null) {
			messageList.add(it)
		}
	}
	var fullscreen by remember { mutableStateOf(false) }
	var viewImage by remember { mutableStateOf(null as Uri?) }
	
	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					Row(
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.SpaceBetween,
						modifier = Modifier.fillMaxWidth()
					) {
						Text("Online", color = Color(0xFFE68415))
						Image(painterResource(R.drawable.sohrab), null, modifier = Modifier.clip(CircleShape))
						Text("Sohrab")
					}
				},
				navigationIcon = {
					IconButton(onClick = { navController.popBackStack() }) {
						Icon(Icons.AutoMirrored.Rounded.ArrowBack, null)
					}
				},
				actions = {
					IconButton(onClick = {}) {
						Icon(Icons.Rounded.Search, null)
					}
				}
			)
		}
	) { contentPadding ->
		Column(
			modifier = Modifier
				.padding(contentPadding)
				.fillMaxSize()
				.imePadding()
		) {
			LazyColumn(
				modifier = Modifier.weight(1f),
				reverseLayout = true,
				contentPadding = PaddingValues(8.dp),
			) {
				items(messageList.asReversed()) {
					when (it) {
						is String -> {
							Text(
								text = it,
								modifier = Modifier
									.padding(top = 8.dp)
									.clip(RoundedCornerShape(100.dp))
									.background(MaterialTheme.colorScheme.tertiaryContainer)
									.padding(8.dp),
								fontSize = 18.sp
							)
						}
						
						is Uri -> {
							Image(
								painter = rememberAsyncImagePainter(it),
								contentDescription = null,
								contentScale = ContentScale.Crop,
								modifier = Modifier
									.clickable {
										viewImage = it
										fullscreen = true
									}
									.size(250.dp)
									.padding(top = 8.dp)
									.clip(RoundedCornerShape(20.dp))
									.background(MaterialTheme.colorScheme.tertiaryContainer)
									.padding(4.dp)
									.clip(RoundedCornerShape(20.dp))
							)
						}
					}
				}
			}
			
			Row(
				modifier = Modifier
					.background(MaterialTheme.colorScheme.surfaceVariant)
					.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically
			) {
				IconButton(onClick = {
					launcher.launch("image/*")
				}) {
					Icon(Icons.Rounded.TagFaces, null)
				}
				
				BasicTextField(
					value = message,
					onValueChange = { message = it },
					modifier = Modifier.weight(1f),
					textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 20.sp),
					cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurfaceVariant)
				)
				
				IconButton(onClick = {
					messageList.add(message)
					message = ""
				}) {
					Icon(Icons.AutoMirrored.Rounded.Send, null)
				}
			}
		}
		
		if (fullscreen) Dialog(
			onDismissRequest = { fullscreen = false },
			properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = false),
		) {
			Box(
				modifier = Modifier
					.fillMaxSize()
					.background(Color.Black.copy(alpha = 0.5f))
			) {
				Image(
					painter = rememberAsyncImagePainter(viewImage),
					contentDescription = null,
					modifier = Modifier.fillMaxSize(),
					contentScale = ContentScale.Fit
				)
				
				IconButton(
					modifier = Modifier
						.offset(16.dp, 16.dp)
						.align(Alignment.TopStart),
					onClick = { fullscreen = false }
				) {
					Icon(Icons.Rounded.Close, null)
				}
			}
		}
	}
}
