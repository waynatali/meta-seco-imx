# Released under the MIT license (see COPYING.MIT for the terms)

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"


KERNEL_SRC ?= "git://git.seco.com/pub/i.mx/yocto/5.x/linux-seco-imx.git"

PROTOCOL ?= "protocol=ssh;"
REPO_USER ?= ""
SRCBRANCH = "ohos/develop/imx_5.4.70_2.3.0"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH};${PROTOCOL}${REPO_USER}"
SRCREV = "9982915529b7f4e49a1814f189b737b43bdc0cd1"

LINUX_VERSION ?= "5.4.70"
