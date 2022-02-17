meta-seco-imx
===============

This README file contains information on building and booting
meta-seco-imx BSP layers.  Please see the corresponding sections below
for details.


Yocto Project Compatible
========================

The BSPs contained in this layer are compatible with the Yocto Project
as per the requirements listed here:

  https://www.yoctoproject.org/webform/yocto-project-compatible-registration


Tested Hardware
================

This layer has been tested with the following MACHINE variables:

  - `seco-imx8mm-c61-2gb` (SBC-SECO-C61 2GB RAM)
  - `seco-imx8mm-c61-4gb` (SBC-SECO-C61 4GB RAM)

The differentiation is due to OP-TEE configuration that needs a 
fixed memory mapping prior to boot.

Dependencies
============

This layer depends on:

  URI: git://git.openembedded.org/bitbake
  branch: master

  URI: git://git.openembedded.org/openembedded-core
  layers: meta
  branch: honister

  URI: git://github.com/freescale/meta-freescale
  layers: meta
  branch: master (honister)

  URI: https://git.yoctoproject.org/meta-intel
  branch: master


Configuration
=============

1. Clone the `meta-seco-imx` layer to your project directory.
2. Add the `meta-seco-imx` layer to `conf/bblayers.conf`
```bitbake
	BBLAYERS += "path/to/meta-seco-imx"
```
3. Add dependency layers to `conf/bblayers.conf`
```bitbake
	BBLAYERS += "path/to/meta-freescale"
```

4. Configure the variable MACHINE in your local.conf.
```bitbake
	MACHINE ?= "seco-imx8mm-c61-2gb"
```
 or while using bitbake:
```bash
	MACHINE="seco-imx8mm-c61-2gb" bitbake core-image-minimal
```

 
Credits
=======

Based on the `meta-freescale` layer from [freescale][freescale].

[freescale]: https://git.yoctoproject.org/cgit/cgit.cgi/meta-freescale/
