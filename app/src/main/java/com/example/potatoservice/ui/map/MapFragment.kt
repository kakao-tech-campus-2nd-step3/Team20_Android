package com.example.potatoservice.ui.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import com.example.potatoservice.R
import com.example.potatoservice.databinding.FragmentMapBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.KakaoMapSdk
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapAuthException
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.Label
import com.kakao.vectormap.label.LabelLayer
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import com.kakao.sdk.common.util.Utility


class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private lateinit var mapView: MapView
    private lateinit var kakaoMap: KakaoMap


    private var isCardViewVisible = false // 카드뷰의 현재 상태를 저장



    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var curLat: Double = 0.0
    private var curLon: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //카카오 키해시 값 로그에 띄우기
        var keyHash = Utility.getKeyHash(requireContext())
        Log.d("testt", keyHash)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initKakaoMap()
        initMapView()

        // 버튼 클릭 이벤트 처리 (대신 XML에서 onClick 속성을 사용할 수도 있습니다.)
        binding.buttonCurrentLocation.setOnClickListener {
            moveToCurrentLocation()
        }

    }

    override fun onResume() {
        super.onResume()
        Log.d("testt", "onResume")
        mapView.resume()
    }

    override fun onPause() {
        super.onPause()
        Log.d("testt", "onPause")
        mapView.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // 여기서 부터 클래스화 시킬거 ->> 리팩토링
    private fun initKakaoMap() {
        KakaoMapSdk.init(requireContext(), getString(R.string.kakao_api_key))
    }

    private fun initMapView() {
        mapView = binding.mapView
        mapView.start(object : MapLifeCycleCallback() {
            override fun onMapDestroy() {
                Log.d("testt", "onMapDestroy")
            }

            override fun onMapError(error: Exception) {
                val errorMessage = when ((error as? MapAuthException)?.errorCode) {
                    401 -> getString(R.string.error_auth_failure)
                    499 -> getString(R.string.error_server_communication_failure)
                    else -> getString(R.string.error_unknown)
                }
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        }, object : KakaoMapReadyCallback() {
            override fun onMapReady(kakaoMap: KakaoMap) {
                this@MapFragment.kakaoMap = kakaoMap
                setInitialCameraPosition()
                setMarker()


            }
        })
    }

    private fun setInitialCameraPosition() {
        currentLocation { latLng ->
            val cameraUpdate = CameraUpdateFactory.newCenterPosition(latLng)
            kakaoMap.moveCamera(cameraUpdate)
        }
    }





    // MarkerData 클래스 정의 (위치 및 정보를 담고 있음)
    data class MarkerData(val latLng: LatLng, val title: String, val address: String, val description: String)

    private fun setMarker() {
        // 10개의 임의의 좌표와 정보를 생성
        val markerDataList = listOf(
            MarkerData(LatLng.from(37.8805, 127.7278), "봉사 활동 1", "춘천시 1", "활동 내용 1"),
            MarkerData(LatLng.from(37.8803, 127.7282), "봉사 활동 2", "춘천시 2", "활동 내용 2"),
            MarkerData(LatLng.from(37.8807, 127.7280), "봉사 활동 3", "춘천시 3", "활동 내용 3"),
            MarkerData(LatLng.from(37.8806, 127.7276), "봉사 활동 4", "춘천시 4", "활동 내용 4"),
            MarkerData(LatLng.from(37.8804, 127.7274), "봉사 활동 5", "춘천시 5", "활동 내용 5"),
            MarkerData(LatLng.from(37.8809, 127.7283), "봉사 활동 6", "춘천시 6", "활동 내용 6"),
            MarkerData(LatLng.from(37.8802, 127.7279), "봉사 활동 7", "춘천시 7", "활동 내용 7"),
            MarkerData(LatLng.from(37.8808, 127.7277), "봉사 활동 8", "춘천시 8", "활동 내용 8"),
            MarkerData(LatLng.from(37.8801, 127.7275), "봉사 활동 9", "춘천시 9", "활동 내용 9"),
            MarkerData(LatLng.from(37.8800, 127.7281), "봉사 활동 10", "춘천시 10", "활동 내용 10")
        )

        // 각 데이터를 지도에 표시하고 클릭 리스너를 설정
        for (markerData in markerDataList) {
            val styles = LabelStyles.from(LabelStyle.from(R.drawable.ic_map_marker).setZoomLevel(5))
            val labelOptions = LabelOptions.from(markerData.latLng).setStyles(styles)

            // 라벨 추가
            val label = kakaoMap.labelManager!!.layer!!.addLabel(labelOptions)

            // 클릭한 라벨을 식별할 수 있도록 label에 tag를 추가해 markerData를 연결
            label.tag = markerData
        }

        // 카카오맵의 라벨 클릭 리스너 설정
        kakaoMap.setOnLabelClickListener(object : KakaoMap.OnLabelClickListener {
            override fun onLabelClicked(kakaoMap: KakaoMap, layer: LabelLayer, clickedLabel: Label) {
                // 클릭된 라벨의 tag에서 MarkerData를 가져와 카드뷰를 업데이트
                val markerData = clickedLabel.tag as MarkerData
                updateCardView(markerData)
                showCardView() // 카드뷰 표시
            }
        })
    }

    // 카드뷰에 정보를 업데이트하는 함수
    private fun updateCardView(markerData: MarkerData) {
        binding.titleText.text = markerData.title
        binding.descriptionText.text = "${markerData.address} \n${markerData.description}"
    }

    // CardView를 보이게 설정
    private fun showCardView() {
        binding.infoCardView.visibility = View.VISIBLE
        isCardViewVisible = true
    }

    // CardView를 숨김
    private fun hideCardView() {
        binding.infoCardView.visibility = View.GONE
        isCardViewVisible = false
    }







    private fun currentLocation(onLocationRetrieved: (LatLng) -> Unit) {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(requireContext(), "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                curLat = it.latitude
                curLon = it.longitude
                val latLng = LatLng.from(curLat, curLon)
                Log.d("CurrentLocation", "위도: $curLat, 경도: $curLon")
                onLocationRetrieved(latLng) // 위치를 찾았을 때 콜백 호출
            } ?: run {
                Toast.makeText(requireContext(), "위치를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 버튼 클릭 시 현재 위치로 이동하는 함수
    private fun moveToCurrentLocation() {
        currentLocation { latLng ->
            val cameraUpdate = CameraUpdateFactory.newCenterPosition(latLng)
            kakaoMap.moveCamera(cameraUpdate) // 카메라를 현재 위치로 이동
            Toast.makeText(requireContext(), "현재 위치로 이동합니다.", Toast.LENGTH_SHORT).show()
        }
    }


}
