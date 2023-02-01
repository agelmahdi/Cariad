package com.agelmahdi.cariad.poi_feature.presentation

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.agelmahdi.cariad.databinding.FragmentMapsBinding
import com.agelmahdi.cariad.poi_feature.domain.model.POI
import com.agelmahdi.cariad.poi_feature.domain.model.POIsState
import com.agelmahdi.cariad.poi_feature.util.Constance
import com.agelmahdi.cariad.poi_feature.util.Tags
import com.agelmahdi.cariad.poi_feature.util.Utils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MapsFragment : Fragment(), GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private var _binding: FragmentMapsBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: POIsViewModel
    private lateinit var map: GoogleMap

    companion object {
        fun newInstance() = MapsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(POIsViewModel::class.java)
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun collectFlowStream() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collectLatest { state ->
                    updateMapMarkers(state)
                }
            }
        }
    }

    private fun updateMapMarkers(state: POIsState) {
        map.clear()
        state.data?.forEach {
            Utils.createMarker(
                map,
                it.address?.latitude!!,
                it.address.longitude!!,
                it.operatorInfo?.title,
                it.numberOfPoints?.toString() + ", " + it.address.title
            )?.apply {
                tag = it
            }
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        if (marker.tag == null) return false

        val poi = marker.tag as POI

        MarkerDetailsDialog(poi).show(
            requireActivity().supportFragmentManager, Tags.MARKER_FLAG
        )
        return true
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }


    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.mapView.onDestroy()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val initialLocation = LatLng(Constance.LATITUDE, Constance.LONGITUDE)
        googleMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                initialLocation, Constance.CAMERA_ZOOM
            )
        )
        googleMap.setOnMarkerClickListener(this)

        collectFlowStream()
    }
}