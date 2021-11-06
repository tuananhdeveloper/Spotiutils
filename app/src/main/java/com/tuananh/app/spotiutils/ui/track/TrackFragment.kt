package com.tuananh.app.spotiutils.ui.track

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuananh.app.spotiutils.R
import com.tuananh.app.spotiutils.base.BaseFragment
import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.data.remote.model.AudioFeatures
import com.tuananh.app.spotiutils.data.remote.model.Image
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.repository.TrackRepository
import com.tuananh.app.spotiutils.data.remote.source.impl.ArtistSourceImpl
import com.tuananh.app.spotiutils.data.remote.source.impl.TrackSourceImpl
import com.tuananh.app.spotiutils.databinding.FragmentTrackBinding
import com.tuananh.app.spotiutils.ui.artist.ArtistFragment
import com.tuananh.app.spotiutils.ui.track.adapter.ArtistAdapter
import com.tuananh.app.spotiutils.ui.track.adapter.MoreTrackFromArtistAdapter
import com.tuananh.app.spotiutils.ui.webview.WebViewActivity
import com.tuananh.app.spotiutils.util.*

class TrackFragment: BaseFragment<FragmentTrackBinding>(), TrackContract.View {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTrackBinding
        get() = FragmentTrackBinding::inflate

    private val moreTrackAdapter by lazy {
        MoreTrackFromArtistAdapter(
            mutableListOf(),
            this::onMoreItemTrackClickListener
        )
    }

    private val artistAdapter by lazy {
        ArtistAdapter(
            mutableListOf(),
            this::onArtistClickListener
        )
    }

    private var track: Track? = null

    override fun initData() {
        track = arguments?.getParcelable(EXTRA_TRACK)
        track?.let {
            showTrack(it)
            TrackPresenter(this,
                TrackRepository.getInstance(
                    TrackSourceImpl.getInstance(),
                    ArtistSourceImpl.getInstance()
                )).run {
                getTopTracks(it.artists[0].id)
                getAudioFeatures(it.id)
                getArtist(it.artists.map { artist ->  artist.id})
            }
        }

    }

