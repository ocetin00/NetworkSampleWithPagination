package com.oguzhancetin.networksample.model

import com.google.gson.annotations.SerializedName

data class Civilization(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("expansion") var expansion: String? = null,
    @SerializedName("army_type") var armyType: String? = null,
    @SerializedName("unique_unit") var uniqueUnit: ArrayList<String> = arrayListOf(),
    @SerializedName("unique_tech") var uniqueTech: ArrayList<String> = arrayListOf(),
    @SerializedName("team_bonus") var teamBonus: String? = null,
    @SerializedName("civilization_bonus") var civilizationBonus: ArrayList<String> = arrayListOf()

)
data class Civilizations(val civilizations: List<Civilization>)