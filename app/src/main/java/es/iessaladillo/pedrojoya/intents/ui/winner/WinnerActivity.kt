package es.iessaladillo.pedrojoya.intents.ui.winner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.intents.data.local.DataSource
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.databinding.WinnerActivityBinding

class WinnerActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_PK_ID = "EXTRA_PK_ID"

        fun newIntent(context: Context, pokemonId: Int): Intent {
            return Intent(context, WinnerActivity::class.java)
                .putExtra(EXTRA_PK_ID, pokemonId)
        }
    }

    private lateinit var b: WinnerActivityBinding
    var bd: DataSource = Database
    private var lista_pokemon = bd.getAllPokemons()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = WinnerActivityBinding.inflate(layoutInflater)
        setContentView(b.root)
        setupViews()
    }

    private fun setupViews() {
        var index = intent.getIntExtra(EXTRA_PK_ID, 0)

        b.imageViewWinner.setImageResource(lista_pokemon[index].drawablePokemon)
        b.textViewNameWinner.text = lista_pokemon[index].nombre
    }

}
