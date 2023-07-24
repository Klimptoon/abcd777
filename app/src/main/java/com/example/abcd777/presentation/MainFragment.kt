package com.example.abcd777.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.abcd777.R
import com.example.abcd777.app.App
import com.example.abcd777.databinding.FragmentMainBinding
import javax.inject.Inject


class MainFragment : Fragment() {

    @Inject
    lateinit var vmFactory: MainViewModelFactory
    lateinit var viewModel: MainFragmentViewModel

    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, vmFactory)[MainFragmentViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }


    override fun onResume() {
        super.onResume()
        val purchaseTypes = resources.getStringArray(R.array.cities)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, purchaseTypes)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.autoCompleteTextViewMain.setAdapter(arrayAdapter)
    }

    private fun init() {
        viewModel.getDefaultCurrency()                                //Вызываем для первого значения на экране(с дефолтным городом)
        viewModelObserve()
        loadingObserve()
        isConnectedObserve()
        startMainSpinner()
    }

    private fun viewModelObserve() {                                  //Обсервер данных из запроса
        viewModel.currency.observe(viewLifecycleOwner) {
            with(binding) {
                it?.let {
                    tvCurrencyBuyEur.text = it.EUR_in
                    tvCurrencySellEur.text = it.EUR_out
                    tvCurrencyBuyRub.text = it.RUB_in
                    tvCurrencySellRub.text = it.RUB_out
                    tvCurrencyBuyUsd.text = it.USD_in
                    tvCurrencySellUsd.text = it.USD_out
                }
            }
        }
    }

    private fun loadingObserve() {
        viewModel.loading.observe(viewLifecycleOwner) {                 //Обсервер загрузки
            with(binding) {
                when (it) {
                    true -> {
                        cardEur.visibility = View.INVISIBLE
                        cardUsd.visibility = View.INVISIBLE
                        cardRub.visibility = View.INVISIBLE
                        progressCircular.visibility = View.VISIBLE
                        progressText.visibility = View.VISIBLE
                    }
                    false -> {
                        progressCircular.visibility = View.INVISIBLE
                        progressText.visibility = View.INVISIBLE
                        cardEur.visibility = View.VISIBLE
                        cardUsd.visibility = View.VISIBLE
                        cardRub.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun isConnectedObserve() {                                  //Обсервер подключения
        viewModel.isConnected.observe(viewLifecycleOwner) {
            with(binding) {
                when (it) {
                    true -> {
                        errorText.visibility = View.INVISIBLE
                        progressCircular.visibility = View.INVISIBLE
                        progressText.visibility = View.INVISIBLE
                    }
                    false -> {
                        errorText.visibility = View.VISIBLE
                        cardEur.visibility = View.INVISIBLE
                        cardUsd.visibility = View.INVISIBLE
                        cardRub.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }


    private fun startMainSpinner() {                                   //Функция для выбора города
        val cities = resources.getStringArray(R.array.cities)

        binding.autoCompleteTextViewMain.onItemClickListener =
            object : AdapterView.OnItemClickListener {
                override fun onItemClick(
                    adapterView: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    item_id: Long
                ) {
                    viewModel.position = position
                    viewModel.city.value = binding.autoCompleteTextViewMain.text.toString()
                    viewModel.getCurrency(cities[position])
                }
            }
    }

}
