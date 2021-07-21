package org.wit.ancuram.activities.animal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.ancuram.R
import org.wit.ancuram.models.animal.Sighting

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,  GoogleMap.OnMarkerDragListener, GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap
    var sighting = Sighting()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        sighting = intent.extras?.getParcelable<Sighting>("sighting")!!
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setOnMarkerDragListener(this)
        map.setOnMarkerClickListener(this)
        val loc = LatLng(sighting.lat, sighting.lng)
        val options = MarkerOptions()
            .title("Sighting")
            .snippet("GPS : " + loc.toString())
            .draggable(true)
            .position(loc)
        map.addMarker(options)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, sighting.zoom))
    }

    override fun onMarkerDragStart(marker: Marker) {
    }

    override fun onMarkerDrag(marker: Marker) {
    }

    override fun onMarkerDragEnd(marker: Marker) {
        sighting.lat = marker.position.latitude
        sighting.lng = marker.position.longitude
        sighting.zoom = map.cameraPosition.zoom
    }

    override fun onBackPressed() {
        val resultIntent = Intent()
        resultIntent.putExtra("sighting", sighting)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
        super.onBackPressed()
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val loc = LatLng(sighting.lat, sighting.lng)
        marker.setSnippet("GPS : " + loc.toString())
        return false
    }
}