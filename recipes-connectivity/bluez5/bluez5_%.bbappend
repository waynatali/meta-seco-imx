FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " \	
	file://audio.conf \	
"

do_install:append() {

	# Add compatibility mode to provide deprecated command line interfaces 
	if [ -f ${D}${base_libdir}/systemd/system/bluetooth.service ]; then
		sed -i 's|ExecStart=/usr/libexec/bluetooth/bluetoothd|ExecStart=/usr/libexec/bluetooth/bluetoothd --compat|g' \
			${D}${base_libdir}/systemd/system/bluetooth.service
	fi
	
	install -m 0644 ${WORKDIR}/audio.conf ${D}/${sysconfdir}/bluetooth/audio.conf
}
