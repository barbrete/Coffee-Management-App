package com.barbara.p2222

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barbara.p2222.database.DAO
import com.barbara.p2222.model.Cafe
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class Mostrar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GerenciandoCafezinho(applicationContext)
        }
    }
}

@Composable
fun GerenciandoCafezinho(contexto : Context) {
    var estadoTextFieldId by remember { mutableStateOf(TextFieldValue()) }
    var estadoTextFieldNome by remember { mutableStateOf(TextFieldValue()) }
    var estadoTextFieldNota by remember { mutableStateOf(TextFieldValue()) }
    var estadoTextFieldAroma by remember { mutableStateOf(TextFieldValue()) }
    var estadoTextFieldAcidez by remember { mutableStateOf(TextFieldValue()) }
    var estadoTextFieldAmargor by remember { mutableStateOf(TextFieldValue()) }
    var estadoTextFieldSabor by remember { mutableStateOf(TextFieldValue()) }
    var estadoTextFieldPreco by remember { mutableStateOf(TextFieldValue()) }
    val estadoListaCafezinho = remember { mutableStateOf<List<String>>(emptyList()) }

    val banco: DatabaseReference = Firebase.database.reference
    val dao = DAO(banco)

    fun atualizarListaCafezinho() {
        dao.mostrarDados { lista ->
            estadoListaCafezinho.value = lista
        }
    }

    atualizarListaCafezinho()

    fun limparCampos() {
        estadoTextFieldId = TextFieldValue()
        estadoTextFieldNome = TextFieldValue()
        estadoTextFieldNota = TextFieldValue()
        estadoTextFieldAroma = TextFieldValue()
        estadoTextFieldAcidez = TextFieldValue()
        estadoTextFieldAmargor = TextFieldValue()
        estadoTextFieldSabor = TextFieldValue()
        estadoTextFieldPreco = TextFieldValue()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Controle de Cafes", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = estadoTextFieldId,
            onValueChange = { estadoTextFieldId = it },
            label = { Text("Digite o valor do ID") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = estadoTextFieldNome,
            onValueChange = { estadoTextFieldNome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = estadoTextFieldNota,
            onValueChange = { estadoTextFieldNota = it },
            label = { Text("Nota") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = estadoTextFieldAroma,
            onValueChange = { estadoTextFieldAroma = it },
            label = { Text("Aroma") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = estadoTextFieldAcidez,
            onValueChange = { estadoTextFieldAcidez = it },
            label = { Text("Acidez") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = estadoTextFieldAmargor,
            onValueChange = { estadoTextFieldAmargor = it },
            label = { Text("Amargor") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = estadoTextFieldSabor,
            onValueChange = { estadoTextFieldSabor = it },
            label = { Text("Sabor") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = estadoTextFieldPreco,
            onValueChange = { estadoTextFieldPreco = it },
            label = { Text("Preco") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    var cafe = Cafe(
                        estadoTextFieldId.text,
                        estadoTextFieldNome.text,
                        estadoTextFieldNota.text,
                        estadoTextFieldAroma.text.toInt(),
                        estadoTextFieldAcidez.text.toInt(),
                        estadoTextFieldAmargor.text.toInt(),
                        estadoTextFieldSabor.text.toInt(),
                        estadoTextFieldPreco.text.toDouble()
                    )

                    //Inserção dos dados no banco.
                    dao.inserir_atualizar(cafe)

                    limparCampos()

                    atualizarListaCafezinho()

                    Toast.makeText(contexto, "Inserido com sucesso", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Adicionar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    var cafe = Cafe(
                        estadoTextFieldId.text,
                        estadoTextFieldNome.text,
                        estadoTextFieldNota.text,
                        estadoTextFieldAroma.text.toInt(),
                        estadoTextFieldAcidez.text.toInt(),
                        estadoTextFieldAmargor.text.toInt(),
                        estadoTextFieldSabor.text.toInt(),
                        estadoTextFieldPreco.text.toDouble()
                    )

                    //Inserção dos dados no banco.
                    dao.inserir_atualizar(cafe)

                    limparCampos()

                    atualizarListaCafezinho()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Atualizar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    dao.excluir(estadoTextFieldId.text)

                    limparCampos()

                    atualizarListaCafezinho()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Excluir")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(estadoListaCafezinho.value) { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    color = Color.Blue
                )
            }
        }
    }
}