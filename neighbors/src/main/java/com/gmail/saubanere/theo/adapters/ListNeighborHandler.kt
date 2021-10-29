package com.gmail.saubanere.theo.adapters

import com.gmail.saubanere.theo.neightbors.models.Neighbor

interface ListNeighborHandler {
    fun onDeleteNeighbor(neighbor: Neighbor)
}
