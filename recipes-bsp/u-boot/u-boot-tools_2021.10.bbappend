require u-boot-seco-imx-common.inc

EXTRA_OEMAKE:class-target += 'CONFIG_EFI_HAVE_CAPSULE_SUPPORT=y'
EXTRA_OEMAKE:class-native += 'CONFIG_EFI_HAVE_CAPSULE_SUPPORT=y'
EXTRA_OEMAKE:class-nativesdk += 'CONFIG_EFI_HAVE_CAPSULE_SUPPORT=y'

do_install:append () {
	# mkeficapsule
	install -m 0755 tools/mkeficapsule ${D}${bindir}/uboot-mkeficapsule
	ln -sf uboot-mkeficapsule ${D}${bindir}/mkeficapsule
}

FILES:${PN}-mkimage += " ${bindir}/uboot-mkeficapsule ${bindir}/mkeficapsule "
