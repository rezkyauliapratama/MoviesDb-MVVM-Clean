package id.co.rezkyauliapratama.rk_moviesdb_mvvm.presenter.common.views


interface ObservableMviView<ListenerType> : MviView {

    fun registerListener(listener: ListenerType)

    fun unregisterListener(listener: ListenerType)
}
