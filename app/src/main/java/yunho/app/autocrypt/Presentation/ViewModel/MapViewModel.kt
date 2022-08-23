package yunho.app.autocrypt.Presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import yunho.app.autocrypt.Data.RemoteDB.Key
import yunho.app.autocrypt.Data.Repository.CenterRepository
import yunho.app.autocrypt.Presentation.BaseViewModel
import yunho.app.autocrypt.Presentation.State.CenterDataState
import yunho.app.autocrypt.Presentation.State.MapDataState

class MapViewModel(
    private val Repository : CenterRepository,
) : BaseViewModel() {
    private var _MapLiveData = MutableLiveData<MapDataState>(MapDataState.UnInitialized)
    val CenterLiveData = _MapLiveData
    override fun fetchData(): Job = viewModelScope.launch{
        _MapLiveData.postValue(MapDataState.Loading)
        _MapLiveData.postValue(MapDataState.success(Repository.getCenterListFromLocalDB()))
    }

}