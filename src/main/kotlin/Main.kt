import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState


@Composable
fun Inicio(changewindow: MutableState<Boolean>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = {
                changewindow.value = true
            }
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun LePreview() {
    var changewindow = remember { mutableStateOf(false) }
    if (changewindow.value) {
        App()
    } else if (!changewindow.value) {
        Inicio(changewindow)
    }
}

@Composable
fun App() {
    val cosos = listOf(
        "Alvaro | 53",
        "Samuel | 81",
        "Andrei | 40",
        "Yoel | 5",
        "Alejandro | 20"
    )
    Surface(){
        Column(
            modifier = Modifier
                .background(Color(0xFF4f7f9f))
                .fillMaxSize()
        ) {
            cosos.forEach{ coso ->
                val expanded = remember { mutableStateOf(false) }
                Box(modifier = Modifier.clip(shape = RoundedCornerShape(20.dp))){
                    Column(
                        modifier = Modifier
                            .padding(bottom = 5.dp, top = 5.dp, start = 5.dp, end = 5.dp)
                            .background(Color(0xFF154363))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                            LeGreeting(coso, Modifier.weight(1f), expanded)
                        }
                        if (expanded.value) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                            ){
                                Text(color = Color.White, text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.")
                            }
                        }
                    }
                }
            }
        }
    }
//    MaterialTheme {
//    }
}

@Composable
fun LeGreeting(name: String, modifier: Modifier = Modifier, expanded: MutableState<Boolean>) {
    Text(
        text = name,
        modifier = modifier.padding(15.dp),
        // fontWeight = FontWeight.Bold,
        color = Color.White
    )
    Button(
        onClick = {
            expanded.value = !expanded.value
        },
        modifier = Modifier.padding(end = 5.dp)
    ) {
        Text(if (expanded.value) "Show less" else "Show more")
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Básico JetpackCompose",
        state = rememberWindowState(width = 500.dp, height = 700.dp)
    ) {
        LePreview()
    }
}
