package es.iessaladillo.pedrojoya.intents.ui.selection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.intents.data.local.DataSource
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.BattleActivityBinding
import es.iessaladillo.pedrojoya.intents.databinding.SelectionActivityBinding

class SelectionActivity : AppCompatActivity() {
    private val b: SelectionActivityBinding by lazy { SelectionActivityBinding.inflate(layoutInflater)}
    private var bd: DataSource = Database
    private var lista_pokemon = bd.getAllPokemons()

    companion object{
        const val EXTRA_CONTAINER = "EXTRA_CONTAINER"
        const val EXTRA_PK = "EXTRA_PK"

        fun newIntent(context: Context, containerId: Int, pokemon: Pokemon): Intent{
            return Intent(context, SelectionActivity::class.java)
                .putExtra(EXTRA_CONTAINER, containerId)
                .putExtra(EXTRA_PK, pokemon)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)
        setupViews()
    }

    private fun setupViews() {
        //Values
        val pokemon = intent.getParcelableExtra<Pokemon>(EXTRA_PK)
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

        if (pokemon != null) {
            when(pokemon.index){
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

        //Funcionality
        b.radioButtonPokemon0.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon0.id)}
        b.radioButtonPokemon1.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon1.id)}
        b.radioButtonPokemon2.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon2.id)}
        b.radioButtonPokemon3.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon3.id)}
        b.radioButtonPokemon4.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon4.id)}
        b.radioButtonPokemon5.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon5.id)}

        b.imageViewPokemon0.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon0.id)}
        b.imageViewPokemon1.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon1.id)}
        b.imageViewPokemon2.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon2.id)}
        b.imageViewPokemon3.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon3.id)}
        b.imageViewPokemon4.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon4.id)}
        b.imageViewPokemon5.setOnClickListener{controlOfUncheckeds(b.radioButtonPokemon5.id)}
    }

    private fun controlOfUncheckeds(id: Int) = when (id) {
        b.radioButtonPokemon0.id -> {
            b.radioButtonPokemon0.isChecked = true
            b.radioButtonPokemon1.isChecked = false
            b.radioButtonPokemon2.isChecked = false
            b.radioButtonPokemon3.isChecked = false
            b.radioButtonPokemon4.isChecked = false
            b.radioButtonPokemon5.isChecked = false
        }
        b.radioButtonPokemon1.id -> {
            b.radioButtonPokemon0.isChecked = false
            b.radioButtonPokemon1.isChecked = true
            b.radioButtonPokemon2.isChecked = false
            b.radioButtonPokemon3.isChecked = false
            b.radioButtonPokemon4.isChecked = false
            b.radioButtonPokemon5.isChecked = false
        }
        b.radioButtonPokemon2.id -> {
            b.radioButtonPokemon0.isChecked = false
            b.radioButtonPokemon1.isChecked = false
            b.radioButtonPokemon2.isChecked = true
            b.radioButtonPokemon3.isChecked = false
            b.radioButtonPokemon4.isChecked = false
            b.radioButtonPokemon5.isChecked = false
        }
        b.radioButtonPokemon3.id -> {
            b.radioButtonPokemon0.isChecked = false
            b.radioButtonPokemon1.isChecked = false
            b.radioButtonPokemon2.isChecked = false
            b.radioButtonPokemon3.isChecked = true
            b.radioButtonPokemon4.isChecked = false
            b.radioButtonPokemon5.isChecked = false
        }
        b.radioButtonPokemon4.id -> {
            b.radioButtonPokemon0.isChecked = false
            b.radioButtonPokemon1.isChecked = false
            b.radioButtonPokemon2.isChecked = false
            b.radioButtonPokemon3.isChecked = false
            b.radioButtonPokemon4.isChecked = true
            b.radioButtonPokemon5.isChecked = false
        }
        else -> {
            b.radioButtonPokemon0.isChecked = false
            b.radioButtonPokemon1.isChecked = false
            b.radioButtonPokemon2.isChecked = false
            b.radioButtonPokemon3.isChecked = false
            b.radioButtonPokemon4.isChecked = false
            b.radioButtonPokemon5.isChecked = true
        }
    }

    override fun onBackPressed() {
        var selected: Pokemon = intent.getParcelableExtra<Pokemon>(EXTRA_PK) as Pokemon
        val container = intent.getIntExtra(EXTRA_CONTAINER, 0)

        if(b.radioButtonPokemon0.isChecked)
            selected = bd.getPokemonById(0)!!
        if(b.radioButtonPokemon1.isChecked)
            selected = bd.getPokemonById(1)!!
        if(b.radioButtonPokemon2.isChecked)
            selected = bd.getPokemonById(2)!!
        if(b.radioButtonPokemon3.isChecked)
            selected = bd.getPokemonById(3)!!
        if(b.radioButtonPokemon4.isChecked)
            selected = bd.getPokemonById(4)!!
        if(b.radioButtonPokemon5.isChecked)
            selected = bd.getPokemonById(5)!!

        val result = Intent().putExtras(bundleOf(EXTRA_PK to selected, EXTRA_CONTAINER to container))
        setResult(RESULT_OK, result)

        super.onBackPressed()
    }

}