package com.gmail.saubanere.theo.data

import com.gmail.saubanere.theo.data.service.DummyNeighborApiService
import com.gmail.saubanere.theo.data.service.NeighborApiService
import com.gmail.saubanere.theo.neightbors.models.Neighbor

class NeighborRepository {
    private val apiService: NeighborApiService

    init {
        apiService = DummyNeighborApiService()
    }

    fun getNeighbours(): List<Neighbor> = apiService.neighbours
    fun deleteNeighbor(neighbor: Neighbor) = apiService.deleteNeighbour(neighbor)
    fun createNeighbour(neighbor: Neighbor) = apiService.createNeighbour(neighbor)

    fun getIdNewNeighbour(): Long {
        return (getNeighbours().size + 1).toLong()
    }

    companion object {
        private var instance: NeighborRepository? = null
        fun getInstance(): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository()
            }
            return instance!!
        }
    }
}
