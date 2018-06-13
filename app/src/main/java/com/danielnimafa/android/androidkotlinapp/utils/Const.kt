package com.danielnimafa.android.androidkotlinapp.utils

import android.Manifest
import android.content.pm.PackageManager

/**
 * Created by dioilham on 10/19/17.
 */

object Const {
    /* http://653c8196.ngrok.io/v1/ */
    const val endpoint_test = "api.test.v2.izzystorage.com"
    const val endpoint = "api.staging.izzyparcel.com"
    const val BASE_URL_TEST = "http://$endpoint_test/v1/"
    const val BASE_URL = "http://$endpoint/v1/"
    const val IS_LOGIN = "islogin"
    const val LOGIN = "courier/login"
    const val LOGOUT = "courier/logout"
    const val PICKUP_LIST = "courier/pickup/list"
    const val PICKUP_DETAIL = "courier/pickup/detail"
    const val PICKUP_FINISH = "courier/pickup/finish"
    const val DELIVERY_LIST = "courier/delivery/list"
    const val DELIVERY_DETAIL = "courier/delivery/detail"
    const val DELIVERY_VALIDATE = "courier/delivery/validate"
    const val DELIVERY_FINISH = "courier/delivery/finish"
    const val DELIVERY_SCAN = "courier/delivery/scan"

    const val STATUS_NEW = "new"
    const val STATUS_PARTIAL = "partial"
    const val STATUS_FINISHED = "finished"
    const val STATUS_DELETED = "deleted"
    const val ISCONTAINER = "is_container"
    const val ISITEM = "is_item"
    const val INBOUND_ID = "inbound_id"
    const val DEFAULT_TIMER = 5
    const val DEFAULT_DEV_ID = "358091050681250asdasr"
    const val field_index = "index"
    const val field_id = "id"
    const val field_code = "code"
    const val field_length = "length"
    const val field_height = "height"
    const val field_weight = "weight"
    const val field_width = "width"

    const val header_appkey = "X-IzzyParcel-App-Key"
    const val header_appsecret = "X-IzzyParcel-App-Secret"
    const val header_deviceID = "X-IzzyParcel-Device-Identifier"
    const val header_client = "X-IzzyStorage-Client-shortName"
    const val header_vendor = "X-IzzyStorage-Vendor-shortName"
    const val header_auth = "Authorization"

    const val value_header_appkey = "IzzyParcelDev"
    const val value_header_appsecret = "IzzyParcelDevSecret"

    const val ROOT_URL = "root_url"

    const val CONTENT_LIMIT = 10

    const val PO_FINISH_CODE = 3
    const val DO_FINISH_CODE = 2
    const val NEW_INBOUND_CODE = 90
    const val LOCATION_REQ_CODE = 17
    const val PERMISSION_LOCATION_REQ_CODE = 27
    const val cameraPermRequestCode = 199
    const val CAMERA_REQUEST_CODE = 10
    const val STORAGE_REQUEST_CODE = 11
    const val INBOUND_DELETE_CODE = 5
    const val REQUEST_READ_PHONE_STATE = 9

    const val multipartFormdata = "multipart/form-data"
    const val IMAGE_DIRECTORY_NAME = "Izzy Parcel Courier Picture"
    const val NO_MEDIA_FILENAME = ".nomedia"
    const val IMAGECACHE_DIRECTORY_NAME = "CACHE"
    const val granted = PackageManager.PERMISSION_GRANTED
    const val denied = PackageManager.PERMISSION_DENIED

    //activity
    const val TYPE_INBOUND = "inbound"
    const val TYPE_PALLETING = "palleting"
    const val TYPE_PUTTING = "putting"
    const val TYPE_PICKING = "picking"
    const val TYPE_PACKING = "packing"
    const val TYPE_OUTBOUND = "outbound"

    val PERMISSIONS_LOCATION = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION)

    const val INTENT_FILTER_LOCATION = "intent-filter-detect-location"
    const val INTENT_FILTER_INTERNET_CONNECTION = "intent-filter-internet-connection"

    //class name
    enum class ActivityID {
        INBOUND_SCAN, PALLET
    }
}