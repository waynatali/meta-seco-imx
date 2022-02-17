
EXTRA_OEMAKE:append:seco-imx8mm-c61-4gb = "${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'BL32_BASE=0xfe000000', '', d)}"

