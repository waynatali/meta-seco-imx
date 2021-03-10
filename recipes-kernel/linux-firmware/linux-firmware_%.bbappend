# Add calibration file
SRC_URI_append_seco-imx8mm-c61 = " \
    git://github.com/murata-wireless/cyw-fmac-fw.git;protocol=https;nobranch=1;name=murata-fw;destsuffix=murata-fw \
    git://github.com/murata-wireless/cyw-fmac-nvram.git;protocol=https;nobranch=1;name=murata-nvram;destsuffix=murata-nvram \
"
SRCREV_murata-fw    = "ba140e42c3320262fc52e185c3af93eeb10117df"
SRCREV_murata-nvram = "8710e74e79470f666912c3ccadf1e354d6fb209c"
SRCREV_FORMAT = "linux-firmware-murata"

do_install_append_seco-imx8mm-c61() {
   #take newest murata firmware
   install -m 0644 ${WORKDIR}/murata-fw/cyfmac43455-sdio.bin ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac43455-sdio.bin
   install -m 0644 ${WORKDIR}/murata-fw/cyfmac43455-sdio.1MW.clm_blob ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac43455-sdio.clm_blob
   install -m 0644 ${WORKDIR}/murata-nvram/cyfmac43455-sdio.1MW.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac43455-sdio.txt
}

FILES_${PN}-bcm43455_append_seco-imx8mm-c61 = " \
  ${nonarch_base_libdir}/firmware/brcm/brcmfmac43430-sdio.clm_blob \
  ${nonarch_base_libdir}/firmware/brcm/brcmfmac43455-sdio.txt \
  ${nonarch_base_libdir}/firmware/brcm/brcmfmac43455-sdio.bin \
"
RDEPENDS_${PN}-bcm43455_remove_seco-imx8mm-c61 = " ${PN}-cypress-license "

RRECOMMENDS_${PN}-bcm43455_append_seco-imx8mm-c61 += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'wifi-suspend', '', d)}"
