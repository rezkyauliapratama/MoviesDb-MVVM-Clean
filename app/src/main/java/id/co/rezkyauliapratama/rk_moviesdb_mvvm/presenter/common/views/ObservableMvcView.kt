package id.co.rezkyauliapratama.rk_moviesdb_mvvm.presenter.common.views


interface ObservableMvcView<ListenerType> : MvcView {

    fun registerListener(listener: ListenerType)

    fun unregisterListener(listener: ListenerType)
}
