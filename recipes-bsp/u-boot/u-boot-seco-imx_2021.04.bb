# Copyright (C) 2021 SECO Spa

require recipes-bsp/u-boot/u-boot.inc
require u-boot-seco-imx-common.inc

DESCRIPTION = "i.MX U-Boot suppporting i.MX SECO boards."

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://git.seco.com/pub/i.mx/yocto/5.x/uboot-seco-imx.git;protocol=https;branch=${SRCBRANCH}"
SRCBRANCH = "ohos/develop/imx_5.10.35_2.0.0"
SRCREV = "11cce61019d52fb17f8b19ecbbe383025170e1eb"

DEPENDS += "flex-native bison-native bc-native dtc-native"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

#inherit fsl-u-boot-localversion
# override from Dunfell meta-freescale layer
UBOOT_LOCALVERSION ?= "2021.04-r0"

LOCALVERSION ?= "-${SRCBRANCH}"

BOOT_TOOLS = "imx-boot-tools"

