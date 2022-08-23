package yunho.app.autocrypt.Presentation.View

import org.koin.androidx.viewmodel.ext.android.viewModel
import yunho.app.autocrypt.Presentation.BaseActivity
import yunho.app.autocrypt.Presentation.BaseViewModel
import yunho.app.autocrypt.Presentation.State.MapDataState
import yunho.app.autocrypt.Presentation.ViewModel.MapViewModel

class MapActivity : BaseActivity<BaseViewModel>() {
    override val viewModel: MapViewModel by viewModel()

    override fun observeData() {
        viewModel.CenterLiveData.observe(this){ State ->
            when (State) {
                is MapDataState.UnInitialized -> initViews()
                is MapDataState.Loading -> handleLoading()
                is MapDataState.success -> handleSuccess(State)
                is MapDataState.Error -> handleError()
            }
        }
    }

    private fun handleError() {

    }

    private fun handleSuccess(state: MapDataState.success) {
        state.LocalData.forEach { Center ->
            //TODO 센터마커찍기
        }
    }

    private fun handleLoading() {

    }

    private fun initViews() {

    }
}