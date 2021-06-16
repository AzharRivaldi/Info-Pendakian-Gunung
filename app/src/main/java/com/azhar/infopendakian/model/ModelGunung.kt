package com.azhar.infopendakian.model

import java.io.Serializable

/**
 * Created by Azhar Rivaldi on 02-06-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

class ModelGunung : Serializable {
    var strNamaGunung: String,
    var strImageGunung: String,
    var strLokasiGunung: String,
    var strDeskripsi: String,
    var strJalurPendakian: String,
    var strInfoGunung: String,
    var strLat = 0.0,
    var strLong = 0.0
}