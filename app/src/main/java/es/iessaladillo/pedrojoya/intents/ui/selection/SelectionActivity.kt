package es.iessaladillo.pedrojoya.intents.ui.selection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.intents.data.local.DataSource
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.databinding.SelectionActivityBinding

class SelectionActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "EXTRA_ID"
        const val EXTRA_CONTAINER = "EXTRA_CONTAINER"
        const val EXTRA_PK_ID = "EXTRA_PK_ID"

        fun newIntent(context: Context, containerId: Int, pokemonId: Int): Intent{
            return Intent(context, this::class.java)
                .putExtra(EXTRA_CONTAINER, containerId)
                .putExtra(EXTRA_PK_ID, pokemonId)
        }
    }

    private lateinit var b: SelectionActivityBinding
    var bd: DataSource = Database
    private var lista_pokemon = bd.getAllPokemons()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = SelectionActivityBinding.inflate(layoutInflater)
        setContentView(b.root)
        initData()
        setupViews()
    }

    private fun initData(){
        var pokemonId = intent.getIntExtra(EXTRA_PK_ID,0)

        b.imageViewPokemon0.setImageResource(lista_pokemon[0].drawablePokemon)
        b.radioButtonPokemon0.text = lista_pokemon[0].nombre
        b.imageViewPokemon1.setImageResource(lista_pokemon[1].drawablePokemon)
        b.radioButtonPokemon1.text = lista_pokemon[1].nombre
        b.imageViewPokemon2.setImageResource(lista_pokemon[2].drawablePokemon)
        b.radioButtonPokemon2.text = lista_pokemon[2].nombre
        b.imageViewPokemon3.setImageResource(lista_pokemon[3].drawablePokemon)
        b.radioButtonPokemon3.text = lista_pokemon[3].nombre
        b.imageViewPokemon4.setImageResource(lista_pokemon[4].drawablePokemon)
        b.radioButtonPokemon4.text = lista_pokemon[4].nombre
        b.imageViewPokemon5.setImageResource(lista_pokemon[5].drawablePokemon)
        b.radioButtonPokemon5.text = lista_pokemon[5].nombre

        when(pokemonId){
            0 -> {
                b.radioButtonPokemon0.isChecked = true
            }
            1 -> {
                b.radioButtonPokemon1.isChecked = true
            }
            2 -> {
                b.radioButtonPokemon2.isChecked = true
            }
            3 -> {
                b.radioButtonPokemon3.isChecked = true
            }
            4 -> {
                b.radioButtonPokemon4.isChecked = true
            }
            5 -> {
                b.radioButtonPokemon5.isChecked = true
            }
        }
    }

    private fun setupViews() {
        b.radioButtonPokemon0.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon0.id)}
        b.radioButtonPokemon1.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon1.id)}
        b.radioButtonPokemon2.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon2.id)}
        b.radioButtonPokemon3.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon3.id)}
        b.radioButtonPokemon4.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon4.id)}
        b.radioButtonPokemon5.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon5.id)}
    }

    private fun controlOfUncheckeds(id: Int) = when (id) {
        b.radioButtonPokemon0.id -> {
            b.radioButtonPokemon1.isChecked = false
            b.radioButtonPokemon2.isChecked = false
            b.radioButtonPokemon3.isChecked = false
            b.radioButtonPokemon4.isChecked = false
            b.radioButtonPokemon5.isChecked = false
        }
        b.radioButtonPokemon1.id -> {
            b.radioButtonPokemon0.isChecked = false
            b.radioButtonPokemon2.isChecked = false
            b.radioButtonPokemon3.isChecked = false
            b.radioButtonPokemon4.isChecked = false
            b.radioButtonPokemon5.isChecked = false
        }
        b.radioButtonPokemon2.id -> {
            b.radioButtonPokemon1.isChecked = false
            b.radioButtonPokemon0.isChecked = false
            b.radioButtonPokemon3.isChecked = false
            b.radioButtonPokemon4.isChecked = false
            b.radioButtonPokemon5.isChecked = false
        }
        b.radioButtonPokemon3.id -> {
            b.radioButtonPokemon1.isChecked = false
            b.radioButtonPokemon2.isChecked = false
            b.radioButtonPokemon0.isChecked = false
            b.radioButtonPokemon4.isChecked = false
            b.radioButtonPokemon5.isChecked = false
        }
        b.radioButtonPokemon4.id -> {
            b.radioButtonPokemon1.isChecked = false
            b.radioButtonPokemon2.isChecked = false
            b.radioButtonPokemon3.isChecked = false
            b.radioButtonPokemon0.isChecked = false
            b.radioButtonPokemon5.isChecked = false
        }
        else -> {
            b.radioButtonPokemon1.isChecked = false
            b.radioButtonPokemon2.isChecked = false
            b.radioButtonPokemon3.isChecked = false
            b.radioButtonPokemon4.isChecked = false
            b.radioButtonPokemon0.isChecked = false
        }
    }

    override fun onBackPressed() {
        var index = -1
        var container = intent.getIntExtra(EXTRA_CONTAINER, 0)

        if(b.radioButtonPokemon0.isChecked)
            index = 0
        if(b.radioButtonPokemon1.isChecked)
            index = 1
        if(b.radioButtonPokemon2.isChecked)
            index = 2
        if(b.radioButtonPokemon3.isChecked)
            index = 3
        if(b.radioButtonPokemon4.isChecked)
            index = 4
        if(b.radioButtonPokemon5.isChecked)
            index = 5

        var result = Intent().putExtras(bundleOf(EXTRA_ID to index, EXTRA_CONTAINER to container))
        setResult(RESULT_OK, result)

        super.onBackPressed()
    }

}