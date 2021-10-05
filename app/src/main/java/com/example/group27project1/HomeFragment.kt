package com.example.group27project1

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.group27project1.api.WeatherResponse
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import java.io.File
import java.util.*
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationServices

import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.tasks.Task


private const val TAG = "HomeFragment"
private const val aKEY_INDEX = "aindex"
private const val bKEY_INDEX = "bindex"
private const val REQUEST_CODE_SAVE = 0
private const val REQUEST_PHOTO = 2



class HomeFragment : Fragment() {

    /**
     * Required interface for hosting activities
     */
    interface Callbacks {
        fun onGameSelected(gameId: UUID)
        fun onDispSelected(gameId: UUID)
    }

    private var callbacks: Callbacks? = null

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val bbViewModel: BasketballViewModel by lazy {
        ViewModelProviders.of(this).get(BasketballViewModel::class.java)
    }

    //private val CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000
    //private var mGoogleApiClient: GoogleApiClient? = null
    //private var mLocationRequest: LocationRequest? = null
    private var currentLatitude = 0.0
    private var currentLongitude = 0.0

    private lateinit var game: Game
    private lateinit var photoFile: File
    private lateinit var photoUri: Uri
    private lateinit var teamAName: EditText

    private lateinit var teamAScoreTextView: TextView
    private lateinit var teamBScoreTextView: TextView
    private lateinit var freeABtn: Button
    private lateinit var freeBBtn: Button
    private lateinit var twoABtn: Button
    private lateinit var twoBBtn: Button
    private lateinit var threeABtn: Button
    private lateinit var threeBBtn: Button
    private lateinit var resetBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var dispBtn: Button

    private lateinit var  rem1ABtn: Button
    private lateinit var rem1BBtn: Button

    private lateinit var photoButton: ImageButton
    private lateinit var photoView: ImageView

    private lateinit var gameWeatherTextView: TextView

    private lateinit var locationCallback: LocationCallback



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        game = Game()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        //val gaeId: UUID = arguments?.getSerializable(ARG_CRIME_ID) as UUID
        //crimeDetailViewModel.loadCrime(crimeId)

        //val flickrLiveData: LiveData<String> = WeatherFetchr().fetchPhotos()
        val flickrLiveData: MutableLiveData<WeatherResponse> = WeatherFetchr().fetchPhotos(currentLatitude, currentLongitude)

        getLastKnownLocation()
        flickrLiveData.observe(
            this,
            Observer { weatherItems ->
                Log.d(TAG, "Response received: $weatherItems")
                Log.d(TAG, currentLatitude.toString())
                Log.d(TAG, currentLongitude.toString())
                gameWeatherTextView.setText( "The weather in " + weatherItems.city.name + " is " + weatherItems.list[1].weItems.temp + "C @ " + weatherItems.list[1].dt_txt)
            })

    }


    fun getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location->
                if (location != null) {
                    currentLatitude = location.latitude
                    currentLongitude = location.longitude
                    Log.d(TAG, currentLatitude.toString())
                    Log.d(TAG, currentLongitude.toString())
                    // use your location object
                    // get latitude , longitude and other info from this
                }

            }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        teamAName = view.findViewById(R.id.teamA_name) as EditText

        freeABtn = view.findViewById(R.id.freeA_btn)
        freeBBtn = view.findViewById(R.id.freeB_btn)
        twoABtn = view.findViewById(R.id.twoA_btn)
        twoBBtn = view.findViewById(R.id.twoB_btn)
        threeABtn = view.findViewById(R.id.threeA_btn)
        threeBBtn = view.findViewById(R.id.threeB_btn)

        rem1ABtn = view.findViewById(R.id.undoA_btn)
        rem1BBtn = view.findViewById(R.id.undoB_btn)

        teamAScoreTextView = view.findViewById(R.id.teamA_score)
        teamBScoreTextView = view.findViewById(R.id.teamB_score)
        resetBtn = view.findViewById(R.id.reset_btn)
        saveBtn = view.findViewById(R.id.save_btn)
        dispBtn = view.findViewById(R.id.display_btn)

        photoButton = view.findViewById(R.id.teamA_camera) as ImageButton
        photoView = view.findViewById(R.id.teamA_photo) as ImageView

        val acurrentIndex = savedInstanceState?.getInt(aKEY_INDEX, 0) ?: 0
        bbViewModel.curGame.teamAScore = acurrentIndex
        val bcurrentIndex = savedInstanceState?.getInt(bKEY_INDEX, 0) ?: 0
        bbViewModel.curGame.teamBScore = bcurrentIndex


        gameWeatherTextView = view.findViewById(R.id.game_weather)

        //val provider: ViewModelProvider = ViewModelProviders.of(this)
        //val bbViewModel = provider.get(BasketballViewModel::class.java)
        Log.d("BasketballViewModel", "Got a BasketballViewModel: $bbViewModel")

        freeABtn.setOnClickListener {
            bbViewModel.setCurrentAScore(bbViewModel.getCurrentAScore +1)
            //teamAScoreTextView.text = bbViewModel.getCurrentAScore.toString()
            updateScores()
        }

        freeBBtn.setOnClickListener {
            bbViewModel.setCurrentBScore(bbViewModel.getCurrentBScore +1)
            //teamBScoreTextView.text = bbViewModel.getCurrentBScore.toString()
            updateScores()
        }

        twoABtn.setOnClickListener {
            bbViewModel.setCurrentAScore(bbViewModel.getCurrentAScore +2)
            updateScores()
        }

        twoBBtn.setOnClickListener {
            bbViewModel.setCurrentBScore(bbViewModel.getCurrentBScore +2)
            updateScores()
        }

        threeABtn.setOnClickListener {
            bbViewModel.setCurrentAScore(bbViewModel.getCurrentAScore +3)
            updateScores()
        }

        threeBBtn.setOnClickListener {
            bbViewModel.setCurrentBScore(bbViewModel.getCurrentBScore +3)
            updateScores()
        }

        rem1ABtn.setOnClickListener {
            bbViewModel.setCurrentAScore(bbViewModel.getCurrentAScore -1)
            updateScores()
        }
        rem1BBtn.setOnClickListener {
            bbViewModel.setCurrentBScore(bbViewModel.getCurrentBScore -1)
            updateScores()
        }

        resetBtn.setOnClickListener {
            bbViewModel.setCurrentAScore(0)
            bbViewModel.setCurrentBScore(0)
            bbViewModel.curGame.id = UUID.randomUUID()
            updateScores()
        }

        saveBtn.setOnClickListener {
            // Start saveActivity
            var flag = 0
            for(i in bbViewModel.gameListLiveData.value!!) {
                if(i.id == bbViewModel.curGame.id)
                    flag = 1

            }
            if (flag == 0)//not in list, add
                bbViewModel.addGame(bbViewModel.curGame)
            else//in list, update
                bbViewModel.updateGame(bbViewModel.curGame)

        }

        dispBtn.setOnClickListener {
            // Start saveActivity


//            var flag = 0
//            for(i in bbViewModel.gameListLiveData.value!!) {
//                if(i.id == bbViewModel.curGame.id)
//                    flag = 1
//
//            }
//            if (flag == 0)//not in list, add
//                bbViewModel.addGame(bbViewModel.curGame)
//            else//in list, update
//                bbViewModel.updateGame(bbViewModel.curGame)
            callbacks?.onDispSelected(bbViewModel.curGame.id)

        }
