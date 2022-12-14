SUMMARY = "Bluetooth firmware for BCM43455"
HOMEPAGE = "https://github.com/murata-wireless/cyw-bt-patch"
LICENSE = "Firmware-cypress-bcm43455"
LIC_FILES_CHKSUM = "file://LICENCE.cypress;md5=cbc5f665d04f741f1e006d2096236ba7"

NO_GENERIC_LICENSE[Firmware-cypress-bcm43455] = "LICENCE.cypress"

inherit allarch

SRC_URI = "git://github.com/murata-wireless/cyw-bt-patch;protocol=https;branch=master"
SRCREV = "9d040c25688071d3b53ee918e5af55a79e00c3c0"

S = "${WORKDIR}/git"

PACKAGES =+ "${PN}-cypress-license"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/brcm/
	install -m 644 ${S}/BCM4345C0_003.001.025.0172.0344.1MW.hcd ${D}${nonarch_base_libdir}/firmware/brcm/BCM4345C0.hcd
	install -m 644 ${S}/LICENCE.cypress ${D}${nonarch_base_libdir}/firmware/LICENCE.cypress_bcm43455

	# another expected firmware location is etc/firmware
	install -d ${D}${base_prefix}/etc/firmware/brcm
	install -m 644 ${S}/BCM4345C0_003.001.025.0172.0344.1MW.hcd ${D}${base_prefix}/etc/firmware/brcm/BCM4345C0.hcd
	install -m 644 ${S}/LICENCE.cypress ${D}${base_prefix}/etc/firmware/LICENCE.cypress_bcm43455
}

LICENSE:${PN} = "Firmware-cypress-bcm43455"
LICENSE:${PN}-cypress-license = "Firmware-cypress-bcm43455"

FILES:${PN}-cypress-license = "${nonarch_base_libdir}/firmware/LICENCE.cypress_bcm43455"
FILES:${PN} = " \
	${nonarch_base_libdir}/firmware/ \
	${base_prefix}/etc/firmware/ \
"

RDEPENDS:${PN} += "${PN}-cypress-license"

# Firmware files are generally not ran on the CPU, so they can be
# allarch despite being architecture specific
INSANE_SKIP = "arch"
