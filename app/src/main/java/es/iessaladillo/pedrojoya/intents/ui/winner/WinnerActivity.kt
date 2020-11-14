package es.iessaladillo.pedrojoya.intents.ui.winner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.intents.data.local.DataSource
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.WinnerActivityBinding

class WinnerActivity : AppCompatActivity() {
    private lateinit var b: WinnerActivityBinding
    var bd: DataSource = Database
    private var lista_pokemon = bd.getAllPokemons()

    companion object{
        const val EXTRA_PK = "EXTRA_PK"

        fun newIntent(context: Context, pokemon: Pokemon): Intent {
            return Intent(context, WinnerActivity::class.java)
                .putExtra(EXTRA_PK, pokemon)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = WinnerActivityBinding.inflate(layoutInflater)
        setContentView(b.root)
        setupViews()
    }

    private fun setupViews() {
        var pokemon = intent.getParcelableExtra<Pokemon>(EXTRA_PK) as Pokemon

        b.imageViewWinner.setImageResource(lista_pokemon[pokemon.index].drawablePokemon)
        b.textViewNameWinner.text = lista_pokemon[pokemon.index].nombre
    }

}
