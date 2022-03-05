SUMMARY = "Linux Kernel provided and supported by SECO"
DESCRIPTION = "Linux Kernel provided and supported by SECO with focus on \
SECO Boards. It includes support for many IPs such as GPU, VPU and IPU."


require recipes-kernel/linux/linux-imx.inc

include linux-seco-src-5.4.70.inc


DEPENDS += "lzop-native bc-native"

COMPATIBLE_MACHINE = "(mx6-nxp-bsp|mx7-nxp-bsp|mx8-nxp-bsp)"
EXTRA_OEMAKE:append:mx6-nxp-bsp = " ARCH=arm"
EXTRA_OEMAKE:append:mx8-nxp-bsp = " ARCH=arm64"

KBUILD_DEFCONFIG:seco-imx8mm-c61 = "seco_imx8_linux_defconfig"


do_kernel_localversion:prepend() {
    install -d ${B}
    cp ${S}/arch/${ARCH}/configs/${KBUILD_DEFCONFIG} ${WORKDIR}/defconfig
}
