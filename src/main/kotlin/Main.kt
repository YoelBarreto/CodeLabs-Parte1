import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.coerceAtLeast
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
//    val cosos = listOf(
//        "Alvaro | 53",
//        "Samuel | 81",
//        "Andrei | 40",
//        "Yoel | 5",
//        "Alejandro | 20"
//    )

    val names: List<String> = List(20) { "$it" }
    Surface(){
        Column(
            modifier = Modifier
                .background(Color(0xFF4f7f9f))
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            names.forEach{ name ->
                val expanded = remember { mutableStateOf(false) }
                val extraPadding by animateDpAsState(
                    if (expanded.value) 48.dp else 0.dp,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                ))
                val currentPadding = extraPadding.coerceAtLeast(0.dp)
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
                            LeGreeting("Pumba ${name}", Modifier.weight(1f), expanded)
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(currentPadding),
                        ){

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
    IconButton(
        onClick = { expanded.value = !expanded.value },
    ) {
        Icon(
            imageVector = if (expanded.value) Filled.KeyboardArrowDown else Filled.KeyboardArrowUp,
            contentDescription =
            if (expanded.value) {
                "Mostrar menos"
            } else {
                "Mostrar más"
            }
        )
    }
    /*Button(
        onClick = {
            expanded.value = !expanded.value
        },
        modifier = Modifier.padding(end = 5.dp)
    ) {
        Text(if (expanded.value) "Show less" else "Show more")
    }*/
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Básico JetpackCompose",
        state = rememberWindowState(width = 600.dp, height = 700.dp)
    ) {
        LePreview()
    }
}