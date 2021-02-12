SUMMARY = "Flutter - Dart based UI framework SDK"
WEBSITE = "https://flutter.dev/"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1d84cf16c48e571923f837136633a265"

PV = "1.26.0"
SRC_URI = "https://storage.googleapis.com/flutter_infra/releases/beta/linux/flutter_linux_${PV}-17.5.pre-beta.tar.xz"
SRC_URI[md5sum] = "d0baffe4bc3e16e9b26425343d27797f"

S = "${WORKDIR}/flutter"

do_install() {

    install -d ${D}${datadir}/flutter/sdk
    cp -rTv ${S}/. ${D}${datadir}/flutter/sdk
}

FILES_${PN}-dev = "${datadir}/flutter/sdk/*"

INSANE_SKIP_${PN} = "already-stripped"

BBCLASSEXTEND =+ " native nativesdk"
