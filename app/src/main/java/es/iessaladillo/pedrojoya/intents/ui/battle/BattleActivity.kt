package es.iessaladillo.pedrojoya.intents.ui.battle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.intents.data.local.DataSource
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.databinding.BattleActivityBinding
import es.iessaladillo.pedrojoya.intents.ui.selection.SelectionActivity
import es.iessaladillo.pedrojoya.intents.ui.winner.WinnerActivity

private const val RC_PK_SELECCTION: Int = 1

class BattleActivity : AppCompatActivity() {
    private lateinit var b: BattleActivityBinding
    var bd: DataSource = Database

    private var pokemon01 = bd.getRandomPokemon()
    private var pokemon02 = bd.getRandomPokemon()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = BattleActivityBinding.inflate(layoutInflater)
        setContentView(b.root)
        initData()
        setupViews()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK && requestCode == RC_PK_SELECCTION && intent != null){
            var index = intent.getIntExtra(SelectionActivity.EXTRA_ID,0)
            var container = intent.getIntExtra(SelectionActivity.EXTRA_CONTAINER,0)

            if(container == 0){
                pokemon01 = bd.getPokemonById(index.toLong())!!
            }else{
                pokemon02 = bd.getPokemonById(index.toLong())!!
            }
            initData()
        }
    }

    private fun initData(){
        b.imageViewPokemon01.setImageResource(pokemon01.drawablePokemon)
        b.textViewPokemon01.text = pokemon01.nombre
        b.imageViewPokemon02.setImageResource(pokemon02.drawablePokemon)
        b.textViewPokemon02.text = pokemon02.nombre
    }
    private fun setupViews() {
        b.contain01.setOnClickListener{this.navigateToSelectPokemonScreen(0, pokemon01.index)}
        b.contain02.setOnClickListener{this.navigateToSelectPokemonScreen(1, pokemon02.index)}
        b.button.setOnClickListener{this.fight()}
    }

    private fun navigateToSelectPokemonScreen(containerId:Int, pokemonId:Int) {
        var intent = SelectionActivity.newIntent(this, containerId, pokemonId)
        startActivityForResult(intent, RC_PK_SELECCTION)
    }

    private fun fight() {
        if(pokemon01.fuerza > pokemon02.fuerza){
            navigateToFightResultScreen(pokemon01.index)
        }else{
            navigateToFightResultScreen(pokemon02.index)
        }
    }

    private fun navigateToFightResultScreen(winnerIndex: Int) {
        var intent = WinnerActivity.newIntent(this, winnerIndex)
        startActivity(intent)
    }

}