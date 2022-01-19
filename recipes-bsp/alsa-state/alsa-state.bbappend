# Append path for seco layer to include alsa-state asound.conf
FILESEXTRAPATHS:prepend:seco-imx8mm-c61 := "${THISDIR}/${PN}/imx:"

PACKAGE_ARCH:seco-imx8mm-c61 = "${MACHINE_ARCH}"
