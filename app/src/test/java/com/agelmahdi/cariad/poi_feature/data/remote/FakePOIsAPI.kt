package com.agelmahdi.cariad.poi_feature.data.remote

import com.agelmahdi.cariad.poi_feature.data.remote.dto.POIDTO
import com.agelmahdi.cariad.poi_feature.domain.repository.FakePOIRepository
import com.agelmahdi.cariad.poi_feature.util.utils
import com.agelmahdi.cariad.poi_feature.util.utils.MockResponse
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase.fail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class FakePOIsAPI {
    @Mock
    lateinit var poiAPI: POIsAPI

    @InjectMocks
    lateinit var fakePOIRepository: FakePOIRepository

    @Test
    fun `for POI when request is not successful, should throw exception`() = runTest() {
        val response: MockResponse<List<POIDTO>> = MockResponse()
        `when`(poiAPI.getPOIsInfo()).thenReturn(response.mockErrorResponse())

        try {
            fakePOIRepository.getPOIsData()
            fail("Should throw exception")
        } catch (e: Throwable) {
            assertThat(e.message).isEqualTo("Should throw exception")
        }
    }

    @Test
    fun `for POI when request successful, should return list of POIs`() = runTest {

        `when`(poiAPI.getPOIsInfo()).thenReturn(utils.mockPOIResponse())

        assertThat(fakePOIRepository.getPOIsData().body()).isEqualTo(utils.mockPOI())
    }

}