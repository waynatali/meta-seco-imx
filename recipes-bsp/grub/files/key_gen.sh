#!/bin/bash

if test -f "KEK.key"; then
	rm KEK.*
	rm PK.*
	rm db.*
fi

GUID=$(python3 -c 'import uuid; print(str(uuid.uuid4()))')

# create platform key
openssl req -x509 -sha256 -newkey rsa:2048 -subj /CN=TEST_PK/ -keyout PK.key -out PK.crt -nodes -days 3650
cert-to-efi-sig-list -g $GUID PK.crt PK.esl
sign-efi-sig-list -t "2020-10-10 08:15:42" -c PK.crt -k PK.key PK PK.esl PK.auth

# create key exchange key
openssl req -x509 -sha256 -newkey rsa:2048 -subj /CN=TEST_KEK/ -keyout KEK.key -out KEK.crt -nodes -days 3650
cert-to-efi-sig-list -g $GUID KEK.crt KEK.esl
sign-efi-sig-list -t "2020-10-10 09:15:42" -c PK.crt -k PK.key KEK KEK.esl KEK.auth

# create whitelist database
openssl req -x509 -sha256 -newkey rsa:2048 -subj /CN=TEST_db/ -keyout db.key -out db.crt -nodes -days 3650
cert-to-efi-sig-list -g $GUID db.crt db.esl
sign-efi-sig-list -t "2021-09-04 08:15:42" -c KEK.crt -k KEK.key db db.esl db.auth

#sign grub-efi image
if test -f "grub-efi-bootaa64.efi"; then
	sbsign --key db.key --cert db.crt --output grub-efi-bootaa64.efi grub-efi-bootaa64.efi
fi
