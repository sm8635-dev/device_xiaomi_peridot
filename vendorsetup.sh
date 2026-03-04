cd vendor/custom
git fetch https://github.com/sm8635-dev/vendor_custom sixteen-qpr2
git reset --hard FETCH_HEAD
croot

rm -rf vendor/lineage-priv/keys
git clone https://github.com/Neon-Duchamp/keys.git -b old-keys vendor/lineage-priv/keys
