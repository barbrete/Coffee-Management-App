package com.barbara.p2222.database

import android.util.Log
import com.barbara.p2222.model.Cafe
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
}