# Copyright (C) 2017-2021 NXP

SUMMARY = "OPTEE OS"
DESCRIPTION = "OPTEE OS"
HOMEPAGE = "http://www.optee.org/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c1f21c4f72f372ef38a5a4aee55ec173"

DEPENDS = "python3-pycryptodomex-native python3-pyelftools-native u-boot-mkimage-native"

SRCBRANCH = "lf-5.10.y_2.0.0"
OPTEE_OS_SRC ?= "git://source.codeaurora.org/external/imx/imx-optee-os.git;protocol=https"
SRC_URI = " \
     ${OPTEE_OS_SRC};branch=${SRCBRANCH} \
     file://0001-optee-os-add-support-4Gb-RAM-for-tzasc.patch \
     file://0002-optee-os-add-support-4Gb-RAM.patch \
"
SRCREV = "2c1092df437bda9a9856a278efcb130e8e8f6884"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build.${PLATFORM_FLAVOR}"

inherit deploy python3native autotools

PLATFORM_FLAVOR             = "mx8mmevk"
PLATFORM_FLAVOR_mx8mm             = "mx8mmevk"

OPTEE_ARCH ?= "arm32"
OPTEE_ARCH_armv7a = "arm32"
OPTEE_ARCH_aarch64 = "arm64"

# Optee-os can be built for 32 bits and 64 bits at the same time
# as long as the compilers are correctly defined.
# For 64bits, CROSS_COMPILE64 must be set
# When defining CROSS_COMPILE and CROSS_COMPILE64, we assure that
# any 32 or 64 bits builds will pass
EXTRA_OEMAKE = " \
    PLATFORM=imx \
    PLATFORM_FLAVOR=${PLATFORM_FLAVOR} \
    CROSS_COMPILE=${HOST_PREFIX} \
    CROSS_COMPILE64=${HOST_PREFIX} \
    CFG_DDR_SIZE=0xC0000000 \
    CFG_DRAM_SIZE_4GB=y \
    -C ${S} O=${B} \
"

LDFLAGS = ""
CFLAGS += "--sysroot=${STAGING_DIR_HOST}"
CXXFLAGS += "--sysroot=${STAGING_DIR_HOST}"

do_compile () {
    unset LDFLAGS
    export CFLAGS="${CFLAGS} --sysroot=${STAGING_DIR_HOST}"
    oe_runmake all CFG_TEE_TA_LOG_LEVEL=0 CFG_TEE_CORE_LOG_LEVEL=0
}

do_deploy () {
    install -d ${DEPLOYDIR}
    ${TARGET_PREFIX}objcopy -O binary ${B}/core/tee.elf ${DEPLOYDIR}/tee.${PLATFORM_FLAVOR}.bin
    ln -sf tee.${PLATFORM_FLAVOR}.bin ${DEPLOYDIR}/tee.bin

    if [ "${OPTEE_ARCH}" != "arm64" ]; then
        IMX_LOAD_ADDR=`${TARGET_PREFIX}readelf -h ${B}/core/tee.elf | grep "Entry point address" | awk '{print $4}'`
        uboot-mkimage -A arm -O linux -C none -a ${IMX_LOAD_ADDR} -e ${IMX_LOAD_ADDR} \
            -d ${DEPLOYDIR}/tee.${PLATFORM_FLAVOR}.bin ${DEPLOYDIR}/uTee-${OPTEE_BIN_EXT}
    fi
}

do_install () {
    install -d ${D}${nonarch_base_libdir}/firmware/
    install -m 644 ${B}/core/*.bin ${D}${nonarch_base_libdir}/firmware/

    # Install the TA devkit
    install -d ${D}${includedir}/optee/export-user_ta_${OPTEE_ARCH}/

    for f in ${B}/export-ta_${OPTEE_ARCH}/*; do
        cp -aR $f ${D}${includedir}/optee/export-user_ta_${OPTEE_ARCH}/
    done

    # Install embedded TAs
    install -d ${D}${base_libdir}/optee_armtz
    find ${B}/ta -name '*.ta' | while read name; do
        install -m 444 $name ${D}${base_libdir}/optee_armtz/
    done
}

addtask deploy after do_compile before do_install


FILES_${PN} = "${nonarch_base_libdir}/firmware/ /lib*/optee_armtz/"
FILES_${PN}-staticdev = "/usr/include/optee/"
RDEPENDS_${PN}-dev += "${PN}-staticdev"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(imx|imx-boot-container|mx8)"
