package com.autfeng.mt

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.autfeng.mt.ui.theme.MyApplicationTheme
import java.io.File

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    SecondActivityContent()
                }
            }
        }
    }
}

@Composable
fun SecondActivityContent() {
    MyApplicationTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column {
                FolderList()
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun FolderList() {
    val folders = getLocalFolders()
    LazyColumn {
        items(folders) { folder ->
            Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .shadow(6.dp, shape = RoundedCornerShape(10.dp))
                        .background(Color.Black, shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_folder_24), // Replace with your folder icon resource
                        contentDescription = "Folder Icon",
                        tint = Color.White,
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = folder.name,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(folder.lastModified()),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Divider()
        }
    }
}

fun getLocalFolders(): List<File> {
    val path = Environment.getExternalStorageDirectory().path
    val directory = File(path)
    return directory.listFiles { file -> file.isDirectory }?.toList() ?: emptyList()
}

@Preview(showBackground = true)
@Composable
fun SecondActivityPreview() {
    MyApplicationTheme {
        SecondActivityContent()
    }
}