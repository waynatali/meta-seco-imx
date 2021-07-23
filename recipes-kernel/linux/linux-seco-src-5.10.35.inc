# Released under the MIT license (see COPYING.MIT for the terms)

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

KERNEL_SRC ?= "git://git.seco.com/pub/i.mx/yocto/5.x/linux-seco-imx.git"

PROTOCOL ?= "protocol=ssh;"
REPO_USER ?= ""
SRCBRANCH = "ohos/develop/imx_5.10.35_2.0.0"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH};${PROTOCOL}${REPO_USER}"
SRCREV = "5c9049b0d846cadb95cd9c5ee473d1efcc425bf0"

LINUX_VERSION ?= "5.10.35"
