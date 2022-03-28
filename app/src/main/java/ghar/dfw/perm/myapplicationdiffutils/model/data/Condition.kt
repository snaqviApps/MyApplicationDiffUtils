package ghar.dfw.perm.myapplicationdiffutils.model.data


import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class Condition @Inject constructor(
    @SerializedName("code")
    val code: Int,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("text")
    val text: String
)