package com.agelmahdi.cariad.poi_feature.data.remote.dto

import com.agelmahdi.cariad.poi_feature.domain.model.Address
import com.agelmahdi.cariad.poi_feature.domain.model.Operator
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.mockito.Mockito.*


class POIDTOTest {

    @Test
    fun `for POIs OperatorInfo, IsNotNull should Return POI with operator`() {
        val operatorInfo = mock(OperatorDTO::class.java)
        val addressInfo = mock(AddressDTO::class.java)
        val poiDTO = POIDTO(addressInfo, operatorInfo, 1)

        val poi = poiDTO.toPOIs()

        verify(operatorInfo).toOperatorInfo()
    }

    @Test
    fun `for POIs NumberOfPoints, IsNotNull should return POI with NumberOfPoints`() {
        val poiDTO = POIDTO(
            address = mock(AddressDTO::class.java),
            operatorInfo = mock(OperatorDTO::class.java),
            numberOfPoints = 1
        )

        val poi = poiDTO.toPOIs()
        assertThat(poi.numberOfPoints).isEqualTo(1)
    }

    @Test
    fun `for POIs Address, IsNotNull should return POI with Address`() {
        val addressDTO = mock(AddressDTO::class.java)
        val operatorDTO = mock(OperatorDTO::class.java)
        val address = mock(Address::class.java)
        val operator = mock(Operator::class.java)
        val poiDTO = POIDTO(addressDTO, operatorDTO, 1)

        `when`(addressDTO.toAddress()).thenReturn(address)
        `when`(operatorDTO.toOperatorInfo()).thenReturn(operator)

        val poi = poiDTO.toPOIs()

        assertThat(poi.address).isEqualTo(address)
    }

}