package com.example.myapplication.view

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.database.DAO
import com.example.myapplication.model.Cafe
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class Mostrar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MostrarCafe(applicationContext) // Exibe a UI com os cafés
        }
    }
}

@Composable
fun MostrarCafe(contexto: Context) {
    // Estado para armazenar a lista de cafés
    val estadoListaCafezinho = remember { mutableStateOf<List<Cafe>>(emptyList()) }
    val banco: DatabaseReference = Firebase.database.reference
    val dao = DAO(banco)

    // Função que chama o DAO para buscar os dados
    fun mostrarCafe() {
        dao.mostrarDados { lista ->
            estadoListaCafezinho.value = lista // Atualiza o estado com a lista de cafés
        }
    }

    // Chama a função mostrarCafe() quando o Composable for composto
    LaunchedEffect(Unit) {
        mostrarCafe()
    }

    // Exibe os cafés na tela
    LazyColumn {
        items(estadoListaCafezinho.value) { cafe ->
            CafeItem(cafe = cafe) // Exibe o item de café
        }
    }
}

@Composable
fun CafeItem(cafe: Cafe) {
    // Exibe os detalhes do café em um Card
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = cafe.nome,
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = cafe.Id,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Nota: ${cafe.nota}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Acidez: ${cafe.acidez}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Amargor: ${cafe.amargor}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Aroma: ${cafe.aroma}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Sabor: ${cafe.sabor}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Preço: R$ ${"%.2f".format(cafe.preco)}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
