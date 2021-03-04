LICENSE = "CLOSED"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# For brcm43455 firmware
SRC_URI += " \
	file://firmware/brcm/brcmfmac43455-sdio.txt \
"

# Install addition firmwares
do_install_append() {
    install -d ${D}/lib/firmware
	cp -r ${WORKDIR}/firmware ${D}/lib/
}

FILES_${PN} = " \
  /lib/firmware/brcm/brcmfmac43455-sdio.txt \
"

