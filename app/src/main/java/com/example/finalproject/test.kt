import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.ui.theme.FinalProjectTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "welcome") {
                composable("welcome") { WelcomeScreen(navController) }
                composable("secondScreen") { SecondScreen() }
            }
        }
    }
}


@Composable
fun WelcomeScreen(navController: NavController) {
    Column {
        Text(text = "Welcome!")
        Button(onClick = { navController.navigate("secondScreen") }) {
            Text(text = "Continue")
        }
    }
}
@Preview
@Composable
fun SecondScreen() {
    Text(text = "Second screen!")
}