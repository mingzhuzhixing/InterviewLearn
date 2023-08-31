package com.v.module_permission

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.location.Geocoder
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.v.module_dialog.toast.ToastUtils
import com.v.module_permission.manager.NotificationMonitorService
import com.v.module_permission.manager.PermissionInterceptor
import com.v.module_permission.manager.PermissionNameConvert
import kotlinx.android.synthetic.main.activity_main_permission.*
import java.io.IOException
import java.util.*

/**
 * github : https://github.com/getActivity/XXPermissions
 * desc   : 权限申请演示
 */
class PermissionMainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_permission)

        btn_main_request_single_permission.setOnClickListener(this)
        btn_main_request_group_permission.setOnClickListener(this)
        btn_main_request_location_permission.setOnClickListener(this)
        btn_main_request_sensors_permission.setOnClickListener(this)
        btn_main_request_activity_recognition_permission.setOnClickListener(this)
        btn_main_request_bluetooth_permission.setOnClickListener(this)
        btn_main_request_wifi_devices_permission.setOnClickListener(this)
        btn_main_request_read_media_location_permission.setOnClickListener(this)
        btn_main_request_read_media_permission.setOnClickListener(this)
        btn_main_request_manage_storage_permission.setOnClickListener(this)
        btn_main_request_install_packages_permission.setOnClickListener(this)
        btn_main_request_system_alert_window_permission.setOnClickListener(this)
        btn_main_request_write_settings_permission.setOnClickListener(this)
        btn_main_request_notification_service_permission.setOnClickListener(this)
        btn_main_request_post_notification.setOnClickListener(this)
        btn_main_request_bind_notification_listener_permission.setOnClickListener(this)
        btn_main_request_usage_stats_permission.setOnClickListener(this)
        btn_main_request_schedule_exact_alarm_permission.setOnClickListener(this)
        btn_main_request_access_notification_policy_permission.setOnClickListener(this)
        btn_main_request_ignore_battery_optimizations_permission.setOnClickListener(this)
        btn_main_request_picture_in_picture_permission.setOnClickListener(this)
        btn_main_request_bind_vpn_service_permission.setOnClickListener(this)
        btn_main_request_get_installed_apps_permission.setOnClickListener(this)
        btn_main_start_permission_activity.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val viewId = view.id
        when (viewId) {
            R.id.btn_main_request_single_permission -> {
                XXPermissions.with(this)
                        .permission(Permission.CAMERA)
                        .interceptor(PermissionInterceptor())
                        .request(OnPermissionCallback { permissions, allGranted ->
                            if (!allGranted) {
                                return@OnPermissionCallback
                            }
                            toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                    PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                        })
            }
            R.id.btn_main_request_group_permission -> {
                XXPermissions.with(this)
                        .permission(Permission.RECORD_AUDIO)
                        .permission(*Permission.Group.CALENDAR)
                        .interceptor(PermissionInterceptor())
                        .request(OnPermissionCallback { permissions, allGranted ->
                            if (!allGranted) {
                                return@OnPermissionCallback
                            }
                            toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                    PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                        })
            }
            R.id.btn_main_request_location_permission -> {
                XXPermissions.with(this)
                        .permission(Permission.ACCESS_COARSE_LOCATION)
                        .permission(Permission.ACCESS_FINE_LOCATION) // 如果不需要在后台使用定位功能，请不要申请此权限
                        .permission(Permission.ACCESS_BACKGROUND_LOCATION)
                        .interceptor(PermissionInterceptor())
                        .request(OnPermissionCallback { permissions, allGranted ->
                            if (!allGranted) {
                                return@OnPermissionCallback
                            }
                            toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                    PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                        })
            }
            R.id.btn_main_request_sensors_permission -> {
                XXPermissions.with(this)
                        .permission(Permission.BODY_SENSORS)
                        .permission(Permission.BODY_SENSORS_BACKGROUND)
                        .interceptor(PermissionInterceptor())
                        .request(OnPermissionCallback { permissions, allGranted ->
                            if (!allGranted) {
                                return@OnPermissionCallback
                            }
                            toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                    PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                        })
            }
            R.id.btn_main_request_activity_recognition_permission -> {
                XXPermissions.with(this)
                        .permission(Permission.ACTIVITY_RECOGNITION)
                        .interceptor(PermissionInterceptor())
                        .request(OnPermissionCallback { permissions, allGranted ->
                            if (!allGranted) {
                                return@OnPermissionCallback
                            }
                            toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                    PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                            addCountStepListener()
                        })
            }
            R.id.btn_main_request_bluetooth_permission -> {
                var delayMillis: Long = 0
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
                    delayMillis = 2000
                    toast(getString(R.string.demo_android_12_bluetooth_permission_hint))
                }
                view.postDelayed({
                    XXPermissions.with(this@PermissionMainActivity)
                            .permission(Permission.BLUETOOTH_SCAN)
                            .permission(Permission.BLUETOOTH_CONNECT)
                            .permission(Permission.BLUETOOTH_ADVERTISE)
                            .interceptor(PermissionInterceptor())
                            .request(OnPermissionCallback { permissions, allGranted ->
                                if (!allGranted) {
                                    return@OnPermissionCallback
                                }
                                toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                        PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                            })
                }, delayMillis)
            }
            R.id.btn_main_request_wifi_devices_permission -> {
                var delayMillis: Long = 0
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                    delayMillis = 2000
                    toast(getString(R.string.demo_android_13_wifi_permission_hint))
                }
                view.postDelayed({
                    XXPermissions.with(this@PermissionMainActivity)
                            .permission(Permission.NEARBY_WIFI_DEVICES)
                            .interceptor(PermissionInterceptor())
                            .request(OnPermissionCallback { permissions, allGranted ->
                                if (!allGranted) {
                                    return@OnPermissionCallback
                                }
                                toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                        PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                            })
                }, delayMillis)
            }
            R.id.btn_main_request_read_media_location_permission -> {
                var delayMillis: Long = 0
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                    delayMillis = 2000
                    toast(getString(R.string.demo_android_10_read_media_location_permission_hint))
                }
                view.postDelayed({
                    XXPermissions.with(this@PermissionMainActivity) // Permission.READ_EXTERNAL_STORAGE 和 Permission.MANAGE_EXTERNAL_STORAGE 二选一
                            // 如果 targetSdk >= 33，则添加 Permission.READ_MEDIA_IMAGES 和 Permission.MANAGE_EXTERNAL_STORAGE 二选一
                            // 如果 targetSdk < 33，则添加 Permission.READ_EXTERNAL_STORAGE 和 Permission.MANAGE_EXTERNAL_STORAGE 二选一
                            .permission(Permission.READ_MEDIA_IMAGES)
                            .permission(Permission.ACCESS_MEDIA_LOCATION)
                            .interceptor(PermissionInterceptor())
                            .request(OnPermissionCallback { permissions, allGranted ->
                                if (!allGranted) {
                                    return@OnPermissionCallback
                                }
                                toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                        PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                                Thread { allImagesFromGallery }.start()
                            })
                }, delayMillis)
            }
            R.id.btn_main_request_read_media_permission -> {
                var delayMillis: Long = 0
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                    delayMillis = 2000
                    toast(getString(R.string.demo_android_13_read_media_permission_hint))
                }
                view.postDelayed({
                    XXPermissions.with(this@PermissionMainActivity) // 不适配分区存储应该这样写
                            //.permission(Permission.MANAGE_EXTERNAL_STORAGE)
                            // 适配分区存储应该这样写
                            .permission(Permission.READ_MEDIA_IMAGES)
                            .permission(Permission.READ_MEDIA_VIDEO)
                            .permission(Permission.READ_MEDIA_AUDIO)
                            .interceptor(PermissionInterceptor())
                            .request(OnPermissionCallback { permissions, allGranted ->
                                if (!allGranted) {
                                    return@OnPermissionCallback
                                }
                                toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                        PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                            })
                }, delayMillis)
            }
            R.id.btn_main_request_manage_storage_permission -> {
                var delayMillis: Long = 0
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                    delayMillis = 2000
                    toast(getString(R.string.demo_android_11_manage_storage_permission_hint))
                }
                view.postDelayed({
                    XXPermissions.with(this@PermissionMainActivity) // 适配分区存储应该这样写
                            //.permission(Permission.Group.STORAGE)
                            // 不适配分区存储应该这样写
                            .permission(Permission.MANAGE_EXTERNAL_STORAGE)
                            .interceptor(PermissionInterceptor())
                            .request(OnPermissionCallback { permissions, allGranted ->
                                if (!allGranted) {
                                    return@OnPermissionCallback
                                }
                                toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                        PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                            })
                }, delayMillis)
            }
            R.id.btn_main_request_install_packages_permission -> {
                XXPermissions.with(this)
                        .permission(Permission.REQUEST_INSTALL_PACKAGES)
                        .interceptor(PermissionInterceptor())
                        .request(OnPermissionCallback { permissions, allGranted ->
                            if (!allGranted) {
                                return@OnPermissionCallback
                            }
                            toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                    PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                        })
            }
            R.id.btn_main_request_system_alert_window_permission -> {
                XXPermissions.with(this)
                        .permission(Permission.SYSTEM_ALERT_WINDOW)
                        .interceptor(PermissionInterceptor())
                        .request(OnPermissionCallback { permissions, allGranted ->
                            if (!allGranted) {
                                return@OnPermissionCallback
                            }
                            toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                    PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                        })
            }
            R.id.btn_main_request_write_settings_permission -> {
                XXPermissions.with(this)
                        .permission(Permission.WRITE_SETTINGS)
                        .interceptor(PermissionInterceptor())
                        .request(OnPermissionCallback { permissions, allGranted ->
                            if (!allGranted) {
                                return@OnPermissionCallback
                            }
                            toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                    PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                        })
            }
            R.id.btn_main_request_notification_service_permission -> {
                XXPermissions.with(this)
                        .permission(Permission.NOTIFICATION_SERVICE)
                        .interceptor(PermissionInterceptor())
                        .request(OnPermissionCallback { permissions, allGranted ->
                            if (!allGranted) {
                                return@OnPermissionCallback
                            }
                            toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                    PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                        })
            }
            R.id.btn_main_request_post_notification -> {
                var delayMillis: Long = 0
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                    delayMillis = 2000
                    toast(getString(R.string.demo_android_13_post_notification_permission_hint))
                }
                view.postDelayed({
                    XXPermissions.with(this@PermissionMainActivity)
                            .permission(Permission.POST_NOTIFICATIONS)
                            .interceptor(PermissionInterceptor())
                            .request(OnPermissionCallback { permissions, allGranted ->
                                if (!allGranted) {
                                    return@OnPermissionCallback
                                }
                                toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                        PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                            })
                }, delayMillis)
            }
            R.id.btn_main_request_bind_notification_listener_permission -> {
                XXPermissions.with(this)
                        .permission(Permission.BIND_NOTIFICATION_LISTENER_SERVICE)
                        .interceptor(PermissionInterceptor())
                        .request(OnPermissionCallback { permissions, allGranted ->
                            if (!allGranted) {
                                return@OnPermissionCallback
                            }
                            toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                    PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                                toggleNotificationListenerService()
                            }
                        })
            }
            R.id.btn_main_request_usage_stats_permission -> {
                XXPermissions.with(this)
                        .permission(Permission.PACKAGE_USAGE_STATS)
                        .interceptor(PermissionInterceptor())
                        .request(OnPermissionCallback { permissions, allGranted ->
                            if (!allGranted) {
                                return@OnPermissionCallback
                            }
                            toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                    PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                        })
            }
            R.id.btn_main_request_schedule_exact_alarm_permission -> {
                XXPermissions.with(this)
                        .permission(Permission.SCHEDULE_EXACT_ALARM)
                        .interceptor(PermissionInterceptor())
                        .request(OnPermissionCallback { permissions, allGranted ->
                            if (!allGranted) {
                                return@OnPermissionCallback
                            }
                            toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                    PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                        })
            }
            R.id.btn_main_request_access_notification_policy_permission -> {
                XXPermissions.with(this)
                        .permission(Permission.ACCESS_NOTIFICATION_POLICY)
                        .interceptor(PermissionInterceptor())
                        .request(OnPermissionCallback { permissions, allGranted ->
                            if (!allGranted) {
                                return@OnPermissionCallback
                            }
                            toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                    PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                        })
            }
            R.id.btn_main_request_ignore_battery_optimizations_permission -> {
                XXPermissions.with(this)
                        .permission(Permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                        .interceptor(PermissionInterceptor())
                        .request(OnPermissionCallback { permissions, allGranted ->
                            if (!allGranted) {
                                return@OnPermissionCallback
                            }
                            toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                    PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                        })
            }
            R.id.btn_main_request_picture_in_picture_permission -> {
                XXPermissions.with(this)
                        .permission(Permission.PICTURE_IN_PICTURE)
                        .interceptor(PermissionInterceptor())
                        .request(OnPermissionCallback { permissions, allGranted ->
                            if (!allGranted) {
                                return@OnPermissionCallback
                            }
                            toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                    PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                        })
            }
            R.id.btn_main_request_bind_vpn_service_permission -> {
                XXPermissions.with(this)
                        .permission(Permission.BIND_VPN_SERVICE)
                        .interceptor(PermissionInterceptor())
                        .request(OnPermissionCallback { permissions, allGranted ->
                            if (!allGranted) {
                                return@OnPermissionCallback
                            }
                            toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                    PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                        })
            }
            R.id.btn_main_request_get_installed_apps_permission -> {
                XXPermissions.with(this)
                        .permission(Permission.GET_INSTALLED_APPS)
                        .interceptor(PermissionInterceptor())
                        .request(OnPermissionCallback { permissions, allGranted ->
                            if (!allGranted) {
                                return@OnPermissionCallback
                            }
                            toast(java.lang.String.format(getString(R.string.demo_obtain_permission_success_hint),
                                    PermissionNameConvert.getPermissionString(this@PermissionMainActivity, permissions)))
                            appList
                        })
            }
            R.id.btn_main_start_permission_activity -> {
                XXPermissions.startPermissionActivity(this)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != XXPermissions.REQUEST_CODE) {
            return
        }
        toast(getString(R.string.demo_return_activity_result_hint))
    }

    fun toast(text: String) {
        ToastUtils.showToast(text)
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private fun toggleNotificationListenerService() {
        packageManager.setComponentEnabledSetting(
                ComponentName(this, NotificationMonitorService::class.java),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP)
        packageManager.setComponentEnabledSetting(
                ComponentName(this, NotificationMonitorService::class.java),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP)
    }// java.lang.UnsupportedOperationException:
    // Caller must hold ACCESS_MEDIA_LOCATION permission to access original
    // 经过测试，在部分手机上面申请获取媒体位置权限，如果用户选择的是 "仅在使用中允许"
    // 那么就会导致权限是授予状态，但是调用 openInputStream 时会抛出此异常
// 获取图片的经纬度// 谷歌官方文档：https://developer.android.google.cn/training/data-storage/shared/media?hl=zh-cn#location-media-captured
    /**
     * 获取所有图片
     */
    private val allImagesFromGallery: Unit
        private get() {
            val projection = arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA,
                    MediaStore.MediaColumns.TITLE, MediaStore.Images.Media.SIZE,
                    MediaStore.Images.ImageColumns.LATITUDE, MediaStore.Images.ImageColumns.LONGITUDE)
            val orderBy = MediaStore.Video.Media.DATE_TAKEN
            val cursor = applicationContext.contentResolver
                    .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
                            null, null, "$orderBy DESC")
            val idIndex = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns._ID)
            val pathIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DATA)
            while (cursor.moveToNext()) {
                val filePath = cursor.getString(pathIndex)
                var latLong = FloatArray(2)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    // 谷歌官方文档：https://developer.android.google.cn/training/data-storage/shared/media?hl=zh-cn#location-media-captured
                    var photoUri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            cursor.getString(idIndex))
                    photoUri = MediaStore.setRequireOriginal(photoUri!!)
                    try {
                        val inputStream = applicationContext
                                .contentResolver.openInputStream(photoUri) ?: continue
                        val exifInterface = ExifInterface(inputStream)
                        // 获取图片的经纬度
                        exifInterface.getLatLong(latLong)
                        inputStream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } catch (e: UnsupportedOperationException) {
                        // java.lang.UnsupportedOperationException:
                        // Caller must hold ACCESS_MEDIA_LOCATION permission to access original
                        // 经过测试，在部分手机上面申请获取媒体位置权限，如果用户选择的是 "仅在使用中允许"
                        // 那么就会导致权限是授予状态，但是调用 openInputStream 时会抛出此异常
                        e.printStackTrace()
                    }
                } else {
                    val latitudeIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.LATITUDE)
                    val longitudeIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.LONGITUDE)
                    latLong = floatArrayOf(cursor.getFloat(latitudeIndex), cursor.getFloat(longitudeIndex))
                }
                if (latLong[0] != 0f && latLong[1] != 0f) {
                    Log.i("XXPermissions", "获取到图片的经纬度：" + filePath + "，" + Arrays.toString(latLong))
                    Log.i("XXPermissions", "图片经纬度所在的地址：" + latLongToAddressString(latLong[0], latLong[1]))
                } else {
                    Log.i("XXPermissions", "该图片获取不到经纬度：$filePath")
                }
            }
            cursor.close()
        }

    /**
     * 将经纬度转换成地址
     */
    private fun latLongToAddressString(latitude: Float, longitude: Float): String {
        var addressString = ""
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(latitude.toDouble(), longitude.toDouble(), 1)
            if (addresses != null) {
                val returnedAddress = addresses[0]
                val strReturnedAddress = StringBuilder("")
                for (i in 0..returnedAddress.maxAddressLineIndex) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n")
                }
                addressString = strReturnedAddress.toString()
            } else {
                Log.w("XXPermissions", "没有返回地址")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.w("XXPermissions", "无法获取到地址")
        }
        return addressString
    }

    private val mSensorEventListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            Log.w("onSensorChanged", "event = $event")
            when (event.sensor.type) {
                Sensor.TYPE_STEP_COUNTER -> Log.w("XXPermissions", "开机以来当天总步数：" + event.values[0])
                Sensor.TYPE_STEP_DETECTOR -> if (event.values[0] == 1f) {
                    Log.w("XXPermissions", "当前走了一步")
                }
                else -> {}
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            Log.w("onAccuracyChanged", accuracy.toString())
        }
    }

    /**
     * 添加步数监听
     */
    private fun addCountStepListener() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return
        }
//        val manager = ContextCompat.getSystemService(this@MainPermissionActivity, Context.SENSOR_SERVICE)
//        val stepSensor = manager!!.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
//        val detectorSensor = manager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
//        if (stepSensor != null) {
//            manager.registerListener(mSensorEventListener, stepSensor, SensorManager.SENSOR_DELAY_NORMAL)
//        }
//        if (detectorSensor != null) {
//            manager.registerListener(mSensorEventListener, detectorSensor, SensorManager.SENSOR_DELAY_NORMAL)
//        }
    }

    private val appList: Unit
        private get() {
            try {
                val flags = PackageManager.GET_ACTIVITIES or PackageManager.GET_SERVICES
                val packageInfoList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    packageManager.getInstalledPackages(PackageManager.PackageInfoFlags.of(flags.toLong()))
                } else {
                    packageManager.getInstalledPackages(flags)
                }
                for (info in packageInfoList) {
                    Log.i("XXPermissions", "应用包名：" + info.packageName)
                }
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
}
