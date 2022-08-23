package yunho.app.autocrypt.Presentation.State

import yunho.app.autocrypt.Data.Entity.CenterEntity

sealed class MapDataState{
    object UnInitialized : MapDataState()

    object Loading : MapDataState()

    data class success(
        val LocalData : List<CenterEntity>
    ) : MapDataState()

    object Error : MapDataState()
}
