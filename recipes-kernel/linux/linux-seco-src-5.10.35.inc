# Released under the MIT license (see COPYING.MIT for the terms)

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

KERNEL_SRC ?= "git://git.seco.com/pub/i.mx/yocto/5.x/linux-seco-imx.git"

PROTOCOL ?= "protocol=ssh;"
REPO_USER ?= ""
SRCBRANCH = "ohos/filter/imx_5.10.35_2.0.0"
SRC_URI = " \
	${KERNEL_SRC};branch=${SRCBRANCH};${PROTOCOL}${REPO_USER} \
	file://firmware.cfg \
"
SRCREV = "6722bbc49b7c35e8567da92fe7458e48d932d9c4"

LINUX_VERSION ?= "5.10.35"
