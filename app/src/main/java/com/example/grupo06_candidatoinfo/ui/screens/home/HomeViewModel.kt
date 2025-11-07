// ui/screens/home/HomeViewModel.kt
package com.example.grupo06_candidatoinfo.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.grupo06_candidatoinfo.data.repository.CandidateRepository
import com.example.grupo06_candidatoinfo.model.Candidate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// Estado de la UI
data class HomeUiState(
    val isLoading: Boolean = false,
    val candidates: List<Candidate> = emptyList(),
    val error: String? = null
)

class HomeViewModel(
    private val repository: CandidateRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadCandidates()
    }

    private fun loadCandidates() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val fetchedCandidates = repository.getCandidates()

            if (fetchedCandidates.isNotEmpty()) {
                _uiState.update { it.copy(isLoading = false, candidates = fetchedCandidates) }
            } else {
                _uiState.update { it.copy(isLoading = false, error = "No se pudieron cargar los candidatos. Verifique la conexión a la API.") }
            }
        }
    }

    fun getPositions() = repository.getPositions()
}

// **FACTORY PARA CREAR EL VIEWMODEL (Necesario para inyectar el repositorio)**
class HomeViewModelFactory(private val repository: CandidateRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}