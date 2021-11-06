package com.tuananh.app.spotiutils.ui.profile

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuananh.app.spotiutils.base.BaseFragment
import com.tuananh.app.spotiutils.data.remote.model.RecentlyPlayedTrack
import com.tuananh.app.spotiutils.data.remote.model.User
import com.tuananh.app.spotiutils.data.remote.repository.CurrentUserProfileRepository
import com.tuananh.app.spotiutils.data.remote.repository.RecentlyPlayedTrackRepository
import com.tuananh.app.spotiutils.data.remote.source.impl.CurrentUserProfileSourceImpl
import com.tuananh.app.spotiutils.data.remote.source.impl.RecentlyPlayedTrackSourceImpl
import com.tuananh.app.spotiutils.databinding.FragmentProfileBinding
import com.tuananh.app.spotiutils.ui.overview.adapter.RecentlyPlayedTrackAdapter
import com.tuananh.app.spotiutils.ui.webview.WebViewActivity

class ProfileFragment: BaseFragment<FragmentProfileBinding>(), ProfileContract.View {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate

    private val recentlyPlayedTrackAdapter by lazy {
        RecentlyPlayedTrackAdapter(
            mutableListOf(),
            this::onRecentlyPlayedItemClick
        )
    }

    override fun initData() {
        ProfilePresenter(
            this,
            CurrentUserProfileRepository.getInstance(
                CurrentUserProfileSourceImpl.getInstance()
            ),
            RecentlyPlayedTrackRepository.getInstance(
                RecentlyPlayedTrackSourceImpl.getInstance()
            )
        ).run {
            getUserInfo()
            getRecentlyPlayedTracks()
        }
    }

    override fun initView() {
        viewBinding?.run {
            with(recyclerviewRecentlyPlayed) {
                adapter = recentlyPlayedTrackAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    override fun showRecentlyPlayedTrack(tracks: MutableList<RecentlyPlayedTrack>) {
        recentlyPlayedTrackAdapter.updateData(tracks)
    }

    override fun showRecentlyPlayedLoading() {
        viewBinding?.loadingRecentlyPlayed?.show()
    }

    override fun hideRecentlyPlayedLoading() {
        viewBinding?.loadingRecentlyPlayed?.hide()
    }

    override fun showUserInfo(user: User) {
        viewBinding?.run {
            textUserName.text = user.displayName
            textUserEmail.text = user.email
            textUserCountry.text = user.country
        }
    }

    override fun showMessage(messageRes: Int) {
        Toast.makeText(context, getString(messageRes), Toast.LENGTH_SHORT).show()
    }

    override fun openWebView() {
        startActivity(Intent(context, WebViewActivity::class.java))
    }

    private fun onRecentlyPlayedItemClick(item: RecentlyPlayedTrack) {
        Toast.makeText(context, item.track.name, Toast.LENGTH_LONG).show()
    }
}