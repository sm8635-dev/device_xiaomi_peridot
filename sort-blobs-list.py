#!/usr/bin/env python3
#
# SPDX-FileCopyrightText: 2024 The LineageOS Project
# SPDX-FileCopyrightText: 2026 zenin1504
# SPDX-License-Identifier: Apache-2.0
#

import sys
import urllib.request

def main():
    url = "https://raw.githubusercontent.com/LineageOS/android_tools_extract-utils/refs/heads/lineage-23.2/sort-blobs-list.py"
    proprietary_files = ["proprietary-files.txt"]

    try:
        with urllib.request.urlopen(url) as response:
            helper_code = response.read().decode('utf-8')
    except Exception as e:
        print(f"Failed to fetch helper: {e}", file=sys.stderr)
        sys.exit(1)

    # Set arguments for the remote script
    sys.argv = ["sort-blobs-list.py", "--dir-first"] + proprietary_files

    # Execute the script in memory
    exec(helper_code, {"__name__": "__main__", "__file__": "sort-blobs-list.py"})

if __name__ == "__main__":
    main()
