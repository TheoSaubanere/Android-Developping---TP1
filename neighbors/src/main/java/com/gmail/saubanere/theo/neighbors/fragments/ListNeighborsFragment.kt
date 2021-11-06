package com.gmail.saubanere.theo.neighbors.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.saubanere.theo.adapters.ListNeighborHandler
import com.gmail.saubanere.theo.adapters.ListNeighborsAdapter
import com.gmail.saubanere.theo.data.NeighborRepository
import com.gmail.saubanere.theo.data.service.DummyNeighborApiService
import com.gmail.saubanere.theo.neighbors.fragments.ui.main.NavigationListener
import com.gmail.saubanere.theo.neightbors.models.Neighbor
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListNeighborsFragment : Fragment(), ListNeighborHandler {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addNeighbor: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_neighbors_fragment, container, false)
        recyclerView = view.findViewById(R.id.neighbors_list)
        addNeighbor = view.findViewById(R.id.addNeighbor)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        addNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.showFragment(AddNeighbourFragment())
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshList()
    }

    override fun onDeleteNeighbor(neighbor: Neighbor) {
        activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.dialog_title)
            builder.apply {
                setPositiveButton(R.string.oui) { dialog, _ ->
                    val dummyNeighborApiService = DummyNeighborApiService()
                    dummyNeighborApiService.deleteNeighbour(neighbor)
                    dialog.dismiss()
                    NeighborRepository.getInstance().deleteNeighbor(neighbor)
                    refreshList()
                }
                setNegativeButton(R.string.non) { dialog, _ ->
                    dialog.dismiss()
                }
            }
            builder.create().show()
        }
    }

    private fun refreshList() {
        val neighbors = NeighborRepository.getInstance().getNeighbours()
        val adapter = ListNeighborsAdapter(neighbors, this)
        recyclerView.adapter = adapter
    }
}
