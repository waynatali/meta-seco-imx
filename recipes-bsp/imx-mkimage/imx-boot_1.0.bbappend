# With this patch we will override default TEE_LOAD_ADDR
FILESEXTRAPATHS_prepend := "${THISDIR}/imx-boot:"
SRC_URI += "file://0001-imx-boot-RAM-4G.patch"
