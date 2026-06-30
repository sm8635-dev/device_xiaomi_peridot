#
# Copyright (C) 2024 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from those products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit_only.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)

# Inherit some common Lineage stuff.
$(call inherit-product, vendor/lineage/config/common_full_phone.mk)

# Inherit from peridot device
$(call inherit-product, device/xiaomi/peridot/device.mk)

PRODUCT_NAME := lineage_peridot
PRODUCT_DEVICE := peridot
PRODUCT_MANUFACTURER := Xiaomi

PRODUCT_CHARACTERISTICS := nosdcard
PRODUCT_GMS_CLIENTID_BASE := android-xiaomi

# Axion Device Configuration
#AXION_MAINTAINER := zenin1504
#AXION_PROCESSOR := SM8635

# Camera Info
#AXION_CAMERA_REAR_INFO := 50,8
#AXION_CAMERA_FRONT_INFO := 20

# Graphics & Display
#TARGET_ENABLE_BLUR := true
#TARGET_SUPPORTED_REFRESH_RATES := 60,90,120

# Features & Performance
#TARGET_INCLUDE_AXFX := false
#PERF_GOV_SUPPORTED := true
#PERF_DEFAULT_GOV := reflex