//        saveBtn.setOnClickListener {
//            // Start saveActivity
//            val intent = SecondActivity.newIntent(this@MainActivity, bbViewModel.getCurrentAScore, bbViewModel.getCurrentBScore)
//            startActivityForResult(intent, REQUEST_CODE_SAVE)
//
//        }
        updateScores()


        return view
        //return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bbViewModel.gameListLiveData.observe(
            viewLifecycleOwner,
            Observer { games ->
                games?.let {
                    Log.i(TAG, "Got games ${games.size}")
                    this.game = games.get(1)
                    photoFile = bbViewModel.getPhotoFile(game)
                    photoUri = FileProvider.getUriForFile(requireActivity(),
                        "com.example.group27project1.fileprovider",
                        photoFile)

                    //updateUI(games)
                    updatePhotoView()

                    gameWeatherTextView.setText("1")

                }
            })
    }

    private fun updateScores() {
        teamAScoreTextView.setText(bbViewModel.getCurrentAScore.toString())
        teamBScoreTextView.setText(bbViewModel.getCurrentBScore.toString())

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()

//        mGoogleApiClient?.connect();
//
//        val location: Location? =
//            LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
//        Log.d(TAG, currentLatitude.toString())
//        if (location == null) {
//            // apparently it doesnt work in emulator LOL
//            currentLatitude = 42.2743
//            currentLongitude = -71.8081
//        } else {
//            //If everything went fine lets get latitude and longitude
//            currentLatitude = location.getLatitude()
//            currentLongitude = location.getLongitude()
//            Log.d(TAG, currentLatitude.toString())
//            Log.d(TAG, currentLongitude.toString())
//
//            //Toast.makeText(this, "$currentLatitude WORKS $currentLongitude", Toast.LENGTH_LONG).show()
//        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(aKEY_INDEX, bbViewModel.getCurrentAScore)
        savedInstanceState.putInt(bKEY_INDEX, bbViewModel.getCurrentBScore)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updatePhotoView() {
        if (photoFile.exists()) {
            val bitmap = getScaledBitmap(photoFile.path, requireActivity())
            photoView.setImageBitmap(bitmap)
        } else {
            photoView.setImageDrawable(null)
        }
    }


    override fun onActivityResult(requestCode: Int,  resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_SAVE) {
            bbViewModel.isScoreSaved =
                data?.getBooleanExtra(EXTRA_SCORES_SAVED, false) ?: false
        }

        if (requestCode == REQUEST_PHOTO) {
            updatePhotoView()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onResume() called")

        val teamANameWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(sequence: CharSequence, start: Int, before: Int, count: Int) {
                //teamA.name = sequence.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }
        }

        photoButton.apply {
            val packageManager: PackageManager = requireActivity().packageManager
            val captureImage = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val resolvedActivity: ResolveInfo? =
                packageManager.resolveActivity(captureImage,
                    PackageManager.MATCH_DEFAULT_ONLY)
            if (resolvedActivity == null) {
                isEnabled = false
            }
            setOnClickListener {
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                val cameraActivities: List<ResolveInfo> =
                    packageManager.queryIntentActivities(captureImage,
                        PackageManager.MATCH_DEFAULT_ONLY)
                for (cameraActivity in cameraActivities) {
                    requireActivity().grantUriPermission(
                        cameraActivity.activityInfo.packageName,
                        photoUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                }
                startActivityForResult(captureImage, REQUEST_PHOTO)
            }
        }

    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }



}