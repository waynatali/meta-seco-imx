do_install:append() {

	# Fix this warning: [pulseaudio] protocol-native.c: Denied access to client with invalid authentication data. 
	if [ -f ${D}${sysconfdir}/pulse/system.pa ]; then
		sed -i 's/load-module module-native-protocol-unix/load-module module-native-protocol-unix auth-anonymous=1/g' \
			${D}${sysconfdir}/pulse/system.pa
	fi
	
	# Set default sample rate to 48kHz and remove the semicolon at the beginning of the line to enable it.
	if [ -f ${D}${sysconfdir}/pulse/daemon.conf ]; then
		sed -i 's/; default-sample-rate = 44100/default-sample-rate = 48000/g' \
			${D}${sysconfdir}/pulse/daemon.conf;
	fi
}
