# Append path for seco layer to include alsa-state asound.conf
FILESEXTRAPATHS_prepend_seco-imx8mm-c61 := "${THISDIR}/${PN}/imx:"

PACKAGE_ARCH_seco-imx8mm-c61 = "${MACHINE_ARCH}"
