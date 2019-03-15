package id.co.rezkyauliapratama.rk_moviesdb_mvvm.presenter.common.views

import java.util.*

abstract class BaseObservableMviView<ListenerType> : BaseMviView(), ObservableMviView<ListenerType> {

    private val mListeners = HashSet<ListenerType>()

    protected val listeners: Set<ListenerType>
        get() = Collections.unmodifiableSet(mListeners)


    override fun registerListener(listener: ListenerType) {
        mListeners.add(listener)
    }

    override fun unregisterListener(listener: ListenerType) {
        mListeners.remove(listener)
    }
}
