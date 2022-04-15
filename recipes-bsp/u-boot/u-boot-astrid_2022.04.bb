require recipes-bsp/u-boot/u-boot.inc
require u-boot-astrid_${PV}.inc

DESCRIPTION = "U-Boot based on mainline U-Boot with board support patches to apply."

inherit auto-patch
PATCHPATH = "${CURDIR}/u-boot-astrid"

inherit ${@oe.utils.ifelse(d.getVar('UBOOT_PROVIDES_BOOT_CONTAINER') == '1', 'imx-boot-container-ext', '')}

do_resolve_and_populate_optee_binaries[depends] += " optee-os:do_deploy "

# Location where U-Boot artifacts should be additionally deployed.

BOOT_TOOLS = "imx-boot-tools"

PROVIDES += "u-boot"

B = "${WORKDIR}/build"

# FIXME: Allow linking of 'tools' binaries with native libraries
#        used for generating the boot logo and other tools used
#        during the build process.
EXTRA_OEMAKE += 'HOSTCC="${BUILD_CC} ${BUILD_CPPFLAGS}" \
                 HOSTLDFLAGS="${BUILD_LDFLAGS}" \
                 HOSTSTRIP=true'

do_configure:prepend:seco-imx8mm-c61-4gb() {
        sed -i -e 's,0xbe000000,0xfe000000,g'  ${S}/board/seco/seco_imx8mm_c61/Kconfig

        sed -i -e 's,default n,default y,g'  ${S}/board/seco/seco_imx8mm_c61/Kconfig
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(seco-imx8mm-c61)"
