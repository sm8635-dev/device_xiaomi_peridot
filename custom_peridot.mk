#
# Copyright (C) 2024 The LineageOS Project
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from those products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit_only.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)

# Inherit some common PixelOS stuff.
$(call inherit-product, vendor/custom/config/common_full_phone.mk)

# Inherit from peridot device
$(call inherit-product, device/xiaomi/peridot/device.mk)

PRODUCT_NAME := custom_peridot
PRODUCT_DEVICE := peridot
PRODUCT_MANUFACTURER := Xiaomi

-include vendor/lineage-priv/keys/keys.mk

# Set BUILD_FINGERPRINT variable to be picked up by both system and vendor build.prop
BuildFingerprint := POCO/peridot_global/peridot:15/AQ3A.240912.001/OS2.0.208.0.VNPMIXM:user/release-keys

PRODUCT_GMS_CLIENTID_BASE := android-xiaomi

# Flags
TARGET_SCREEN_WIDTH := 1080
