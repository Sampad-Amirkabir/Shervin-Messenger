package ir.shervin.messenger.pages

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.automirrored.rounded.Message
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.ClosedCaption
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material.icons.rounded.TagFaces
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import ir.shervin.messenger.R
import ir.shervin.messenger.applyIf

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
	var isExpanded by remember { mutableStateOf(false) }
	
	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					Row(
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.SpaceBetween,
						modifier = Modifier
							.fillMaxWidth()
							.applyIf(!isExpanded) {
								clickable {
									isExpanded = true
								}
							}
					) {
						Text("Online", color = Color(0xFFE68415))
						Image(
							painterResource(R.drawable.sohrab),
							null,
							modifier = Modifier
								.clip(CircleShape)
						)
						Text("Sohrab")
					}
				},
				navigationIcon = {
					IconButton(onClick = {
						if (isExpanded) {
							isExpanded = false
						} else {
							navController.popBackStack()
						}
					}) {
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
		
		if (isExpanded) Popup {
			var x by remember { mutableStateOf(false) }
			val state = remember { MutableTransitionState(x) }
			val transition = rememberTransition(state)
			val imageSize by transition.animateDp { if (it) 400.dp else 66.dp }
			val fillet by transition.animateDp { if (it) 0.dp else 200.dp }
			val offset by transition.animateDp { if (it) 0.dp else (-3).dp }
			val offset2 by transition.animateDp { if (it) 0.dp else 500.dp }
			val scale by transition.animateFloat { if (it) 1f else 0f }
			var isShrinking by remember { mutableStateOf(false) }
			
			BackHandler {
				state.targetState = false
				isShrinking = true
			}
			
			BoxWithConstraints(
				modifier = Modifier
					.fillMaxSize()
			) {
				Image(
					painterResource(R.drawable.sohrab),
					null,
					modifier = Modifier
						.offset(offset)
						.clip(RoundedCornerShape(fillet))
						.align(Alignment.TopCenter)
						.size(imageSize)
				)
				
				Column(
					modifier = Modifier
						.offset(y = offset2)
						.background(MaterialTheme.colorScheme.surface)
						.fillMaxWidth()
						.height(maxHeight - 400.dp)
						.align(Alignment.BottomCenter)
						.padding(20.dp)
				) {
					Text("Sohrab", fontSize = 20.sp, fontWeight = FontWeight.Bold)
					Text("Name", fontSize = 14.sp, color = Color(0xFFCCCCCC), fontWeight = FontWeight.Light)
					
					Spacer(Modifier.height(10.dp))
					
					Text("پو پو پو", fontSize = 20.sp, fontWeight = FontWeight.Bold)
					Text("Bio", fontSize = 14.sp, color = Color(0xFFCCCCCC), fontWeight = FontWeight.Light)
					
					Spacer(Modifier.height(10.dp))
					
					Text("+98 992 828 3720", fontSize = 20.sp, fontWeight = FontWeight.Bold)
					Text("Number", fontSize = 14.sp, color = Color(0xFFCCCCCC), fontWeight = FontWeight.Light)
					
					Spacer(Modifier.height(10.dp))
					
					Text("@AghrabePashmaloo", fontSize = 20.sp, fontWeight = FontWeight.Bold)
					Text("Username", fontSize = 14.sp, color = Color(0xFFCCCCCC), fontWeight = FontWeight.Light)
					
					Spacer(Modifier.height(10.dp))
					
					Row(
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.SpaceBetween,
						modifier = Modifier.fillMaxWidth()
					) {
						Text("Notifications", fontSize = 20.sp)
						Switch(true, null)
					}
				}
				
				FilledIconButton(
					onClick = {},
					modifier = Modifier
						.offset(330.dp, 375.dp)
						.scale(scale)
						.clip(CircleShape)
						.background(MaterialTheme.colorScheme.primary)
						.padding(4.dp)
				) {
					Icon(Icons.AutoMirrored.Rounded.Message, null)
				}
			}
			
			LaunchedEffect(state.currentState) {
				if (isShrinking && state.currentState.not())
					isExpanded = false
			}
			
			LaunchedEffect(Unit) {
				state.targetState = true
			}
		}
	}
}
