# Copyright (C) 2012-2019 O.S. Systems Software LTDA.
# Released under the MIT license (see COPYING.MIT for the terms)
#
# SPDX-License-Identifier: MIT
#

SUMMARY = "SECO FSL Community BSP Linux mainline based kernel with backported features and fixes"
DESCRIPTION = "Linux kernel based on mainline kernel used by FSL Community BSP in order to \
provide support for some backported features and fixes, or because it was applied in linux-next \
and takes some time to become part of a stable version, or because it is not applicable for \
upstreaming."

include linux-seco-fslc.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

# PV is defined in the base in linux-imx.inc file and uses the LINUX_VERSION definition
# required by kernel-yocto.bbclass.
#
# LINUX_VERSION define should match to the kernel version referenced by SRC_URI and
# should be updated once patchlevel is merged.
LINUX_VERSION = "5.10.10"

SRCBRANCH = "seco/develop/v5.10.10"
SRCREV = "e693c0f58a500372c211b2b32cdf70ba57841003"

COMPATIBLE_MACHINE = "(mxs|mx5|mx6|mx8|vf|use-mainline-bsp)"
