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
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import com.kakao.sdk.common.util.Utility


class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private lateinit var mapView: MapView
    private lateinit var kakaoMap: KakaoMap



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
                    401 -> "API 인증에 실패"
                    499 -> "서버 통신 실패"
                    else -> "알 수 없는 오류"
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

    private fun setMarker() {
        val latitude = 37.402005
        val longitude = 127.108621
        var styles = LabelStyles.from(LabelStyle.from(R.drawable.ic_marker).setZoomLevel(5))
        styles = kakaoMap.labelManager!!.addLabelStyles(styles!!)
        kakaoMap.labelManager!!.layer!!.addLabel(
            LabelOptions.from(LatLng.from(latitude, longitude))
                .setStyles(styles)
        )
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
