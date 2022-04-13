#
# This class provides an additional support to build the boot container for
# i.MX8M derivatives.
#
inherit imx-boot-container

do_resolve_and_populate_optee_binaries() {
    if [ -n "${UBOOT_CONFIG}" ]; then
        for config in ${UBOOT_MACHINE}; do
            i=$(expr $i + 1);
            for type in ${UBOOT_CONFIG}; do
                j=$(expr $j + 1);
                if [ $j -eq $i ]; then
                    if [ -n "${ATF_MACHINE_NAME}" ]; then
                        cp ${DEPLOY_DIR_IMAGE}/tee.${OPTEE_PLATFORM_FLAVOR}.bin ${B}/${config}/tee.bin
                    else
                        bberror "bl32 binary is undefined, result binary would be unusable!"
                    fi
                fi
            done
            unset  j
        done
        unset  i
    fi
}
addtask do_resolve_and_populate_optee_binaries before do_compile after do_configure

