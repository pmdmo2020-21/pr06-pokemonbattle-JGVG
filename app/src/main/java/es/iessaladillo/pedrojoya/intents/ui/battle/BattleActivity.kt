package es.iessaladillo.pedrojoya.intents.ui.battle

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.intents.databinding.BattleActivityBinding

class BattleActivity : AppCompatActivity() {
    private val b: BattleActivityBinding by lazy {BattleActivityBinding.inflate(layoutInflater)}
    private val viewModel : BattleActivityViewModel by viewModels()
    private val dateSelectedCall = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        viewModel.extractResult(result, result.data!!)
        setupViews()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)
        setupViews()
    }

    private fun setupViews() {
        //Values
        b.imageViewPokemon01.setImageResource(viewModel.pokemon01.drawablePokemon)
        b.textViewPokemon01.text = viewModel.pokemon01.nombre
        b.imageViewPokemon02.setImageResource(viewModel.pokemon02.drawablePokemon)
        b.textViewPokemon02.text = viewModel.pokemon02.nombre

        //funcionality
        b.contain01.setOnClickListener{viewModel.navigateToSelectPokemonScreen(0, viewModel.pokemon01, dateSelectedCall)}
        b.contain02.setOnClickListener{viewModel.navigateToSelectPokemonScreen(1, viewModel.pokemon02, dateSelectedCall)}
        b.button.setOnClickListener{startActivity(viewModel.navigateToFightResultScreen())}
    }

}