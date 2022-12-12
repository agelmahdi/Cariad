package com.agelmahdi.cariad.poi_feature.presentation

import com.agelmahdi.cariad.core.util.Resource
import com.agelmahdi.cariad.poi_feature.data.remote.POIsAPI
import com.agelmahdi.cariad.poi_feature.domain.repository.FakePOIRepository
import com.agelmahdi.cariad.poi_feature.domain.use_case.FakePoiUseCase
import com.agelmahdi.cariad.poi_feature.util.utils
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class POIsViewModelTest {

    @Mock
    lateinit var poisAPI: POIsAPI

    @InjectMocks
    lateinit var fakePOIRepository: FakePOIRepository

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    private lateinit var fakePoiUseCase: FakePoiUseCase
    private lateinit var viewModel: POIsViewModel

    @Before
    fun setUp() {
        fakePoiUseCase = FakePoiUseCase()
        viewModel = POIsViewModel(fakePoiUseCase)
    }

    @Test
    fun `for success resource, data must be available`() = runTest {
        Mockito.`when`(poisAPI.getPOIsInfo()).thenReturn(utils.mockPOIResponse())
        val result = fakePOIRepository.getPOIsData().body()?.map { it.toPOIs() }
        fakePoiUseCase.emit(Resource.Success(result))

        assertThat(viewModel.stateFlow.value.data).isNotNull()
        assertThat(viewModel.stateFlow.value.hasError).isFalse()
        assertThat(viewModel.stateFlow.value.isLoading).isFalse()

    }

    @Test
    fun `for loading resource, data should be null && isLoading should be true`() = runTest {
        fakePoiUseCase.emit(Resource.Loading())
        assertThat(viewModel.stateFlow.value.isLoading).isTrue()
        assertThat(viewModel.stateFlow.value.data).isNull()
        assertThat(viewModel.stateFlow.value.hasError).isFalse()
    }

    @Test
    fun `for error resource, data should be null && hasError should be true && errorMessage should be Error`() =
        runTest {
            fakePoiUseCase.emit(Resource.Error(message = "Error"))
            assertThat(viewModel.stateFlow.value.data).isNull()
            assertThat(viewModel.stateFlow.value.hasError).isEqualTo(true)
            assertThat(viewModel.stateFlow.value.errorMessage).isEqualTo("Error")
        }
}