DESCRIPTION = "i.MX U-Boot suppporting i.MX SECO boards."

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://git.seco.com/pub/i.mx/yocto/5.x/uboot-seco-imx.git;protocol=https;branch=${SRCBRANCH}"
SRCBRANCH = "ohos/develop/imx_5.10.35_2.0.0"
SRCREV = "c2648e967038c861c13fa5fecd3f532470306683"

DEPENDS += "flex-native bison-native bc-native dtc-native"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit fsl-u-boot-localversion

LOCALVERSION ?= "-${SRCBRANCH}"

BOOT_TOOLS = "imx-boot-tools"

