package yunho.app.autocrypt.Presentation.View

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.MarkerIcons
import org.koin.androidx.viewmodel.ext.android.viewModel
import yunho.app.autocrypt.Data.Entity.CenterEntity
import yunho.app.autocrypt.Presentation.Adapter.CenterAdapter
import yunho.app.autocrypt.Presentation.BaseActivity
import yunho.app.autocrypt.Presentation.BaseViewModel
import yunho.app.autocrypt.Presentation.State.MapDataState
import yunho.app.autocrypt.Presentation.ViewModel.MapViewModel
import yunho.app.autocrypt.databinding.ActivityMapBinding

class MapActivity : BaseActivity<BaseViewModel>(),OnMapReadyCallback, Overlay.OnClickListener {
    private lateinit var binding: ActivityMapBinding
    private val adapter = CenterAdapter()
    override val viewModel: MapViewModel by viewModel()
    private lateinit var naverMap: NaverMap
    private lateinit var Centers : List<CenterEntity>
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
        binding.mapView.getMapAsync(this)
    }

    private fun handleError() {

    }

    private fun handleSuccess(state: MapDataState.success) {
        Centers = state.LocalData
    }

    private fun handleLoading() {

    }

    private fun initViews() = with(binding){
        centerList.adapter = adapter
        centerList.layoutManager = LinearLayoutManager(this@MapActivity)
        CenterInfoView.visibility = View.GONE
        //TODO 지도초기화
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map
        naverMap.maxZoom = 18.0
        naverMap.minZoom = 10.0

        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.497885, 127.027512))
        naverMap.moveCamera(cameraUpdate)

        val uiSetting = naverMap.uiSettings
        uiSetting.isLocationButtonEnabled = false
        binding.currentLocationButton.map = naverMap
        updateMarker(Centers)

    }

    override fun onClick(marker: Overlay): Boolean {
        val selectedCenter = Centers.firstOrNull{
            it.id == marker.tag
        }
        Log.e("Touch","${selectedCenter?.centerName}")
        return true
    }

    private fun updateMarker(Centers: List<CenterEntity>) {
        Centers.forEach { Center ->
            val marker = Marker()
            marker.position = LatLng(Center.lat.toDouble(), Center.lng.toDouble())
            marker.onClickListener = this
            marker.tag = Center.id
            marker.icon = MarkerIcons.BLUE
            marker.iconTintColor = Color.RED
            marker.map = naverMap
        }
    }
}