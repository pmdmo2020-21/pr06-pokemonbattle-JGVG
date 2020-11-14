package es.iessaladillo.pedrojoya.intents.data.local.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(val index: Int, val nombre: String, val fuerza: Int, @field:DrawableRes val drawablePokemon: Int): Parcelable