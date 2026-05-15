package com.segundoparcialseminaroi2partea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.segundoparcialseminaroi2partea.ui.screens.DetalleScreen
import com.segundoparcialseminaroi2partea.ui.screens.ListaScreen
import com.segundoparcialseminaroi2partea.ui.theme.SegundoParcialSeminario2ParteATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SegundoParcialSeminario2ParteATheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    // Navegación simple por estados para evitar problemas de conexión con librerías externas
    var currentScreen by remember { mutableStateOf("lista") }
    var selectedRegistroId by remember { mutableIntStateOf(-1) }

    when (currentScreen) {
        "lista" -> {
            ListaScreen(
                onRegistroClick = { id ->
                    selectedRegistroId = id
                    currentScreen = "detalle"
                }
            )
        }
        "detalle" -> {
            DetalleScreen(
                registroId = selectedRegistroId,
                onBack = {
                    currentScreen = "lista"
                }
            )
        }
    }
}
