package es.iessaladillo.pedrojoya.intents.ui.battle

import android.app.Application
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import es.iessaladillo.pedrojoya.intents.data.local.DataSource
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.ui.selection.SelectionActivity
import es.iessaladillo.pedrojoya.intents.ui.winner.WinnerActivity

class BattleActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var bd: DataSource = Database

    var pokemon01: Pokemon = bd.getRandomPokemon()
    var pokemon02: Pokemon = bd.getRandomPokemon()
    private var appContext = application.applicationContext!!

    fun extractResult(result: ActivityResult, resultIntent: Intent){
        if(result.resultCode == AppCompatActivity.RESULT_OK){
            if(resultIntent.getIntExtra(SelectionActivity.EXTRA_CONTAINER,0) == 0){
                pokemon01 = resultIntent.getParcelableExtra<Pokemon>(SelectionActivity.EXTRA_PK) as Pokemon
            }else{
                pokemon02 = resultIntent.getParcelableExtra<Pokemon>(SelectionActivity.EXTRA_PK) as Pokemon
            }
        }
    }

    fun navigateToSelectPokemonScreen(containerId:Int, pokemon: Pokemon, dateSelectedCall: ActivityResultLauncher<Intent>) {
        val intent = SelectionActivity.newIntent(appContext,containerId, pokemon)

        dateSelectedCall.launch(intent)
    }

    fun navigateToFightResultScreen(): Intent {
        return if(pokemon01.fuerza > pokemon02.fuerza){
            WinnerActivity.newIntent(appContext, pokemon01)
        }else{
            WinnerActivity.newIntent(appContext, pokemon02)
        }
    }

}