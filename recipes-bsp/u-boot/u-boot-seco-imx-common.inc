DESCRIPTION = "i.MX U-Boot suppporting i.MX SECO boards."

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://git.seco.com/pub/i.mx/yocto/5.x/uboot-seco-imx.git;protocol=https;branch=${SRCBRANCH}"
SRCBRANCH = "ohos/develop/imx_5.4.70_2.3.0"
SRCREV = "7da63baaba769b556b7434e77cb259689dc5f2ad"

DEPENDS += "flex-native bison-native bc-native dtc-native"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit fsl-u-boot-localversion

LOCALVERSION ?= "-${SRCBRANCH}"

BOOT_TOOLS = "imx-boot-tools"

