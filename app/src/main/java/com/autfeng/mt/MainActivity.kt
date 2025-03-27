package com.autfeng.mt

import DrawerContent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.autfeng.mt.ui.theme.MyApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val view = LocalView.current
            if (!view.isInEditMode) {
                WindowCompat.setDecorFitsSystemWindows(window, false)
                WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = true
                window.statusBarColor = android.graphics.Color.TRANSPARENT
            }
            MyApplicationTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    LocalContext.current
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent() },
        content = {
            Scaffold(
                topBar = { TopBar(scope, drawerState) },
                bottomBar = { BottomBar() }
            ) { innerPadding ->
                Row(modifier = Modifier.padding(innerPadding)) {
                    Column(modifier = Modifier.weight(1f)) {
                        SecondActivityContent()
                    }
                    Divider(
                        color = Color.Gray,
                        modifier = Modifier
                            .width(1.dp)
                            .fillMaxHeight()
                            .shadow(4.dp)
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        SecondActivityContent()
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(scope: CoroutineScope, drawerState: DrawerState) {
    TopAppBar(
        title = {
            Column {
                Text(
                    "/storage/emulated/0/",
                    style = MaterialTheme.typography.titleSmall, // Smaller main title text size
                    color = Color.White
                )
                Text(
                    "文件夹：4 文件：100 储存：217.24G/225.64G",
                    style = MaterialTheme.typography.bodySmall, // Smaller subtitle text size
                    color = Color.White
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch { drawerState.open() }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_menu_32),
                    contentDescription = "Menu",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Handle action icon click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_more_vert_32),
                    contentDescription = "Action",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp) // Smaller icon size
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF303030),
            titleContentColor = Color.White
        ),
        modifier = Modifier
            .shadow(8.dp) // Reduced shadow size
    )
}

@Composable
fun BottomBar() {
    Column {
        Divider(
            color = Color.Gray,
            thickness = 0.2.dp
        )
        BottomAppBar(
            containerColor = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            content = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = { /* Handle left icon click */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_left_24),
                            contentDescription = "Keyboard Arrow Left",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = { /* Handle left icon click */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                            contentDescription = "Keyboard Arrow Right",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = { /* Handle add icon click */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_add_24),
                            contentDescription = "Add",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = { /* Handle right icon click */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_team_24),
                            contentDescription = "Arrow Team",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = { /* Handle right icon click */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_upward_24),
                            contentDescription = "Arrow Upward",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MyApplicationTheme {
        MainScreen()
    }
}