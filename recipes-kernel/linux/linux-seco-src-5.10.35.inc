# Released under the MIT license (see COPYING.MIT for the terms)

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

KERNEL_SRC ?= "git://git.seco.com/pub/i.mx/yocto/5.x/linux-seco-imx.git"

PROTOCOL ?= "protocol=ssh;"
REPO_USER ?= ""
SRCBRANCH = "ohos/develop/imx_5.10.35_2.0.0"
SRC_URI = " \
	${KERNEL_SRC};branch=${SRCBRANCH};${PROTOCOL}${REPO_USER} \
	file://firmware.cfg \
"
SRCREV = "ead23a59d81ac1b4c98659c0453f15ea3db6e5dd"

LINUX_VERSION ?= "5.10.35"
