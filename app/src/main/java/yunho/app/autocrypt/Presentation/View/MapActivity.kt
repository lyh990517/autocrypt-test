package yunho.app.autocrypt.Presentation.View

import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import yunho.app.autocrypt.Presentation.BaseActivity
import yunho.app.autocrypt.Presentation.BaseViewModel
import yunho.app.autocrypt.Presentation.State.MapDataState
import yunho.app.autocrypt.Presentation.ViewModel.MapViewModel
import yunho.app.autocrypt.databinding.ActivityMapBinding

class MapActivity : BaseActivity<BaseViewModel>() {
    private lateinit var binding: ActivityMapBinding
    override val viewModel: MapViewModel by viewModel()

    override fun observeData() {
        viewModel.MapLiveData.observe(this){ State ->
            when (State) {
                is MapDataState.UnInitialized -> initViews()
                is MapDataState.Loading -> handleLoading()
                is MapDataState.success -> handleSuccess(State)
                is MapDataState.successOne -> handleSuccessOne(State)
                is MapDataState.Error -> handleError()
            }
        }
    }

    private fun handleSuccessOne(state: MapDataState.successOne) {
        //TODO 가져온 데이터로 정보안내창 초기화
        state.CenterInfo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

    private fun initViews() = with(binding){
        //TODO 지도초기화
    }
}