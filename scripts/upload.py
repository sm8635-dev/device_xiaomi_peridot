#!/usr/bin/env python3

import os
import subprocess
import glob
import sys

def get_root():
    curr = os.getcwd()
    while curr != "/":
        if os.path.exists(os.path.join(curr, "out/target/product/peridot")):
            return curr
        curr = os.path.dirname(curr)
    return None

root = get_root()
if not root:
    print("Error: out/target/product/peridot not found.")
    sys.exit(1)

img_dir = os.path.join(root, "out/target/product/peridot/obj/PACKAGING/target_files_intermediates/yaap_peridot-target_files/IMAGES")
rom_dir = os.path.join(root, "out/target/product/peridot")

res = subprocess.run("echo 'ls -1' | sftp zenin1504@frs.sourceforge.net:/home/frs/project/peridot-build", shell=True, capture_output=True, text=True)
dirs = [d for d in res.stdout.splitlines() if not d.startswith('sftp>') and d.strip() and "Connected" not in d]

print("-" * 30)
for i, d in enumerate(dirs):
    print(f"{i+1}. {d}")
print("n. Create new")
print("-" * 30)

choice = input("Select: ")

if choice.lower() == 'n':
    dir_name = input("Name: ")
    subprocess.run(f"echo 'mkdir /home/frs/project/peridot-build/{dir_name}' | sftp zenin1504@frs.sourceforge.net", shell=True)
else:
    dir_name = dirs[int(choice)-1]

dest = f"zenin1504@frs.sourceforge.net:/home/frs/project/peridot-build/{dir_name}/"

for img in ["boot.img", "dtbo.img", "init_boot.img", "vendor_boot.img"]:
    path = os.path.join(img_dir, img)
    if os.path.exists(path):
        print(f"Uploading {img}...")
        subprocess.run(["rsync", "-Ph", path, dest])

rom_zip = next((z for z in glob.glob(os.path.join(rom_dir, "*.zip")) if "ota" not in z.lower()), None)
if rom_zip:
    print(f"Uploading {os.path.basename(rom_zip)}...")
    subprocess.run(["rsync", "-Ph", rom_zip, dest])
    if os.path.exists(f"{rom_zip}.sha256sum"):
        subprocess.run(["rsync", "-Ph", f"{rom_zip}.sha256sum", dest])

print(f"Done: https://sourceforge.net/projects/peridot-build/files/{dir_name}/")