    override fun initView() {
        viewBinding?.run {

            appbarFragmentTrack.toolbar.apply {
                inflateMenu(R.menu.menu_fragment_track_artist)
                setNavigationOnClickListener {
                    if(isAdded) {
                        parentFragmentManager.popBackStack()
                    }
                }

                setOnMenuItemClickListener {
                    when(it.itemId) {
                        R.id.menu_favorite -> {
                            addToFavorites()
                            true
                        }
                        else -> true
                    }
                }
            }

            appbarFragmentTrack.collapsingToolbar.apply {
                title = ""
                setExpandedTitleColor(Color.WHITE)
                setCollapsedTitleTextColor(Color.WHITE)
                setContentScrimColor(ContextCompat.getColor(context, R.color.primaryColor))
            }

            recyclerviewMoreTracks.apply {
                layoutManager = GridLayoutManager(context, SPAN_COUNT)
                adapter = moreTrackAdapter
            }

            recyclerviewArtists.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = artistAdapter
            }

        }
    }

    override fun showTopTracks(tracks: List<Track>) {
        moreTrackAdapter.updateData(tracks as MutableList<Track>)
    }

    override fun showAudioFeatures(audioFeatures: AudioFeatures) {
        viewBinding?.run {
            layoutAudioFeatures.apply {
                progressAcoustic.max = 100
                progressAcoustic.progress = (audioFeatures.acousticness*100).toInt()

                progressDanceable.max = 100
                progressDanceable.progress = (audioFeatures.danceability*100).toInt()

                progressEnergetic.max = 100
                progressEnergetic.progress = (audioFeatures.energy*100).toInt()

                progressInstrumental.max = 100
                progressInstrumental.progress = (audioFeatures.instrumentalness*100).toInt()

                progressLively.max = 100
                progressLively.progress = (audioFeatures.liveness*100).toInt()

                progressPopularity.max = 100
                progressPopularity.progress = track!!.popularity

                progressSpeechful.max = 100
                progressSpeechful.progress = (audioFeatures.speechiness*100).toInt()

                progressValence.max = 100
                progressValence.progress = (audioFeatures.valence*100).toInt()

            }
            textKeyContent.text = audioFeatures.key.toKey()
            textBpmContent.text = audioFeatures.tempo.toString()
        }

    }

    override fun showArtist(artists: List<Artist>) {
        artistAdapter.updateData(artists as MutableList<Artist>)
    }

    override fun showLoading() {
        viewBinding?.loading?.show()
    }

    override fun hideLoading() {
        viewBinding?.loading?.hide()
    }

    override fun showAllView() {
        viewBinding?.run {
            cardviewPopularity.root.show()
            cardviewTrackLength.root.show()
            textAudioFeatures.show()
            layoutAudioFeatures.root.show()
            textAlbum.show()
            cardviewAlbum.root.show()
            textArtist.show()
            recyclerviewArtists.show()
            textMoreTracks.show()
            recyclerviewMoreTracks.show()
            textLyrics.show()
            textLyricsContent.show()
            textAudioAnalysis.show()
            textKeyContent.show()
            textKey.show()
            textBpm.show()
            textBpmContent.show()

        }
    }

    override fun hideAllView() {
        viewBinding?.run {
            cardviewPopularity.root.hide()
            cardviewTrackLength.root.hide()
            textAudioFeatures.hide()
            layoutAudioFeatures.root.hide()
            textAlbum.hide()
            cardviewAlbum.root.hide()
            textArtist.hide()
            recyclerviewArtists.hide()
            textMoreTracks.hide()
            recyclerviewMoreTracks.hide()
            textLyrics.hide()
            textLyricsContent.hide()
            textAudioAnalysis.hide()
            textKeyContent.hide()
            textKey.hide()
            textBpm.hide()
            textBpmContent.hide()
        }
    }

    override fun showMessage(messageRes: Int) {
        Toast.makeText(context, messageRes, Toast.LENGTH_SHORT).show()
    }

    override fun openWebView() {
        startActivity(Intent(context, WebViewActivity::class.java))
    }

    private fun showTrack(track: Track) {
        viewBinding?.run {
            appbarFragmentTrack.apply {
                image.loadImage(track.album.images[Image.ImageType.SIZE300_300.ordinal].url)
                toolbar.title = track.name
            }
            cardviewPopularity.apply {
                textPopularity.text = (track.popularity.toDouble()/10).toString()
            }
            cardviewTrackLength.apply {
                textTrackLength.text = miliSecToTrackLength(track.durationMs)
            }
            cardviewAlbum.apply {
                this.imageAlbum.loadImage(track.album.images[Image.ImageType.SIZE64_64.ordinal].url)
                this.textAlbumName.text = track.album.name
                this.textArtistName.text = track.artists.map { artist -> artist.name}
                    .joinToString(separator = ", " )
            }
            buttonOpen.setOnClickListener { openWeb(track) }
        }
    }

    private fun openWeb(track: Track) {
        var intent = Intent(Intent.ACTION_VIEW, Uri.parse(track.uri))
        context?.let {
            if(isIntentAvailable(it, intent)) {
                startActivity(intent)
            }
            else {
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("$OPEN_TRACK_URL${track.id}"))
                if(isIntentAvailable(it, intent)) {
                    startActivity(intent)
                }
                else {
                    Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    fun isIntentAvailable(ctx: Context, intent: Intent?): Boolean {
        val mgr: PackageManager = ctx.getPackageManager()
        val list = mgr.queryIntentActivities(
            intent!!,
            PackageManager.MATCH_ALL
        )
        return list.size > 0
    }

    private fun onMoreItemTrackClickListener(track: Track) {
        openNewTrackFragment(track)
    }

    private fun onArtistClickListener(artist: Artist) {
        openNewArtistFragment(artist)
    }

    private fun addToFavorites() {
        Toast.makeText(context, "add to favorites", Toast.LENGTH_SHORT).show()
    }

    private fun openNewTrackFragment(track: Track) {
        val trackFragment = TrackFragment()
        trackFragment.arguments = bundleOf(EXTRA_TRACK to track)
        fragmentManager?.addFragment(R.id.fragment_container,
            trackFragment,
            null,
            true
        )
    }

    private fun openNewArtistFragment(artist: Artist) {
        val artistFragment = ArtistFragment()
        artistFragment.arguments = bundleOf(ArtistFragment.EXTRA_ARTIST to artist)
        fragmentManager?.addFragment(R.id.fragment_container,
            artistFragment,
            null,
            true
        )
    }

    companion object {
        const val EXTRA_TRACK = "extra_track"
        const val SPAN_COUNT = 2
        const val OPEN_TRACK_URL = "https://open.spotify.com/track/"
    }
}