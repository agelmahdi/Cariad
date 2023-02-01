package com.agelmahdi.cariad.poi_feature.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.agelmahdi.cariad.core.util.JsonParser
import com.agelmahdi.cariad.poi_feature.domain.model.Address
import com.agelmahdi.cariad.poi_feature.domain.model.Operator
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromAddress(json: String): Address {
        return jsonParser.fromJson<Address>(
            json,
            object : TypeToken<Address>() {}.type
        ) ?: Address()
    }

    @TypeConverter
    fun toAddress(address: Address): String {
        return jsonParser.toJson(
            address,
            object : TypeToken<Address>() {}.type
        ) ?: ""
    }

    @TypeConverter
    fun fromOperator(operator: Operator): String {
        return operator.title.toString()
    }

    @TypeConverter
    fun toOperator(name: String): Operator {
        return Operator(name)
    }
}