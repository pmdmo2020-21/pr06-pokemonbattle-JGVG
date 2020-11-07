package es.iessaladillo.pedrojoya.intents.data.local

import es.iessaladillo.pedrojoya.intents.R
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon

object Database: DataSource{
    private val lista_pokemon: List<Pokemon> = listOf(
        Pokemon(0,"Bulbasur", 10, R.drawable.bulbasur),
        Pokemon(1,"Cubone", 11, R.drawable.cubone),
        Pokemon(2,"Feebas", 9, R.drawable.feebas),
        Pokemon(3,"Giratina", 15, R.drawable.giratina),
        Pokemon(4,"Gyarados", 14, R.drawable.gyarados),
        Pokemon(5,"Pikachu", 13, R.drawable.pikachu),
    )

    override fun getRandomPokemon(): Pokemon {
        return lista_pokemon[(0..5).random()]
    }

    override fun getAllPokemons(): List<Pokemon> {
        return lista_pokemon
    }

    override fun getPokemonById(id: Long): Pokemon? {
        return lista_pokemon[id.toInt()]
    }

}