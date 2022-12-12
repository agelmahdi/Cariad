package com.agelmahdi.cariad.poi_feature.presentation

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.agelmahdi.cariad.R
import com.agelmahdi.cariad.databinding.MarkerDetailsBinding
import com.agelmahdi.cariad.poi_feature.domain.model.POI
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MarkerDetailsDialog(
    private val poiInfo: POI
) : DialogFragment() {

    private lateinit var binding: MarkerDetailsBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = MarkerDetailsBinding.inflate(layoutInflater)

        val view = binding.root

        binding.tvTitle.text = poiInfo.operatorInfo?.title
        binding.tvAddress.text = poiInfo.address?.title
        binding.tvPoints.text = poiInfo.numberOfPoints.toString()

        return MaterialAlertDialogBuilder(requireContext()).setTitle(getString(R.string.stations_details))
            .setNegativeButton(getString(R.string.cancel)) { dialogInterface, _ ->
                dialogInterface.cancel()
            }.setView(view).create()
    }


}