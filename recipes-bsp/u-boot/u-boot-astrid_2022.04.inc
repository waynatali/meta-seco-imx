DESCRIPTION = "i.MX U-Boot suppporting i.MX SECO boards."

inherit fsl-u-boot-localversion

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS += "flex-native bison-native bc-native dtc-native"

SRC_URI = "git://source.denx.de/u-boot/u-boot.git;protocol=https;branch=${SRCBRANCH}"
SRCBRANCH = "master"
SRCREV = "01f1ab67f38882dc7665a0a6eca4bbeba6d84f81"

PV = "v2022.04+git${SRCPV}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

