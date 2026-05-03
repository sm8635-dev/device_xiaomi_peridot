cd frameworks/base
git fetch https://github.com/sm8635-dev/frameworks_base sixteen
git reset --hard FETCH_HEAD
croot

cd vendor/yaap
git fetch https://github.com/sm8635-dev/vendor_yaap sixteen
git reset --hard FETCH_HEAD
croot

rm -rf vendor/yaap/signing/keys
git clone https://github.com/Neon-Duchamp/keys.git -b yaap-keys vendor/yaap/signing/keys
