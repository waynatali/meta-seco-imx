FILESEXTRAPATHS:prepend := "${THISDIR}/imx-boot:"
SRC_URI:append = " file://capsule1.its "
SRC_URI:append:seco-imx8mm-c61-4gb = " file://0001-imx-boot-RAM-4G.patch "

do_deploy:append() {
	cp ${STAGING_DIR_NATIVE}/${bindir}/mkeficapsule ${BOOT_STAGING}
	cp ../capsule1.its ${BOOT_STAGING}
	cd ${BOOT_STAGING}
	mkimage -f capsule1.its capsule1.itb
	./mkeficapsule --fit capsule1.itb --index 1 capsule1.bin
	cd -
	install -m 0755 ${BOOT_STAGING}/capsule1.itb ${DEPLOYDIR}/${BOOT_TOOLS}
	install -m 0755 ${BOOT_STAGING}/mkeficapsule ${DEPLOYDIR}/${BOOT_TOOLS}
	install -m 0755 ${BOOT_STAGING}/capsule1.bin ${DEPLOYDIR}
}
