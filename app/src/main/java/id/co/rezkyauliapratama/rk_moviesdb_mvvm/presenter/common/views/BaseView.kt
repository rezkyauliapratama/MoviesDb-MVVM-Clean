package id.co.rezkyauliapratama.rk_moviesdb_mvvm.presenter.common.views

import android.content.Context
import androidx.annotation.StringRes
import androidx.databinding.ViewDataBinding


abstract class BaseMviView: MviView {

    override lateinit var dataBinding: ViewDataBinding

    protected fun getContext(): Context {
        return dataBinding.root.context
    }

    protected fun getString(@StringRes id: Int): String? {
        return getContext().getString(id)
    }
}
