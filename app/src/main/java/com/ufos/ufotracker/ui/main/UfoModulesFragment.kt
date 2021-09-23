package com.ufos.ufotracker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufos.ufotracker.SightingData
import com.ufos.ufotracker.UfoType
import com.ufos.ufotracker.databinding.FragmentMainBinding
import com.ufos.ufotracker.databinding.SightningRowItemBinding

/**
 * A placeholder fragment containing a simple view.
 */
class UfoModulesFragment : Fragment() {

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): UfoModulesFragment {
            return UfoModulesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    private val viewModel: UfoModulesViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root

        //        val textView: TextView = binding.sectionLabel
        //        viewModel.text.observe(viewLifecycleOwner, {
        //            textView.text = it
        //        })
        return root
    }

    private lateinit var sightingAdapter: SightingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sightingAdapter = SightingAdapter()
        binding.recyclerView.apply {
            adapter = sightingAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val datas = "askdhsakdukhbjhwbewewesdfsdfsdffsdfsdf".split("").map {
            val speed = (0..100).random()
            SightingData(it, speed, UfoType.Blob)
        }
        sightingAdapter.items = datas
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class SightingsVH(binding: SightningRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val sightingUi = binding.sightingRowItem
}

class SightingAdapter() : RecyclerView.Adapter<SightingsVH>() {
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
        holder.sightingUi.setTitle("$position ${data.timestamp}")
    }

    override fun getItemCount() = items.size
}

