# Copyright (C) 2012-2015 O.S. Systems Software LTDA.
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-imx.inc

DEPENDS += "lzop-native bc-native"

SRC_URI = "git://git.seco.com/pub/i.mx/yocto/5.x/linux-seco-fslc.git;branch=${SRCBRANCH}"

KBUILD_DEFCONFIG_seco-imx8mm-c61 = "seco_imx8_defconfig"

LOCALVERSION = "-fslc"
