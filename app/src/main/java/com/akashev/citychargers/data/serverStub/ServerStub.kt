package com.akashev.citychargers.data.serverStub

import java.io.IOException

class ServerStub {
    companion object {
        private const val ALL_DATA = "{\"code\":200,\"data\":[{\"city\":\"Moscow\",\"charger\":{\"busy\":true,\"name\":" +
                "\"Энергия Москвы\",\"address\":\"Измайловское ш., 4А\"}},{\"city\":\"Moscow\",\"" +
                "charger\":{\"busy\":false,\"name\":\"Lipgart\",\"address\":\"2-я Бауманская ул., " +
                "5, стр. 5\"}},{\"city\":\"Saint Petersburg\",\"charger\":{\"busy\":true,\"name\":" +
                "\"Станция зарядки электромобилей\",\"address\":\"Большой просп. Васильевского " +
                "острова, 68\"}},{\"city\":\"Moscow\",\"charger\":{\"busy\":false,\"name\":\"Zevs\"" +
                ",\"address\":\"Мясницкая ул., 13, стр. 10\"}},{\"city\":\"Saint Petersburg\"," +
                "\"charger\":{\"busy\":false,\"name\":\"Punkt E\",\"address\":\"Малая Монетная ул., 2Г\"}}]}"
        private const val ONE_CITY = "{\"code\":200,\"data\":[{\"city\":\"Moscow\",\"charger\"" +
                ":{\"busy\":true,\"name\":\"Энергия Москвы\",\"address\":\"Измайловское ш., 4А\"}}]}"
        private const val EMPTY_DATA = "{\"code\":200, \"data\":[]}"
        private const val SERVER_ERROR = "{\"code\":500}"
    }
    private val responseMap = mapOf(
        "all" to ALL_DATA,
        "one" to ONE_CITY,
        "empty" to EMPTY_DATA,
        "error" to SERVER_ERROR,
    )

    fun makeNetworkRequest(key: String? = "all"): String {
        return responseMap.get(key) ?: throw IOException("imitating IO exception")
    }
}

