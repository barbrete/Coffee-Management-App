package com.example.myapplication.database

import android.util.Log
import com.example.myapplication.model.Cafe
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson

class DAO (banco : DatabaseReference){
    var banco : DatabaseReference
    init{
        this.banco = banco
    }
    fun inserir_atualizar(cafe : Cafe){
        this.banco.child(cafe.Id).setValue(cafe)
    }

    fun mostrarDados(callback: (ArrayList<String>) -> Unit) {
        val listaCafes = ArrayList<String>()

        this.banco.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val gson = Gson()
                    Log.i("TESTE", "------")
                    for (i in snapshot.children) {
                        val json = gson.toJson(i.value)
                        val cafe = gson.fromJson(json, Cafe::class.java)
                        listaCafes.add(cafe.toString())
                        Log.i("TESTE", "Cafe: " + cafe.toString())
                    }
                    Log.i("TESTE", "------")
                    callback(listaCafes)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("Teste", "Erro: $error")
            }
        })
    }

    fun excluir(registro : String){
        this.banco.child(registro).removeValue()
    }

    fun cafeCaro(callback: (Cafe?) -> Unit) {

        this.banco.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val gson = Gson()
                    val listaDeCafes = mutableListOf<Cafe>()
                    Log.i("TESTE", "------")
                    for (i in snapshot.children) {
                        val json = gson.toJson(i.value)
                        val cafe = gson.fromJson(json, Cafe::class.java)
                        listaDeCafes.add(cafe)
                    }
                    val cafeMaisCaro = listaDeCafes.maxByOrNull { it.preco }
                    cafeMaisCaro?.let {
                        Log.i("TESTE", "Café mais CARO: ${it.nome} com o Preço de R$: ${it.preco}")
                    }
                    Log.i("TESTE", "------")
                    callback(cafeMaisCaro)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("Teste", "Erro: $error")
            }
        }
        )
    }

    fun mediaCafe(callback: (Double) -> Unit) {
        this.banco.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val gson = Gson()
                    val listaDeCafes = mutableListOf<Cafe>()
                    Log.i("TESTE", "------")
                    for (i in snapshot.children) {
                        val json = gson.toJson(i.value)
                        val cafe = gson.fromJson(json, Cafe::class.java)
                        listaDeCafes.add(cafe)
                    }
                    Log.i("TESTE", "------")
                    val preco = listaDeCafes.sumOf { it.preco }
                    val media = preco / listaDeCafes.size
                    Log.i("TESTE", "Média de preço de cafés é: R$ ${media}")
                    callback(media)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.i("Teste", "Erro: $error")
            }
        })
    }

    fun maisAroma(callback: (Cafe?) -> Unit) {
        this.banco.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val gson = Gson()
                    val listaAromas = mutableListOf<Cafe>()
                    Log.i("TESTE", "------")
                    for (i in snapshot.children) {
                        val json = gson.toJson(i.value)
                        val cafe = gson.fromJson(json, Cafe::class.java)
                        listaAromas.add(cafe)
                    }
                    val maisAroma = listaAromas.maxByOrNull { it.aroma }
                    maisAroma?.let {
                        Log.i("TESTE", "Café com mais Aroma: ${it.nome}, nota do Aroma: ${it.aroma}")
                    }
                    Log.i("TESTE", "------")
                    callback(maisAroma)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("Teste", "Erro: $error")
            }
        })
    }
    fun menosAcidez(callback: (Cafe?) -> Unit) {
        this.banco.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val gson = Gson()
                    val listaAcidez = mutableListOf<Cafe>()
                    Log.i("TESTE", "------")
                    for (i in snapshot.children) {
                        val json = gson.toJson(i.value)
                        val cafe = gson.fromJson(json, Cafe::class.java)
                        listaAcidez.add(cafe)
                    }
                    val menosAcidez = listaAcidez.minByOrNull { it.acidez }
                    menosAcidez?.let {
                        Log.i("TESTE", "Café com menos Acidez: ${it.nome}, nota da Acidez: ${it.acidez}")
                    }
                    Log.i("TESTE", "------")
                    callback(menosAcidez)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("Teste", "Erro: $error")
            }
        })
    }
}