SUMMARY = "Flutter - Dart based UI framework SDK"
WEBSITE = "https://flutter.dev/"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=343c5a032261c8c32d621412cdcd70a8"

PV = "1.22.6"
SRC_URI = "https://storage.googleapis.com/flutter_infra/releases/stable/linux/flutter_linux_${PV}-stable.tar.xz"
SRC_URI[md5sum] = "c56bb0521bed218ec8cb262c89dc22b9"

S = "${WORKDIR}/flutter"

do_install() {

    install -d ${D}${datadir}/flutter/sdk
    cp -rTv ${S}/. ${D}${datadir}/flutter/sdk
}

FILES_${PN}-dev = "${datadir}/flutter/sdk/*"

INSANE_SKIP_${PN} = "already-stripped"

BBCLASSEXTEND =+ " native nativesdk"
