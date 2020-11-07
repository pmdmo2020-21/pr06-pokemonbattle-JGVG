package es.iessaladillo.pedrojoya.intents.data.local.model

import androidx.annotation.DrawableRes

// TODO: Define las propiedades de un pokemon
class Pokemon{
    var index: Int
    var nombre: String
    var fuerza: Int
    @field:DrawableRes var drawablePokemon: Int

    constructor(index: Int, nombre: String, fuerza: Int, drawablePokemon: Int){
        this.index = index
        this.nombre = nombre
        this.fuerza = fuerza
        this. drawablePokemon = drawablePokemon
    }

}