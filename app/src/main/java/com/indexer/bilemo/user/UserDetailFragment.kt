package com.indexer.bilemo.user

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fondesa.kpermissions.PermissionStatus
import com.fondesa.kpermissions.allGranted
import com.fondesa.kpermissions.anyPermanentlyDenied
import com.fondesa.kpermissions.anyShouldShowRationale
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.request.PermissionRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.indexer.bilemo.R
import com.indexer.bilemo.databinding.FragmentUserDetailBinding
import com.indexer.bilemo.user.enitity.UserResponse
import com.indexer.bilemo.user.viewmodel.UserDetailViewModel
import com.indexer.bilemo.utils.showPermanentlyDeniedDialog
import com.indexer.bilemo.utils.showRationaleDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserDetailFragment : Fragment(), PermissionRequest.Listener {

    private lateinit var userDetailBinding: FragmentUserDetailBinding

    private lateinit var googleMap: GoogleMap

    private val userDetailViewModel: UserDetailViewModel by viewModels()


    private val permissionRequest by lazy {
        permissionsBuilder(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ).build()
    }


    private lateinit var fusedLocationClient: FusedLocationProviderClient


    private val callback = OnMapReadyCallback { googleMap ->
        this.googleMap = googleMap
        googleMap.uiSettings.isScrollGesturesEnabledDuringRotateOrZoom = false
        showOnMap(null)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userDetailBinding = FragmentUserDetailBinding.inflate(layoutInflater)
        val toolbar: Toolbar = userDetailBinding.toolbarUserDetail
        //for crate home button
        val activity = activity as AppCompatActivity?
        activity?.setSupportActionBar(toolbar)
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return userDetailBinding.root
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment?
        context?.let {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(it)
        }
        userDetailBinding.nestedView.isNestedScrollingEnabled = true
        mapFragment?.getMapAsync(callback)
        permissionRequest.addListener(this)
        permissionRequest.send()
        val userId: Int = arguments?.getInt(getString(R.string.userId)) ?: 0
        userDetailViewModel.getUserById(userId)
        userDetailViewModel.getUserById.observe(viewLifecycleOwner, {
            val location = Location("default")
            location.latitude = it.address.geo.lat.toDouble()
            location.longitude = it.address.geo.lng.toDouble()
            setUserPersonalInformation(it, userDetailBinding)
            try {
                showUserLocationMap(location = location, it.name, googleMap)
            } catch (e: UninitializedPropertyAccessException) {
                e.printStackTrace()
            }
        })

        userDetailBinding.toolbarUserDetail.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun setUserPersonalInformation(it: UserResponse, binding: FragmentUserDetailBinding) {
        val context = binding.root.context
        val stringBuilder = StringBuilder()
        stringBuilder.append(context.getString(R.string.user_name)).append(it.username)
        binding.userName.text = stringBuilder.toString()
        val addressStringBuilder = StringBuilder()
        val addressValue =
            it.address.suite + " , " + it.address.street + " , " + it.address.city + " , " + it.address.zipcode
        binding.userAddress.text =
            addressStringBuilder.append(binding.userAddress.context.getString(R.string.address))
                .append(addressValue)
        val websiteStringBuilder = StringBuilder()

        binding.userWebsite.text =
            websiteStringBuilder.append(context.getString(R.string.website)).append(it.website)
        val emailStringBuilder = StringBuilder()
        binding.userEmail.text =
            emailStringBuilder.append(context.getString(R.string.email)).append(it.email)
        val companyStringBuilder = StringBuilder()
        userDetailBinding.userCompanyName.text =
            companyStringBuilder.append(getString(R.string.company)).append(it.company.companyName)

    }


    private fun showUserLocationMap(location: Location?, string: String, googleMap: GoogleMap?) {
        when {
            location != null -> {
                googleMap?.addMarker(
                    MarkerOptions().position(
                        LatLng(location.latitude, location.longitude)
                    ).title(string)
                )
            }
            else -> {
                val singapore = LatLng(1.352083, 103.819839)
                googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore, 3f))
            }
        }
    }

    private fun showOnMap(location: Location?) {
        when {
            location != null -> {
                googleMap.addMarker(
                    MarkerOptions().position(
                        LatLng(location.latitude, location.longitude)
                    ).title(getString(R.string.user_current_location_title))
                )
                googleMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(location.latitude, location.longitude),
                        3f
                    )
                )
            }
            else -> {
                val singapore = LatLng(1.352083, 103.819839)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore, 3f))
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onPermissionsResult(result: List<PermissionStatus>) {
        when {
            result.allGranted() -> {
                fusedLocationClient.lastLocation.addOnSuccessListener {
                    showOnMap(it)
                }
            }
            result.anyShouldShowRationale() -> {
                context?.showRationaleDialog(result, permissionRequest)
            }
            result.anyPermanentlyDenied() -> {
                context?.showPermanentlyDeniedDialog(result)
            }
        }
    }


}