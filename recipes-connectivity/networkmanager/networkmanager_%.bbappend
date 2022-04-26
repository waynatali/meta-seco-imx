# In case MACHINE_FEATURES contains modem, this sets the build configuration of NetworkManager with ModemManager.
PACKAGECONFIG:append = " \
	${@bb.utils.contains('MACHINE_FEATURES', 'modem', ' modemmanager ', '', d)} \
"
