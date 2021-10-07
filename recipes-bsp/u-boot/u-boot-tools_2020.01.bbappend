require u-boot-seco-imx-common.inc

EXTRA_OEMAKE_class-target += 'CONFIG_EFI_HAVE_CAPSULE_SUPPORT=y'
EXTRA_OEMAKE_class-native += 'CONFIG_EFI_HAVE_CAPSULE_SUPPORT=y'
EXTRA_OEMAKE_class-nativesdk += 'CONFIG_EFI_HAVE_CAPSULE_SUPPORT=y'

do_install_append () {
	# mkeficapsule
	install -m 0755 tools/mkeficapsule ${D}${bindir}/uboot-mkeficapsule
	ln -sf uboot-mkeficapsule ${D}${bindir}/mkeficapsule
}

FILES_${PN}-mkimage += " ${bindir}/uboot-mkeficapsule ${bindir}/mkeficapsule "
