package com.tuananh.app.spotiutils.data.remote.model

import com.tuananh.app.spotiutils.util.PlaylistsItemModelConst
import com.tuananh.app.spotiutils.util.parseObjects
import org.json.JSONObject

data class PlaylistsItem(
  val collaborative: Boolean,
  val description: String,
  val href: String,
  val id: String,
  val images: List<Image>,
  val name: String,
  val owner: Owner,
  val public: Boolean,
  val tracks: PlaylistsItemTracks,
  val type: String,
  val uri: String
) {
  constructor(jsonObject: JSONObject): this(
    jsonObject.getBoolean(PlaylistsItemModelConst.COLLABORATIVE),
    jsonObject.getString(PlaylistsItemModelConst.DESCRIPTION),
    jsonObject.getString(PlaylistsItemModelConst.HREF),
    jsonObject.getString(PlaylistsItemModelConst.ID),
    jsonObject.getString(PlaylistsItemModelConst.IMAGES).parseObjects(),
    jsonObject.getString(PlaylistsItemModelConst.NAME),
    Owner(jsonObject.getJSONObject(PlaylistsItemModelConst.OWNER)),
    jsonObject.getBoolean(PlaylistsItemModelConst.PUBLIC),
    PlaylistsItemTracks(jsonObject.getJSONObject(PlaylistsItemModelConst.TRACKS)),
    jsonObject.getString(PlaylistsItemModelConst.TYPE),
    jsonObject.getString(PlaylistsItemModelConst.URI)
  )
}