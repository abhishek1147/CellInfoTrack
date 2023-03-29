package com.zonedevs.cellinfotracker.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zonedevs.cellinfotracker.DisplayAdapter
import com.zonedevs.cellinfotracker.R
import com.zonedevs.cellinfotracker.models.DisplayModel

class MonitorFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    private var dataList: ArrayList<DisplayModel> = arrayListOf()
    lateinit var displayAdapter: DisplayAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.monitor_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateView(){
        dataList = arrayListOf()
        dataList.apply {
            add(DisplayModel("Timestamp","2023-01-24 22:42:56.973"))
            add(DisplayModel("Network Type : 4G_LTE","RSRP: -112, RSRQ: -12, SINR: -1, PCI: 234\nLTE band: 3, Bandwidth: 44"))
            add(
                DisplayModel("Network Type : 5G_NR_NSA","ss_RSRP: -112, ss_RSRQ: -12, ss_SINR: -1\n" +
                    "csi_RSRP: -99, csi_RSRQ: -10, csi_SINR: -3\nPCI: 234, NR band: n1")
            )
            add(DisplayModel("Location Info","Longitude: -23.23455, Latitude: -22.43456\nAccuracy: 15, Altitude: 10"))
            add(
                DisplayModel("Cell Info","Network operator name : JIO 4G\nSIM operator name : AT&T\nSIM carrier ID name : AT&T\n" +
                    "SIM carrier ID based on MCCMNC : 1187\nServing MNC : 54, Serving MCC : 405\n" +
                    "Roaming Status : Home\nIP Address : 10.185.60.133")
            )
            add(
                DisplayModel("Device Info","OS : Android, OS Version : 13\nManufacturer : Samsung\n" +
                    "Model No.: SM-S901U\n" +
                    "IMEI : 8409589753908\nICCID : jk389374kfjn34\nSerial No.: 34bh5jh54hb54jh5\nPhone No.: 89437543985")
            )
        }
        displayAdapter.setItems(dataList)
        displayAdapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        displayAdapter = DisplayAdapter(dataList)

        updateView()

        recyclerView.adapter = displayAdapter
        displayAdapter.notifyDataSetChanged()
    }
}