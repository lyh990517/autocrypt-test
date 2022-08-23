package yunho.app.autocrypt.Presentation

import yunho.app.autocrypt.Data.Entity.CenterEntity

sealed class DataState{
    object UnInitialized : DataState()

    object Loading : DataState()

    data class success(
        val Data : List<CenterEntity>
    ) : DataState()

    object Error : DataState()
}
