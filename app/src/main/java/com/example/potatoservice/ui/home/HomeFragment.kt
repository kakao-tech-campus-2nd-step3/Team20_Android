package com.example.potatoservice.ui.home

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.potatoservice.databinding.FragmentHomeBinding
import com.example.potatoservice.ui.share.AdapterCallback
import com.example.potatoservice.ui.share.SpinnerHintAdapter
import com.example.potatoservice.ui.share.Volunteer

class HomeFragment : Fragment(), AdapterCallback {

	private lateinit var binding: FragmentHomeBinding
	private lateinit var searchResultAdapter: SearchResultAdapter
	private lateinit var homeViewModel: HomeViewModel
	private var selectedSpinnerNum = 0
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

		binding = FragmentHomeBinding.inflate(inflater, container, false)
		binding.viewModel = homeViewModel
		setRecyclerAdapter()

		setSpinner()
		return binding.root
	}

	private fun setRecyclerAdapter(){
		binding.searchResultRecyclerView.layoutManager = LinearLayoutManager(activity)
		searchResultAdapter = SearchResultAdapter(this)
		homeViewModel.serviceList.observe(viewLifecycleOwner, Observer { service ->
			searchResultAdapter.submitList(service)
			binding.searchResultRecyclerView.adapter = searchResultAdapter
		})
	}

	private fun setSpinner(){
		binding.majorRegionalCategories.adapter = SpinnerHintAdapter(
			requireContext(), R.layout.simple_spinner_dropdown_item, homeViewModel.majorRegoinList)
		binding.majorRegionalCategories.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View?,
				position: Int,
				id: Long
			) {
				if(position == 0 && selectedSpinnerNum != 0){
					binding.minorRegionalCategories.adapter = SpinnerHintAdapter(
						requireContext(), R.layout.simple_spinner_dropdown_item, homeViewModel.minorRegoinList.value!![selectedSpinnerNum])
				}
				else{
					selectedSpinnerNum = position
					binding.minorRegionalCategories.adapter = SpinnerHintAdapter(
						requireContext(), R.layout.simple_spinner_dropdown_item, homeViewModel.minorRegoinList.value!![selectedSpinnerNum])
				}
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {

			}

		}
		binding.minorRegionalCategories.adapter = SpinnerHintAdapter(
			requireContext(), R.layout.simple_spinner_dropdown_item, homeViewModel.minorRegoinList.value!![selectedSpinnerNum])
		binding.minorRegionalCategories.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View?,
				position: Int,
				id: Long
			) {

			}

			override fun onNothingSelected(parent: AdapterView<*>?) {

			}

		}
		binding.volunteerActivitiesCategories.adapter = SpinnerHintAdapter(
			requireContext(), R.layout.simple_spinner_dropdown_item, homeViewModel.volunteerList)
		binding.volunteerActivitiesCategories.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View?,
				position: Int,
				id: Long
			) {

			}

			override fun onNothingSelected(parent: AdapterView<*>?) {

			}

		}
	}

	override fun onClicked(volunteer: Volunteer) {
		//봉사 카드 클릭 시 상세 페이지로 이동 기능 추가 예정.
	}

}