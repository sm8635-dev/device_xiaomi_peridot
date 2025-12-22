cd hardware/qcom-caf/sm8650/audio/agm
git fetch https://github.com/sm8635-dev/vendor_qcom_opensource_agm lineage-23.2-caf-sm8650
git reset --hard FETCH_HEAD
croot

cd hardware/qcom-caf/sm8650/audio/graphservices
git fetch https://github.com/LineageOS/android_vendor_qcom_opensource_audioreach-graphservices lineage-23.2-caf-sm8650
git reset --hard FETCH_HEAD
croot

cd hardware/qcom-caf/sm8650/audio/pal
git fetch https://github.com/LineageOS/android_vendor_qcom_opensource_arpal-lx lineage-23.2-caf-sm8650
git reset --hard FETCH_HEAD
croot

cd hardware/qcom-caf/sm8650/audio/primary-hal
git fetch https://github.com/LineageOS/android_hardware_qcom_audio-ar lineage-23.2-caf-sm8650
git reset --hard FETCH_HEAD
croot

cd hardware/lineage/interfaces
git fetch https://github.com/sm8635-dev/hardware_lineage_interfaces sixteen
git reset --hard FETCH_HEAD
croot

cd device/lineage/sepolicy
git fetch https://github.com/sm8635-dev/device_lineage_sepolicy sixteen
git reset --hard FETCH_HEAD
croot

rm -rf vendor/yaap/signing/keys
git clone https://github.com/Neon-Duchamp/keys.git -b yaap-keys vendor/yaap/signing/keys
