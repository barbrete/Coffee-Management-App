package com.barbara.p2222.model

class Cafe(Id : String, nome : String, nota : String, aroma : Int, acidez : Int, sabor : Int , amargor : Int , preco : Double){
    var Id : String
    var nome : String
    var nota : String
    var aroma : Int
    var acidez : Int
    var sabor : Int
    var amargor : Int
    var preco : Double

    init{
        this.Id = Id
        this.nome = nome
        this.nota = nota
        this.aroma = aroma
        this.acidez = acidez
        this.sabor = sabor
        this.amargor = amargor
        this.preco = preco
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cafe

        if (Id != other.Id) return false
        if (nome != other.nome) return false
        if (nota != other.nota) return false
        if (aroma != other.aroma) return false
        if (acidez != other.acidez) return false
        if (sabor != other.sabor) return false
        if (amargor != other.amargor) return false
        if (preco != other.preco) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Id.hashCode()
        result = 31 * result + nome.hashCode()
        result = 31 * result + nota.hashCode()
        result = 31 * result + aroma
        result = 31 * result + acidez
        result = 31 * result + sabor
        result = 31 * result + amargor
        result = 31 * result + preco.hashCode()
        return result
    }

    override fun toString(): String {
        return "Cafe(Id='$Id', nome='$nome', nota='$nota', aroma=$aroma, acidez=$acidez, sabor=$sabor, amargor=$amargor, preco=$preco)"
    }
}