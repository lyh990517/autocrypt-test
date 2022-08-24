package yunho.app.autocrypt.Presentation.View

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import org.koin.androidx.viewmodel.ext.android.viewModel
import yunho.app.autocrypt.Data.Entity.CenterEntity
import yunho.app.autocrypt.Presentation.BaseActivity
import yunho.app.autocrypt.Presentation.BaseViewModel
import yunho.app.autocrypt.Presentation.State.MapDataState
import yunho.app.autocrypt.Presentation.ViewModel.MapViewModel
import yunho.app.autocrypt.databinding.ActivityMapBinding
import java.util.*

class MapActivity : BaseActivity<BaseViewModel>(), OnMapReadyCallback, Overlay.OnClickListener {
    private lateinit var binding: ActivityMapBinding
    override val viewModel: MapViewModel by viewModel()
    private lateinit var naverMap: NaverMap
    private lateinit var Centers: List<CenterEntity>
    private lateinit var locationSource: FusedLocationSource
    private var isClicked = false
    private val IDqueue: Queue<Int> = LinkedList()
    override fun observeData() {
        viewModel.MapLiveData.observe(this) { State ->
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
        with(state.CenterInfo) {
            binding.address.text = address
            binding.centerName.text = centerName
            binding.facilityName.text = facilityName
            binding.phoneNumber.text = phoneNumber
            binding.updatedAt.text = updatedAt
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mapView.getMapAsync(this)
        locationSource =
            FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

    }

    private fun handleError() {

    }

    private fun handleSuccess(state: MapDataState.success) {
        Centers = state.LocalData
    }

    private fun handleLoading() {

    }

    private fun initViews() = with(binding) {

    }

    private fun goLatLng(lat: Double, lng: Double){
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(lat, lng)).animate(CameraAnimation.Easing)
        naverMap.moveCamera(cameraUpdate)
    }
    override fun onMapReady(map: NaverMap) {
        naverMap = map
        naverMap.maxZoom = 20.0
        naverMap.minZoom = 10.0
        goLatLng(37.5301, 126.9263)
        val uiSetting = naverMap.uiSettings
        uiSetting.isLocationButtonEnabled = false
        binding.currentLocationButton.map = naverMap
        naverMap.locationSource = locationSource
        updateMarker(Centers)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions,
                grantResults
            )
        ) {
            if (!locationSource.isActivated) {
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onClick(marker: Overlay): Boolean {
        val selectedCenter = Centers.first {
            it.id == marker.tag
        }
        goLatLng(selectedCenter.lat.toDouble(),selectedCenter.lng.toDouble())
        if (IDqueue.isEmpty()) {
            IDqueue.add(selectedCenter.id)
        }
        viewModel.MapLiveData.postValue(MapDataState.successOne(selectedCenter))
        if (!isClicked) {
            binding.MapLayout.transitionToEnd()
            isClicked = true
        } else {
            if (IDqueue.peek() == selectedCenter.id) {
                IDqueue.poll()
                binding.MapLayout.transitionToStart()
                isClicked = false
            } else {
                IDqueue.poll()
                IDqueue.add(selectedCenter.id)
            }
        }
        return true
    }
    //test
    private fun updateMarker(Centers: List<CenterEntity>) {
        Centers.forEach { Center ->
            val marker = Marker()
            marker.position = LatLng(Center.lat.toDouble(), Center.lng.toDouble())
            marker.onClickListener = this
            if(Center.centerType == "지역"){
                marker.icon = MarkerIcons.GREEN
                marker.iconTintColor = Color.BLUE
            }else{
                //중앙권역
                marker.icon = MarkerIcons.RED
                marker.iconTintColor = Color.BLACK
            }
            marker.tag = Center.id
            marker.map = naverMap
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

}