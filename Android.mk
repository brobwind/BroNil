LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := tests

LOCAL_SRC_FILES := \
	$(call all-java-files-under, src) \
	src/com/brobwind/bronil/IProxyCallback.aidl \
	src/com/brobwind/bronil/IProxyPortListener.aidl

LOCAL_PACKAGE_NAME := BroNil
#LOCAL_CERTIFICATE := platform
#LOCAL_PRIVILEGED_MODULE := true

# CardView support
LOCAL_STATIC_JAVA_LIBRARIES := android-support-v7-cardview

LOCAL_RESOURCE_DIR := \
    frameworks/support/v7/cardview/res \
    $(LOCAL_PATH)/res

LOCAL_AAPT_FLAGS := --auto-add-overlay --extra-packages android.support.v7.cardview

#LOCAL_JARJAR_RULES := \
#	$(LOCAL_PATH)/jarjar-rules.txt

include $(BUILD_PACKAGE)
