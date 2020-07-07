package br.com.recipebook.utilityandroid.view

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import androidx.fragment.app.Fragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

private val SCREEN_ARGS = "SCREEN_ARGS"

fun <V : Any> safeArgs() = object : ReadOnlyProperty<Fragment, V> {
    var value: V? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): V {
        if (value == null) {
            val args = thisRef.arguments
                ?: throw IllegalArgumentException("There are no fragment arguments!")
            val argUntyped = args.get(SCREEN_ARGS)
            argUntyped
                ?: throw IllegalArgumentException("Screen arguments not found at key SCREEN_ARGS!")
            @Suppress("UNCHECKED_CAST")
            value = argUntyped as V
        }
        return value ?: throw IllegalArgumentException("")
    }
}

fun <V : Any> activitySafeArgs() = object : ReadOnlyProperty<Activity, V> {
    var value: V? = null

    override fun getValue(thisRef: Activity, property: KProperty<*>): V {
        if (value == null) {
            val args = thisRef.intent.extras
                ?: throw IllegalArgumentException("There are no activity arguments!")
            val argUntyped = args.get(SCREEN_ARGS)
            argUntyped
                ?: throw IllegalArgumentException("Screen arguments not found at key SCREEN_ARGS!")
            @Suppress("UNCHECKED_CAST")
            value = argUntyped as V
        }
        return value ?: throw IllegalArgumentException("")
    }
}

fun Intent.putSafeArgs(value: Parcelable) {
    putExtra(SCREEN_ARGS, value)
}
