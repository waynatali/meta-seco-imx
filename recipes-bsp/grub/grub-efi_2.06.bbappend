FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://cfg \
            file://key_gen.sh"

DEPENDS += " openssl efitools-native sbsigntool-native "

do_sign() {
	cd ${B}
	cp ../key_gen.sh .
	chmod a+x key_gen.sh
	./key_gen.sh
}

addtask sign after do_mkimage before do_install

do_sign_class-native() {
	:
}

do_deploy_append_class-target() {
	install -m 644 ${B}/PK.auth ${DEPLOYDIR}
	install -m 644 ${B}/KEK.auth ${DEPLOYDIR}
	install -m 644 ${B}/db.auth ${DEPLOYDIR}
	install -m 644 ${B}/db.crt ${DEPLOYDIR}
	install -m 644 ${B}/db.key ${DEPLOYDIR}
}
