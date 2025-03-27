import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DrawerContent() {
    ModalDrawerSheet(
        modifier = Modifier.width(300.dp) // Set custom width to a lower value
    ) {
        Column {
            TopAppBar(
                title = { Text("MT管理器") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF303030),
                    titleContentColor = Color.White
                )
            )
        }
    }
}