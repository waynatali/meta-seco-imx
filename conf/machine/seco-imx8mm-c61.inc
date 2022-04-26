
MACHINEOVERRIDES =. "mx8:mx8m:mx8mm:seco-imx8mm-c61:"
IMX_DEFAULT_BSP = "mainline"

require conf/machine/include/imx-base.inc
require conf/machine/include/arm/armv8a/tune-cortexa53.inc

MACHINE_FEATURES += " pci optee pulseaudio modem"

KERNEL_DEVICETREE_BASENAME = "seco-imx8mm-c61"

KERNEL_DEVICETREE_OVERLAYS ?= " \
    seco/overlays/seco-imx8mm-c61-video-sn65dsi86.dtbo \
    seco/overlays/seco-imx8mm-c61-video-sn65dsi84.dtbo \
    seco/overlays/seco-imx8mm-c61-ov5640.dtbo \
    seco/overlays/seco-imx8mm-c61-port1-can.dtbo \
    seco/overlays/seco-imx8mm-c61-port1-rs232.dtbo \
    seco/overlays/seco-imx8mm-c61-port1-rs485.dtbo \
    seco/overlays/seco-imx8mm-c61-port1-gpios.dtbo \
    seco/overlays/seco-imx8mm-c61-port2-can.dtbo \
    seco/overlays/seco-imx8mm-c61-port2-rs232.dtbo \
    seco/overlays/seco-imx8mm-c61-port2-rs485.dtbo \
    seco/overlays/seco-imx8mm-c61-port2-gpios.dtbo \
    seco/overlays/seco-imx8mm-c61-modem.dtbo \
"
# The image will contain the currently supported FDT overlay blob files
KERNEL_DEVICETREE_OVERLAYS = " \
    seco/overlays/seco-imx8mm-c61-video-sn65dsi84.dtbo \
    ${@bb.utils.contains('MACHINE_FEATURES', 'modem', 'seco/overlays/seco-imx8mm-c61-modem.dtbo', '', d)} \
"
KERNEL_DEVICETREE = " \
    seco/${KERNEL_DEVICETREE_BASENAME}.dtb \
    ${KERNEL_DEVICETREE_OVERLAYS} \
"
PREFERRED_PROVIDER_virtual/kernel = "linux-seco"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-astrid"
PREFERRED_PROVIDER_u-boot = "u-boot-astrid"

UBOOT_CONFIG ??= "emmc"
UBOOT_CONFIG_BASENAME = "seco_imx8mm_c61"
UBOOT_CONFIG[emmc] = "${UBOOT_CONFIG_BASENAME}_defconfig"
SPL_BINARY = "spl/u-boot-spl.bin"

# Set atf for imx-boot
ATF_MACHINE_NAME = "bl31-imx8mm.bin"
IMX_BOOT_SOC_TARGET = "iMX8MM"
ATF_PLATFORM = "imx8mm"
ATF_LOAD_ADDR = "0x920000"

OPTEE_PLATFORM_FLAVOR = "mx8mmevk"

# Set u-boot DTB
UBOOT_DTB_NAME = "seco-imx8mm-c61.dtb"

DDR_FIRMWARE_NAME = " \
	lpddr4_pmu_train_1d_imem.bin \
	lpddr4_pmu_train_1d_dmem.bin \
	lpddr4_pmu_train_2d_imem.bin \
	lpddr4_pmu_train_2d_dmem.bin \
"

IMXBOOT_TARGETS = "flash_evk"

# Set Serial console
SERIAL_CONSOLES = "115200;ttymxc1"

LOADADDR = ""
UBOOT_SUFFIX = "bin"
UBOOT_MAKE_TARGET = ""
IMX_BOOT_SEEK = "33"

PREFERRED_VERSION_optee-os:mx8-nxp-bsp     = "3.13.0.imx"
PREFERRED_VERSION_optee-client:mx8-nxp-bsp = "3.13.0.imx"
PREFERRED_VERSION_optee-test:mx8-nxp-bsp   = "3.13.0.imx"
OPTEE_BIN_EXT = "8mm"

EFI_PROVIDER = "grub-efi"

IMAGE_INSTALL:append = " \
	alsa-utils \
	alsa-lib \
	bluez-alsa \
	${@bb.utils.contains('MACHINE_FEATURES', 'optee', ' optee-os optee-client ', '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'pulseaudio', ' pulseaudio-server pulseaudio-misc pulseaudio-module-alsa-card pulseaudio-module-alsa-source pulseaudio-module-alsa-sink pulseaudio-module-dbus-protocol pulseaudio-module-switch-on-connect pulseaudio-module-bluetooth-discover pulseaudio-module-bluez5-discover pulseaudio-module-bluez5-device pulseaudio-module-bluetooth-policy ', '', d)} \
	${@bb.utils.contains('EFI_PROVIDER', 'grub-efi', 'grub-efi grub-bootconf', '', d)} \
"
MACHINE_EXTRA_RRECOMMENDS:append = " linux-firmware-bcm43455 linux-firmware-bluetooth-bcm43455 "
WKS_FILE_DEPENDS:append = " sbsigntool-native"
