cd vendor/custom
git fetch https://github.com/sm8635-dev/vendor_custom sixteen-qpr2
git reset --hard FETCH_HEAD
croot

rm -rf vendor/lineage-priv/keys
git clone https://github.com/sm8635-dev/signed_keys.git -b ap3a vendor/lineage-priv/keys
