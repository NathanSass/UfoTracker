package com.ufos.ufotracker.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufos.ufotracker.MainActivity
import com.ufos.ufotracker.R
import com.ufos.ufotracker.SightingData
import com.ufos.ufotracker.UfoModulesViewModel
import com.ufos.ufotracker.UfoType
import com.ufos.ufotracker.Util
import com.ufos.ufotracker.databinding.FragmentMainBinding
import com.ufos.ufotracker.databinding.SightningRowItemBinding

/**
 * A placeholder fragment containing a simple view.
 */
class UfoModulesFragment : Fragment() {

    companion object {
        private const val ARG_SIGHTINGS_LIST = "key_sightings_list"

        @JvmStatic
        fun newInstance(sightingTypes: List<String>): UfoModulesFragment {
            return UfoModulesFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(ARG_SIGHTINGS_LIST, ArrayList(sightingTypes))
                }
            }
        }
    }

    private val viewModel: UfoModulesViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sightingTypes = arguments?.getStringArrayList(ARG_SIGHTINGS_LIST)?.toList() ?: emptyList()
        viewModel.sightingTypes = sightingTypes
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var sightingAdapter: SightingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sightingAdapter = SightingAdapter(
            onItemRemoved = { position, item ->
                Log.v("NATHAN", "removed")
            }
        )

        binding.recyclerView.apply {
            adapter = sightingAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(AlienSightingsDecorator(context))
        }

        viewModel.subscribeToData()

        viewModel.sightingsLiveData.observe(viewLifecycleOwner, {
            sightingAdapter.items = it
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).binding.addButton.setOnClickListener {
            viewModel.addNewRecord()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class SightingsVH(binding: SightningRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val sightingUi = binding.sightingRowItem
}

class SightingAdapter(
    private val onItemRemoved: (Int, SightingData) -> Unit
) : RecyclerView.Adapter<SightingsVH>() {
    var items: List<SightingData> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged() // TODO: use better diff strategy
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SightingsVH {
        val binding = SightningRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SightingsVH((binding))
    }

    override fun onBindViewHolder(holder: SightingsVH, position: Int) {
        val data = items[position]
        holder.sightingUi.apply {
            val timeDisplayStr = Util.getDateString(data.timestamp)
            setTitle(timeDisplayStr)
            val type = UfoType.values().first { it.type == data.type }
            setImage(type)

            val subtitleText = "${data.speedKnots} knots · ${type.displayString}"
            setSubtitle(subtitleText)

            setOnDeleteButtonListener {
                onItemRemoved(position, data)
            }
        }
    }

    override fun getItemCount() = items.size
}

class AlienSightingsDecorator(context: Context) : DividerItemDecoration(context, VERTICAL) {
    init {
        setDrawable(ContextCompat.getDrawable(context, R.drawable.divider_line)!!)
    }
}

