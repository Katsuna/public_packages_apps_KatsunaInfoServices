LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_USE_AAPT2 := true
LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under, app/src/main)

LOCAL_MANIFEST_FILE := app/src/main/AndroidManifest.xml

LOCAL_RESOURCE_DIR := \
    $(LOCAL_PATH)/app/src/main/res

# Include KatsunaCommon into this app
LOCAL_REQUIRED_MODULES := KatsunaCommon

LOCAL_STATIC_ANDROID_LIBRARIES := \
    KatsunaCommon \
    android-support-v4 \
    android-support-v7-appcompat \
    android-support-v7-cardview \
    android-support-v7-recyclerview \
    android-support-design

LOCAL_STATIC_JAVA_LIBRARIES := \
    androidvolley \
    googlegson

LOCAL_STATIC_JAVA_AAR_LIBRARIES := \
    androiddevicenames \
    roundedimageview

LOCAL_AAPT_INCLUDE_ALL_RESOURCES := true

LOCAL_PACKAGE_NAME := KatsunaInfoServices
#LOCAL_PROGUARD_FLAG_FILES := app/proguard-rules.pro

LOCAL_PROGUARD_FLAGS := -ignorewarnings -include build/core/proguard_basic_keeps.flags
LOCAL_PROGUARD_ENABLED := nosystem
LOCAL_PROGUARD_FLAG_FILES := app/proguard-rules.pro

include $(BUILD_PACKAGE)
