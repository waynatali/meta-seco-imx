FILESEXTRAPATHS_prepend := "${THISDIR}/imx-boot:"
SRC_URI += " \
            file://0001-imx-boot-RAM-4G.patch \
            file://0002-imx-boot-fit-fix-node-name.patch \
            file://capsule1.its"

do_deploy_append() {
	cp ${STAGING_DIR_NATIVE}/${bindir}/mkeficapsule ${BOOT_STAGING}
	cp ../capsule1.its ${BOOT_STAGING}
	cd ${BOOT_STAGING}
	./mkimage_uboot -f capsule1.its capsule1.itb
	./mkeficapsule --fit capsule1.itb --index 1 capsule1.bin
	cd -
	install -m 0755 ${BOOT_STAGING}/capsule1.itb ${DEPLOYDIR}/${BOOT_TOOLS}
	install -m 0755 ${BOOT_STAGING}/mkeficapsule ${DEPLOYDIR}/${BOOT_TOOLS}
	install -m 0755 ${BOOT_STAGING}/capsule1.bin ${DEPLOYDIR}
}
